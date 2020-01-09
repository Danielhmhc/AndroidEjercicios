package com.danielhmhc.firebaseexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity(),FirebaseAuth.AuthStateListener {
    private val POLITICA: String = "http://google.com"
    private val PROVEEDOR_DESCONOCIDO: String = "desconocido"  //En este ejemplo, estas son nuestras credenciales
    private val PASSWORD_FIREBASE = "password"
    private val PASSWORD_FACEBOOK = "facebook.com"
    private val REQUES_CODE: Int = 200
    lateinit var mFirebaseAuth: FirebaseAuth
    lateinit var mFirebaseAuthListener: FirebaseAuth.AuthStateListener


    override fun onAuthStateChanged(f0: FirebaseAuth) {
         //Al entrar a la ventanma de la aplicacion, va a ver si existe alguna sesion. Si no existe, manda una actividad donde ingresa
        var user: FirebaseUser? = f0.currentUser  //Accedemos al usuario  que esta actualmente dentro
        var proveedores = mutableListOf(
            AuthUI.IdpConfig.EmailBuilder().build()  //Aqui importamos la del email
           // AuthUI.IdpConfig.FacebookBuilder().build()
        )

        if(user!=null){
            var proveedor =
                if(user.providerData!= null)
                    user.providerData.get(1).providerId
                else
                    PROVEEDOR_DESCONOCIDO

            mostrarDatos(user.displayName,user.email,proveedor,user.photoUrl.toString())



        }
        else{
            startActivityForResult(AuthUI.getInstance()  //No hay sesion iniciada
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false)  //Para que recuerde nuestra
                .setAvailableProviders(proveedores)//Pasamos a los proveedores, en este momento es solo el correo
                .setTosAndPrivacyPolicyUrls(POLITICA,POLITICA)
                .build(),REQUES_CODE)
            /*
            limpiarDatos()

             */
        }

    }

    private fun mostrarDatos(displayName: String?, email: String?, proveedor: String,foto:String = "") {
        txtUsuario.text = displayName.toString()
        txtEmail.text = email.toString()
        txtProveedor.text = proveedor
        when(proveedor){
            PASSWORD_FIREBASE->{
                imgProveedor.setImageResource(R.drawable.ic_firebase)
                imgFotoPerfil.setImageResource(R.mipmap.ic_launcher_round)
            }
            /*PASSWORD_FACEBOOK->{
                imgProveedor.setImageResource(R.drawable.ic_facebook_box)
                Glide.with(this).load(foto).into(imgFotoPerfil)
            }

             */
            else->{
                imgFotoPerfil.setImageResource(R.mipmap.ic_launcher_round)
                imgProveedor.setImageResource(R.mipmap.ic_launcher_round)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        mFirebaseAuth= FirebaseAuth.getInstance()
        mFirebaseAuthListener= this
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            REQUES_CODE->{
                if(resultCode == Activity.RESULT_OK){
                    Toast.makeText(applicationContext, "Bienvenido",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(applicationContext,"Algo ha salido mal",Toast.LENGTH_SHORT)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mFirebaseAuth.addAuthStateListener { mFirebaseAuthListener }
    }

    override fun onPause() {
        super.onPause()
        if (mFirebaseAuthListener != null){
            mFirebaseAuth.removeAuthStateListener { mFirebaseAuthListener }
        }
    }

    fun cerrarSesionFirebase(view: View) {
        AuthUI.getInstance().signOut(applicationContext)
    }

}
