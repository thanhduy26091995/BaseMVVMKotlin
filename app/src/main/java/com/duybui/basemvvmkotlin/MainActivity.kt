package com.duybui.basemvvmkotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.duybui.basemvvmkotlin.data.model.Theme
import com.duybui.basemvvmkotlin.data.model.themeFromStorageKey
import com.duybui.basemvvmkotlin.data.network.NetworkState
import com.duybui.basemvvmkotlin.ui.MainViewModel
import com.duybui.basemvvmkotlin.ui.base.BaseActivity
import com.duybui.basemvvmkotlin.ui.reddit.RedditPostsAdapter
import com.duybui.basemvvmkotlin.ui.reddit.RedditViewModel
import com.duybui.basemvvmkotlin.ui.users.UserAdapter
import com.duybui.basemvvmkotlin.ui.users.UserViewModel
import com.duybui.basemvvmkotlin.utils.updateForTheme
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {
    override val layoutRes: Int
        get() = R.layout.activity_main

    //    @Inject
//    lateinit var viewModelFactory: ViewModelFactory
    lateinit var userAdapter: UserAdapter

    var redditPostsAdapter = RedditPostsAdapter()

//    private val userViewModel by lazy {
//        ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
//    }


    //    private val userViewModel by viewModel<UserViewModel>()
    private val redditViewModel by viewModel<RedditViewModel>()
    private val userViewModel by viewModel<UserViewModel>()
    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        presentationComponent.inject(this)

        setupRecyclerView()
//        userViewModel.getRandomUser(10)
        // userViewModel.getRandomUserUsingAsync()

//        userViewModel.users.observe(this, Observer { value ->
//            userAdapter.setData(value)
//            userAdapter.notifyDataSetChanged()
//        })

        redditViewModel.posts.observe(this, Observer {
            redditPostsAdapter.submitList(it)
        })

//        userViewModel.getRandomUser(10).observe(this, Observer {
//            when (it.status) {
//                NetworkState.SUCCESS -> {
//                    userAdapter.setData(it?.data?.data)
//                    userAdapter.notifyDataSetChanged()
//                }
//                NetworkState.FAIL -> {
//                    handleServerError(null, it.message, it.code)
//                }
//            }
//        })
//
//        userViewModel.getRandomUserWithLocal(10).observe(this, Observer {
//            when (it.status) {
//                NetworkState.SUCCESS -> {
//                    userAdapter.setData(it?.data)
//                    userAdapter.notifyDataSetChanged()
//                }
//                NetworkState.FAIL -> {
//                    handleServerError(null, it.message, it.code)
//                }
//            }
//        })
        mainViewModel.currentTheme.observe(this, Observer {
            updateForTheme(themeFromStorageKey(it))
        })
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(this)
        rv_users.layoutManager = LinearLayoutManager(this)
        rv_users.adapter = redditPostsAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.light_mode -> {
                mainViewModel.setTheme(Theme.LIGHT)
            }

            R.id.dark_mode -> {
                mainViewModel.setTheme(Theme.DARK)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
