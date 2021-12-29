package uk.ac.aber.dcs.cs31620.quizapp.datasource.module

import androidx.lifecycle.LiveData
import androidx.room.*
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Module

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

    @Query("SELECT * FROM modules ORDER BY id ASC")
    fun readAllModules(): LiveData<List<Module>>

}