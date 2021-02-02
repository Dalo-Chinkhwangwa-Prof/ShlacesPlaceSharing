package com.americanairlines.shlaces_shareplaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.americanairlines.shlaces_shareplaces.model.NewUser
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), SignUpFragment.SignUpDelegate {

    private val signUpFragment: SignUpFragment = SignUpFragment()

    private lateinit var userNameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var signUpTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userNameEditText = findViewById(R.id.username_edittext)
        passwordEditText = findViewById(R.id.password_edittext)
        loginButton = findViewById(R.id.login_button)
        signUpTextView = findViewById(R.id.sign_up_textview)

        FirebaseAuth.getInstance().currentUser?.let {
            if(it.isEmailVerified)
                openProfileActivity()
            else
                showMessage("Please verify via email address.")
        }


        loginButton.setOnClickListener {
            if(checkInput()){
                val emailAddress = userNameEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                FirebaseAuth
                    .getInstance()
                    .signInWithEmailAndPassword(emailAddress, password)
                    .addOnCompleteListener {

                        if(it.isComplete && it.isSuccessful && FirebaseAuth.getInstance().currentUser?.isEmailVerified == true){
                            //user has successfully logged in...
                            openProfileActivity()
                        } else {
                            showMessage(it.exception?.localizedMessage ?: "Error logging in.")
                        }
                    }
            }
        }

        signUpTextView.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )
                .add(R.id.sign_up_frame, signUpFragment)
                .addToBackStack(signUpFragment.tag)
                .commit()
        }

    }

    private fun openProfileActivity() {
        startActivity(Intent(this, ProfileActivity::class.java).also {

            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }

    private fun checkInput(): Boolean {

        when{
            userNameEditText.text.toString().trim().isEmpty() -> {
                showMessage("Username cannot be empty!")
            }
            passwordEditText.text.toString().trim().isEmpty() -> {
                showMessage("Password cannot be empty!")
            }
        }

        return true

    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun signUpNewUser(newUser: NewUser) {

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(newUser.userEmail, newUser.userPassword)
            .addOnCompleteListener {
                if(it.isComplete && it.isSuccessful){

                    if(FirebaseAuth.getInstance().currentUser?.isEmailVerified ==  true){
                        openProfileActivity()
                    } else
                        FirebaseAuth.getInstance().currentUser?.sendEmailVerification()

                } else {
                    showMessage(it.exception?.localizedMessage ?: "Sign up failed. Try again :(")
                }
            }


    }
}









