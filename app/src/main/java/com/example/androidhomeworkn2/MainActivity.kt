package com.example.androidhomeworkn2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmailAddress: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText
    private lateinit var registerButton: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        registrationListener()
    }


    private fun init(){
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword)
        registerButton = findViewById(R.id.registerButton)
    }

    private fun registrationListener(){
        registerButton.setOnClickListener {
            val email = editTextEmailAddress.text.toString()
            val password = editTextPassword.text.toString()
            val confirmPassword = editTextConfirmPassword.text.toString()

            if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                Toast.makeText(this,"One of the inputs is Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(password.length <= 8){
                Toast.makeText(this,"Password should be more than 8 characters!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(password != confirmPassword){
                Toast.makeText(this,"Passwords DO NOT MATCH!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"User Registered Successfully!",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"Firebase Error! Try again!",Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}