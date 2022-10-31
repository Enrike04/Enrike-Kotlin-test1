![Gradle build](https://github.com/cscenter/Kotlin-test-1/actions/workflows/gradle-build.yml/badge.svg)
![Task#1 tests](https://github.com/cscenter/Kotlin-test-1/actions/workflows/test-hagman.yml/badge.svg)
![Task#2 tests](https://github.com/cscenter/Kotlin-test-1/actions/workflows/test-photoshop.yml/badge.svg)
![Detekt](https://github.com/cscenter/Kotlin-test-1/actions/workflows/detekt.yml/badge.svg)
![Diktat](https://github.com/cscenter/Kotlin-test-1/actions/workflows/diktat.yml/badge.svg)

### Test #1

In this test you need to implement two tasks:
- [Task#1](./task1/README.md)
- [Task#2](./task2/README.md)

See the provided links to get more details.

**If you have two red build, the total points will be reduced halved**

Each project has tests that must be fully passed before submitting the assignment. 
In addition, the project has style checks configured by Detekt and 
Diktat that must be passed (using the Suppress annotation is prohibited).

To run all tests locally you can use:`./gradlew test`;

To run only task#1 tests locally you can use:`./gradlew :task1:test`;

To run only task#2 tests locally you can use:`./gradlew :task2:test`;

To run Detekt locally you can use: `./gradlew detektCheckAll`;

To run Diktat locally you can use: `./gradlew diktatCheckAll`.
