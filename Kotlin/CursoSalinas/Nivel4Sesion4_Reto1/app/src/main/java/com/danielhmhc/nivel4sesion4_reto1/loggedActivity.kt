package com.danielhmhc.nivel4sesion4_reto1

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_logged.*
import kotlinx.android.synthetic.main.activity_main.*

class loggedActivity : AppCompatActivity() {
    lateinit var preferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)
        preferences = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        tvEmail.text = preferences.getString("EMAIL", "Fallo al entrar")
        tvNombre.text = preferences.getString("NOMBRE", "Fallo al entrar")
    }


}
