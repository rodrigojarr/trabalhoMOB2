package br.ufpr.trabmob2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TeachersActivity : AppCompatActivity() {
    private lateinit var progressbar: ProgressBar
    private lateinit var characterFactApi: CharacterFactApi
    private var recyclerViewTeachers: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_teachers)

        val retrofit = Retrofit.Builder().baseUrl("https://hp-api.onrender.com/api/").
        addConverterFactory(GsonConverterFactory.create()).build()

        val btnGoMain = findViewById<Button>(R.id.btnGoMain)
        recyclerViewTeachers = findViewById(R.id.rcvTeachers)
        progressbar = findViewById(R.id.progressBarTeachers)
        characterFactApi = retrofit.create(CharacterFactApi::class.java)
        progressbar.visibility = android.view.View.VISIBLE
        recyclerViewTeachers?.layoutManager = LinearLayoutManager(this@TeachersActivity)
        recyclerViewTeachers?.setHasFixedSize(true)
        recyclerViewTeachers?.addItemDecoration(
            DividerItemDecoration(this@TeachersActivity, DividerItemDecoration.VERTICAL)
        )

        lifecycleScope.launch {
            try {
                val teachers = withContext(Dispatchers.IO){
                    characterFactApi.getTeachers()
                }
                recyclerViewTeachers?.adapter = AdapterTeacher(teachers, this@TeachersActivity){}
            } catch (ex: Exception){
                Log.e("TeachersActivity", "Erro ao obter os professores da api.", ex)
            } finally {
                progressbar.visibility = View.GONE
            }
        }

        btnGoMain.setOnClickListener {
            finish()
        }
    }
}