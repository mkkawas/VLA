package com.example.vla.data

import java.util.*

object DataStorage {
    var id: String = ""
    var UserType: String = ""
    var name: String = ""
    var teacherId:String = ""
    var courseSelected: String = ""
    var boardSelected:String = ""
    var oldBoards:String = ""
    var LastBoardId:Int = 999
    var LastNoteID:Int = 999
    var oldNotes:String = ""
    var LastSessionId: Int = 999
    var oldSessions: String = ""
    var lastTaskId: Int = 999
    var oldTasks:String = ""

    fun reset(){
        id = ""
        UserType = ""
        name = ""
        teacherId = ""
        courseSelected = ""
        boardSelected = ""
        oldBoards = ""
        oldTasks =""
        oldSessions = ""
        oldNotes = ""
        lastTaskId = 999
        LastNoteID = 999
        LastSessionId = 999
        LastBoardId = 999

    }

}