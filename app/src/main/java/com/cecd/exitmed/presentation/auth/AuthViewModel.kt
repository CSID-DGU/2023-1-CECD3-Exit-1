package com.cecd.exitmed.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.data.dataSource.local.ExitLocalDataSource
import com.cecd.exitmed.data.model.request.RequestEmailDoubleCheck
import com.cecd.exitmed.data.model.request.RequestSignIn
import com.cecd.exitmed.data.model.request.RequestSignUp
import com.cecd.exitmed.domain.repository.AuthRepository
import com.cecd.exitmed.type.GenderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dataStore: ExitLocalDataSource,
    private val authRepository: AuthRepository
) : ViewModel() {
    val inputEmail = MutableStateFlow("")
    val inputPW = MutableStateFlow("")
    val inputPWReConfirm = MutableStateFlow("")
    val inputName = MutableStateFlow("")
    val inputBirth = MutableStateFlow("")
    private var _inputGender = MutableStateFlow<GenderType?>(null)
    val inputGender get() = _inputGender.asStateFlow()
    private var _isCompleteSignUp = MutableStateFlow<Boolean?>(null)
    val isCompleteSignUp get() = _isCompleteSignUp.asStateFlow()
    private val _isEmailDuplicated = MutableStateFlow<Boolean?>(null)
    val isEmailDuplicated get() = _isEmailDuplicated.asStateFlow()
    private var _isCompleteSignIn = MutableStateFlow<Boolean?>(null)
    val isCompleteSignIn get() = _isCompleteSignIn.asStateFlow()

    val isValidEmail: StateFlow<Boolean?> = inputEmail.map { email ->
        email.matches(Regex(EMAIL_PATTERN))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun initEmailDuplicated() {
        _isEmailDuplicated.value = null
    }

    val isValidPassword: StateFlow<Boolean?> = inputPW.map { password ->
        password.matches(Regex(PASSWORD_PATTERN))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val isDoubleCheck: StateFlow<Boolean?> = combine(
        inputPW,
        inputPWReConfirm
    ) { password, passwordDoubleCheck ->
        password == passwordDoubleCheck
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun setUserGender(genderType: GenderType) {
        _inputGender.value = genderType
    }

    val isInfoComplete: StateFlow<Boolean?> = combine(
        inputName,
        inputBirth,
        inputGender
    ) { name, birth, gender ->
        name.isNotEmpty() && birth.length == 4 && _inputGender.value != null
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun signUp(email: String, password: String, birth: Int, name: String, gender: String) {
        viewModelScope.launch {
            authRepository.signUp(
                RequestSignUp(
                    email,
                    password,
                    birth,
                    name,
                    gender,
                    false
                )
            )
                .onSuccess {
                    _isCompleteSignUp.value = true
                }
                .onFailure { throwable ->
                    _isCompleteSignUp.value = false
                    Timber.e(throwable.message)
                }
        }
    }

    fun checkEmailDuplicated() {
        viewModelScope.launch {
            authRepository.checkEmailDuplicated(RequestEmailDoubleCheck(inputEmail.value))
                .onSuccess { isDuplicated ->
                    _isEmailDuplicated.value = isDuplicated.duplicated
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }

    fun signIn() {
        viewModelScope.launch {
            authRepository.signIn(RequestSignIn(inputEmail.value, inputPW.value))
                .onSuccess {
                    dataStore.isLogin = true
                    dataStore.accessToken = it.accessToken
                    _isCompleteSignIn.value = true
                }
                .onFailure { throwable ->
                    _isCompleteSignIn.value = false
                    Timber.tag("aaaaa").e(throwable.message)
                }
        }
    }

    private
    companion object {
        const val EMAIL_PATTERN = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$"
        const val PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{10,}\$"
        const val NAME_PATERN = ""
    }
}
