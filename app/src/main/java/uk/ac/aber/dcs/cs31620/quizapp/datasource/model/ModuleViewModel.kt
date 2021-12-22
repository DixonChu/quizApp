package uk.ac.aber.dcs.cs31620.quizapp.datasource.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.quizapp.datasource.Module
import uk.ac.aber.dcs.cs31620.quizapp.datasource.ModuleDatabase
import uk.ac.aber.dcs.cs31620.quizapp.datasource.ModuleRepository

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
}