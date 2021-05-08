package com.example.bottomtest

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern


class LoginRegisterViewModel(application: Application) : AndroidViewModel(application) {

    /*public fun LoginRegisterViewModel(activity: Activity){
        this.activity = activity
    }

    public fun getActivity() : Activity {
        return activity
    }*/

    fun validationLog(textName : TextInputLayout,
                      textPass : TextInputLayout){
        validateName(textName)
        validatePass(textPass)
        if (validateName(textName) &&
            validatePass(textPass)){
            val name = textName.editText?.text.toString()
            val pass = textPass.editText?.text.toString()
            // TODO: send data
        }
    }

    fun validationReg(textName : TextInputLayout,
                   textSurname : TextInputLayout,
                   textPatronymic : TextInputLayout,
                   textBirthDate : TextInputLayout,
                   textPhoneNum : TextInputLayout,
                   textEmail : TextInputLayout,
                   textPass : TextInputLayout,
                   textPassSubmit : TextInputLayout,){
        validateName(textName)
        validateSurname(textSurname)
        validatePatronymic(textPatronymic)
        validateBirthDate(textBirthDate)
        validatePhoneNum(textPhoneNum)
        validateEmail(textEmail)
        validatePass(textPass)
        validatePassSubmit(textPassSubmit, textPass)
        if (validateName(textName) &&
            validateSurname(textSurname) &&
            validatePatronymic(textPatronymic) &&
            validateBirthDate(textBirthDate) &&
            validatePhoneNum(textPhoneNum) &&
            validateEmail(textEmail) &&
            validatePass(textPass) &&
            validatePassSubmit(textPassSubmit, textPass)){
            val name = textName.editText?.text.toString()
            val surname = textSurname.editText?.text.toString()
            val patronymic = textPatronymic.editText?.text.toString()
            val birthDate = textBirthDate.editText?.text.toString()
            val phoneNum = textPhoneNum.editText?.text.toString()
            val email = textEmail.editText?.text.toString()
            val pass = textPass.editText?.text.toString()
            val passSubmit = textPassSubmit.editText?.text.toString()
            // TODO: send data
        }
    }

    private fun getErrorEmpty() : String {
        return getApplication<Application>().resources.getString(R.string.error_empty)
    }

    fun validateName(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return if (temp.isEmpty()){
            field?.error = getErrorEmpty()
            false
        } else {
            field?.error = null
            true
        }
    }

    fun validateSurname(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return if (temp.isEmpty()){
            field?.error = getErrorEmpty()//resources.getString(R.string.error_empty)
            false
        } else {
            field?.error = null
            true
        }
    }

    fun validatePatronymic(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return if (temp.isEmpty()){
            field?.error = getErrorEmpty()//resources.getString(R.string.error_empty)
            false
        } else {
            field?.error = null
            true
        }
    }

    fun validateBirthDate(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return if (temp.isEmpty()){
            field?.error = getErrorEmpty()//resources.getString(R.string.error_empty)
            false
        } else if (!Pattern.matches("\\d\\d.\\d\\d.\\d\\d\\d\\d", temp)){
            field?.error = getApplication<Application>().resources.getString(R.string.error_wrong_birth_date)//resources.getString(R.string.error_wrong_birth_date)
            false
        } else {
            field?.error = null
            true
        }
    }

    fun validatePhoneNum(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return if (temp.isEmpty()){
            field?.error = getErrorEmpty()//resources.getString(R.string.error_empty)
            false
        } else if (!Patterns.PHONE.matcher(temp).matches()){
            field?.error = getApplication<Application>().resources.getString(R.string.error_wrong_phone)//resources.getString(R.string.error_wrong_phone)
            false
        } else {
            field?.error = null
            true
        }
    }

    fun validateEmail(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return if (temp.isEmpty()){
            field?.error = getErrorEmpty()//resources.getString(R.string.error_empty)
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(temp).matches()){
            field?.error = getApplication<Application>().resources.getString(R.string.error_wrong_e_mail)//resources.getString(R.string.error_wrong_e_mail)
            false
        } else {
            field?.error = null
            true
        }
    }

    fun validatePass(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return when {
            temp.isEmpty() -> {
                field?.error = getErrorEmpty()//resources.getString(R.string.error_empty)
                false
            }
            temp.count() < 4 -> {
                field?.error = getApplication<Application>().resources.getString(R.string.error_short_password)//resources.getString(R.string.error_short_password)
                false
            }
            else -> {
                field?.error = null
                true
            }
        }
    }

    fun validatePassSubmit(fieldPassSubmit: TextInputLayout, fieldPass: TextInputLayout): Boolean {
        val temp = fieldPassSubmit.editText?.text.toString()
        return when {
            temp.isEmpty() -> {
                fieldPassSubmit.error = getErrorEmpty()//resources.getString(R.string.error_empty)
                false
            }
            temp != fieldPass.editText?.text.toString() -> {
                fieldPassSubmit.error = getApplication<Application>().resources.getString(R.string.error_match_password)//resources.getString(R.string.error_match_password)
                false
            }
            else -> {
                fieldPassSubmit.error = null
                true
            }
        }
    }

}