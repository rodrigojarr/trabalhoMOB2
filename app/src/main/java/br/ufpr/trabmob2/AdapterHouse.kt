package br.ufpr.trabmob2

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterHouse (
    private val houseCharacter: List<String>,
    private val context: Context,
    private val click: (house: String) -> Unit
): RecyclerView.Adapter<AdapterHouse.HouseViewHolder>() {
    private var selectedPosition = -1

    inner class HouseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rdbHouse = itemView.findViewById<RadioButton>(R.id.rdbHouse)
        val nameHouse = itemView.findViewById<TextView>(R.id.tvNameHouse)

        @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
        fun bind(house: String, position: Int) {

            if(house != "")
                nameHouse.text = house.orEmpty()

            rdbHouse.isChecked = (position == selectedPosition)
            //clique no radiobutton
            rdbHouse.setOnClickListener {
                selectedPosition = bindingAdapterPosition
                notifyDataSetChanged()
                click(house)
            }
            //clique no item
            itemView.setOnClickListener {
                selectedPosition = bindingAdapterPosition
                notifyDataSetChanged()
                click(house)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_house, parent, false)
        return HouseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return houseCharacter.size
    }

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        val house = houseCharacter[position]
        holder.bind(house, position)
    }
}