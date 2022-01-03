package uk.ac.aber.dcs.cs31620.quizapp.datasource.module

import androidx.lifecycle.LiveData
import androidx.room.*
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.list.questionBanks.QuestionBanksFragmentArgs
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Question
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.relations.ModuleWithQuestionBanks
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.relations.QuestionBankWithQuestionCrossRef
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.relations.QuestionBankWithQuestions
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.relations.QuestionWithQuestionBanks

@Dao
interface ModuleDao {

    @Insert
    fun addModule(module: Module)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateModule(module: Module)

    @Delete
    fun deleteModule(module: Module)

    @Query("DELETE FROM modules")
    fun deleteAllModules()

    @Query("SELECT * FROM modules ORDER BY id ASC")
    fun readAllModules(): LiveData<List<Module>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertQuestionBankQuestionCrossRef(crossRef: QuestionBankWithQuestionCrossRef)
//
//    @Transaction
//    @Query("SELECT * FROM questionBanks WHERE questionBankName=:questionBankName")
//    fun getQuestionBankWithQuestions(questionBankName: String): List<QuestionBankWithQuestions>
//
//    @Transaction
//    @Query("SELECT * FROM questions WHERE question=:question")
//    fun getQuestionsWithQuestionBank(question: String): List<QuestionWithQuestionBanks>



}
