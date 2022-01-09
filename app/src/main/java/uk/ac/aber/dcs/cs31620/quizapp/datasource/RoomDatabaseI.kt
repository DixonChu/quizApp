package uk.ac.aber.dcs.cs31620.quizapp.datasource

interface RoomDatabaseI {
    fun quizDao(): QuizDao
    fun closeDb()
}