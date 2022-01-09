package uk.ac.aber.dcs.cs31620.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import uk.ac.aber.dcs.cs31620.quizapp.ui.student.Student
import uk.ac.aber.dcs.cs31620.quizapp.ui.teacher.Teacher

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentButton()
        teacherButton()

    }

    // Student Page
    private fun studentButton() {
        val studentButton = findViewById<Button>(R.id.studentButton)
        studentButton.setOnClickListener {
            val intent = Intent(this, Student::class.java)
            startActivity(intent)
        }
    }

    // Teacher page
    private fun teacherButton() {
        val teacherButton = findViewById<Button>(R.id.teacherButton)
        teacherButton.setOnClickListener {
            val intent = Intent(this, Teacher::class.java)
            startActivity(intent)
        }
    }


}

