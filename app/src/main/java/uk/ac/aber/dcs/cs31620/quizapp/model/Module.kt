package uk.ac.aber.dcs.cs31620.quizapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity(tableName = "modules")
class Module {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0
    var name: String = ""
    var description: String = ""
    var dob: LocalDateTime = LocalDateTime.now()
}