package uk.ac.aber.dcs.cs31620.quizapp.repository

import androidx.lifecycle.LiveData
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDao
import uk.ac.aber.dcs.cs31620.quizapp.model.Question

class QuestionRepository(private val questionDao: QuizDao) {

    /**
     * Read all question data
     */
    val readAllData: LiveData<List<Question>> = questionDao.readAllQuestions()

    /**
     * Add question
     *
     * @param question Question Info
     */
    fun addQuestion(question: Question){
        questionDao.addQuestion(question)
    }

    /**
     * Update question
     *
     * @param question Question Info
     */
    fun updateQuestion(question: Question){
        questionDao.updateQuestion(question)
    }

    /**
     * Delete question
     *
     * @param question Question Info
     */
    fun deleteQuestion(question: Question){
        questionDao.deleteQuestion(question)
    }

    /**
     * Delete question by question bank name
     *
     * @param questionBankName Question Bank Name
     */
    fun deleteQuestionByQuestionBankName(questionBankName: String){
        questionDao.deleteQuestionByQuestionBank(questionBankName)
    }

    /**
     * Get question by question bank name
     *
     * @param questionBankName Question Bank Name
     */
    fun getQuestionByQuestionBankName(questionBankName: String): LiveData<List<Question>>{
        return questionDao.getQuestionByQuestionBank(questionBankName)
    }

}