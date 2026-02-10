package com.example.myapplication

import Producto

object CarritoManager {

    private val items = mutableListOf<CarritoItem>()

    fun agregarProducto(producto: Producto, cantidad: Int) {
        if (cantidad <= 0) return

        val existente = items.find { it.producto.nombre == producto.nombre }
        if (existente != null) {
            existente.cantidad += cantidad
        } else {
            items.add(CarritoItem(producto, cantidad))
        }
    }

    fun obtenerItems(): List<CarritoItem> = items

    // NUEVO: resta 1 unidad. Si llega a 0, elimina el item.
    fun restarUno(producto: Producto) {
        val existente = items.find { it.producto.nombre == producto.nombre } ?: return
        existente.cantidad -= 1
        if (existente.cantidad <= 0) {
            items.remove(existente)
        }
    }

    fun vaciar() {
        items.clear()
    }
}
