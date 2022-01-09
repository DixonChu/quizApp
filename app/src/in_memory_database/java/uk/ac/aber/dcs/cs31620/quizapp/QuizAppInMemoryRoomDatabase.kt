package uk.ac.aber.dcs.cs31620.quizapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDao
import uk.ac.aber.dcs.cs31620.quizapp.datasource.RoomDatabaseI
import uk.ac.aber.dcs.cs31620.quizapp.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.model.Question
import uk.ac.aber.dcs.cs31620.quizapp.model.QuestionBank

@Database(entities = [Module::class, QuestionBank::class, Question::class], version = 1)
abstract class QuizAppInMemoryRoomDatabase : RoomDatabase(), RoomDatabaseI {

    abstract override fun quizDao(): QuizDao

    override fun closeDb() {
        instance?.close()
        instance = null
    }

    companion object {
        private var instance: QuizAppInMemoryRoomDatabase? = null

        fun getDatabase(context: Context): QuizAppInMemoryRoomDatabase? {
            synchronized(this) {
                if (instance == null) {
                    instance =
                        Room.inMemoryDatabaseBuilder(
                            context.applicationContext,
                            QuizAppInMemoryRoomDatabase::class.java
                        ).allowMainThreadQueries() // For testing
                            .build()
                }
                return instance!!
            }
        }

    }

}