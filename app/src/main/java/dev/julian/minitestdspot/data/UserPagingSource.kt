package dev.julian.minitestdspot.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.julian.minitestdspot.api.UserService
import kotlin.math.max

class UserPagingSource (private val service: UserService, private val query: String) : PagingSource<Int, User>() {

    private val ITEMS_PER_PAGE = 50
    private val STARTING_KEY = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        // Start paging with the STARTING_KEY if this is the first load
        val start = params.key ?: STARTING_KEY
        // Load as many items as hinted by params.loadSize
        val range = start.until(start + params.loadSize)

        return try {
            val response = service.loadUser(start, ITEMS_PER_PAGE, "123qweasd",query)
            Log.i("UserPagingSource", "load: result = ${response.results}")
            val users = response.results
            Log.i("UserPagingSource", "nextKey: range = ${response.info.page}")
            LoadResult.Page(
                data = users,
                prevKey = when (start) {
                    STARTING_KEY -> null
                    else -> ensureValidKey(key = range.first - params.loadSize)
                },
                nextKey = response.info.page + 1
            )
        } catch (exception: Exception) {
            Log.e("UserPagingSource", "load: exception = ${exception.message}" , exception )
            LoadResult.Error(exception)
        }
    }

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        // In our case we grab the item closest to the anchor position
        // then return its id - ITEMS_PER_PAGE as a buffer
        val anchorPosition = state.anchorPosition ?: return null
        val user = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = user.userId - ITEMS_PER_PAGE)
    }

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}