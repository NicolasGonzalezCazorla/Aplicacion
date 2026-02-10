package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class CarritoActivity : AppCompatActivity() {

    private lateinit var recyclerCarrito: RecyclerView
    private lateinit var txtResumenPago: TextView
    private lateinit var btnPagar: MaterialButton

    private lateinit var adapter: CarritoAdapter
    private val itemsMutable = CarritoManager.obtenerItems().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        recyclerCarrito = findViewById(R.id.recyclerCarrito)
        val btnVolver = findViewById<Button>(R.id.btnVolverCarrito)
        btnPagar = findViewById(R.id.btnPagar)
        txtResumenPago = findViewById(R.id.txtResumenPago)

        recyclerCarrito.layoutManager = LinearLayoutManager(this)

        adapter = CarritoAdapter(itemsMutable) {
            actualizarResumen()
        }
        recyclerCarrito.adapter = adapter


        recyclerCarrito.adapter = adapter

        btnVolver.setOnClickListener { finish() }

        btnPagar.setOnClickListener {
            actualizarResumen(mostrarDetalle = true)
        }

        actualizarResumen()
    }

    private fun actualizarResumen(mostrarDetalle: Boolean = false) {
        val items = itemsMutable

        if (items.isEmpty()) {
            txtResumenPago.text = "El carrito está vacío."
            btnPagar.isEnabled = false
            return
        }

        btnPagar.isEnabled = true

        var total = 0.0

        if (!mostrarDetalle) {
            for (it in items) {
                val p = it.producto
                val precioNum = p.precio.replace("€", "").replace(" ", "").replace(",", ".").toDoubleOrNull() ?: 0.0
                total += precioNum * it.cantidad
            }
            txtResumenPago.text = "Total: ${"%.2f".format(total)} €"
            return
        }

        val resumen = StringBuilder()
        resumen.append("Resumen de compra:\n\n")

        for (it in items) {
            val p = it.producto
            val precioNum = p.precio
                .replace("€", "")
                .replace(" ", "")
                .replace(",", ".")
                .toDoubleOrNull() ?: 0.0

            val subtotal = precioNum * it.cantidad
            total += subtotal

            resumen.append("- ${p.nombre} (x${it.cantidad}): ${"%.2f".format(subtotal)} €\n")
        }

        resumen.append("\nTOTAL: ${"%.2f".format(total)} €")
        txtResumenPago.text = resumen.toString()
    }

    override fun onResume() {
        super.onResume()
        // refrescar por si el carrito cambió
        itemsMutable.clear()
        itemsMutable.addAll(CarritoManager.obtenerItems())
        adapter.notifyDataSetChanged()
        actualizarResumen()
    }
}
