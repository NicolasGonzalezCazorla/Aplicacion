package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CarritoActivity : AppCompatActivity() {

    private lateinit var recyclerCarrito: RecyclerView
    private lateinit var adapter: CarritoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        recyclerCarrito = findViewById(R.id.recyclerCarrito)
        recyclerCarrito.layoutManager = LinearLayoutManager(this)

        // Ejemplo de productos añadidos
        val listaCarrito = listOf(
            Producto("Zapatillas Nike", "79.99 €", "Running"),
            Producto("Balón de fútbol", "19.99 €", "Fútbol")
        )

        adapter = CarritoAdapter(listaCarrito)
        recyclerCarrito.adapter = adapter
    }
}
