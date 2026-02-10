package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class CarritoAdapter(
    private val lista: MutableList<CarritoItem>,
    private val onCambio: () -> Unit
) : RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imgCarrito)
        val nombre: TextView = itemView.findViewById(R.id.txtNombreCarrito)
        val categoria: TextView = itemView.findViewById(R.id.txtCategoriaCarrito)
        val linea: TextView = itemView.findViewById(R.id.txtLineaCarrito)
        val btnEliminar: MaterialButton = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carrito, parent, false)
        return CarritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val item = lista[position]
        val p = item.producto

        holder.img.setImageResource(p.imageResId)
        holder.nombre.text = p.nombre
        holder.categoria.text = p.categoria

        fun precioNum(): Double =
            p.precio.replace("€", "").replace(" ", "").replace(",", ".").toDoubleOrNull() ?: 0.0

        val unit = precioNum()
        val subtotal = unit * item.cantidad

        holder.linea.text =
            "x${item.cantidad} • ${"%.2f".format(unit)} €  →  ${"%.2f".format(subtotal)} €"

        holder.btnEliminar.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos == RecyclerView.NO_POSITION) return@setOnClickListener

            val it = lista[pos]

            //SOLO RESTAR EN EL MANAGER
            CarritoManager.restarUno(it.producto)

            // Ahora 'it.cantidad' ya viene actualizada (porque es el mismo objeto)
            if (it.cantidad <= 0) {
                lista.removeAt(pos)
                notifyItemRemoved(pos)
            } else {
                notifyItemChanged(pos)
            }

            onCambio()
        }
    }

    override fun getItemCount(): Int = lista.size
}
