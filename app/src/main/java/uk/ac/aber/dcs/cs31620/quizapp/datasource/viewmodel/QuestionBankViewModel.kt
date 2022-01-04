package uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDatabase
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.repository.QuestionBankRepository

class QuestionBankViewModel(application: Application) : AndroidViewModel(application) {

    var readAllData: LiveData<List<QuestionBank>>
    private val repository: QuestionBankRepository

    init {
        val questionBankDao = QuizDatabase.getDatabase(application).quizDao()
        repository = QuestionBankRepository(questionBankDao)
        readAllData = repository.readAllData
    }

    fun addQuestionBank(questionBank: QuestionBank) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addQuestionBank(questionBank)
        }
    }

    fun updateQuestionBank(questionBank: QuestionBank){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateQuestionBank(questionBank)
        }
    }

    fun deleteQuestionBank(questionBank: QuestionBank){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteQuestionBank(questionBank)
        }
    }

    fun deleteAllQuestionBankByModuleName(moduleName: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllQuestionBankByModuleName(moduleName)
        }
    }

    fun deleteAllQuestionBank(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllQuestionBank()
        }
    }

    fun getQuestionBankWithModuleName(moduleName: String): LiveData<List<QuestionBank>>{
        readAllData = repository.getQuestionBankWithModuleName(moduleName)

        return readAllData
    }

}