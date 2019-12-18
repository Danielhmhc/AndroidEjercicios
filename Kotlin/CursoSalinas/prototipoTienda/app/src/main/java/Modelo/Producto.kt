package Modelo

class Producto (nombre:String,Descripcion:String,Imagen:Int,Precio:Float){
    var nombre: String = ""
    var Descripcion: String = ""
    var Imagen: Int = 0
    var Precio: Float = 15.99F
    init {
        this.nombre=nombre
        this.Descripcion=Descripcion
        this.Imagen=Imagen
        this.Precio=Precio
    }
}
