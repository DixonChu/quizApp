package uk.ac.aber.dcs.cs31620.quizapp

import android.content.Context
import uk.ac.aber.dcs.cs31620.quizapp.datasource.RoomDatabaseI

object Injection {
    fun getDatabase(context: Context): RoomDatabaseI = QuizAppInMemoryRoomDatabase.getDatabase(context)!!
}