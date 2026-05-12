# Security policy — PADELAS

This policy applies to the **PADELAS** codebase hosted in the **Padel-analytics** GitHub repository.

## Reporting a vulnerability

Please **do not** open a public issue for security-sensitive reports.

Use [GitHub Security advisories](https://github.com/Ziedfilali/Padel-analytics/security/advisories/new) for this repository so the maintainers can review the report privately.

## Scope

- Landing branch: `master` (README, license, policies).
- Application code: feature branches listed in the [README](README.md). Review each branch before deployment; never commit real `.env` files, production database passwords, or webhook URLs.

## Secrets

If you accidentally committed a secret, revoke or rotate it immediately, remove it from git history (e.g. `git filter-repo` or GitHub support), and consider enabling [secret scanning](https://docs.github.com/code-security/secret-scanning) on the organization or account.
