package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.quizapp.datasource.module.ModuleDatabase
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.repository.ModuleRepository
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank

class ModuleViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Module>>
    private val repository: ModuleRepository


    init {
        val moduleDao = ModuleDatabase.getDatabase(application).moduleDao()
        repository = ModuleRepository(moduleDao)
        readAllData = repository.readAllData
    }

    fun addModule(module: Module) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addModule(module)
        }
    }

    fun updateModule(module: Module){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateModule(module)
        }
    }

    fun deleteModule(module: Module){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteModule(module)

        }
    }

    fun deleteAllModule(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllModule()
        }
    }
}