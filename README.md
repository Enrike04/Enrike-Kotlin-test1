[![Test1](https://github.com/cscenter/Kotlin-test-1/actions/workflows/Test1.yml/badge.svg)](https://github.com/cscenter/Kotlin-test-1/actions/workflows/Test1.yml)

### Test #1

In this test you need to complete two tasks:
- [Task#1](./task1/README.md)
- [Task#2](./task2/README.md)

See the provided links for more details.

**If you have two red builds, you will receive half of the available points.**

Each project has tests that must be fully passed before the assignment is submitted. Additionally, the project has style checks configured by Detekt and Diktat that must be passed (using the Suppress annotation is prohibited).

To run the tests you could use predefined run configurations or run them manually:

* To run all tests locally you can use:`./gradlew test`;

* To run only task 1 tests locally, you can use:`./gradlew :basic:test`;

* To run only task 2 tests locally, you can use:`./gradlew :oop:test`;

* To run Detekt locally, you can use: `./gradlew detekt`;

* To run Diktat locally, you can use: `./gradlew diktat`.
