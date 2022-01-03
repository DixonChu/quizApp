package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "questionBanks")
data class QuestionBank(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val moduleName: String,
    var questionBankName: String,
    var questionBankDescription: String,
) : Parcelable