package com.advanced.githubrepos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.advanced.githubrepos.models.Item
import retrofit2.HttpException
import java.io.IOException

class MyRepository(private val api: Api) : PagingSource<Int, Item>() {

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {

        return try {
            val currentPage = params.key ?: 1
            val response = api.getReposByStars(currentPage)
            val data = response.body()?.items ?: emptyList()

            LoadResult.Page(
                data,
                if (currentPage == 1) null else currentPage - 1,
                currentPage.plus(1)
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


}