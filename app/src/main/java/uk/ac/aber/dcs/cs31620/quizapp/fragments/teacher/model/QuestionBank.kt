package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "questionBanks")
data class QuestionBank(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int,
    var moduleInfo: String,
    var questions: String,
) : Parcelable