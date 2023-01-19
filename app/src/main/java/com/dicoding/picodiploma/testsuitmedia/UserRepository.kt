package com.dicoding.picodiploma.testsuitmedia

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.dicoding.picodiploma.testsuitmedia.api.API
import com.dicoding.picodiploma.testsuitmedia.data.UserPagingSource
import com.dicoding.picodiploma.testsuitmedia.dataclass.ListUserItem

class UserRepository(private val userDatabase: UserDatabase, private val apiService: API) {
    fun getUser(): LiveData<PagingData<ListUserItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }
}