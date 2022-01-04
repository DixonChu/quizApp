package uk.ac.aber.dcs.cs31620.quizapp.fragments.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import uk.ac.aber.dcs.cs31620.quizapp.R

class Student : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.studentFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appCompatActivity = AppBarConfiguration(setOf(R.id.chooseQuizModeFragment, R.id.quizView))
        setupActionBarWithNavController(navController, appCompatActivity)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.studentFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }



}