package com.miranda.nivel3sesion7_menuopcional

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_opciones, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.Perfil -> {
                Toast.makeText(applicationContext,"Se ha seleccionado Perfil",Toast.LENGTH_SHORT).show()
                true
            }
            R.id.Historial -> {
                Toast.makeText(applicationContext,"Historial",Toast.LENGTH_SHORT).show()
                true
            }
            R.id.Empresas -> {
                Toast.makeText(applicationContext,"Empresas",Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
