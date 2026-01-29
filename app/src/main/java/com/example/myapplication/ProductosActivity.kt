package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        val recycler = findViewById<RecyclerView>(R.id.recyclerProductos)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        recycler.layoutManager = LinearLayoutManager(this)

        val listaProductos = listOf(
            Producto("Zapatillas Nike Air", "79.99 €", "Running"),
            Producto("Zapatillas Adidas Run", "69.99 €", "Running"),
            Producto("Balón de fútbol Adidas", "19.99 €", "Fútbol"),
            Producto("Balón de baloncesto Spalding", "24.99 €", "Baloncesto"),
            Producto("Mancuernas 10kg", "39.99 €", "Fitness"),
            Producto("Mancuernas 20kg", "59.99 €", "Fitness"),
            Producto("Camiseta deportiva Nike", "25.99 €", "Ropa"),
            Producto("Pantalón corto Adidas", "22.99 €", "Ropa"),
            Producto("Sudadera Puma", "49.99 €", "Ropa"),
            Producto("Guantes de gimnasio", "14.99 €", "Fitness"),
            Producto("Esterilla yoga", "18.99 €", "Yoga"),
            Producto("Cuerda para saltar", "9.99 €", "Cardio"),
            Producto("Casco bicicleta", "34.99 €", "Ciclismo"),
            Producto("Guantes de boxeo", "44.99 €", "Boxeo"),
            Producto("Saco de boxeo", "89.99 €", "Boxeo"),
            Producto("Raqueta de tenis Wilson", "99.99 €", "Tenis"),
            Producto("Pelotas de tenis (pack 3)", "7.99 €", "Tenis"),
            Producto("Botella térmica", "12.99 €", "Accesorios"),
            Producto("Mochila deportiva", "29.99 €", "Accesorios"),
            Producto("Calcetines deportivos", "8.99 €", "Ropa")
        )

        recycler.adapter = ProductoAdapter(listaProductos)

        btnVolver.setOnClickListener {
            finish()
        }
    }
}
