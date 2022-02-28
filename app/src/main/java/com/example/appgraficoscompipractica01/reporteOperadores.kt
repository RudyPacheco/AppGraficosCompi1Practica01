package com.example.appgraficoscompipractica01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.objetos.countOperacion

class reporteOperadores : AppCompatActivity() {
    var operaciones:ArrayList<countOperacion>?=null;
    var textView:TextView?=null;
    var tabla:TableLayout?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reporte_operadores)
        this.setTitle(R.string.tituloReporteOp);
        val bun = intent.extras;
        operaciones = bun?.get("countOperaciones") as ArrayList<countOperacion>;

        textView=findViewById(R.id.textView5) as TextView;
        tabla=findViewById(R.id.tablaOperadores) as TableLayout;

        textView!!.setText("");

        operaciones!!.forEach {
            println("XDXD"+it.operacion)

            var operador = TextView(this);
            var fila=TextView(this);
            var columna=TextView(this);
            var ocurrencia=TextView(this);
            var row=TableRow(this);
          //  operador.setPadding(30,30,30,30);
           // fila.setPadding(30,30,30,30);
            //columna.setPadding(30,30,30,30);
            //ocurrencia.setPadding(30,30,30,30);

            operador.setText(it.operacion);
            fila.setText(it.fila.toString());
            columna.setText(it.columna.toString());
            ocurrencia.setText(it.expresion);

            row.addView(operador);
            row.addView(fila);
            row.addView(columna);
            row.addView(ocurrencia);
            tabla!!.addView(row);
        }
    }
}