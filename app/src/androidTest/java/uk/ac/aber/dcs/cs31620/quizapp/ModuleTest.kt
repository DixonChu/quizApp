package uk.ac.aber.dcs.cs31620.quizapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDao
import uk.ac.aber.dcs.cs31620.quizapp.datasource.RoomDatabaseI
import uk.ac.aber.dcs.cs31620.quizapp.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.util.LiveDataTestUtil
import uk.ac.aber.dcs.cs31620.quizapp.util.TestUtil

@RunWith(AndroidJUnit4::class)
class ModuleTest {
    @JvmField @Rule
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
    fun onInsertingAModule_checkThat_moduleWasInserted() = runBlocking {
        val module = testUtil.createModule(1, "CS31620", "Android Development")

        quizDao.addModule(module[0])

        val foundModule = quizDao.readAllModules()
        assertEquals(1, LiveDataTestUtil.getValue(foundModule).size)
    }

    // Update
    @Test
    fun onUpdateModule_checkThat_allModuleNameWasUpdated(){
        quizDao.deleteAllData()
        val module = testUtil.createModule(1, "CS31620", "Android Development")
        val questionBank = testUtil.createQuestionBank(1, "CS31620","Question Bank Test 1", "Question Bank Test 1 Description")

        quizDao.addModule(module[0])
        quizDao.addQuestionBank(questionBank[0])

        val updateModule = Module(0, "CS37420", "E-Commerce")
        quizDao.updateAllData(updateModule, "CS37420", "CS31620")

        val foundQuestionBank = quizDao.readQuestionBankWithModuleName("CS37420")
        val foundModule = quizDao.readAllModules()
        assertEquals(1, LiveDataTestUtil.getValue(foundQuestionBank).size)
        assertEquals(1, LiveDataTestUtil.getValue(foundModule).size)
    }

    // Remove
    @Test
    fun onDeleteAllModule_checkThat_allDataWasDeleted() = runBlocking {
        quizDao.deleteAllData()
        val foundModule = quizDao.readAllModules()
        val foundQuestionBank = quizDao.readAllQuestionBanks()
        val foundQuestion = quizDao.readAllQuestions()
        assertEquals(0, LiveDataTestUtil.getValue(foundModule).size)
        assertEquals(0, LiveDataTestUtil.getValue(foundQuestionBank).size)
        assertEquals(0, LiveDataTestUtil.getValue(foundQuestion).size)
    }



}