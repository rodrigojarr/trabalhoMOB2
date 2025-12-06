package br.ufpr.trabmob2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class DetailsStudentsActivity : AppCompatActivity() {
    private lateinit var progressbar: ProgressBar
    private lateinit var tvStudentsList: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details_students)

        val house = intent.getStringExtra(StudentsActivity.EXTRA_MESSAGE).toString()
        val btnGoHouses = findViewById<Button>(R.id.btnGoHouses)
        tvStudentsList = findViewById(R.id.tvStudentsList)
        progressbar = findViewById(R.id.progressBarListStudents)
        progressbar.visibility = android.view.View.VISIBLE

        lifecycleScope.launch {
            try {
                val students = withContext(Dispatchers.IO){
                    ApiClient.characterFactApi.getStudentsByHouse(house)
                }
                val studentNames = students.joinToString(separator = "\n") { it.name }
                tvStudentsList.text = studentNames
            } catch (ex: Exception){
                Log.e("DetailsStudentsActivity", "Erro ao obter os estudantes da Escola $house.", ex)
                tvStudentsList.text = "Erro ao carregar estudantes."
            } finally {
                progressbar.visibility = View.GONE
            }
        }

        btnGoHouses.setOnClickListener {
            finish()
        }
    }
}
