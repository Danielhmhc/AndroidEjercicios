package com.danielhmhc.firebaseexample

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object BaseDatos {  //Hacemos que sea un objeto, para que cuando lo necesitemos solo lo mandemos a llamar , ESTA CLASE SE CONECTA A NUESTRO FIREBASE
    val RUTA_PRODUCTOS =  "PRODUCTOS"

    fun obtenerInstanciaProductos():DatabaseReference{
        val db = FirebaseDatabase.getInstance()  //Obtenemos la instancia
        val  reference = db.getReference(RUTA_PRODUCTOS) //Indicamos cual va a ser nuestra raiz
        return reference
    }


}