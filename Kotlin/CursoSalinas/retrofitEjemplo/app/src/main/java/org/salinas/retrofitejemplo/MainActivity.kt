package org.salinas.retrofitejemplo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val URL_BASE = "https://jsonplaceholder.typicode.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofitComments = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var apiWeb = retrofitComments.create(ApiWeb::class.java)

        var callRespuesta = apiWeb.recuperaComentarios("comments")

        callRespuesta.enqueue(object:Callback<List<Comentario>>{
            override fun onFailure(call: Call<List<Comentario>>, t: Throwable) {
                Log.d("Mensaje",t.toString())
            }

            override fun onResponse(call: Call<List<Comentario>>,response: Response<List<Comentario>>) {
                if (response.isSuccessful){
                    for (comentario in response.body()!!){
                        Log.d("Mensaje", "${comentario.id}")
                        Log.d("Mensaje", comentario.name)
                        Log.d("Mensaje", comentario.body)
                        Log.d("Mensaje", comentario.email)
                    }
                }
            }

        })//asincrono
    }
}
