package com.example.testmovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.example.testmovil.clases.Automovil

class NuevoAuto : AppCompatActivity() {
    var fotoindex:Int=0
    var foto:ImageView?=null
    val fotos=arrayOf(R.drawable.foto01,R.drawable.foto02,R.drawable.foto03)
    var toolbar: Toolbar?=null
    var index:Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_auto)
        toolbar =findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        var actionbar=supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        foto=findViewById<ImageView>(R.id.ivphoto)
        foto?.setOnClickListener {
            seleccionarfotos()
        }
// reconocer la accion de nuevo vs editar
        if(intent.hasExtra("ID")){
            index =intent.getStringExtra("ID")?.toInt()!!
            rellenarDatos(index)
        }
    }
    // esto es el menu y en este caso es menu_main
    override fun onCreateOptionsMenu(menu: Menu?):Boolean{
        menuInflater.inflate(R.menu.nuevo,menu)
        return super.onCreateOptionsMenu(menu)
    }
    // esto se dirige al boton add
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
            R.id.crearnuevo->{
                // va a crear un nuevo de tipo contacto
                //val intent = Intent(this,nuevo::class.java)
                //startActivity(intent)
                val numasien=findViewById<EditText>(R.id.txttnumasien)
                val precio=findViewById<EditText>(R.id.txttprecio)
                val new_used=findViewById<EditText>(R.id.txttnew_used)
                val modelo=findViewById<EditText>(R.id.txttmodelo)
                val fecha=findViewById<EditText>(R.id.txttfecha)

                // validar campos
                var campos  =ArrayList<String>()
                campos.add(numasien.text.toString())
                campos.add(precio.text.toString())
                campos.add(new_used.text.toString())
                campos.add(modelo.text.toString())
                campos.add(fecha.text.toString())
                var flag =0
                for (campo in campos){
                    if(campo.isNullOrEmpty()){
                        flag++
                    }}
                    if (flag  >0){
                        Toast.makeText(this,"Rellene todos los campos",Toast.LENGTH_LONG).show()
                    }else{
                        if(index >-1){
                            println(obtenerfoto(fotoindex))
                            MainActivity.actulizarautomovil(index,Automovil(numasien.text.toString(),precio.text.toString(),new_used.text.toString(),(modelo.text.toString()),(fecha.text.toString()),obtenerfoto(fotoindex)))
                        }else {
                            println(obtenerfoto(fotoindex))
                            MainActivity.agregarcontacto(Automovil(numasien.text.toString(),precio.text.toString(),new_used.text.toString(),(modelo.text.toString()),(fecha.text.toString()),obtenerfoto(fotoindex)))
                            // para finalizar la guardada

                        }
                        finish()
                    }

                return true
            }else->{ return super.onOptionsItemSelected(item)}
            // db.automovilDao().insertAll(automovill)

        }
    }
    fun rellenarDatos(index:Int){
        val automovil =MainActivity.obtenerAutmovil(index)

        val txtnumasient=findViewById<EditText>(R.id.txttnumasien)
        val txtprecio=findViewById<EditText>(R.id.txttprecio)
        val txtnew_used=findViewById<EditText>(R.id.txttnew_used)
        val txtmodel=findViewById<EditText>(R.id.txttmodelo)
        val  txtfecha=findViewById<EditText>(R.id.txttfecha)
        val  foto=findViewById<ImageView>(R.id.ivphoto)

        txtnumasient.setText(automovil.numasien,TextView.BufferType.EDITABLE)
        txtprecio.setText(automovil.precio,TextView.BufferType.EDITABLE)
        txtnew_used.setText(automovil.new_used,TextView.BufferType.EDITABLE)
        txtmodel.setText(automovil.modelo,TextView.BufferType.EDITABLE)
        txtfecha.setText(automovil.fecha,TextView.BufferType.EDITABLE)
        foto.setImageResource(automovil.foto)
        
    }
    fun seleccionarfotos(){
        // crea un dialogo
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona imagen de perfil")
        // mostrar
        val adaptadorDialogo= ArrayAdapter<String>(this,android.R.layout.simple_selectable_list_item)
        adaptadorDialogo.add("foto01")
        adaptadorDialogo.add("foto02")
        adaptadorDialogo.add("foto03")
        //  ingresamos los valores me da el indice 
        builder.setAdapter(adaptadorDialogo){
            dialog, which ->  
            fotoindex=which
            foto?.setImageResource(obtenerfoto(fotoindex))
        }
        
        builder.setNegativeButton("Cancelar"){
            dialog, which ->
            dialog.dismiss()
        }

        builder.show()

    }
    fun obtenerfoto(index:Int):Int{
        return fotos.get(index)
    }

}
