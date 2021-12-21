package uk.ac.aber.dcs.cs31620.quizapp.datasource

import androidx.lifecycle.LiveData

class ModuleRepository(private val moduleDao: ModuleDao) {

    val readAllData: LiveData<List<Module>> = moduleDao.readAllModules()

    fun addModule(module: Module){
        moduleDao.addModule(module)
    }

}