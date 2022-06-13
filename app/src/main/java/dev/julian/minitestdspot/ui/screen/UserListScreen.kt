package dev.julian.minitestdspot.ui.screen

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.julian.minitestdspot.data.User
import dev.julian.minitestdspot.viewmodels.UserViewModel

const val USER_LIST_TEST_TAG = "user_list_test_tag"
const val TAG = "USER_LIST_SCREEN"

@Composable
fun UserListScreen(viewModel: UserViewModel = hiltViewModel()) {
    val allUser : LazyPagingItems<User> = viewModel.searchUser("name,email,picture,id,location").collectAsLazyPagingItems()
    LazyColumn(modifier = Modifier.testTag(USER_LIST_TEST_TAG)){
        items(allUser){ user ->
            if (user != null)
                UserDetails(user)
            Divider()
        }

        allUser.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    repeat(15) {
                        item {
                            Log.i(TAG, "UserList: loadState.refresh is LoadState.Loading")
                            //CircularProgressIndicator()
                            ShimmerAnimation()
                            Divider()
                        }
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Log.i(TAG, "UserList: loadState.append is LoadState.Loading")
                        CircularProgressIndicator()
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    item { Log.i(TAG, "UserList: loadState.refresh is LoadState.Error") }
                }
                loadState.append is LoadState.Error -> {
                    item { Log.i(TAG, "UserList: loadState.append is LoadState.Error") }
                }
            }
        }
    }
}
