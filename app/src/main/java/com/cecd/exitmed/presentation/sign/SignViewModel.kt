package com.cecd.exitmed.presentation.sign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.type.GenderType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SignViewModel : ViewModel() {
    val inputEmail = MutableStateFlow("")
    val inputPW = MutableStateFlow("")
    val inputPWDoubleCheck = MutableStateFlow("")
    val inputName = MutableStateFlow("")
    val inputBirth = MutableStateFlow("")
    private var _inputGender = MutableStateFlow<GenderType?>(null)
    val inputGender get() = _inputGender.asStateFlow()

    val isValidEmail: StateFlow<Boolean?> = inputEmail.map { email ->
        email.matches(Regex(EMAIL_PATTERN))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val isValidPassword: StateFlow<Boolean?> = inputPW.map { password ->
        password.matches(Regex(PASSWORD_PATTERN))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val isDoubleCheck: StateFlow<Boolean?> = inputPWDoubleCheck.map { doubleCheck ->
        doubleCheck == inputPW.value
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


    companion object {
        const val EMAIL_PATTERN = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$"
        const val PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{10,}\$"
        const val NAME_PATERN = ""
    }
}
