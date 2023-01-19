package com.dicoding.picodiploma.testsuitmedia.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dicoding.picodiploma.testsuitmedia.api.API
import com.dicoding.picodiploma.testsuitmedia.dataclass.ListUserItem
import retrofit2.HttpException
import okio.IOException

class UserPagingSource(private val apiService: API) :
    PagingSource<Int, ListUserItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListUserItem> {

        return try {

            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getUser(page, params.loadSize).data

            LoadResult.Page(
                data = responseData,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseData.isNullOrEmpty()) null else page + 1
            )

        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ListUserItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}