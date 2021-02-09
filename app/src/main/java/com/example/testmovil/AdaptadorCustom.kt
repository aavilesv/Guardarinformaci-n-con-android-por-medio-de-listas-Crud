package com.example.testmovil

import android.content.Context
import com.example.testmovil.clases.Automovil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
class AdaptadorCustom(var contexto: Context,items:ArrayList<Automovil>):BaseAdapter() {
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
            vista= LayoutInflater.from(contexto).inflate(R.layout.template_auto,null)
            holderr = ViewHolder(vista)
            vista.tag=holderr
        }else{
            holderr=vista.tag as? ViewHolder
        }
        val item=getItem(position) as Automovil
        holderr?.numasien?.text =item.numasien
        holderr?.precio?.text =item.precio
        holderr?.new_used?.text =item.new_used
        holderr?.modelo?.text=item.modelo
        holderr?.fecha?.text=item.fecha
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
        var numasien:TextView?=null
        var precio:TextView?=null
        var new_used:TextView?=null
        var modelo:TextView?=null
        var fecha:TextView?=null
        var foto:ImageView?=null

        init {
            numasien=vista.findViewById(R.id.txtnumasient)
            precio=vista.findViewById(R.id.txtprecio)
            new_used=vista.findViewById(R.id.txtnew_used)
            modelo=vista.findViewById(R.id.txtmodel)
            fecha=vista.findViewById(R.id.txtfecha)
            foto=vista.findViewById(R.id.idhoto)

        }
    }
}