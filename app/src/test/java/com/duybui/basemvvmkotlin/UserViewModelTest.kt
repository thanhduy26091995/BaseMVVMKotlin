package com.duybui.basemvvmkotlin

import com.duybui.basemvvmkotlin.ui.users.UserViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before

@ExperimentalCoroutinesApi
class UserViewModelTest {
    private lateinit var userViewModel: UserViewModel

    @Before
    fun init(){
        userViewModel = UserViewModel(
    }
}