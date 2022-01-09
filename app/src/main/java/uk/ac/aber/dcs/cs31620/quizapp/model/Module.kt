package uk.ac.aber.dcs.cs31620.quizapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "modules")
data class Module (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val moduleName: String,
    val moduleDescription: String,
): Parcelable