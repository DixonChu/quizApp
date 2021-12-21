package uk.ac.aber.dcs.cs31620.quizapp.datasource

import android.os.Build
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questionBanks")
class QuestionBank {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0
    var moduleInfo: String = ""
    var question: String = ""
    var answer: String = ""

    fun isTrue(): Boolean {
        //Check student answer is is correct
        return true
    }
}