package uk.ac.aber.dcs.cs31620.quizapp.util

import uk.ac.aber.dcs.cs31620.quizapp.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.model.Question
import uk.ac.aber.dcs.cs31620.quizapp.model.QuestionBank

class TestUtil {

    fun createModule(num: Int, moduleName: String, moduleDescription: String): MutableList<Module> {
        val modules = mutableListOf<Module>()

        for (i in 0 until num) {
            modules.add(Module(0, moduleName, moduleDescription))
        }
        return modules
    }

    fun createQuestionBank(
        num: Int,
        moduleName: String,
        questionBankName: String,
        questionBankDescription: String
    ): MutableList<QuestionBank> {
        val questionBanks = mutableListOf<QuestionBank>()

        for (i in 0 until num) {
            questionBanks.add(
                QuestionBank(
                    0,
                    moduleName,
                    questionBankName,
                    questionBankDescription
                )
            )
        }
        return questionBanks
    }

    fun createQuestion(
        num: Int,
        moduleName: String,
        questionBankName: String,
        question: String,
        questionAnswer: Int,
        optionAns1: String,
        optionAns2: String,
        optionAns3: String,
        optionAns4: String,
        optionAns5: String,
        optionAns6: String,
        optionAns7: String,
        optionAns8: String,
        optionAns9: String,
        optionAns10: String,
    ): MutableList<Question> {
        val questions = mutableListOf<Question>()

        for (i in 0 until num) {
            questions.add(
                Question(
                    0,
                    moduleName,
                    questionBankName,
                    question,
                    questionAnswer,
                    optionAns1,
                    optionAns2,
                    optionAns3,
                    optionAns4,
                    optionAns5,
                    optionAns6,
                    optionAns7,
                    optionAns8,
                    optionAns9,
                    optionAns10,
                )
            )
        }
        return questions
    }

}
