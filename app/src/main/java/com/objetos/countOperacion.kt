package com.objetos

import java.io.Serializable

class countOperacion:Serializable {
    var operacion:String;
    var fila:Int;
    var columna:Int;
    var expresion:String;

    constructor(operacion:String, fila:Int,columna:Int,expresion:String){
        this.operacion=operacion;
        this.fila=fila;
        this.columna=columna;
        this.expresion=expresion;
    }



}