package uk.ac.aber.dcs.cs31620.quizapp.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uk.ac.aber.dcs.cs31620.quizapp.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.model.Question
import uk.ac.aber.dcs.cs31620.quizapp.model.QuestionBank

@Database(entities = [Module::class, QuestionBank::class, Question::class], version = 1, exportSchema = false)

abstract class QuizDatabase: RoomDatabase() {
    abstract fun quizDao(): QuizDao

    companion object{
        @Volatile
        private var INSTANCE: QuizDatabase?=null
        fun getDatabase(context: Context): QuizDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "quiz_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}