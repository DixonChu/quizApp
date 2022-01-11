package uk.ac.aber.dcs.cs31620.quizapp.repository

import androidx.lifecycle.LiveData
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDao
import uk.ac.aber.dcs.cs31620.quizapp.model.Module

class ModuleRepository(private val moduleDao: QuizDao) {

    /**
     * Read all module data
     */
    val readAllData: LiveData<List<Module>> = moduleDao.readAllModules()

    /**
     * Add module data
     *
     * @param module Module Info
     */
    fun addModule(module: Module){
        moduleDao.addModule(module)
    }

    /**
     * Delete module data
     *
     * @param module Module Info
     */
    fun deleteModule(module: Module){
        moduleDao.deleteModule(module)
    }

    /**
     * Update module data, module name in question bank and question
     *
     * @param module Module Info
     * @param moduleName New Module Name
     * @param currentModuleName Current Module Name
     */
    fun updateAllData(module: Module, moduleName: String, currentModuleName: String){
        moduleDao.updateAllData(module, moduleName, currentModuleName)
    }

    /**
     * Delete all data
     */
    fun deleteAllData(){
        moduleDao.deleteAllData()
    }

    fun deleteModuleQuestionBankAndQuestion(module: Module){
        moduleDao.deleteModuleQuestionBankAndQuestion(module)
    }

}