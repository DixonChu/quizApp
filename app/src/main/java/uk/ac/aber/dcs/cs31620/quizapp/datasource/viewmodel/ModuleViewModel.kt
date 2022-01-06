package uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDatabase
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.repository.ModuleRepository
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Module

class ModuleViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Module>>
    private val repository: ModuleRepository


    init {
//        val moduleDao = ModuleDatabase.getDatabase(application).moduleDao()
        val quizDao = QuizDatabase.getDatabase(application).quizDao()
        repository = ModuleRepository(quizDao)
        readAllData = repository.readAllData
    }

    fun addModule(module: Module) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addModule(module)
        }
    }


    fun deleteModule(module: Module){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteModule(module)
        }
    }

    fun deleteAllData(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllData()
        }
    }

    fun updateAllData(module: Module, moduleName: String, currentModuleName: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateAllData(module, moduleName, currentModuleName)
        }

    }
}