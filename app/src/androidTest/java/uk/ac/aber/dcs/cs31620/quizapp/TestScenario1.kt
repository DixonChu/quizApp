package uk.ac.aber.dcs.cs31620.quizapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDao
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDatabase
import uk.ac.aber.dcs.cs31620.quizapp.util.LiveDataTestUtil
import uk.ac.aber.dcs.cs31620.quizapp.util.TestUtil
import java.lang.Exception


/**
 * This test scenario is inserting module, question banks and questions that assigned to the module name.
 * When deleting the module, question bank and questions that assigned to the module are going to be removed.
 */

@RunWith(AndroidJUnit4::class)
class TestScenario1 {
    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var quizDao: QuizDao
    private lateinit var db: QuizDatabase
    private val testUtil = TestUtil()

    @Before
    @Throws(Exception::class)
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = QuizDatabase.getDatabase(context)
        quizDao = db.quizDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun onInsertingModule_checkThat_moduleWasInserted() = runBlocking {
        val module = testUtil.createModule(1, "CS31620", "Android Development")

        quizDao.addModule(module[0])

        val foundModule = quizDao.readAllModules()
        assertEquals(1, LiveDataTestUtil.getValue(foundModule).size)
    }

    @Test
    fun onInsertingQuestionBank_checkThat_questionBankWasInserted() = runBlocking {
        val questionBank = testUtil.createQuestionBank(1, "CS31620","Question Bank Test 1", "Question Bank Test 1 Description")

        quizDao.addQuestionBank(questionBank[0])

        val foundModule = quizDao.readAllModules()
        assertEquals(1, LiveDataTestUtil.getValue(foundModule).size)
    }

    @Test
    fun onInsertingQuestion_checkThat_questionWasInserted() = runBlocking {
        val question = testUtil.createQuestion(1, "CS31620","Question Bank Test 1", "What is the current test called?", 1, "Test Scenario 1", "Test Scenario 2", "Test Scenario 3", "Test Scenario 4", "Test Scenario 5", "Test Scenario 6", "null", "null", "null", "null")

        quizDao.addQuestion(question[0])

        val foundQuestion = quizDao.readAllQuestions()
        assertEquals(1, LiveDataTestUtil.getValue(foundQuestion).size)
    }

    @Test
    fun onRemovingModule_checkThat_questionBankAndQuestionWasRemoved() = runBlocking {
        quizDao.deleteAllData()
        val foundModule = quizDao.readAllModules()
        val foundQuestionBank = quizDao.readAllQuestionBanks()
        val foundQuestion = quizDao.readAllQuestions()
        assertEquals(0, LiveDataTestUtil.getValue(foundModule).size)
        assertEquals(0, LiveDataTestUtil.getValue(foundQuestionBank).size)
        assertEquals(0, LiveDataTestUtil.getValue(foundQuestion).size)
    }


}