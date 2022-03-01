package com.objetos

import java.io.Serializable

class tupla: Serializable{
    var posy:Int=0;
    var posx:Int=0;

    constructor(posy: Int,posx: Int){
        this.posy=posy;
        this.posx=posx;

    }

}