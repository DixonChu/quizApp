package uk.ac.aber.dcs.cs31620.quizapp.model.relations

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import uk.ac.aber.dcs.cs31620.quizapp.model.QuestionBank
import uk.ac.aber.dcs.cs31620.quizapp.model.Question

data class QuestionBankWithQuestions(
    @Embedded val questionBank: QuestionBank,
    @Relation(
        parentColumn = "questionBankName",
        entityColumn = "question",
        associateBy = Junction(QuestionBankWithQuestionCrossRef::class)
    )

    val question: List<Question>
)