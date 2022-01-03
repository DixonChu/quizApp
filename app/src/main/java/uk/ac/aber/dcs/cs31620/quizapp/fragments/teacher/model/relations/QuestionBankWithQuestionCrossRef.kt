package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.relations

import androidx.room.Entity

@Entity(primaryKeys = ["questionBankName", "question"])
data class QuestionBankWithQuestionCrossRef(
    val questionBankName: String,
    val question: String,
)