package com.danielhmhc.ejemploretrofit.okhttp

import okhttp3.Interceptor
import okhttp3.Response

class IntercepterPokemon : Interceptor{

    //la nueva url que va a sustituir a la anterior
    private val NEW_URL = "https://pokeapi.co/api/v2/"

    //override de la clase Interceptor
    override fun intercept(chain: Interceptor.Chain): Response {

        //creamos un new builder
        val requestBuilder = chain.request().newBuilder()

        //nuevo header agregado por el interceptor
        requestBuilder.addHeader("X-Been","Intercepted");

        //cambiamos la url
        requestBuilder.url(NEW_URL)

        //regresamos el builder modificado
        return chain.proceed(requestBuilder.build())
        //response.newBuilder.body(<a_new_body_response>);
    }
}