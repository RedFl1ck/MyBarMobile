package com.example.bottomtest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bottomtest.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var mViewModel: LoginRegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mViewModel = ViewModelProvider(this).get(LoginRegisterViewModel::class.java)
        setListeners()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sendValidation() {
        mViewModel.validationReg(
            binding.regName,
            binding.regSurname,
            binding.regPatronymic,
            binding.regBirthDate,
            binding.regPhoneNum,
            binding.regEMail,
            binding.regPassword,
            binding.regPasswordSubmit)
    }

    private fun setListeners(){
        binding.buttonRegIN.setOnClickListener {
            sendValidation()
        }
        binding.regPasswordSubmit.editText?.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                sendValidation()
                true
            } else {
                false
            }
        }
        binding.regName.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mViewModel.validateName(binding.regName)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mViewModel.validateName(binding.regName)
            }
        })
        binding.regSurname.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mViewModel.validateSurname(binding.regSurname)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mViewModel.validateSurname(binding.regSurname)
            }
        })
        binding.regPatronymic.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mViewModel.validatePatronymic(binding.regPatronymic)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mViewModel.validatePatronymic(binding.regPatronymic)
            }
        })
        binding.regBirthDate.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mViewModel.validateBirthDate(binding.regBirthDate)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mViewModel.validateBirthDate(binding.regBirthDate)
            }
        })
        binding.regPhoneNum.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mViewModel.validatePhoneNum(binding.regPhoneNum)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mViewModel.validatePhoneNum(binding.regPhoneNum)
            }
        })
        binding.regEMail.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mViewModel.validateEmail(binding.regEMail)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mViewModel.validateEmail(binding.regEMail)
            }
        })
        binding.regPassword.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mViewModel.validatePass(binding.regPassword)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mViewModel.validatePass(binding.regPassword)
            }
        })
        binding.regPasswordSubmit.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mViewModel.validatePassSubmit(binding.regPasswordSubmit, binding.regPassword)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mViewModel.validatePassSubmit(binding.regPasswordSubmit, binding.regPassword)
            }
        })
    }
}