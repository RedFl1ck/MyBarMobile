package com.example.bottomtest.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.bottomtest.R
import com.google.android.material.textfield.TextInputLayout

class PersonalAccountFragment : Fragment() {

    private var user = true

    private var loginButton : Button? = null
    private var exitButton : Button? = null
    private var textBirthDate : TextInputLayout? = null
    private var textPhoneNum : TextInputLayout? = null
    private var textEmail : TextInputLayout? = null
    private var textName : TextView? = null


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_personal_account, container, false)

        loginButton = root.findViewById(R.id.buttonLogUser)
        exitButton = root.findViewById(R.id.personal_exit_button)
        textBirthDate = root.findViewById(R.id.personal_birth_date)
        textPhoneNum = root.findViewById(R.id.personal_phone_number)
        textEmail = root.findViewById(R.id.personal_email)
        textName = root.findViewById(R.id.personal_name)

        textName?.text = "Name Surname Patronymic"
        textBirthDate?.editText?.setText("01.01.2001")
        textPhoneNum?.editText?.setText("+7(999)999-99-99")
        textEmail?.editText?.setText("student@mail.ru")

        if (!user){
            startActivity(Intent(activity, LoginActivity::class.java))
        }

        loginButton?.setOnClickListener {
            //TODO: exit current user
            user = false
            startActivity(Intent(activity, LoginActivity::class.java))
        }

        exitButton?.setOnClickListener {
            exitUser()
        }

        return root
    }

    private fun changeVisibility(state: Boolean) {
        loginButton?.isVisible = state
        exitButton?.isVisible = state
        textBirthDate?.isVisible = state
        textPhoneNum?.isVisible = state
        textEmail?.isVisible = state
        textName?.isVisible = state
        loginButton?.isVisible = !state
    }


    private fun exitUser() {
        val builder = android.app.AlertDialog.Builder(activity)
        builder.setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
            user = false
            startActivity(Intent(activity, LoginActivity::class.java))
        }
        builder.setNegativeButton(resources.getString(R.string.no)) { _, _ -> }
        builder.setTitle(resources.getString(R.string.exit))
        builder.setMessage(resources.getString(R.string.exit_sure))
        builder.create().show()
    }


    override fun onStart() {
        super.onStart()
        changeVisibility(!user)
    }

}