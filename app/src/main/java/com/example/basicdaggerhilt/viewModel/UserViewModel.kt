package com.example.basicdaggerhilt.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicdaggerhilt.model.User
import com.example.basicdaggerhilt.repository.api.Resource
import com.example.basicdaggerhilt.repository.UserRepository
import com.example.basicdaggerhilt.util.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository, private val networkStatus: NetworkStatus
) : ViewModel() {

    private val _user = MutableLiveData<Resource<User>>()

    val users: LiveData<Resource<User>>
        get() = _user

    init {

        fetchUser()
    }

    private fun fetchUser() {

        viewModelScope.launch {
            _user.postValue(Resource.loading())

            if (networkStatus.checkNetworkMethod()) {

                repository.getUser().let { response ->
                    if (response.isSuccessful)
                        _user.postValue(Resource.success(response.body()))
                    else
                        _user.postValue(Resource.error(response.errorBody().toString()))
                }
            } else {
                _user.postValue(Resource.error("Internet connection Error"))
            }
        }
    }
}