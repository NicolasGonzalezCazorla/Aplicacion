package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import Producto
class DetalleProductoActivity : AppCompatActivity() {

    private var cantidad = 1

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

        val txtCantidad = findViewById<TextView>(R.id.txtCantidad)
        val btnMenos = findViewById<MaterialButton>(R.id.btnMenos)
        val btnMas = findViewById<MaterialButton>(R.id.btnMas)
        val btnAgregar = findViewById<MaterialButton>(R.id.btnAgregarCarrito)

        fun refrescarUI() {
            txtCantidad.text = cantidad.toString()
            btnMenos.isEnabled = cantidad > 1
            btnAgregar.text = "Añadir al carrito (x$cantidad)"
        }

        btnMenos.setOnClickListener {
            if (cantidad > 1) cantidad--
            refrescarUI()
        }

        btnMas.setOnClickListener {
            if (cantidad < 99) cantidad++
            refrescarUI()
        }

        refrescarUI()

        findViewById<Button>(R.id.btnVolver).setOnClickListener { finish() }

        btnAgregar.setOnClickListener {
            CarritoManager.agregarProducto(
                Producto(nombre, precio, categoria, imageResId),
                cantidad
            )

            // ✅ feedback bonito, sin salir de la pantalla
            Snackbar.make(btnAgregar, "Añadido x$cantidad al carrito", Snackbar.LENGTH_SHORT).show()

            // opcional: reset a 1 después de añadir
            cantidad = 1
            refrescarUI()
        }
    }
}
