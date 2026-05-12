# Build SPA bundle (web output: single) and serve with nginx.
FROM node:20-bookworm-slim AS build
WORKDIR /app

RUN apt-get update \
    && apt-get install -y --no-install-recommends ca-certificates \
    && rm -rf /var/lib/apt/lists/*

COPY package.json package-lock.json ./
RUN npm config set fetch-retries 10 \
    && npm config set fetch-retry-mintimeout 20000 \
    && npm config set fetch-retry-maxtimeout 120000 \
    && npm ci

COPY . .

# Expo config references ./assets/*.png; repo may omit them — minimal 1×1 PNG for build.
RUN mkdir -p assets \
    && printf '%s' 'iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mP8z8BQDwAEhQGAhKmMIQAAAABJRU5ErkJggg==' | base64 -d > assets/favicon.png \
    && cp assets/favicon.png assets/splash-icon.png

# Empty = bundled app calls relative `/api/*` → nginx proxies to model-service (any host/port for the SPA).
ARG EXPO_PUBLIC_API_BASE=
ENV EXPO_PUBLIC_API_BASE=${EXPO_PUBLIC_API_BASE}
ARG EXPO_PUBLIC_POWERBI_EMBED_URL=https://app.powerbi.com/reportEmbed?reportId=c0332af6-03e7-4cbe-b297-e3d05e89bee2&autoAuth=true&ctid=604f1a96-cbe8-43f8-abbf-f8eaf5d85730
ENV EXPO_PUBLIC_POWERBI_EMBED_URL=${EXPO_PUBLIC_POWERBI_EMBED_URL}
ENV CI=1
ENV METRO_MAX_WORKERS=1
ENV NODE_OPTIONS=--max-old-space-size=8192

RUN npx expo export -p web

FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY docker/nginx.default.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
