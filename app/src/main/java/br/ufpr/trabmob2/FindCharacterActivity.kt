package br.ufpr.trabmob2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FindCharacterActivity : AppCompatActivity() {
    private lateinit var progressbar: ProgressBar
    private lateinit var cardCharacterDetails: CardView
    private lateinit var ivCharacterImage: ImageView
    private lateinit var tvCharacterName: TextView
    private lateinit var tvCharacterHouse: TextView
    private lateinit var tvCharacterSpecies: TextView
    private lateinit var tvCharacterDob: TextView
    private lateinit var tvCharacterActor: TextView

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_find_character)

        // Initialize views
        progressbar = findViewById(R.id.progressBar)
        cardCharacterDetails = findViewById(R.id.cardCharacterDetails)
        ivCharacterImage = findViewById(R.id.ivCharacterImage)
        tvCharacterName = findViewById(R.id.tvCharacterName)
        tvCharacterHouse = findViewById(R.id.tvCharacterHouse)
        tvCharacterSpecies = findViewById(R.id.tvCharacterSpecies)
        tvCharacterDob = findViewById(R.id.tvCharacterDob)
        tvCharacterActor = findViewById(R.id.tvCharacterActor)
        
        val btnFindCharacterId = findViewById<Button>(R.id.btnSearch)
        val btnIdGoMain = findViewById<Button>(R.id.btnGoMain)
        val idCharacter = findViewById<EditText>(R.id.etCharacterId)

        // Hide the details card initially
        cardCharacterDetails.visibility = View.GONE

        btnFindCharacterId.setOnClickListener {
            val inputId = idCharacter.text.toString()
            if (inputId.isEmpty()) {
                Toast.makeText(this, "Insira o ID do Personagem para buscar!", Toast.LENGTH_SHORT).show()
            } else {
                // Hide the card and show progress
                cardCharacterDetails.visibility = View.GONE
                progressbar.visibility = View.VISIBLE
                
                lifecycleScope.launch {
                    try {
                        val character = withContext(Dispatchers.IO) {
                            ApiClient.characterFactApi.findCharacterById(inputId)
                        }
                        
                        if (character.isNotEmpty()) {
                            val char = character[0]
                            
                            // Populate all fields
                            tvCharacterName.text = char.name.ifEmpty { "Não informado" }
                            tvCharacterHouse.text = char.house.ifEmpty { "Não informado" }
                            tvCharacterSpecies.text = char.species.ifEmpty { "Não informado" }
                            tvCharacterDob.text = char.dateOfBirth.ifEmpty { "Não informado" }
                            tvCharacterActor.text = char.actor.ifEmpty { "Não informado" }
                            
                            // Load image with Glide
                            if (char.image.isNotEmpty()) {
                                Glide.with(this@FindCharacterActivity)
                                    .load(char.image)
                                    .placeholder(R.color.hp_surface_variant)
                                    .error(R.color.hp_surface_variant)
                                    .into(ivCharacterImage)
                            } else {
                                ivCharacterImage.setImageResource(android.R.color.transparent)
                                ivCharacterImage.setBackgroundColor(getColor(R.color.hp_surface_variant))
                            }
                            
                            // Show the details card
                            cardCharacterDetails.visibility = View.VISIBLE
                        } else {
                            Toast.makeText(
                                this@FindCharacterActivity,
                                "Personagem não encontrado",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (ex: Exception) {
                        Log.e("FindCharacterActivity", "Erro ao obter o personagem da api hp.", ex)
                        Toast.makeText(
                            this@FindCharacterActivity,
                            "Erro ao buscar personagem",
                            Toast.LENGTH_SHORT
                        ).show()
                    } finally {
                        progressbar.visibility = View.GONE
                    }
                }
            }
        }

        btnIdGoMain.setOnClickListener {
            finish()
        }
    }
}
