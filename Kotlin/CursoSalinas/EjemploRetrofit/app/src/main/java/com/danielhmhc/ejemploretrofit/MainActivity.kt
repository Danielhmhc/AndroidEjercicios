package com.danielhmhc.ejemploretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.danielhmhc.ejemploretrofit.okhttp.IntercepterPokemon
import com.danielhmhc.ejemploretrofit.retrofit.Pokemon
import com.danielhmhc.ejemploretrofit.retrofit.WebServices
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val TIMEOUT_CALL_SECONDS = 30L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSearch.setOnClickListener{
            println("Esta es una prueba de que va bien todo")

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            //Construccion de la intancia de interceptor
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .connectTimeout(TIMEOUT_CALL_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_CALL_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_CALL_SECONDS, TimeUnit.SECONDS)
                .build()

            //Construcción de la instancia de retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            val endpoint = retrofit.create(WebServices::class.java)
            ///obtener el valor insertado en el editText (nombre del pokemon), y enviarla al endpoint
            val pokeSearch = etPokemon.text.toString()
            val call = endpoint.getPokemon(pokeSearch)
            //poniendo en cola la llamada FGET
            call?.enqueue(object : Callback<Pokemon> {
                //imprimimos algo si no nos llegó respuesta
                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Log.d("hola","no hubo nada para mi: ${t}")
                }
                //mostramos los archivos solo si el resultado es 200
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if(response.code()==200){
                        val body = response.body()
                        Log.e("Respuesta","${response.body().toString()}")
                        tvPokemon.text = body?.name
                        tvWeight.text = "peso: " + body?.weight.toString()
                        Picasso.get().load(body?.sprites?.photoUrl).into(pokemon); //esto es lobrd
                    } else{
                        Toast.makeText(applicationContext,"Pokemon no encontrado", Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }
    }
}
