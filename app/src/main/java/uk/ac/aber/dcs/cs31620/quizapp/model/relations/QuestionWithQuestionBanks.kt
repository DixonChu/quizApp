package uk.ac.aber.dcs.cs31620.quizapp.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import uk.ac.aber.dcs.cs31620.quizapp.model.Question
import uk.ac.aber.dcs.cs31620.quizapp.model.QuestionBank

data class QuestionWithQuestionBanks(
    @Embedded val question: Question,
    @Relation(
        parentColumn = "question",
        entityColumn = "questionBankName",
        associateBy = Junction(QuestionBankWithQuestionCrossRef::class)
    )
    val questionBank: List<QuestionBank>
)