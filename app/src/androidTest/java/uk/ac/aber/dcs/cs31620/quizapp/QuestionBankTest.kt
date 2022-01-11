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
import uk.ac.aber.dcs.cs31620.quizapp.datasource.RoomDatabaseI
import uk.ac.aber.dcs.cs31620.quizapp.util.LiveDataTestUtil
import uk.ac.aber.dcs.cs31620.quizapp.util.TestUtil
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class QuestionBankTest {
    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var quizDao: QuizDao
    private lateinit var db: RoomDatabaseI
    private val testUtil = TestUtil()

    @Before
    @Throws(Exception::class)
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Injection.getDatabase(context)
        quizDao = db.quizDao()
    }

    @After
    fun closeDb() {
        db.closeDb()
    }

    // Create and Read
    @Test
    fun onInsertingQuestionBank_checkThat_questionBankWasInserted() = runBlocking {
        val questionBank = testUtil.createQuestionBank(1, "CS31620","Question Bank Test 1", "Question Bank Test 1 Description")

        quizDao.addQuestionBank(questionBank[0])

        val foundModule = quizDao.readQuestionBankWithModuleName("CS31620")
        assertEquals(1, LiveDataTestUtil.getValue(foundModule).size)
    }

    //Update
    @Test
    fun onUpdateQuestionBank_checkThat_questionBankWasUpdated(){
        val questionBank = testUtil.createQuestionBank(1, "CS31620","Question Bank Test 1", "Question Bank Test 1 Description")
        val question = testUtil.createQuestion(1, "CS31620","Question Bank Test 1", "What is this?", 1, "This is a question", "answer2", "answer3", "answer4", "answer5", "answer6", "answer7", "answer8", "answer9", "answer10")

        quizDao.addQuestionBank(questionBank[0])
        quizDao.addQuestion(question[0])

        quizDao.updateQuestionBankNameWithQuestion(questionBank[0], "Question Bank Update Test 1", "Question Bank Test 1")

        val foundQuestion = quizDao.getQuestionByQuestionBank("Question Bank Update Test 1")
        assertEquals(1, LiveDataTestUtil.getValue(foundQuestion).size)
    }


    // Remove
    @Test
    fun onDeletingQuestionBanks_checkThat_questionBanksWasDeleted() = runBlocking {
        val module = testUtil.createModule(1, "CS31620", "Android Development")
        val questionBank = testUtil.createQuestionBank(1, "CS31620","Question Bank Test 1", "Question Bank Test 1 Description")
        val question = testUtil.createQuestion(1, "CS31620","Question Bank Test 1", "What is this?", 1, "This is a question", "answer2", "answer3", "answer4", "answer5", "answer6", "answer7", "answer8", "answer9", "answer10")

        quizDao.addModule(module[0])
        quizDao.addQuestionBank(questionBank[0])
        quizDao.addQuestion(question[0])

        quizDao.deleteAllQuestionBankAndQuestionByModuleName("CS31620")
        val foundQuestionBank = quizDao.readAllQuestionBanks()
        val foundQuestion = quizDao.getQuestionByQuestionBank("Question Bank Test 1")

        assertEquals(0, LiveDataTestUtil.getValue(foundQuestionBank).size)
        assertEquals(0, LiveDataTestUtil.getValue(foundQuestion).size)
    }



}