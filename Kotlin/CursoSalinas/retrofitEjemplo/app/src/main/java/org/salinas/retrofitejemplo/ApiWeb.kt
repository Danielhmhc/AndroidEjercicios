package org.salinas.retrofitejemplo

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiWeb {

    @GET("{parametro}")
    fun recuperaComentarios(@Path("parametro")parametro:String): retrofit2.Call<List<Comentario>>

}