package com.example.testmovil.clases

open class Automovil(numasien:String , precio:String,new_used:String,modelo:String,fecha:String,foto:Int){
    var numasien:String?=""
    var precio:String=""
      var new_used:String=""
     var modelo:String=""
      var fecha:String=""
     var foto:Int=0
    init{
        this.numasien=numasien
        this.precio=precio
        this.new_used=new_used
        this.modelo=modelo
        this.fecha=fecha
        this.foto=foto
    }

}