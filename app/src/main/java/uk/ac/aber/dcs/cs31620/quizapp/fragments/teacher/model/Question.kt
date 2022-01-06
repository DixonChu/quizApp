package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "questions")
data class Question (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val moduleName: String,
    val questionBankName: String,
    val question: String,
    val questionAnswer: Int,
    val optionAns1: String,
    val optionAns2: String,
    val optionAns3: String,
    val optionAns4: String,
    val optionAns5: String,
    val optionAns6: String,
    val optionAns7: String,
    val optionAns8: String,
    val optionAns9: String,
    val optionAns10: String,
): Parcelable
