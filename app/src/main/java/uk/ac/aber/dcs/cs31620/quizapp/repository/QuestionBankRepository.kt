package uk.ac.aber.dcs.cs31620.quizapp.repository

import androidx.lifecycle.LiveData
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDao
import uk.ac.aber.dcs.cs31620.quizapp.model.QuestionBank

class QuestionBankRepository(private val questionBankDao: QuizDao) {

    /**
     * Read all question bank data
     */
    val readAllData: LiveData<List<QuestionBank>> = questionBankDao.readAllQuestionBanks()

    /**
     * Add question bank
     *
     * @param questionBank Question Bank Info
     */
    fun addQuestionBank(questionBank: QuestionBank){
        questionBankDao.addQuestionBank(questionBank)
    }

    /**
     * Delete question bank and question
     *
     * @param questionBank Question Bank Info
     * @param questionBankName Question Bank Name
     */
    fun deleteQuestionBankAndQuestion(questionBank: QuestionBank, questionBankName: String){
        questionBankDao.deleteQuestionBankAndQuestion(questionBank, questionBankName)
    }

    /**
     * Get question bank by module name
     *
     * @param moduleName Module Name
     */
    fun getQuestionBankWithModuleName(moduleName: String): LiveData<List<QuestionBank>>{
        return questionBankDao.readQuestionBankWithModuleName(moduleName)
    }

    /**
     * Update question bank name and question
     *
     * @param questionBank Question Bank Info
     * @param questionBankName New Question Bank Name
     * @param currentQuestionBankName Current Question Bank Name
     */
    fun updateQuestionBankNameWithQuestion(questionBank: QuestionBank, questionBankName:String, currentQuestionBankName: String){
        return questionBankDao.updateQuestionBankNameWithQuestion(questionBank, questionBankName, currentQuestionBankName)
    }

    /**
     * Delete all question bank and question by module name
     *
     * @param moduleName Module Name
     */
    fun deleteAllQuestionBankAndQuestionByModuleName(moduleName: String){
        return questionBankDao.deleteAllQuestionBankAndQuestionByModuleName(moduleName)
    }
}