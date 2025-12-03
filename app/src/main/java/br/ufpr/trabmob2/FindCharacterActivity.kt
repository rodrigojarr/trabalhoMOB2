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
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FindCharacterActivity : AppCompatActivity() {
    private lateinit var progressbar: ProgressBar
    private lateinit var characterFactApi: CharacterFactApi
    private lateinit var ids: List<String>

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_find_character)

        progressbar = findViewById(R.id.progressBarId)
        val btnFindCharacterId = findViewById<Button>(R.id.btnFindCharacter)
        val btnContId = findViewById<Button>(R.id.btnContCharacter)
        val btnIdGoMain = findViewById<Button>(R.id.btnIdGoMain)
        val idCharacter = findViewById<EditText>(R.id.tvInputId)
        val imgCharacter = findViewById<ImageView>(R.id.imgCharacterId)
        val nameCharacter = findViewById<TextView>(R.id.nameCharacterId)
        val houseCharacter = findViewById<TextView>(R.id.houseCharacterId)

        val retrofit = Retrofit.Builder().baseUrl("https://hp-api.onrender.com/api/").
        addConverterFactory(GsonConverterFactory.create()).build()
        characterFactApi = retrofit.create(CharacterFactApi::class.java)

        lifecycleScope.launch {
            try {
                val allcharacter = withContext(Dispatchers.IO){
                    characterFactApi.getCharacters()
                }
                ids = allcharacter.map { it.id }
            } catch (ex: Exception){
                Log.e("FindCharacterActivity", "Erro ao obter os personagens da api hp.", ex)
            } finally {
                progressbar.visibility = View.GONE
            }
        }

        btnContId.setOnClickListener{
            val index = (0 until ids.size).random()
            idCharacter.setText(ids.get(index))
        }

        btnFindCharacterId.setOnClickListener{
            val inputId = idCharacter.text.toString()
            if(inputId.length == 0){
                Toast.makeText(this, "Insira o ID do Personagem para buscar!", Toast.LENGTH_SHORT).show()
            } else{
                lifecycleScope.launch {
                    try {
                        val character = withContext(Dispatchers.IO){
                            characterFactApi.findCharacterById(inputId)
                        }
                        Glide.with(this@FindCharacterActivity)
                            .load(character[0].image)
                            .into(imgCharacter)
                        nameCharacter.text = character[0].name
                        houseCharacter.text = character[0].house
                    } catch (ex: Exception){
                        Log.e("FindCharacterActivity", "Erro ao obter o personagen da api hp.", ex)
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