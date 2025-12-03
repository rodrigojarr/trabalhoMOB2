package br.ufpr.trabmob2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.ufpr.trabmob2.StudentsActivity.Companion.EXTRA_MESSAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsStudentsActivity : AppCompatActivity() {
    private lateinit var progressbar: ProgressBar
    private lateinit var characterFactApi: CharacterFactApi
    private var recyclerViewStudents: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details_students)

        val retrofit = Retrofit.Builder().baseUrl("https://hp-api.onrender.com/api/").
        addConverterFactory(GsonConverterFactory.create()).build()
        val house = intent.getStringExtra(StudentsActivity.EXTRA_MESSAGE).toString()
        val btnGoHouses = findViewById<Button>(R.id.btnGoHouses)
        recyclerViewStudents = findViewById(R.id.rcvStudents)
        progressbar = findViewById(R.id.progressBarListStudents)
        characterFactApi = retrofit.create(CharacterFactApi::class.java)
        progressbar.visibility = android.view.View.VISIBLE
        recyclerViewStudents?.layoutManager = LinearLayoutManager(this@DetailsStudentsActivity)
        recyclerViewStudents?.setHasFixedSize(true)
        recyclerViewStudents?.addItemDecoration(
            DividerItemDecoration(this@DetailsStudentsActivity, DividerItemDecoration.VERTICAL)
        )

        lifecycleScope.launch {
            try {
                val students = withContext(Dispatchers.IO){
                    characterFactApi.getStudentsByHouse(house)
                }
                recyclerViewStudents?.adapter = AdapterCharacter(students, this@DetailsStudentsActivity){}
            } catch (ex: Exception){
                Log.e("DetailsStudentsActivity", "Erro ao obter os estudantes da Escola $house.", ex)
            } finally {
                progressbar.visibility = View.GONE
            }
        }

        btnGoHouses.setOnClickListener {
            finish()
        }
    }
}