package uk.ac.aber.dcs.cs31620.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        studentButton();
        teacherButton();




//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_teacher) as NavHostFragment
//        val navController = navHostFragment.navController
//        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_teacher))
//        setupActionBarWithNavController(navController,appBarConfiguration)
    }

    private fun studentButton() {
        val studentButton = findViewById<Button>(R.id.studentButton)
        studentButton.setOnClickListener {
            val intent = Intent(this, student::class.java)
            startActivity(intent)
        }
    }

    private fun teacherButton() {
        val teacherButton = findViewById<Button>(R.id.teacherButton)
        teacherButton.setOnClickListener {
            val intent = Intent(this, teacher::class.java)
            startActivity(intent)
        }
    }


}

