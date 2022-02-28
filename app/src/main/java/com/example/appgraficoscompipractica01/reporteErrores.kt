package com.example.appgraficoscompipractica01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.objetos.errorE

class reporteErrores : AppCompatActivity() {

    var erroresSin:ArrayList<errorE> = arrayListOf();
    var erroresLex:ArrayList<errorE> = arrayListOf();
    var textView: TextView?=null;
    var tabla: TableLayout?=null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reporte_errores)

        val bun = intent.extras;
        erroresLex=bun?.get("erroresLex") as ArrayList<errorE>;
        erroresSin=bun?.get("erroresSin") as ArrayList<errorE>;

        textView=findViewById(R.id.textViewErrores) as TextView;
        tabla=findViewById(R.id.tableErrores) as TableLayout;

        textView!!.setText("");

        erroresLex.forEach {
            var lexema = TextView(this);
            var linea = TextView(this);
            var columna = TextView(this);
            var tipo = TextView(this);
            var descripcion = TextView(this);
            var row= TableRow(this);

            lexema.setText(it.lexema);
            linea.setText(it.linea.toString());
            columna.setText(it.columna.toString());
            tipo.setText(it.tipo);
            descripcion.setText(it.descripcion)

            row.addView(lexema);
            row.addView(linea);
            row.addView(columna);
            row.addView(tipo);
            row.addView(descripcion);
            tabla!!.addView(row);

        }

        erroresSin.forEach {
            var lexema = TextView(this);
            var linea = TextView(this);
            var columna = TextView(this);
            var tipo = TextView(this);
            var descripcion = TextView(this);
            var row= TableRow(this);

            lexema.setText(it.lexema);
            linea.setText(it.linea.toString());
            columna.setText(it.columna.toString());
            tipo.setText(it.tipo);
            descripcion.setText(it.descripcion)

            row.addView(lexema);
            row.addView(linea);
            row.addView(columna);
            row.addView(tipo);
            row.addView(descripcion);
            tabla!!.addView(row);
        }


    }
}