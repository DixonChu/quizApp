package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDao
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Question

class QuestionRepository(private val questionDao: QuizDao) {
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

    fun deleteQuestionByQuestionBankName(questionBankName: String){
        questionDao.deleteQuestionByQuestionBank(questionBankName)
    }

    fun deleteQuestionByModuleName(moduleName: String){
        questionDao.deleteQuestionByModule(moduleName)
    }

    fun getQuestionByQuestionBankName(questionBankName: String): LiveData<List<Question>>{
        return questionDao.getQuestionByQuestionBank(questionBankName)
    }

    fun updateModuleNameInQuestion(moduleName: String, currentModuleName:String){
        return questionDao.updateModuleNameInQuestion(moduleName, currentModuleName)
    }

    fun updateQuestionBankNameInQuestion(questionBankName: String, currentQuestionBankName: String){
        return questionDao.updateQuestionBankNameInQuestion(questionBankName, currentQuestionBankName)
    }

//    suspend fun queryAllQuestionName(): Array<String> {
//        return questionDao.queryAllQuestionName()
//    }


}