package com.ms.quizapp.data.network

class Apis {

    companion object {

        val BASE_URL = ""
        private val appMode = AppMode.RELEASE
        const val FOOD_FILTER = "filter.php"
        const val USER_LIST = "user"


        fun getBaseUrl(): String {
            return when (appMode) {
                AppMode.DEV -> {
                    "https://dummyapi.io/data/v1/"
                }
                AppMode.RELEASE -> {
                    "https://dummyapi.io/data/v1/"
                }
                AppMode.LOCAL -> {
                    "https://dummyapi.io/data/v1/"
                }
                else -> {
                    ""
                }
            }
        }


        enum class AppMode {
            DEV, RELEASE, QA, LOCAL
        }

    }
}