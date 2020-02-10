package com.duybui.basemvvmkotlin.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duybui.basemvvmkotlin.data.model.User
import com.duybui.basemvvmkotlin.data.repo.DataRepository

class UserViewModel(val dataRepository: DataRepository) : ViewModel() {
    private var _userList = MutableLiveData<List<User>>()

    val users: MutableLiveData<List<User>>
        get() = _userList


    fun getRandomUser(number: Int) {
        _userList = dataRepository.getRandomUser(number)
    }

//    fun getRandomUserUsingAsync() {
//        CoroutineScope(Dispatchers.IO).launch {
//            val userResponse = async(Dispatchers.IO) { apiInterface.getRandomUser(10) }
//            withContext(Dispatchers.Main) {
//                _userList.value = userResponse.await().data
//            }
//        }
//    }

    override fun onCleared() {
        super.onCleared()
    }
}
