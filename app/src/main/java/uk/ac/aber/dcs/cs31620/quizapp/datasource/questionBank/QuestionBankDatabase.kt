package uk.ac.aber.dcs.cs31620.quizapp.datasource.questionBank

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank

@Database(entities = [QuestionBank::class], version = 1, exportSchema = false)
abstract class QuestionBankDatabase : RoomDatabase() {

    abstract fun questionBankDao(): QuestionBankDao

    companion object {
        @Volatile
        private var INSTANCE: QuestionBankDatabase? = null

        fun getDatabase(context: Context): QuestionBankDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionBankDatabase::class.java,
                    "question_bank_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

        val Migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("migrate", "Doing a migrate from version 1 to version 2")
            }
        }
    }

}