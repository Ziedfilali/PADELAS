"""JWT auth for app users (separate from Power BI / Microsoft sign-in)."""

import os
from datetime import datetime, timedelta, timezone
from typing import Optional

import bcrypt
from fastapi import APIRouter, Depends, HTTPException, status
from fastapi.security import HTTPAuthorizationCredentials, HTTPBearer
from jose import JWTError, jwt
from pydantic import BaseModel, Field

router = APIRouter()
security = HTTPBearer(auto_error=False)
auth_credentials_dep = Depends(security)

JWT_SECRET = os.getenv("JWT_SECRET", "dev-secret")
JWT_ALGORITHM = "HS256"
ACCESS_TOKEN_EXPIRE_HOURS = 8


def _bcrypt_hash(plain_password: str) -> str:
    return bcrypt.hashpw(plain_password.encode("utf-8"), bcrypt.gensalt(rounds=12)).decode("utf-8")


def _bcrypt_verify(plain_password: str, password_hash: str) -> bool:
    try:
        return bcrypt.checkpw(
            plain_password.encode("utf-8"),
            password_hash.encode("utf-8"),
        )
    except (ValueError, TypeError):
        return False


# Hashed at import — verify uses bcrypt.checkpw (robust vs passlib/bcrypt drift).
USERS = {
    "admin": {
        "password_hash": _bcrypt_hash("admin123"),
        "role": "Admin",
        "display_name": "Administrator",
    },
    "qatar_mgr": {
        "password_hash": _bcrypt_hash("qatar123"),
        "role": "Qatar_Manager",
        "display_name": "Qatar Manager",
    },
    "france_mgr": {
        "password_hash": _bcrypt_hash("france123"),
        "role": "France_Manager",
        "display_name": "France Manager",
    },
    "argentina_mgr": {
        "password_hash": _bcrypt_hash("arg123"),
        "role": "Argentina_Manager",
        "display_name": "Argentina Manager",
    },
    "spain_mgr": {
        "password_hash": _bcrypt_hash("spain123"),
        "role": "Spain_Manager",
        "display_name": "Spain Manager",
    },
}


class LoginBody(BaseModel):
    username: str = Field(..., min_length=1)
    password: str = Field(..., min_length=1)


class TokenResponse(BaseModel):
    access_token: str
    token_type: str = "bearer"
    user: dict


class UserPublic(BaseModel):
    username: str
    role: str
    display_name: str


def _create_access_token(sub: str, role: str, display_name: str) -> str:
    expire_ts = int(
        (datetime.now(timezone.utc) + timedelta(hours=ACCESS_TOKEN_EXPIRE_HOURS)).timestamp()
    )
    payload = {
        "sub": sub,
        "role": role,
        "display_name": display_name,
        "exp": expire_ts,
    }
    return jwt.encode(payload, JWT_SECRET, algorithm=JWT_ALGORITHM)


def decode_token(token: str) -> dict:
    return jwt.decode(token, JWT_SECRET, algorithms=[JWT_ALGORITHM])


async def get_current_user(
    credentials: Optional[HTTPAuthorizationCredentials] = auth_credentials_dep,
) -> dict:
    if credentials is None or not credentials.credentials:
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Not authenticated",
            headers={"WWW-Authenticate": "Bearer"},
        )
    token = credentials.credentials
    try:
        payload = decode_token(token)
        username = payload.get("sub")
        if not username or username not in USERS:
            raise HTTPException(status_code=401, detail="Invalid user")
        u = USERS[username]
        return {
            "username": username,
            "role": u["role"],
            "display_name": payload.get("display_name") or u["display_name"],
        }
    except JWTError:
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Invalid or expired token",
            headers={"WWW-Authenticate": "Bearer"},
        )


current_user_dep = Depends(get_current_user)


@router.post("/login", response_model=TokenResponse)
def login(body: LoginBody):
    key = body.username.strip()
    user = USERS.get(key)
    if not user or not _bcrypt_verify(body.password, user["password_hash"]):
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Invalid username or password",
        )
    token = _create_access_token(
        key,
        user["role"],
        user["display_name"],
    )
    return TokenResponse(
        access_token=token,
        token_type="bearer",
        user={
            "username": key,
            "role": user["role"],
            "display_name": user["display_name"],
        },
    )


@router.get("/me", response_model=UserPublic)
def me(user: dict = current_user_dep):
    return UserPublic(
        username=user["username"],
        role=user["role"],
        display_name=user["display_name"],
    )
