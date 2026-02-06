package com.example.myapplication

import Producto
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleProductoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)

        val nombre = intent.getStringExtra("nombre") ?: ""
        val precio = intent.getStringExtra("precio") ?: ""
        val categoria = intent.getStringExtra("categoria") ?: ""
        val imageResId = intent.getIntExtra("imageResId", R.drawable.ic_productos)

        findViewById<TextView>(R.id.txtNombreDetalle).text = nombre
        findViewById<TextView>(R.id.txtPrecioDetalle).text = precio
        findViewById<TextView>(R.id.txtCategoriaDetalle).text = categoria
        findViewById<ImageView>(R.id.imgDetalleProducto).setImageResource(imageResId)

        findViewById<Button>(R.id.btnVolver).setOnClickListener { finish() }

        findViewById<Button>(R.id.btnAgregarCarrito).setOnClickListener {
            // ✅ Guardar en carrito (lista en memoria)
            CarritoManager.productos.add(
                Producto(nombre, precio, categoria, imageResId)
            )
            finish()
        }
    }
}
