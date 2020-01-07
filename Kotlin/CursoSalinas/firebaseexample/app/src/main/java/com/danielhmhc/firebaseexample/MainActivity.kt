package com.danielhmhc.firebaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ValueEventListener, ChildEventListener {
    override fun onCancelled(p0: DatabaseError) {

    }

    override fun onDataChange(p0: DataSnapshot) {
        Log.d("Mensaje", "Hijos = ${p0.childrenCount}")
        for (snapshot in p0.children){
            var producto = snapshot.getValue(Producto::class.java)

            if(producto != null){
                producto.id_firebase= snapshot.key.toString()
                Log.d("mensaje", "Agregado $producto")
            }

        }
    }

    override fun onChildMoved(p0: DataSnapshot, p1: String?) {
        var producto=p0.getValue(Producto::class.java)

        if(producto != null){
            producto.id_firebase = p0.key.toString()
            Toast.makeText(applicationContext,producto.id_firebase,Toast.LENGTH_LONG).show()
            Log.d("mensaje", "Movido $producto")
        }
    }

    override fun onChildChanged(p0: DataSnapshot, p1: String?) {
        var producto=p0.getValue(Producto::class.java)

        if(producto != null){
            producto.id_firebase = p0.key.toString()
            Toast.makeText(applicationContext,producto.id_firebase,Toast.LENGTH_LONG).show()
            Log.d("mensaje", "Modificado $producto")
        }

    }

    override fun onChildAdded(p0: DataSnapshot, p1: String?) { //DataSnapshot es el objeto recuperado de fire
        var producto=p0.getValue(Producto::class.java)

        if(producto != null){
            producto.id_firebase = p0.key.toString()
            Toast.makeText(applicationContext,producto.id_firebase,Toast.LENGTH_LONG).show()
            Log.d("mensaje", "Agregado $producto")
        }


    }

    override fun onChildRemoved(p0: DataSnapshot) {
        var producto=p0.getValue(Producto::class.java)
        if(producto != null){
            Log.d("mensaje", "Removido $producto")
        }
    }

    val db = BaseDatos.obtenerInstanciaProductos() //Llamamos a nuestro objeto de la conexion a firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Indicamos los metodos que se diparan en los listeners
        db.addValueEventListener(this)
        db.addChildEventListener(this)


        btnGuardar.setOnClickListener { //Al dar click al boton, guardamos los campos en un objeto y luego a firebase
            val p = Producto(txtNombre.text.toString(),txtPrecio.text.toString().toDouble())

            db.push().setValue(p)  //En esta referencia guardamos el producto en firebase
            txtNombre.setText("")  //Limpiamos los campos en nueswtra UI
            txtPrecio.setText("")
          //   actualizar()
            //eliminar()
        }


    }

    fun actualizar(){ // Para actualizar en firebase, solo se necesita obtener con un child la referencia padre
        db.child("-LxwuhiCWEEAFVLYEToj").child("precio").setValue("99.9")
    }

    fun eliminar(){
        db.child("-LxwuhiCWEEAFVLYEToj").child("precio").removeValue()
    }

    /* Notas y conocimientos sobre el proyecto

    Antes para trabajar con notificaciones push se podian hacer en tres grupos, ahorita solo se puede bajo un esquema.

    Canales en las notificaciones: Te categoriza las notificaciones, y el  usuario puede decidir que not recibe y cuales no
    


     */
}
