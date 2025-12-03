package br.ufpr.trabmob2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        val btnListStudents = findViewById<Button>(R.id.btnListStudents)
        val btnListTeachers = findViewById<Button>(R.id.btnListTeacher)
        val btnFindCharacter= findViewById<Button>(R.id.btnListCharacterId)
        val btnClose = findViewById<Button>(R.id.btnClose)

        btnListStudents.setOnClickListener {
            val intent = Intent(this, StudentsActivity::class.java)
            startActivity(intent)
        }

        btnListTeachers.setOnClickListener {
            val intent = Intent(this, TeachersActivity::class.java)
            startActivity(intent)
        }

        btnFindCharacter.setOnClickListener {
            val intent = Intent(this, FindCharacterActivity::class.java)
            startActivity(intent)
        }

        btnClose.setOnClickListener {
            finishAffinity()
        }
    }
}