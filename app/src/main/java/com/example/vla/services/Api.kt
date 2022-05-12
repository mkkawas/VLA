package com.example.vla.services

import com.example.vla.data.*

import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("login.php")
    fun logIn(
        @Query("email")email:String,
        @Query("password")password:String
    ):Call<LogInModel>

    @GET("getCourses.php")
    fun getCourses(
        @Query("type")type:String,
        @Query("id")id:String
    ):Call<FetchCourseModel>

    @GET("getTasks.php")
    fun getTasksStudent(
        @Query("id")id:String,
        @Query("course")course:String,
        @Query("type")type:String
    ): Call<GetTasks>

    @GET("getTeacherName.php")
    fun getName(
        @Query("course")course:String,
        @Query("id")id:String
    ):Call<FetchTeacherName>

    @GET("getTask.php")
    fun getTask(
        @Query("id")id:String,
        @Query("course")course:String,
        @Query("teacher_id")teacher_id:String
    ):Call<FetchTask>

    @GET("getBoards.php")
    fun getBoards(
        @Query("student_id")student_id:String,
        @Query("course")course:String
    ):Call<GetBoardsModel>

    @GET("getBoard.php")
    fun getBoard(
        @Query("student_id")student_id:String,
        @Query("board_id")board_id:String,
        @Query("course")course:String
    ): Call<GetBoardModel>

    @FormUrlEncoded
    @POST("addBoards.php")
    fun addBoard(
        @Field("board_id")board_id:String,
        @Field("student_id")student_id:String,
        @Field("title")title:String,
        @Field("course")course:String
    ): Call<AddBoardModel>


    @FormUrlEncoded
    @POST("updateBoards.php")
    fun updateBoards(
        @Field("student_id")student_id:String,
        @Field("course")course:String,
        @Field("oldBoards")oldBoards:String,
        @Field("newBoard")newBoard:String
    ): Call<UpdateBoardsModel>



    @GET("getNotesFromBoard.php")
    fun getNotesFromBoard(
        @Query("student_id")student_id:String,
        @Query("board_id")board_id:String,
        @Query("course")course:String
    ): Call<GetNotesFromBoardModel>


    @GET("getNotes.php")
    fun getNote(
        @Query("student_id")student_id:String,
        @Query("board_id")board_id:String,
        @Query("course")course:String,
        @Query("note_id")note_id:String
    ): Call<GetNotes>

    @FormUrlEncoded
    @POST("addNote.php")
    fun addNote(
        @Field("note_id")note_id:String,
        @Field("description")description:String,
        @Field("student_id")student_id:String,
        @Field("board_id")board_id:String,
        @Field("course")course:String,
    ): Call<AddNoteModel>


    @FormUrlEncoded
    @POST("updateNotes.php")
    fun updateNotes(
        @Field("student_id")student_id:String,
        @Field("course")course:String,
        @Field("board_id")board_id:String,
        @Field("noteids")noteids:String,
        @Field("newnoteid")newnoteid:String
    ): Call<UpdateNotesModel>

    @FormUrlEncoded
    @POST("addSession.php")
    fun addSession(
        @Field("student_id")student_id:String,
        @Field("session_id")session_id:String,
        @Field("day")day:String,
        @Field("start_time")start_time:String,
        @Field("duration")duration:String,
        @Field("course")course:String
    ): Call<AddSessionModel>


    @FormUrlEncoded
    @POST("updateSessions.php")
    fun updateSessions(
        @Field("student_id")student_id:String,
        @Field("oldSessions")oldSessions:String,
        @Field("newSession")newSession:String
    ): Call<UpdateSessionsModel>


    @GET("getSessions.php")
    fun getSessions(
        @Query("student_id")student_id:String
    ):Call<GetSessionsModel>

    @GET("getSession.php")
    fun getSession(
        @Query("student_id")student_id:String,
        @Query("session_id")session_id:String
    ):Call<GetSessionModel>


    @FormUrlEncoded
    @POST("addTask.php")
    fun addTask(
        @Field("course")course:String,
        @Field("description")description:String,
        @Field("teacher")teacher:String,
        @Field("task_id")task_id:String
    ):Call<AddTaskModel>


    @FormUrlEncoded
    @POST("updateTasks.php")
    fun updateTasks(
        @Field("teacher_id")teacher_id:String,
        @Field("oldTasks")oldTasks:String,
        @Field("course")course:String,
        @Field("newTask")newTask:String
    ):Call<UpdateTasksModel>




}