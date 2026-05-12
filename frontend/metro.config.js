const { getDefaultConfig } = require('expo/metro-config');

const config = getDefaultConfig(__dirname);

config.resolver.sourceExts.push('cjs');

// Docker / CI: one Metro worker avoids RAM spikes that crash BuildKit on Windows (RPC EOF).
if (process.env.CI === '1' || process.env.METRO_MAX_WORKERS === '1') {
  config.maxWorkers = 1;
}

module.exports = config;
