package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleProductoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)

        val nombre = intent.getStringExtra("nombre")
        val precio = intent.getStringExtra("precio")
        val categoria = intent.getStringExtra("categoria")

        findViewById<TextView>(R.id.txtNombreDetalle).text = nombre
        findViewById<TextView>(R.id.txtPrecioDetalle).text = precio
        findViewById<TextView>(R.id.txtCategoriaDetalle).text = categoria

        findViewById<Button>(R.id.btnVolver).setOnClickListener {
            finish() // vuelve a ProductosActivity
        }

        findViewById<Button>(R.id.btnAgregarCarrito).setOnClickListener {
            // Aquí más adelante puedes agregar el producto al carrito
        }
    }
}
