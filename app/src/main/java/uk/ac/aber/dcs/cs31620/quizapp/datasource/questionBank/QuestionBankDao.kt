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

    @Query("DELETE FROM questionBanks")
    fun deleteAllQuestionBanks()

    @Query("SELECT * FROM questionBanks ORDER BY id ASC")
    fun readAllQuestionBanks(): LiveData<List<QuestionBank>>
}