"""
POWER BI RLS SETUP (do this once in Power BI Desktop):
1. Open your .pbix file in Power BI Desktop
2. Go to Modeling tab → Manage Roles
3. Create these roles with DAX filters:

   Role: Qatar_Manager
   Table: your_players_table → [country] = "Qatar"
   Table: your_courts_table → [country] = "Qatar"
   Table: your_tournaments_table → [country] = "Qatar"

   Role: France_Manager
   Same pattern with "France"

   (repeat for each country)

   Role: Admin → no filters (leave empty)

4. Publish the updated report to Power BI Service
5. In Power BI Service → your dataset → Security → assign Microsoft accounts to each role
   The logged-in Microsoft account (via autoAuth) will be matched to its RLS role automatically
"""

import os

from fastapi import APIRouter, Depends

from routers.auth import get_current_user

router = APIRouter()
current_user_dep = Depends(get_current_user)

REPORT_ID = "c0332af6-03e7-4cbe-b297-e3d05e89bee2"
CTID = "604f1a96-cbe8-43f8-abbf-f8eaf5d85730"
DEFAULT_EMBED_URL = (
    f"https://app.powerbi.com/reportEmbed?reportId={REPORT_ID}" f"&autoAuth=true&ctid={CTID}"
)


@router.get("/embed-config")
def embed_config(user: dict = current_user_dep):
    """
    Returns embed URL and app role for UI labels.
    RLS is enforced in Power BI Service for the signed-in Microsoft account.
    """
    role = user["role"]
    # Admin: no named RLS role filter in UI (full access in Power BI if account allows)
    rls_role = None if role == "Admin" else role
    return {
        "embedUrl": os.getenv("POWERBI_EMBED_URL", DEFAULT_EMBED_URL),
        "role": rls_role,
        "username": user["username"],
        "reportId": REPORT_ID,
    }
