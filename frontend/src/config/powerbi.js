/**
 * Power BI embed (report URL + autoAuth). Defaults work without .env at build/runtime.
 */

const DEFAULT_EMBED_URL =
  'https://app.powerbi.com/reportEmbed?reportId=c0332af6-03e7-4cbe-b297-e3d05e89bee2&autoAuth=true&ctid=604f1a96-cbe8-43f8-abbf-f8eaf5d85730';

const DEFAULT_REPORT_ID = 'c0332af6-03e7-4cbe-b297-e3d05e89bee2';
const DEFAULT_CTID = '604f1a96-cbe8-43f8-abbf-f8eaf5d85730';

function readEnv(name) {
  if (typeof process === 'undefined' || process.env[name] === undefined || process.env[name] === null) {
    return '';
  }
  return String(process.env[name]).trim();
}

/** Prefer full embed URL from env; otherwise optional pieces; last resort built-in default. */
export function buildReportEmbedUrl() {
  const explicit = readEnv('EXPO_PUBLIC_POWERBI_EMBED_URL');
  if (explicit) return explicit;

  const reportId = readEnv('EXPO_PUBLIC_POWERBI_REPORT_ID') || DEFAULT_REPORT_ID;
  const ctid = readEnv('EXPO_PUBLIC_POWERBI_CTID') || DEFAULT_CTID;

  return `https://app.powerbi.com/reportEmbed?reportId=${reportId}&autoAuth=true&ctid=${ctid}`;
}

/** Display helpers (footer); workspace is optional — not always present in reportEmbed URLs. */
export const powerBiConfig = {
  get workspaceId() {
    return readEnv('EXPO_PUBLIC_POWERBI_WORKSPACE_ID') || '';
  },
  get reportId() {
    return readEnv('EXPO_PUBLIC_POWERBI_REPORT_ID') || DEFAULT_REPORT_ID;
  },
  get tenantId() {
    return readEnv('EXPO_PUBLIC_POWERBI_CTID') || DEFAULT_CTID;
  },
};

/** Names of missing env vars when you enable strict partial configuration (currently unused). */
export function missingPowerBiConfig() {
  return [];
}
