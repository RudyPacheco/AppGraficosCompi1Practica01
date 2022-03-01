package com.example.appgraficoscompipractica01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.analizadores.AnalizadorLexico
import com.analizadores.parser
import com.objetos.countOperacion
import com.objetos.errorE
import com.objetos.grafico
import java.io.Reader
import java.io.StringReader


class MainActivity : AppCompatActivity() {


    var botonEjecutar:Button ?=null;
    lateinit var textArea:EditText;
    var op:MutableList<countOperacion> = mutableListOf();
    var operaciones:ArrayList<countOperacion> = arrayListOf();
    var graficas:ArrayList<grafico> = arrayListOf();
    var erroresSin:ArrayList<errorE> = arrayListOf();
    var erroresLex:ArrayList<errorE> = arrayListOf();
    var totalBarras:Int=0;
    var totalPie:Int=0;
    var listGraficasEjecutar: java.util.ArrayList<String> = arrayListOf();
    var entrada:String?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botonEjecutar = findViewById(R.id.buttonEjecutar)
        textArea = findViewById(R.id.multiineTextAreaEntrada)



    }



    fun onClick(v: View?) {

       entrada = textArea.text.toString();
        val reader:Reader = StringReader(entrada);
        val analizadorLexico: AnalizadorLexico = AnalizadorLexico(reader);
        val parser: parser = parser(analizadorLexico);

        if (entrada.equals("")) {
            Toast.makeText(this,"No se a ingresado ningun texto",Toast.LENGTH_LONG).show();
        }else{

            try {
                parser.parse();
                operaciones = parser.listOperaciones as ArrayList<countOperacion>;
                graficas = parser.listGraficas as ArrayList<grafico>;
                erroresSin = parser.listErroresSin as ArrayList<errorE>;
                erroresLex = analizadorLexico.listErroresLex as ArrayList<errorE>;
                listGraficasEjecutar = parser.listGraficasEjecucion;
                totalBarras=parser.countBarras;
                totalPie=parser.getcountPie();


                if (erroresLex.isEmpty() && erroresSin.isEmpty() ){
                    var lanzar =Intent(this,menuBotones::class.java);
                    lanzar.putExtra("countOperaciones", operaciones);
                    lanzar.putExtra("totalBarras",totalBarras);
                    lanzar.putExtra("totalPie",totalPie);
                    lanzar.putExtra("graficas",graficas);
                    lanzar.putExtra("listaEjecucion",listGraficasEjecutar);
                    startActivity(lanzar);
                }else{
                    var lanzar =Intent(this,reporteErrores::class.java);
                    lanzar.putExtra("erroresLex",erroresLex);
                    lanzar.putExtra("erroresSin",erroresSin);
                    startActivity(lanzar);
                }
            }catch (e:Exception ){
                e.printStackTrace();
            }
        }







    }

}