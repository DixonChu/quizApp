package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.quizapp.datasource.questionBank.QuestionBankDatabase
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.repository.QuestionBankRepository

class QuestionBankViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<QuestionBank>>
    private val repository: QuestionBankRepository

    init {
        val questionBankDao = QuestionBankDatabase.getDatabase(application).questionBankDao()
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

    fun deleteAllQuestionBank(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllQuestionBank()
        }
    }
}