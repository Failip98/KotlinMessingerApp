package com.example.kotlinmessingerapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btn_login.setOnClickListener {
            preformLogin()
        }

        text_login_back.setOnClickListener {
            finish()
        }
    }

    private fun preformLogin() {
        val email = text_login_email.text.toString()
        val password = text_login_password.text.toString()

        Log.d("___", "Username is: $email")
        Log.d("___", "Password is: $password")

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Enter email or password", Toast.LENGTH_SHORT).show()
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(!it.isSuccessful)return@addOnCompleteListener
                Toast.makeText(this,"User created",Toast.LENGTH_SHORT).show()
                //Log.d("aaa", "Successfully created user with uid: ${it.result?.user?.uid}")
            }
            .addOnFailureListener {
                Toast.makeText(this,"${it.message}",Toast.LENGTH_SHORT).show()
            }
    }

}