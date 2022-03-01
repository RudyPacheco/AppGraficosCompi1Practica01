package com.example.appgraficoscompipractica01

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.objetos.grafico
import com.objetos.graficoBarras
import com.objetos.graficoPIe
import java.lang.Exception

class generarGraficas : AppCompatActivity() {

    var graficas:ArrayList<grafico> = arrayListOf();
    var barras: ArrayList<graficoBarras> = arrayListOf()
    var pies: ArrayList<graficoPIe> = arrayListOf()
    var tituloTem:String = "";
    private var layout: LinearLayout? = null
    var items:ArrayList<String> = arrayListOf()
    var valores:ArrayList<Double> = arrayListOf()
    private val colors = intArrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.DKGRAY,Color.CYAN, Color.MAGENTA, Color.WHITE, Color.YELLOW)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generar_graficas)
        layout = findViewById<View>(R.id.layoutGraficos) as LinearLayout
        val bun = intent.extras;
        graficas = bun?.get("graficas") as ArrayList<grafico>;
        separarGraficas()

        //Se generan las graficas de barras
        var index=0
        barras.forEach(){
            ordenandoEjeX(index)
            ordenandoEjeY(index)
            unir(index, true)
            tituloTem = it.titulo.toString()
            val barchart = BarChart(this)
            val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 700)
            barchart.layoutParams = lp
            createCharts(barchart)
            layout?.addView(barchart)
            index++
        }

        //Se generan las graficas de Pie
        index = 0
        pies.forEach(){
            ordenandoValores(index)
            ordenandoEtiquetas(index)
            unir(index, false)
            calculoExtra(index,it.tipo.toString())
            tituloTem = it.titulo.toString()
            val pieChart = PieChart(this)
            val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 700)
            pieChart.layoutParams = lp
            createPieCharts(pieChart)
            layout?.addView(pieChart)
            index++
        }
    }

    private fun getSameChart(
        chart: Chart<*>,
        description: String,
        textColor: Int,
        background: Int
    ): Chart<*>? {
        chart.description.text = description
        chart.description.textColor = textColor
        chart.description.textSize = 15f
        chart.setBackgroundColor(background)
        legend(chart)
        return chart
    }


    fun createCharts(barChart: BarChart) {
        var barChart = barChart
        barChart = getSameChart(barChart, tituloTem, Color.RED, Color.LTGRAY) as BarChart
        barChart.setDrawGridBackground(true)
        barChart.data = getBarData()
        barChart.invalidate()
        barChart.legend.isEnabled = true
        //Configuracion de numeros eje x, eje y
        axisX(barChart.xAxis) //
        axisLeft(barChart.axisLeft)
        axisRight(barChart.axisRight)
    }

    //diseño de la grafica Pie
    fun createPieCharts(pieChart: PieChart?) {
        var pieChart = pieChart
        pieChart = getSameChart(pieChart!!, tituloTem, Color.BLUE, Color.LTGRAY) as PieChart?
        pieChart!!.holeRadius = 12f
        pieChart.setHoleColor(Color.WHITE)
        pieChart.transparentCircleRadius = 15f
        pieChart.setTransparentCircleColor(Color.TRANSPARENT)
        pieChart.invalidate()
        pieChart.data = getPieData()
        pieChart.isDrawHoleEnabled = false
    }


    private fun getPieData(): PieData {
        val pieDataSet = getDataSame(PieDataSet(getPieEntries(), "")) as PieDataSet
        pieDataSet.sliceSpace = 3f
        pieDataSet.valueFormatter = PercentFormatter()
        return PieData(pieDataSet)
    }

    //se agrega los valores en PIe
    private fun getPieEntries(): java.util.ArrayList<PieEntry>? {
        val entries = java.util.ArrayList<PieEntry>()
        for (i in valores.indices) entries.add(PieEntry(valores.get(i).toFloat()))
        return entries
    }

    private fun getBarData(): BarData? {
        val barDataSet = getDataSame(BarDataSet(getBarEntries(), "")) as BarDataSet
        val barData = BarData(barDataSet)
        barData.barWidth = 0.45f // grosor de la barra
        return barData
    }

    //se agrega los valores en barras
    private fun getBarEntries(): java.util.ArrayList<BarEntry>? {
        val entries = java.util.ArrayList<BarEntry>()
        for (i in valores.indices) entries.add(BarEntry(i.toFloat(), valores[i].toFloat()))
        return entries
    }

    /**
     * Personalizar el diseno de los datos en la grafica
     * Colores de las barras/partes de la grafica
     * Color de los valores
     * Tamanio de los valores
     */
    private fun getDataSame(dataSet: DataSet<*>): DataSet<*>? {
        dataSet.setColors(*colors)
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 15f
        return dataSet
    }

    //etiquetas
    private fun axisX(axis: XAxis) {
        axis.isGranularityEnabled = true
        axis.position = XAxis.XAxisPosition.BOTTOM
        axis.valueFormatter = IndexAxisValueFormatter(items)
    }

    //lado izquierdo de la grafica
    private fun axisLeft(axis: YAxis) {
        axis.spaceTop = 100f
        axis.axisMinimum = 0f
        axis.granularity = 50f


    }

    //lado derecho de la grafica
    private fun axisRight(axis: YAxis) {
        axis.isEnabled = false
    }

    //Para modificar el diseno de la legenda y su texto acompaniante
    private fun legend(chart: Chart<*>) {
        val legend = chart.legend
        legend.form = Legend.LegendForm.LINE
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        val entries = java.util.ArrayList<LegendEntry>()
        for (i in items.indices) {
            val entry = LegendEntry()
            entry.formColor = colors.get(i)
            entry.label = items.get(i)
            entries.add(entry)
        }
        legend.setCustom(entries)
    }




    fun ordenandoEjeX(index: Int){
        var itemsTemp:ArrayList<String> = arrayListOf()
        var cont:Int=barras.get(index).etiquetas.size-1
        barras.get(index).etiquetas.forEach(){
            itemsTemp.add(barras.get(index).etiquetas.get(cont))
            cont--
        }
        items = itemsTemp
    }


    fun ordenandoEjeY(index: Int){
        var valoresTem:ArrayList<Double> = arrayListOf()
        var cont:Int=barras.get(index).etiquetas.size-1
        barras.get(index).valores.forEach(){
            valoresTem.add(barras.get(index).valores.get(cont))
            cont--
        }
        valores = valoresTem

    }

   //union de valores con etiquetas
    fun unir(index: Int, esBarra: Boolean){
        try {
            if(esBarra){
                barras.get(index).unir.forEach(){
                    println("---${it.posx} ${it.posy}")
                    barras.get(index).valores.set(it.posx,valores.get(it.posy))
                }
                valores = barras.get(index).valores as ArrayList<Double>
            }else{
                pies.get(index).unir.forEach(){
                    println("---${it.posx} ${it.posy}")
                    pies.get(index).valores.set(it.posx,valores.get(it.posy))
                }
                valores = pies.get(index).valores as ArrayList<Double>
            }
        }catch (ex: Exception){
            Toast.makeText(this,"Haz cometido un error Semantico", Toast.LENGTH_LONG).show()
        }

    }


    fun ordenandoValores(index: Int){
        var valoresTem:ArrayList<Double> = arrayListOf()
        var cont:Int=pies.get(index).etiquetas.size-1
        pies.get(index).valores.forEach(){
            valoresTem.add(pies.get(index).valores.get(cont))
            cont--
        }
        valores = valoresTem

    }


    fun ordenandoEtiquetas(index: Int){
        var itemsTemp:ArrayList<String> = arrayListOf()
        var cont:Int=pies.get(index).etiquetas.size-1
        pies.get(index).etiquetas.forEach(){
            itemsTemp.add(pies.get(index).etiquetas.get(cont))
            cont--
        }
        items = itemsTemp
    }

    //calular el extra
    fun calculoExtra(index: Int, esCantidad:String){
        var suma:Double=0.0;
        valores.forEach(){
            suma += it
        }
        if (esCantidad.equals("Cantidad")){
            var cont=0
            valores.forEach(){
                pies.get(index).valores.set(cont,it*100.0 / pies.get(index).total!!)
                cont++
            }
            pies.get(index).valores.add(pies.get(index).total!!-suma)
            valores = pies.get(index).valores as ArrayList<Double>
        }else{
            valores.add(360.0-suma)
        }
        items.add(pies.get(index).extra.toString())

    }

    fun separarGraficas(){
        graficas.forEach(){
            if (it is graficoBarras){
                barras.add(it)
            }else{
                if (it is graficoPIe){
                    pies.add(it)
                }
            }
        }
    }








}



