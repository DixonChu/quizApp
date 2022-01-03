package uk.ac.aber.dcs.cs31620.quizapp.datasource.module

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Question
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.relations.QuestionBankWithQuestionCrossRef

@Database(entities = [Module::class], version = 2, exportSchema = false)
abstract class ModuleDatabase : RoomDatabase() {

    abstract fun moduleDao(): ModuleDao

    companion object {
        @Volatile
        private var INSTANCE: ModuleDatabase? = null

        fun getDatabase(context: Context): ModuleDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ModuleDatabase::class.java,
                    "module_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }

        val Migration_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("migrate", "Doing a migrate from version 1 to version 2")
            }
        }

    }
}