package com.danielhmhc.prototipotienda

import Modelo.Producto
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import presentador.ArticuloAdaptador

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TiendaFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TiendaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TiendaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var users = mutableListOf<Producto>()
        users.add(Producto("Computadora", "Una compu con buen procesador, RAM y Disco",R.drawable.compu, 13548.20F))
        users.add(Producto("Celular", "Un cel super bonito y muy buen camara para selfies ",R.drawable.celular, 6048.20F))
        users.add(Producto("Mesita Billar", "Una mesita de billar para que jueges con tus sobrinitos ",R.drawable.mesa_billar, 248.20F))

        users.add(Producto("Computadora2", "Una compu con buen procesador pero poca RAM y Disco",R.drawable.compu2, 7634.20F))
        users.add(Producto("Celular2", "Un cel super bonito ... y ya ",R.drawable.celular2, 300.20F))
        users.add(Producto("Mesita Billar2", "Una mesa de billar para que juegues pero con tus compas ",R.drawable.mesa_billar2, 1208.20F))

        //Obtenemos la instancia de nuestro RecyclerView
        val userList = view!!.findViewById<RecyclerView>(R.id.recArticulos)
        userList.setHasFixedSize(true)
        userList.layoutManager= LinearLayoutManager(context)
        //Creamos una instancia de el Adaptador pasandole como parametro el contexto de la actividad y la coleccion de datos.
        val adapter = this.context?.let { ArticuloAdaptador(it, users) }
        //Asignamos el adaptador a la propiedad adapter de el RecyclerView.
        userList.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tienda, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance(): TiendaFragment = TiendaFragment()
    }
}