package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.quizapp.datasource.question.QuestionDatabase
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Question
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.repository.QuestionRepository

class QuestionViewModel(application: Application) : AndroidViewModel(application) {
    var readAllData: LiveData<List<Question>>
    private val repository: QuestionRepository

    init {
        val questionDao = QuestionDatabase.getDatabase(application).questionDao()
        repository = QuestionRepository(questionDao)
        readAllData = repository.readAllData
    }

    fun addQuestion(question: Question){
        viewModelScope.launch(Dispatchers.IO){
            repository.addQuestion(question)
        }
    }

    fun updateQuestion(question: Question){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateQuestion(question)
        }
    }

    fun deleteQuestion(question: Question){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteQuestion(question)
        }
    }

    fun deleteAllQuestion(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllQuestion()
        }
    }

    fun deleteQuestionByQuestionBankName(questionBankName: String){

    }

    fun getQuestionByQuestionBankName(questionBankName: String): LiveData<List<Question>>{
        readAllData = repository.getQuestionByQuestionBankName(questionBankName)
        return readAllData
    }
}