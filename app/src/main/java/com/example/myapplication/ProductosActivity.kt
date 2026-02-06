package com.example.myapplication

import Producto
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
            Producto("Zapatillas Adidas Run", "69.99 €", "Running", R.drawable.zapatillas_adidas_run),
            Producto("Balón de fútbol Adidas", "19.99 €", "Fútbol", R.drawable.balon_adidas),
            Producto("Balón de baloncesto Spalding", "24.99 €", "Baloncesto", R.drawable.balon_spalding),
            Producto("Mancuernas 10kg", "39.99 €", "Fitness", R.drawable.mancuernas),
            Producto("Mancuernas 20kg", "59.99 €", "Fitness", R.drawable.mancuernas_20kg),
            Producto("Pantalón corto Adidas", "22.99 €", "Ropa", R.drawable.pantalon_corto_adidas),
            Producto("Sudadera Puma", "49.99 €", "Ropa", R.drawable.sudadera_puma),
            Producto("Guantes de gimnasio", "14.99 €", "Fitness", R.drawable.guantes_de_gym),
            Producto("Esterilla yoga", "18.99 €", "Yoga", R.drawable.esterilla_yoga),
            Producto("Cuerda para saltar", "9.99 €", "Cardio", R.drawable.comba),
            Producto("Casco bicicleta", "34.99 €", "Ciclismo", R.drawable.casco_bici),
            Producto("Guantes de boxeo", "44.99 €", "Boxeo", R.drawable.guantes_boxeo),
            Producto("Saco de boxeo", "89.99 €", "Boxeo", R.drawable.saco_boxeo),
            Producto("Raqueta de tenis Wilson", "99.99 €", "Tenis", R.drawable.raqueta_de_tenis),
            Producto("Pelotas de tenis (pack 3)", "7.99 €", "Tenis", R.drawable.pelotas_tenis),
            Producto("Botella térmica", "12.99 €", "Accesorios", R.drawable.botellas_termicas),
            Producto("Mochila deportiva", "29.99 €", "Accesorios", R.drawable.mochila_deportiva),
            Producto("Calcetines deportivos", "8.99 €", "Ropa", R.drawable.calcetines_deportivos)
        )

        recycler.adapter = ProductoAdapter(listaProductos)

        btnVolver.setOnClickListener { finish() }
    }
}
