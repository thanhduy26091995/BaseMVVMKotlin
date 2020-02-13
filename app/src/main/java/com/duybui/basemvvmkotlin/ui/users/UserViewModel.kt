package com.duybui.basemvvmkotlin.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duybui.basemvvmkotlin.data.model.User
import com.duybui.basemvvmkotlin.data.network.Result
import com.duybui.basemvvmkotlin.data.repo.DataRepository
import com.duybui.basemvvmkotlin.data.response.BaseResponseObject
import com.duybui.basemvvmkotlin.ui.base.BaseViewModel

class UserViewModel(val dataRepository: DataRepository) : BaseViewModel() {


    fun getRandomUser(number: Int): LiveData<Result<BaseResponseObject<List<User>>>> {
        return dataRepository.getUsers(number)
    }

    fun getRandomUserWithLocal(number: Int): LiveData<Result<List<User>>> {
        return dataRepository.getUsersWithLocal(number)
    }

    override fun onCleared() {
        super.onCleared()
    }
}
