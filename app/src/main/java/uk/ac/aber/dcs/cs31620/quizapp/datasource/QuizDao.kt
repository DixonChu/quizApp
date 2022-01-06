package uk.ac.aber.dcs.cs31620.quizapp.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.list.questions.QuestionsFragmentDirections
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Question
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank

@Dao
interface QuizDao {

    @Insert
    fun addModule(module: Module)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateModule(module: Module)

    @Delete
    fun deleteModule(module: Module)

    @Query("DELETE FROM modules")
    fun deleteAllModules()

    @Query("SELECT * FROM modules")
    fun readAllModules(): LiveData<List<Module>>

    @Insert
    fun addQuestionBank(questionBank: QuestionBank)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateQuestionBank(questionBank: QuestionBank)

    @Delete
    fun deleteQuestionBank(questionBank: QuestionBank)

    @Query("DELETE FROM questionBanks WHERE moduleName= :moduleName")
    fun deleteAllQuestionBanksByModuleName(moduleName: String)

    @Query("DELETE FROM questionBanks")
    fun deleteAllQuestionBank()

    @Query("SELECT * FROM questionBanks")
    fun readAllQuestionBanks(): LiveData<List<QuestionBank>>

    @Query("SELECT * FROM questionBanks WHERE moduleName= :moduleName")
    fun readQuestionBankWithModuleName(moduleName: String): LiveData<List<QuestionBank>>

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

    @Query("SELECT * FROM questions")
    fun readAllQuestions(): LiveData<List<Question>>


    @Query("UPDATE questionBanks SET moduleName =:moduleName WHERE moduleName =:currentModuleName")
    fun updateModuleNameInQuestionBank(moduleName: String, currentModuleName: String)

    @Query("UPDATE questions SET moduleName =:moduleName WHERE moduleName =:currentModuleName")
    fun updateModuleNameInQuestion(moduleName: String, currentModuleName: String)


    @Query("UPDATE questions SET questionBankName =:questionBankName WHERE questionBankName =:currentQuestionBankName")
    fun updateQuestionBankNameInQuestion(questionBankName: String, currentQuestionBankName: String)


    @Query("SELECT * FROM questions WHERE questionBankName = :questionBankName")
    fun getQuestionByQuestionBank(questionBankName: String): LiveData<List<Question>>





    //---------------------- Transaction ----------------------

    // Update Logic
    @Transaction
    fun updateAllData(module: Module, moduleName: String, currentModuleName: String) {
        updateModule(module)
        updateModuleNameInQuestion(moduleName, currentModuleName)
        updateModuleNameInQuestionBank(moduleName, currentModuleName)
    }

    @Transaction
    fun updateQuestionBankNameWithQuestion(
        questionBank: QuestionBank,
        questionBankName: String,
        currentQuestionBankName: String
    ) {
        updateQuestionBank(questionBank)
        updateQuestionBankNameInQuestion(questionBankName, currentQuestionBankName)
    }

    // Delete Logic
    @Transaction
    fun deleteAllData() {
        deleteAllModules()
        deleteAllQuestionBank()
        deleteAllQuestions()
    }

    @Transaction
    fun deleteQuestionBankAndQuestion(questionBank: QuestionBank, questionBankName: String) {
        deleteQuestionBank(questionBank)
        deleteQuestionByQuestionBank(questionBankName)
    }


}