package com.example.testmovil

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.GridView
import android.widget.ListView
import android.widget.Switch
import android.widget.ViewSwitcher
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import com.example.testmovil.clases.Automovil
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var toolbar: Toolbar?=null
//    private val db= AppDatabase.getDatabase(this)
    var lista:ListView?=null
    var grid:GridView?=null
var switcherView: ViewSwitcher?=null
    // esto te ayuda para hacer el objeto estatico
    companion object{
        var automovils:ArrayList<Automovil>?=null
        var adaptadorr:AdaptadorCustom?=null
        var adaptadorrgriew:AdaptadorCustomGriew?=null
        fun agregarcontacto(contacto: Automovil){
            adaptadorr?.addItem(contacto)
        }
        fun obtenerAutmovil(index:Int):Automovil{
            return adaptadorr?.getItem(index) as Automovil
        }
        fun eliminarautomovil(index:Int){
            adaptadorr?.removeIte(index)

        }
        fun actulizarautomovil(index: Int,nuevocontacto:Automovil){

            adaptadorr?.Updatee(index,nuevocontacto)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar =findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        automovils = ArrayList()

        automovils?.add(Automovil("23","15","Nuevo","Mazda","2020/12/11",R.drawable.foto01))
        automovils?.add(Automovil("20","15","Usado","Toyota","2021/01/11",R.drawable.foto02))

        lista =findViewById<ListView>(R.id.lista)
        grid =findViewById<GridView>(R.id.grid)
        // aqui llamamos a la clase AdaptadorCustom
        adaptadorr = AdaptadorCustom(this,automovils!!)
        adaptadorrgriew= AdaptadorCustomGriew(this,automovils!!)
        switcherView =findViewById(R.id.viewswitcher)

        lista?.adapter = adaptadorr
        grid?.adapter=adaptadorrgriew
        lista?.setOnItemClickListener{ parent,view,position,id->
            val intent = Intent(this,DetalleAuto::class.java)
            intent.putExtra("ID",position.toString())
            startActivity(intent)
        }
    }
    // esto es el menu y en este caso es menu_main
    override fun onCreateOptionsMenu(menu: Menu?):Boolean{
        menuInflater.inflate(R.menu.menu_main,menu)
        val serchManager =getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val itemBusqueda =menu?.findItem(R.id.idsearcg)
        val searchView = itemBusqueda?.actionView as SearchView
        // valido del swith
        val itemswitch= menu?.findItem(R.id.idswitchmenu)
        itemswitch?.setActionView(R.layout.switch_item)
        val swithview=itemswitch?.actionView?.findViewById<Switch>(R.id.idcambiaavista)
        searchView.setSearchableInfo(serchManager.getSearchableInfo(componentName))
        searchView.queryHint="Buscar Automovil"
        searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
//preparar los datos

        }
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
            //filtar
            adaptadorr?.filtrar(newText!!)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // filtar
                return true
            }

        })
        swithview?.setOnCheckedChangeListener { buttonView, isChecked ->
switcherView?.showNext()
        }
        return super.onCreateOptionsMenu(menu)
    }
    // esto se dirige al boton add
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.idnuevo->{
                val intent = Intent(this,NuevoAuto::class.java)
                startActivity(intent)
                return true
            }else->{ return super.onOptionsItemSelected(item)}
        }

    }
    // este metodo refrezca los cambios de la vista en este caso es el ingreso de los datos

    override fun onResume() {
        super.onResume()
        adaptadorr?.notifyDataSetChanged()
    }
}
