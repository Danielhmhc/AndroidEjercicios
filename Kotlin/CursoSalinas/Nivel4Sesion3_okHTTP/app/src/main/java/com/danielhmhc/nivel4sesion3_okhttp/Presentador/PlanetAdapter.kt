package com.danielhmhc.nivel4sesion3_okhttp.Presentador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.danielhmhc.nivel4sesion3_okhttp.Modelo.Planeta
import com.danielhmhc.nivel4sesion3_okhttp.R
import kotlinx.android.synthetic.main.item_planet.tvPlanet

class PlanetAdapter (private val context: Context, private val datos: ArrayList<Planeta>): BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    //Infla una vista para un Item de la lista, aquí se describe cómo se va a desplegar la info
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        //inflamos la vista item_game para cada elemento de la lista
        val rowView = inflater.inflate(R.layout.item_planet, parent, false)

        //obtenemos las referencias de cada View de nuestro layout de item_game
        val tvPlanet = rowView.findViewById<View>(R.id.tvPlanet) as TextView
        val tvTerrain = rowView.findViewById<View>(R.id.tvTerrain) as TextView

        //obtenemos la información del item por medio de getItem()
        val planeta = getItem(position) as Planeta

        //seteamos todos los valores a nuestras vistas para desplegar la información
        //seteamos todos los valores a nuestras vistas para desplegar la información
        tvPlanet.text = planeta.planeta
        tvTerrain.text = planeta.terrain

        return rowView
    }


    //regresa un item para ser colocado en la posición position del Listview
    override fun getItem(position: Int): Any {
        return datos[position]
    }

    //Este método define un id para cada item, decidimos usar el index en el array de data
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //este método le dice al ListView cuantos items mostrar
    override fun getCount(): Int {
        return datos.size
    }
}