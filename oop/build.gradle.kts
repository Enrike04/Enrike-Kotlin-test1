group = rootProject.group
version = rootProject.version

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom("${rootProject.projectDir}/config/detekt.yml")
}
