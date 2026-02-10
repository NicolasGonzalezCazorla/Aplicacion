package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import Producto

class ProductoAdapter(
    private var listaProductos: List<Producto>
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
        val txtNombreProducto: TextView = itemView.findViewById(R.id.txtNombreProducto)
        val txtPrecioProducto: TextView = itemView.findViewById(R.id.txtPrecioProducto)
        val txtCategoriaProducto: TextView = itemView.findViewById(R.id.txtCategoriaProducto)
    }

    fun actualizarLista(nuevaLista: List<Producto>) {
        listaProductos = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = listaProductos[position]

        holder.txtNombreProducto.text = producto.nombre
        holder.txtPrecioProducto.text = producto.precio
        holder.txtCategoriaProducto.text = producto.categoria
        holder.imgProducto.setImageResource(producto.imageResId)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetalleProductoActivity::class.java)
            intent.putExtra("nombre", producto.nombre)
            intent.putExtra("precio", producto.precio)
            intent.putExtra("categoria", producto.categoria)
            intent.putExtra("imageResId", producto.imageResId)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listaProductos.size
}
