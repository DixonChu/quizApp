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

    @Query("SELECT * FROM questions ORDER BY id ASC")
    fun readAllQuestions(): LiveData<List<Question>>

}