package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.relations

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Question

data class QuestionBankWithQuestions(
    @Embedded val questionBank: QuestionBank,
    @Relation(
        parentColumn = "questionBankName",
        entityColumn = "question",
        associateBy = Junction(QuestionBankWithQuestionCrossRef::class)
    )

    val question: List<Question>
)