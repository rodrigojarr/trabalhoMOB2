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
class TeachersActivity : AppCompatActivity() {
    private lateinit var progressbar: ProgressBar
    private lateinit var tvTeachersList: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_teachers)

        val btnGoMain = findViewById<Button>(R.id.btnGoMain)
        tvTeachersList = findViewById(R.id.tvTeachersList)
        progressbar = findViewById(R.id.progressBarListTeachers)
        progressbar.visibility = android.view.View.VISIBLE

        lifecycleScope.launch {
            try {
                val teachers = withContext(Dispatchers.IO){
                    ApiClient.characterFactApi.getTeachers()
                }
                val teacherNames = teachers.joinToString(separator = "\n") { it.name }
                tvTeachersList.text = teacherNames
            } catch (ex: Exception){
                Log.e("TeachersActivity", "Erro ao obter os professores da api.", ex)
                tvTeachersList.text = "Erro ao carregar professores."
            } finally {
                progressbar.visibility = View.GONE
            }
        }

        btnGoMain.setOnClickListener {
            finish()
        }
    }
}
