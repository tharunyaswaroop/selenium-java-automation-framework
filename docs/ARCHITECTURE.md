# Framework decisions

- Settings resolves Java system properties, then QA_* environment variables, then committed defaults.
- Page objects own locators and waits; tests own business expectations. No implicit waits or Thread.sleep.
- One driver per test invocation stored in ThreadLocal. Surefire runs two methods concurrently; data rows stay isolated through fresh browsers.
- Fictional static checkout fixture is loaded from test resources with a file URL. It simulates presentation and calculation, not a real backend/gateway.
- Assertions use explicit independent expected values. CSV contains boundaries and invalid inputs.
- Failed tests save uniquely named PNGs before quitting; cleanup uses finally. TestNG reports capture test names and failures; CI archives evidence even when Maven fails.
- No retry mechanism hides flaky tests. Investigate locator, application, browser, driver, or environment failures separately.

Selenium Manager resolves a compatible driver on first execution. Internet access may be required. Alternatively provide a trusted driver using the standard webdriver.chrome.driver / webdriver.edge.driver Java properties. Browser installation and driver acquisition are separate prerequisites.

Scope limits: no authentication, live payment, database integration, browser grid, or accessibility certification. A static fixture makes runs deterministic but does not establish coverage against a commercial POS system.
