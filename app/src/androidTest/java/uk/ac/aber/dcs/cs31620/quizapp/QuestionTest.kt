package uk.ac.aber.dcs.cs31620.quizapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.aber.dcs.cs31620.quizapp.datasource.QuizDao
import uk.ac.aber.dcs.cs31620.quizapp.datasource.RoomDatabaseI
import uk.ac.aber.dcs.cs31620.quizapp.util.LiveDataTestUtil
import uk.ac.aber.dcs.cs31620.quizapp.util.TestUtil

@RunWith(AndroidJUnit4::class)
class QuestionTest {
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
    fun onInsertingQuestion_checkThat_questionWasInserted() = runBlocking {
        val question = testUtil.createQuestion(1, "CS31620","Question Bank Test 1", "What is this?", 1, "This is a question", "answer2", "answer3", "answer4", "answer5", "answer6", "answer7", "answer8", "answer9", "answer10")

        quizDao.addQuestion(question[0])

        val foundQuestion = quizDao.getQuestionByQuestionBank("Question Bank Test 1")
        Assert.assertEquals(1, LiveDataTestUtil.getValue(foundQuestion).size)
    }

    // Remove
    @Test
    fun onDeleteQuestion_checkThat_questionWasDeleted() = runBlocking {
        quizDao.deleteAllQuestions()
        val foundQuestion = quizDao.readAllQuestions()
        Assert.assertEquals(0, LiveDataTestUtil.getValue(foundQuestion).size)
    }
}