package dev.julian.minitestdspot.viewmodels

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.julian.minitestdspot.data.User
import dev.julian.minitestdspot.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val ITEMS_PER_PAGE = 50

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    fun searchUser(query: String): Flow<PagingData<User>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { repository.userPagingSource(query) }
    )
        .flow
        .cachedIn(viewModelScope)
}
