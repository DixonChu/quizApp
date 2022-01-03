package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.repository

import androidx.lifecycle.LiveData
import uk.ac.aber.dcs.cs31620.quizapp.datasource.question.QuestionDao
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Question

class QuestionRepository(private val questionDao: QuestionDao) {
    val readAllData: LiveData<List<Question>> = questionDao.readAllQuestions()

    fun addQuestion(question: Question){
        questionDao.addQuestion(question)
    }

    fun updateQuestion(question: Question){
        questionDao.updateQuestion(question)
    }

    fun deleteQuestion(question: Question){
        questionDao.deleteQuestion(question)
    }

    fun deleteAllQuestion(){
        questionDao.deleteAllQuestions()
    }

    fun getQuestionByQuestionBankName(questionBankName: String): LiveData<List<Question>>{
        return questionDao.getQuestionByQuestionBank(questionBankName)
    }
}