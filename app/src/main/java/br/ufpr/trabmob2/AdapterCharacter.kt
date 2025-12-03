package br.ufpr.trabmob2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterCharacter (
    private val characters: List<CharacterFact>,
    private val context: Context,
    private val click: (st: CharacterFact) -> Unit
): RecyclerView.Adapter<AdapterCharacter.CharacterViewHolder>() {

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgStudent = itemView.findViewById<ImageView>(R.id.imgStudent)
        val nameStudent = itemView.findViewById<TextView>(R.id.nameStudent)
        val specieStudent = itemView.findViewById<TextView>(R.id.specieTeacher)
        val houseStudent = itemView.findViewById<TextView>(R.id.houseStudent)
        val birthStudent = itemView.findViewById<TextView>(R.id.birthStudent)
        val actorStudent = itemView.findViewById<TextView>(R.id.actorTeacher)

        fun bind(st: CharacterFact) {
            Glide.with(itemView)
                .load(st.image)
                .into(imgStudent)
            nameStudent.text   = st.name.orEmpty()
            specieStudent.text = "Espécie: ${st.species.orEmpty()}"
            houseStudent.text  = "Casa/Escola: ${st.house.orEmpty()}"
            birthStudent.text  = "Nascimento: ${st.dateOfBirth.orEmpty()}"
            actorStudent.text  = "Ator: ${st.actor.orEmpty()}"
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.list_student, parent, false)
            return CharacterViewHolder(view)
        }

        override fun getItemCount(): Int {
            return characters.size
        }

        override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
            val character = characters[position]
            holder.bind(character)
        }
}