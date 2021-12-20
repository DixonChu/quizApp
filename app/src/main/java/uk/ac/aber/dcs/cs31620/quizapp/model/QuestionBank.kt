package uk.ac.aber.dcs.cs31620.quizapp.model

import android.os.Build
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "questionBanks")
class QuestionBank {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0
    var moduleInfo: String = ""
    var question: String = ""
    var answer: String = ""
    var dob: LocalDateTime = LocalDateTime.now()
    @ColumnInfo(name = "admission_date")
    var admissionDate: LocalDateTime = LocalDateTime.now()

    fun isTrue(): Boolean {
        //Check student asnswer is is correct
        return true
    }
}