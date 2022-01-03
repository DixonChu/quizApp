package uk.ac.aber.dcs.cs31620.quizapp.datasource.questionBank

import androidx.lifecycle.LiveData
import androidx.room.*
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank

@Dao
interface QuestionBankDao {
    @Insert
    fun addQuestionBank(questionBank: QuestionBank)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateQuestionBank(questionBank: QuestionBank)

    @Delete
    fun deleteQuestionBank(questionBank: QuestionBank)

    @Query("DELETE FROM questionBanks WHERE moduleName= :moduleName")
    fun deleteAllQuestionBanksByModuleName(moduleName: String)

    @Query("SELECT * FROM questionBanks")
    fun readAllQuestionBanks(): LiveData<List<QuestionBank>>

    @Query("SELECT * FROM questionBanks WHERE moduleName= :moduleName")
    fun readQuestionBankWithModuleName(moduleName: String): LiveData<List<QuestionBank>>
}