package com.danielhmhc.nivel4sesion4_reto1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var preferences: SharedPreferences

    companion object Preferences{
        val PREFS_NAME = "org.bedu.login"
        val EMAIL = "danielhmhc@gmail.com"
        val IS_LOGGED = false
        val NOMBRE = "SIN NOMBRE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences=getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        btnLogin.setOnClickListener { preferences.edit().putBoolean("IS_LOGGED",true)
            preferences.edit().putString("NOMBRE",etNombre.text.toString()) }
    }

    override fun onStart() {
        super.onStart()

        if(isLogged()){
            goToLogged()
        }
    }

    fun isLogged(): Boolean {
        return preferences.getBoolean("IS_LOGGED",false)
    }

    fun goToLogged(){
        val i = Intent(this, loggedActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }
}
