package com.danielhmhc.nivel3sesion8_navegfragm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottom_navigation_view.setOnNavigationItemSelectedListener  { menuItem ->
            when (menuItem.itemId) {
                R.id.Perfil -> {
                    val fragment = PerfilFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.Tienda -> {
                    val fragment = TiendaFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.Carrito -> {
                    val fragment = CarritoFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}
