package uk.ac.aber.dcs.cs31620.quizapp.datasource

import androidx.lifecycle.LiveData
import androidx.room.*
import uk.ac.aber.dcs.cs31620.quizapp.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.model.Question
import uk.ac.aber.dcs.cs31620.quizapp.model.QuestionBank

@Dao
interface QuizDao {

    /**
     * Add Module
     *
     * @param module Module Info
     */
    @Insert
    fun addModule(module: Module)

    /**
     * Update Module
     *
     * @param module Module Info
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateModule(module: Module)

    /**
     *Delete Module
     *
     * @param module Module Info
     */
    @Delete
    fun deleteModule(module: Module)

    /**
     * Delete All Modules
     */
    @Query("DELETE FROM modules")
    fun deleteAllModules()

    /**
     * Raed All Modules
     */
    @Query("SELECT * FROM modules")
    fun readAllModules(): LiveData<List<Module>>

    /**
     * Add Question Bank
     *
     * @param questionBank Question Bank Info
     */
    @Insert
    fun addQuestionBank(questionBank: QuestionBank)

    /**
     * Update Question Bank
     *
     * @param questionBank Question Bank Info
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateQuestionBank(questionBank: QuestionBank)

    /**
     * Delete Question Bank
     *
     * @param questionBank Question Bank Info
     */
    @Delete
    fun deleteQuestionBank(questionBank: QuestionBank)

    /**
     * Delete Question Bank By Module Name
     *
     * @param moduleName Module Name
     */
    @Query("DELETE FROM questionBanks WHERE moduleName= :moduleName")
    fun deleteAllQuestionBanksByModuleName(moduleName: String)

    /**
     * Delete ALl Question Bank
     */
    @Query("DELETE FROM questionBanks")
    fun deleteAllQuestionBank()

    /**
     * Read All Question Bank
     */
    @Query("SELECT * FROM questionBanks")
    fun readAllQuestionBanks(): LiveData<List<QuestionBank>>

    /**
     * Read All Question Bank By Module Name
     *
     * @param moduleName Module Name
     */
    @Query("SELECT * FROM questionBanks WHERE moduleName= :moduleName")
    fun readQuestionBankWithModuleName(moduleName: String): LiveData<List<QuestionBank>>

    /**
     * Add Question
     *
     * @param question Question Info
     */
    @Insert
    fun addQuestion(question: Question)

    /**
     * Update Question
     *
     * @param question Question Info
     */
    @Update
    fun updateQuestion(question: Question)

    /**
     * Delete Question
     *
     * @param question Question Info
     */
    @Delete
    fun deleteQuestion(question: Question)

    /**
     * Delete All Question
     */
    @Query("DELETE FROM questions")
    fun deleteAllQuestions()

    /**
     * Delete Question Bank By Question Bank Name
     *
     * @param questionBankName Question Bank Name
     */
    @Query("DELETE FROM questions WHERE questionBankName =:questionBankName")
    fun deleteQuestionByQuestionBank(questionBankName: String)

    /**
     * Delete Question By Module Name
     *
     * @param moduleName Module Name
     */
    @Query("DELETE FROM questions WHERE moduleName =:moduleName")
    fun deleteQuestionByModule(moduleName: String)

    /**
     * Read All Questions
     */
    @Query("SELECT * FROM questions")
    fun readAllQuestions(): LiveData<List<Question>>

    /**
     * Update Module Name in Question Bank Table
     *
     * @param moduleName New Module Name
     * @param currentModuleName Current Module Name
     */
    @Query("UPDATE questionBanks SET moduleName =:moduleName WHERE moduleName =:currentModuleName")
    fun updateModuleNameInQuestionBank(moduleName: String, currentModuleName: String)

    /**
     * Update Module Name in Question Table
     *
     * @param moduleName New Module Name
     * @param currentModuleName Current Module Name
     */
    @Query("UPDATE questions SET moduleName =:moduleName WHERE moduleName =:currentModuleName")
    fun updateModuleNameInQuestion(moduleName: String, currentModuleName: String)

    /**
     * Update Question Bank Name in Questions Table
     *
     * @param questionBankName New Question Bank Name
     * @param currentQuestionBankName Current Question Bank Name
     */
    @Query("UPDATE questions SET questionBankName =:questionBankName WHERE questionBankName =:currentQuestionBankName")
    fun updateQuestionBankNameInQuestion(questionBankName: String, currentQuestionBankName: String)

    /**
     * Read Question By Question Bank Name
     *
     * @param questionBankName Question Bank Name
     */
    @Query("SELECT * FROM questions WHERE questionBankName = :questionBankName")
    fun getQuestionByQuestionBank(questionBankName: String): LiveData<List<Question>>



    //---------------------- Transaction -----------------------

    //---------------------- Update Logic ----------------------

    /**
     * Update Module and module name in other table
     *
     * @param module Module Indo
     * @param moduleName New Module Name
     * @param currentModuleName Current Module Name
     */
    @Transaction
    fun updateAllData(module: Module, moduleName: String, currentModuleName: String) {
        updateModule(module)
        updateModuleNameInQuestionBank(moduleName, currentModuleName)
        updateModuleNameInQuestion(moduleName, currentModuleName)
    }

    /**
     * Update Question Bank and question bank name in other table
     *
     * @param questionBank Question Bank Info
     * @param questionBankName New Question Bank Name
     * @param currentQuestionBankName Current Question Bank Name
     */
    @Transaction
    fun updateQuestionBankNameWithQuestion(
        questionBank: QuestionBank,
        questionBankName: String,
        currentQuestionBankName: String
    ) {
        updateQuestionBank(questionBank)
        updateQuestionBankNameInQuestion(questionBankName, currentQuestionBankName)
    }

    //---------------------- Delete Logic -----------------------

    /**
     * Delete All Data
     */
    @Transaction
    fun deleteAllData() {
        deleteAllModules()
        deleteAllQuestionBank()
        deleteAllQuestions()
    }

    @Transaction
    fun deleteModuleQuestionBankAndQuestion(module: Module){
        deleteModule(module)
        deleteAllQuestionBankAndQuestionByModuleName(module.moduleName)
    }

    /**
     * Delete Question Bank and question in question bank
     *
     * @param questionBank Question Bank Info
     * @param questionBankName Question Bank Name
     */
    @Transaction
    fun deleteQuestionBankAndQuestion(questionBank: QuestionBank, questionBankName: String) {
        deleteQuestionBank(questionBank)
        deleteQuestionByQuestionBank(questionBankName)
    }

    /**
     * Delete all question bank and question by module name
     *
     * @param moduleName Module Name
     */
    @Transaction
    fun deleteAllQuestionBankAndQuestionByModuleName(moduleName: String){
        deleteAllQuestionBanksByModuleName(moduleName)
        deleteQuestionByModule(moduleName)
    }


}