package com.duybui.basemvvmkotlin

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.duybui.basemvvmkotlin.data.network.NetworkState
import com.duybui.basemvvmkotlin.ui.base.BaseActivity
import com.duybui.basemvvmkotlin.ui.base.ServerErrorDialogFragment
import com.duybui.basemvvmkotlin.ui.reddit.RedditPostsAdapter
import com.duybui.basemvvmkotlin.ui.reddit.RedditViewModel
import com.duybui.basemvvmkotlin.ui.users.UserAdapter
import com.duybui.basemvvmkotlin.ui.users.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {
    override val layoutRes: Int
        get() = R.layout.activity_main

    //    @Inject
//    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var userAdapter: UserAdapter

    var redditPostsAdapter = RedditPostsAdapter()

//    private val userViewModel by lazy {
//        ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
//    }


    //    private val userViewModel by viewModel<UserViewModel>()
    private val redditViewModel by viewModel<RedditViewModel>()
    private val userViewModel by viewModel<UserViewModel>()

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

//       userViewModel.getRandomUser(10).observe(this, Observer {
//           when (it.status){
//               NetworkState.SUCCESS -> {
//                   userAdapter.setData(it?.data?.data)
//                   userAdapter.notifyDataSetChanged()
//                   println(it.data?.data?.size)
//               }
//               NetworkState.FAIL -> {
//                   ServerErrorDialogFragment.newInstance(null, it.message).show(supportFragmentManager, "Error")
//               }
//           }
//       })

        userViewModel.getRandomUserWithLocal(10).observe(this, Observer {
            when (it.status) {
                NetworkState.SUCCESS -> {
                    userAdapter.setData(it?.data)
                    userAdapter.notifyDataSetChanged()
                }
                NetworkState.FAIL -> {
                    ServerErrorDialogFragment.newInstance(null, it.message)
                        .show(supportFragmentManager, "Error")
                }
            }
        })

//        redditViewModel.networkState?.observe(this, Observer {
//            println(it.toString())
//        })

        //listen error
//        userViewModel.error.observe(this, Observer { error ->
//            error?.let {
//                ServerErrorDialogFragment.newInstance(null, error).show(supportFragmentManager, "A")
//            }
//        })
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(this)
        rv_users.layoutManager = LinearLayoutManager(this)
        rv_users.adapter = userAdapter
    }
}
