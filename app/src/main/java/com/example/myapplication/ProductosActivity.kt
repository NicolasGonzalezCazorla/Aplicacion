package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        val recycler = findViewById<RecyclerView>(R.id.recyclerProductos)

        recycler.layoutManager = LinearLayoutManager(this)

        val listaProductos = listOf(
            Producto("Zapatillas Nike", "79.99 €", "Running"),
            Producto("Balón de fútbol", "19.99 €", "Fútbol"),
            Producto("Mancuernas", "39.99 €", "Fitness")
        )

        recycler.adapter = ProductoAdapter(listaProductos)
    }
}
