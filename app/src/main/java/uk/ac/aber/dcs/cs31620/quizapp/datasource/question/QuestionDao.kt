package uk.ac.aber.dcs.cs31620.quizapp.datasource.question

import androidx.lifecycle.LiveData
import androidx.room.*
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Question

@Dao
interface QuestionDao {
    @Insert
    fun addQuestion(question: Question)

    @Update
    fun updateQuestion(question: Question)

    @Delete
    fun deleteQuestion(question: Question)

    @Query("DELETE FROM questions")
    fun deleteAllQuestions()

    @Query("DELETE FROM questions WHERE questionBankName =:questionBankName")
    fun deleteQuestionByQuestionBank(questionBankName: String)

    @Query("DELETE FROM questions WHERE moduleName =:moduleName")
    fun deleteQuestionByModule(moduleName: String)

    @Query("SELECT * FROM questions ORDER BY id ASC")
    fun readAllQuestions(): LiveData<List<Question>>

    @Query("SELECT * FROM questions WHERE questionBankName = :questionBankName")
    fun getQuestionByQuestionBank(questionBankName: String): LiveData<List<Question>>

}