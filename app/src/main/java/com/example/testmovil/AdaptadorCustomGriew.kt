package com.example.testmovil
import android.content.Context
import com.example.testmovil.clases.Automovil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
class AdaptadorCustomGriew(var contexto: Context,items:ArrayList<Automovil>):BaseAdapter() {
    // almacenar los elementos que se van almecenar en el listview
    var items:ArrayList<Automovil>?=null
    var copiaItems:ArrayList<Automovil>?=null
    init {
        this.items= ArrayList(items)
        this.copiaItems=items
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var holderr:ViewHolder?=null
        var vista:View?=convertView
        if (vista ==null){
            vista= LayoutInflater.from(contexto).inflate(R.layout.template_automovil_griew,null)
            holderr = ViewHolder(vista)

            vista.tag=holderr
        }else{
            holderr=vista.tag as? ViewHolder
        }
        val item=getItem(position) as Automovil
        holderr?.modelo?.text=item.modelo
        holderr?.foto?.setImageResource(item.foto)
        return vista!!
    }
    override fun getItem(position: Int): Any {
        return this.items?.get(position)!!
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getCount(): Int {
        // numero de elementos de mi lista
        //? es opcional
        return this.items?.count()!!
    }
    fun addItem(item:Automovil){
        copiaItems?.add(item)
        items= ArrayList(copiaItems)
        notifyDataSetChanged()
    }
    fun filtrar(str:String){
        items?.clear()
        if(str.isEmpty()){
            items= ArrayList(copiaItems)
            notifyDataSetChanged()
            return
        }
        var busqueda =str
        busqueda =busqueda.toLowerCase()
        for (item in copiaItems!!){
            val nombre =item.modelo.toLowerCase()
            if(nombre.contains(busqueda)){
                items?.add(item)
            }
        }
        notifyDataSetChanged()
    }
    fun Updatee(item:Int,newitem:Automovil){
        copiaItems?.set(item,newitem)
        items =ArrayList(copiaItems)
        notifyDataSetChanged()
    }
    fun removeIte(item:Int){
        copiaItems?.removeAt(item)
        items =ArrayList(copiaItems)
        notifyDataSetChanged()
    }
    // este llama a la vista contacto
    private class ViewHolder(vista:View){
        var modelo:TextView?=null
        var foto:ImageView?=null

        init {
            modelo=vista.findViewById(R.id.txtnombre)
            foto=vista.findViewById(R.id.idphotooo)

        }
    }
}