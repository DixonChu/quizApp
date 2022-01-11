package uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDatabase
import uk.ac.aber.dcs.cs31620.quizapp.model.QuestionBank
import uk.ac.aber.dcs.cs31620.quizapp.repository.QuestionBankRepository

class QuestionBankViewModel(application: Application) : AndroidViewModel(application) {

    var readAllData: LiveData<List<QuestionBank>>
    private val repository: QuestionBankRepository

    init {
        val questionBankDao = QuizDatabase.getDatabase(application).quizDao()
        repository = QuestionBankRepository(questionBankDao)
        readAllData = repository.readAllData
    }

    /**
     * Add question bank
     *
     * @param questionBank Question Bank
     */
    fun addQuestionBank(questionBank: QuestionBank) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addQuestionBank(questionBank)
        }
    }

    /**
     * Update question bank name with question
     *
     * @param questionBank Question Bank
     * @param questionBankName New Question Bank Name
     * @param currentQuestionBankName Current Question Bank Name
     */
    fun updateQuestionBankNameWithQuestion(questionBank: QuestionBank, questionBankName: String, currentQuestionBankName: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateQuestionBankNameWithQuestion(questionBank, questionBankName, currentQuestionBankName)
        }
    }

    /**
     * Delete question bank and question
     *
     * @param questionBank Question Bank
     * @param questionBankName Question Bank Name
     */
    fun deleteQuestionBankAndQuestion(questionBank: QuestionBank, questionBankName: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteQuestionBankAndQuestion(questionBank, questionBankName)
        }
    }

    /**
     * Delete all question bank and question by module name
     *
     * @param moduleName Module Name
     */
    fun deleteAllQuestionBankAndQuestionByModuleName(moduleName: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllQuestionBankAndQuestionByModuleName(moduleName)
        }
    }

    /**
     * Get question bank with module name
     *
     * @param moduleName Module Name
     */
    fun getQuestionBankWithModuleName(moduleName: String): LiveData<List<QuestionBank>>{
        readAllData = repository.getQuestionBankWithModuleName(moduleName)

        return readAllData
    }

}