package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.repository

import androidx.lifecycle.LiveData
import uk.ac.aber.dcs.cs31620.quizapp.datasource.module.ModuleDao
import uk.ac.aber.dcs.cs31620.quizapp.datasource.questionBank.QuestionBankDao
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank

class QuestionBankRepository(private val questionBankDao: QuestionBankDao) {

    val readAllData: LiveData<List<QuestionBank>> = questionBankDao.readAllQuestionBanks()

    fun addQuestionBank(questionBank: QuestionBank){
        questionBankDao.addQuestionBank(questionBank)
    }

    fun updateQuestionBank(questionBank: QuestionBank){
        questionBankDao.updateQuestionBank(questionBank)
    }

    fun deleteQuestionBank(questionBank: QuestionBank){
        questionBankDao.deleteQuestionBank(questionBank)
    }

    fun deleteAllQuestionBankByModuleName(moduleName: String){
        questionBankDao.deleteAllQuestionBanksByModuleName(moduleName)
    }

    fun getQuestionBankWithModuleName(moduleName: String): LiveData<List<QuestionBank>>{
        return questionBankDao.readQuestionBankWithModuleName(moduleName)
    }
}