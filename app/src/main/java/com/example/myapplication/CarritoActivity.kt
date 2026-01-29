package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CarritoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        val recyclerCarrito = findViewById<RecyclerView>(R.id.recyclerCarrito)
        val btnVolver = findViewById<Button>(R.id.btnVolverCarrito)

        recyclerCarrito.layoutManager = LinearLayoutManager(this)

        val listaCarrito = listOf(
            Producto("Zapatillas Nike", "79.99 €", "Running"),
            Producto("Balón de fútbol", "19.99 €", "Fútbol")
        )

        recyclerCarrito.adapter = CarritoAdapter(listaCarrito)

        btnVolver.setOnClickListener {
            finish()
        }
    }
}
