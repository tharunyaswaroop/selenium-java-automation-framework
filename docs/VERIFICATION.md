# Verification record

Date: 2026-10-01. Local execution only.

## Observed result

12 tests passed; 0 failed, 0 skipped in the final run.

## Command

```text
mvn -B -Dbrowser=edge verify
```

## Environment

Windows; JDK 24.0.1 compiling for Java 17; Maven 3.9.16; headless Edge 154.0.4258.48; two parallel methods.

## Limits and execution notes

Selenium Manager used a cached Edge driver after its discovery endpoint failed. CDP-version warnings occurred; these tests use WebDriver, not CDP. Chrome/Linux CI and a deliberate screenshot-failure probe were not run.

GitHub Actions and Jenkins were configured but not executed. No production, employer, or paid cloud system was tested. Generated reports are ignored rather than committed.
