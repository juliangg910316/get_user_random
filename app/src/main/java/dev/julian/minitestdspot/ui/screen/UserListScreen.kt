package dev.julian.minitestdspot.ui.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import dev.julian.minitestdspot.data.User
import dev.julian.minitestdspot.viewmodels.UserViewModel
import dev.julian.minitestdspot.R

const val USER_LIST_TEST_TAG = "user_list_test_tag"
const val TAG = "USER_LIST_SCREEN"

@Composable
fun UserListScreen(viewModel: UserViewModel = hiltViewModel()) {
    val allUser : LazyPagingItems<User> = viewModel.searchUser("name,email,picture,id,location").collectAsLazyPagingItems()
    UserList(users = allUser)
}


@Composable
fun UserList(users : LazyPagingItems<User>) {
    LazyColumn(modifier = Modifier.testTag(USER_LIST_TEST_TAG)){
        items(users){ user ->
            if (user != null)
                UserRow(user)
            Divider()
        }

        users.apply {
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

@Composable
fun UserRow(user: User) {
    val context = LocalContext.current
    Row (
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .clickable {
                    ContextCompat.startActivity(context, intentDisplayMap(user), null)
                },
            verticalAlignment = Alignment.CenterVertically
                ) {

            user.picture.thumbnail.let {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user.picture.thumbnail)
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_baseline_person_outline_24),
                    error = painterResource(R.drawable.ic_baseline_person_outline_24),
                    contentDescription = "Image",
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .width(50.dp)
                        .clip(CircleShape)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(user.name.nameComplete(),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
                Text(user.email)
            }


    }
}

/*
 * Search for locations or places, and display them on a map.
 */
private fun intentDisplayMap(user: User) : Intent {
    val uriStringAddress = "geo:0,0?q="
    val encodeString = "${user.location.street.number} ${user.location.street.name}, ${user.location.city}, ${user.location.state}, ${user.location.country}"
    val uri = Uri.parse(uriStringAddress + Uri.encode(encodeString))
    return Intent(Intent.ACTION_VIEW, uri)
}
