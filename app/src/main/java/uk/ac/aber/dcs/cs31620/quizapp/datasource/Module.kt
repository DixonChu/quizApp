package uk.ac.aber.dcs.cs31620.quizapp.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "modules")
data class Module (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val moduleName: String,
    val moduleDescription: String,
    val questionBank: String
)