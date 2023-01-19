package com.dicoding.picodiploma.testsuitmedia

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.picodiploma.testsuitmedia.dataclass.ListUserItem
import com.dicoding.picodiploma.testsuitmedia.di.Injection

class MainViewModel3(storyRepository: UserRepository) : ViewModel() {

    val user: LiveData<PagingData<ListUserItem>> =
        storyRepository.getUser().cachedIn(viewModelScope)

    class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel3::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel3(Injection.provideRepository(context)) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}