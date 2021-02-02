package com.americanairlines.shlaces_shareplaces

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.americanairlines.shlaces_shareplaces.model.NewUser

class SignUpFragment : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var verifyUserNameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var verifyPasswordEditText: EditText
    private lateinit var signUpButton: Button

    private lateinit var signUpDelegate: SignUpDelegate

    interface SignUpDelegate {
        fun signUpNewUser(newUser: NewUser)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signUpDelegate = (context as LoginActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.signup_fragment_layout, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usernameEditText = view.findViewById(R.id.su_username_edittext)
        verifyUserNameEditText = view.findViewById(R.id.su_verify_username_edittext)
        passwordEditText = view.findViewById(R.id.su_password_edittext)
        verifyPasswordEditText = view.findViewById(R.id.su_verify_password_edittext)

        signUpButton = view.findViewById(R.id.register_button)

        signUpButton.setOnClickListener {
            if (checkInput()) {
                val newUser = NewUser(usernameEditText.text.toString().trim(), passwordEditText.text.toString().trim())
                signUpDelegate.signUpNewUser(newUser)
                clearTextFields()
                fragmentManager?.popBackStack()
            }
        }
    }

    private fun clearTextFields() {
        usernameEditText.text.clear()
        verifyUserNameEditText.text.clear()
        passwordEditText.text.clear()
        verifyPasswordEditText.text.clear()
    }

    private fun checkInput(): Boolean {

        when {
            usernameEditText.text.toString().trim().isEmpty() -> {
                showToast("Username cannot be empty")
                return false
            }
            passwordEditText.text.toString().trim().isEmpty() -> {
                showToast("Password cannot be empty")
                return false
            }
            usernameEditText.text.toString().trim() != verifyUserNameEditText.text.toString().trim() -> {
                showToast("Username's do not match")
                return false
            }
            passwordEditText.text.toString().trim() != verifyPasswordEditText.text.toString().trim() -> {
                showToast("Password's do not match")
                return false
            }
        }
        return true
    }

    private fun showToast(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }
}








