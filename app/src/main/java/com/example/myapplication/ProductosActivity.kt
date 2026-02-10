package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import Producto
class ProductosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        val recycler = findViewById<RecyclerView>(R.id.recyclerProductos)
        val btnVolver = findViewById<Button>(R.id.btnVolver)
        val etBuscar = findViewById<TextInputEditText>(R.id.etBuscar)
        val spCategoria = findViewById<Spinner>(R.id.spCategoria)

        recycler.layoutManager = LinearLayoutManager(this)

        val listaOriginal = listOf(
            Producto("Zapatillas Adidas Run", "69.99 €", "Running", R.drawable.zapatillas_adidas_run),
            Producto("Balón de fútbol Adidas", "19.99 €", "Fútbol", R.drawable.balon_adidas),
            Producto("Balón de baloncesto Spalding", "24.99 €", "Baloncesto", R.drawable.balon_spalding),
            Producto("Mancuernas 10kg", "39.99 €", "Fitness", R.drawable.mancuernas),
            Producto("Mancuernas 20kg", "59.99 €", "Fitness", R.drawable.mancuernas_20kg),
            Producto("Pantalón corto Adidas", "22.99 €", "Ropa", R.drawable.pantalon_corto_adidas),
            Producto("Sudadera Puma", "49.99 €", "Ropa", R.drawable.sudadera_puma),
            Producto("Guantes de gimnasio", "14.99 €", "Fitness", R.drawable.guantes_de_gym),
            Producto("Esterilla yoga", "18.99 €", "Yoga", R.drawable.esterilla_yoga),
            Producto("Comba", "9.99 €", "Cardio", R.drawable.comba),
            Producto("Casco bicicleta", "34.99 €", "Ciclismo", R.drawable.casco_bici),
            Producto("Guantes de boxeo", "44.99 €", "Boxeo", R.drawable.guantes_boxeo),
            Producto("Saco de boxeo", "89.99 €", "Boxeo", R.drawable.saco_boxeo),
            Producto("Raqueta de tenis Wilson", "99.99 €", "Tenis", R.drawable.raqueta_de_tenis),
            Producto("Pelotas de tenis (pack 3)", "7.99 €", "Tenis", R.drawable.pelotas_tenis),
            Producto("Botella térmica", "12.99 €", "Accesorios", R.drawable.botellas_termicas),
            Producto("Mochila deportiva", "29.99 €", "Accesorios", R.drawable.mochila_deportiva),
            Producto("Calcetines deportivos", "8.99 €", "Ropa", R.drawable.calcetines_deportivos)
        )

        val adapter = ProductoAdapter(listaOriginal)
        recycler.adapter = adapter

        // ✅ Spinner categorías (Todas + únicas)
        val categorias = mutableListOf("Todas")
        categorias.addAll(listaOriginal.map { it.categoria }.distinct().sorted())

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categorias
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spCategoria.adapter = spinnerAdapter

        fun aplicarFiltros() {
            val texto = etBuscar.text?.toString()?.trim()?.lowercase() ?: ""
            val categoriaSel = spCategoria.selectedItem?.toString() ?: "Todas"

            val filtrada = listaOriginal.filter { p ->
                val coincideTexto = p.nombre.lowercase().contains(texto)
                val coincideCategoria = (categoriaSel == "Todas" || p.categoria == categoriaSel)
                coincideTexto && coincideCategoria
            }

            adapter.actualizarLista(filtrada)
        }

        // Buscar al escribir (simple y válido)
        etBuscar.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                aplicarFiltros()
            }
            override fun afterTextChanged(s: android.text.Editable?) {}
        })

        // Filtrar al cambiar categoría
        spCategoria.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: android.widget.AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                aplicarFiltros()
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}
        }

        btnVolver.setOnClickListener { finish() }
    }
}
