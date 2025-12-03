package br.ufpr.trabmob2

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

class StudentsActivity : AppCompatActivity() {
    private lateinit var progressbar: ProgressBar
    private lateinit var characterFactApi: CharacterFactApi
    private var recyclerViewHouses: RecyclerView? = null
    var selectedHouse: String? = null

    companion object {
        val EXTRA_MESSAGE = "message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students)

        val retrofit = Retrofit.Builder().baseUrl("https://hp-api.onrender.com/api/").
        addConverterFactory(GsonConverterFactory.create()).build()
        val btnGoStudents = findViewById<Button>(R.id.btnGoStudents)
        val btnGoMain = findViewById<Button>(R.id.btnGoMain)
        recyclerViewHouses = findViewById(R.id.rcvHouses)
        progressbar = findViewById(R.id.progressBar)
        characterFactApi = retrofit.create(CharacterFactApi::class.java)
        progressbar.visibility = android.view.View.VISIBLE
        recyclerViewHouses?.layoutManager = LinearLayoutManager(this@StudentsActivity)
        recyclerViewHouses?.setHasFixedSize(true)
        recyclerViewHouses?.addItemDecoration(
            DividerItemDecoration(this@StudentsActivity, DividerItemDecoration.VERTICAL)
        )

        lifecycleScope.launch {
            try {
                val allCharacters = withContext(Dispatchers.IO){
                    characterFactApi.getCharacters()
                }
                val houses = allCharacters.map { it.house }
                    .filter { it.isNotBlank() }
                    .distinct()
                recyclerViewHouses?.adapter = AdapterHouse(houses, this@StudentsActivity){ house ->
                    selectedHouse = house
                }
            } catch (ex: Exception){
                Log.e("StudentsActivity", "Erro ao obter os personagens da api hp.", ex)
            } finally {
                progressbar.visibility = View.GONE
            }
        }

        btnGoStudents.setOnClickListener {
            val house = selectedHouse

            if (house != null) {
                val intent = Intent(this, DetailsStudentsActivity::class.java)
                intent.putExtra(EXTRA_MESSAGE, house.lowercase())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Selecione uma casa.", Toast.LENGTH_SHORT).show()
            }
        }

        btnGoMain.setOnClickListener {
            finish()
        }
    }

}