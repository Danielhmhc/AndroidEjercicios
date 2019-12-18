package presentador

import Modelo.Producto
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielhmhc.prototipotienda.R
import kotlinx.android.synthetic.main.item_articulo.view.*

class ArticuloAdaptador (var context: Context, tasks: MutableList<Producto>): RecyclerView.Adapter<ArticuloAdaptador.ViewHolder>() {

    var items: MutableList<Producto>? = null

    init {
        this.items = tasks
    }

    // Se crea una instancia del ViewHolder inflando la vista del item que ya habiamos definido
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticuloAdaptador.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_articulo, parent, false)
        return ViewHolder(view)
    }

    // Retorna la catidad de elementos en nuestra coleccion de datos.
    override fun getItemCount(): Int {
        return this.items?.size!!
    }

    //Se obtiene una instancia de viewHolder por cada elemento de nuestra coleccion de datos y se le asigan sus correspondientes valores.
    override fun onBindViewHolder(holder: ArticuloAdaptador.ViewHolder, position: Int) {
        holder.bind(items?.get(position))
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.imvFotoArticulo
        private val userName = view.lblNombreArt
        private val descripcion = view.lblDescripcion
        private val precio=view.lblPrecio
        fun bind(Articulo: Producto?) {
            userName.text = Articulo!!.nombre
            descripcion.text = Articulo.Descripcion
            imageView.setImageResource(Articulo.Imagen)
            precio.text=" $ " + Articulo.Precio.toString()
        }
    }
}