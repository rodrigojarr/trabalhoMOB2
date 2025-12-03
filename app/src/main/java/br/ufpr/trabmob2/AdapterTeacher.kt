package br.ufpr.trabmob2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterTeacher(
    private val characters: List<CharacterFact>,
    private val context: Context,
    private val click: (st: CharacterFact) -> Unit
) : RecyclerView.Adapter<AdapterTeacher.TeacherViewHolder>(){

    inner class TeacherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgTeacher = itemView.findViewById<ImageView>(R.id.imgTeacher)
        val nameTeacher= itemView.findViewById<TextView>(R.id.nameTeacher)
        val specieTeacher = itemView.findViewById<TextView>(R.id.specieTeacher)
        val actorTeacher = itemView.findViewById<TextView>(R.id.actorTeacher)

        fun bind(t: CharacterFact) {
            Glide.with(itemView)
                .load(t.image)
                .into(imgTeacher)
            nameTeacher.text = t.name.orEmpty()
            specieTeacher.text = "Espécie: ${t.species.orEmpty()}"
            actorTeacher.text = "Ator: ${t.actor.orEmpty()}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_teacher, parent, false)
        return TeacherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }
}