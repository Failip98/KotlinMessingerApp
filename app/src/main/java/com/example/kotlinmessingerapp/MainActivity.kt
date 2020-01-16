package com.example.kotlinmessingerapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_regrister.setOnClickListener {
            preformRegrister()
        }

        textView_regrister.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)

        }



        println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
        // Create a new user with a first and last name
        val user: MutableMap<String, Any> = HashMap()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = 1815

        val db = FirebaseFirestore.getInstance()
        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    TAG,
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }

//        db.collection("cities").document("LA")
//            .set(city)
//            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
//            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
        println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB")
    }

    private fun preformRegrister() {
        val username = text_regrister_username.text.toString()
        val email = text_regrister_email.text.toString()
        val password = text_regrister_password.text.toString()

        Log.d(TAG, "Username is: $username")
        Log.d(TAG, "Email is: $email")
        Log.d(TAG, "Password is: $password")

        if(email.isEmpty() || password.isEmpty() || username.isEmpty()){
            Toast.makeText(this,"Enter username email, or password",Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(!it.isSuccessful)return@addOnCompleteListener
                Toast.makeText(this,"User created",Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Successfully created user with uid: ${it.result?.user?.uid}")
                saveUserToDb()
            }
            .addOnFailureListener {
                Toast.makeText(this,"${it.message}",Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Error: ${it.message}")
            }
    }

    private fun saveUserToDb() {
        Log.d(TAG, "Saving")
        val db = FirebaseFirestore.getInstance()
        val city = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA"
        )

        println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
        // Create a new user with a first and last name
        val user: MutableMap<String, Any> = HashMap()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = 1815

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    TAG,
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }

//        db.collection("cities").document("LA")
//            .set(city)
//            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
//            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
        println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB")


        /*
        val uid = FirebaseAuth.getInstance().uid ?:""
        val user = User(uid,text_regrister_username.text.toString())
//User(
//                    uid!!,
//                    text_regrister_username.text.toString()
//                )

        db
            .collection("/users")
            .document("/$uid")
            .set(user)
             .addOnSuccessListener {
                Log.d(TAG, "success User saved to db")
                Toast.makeText(this,"success User saved to db",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.d(TAG, "abc error ${it.message}")
                Toast.makeText(this,"${it.message}",Toast.LENGTH_SHORT).show()
            }

            */
        Log.d(TAG, "Allt faller sönder")
        //Log.d(TAG, user.toString())
    }
}

data class User(var uid: String ="", var username: String="")

