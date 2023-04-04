package com.ms.quizapp.data.network.dataloadstatus

class DataLoadStatus<T> private constructor(
    val dataLoadStatusEnum: DataLoadStatusEnum,
    val data: T?,
    val error: DataLoadError?,
    val mainadapterposorid: Int = -1,
    val message: String = ""//or send id
) {
    companion object {
        fun <T> idle() = DataLoadStatus(DataLoadStatusEnum.IDLE, null, null) as DataLoadStatus<T>
        fun <T> started(mainadapterposorid: Int = -1) = DataLoadStatus(
            DataLoadStatusEnum.STARTED,
            null,
            null,
            mainadapterposorid
        ) as DataLoadStatus<T>

        fun <T> success(data: T, mainadapterposorid: Int = -1) =
            DataLoadStatus(
                DataLoadStatusEnum.SUCCESS,
                data,
                null,
                mainadapterposorid
            )

        fun <T> fail(message: String, mainadapterposorid: Int = -1) = DataLoadStatus(
            DataLoadStatusEnum.FAILED,
            null,
            DataLoadError(DataLoadErrorEnum.API_ERROR, message),
            mainadapterposorid
        ) as DataLoadStatus<T>

    }
}