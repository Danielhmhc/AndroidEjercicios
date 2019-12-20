package com.danielhmhc.nivel4sesion3_okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.danielhmhc.nivel4sesion3_okhttp.Modelo.Planeta
import com.danielhmhc.nivel4sesion3_okhttp.Presentador.PlanetAdapter
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val baseUrl = "https://swapi.co/api/planets"
    private lateinit var adapter: PlanetAdapter
    var planetList = ArrayList<Planeta>()
    var planetasres = ArrayList<Planeta>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnllamadaasincrona.setOnClickListener{
            llamadaAsincrona()
        }
    }

    fun populateAdapter(data: ArrayList<Planeta>){
        planetList.clear()
        planetList.addAll(data)
        adapter.notifyDataSetChanged()
    }

    fun llamadaAsincrona(){
        val okHttpClient = OkHttpClient()

        //obteniendo la url completa
        val url = "https://swapi.co/api/planets"

        //El objeto Request contiene todos los parámetros de la petición (headers, url, body etc)
        val request = Request.Builder()
            .url(url)
            .build()

        //enviando y recibiendo las llamadas de forma asíncrona
        okHttpClient.newCall(request).enqueue(object : Callback {

            //el callback a ejecutar cuando hubo un error
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Error",e.toString())
            }

            //el callback a ejectutar cuando obtuvimos una respuesta
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                Log.d("Response: ", body)


                    val json = JSONObject(body) //se obtiene el objeto raíz
                    val array = json.getJSONArray("results") //se obtiene el arreglo de planetas
                    val numPlanets = array.length() -1 //el número máximo para ejecutar el ciclo for
                    planetasres.clear()
                    for (i in 0.. numPlanets){ //ciclo for para todos los planetas
                        val planeta = array.getJSONObject(i)  //así obtenemos el planeta del arreglo

                        //TODO: recuperar la información que requerimos del planeta y guardarla en el arreglo que va en el adapter
                        val planetaobtenido = Planeta(planeta.getString("name"),planeta.getString("terrain"))
                        planetasres.add(planetaobtenido)
                    }


            }


        })

        adapter = PlanetAdapter(this@MainActivity, planetasres )
        lvPlanetas.adapter = adapter
    }

}
