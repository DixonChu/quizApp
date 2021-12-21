package uk.ac.aber.dcs.cs31620.quizapp.datasource

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ModuleDao {

    @Insert
    fun addModule(module: Module)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateModule(module: Module)

    @Delete
    fun deleteModule(module: Module)

    @Query("DELETE FROM modules")
    fun deleteAllModules()

    @Query("SELECT * FROM modules")
    fun readAllModules(): LiveData<List<Module>>

}