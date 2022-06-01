package dev.julian.minitestdspot.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.Pager
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import dev.julian.minitestdspot.R
import dev.julian.minitestdspot.data.User
import dev.julian.minitestdspot.navigation.Screen
import dev.julian.minitestdspot.viewmodels.UserViewModel

@Composable
fun UserListScreen(navController: NavHostController, viewModel: UserViewModel = hiltViewModel()) {
    val allUser : LazyPagingItems<User> = viewModel.searchUser("name,email,picture,id").collectAsLazyPagingItems()

    LazyColumn(){
        items(
            items = allUser,
            /*key = { user ->
                user.pk
            }*/
        ){ user ->
            if (user != null)
                UserRow(user, navController)
            Divider()
        }
    }


}

@Composable
fun UserRow(user: User, navController: NavHostController) {
    Row (
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .clickable {
                    navController.navigate(route = Screen.UserDetails.passUserId(user.id.value!!))
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
                    //placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = null,
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
