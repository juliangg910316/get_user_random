package dev.julian.minitestdspot

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.paging.PagingData
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.julian.minitestdspot.data.*
import dev.julian.minitestdspot.ui.screen.USER_LIST_TEST_TAG
import dev.julian.minitestdspot.ui.screen.UserListScreen
import dev.julian.minitestdspot.ui.theme.MiniTestDSpotTheme
import dev.julian.minitestdspot.viewmodels.UserViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI test, used Mockk Library. (https://mockk.io/ANDROID.html)
 *
 * Reference - (https://proandroiddev.com/learn-with-code-jetpack-compose-lists-and-pagination-part-1-545447c55cb2).
 */
@RunWith(AndroidJUnit4::class)
class UserListPageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val userViewModel = mockk<UserViewModel>()

    @Before
    fun init(){
        MockKAnnotations.init(this, true)
        //provide mock data
        every { userViewModel.searchUser("name,email,picture,id,location") } answers {
            UserData.getFakePagingData()
        }
    }

    @Test
    fun testUserListScreenIfUsersAreNotEmpty() {

        composeTestRule.setContent {
            MiniTestDSpotTheme {
                UserListScreen(viewModel = userViewModel)
            }
        }

        composeTestRule.onNodeWithTag(USER_LIST_TEST_TAG)
            .onChildren()
            .assertCountEquals(1)

        composeTestRule.onNodeWithText("Madame Lilly Fernandez").assertIsDisplayed()

        composeTestRule.onNodeWithText("lilly.fernandez@example.com").assertIsDisplayed()

        composeTestRule
            .onAllNodesWithContentDescription("Image")
            .assertCountEquals(1)
    }
}

object UserData {

    fun getFakePagingData():
            Flow<PagingData<User>> {
        return flow {
            emit(PagingData.from(getUserEntity()))
        }
    }

    private fun getUserEntity(): List<User> {
        val allUser: ArrayList<User> = ArrayList()
        allUser.add(
            User(
                userId = 0,
                id = Id(
                    name="AVS",
                    value="756.1899.0072.04"
                ),
                name = Name(
                    title="Madame",
                    first="Lilly",
                    last="Fernandez"
                ),
                location = Location(
                    street = Street(
                        number=4688,
                        name="Rue Louis-Blanqui"
                    ),
                    city="La Tour-de-Peilz",
                    state="Zürich",
                    country="Switzerland",
                    postcode="3162",
                    coordinates = Coordinates(
                        latitude="-48.5682",
                        longitude="99.3579"
                    ),
                    timezone = TimeZone(
                        offset="+3:30",
                        description="Tehran"
                    )
                ),
                email="lilly.fernandez@example.com",
                picture = Picture(
                    large="https://randomuser.me/api/portraits/women/81.jpg",
                    medium="https://randomuser.me/api/portraits/med/women/81.jpg",
                    thumbnail="https://randomuser.me/api/portraits/thumb/women/81.jpg"
                )
            )
        )
        return allUser
    }
}