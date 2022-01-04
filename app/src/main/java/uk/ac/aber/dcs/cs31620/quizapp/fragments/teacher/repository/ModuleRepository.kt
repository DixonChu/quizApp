package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.repository

import androidx.lifecycle.LiveData
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDao
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank

class ModuleRepository(private val moduleDao: QuizDao) {

    val readAllData: LiveData<List<Module>> = moduleDao.readAllModules()

    fun addModule(module: Module){
        moduleDao.addModule(module)
    }

    fun updateModule(module: Module){
        moduleDao.updateModule(module)
    }

    fun deleteModule(module: Module){
        moduleDao.deleteModule(module)
    }

    fun deleteAllModule(){
        moduleDao.deleteAllModules()
    }

}