package com.example.todo.features.ui.details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.core.domain.Contact
import com.example.todo.core.domain.Product
import com.example.todo.features.R

enum class ViewType(val type: Int) {
    TYPE_CONTACT(0),
    TYPE_PRODUCT(1)
}

class DetailsAdapter(private var contactList: List<Contact>,
                     private var productList: List<Product>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Contact) {
            val tvName: TextView = itemView.findViewById(R.id.tvName)
            val tvNumber: TextView = itemView.findViewById(R.id.tvNumber)

            tvName.text = "Name: ".plus(item.name)
            tvNumber.text = "Number: ".plus(item.number)

        }

    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Product) {
            val tvName: TextView = itemView.findViewById(R.id.tvName)
            val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
            val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantity)

            tvName.text = "Name: ".plus(item.name)
            tvPrice.text = "Price: ".plus(item.price)
            tvQuantity.text = "Quantity: ".plus(item.quantity)
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return when (viewType) {
            ViewType.TYPE_CONTACT.type -> {
                val view = inflater.inflate(R.layout.contact_row_item, viewGroup, false)
                ContactViewHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.product_row_item, viewGroup, false)
                ProductViewHolder(view)
            }
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder.apply {
            when (viewHolder) {
                is ContactViewHolder -> viewHolder.bind(contactList[position])
                is ProductViewHolder -> viewHolder.bind(productList[position])
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = contactList.count() + productList.count()

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            in 0 until contactList.count() -> ViewType.TYPE_CONTACT.type
            else -> ViewType.TYPE_PRODUCT.type
        }
    }
}
