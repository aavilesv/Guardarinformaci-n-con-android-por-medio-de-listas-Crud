
package com.example.testmovil
import android.content.Intent
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar

class DetalleAuto : AppCompatActivity() {
    var index =0
    var toolbar: Toolbar?=null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_auto)
        toolbar =findViewById(R.id.toolbar)
        toolbar?.title ="Información"
        setSupportActionBar(toolbar)

        var actionbar=supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
         index = intent.getStringExtra("ID")?.toInt()!!

        mapear()
    }
    // esto es el menu y en este caso es menu_main
    override fun onCreateOptionsMenu(menu: Menu?):Boolean{
        menuInflater.inflate(R.menu.menu_detalle,menu)
        return super.onCreateOptionsMenu(menu)
    }
    // esto se dirige al boton add
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home ->{
            finish()
                return true
            }
            R.id.ideditar->{
                val intent =Intent(this,NuevoAuto::class.java)
                intent.putExtra("ID",index.toString())
                startActivity(intent)
                return true
            }
            R.id.ideliminar->{
                MainActivity.eliminarautomovil(index)
                finish()
                return true
            }

            else->{ return super.onOptionsItemSelected(item)}
        }

    }
    fun mapear(){
        var automovil = index?.let { MainActivity.obtenerAutmovil(it) }

        val txtnumasient=findViewById<TextView>(R.id.txtnumasient)
        val txtprecio=findViewById<TextView>(R.id.txtprecio)
        val txtnew_used=findViewById<TextView>(R.id.txtnew_used)
        val txtmodel=findViewById<TextView>(R.id.txtmodel)
        val  txtfecha=findViewById<TextView>(R.id.txtfecha)
        val  foto=findViewById<ImageView>(R.id.idphotoo)
        txtnumasient.text =automovil?.numasien
        txtprecio.text =automovil?.precio
        txtnew_used.text =automovil?.new_used
        txtmodel.text =automovil?.modelo
        txtfecha.text =automovil?.fecha
        foto.setImageResource(automovil?.foto)



    }
    override fun onResume() {
        super.onResume()
        mapear()
    }
}