package com.ms.quizapp.data.database

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ms.quizapp.data.models.Question

 class QuestionPagingSource (private val dao: QuizDao): PagingSource<Int, Question>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Question> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        val offset = (page - 1) * pageSize

        return try {
            val questions = dao.getQuestions(offset, pageSize)
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (questions.isEmpty()) null else page + 1
            LoadResult.Page(questions, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Question>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}