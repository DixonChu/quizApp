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

@RunWith(AndroidJUnit4::class)
class QuestionBankTestCRUD {
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
    fun onInsertingQuestionBank_checkThat_questionBankWasInserted() = runBlocking {
        val questionBank = testUtil.createQuestionBank(1, "CS31620","Question Bank Test 1", "Question Bank Test 1 Description")

        quizDao.addQuestionBank(questionBank[0])

        val foundModule = quizDao.readAllModules()
        assertEquals(1, LiveDataTestUtil.getValue(foundModule).size)
    }


    /**
     * Test removing all question banks
     */
    @Test
    fun onDeletingQuestionBanks_checkThat_questionBanksWasDeleted() = runBlocking {

        quizDao.deleteAllQuestionBank()
        val foundModule = quizDao.readAllQuestionBanks()
        assertEquals(0, LiveDataTestUtil.getValue(foundModule).size)
    }



}