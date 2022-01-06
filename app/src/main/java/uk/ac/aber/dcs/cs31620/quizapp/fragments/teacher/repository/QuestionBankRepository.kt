package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.repository

import androidx.lifecycle.LiveData
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDao
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank

class QuestionBankRepository(private val questionBankDao: QuizDao) {

    val readAllData: LiveData<List<QuestionBank>> = questionBankDao.readAllQuestionBanks()

    fun addQuestionBank(questionBank: QuestionBank){
        questionBankDao.addQuestionBank(questionBank)
    }

    fun updateQuestionBank(questionBank: QuestionBank){
        questionBankDao.updateQuestionBank(questionBank)
    }


    fun deleteQuestionBankAndQuestion(questionBank: QuestionBank, questionBankName: String){
        questionBankDao.deleteQuestionBankAndQuestion(questionBank, questionBankName)
    }

    fun deleteAllQuestionBankByModuleName(moduleName: String){
        questionBankDao.deleteAllQuestionBanksByModuleName(moduleName)
    }


    fun getQuestionBankWithModuleName(moduleName: String): LiveData<List<QuestionBank>>{
        return questionBankDao.readQuestionBankWithModuleName(moduleName)
    }

    fun updateQuestionBankNameWithQuestion(questionBank: QuestionBank, questionBankName:String, currentQuestionBankName: String){
        return questionBankDao.updateQuestionBankNameWithQuestion(questionBank, questionBankName, currentQuestionBankName)
    }
}