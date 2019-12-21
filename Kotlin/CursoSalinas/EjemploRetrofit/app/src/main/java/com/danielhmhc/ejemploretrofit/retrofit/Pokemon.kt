package com.danielhmhc.ejemploretrofit.retrofit

import com.google.gson.annotations.SerializedName

data class Pokemon (
    val name : String? = "",
    val weight: Int? = 0,
    val sprites : sprites? = null

)

//la clase definida para sprites, sólo nos interesa la url, por lo cual ignoramos su(s) otro(s) parámetro(s).
data class sprites(
    @SerializedName("front_default")
    val photoUrl : String? = ""
)