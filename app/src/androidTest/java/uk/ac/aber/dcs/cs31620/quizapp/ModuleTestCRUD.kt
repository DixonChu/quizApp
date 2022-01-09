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
class ModuleTestCRUD {
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

    /**
     * Test inserting module
     */
    @Test
    fun onInsertingModule_checkThat_moduleWasInserted() = runBlocking {
        val module = testUtil.createModule(1, "CS31620", "Android Development")

        quizDao.addModule(module[0])

        val foundModule = quizDao.readAllModules()
        assertEquals(1, LiveDataTestUtil.getValue(foundModule).size)
    }

    /**
     * Test remove all Data
     */
    @Test
    fun onDeleteModule_checkThat_allDataWasDeleted() = runBlocking {
        quizDao.deleteAllData()
        val foundModule = quizDao.readAllModules()
        val foundQuestionBank = quizDao.readAllQuestionBanks()
        val foundQuestion = quizDao.readAllQuestions()
        assertEquals(0, LiveDataTestUtil.getValue(foundModule).size)
        assertEquals(0, LiveDataTestUtil.getValue(foundQuestionBank).size)
        assertEquals(0, LiveDataTestUtil.getValue(foundQuestion).size)
    }

}