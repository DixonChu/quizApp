package uk.ac.aber.dcs.cs31620.quizapp.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ModuleDao {
    @Insert
    fun insertMultipleModuleName(moduleList: List<Module>)

    @Insert
    fun insertSingleModuleName(module: Module)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(module: Module)

    @Delete
    fun deleteModule(module: Module)

    @Query("DELETE FROM modules")
    fun deleteAll()
}