object Modules {
    const val app = ":app"
    const val data = ":data"
    const val domain = ":domain"
    const val mviCore = ":mvi-core"

    object Presentation {
        private const val presentation = "presentation"
        const val home = ":$presentation:home"
    }

    object Commons {
        private const val commons = "commons"
        const val kotlin = ":$commons:commons-kotlin"
        const val android = ":$commons:commons-android"
    }
}