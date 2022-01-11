package uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDatabase
import uk.ac.aber.dcs.cs31620.quizapp.model.Question
import uk.ac.aber.dcs.cs31620.quizapp.repository.QuestionRepository

class QuestionViewModel(application: Application) : AndroidViewModel(application) {
    var readAllData: LiveData<List<Question>>
    private val repository: QuestionRepository

    init {
        val questionDao = QuizDatabase.getDatabase(application).quizDao()
        repository = QuestionRepository(questionDao)
        readAllData = repository.readAllData
    }

    /**
     * Add question
     *
     * @param question Question
     */
    fun addQuestion(question: Question){
        viewModelScope.launch(Dispatchers.IO){
            repository.addQuestion(question)
        }
    }

    /**
     * Update question
     *
     * @param question Question
     */
    fun updateQuestion(question: Question){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateQuestion(question)
        }
    }

    /**
     * Delete question
     *
     * @param question Question
     */
    fun deleteQuestion(question: Question){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteQuestion(question)
        }
    }

    /**
     * Delete question by question bank name
     *
     * @param questionBankName Question Bank Name
     */
    fun deleteQuestionByQuestionBankName(questionBankName: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteQuestionByQuestionBankName(questionBankName)
        }
    }

    /**
     * Get question by question bank name
     *
     * @param questionBankName Question Bank Name
     */
    fun getQuestionByQuestionBankName(questionBankName: String): LiveData<List<Question>>{
        readAllData = repository.getQuestionByQuestionBankName(questionBankName)
        return readAllData
    }




}