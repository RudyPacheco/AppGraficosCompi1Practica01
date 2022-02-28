package com.objetos

import java.io.Serializable

class errorE:Serializable {
    var lexema:String;
    var linea : Int;
    var columna:Int;
    var descripcion:String;
    var tipo:String;

    constructor(lexema:String,fila:Int,columna:Int,descripcion:String,tipo:String){
        this.lexema=lexema;
        this.linea=fila;
        this.columna=columna;
        this.descripcion=descripcion;
        this.tipo=tipo;
    }




}