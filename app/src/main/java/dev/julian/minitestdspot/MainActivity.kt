package dev.julian.minitestdspot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.databinding.DataBindingUtil
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.julian.minitestdspot.adapters.UserAdapter
import dev.julian.minitestdspot.databinding.ActivityUserBinding
import dev.julian.minitestdspot.navigation.NavGraph
//import dev.julian.minitestdspot.databinding.ActivityUserBinding
import dev.julian.minitestdspot.ui.screen.UserListScreen
import dev.julian.minitestdspot.ui.theme.MiniTestDSpotTheme
import dev.julian.minitestdspot.viewmodels.UserViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val binding : ActivityUserBinding = DataBindingUtil.setContentView(this, R.layout.activity_user)
        /*val binding = ActivityUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModel by viewModels<UserViewModel>()

        val items = viewModel.searchUser("name,email,picture,id")
        val userAdapter = UserAdapter()

        binding.bindAdapter(userAdapter = userAdapter)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                items.collectLatest {
                    userAdapter.submitData(it)
                }
            }
        }*/

        /*lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                userAdapter.loadStateFlow.collect {
                    binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                    binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                }
            }
        }*/

        setContent {
            MiniTestDSpotTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}

/*private fun ActivityUserBinding.bindAdapter(userAdapter: UserAdapter){
    list.adapter = userAdapter
    list.layoutManager = LinearLayoutManager(list.context)
    val decoration = DividerItemDecoration(list.context, DividerItemDecoration.VERTICAL)
    list.addItemDecoration(decoration)
}*/

/*
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
    //Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MiniTestDSpotTheme {
        Greeting("Android")
    }
}*/
