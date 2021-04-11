package br.com.ricardo.developer.cultuascnpj.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.ricardo.developer.cultuascnpj.R

class ListaCnpjSalvosAdapter(
    private val context: Context,
    private val celulaCnpj: Int,
    private val listCnpj: List<String>,
    private val itemClicado: (item: String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)

        val mView: View = inflater.inflate(celulaCnpj, parent, false)

        return DefaultVH(mView)
    }

    private inner class DefaultVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var topicoCv = itemView.findViewById<CardView>(R.id.cv_cnpj)
        var tv_titulo = itemView.findViewById<TextView>(R.id.tv_titulo_resumido)

        init {
            topicoCv.setOnClickListener {
                val position = adapterPosition
                val item = listCnpj[position]

                itemClicado.invoke(item)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val defaltvH = holder as DefaultVH
        val item = listCnpj[position]

        defaltvH.tv_titulo.text = item

    }

    override fun getItemCount(): Int {
        return listCnpj.size
    }
}