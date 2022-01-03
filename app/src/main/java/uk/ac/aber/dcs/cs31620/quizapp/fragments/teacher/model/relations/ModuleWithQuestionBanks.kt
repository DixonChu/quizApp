package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.relations

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank

data class ModuleWithQuestionBanks(
    @Embedded val module: Module,
    @Relation(
        parentColumn = "moduleName",
        entityColumn = "moduleName"
    )
    val questionBanks: List<QuestionBank>
)
