package com.objetos

import java.io.Serializable

open class grafico: Serializable{
    //definicion de variables
    var titulo:String? = null;
    var ejex:MutableList<String> = mutableListOf();
    var ejey:MutableList<Double> = mutableListOf();
    var etiquetas:MutableList<String> = mutableListOf();
    var valores:MutableList<Double> = mutableListOf();
    var unir:MutableList<tupla> = mutableListOf();
    var tipo: String ?= null;
    var total:Double? = null;
    var extra:String? =null;


    //contructores
    constructor();
    constructor(titulo:String?,unir:MutableList<tupla>,etiquetas: MutableList<String>,valores: MutableList<Double>) {
        this.titulo=titulo;
        this.unir=unir;
        this.etiquetas=etiquetas;
        this.valores=valores;
    }
    constructor(titulo: String?,tipo:String?,etiquetas:MutableList<String>,valores:MutableList<Double>,total:Double?,unir: MutableList<tupla>,extra:String?){
        this.titulo=titulo;
        this.tipo=tipo;
        this.etiquetas=etiquetas;
        this.valores=valores;
        this.total=total;
        this.unir=unir;
        this.extra=extra;
    }

    fun clearGrafica(){
        this.titulo=null;
        this.ejex= mutableListOf();
        this.ejey= mutableListOf();
        this.etiquetas= mutableListOf();
        this.valores = mutableListOf();
        this.unir= mutableListOf();
        this.tipo=null;
        this.total=null;
        this.extra=null;
    }

}