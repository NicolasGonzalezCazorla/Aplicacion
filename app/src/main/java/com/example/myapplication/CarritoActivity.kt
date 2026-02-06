package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class CarritoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        val recyclerCarrito = findViewById<RecyclerView>(R.id.recyclerCarrito)
        val btnVolver = findViewById<Button>(R.id.btnVolverCarrito)
        val btnPagar = findViewById<MaterialButton>(R.id.btnPagar)
        val txtResumenPago = findViewById<TextView>(R.id.txtResumenPago)

        recyclerCarrito.layoutManager = LinearLayoutManager(this)
        recyclerCarrito.adapter = CarritoAdapter(CarritoManager.productos)

        btnVolver.setOnClickListener { finish() }

        btnPagar.setOnClickListener {
            val lista = CarritoManager.productos

            if (lista.isEmpty()) {
                txtResumenPago.text = "El carrito está vacío."
                return@setOnClickListener
            }

            var total = 0.0
            val resumen = StringBuilder()
            resumen.append("Resumen de compra:\n\n")

            for (p in lista) {
                val precioNum = p.precio
                    .replace("€", "")
                    .replace(" ", "")
                    .replace(",", ".")
                    .toDoubleOrNull() ?: 0.0

                total += precioNum
                resumen.append("- ${p.nombre}: ${"%.2f".format(precioNum)} €\n")
            }

            resumen.append("\nTOTAL: ${"%.2f".format(total)} €")
            txtResumenPago.text = resumen.toString()
        }
    }

    override fun onResume() {
        super.onResume()
        (findViewById<RecyclerView>(R.id.recyclerCarrito).adapter as? CarritoAdapter)?.notifyDataSetChanged()
    }
}
