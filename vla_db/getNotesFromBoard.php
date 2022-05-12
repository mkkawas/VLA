<?php

include("db_info.php");

$response = array();

$student_id = $_GET["student_id"];
$board_id = $_GET["board_id"];
$course = $_GET["course"];

$notesQuery = $mysqli->query("SELECT notes FROM boards WHERE student_id = '$student_id'AND board_id = '$board_id' AND course = '$course'");

$fetchedNotes = mysqli_fetch_assoc($notesQuery);

$notes = $fetchedNotes["notes"];

if($notes == null){
    //return no boards created by user
    $response["error"] = true;
    $response["message"] = "No Boards Created By User!!";
    $response["notes"] = $notes;
    echo json_encode($response);

}else{
    $response["error"] = false;
    $response["message"] = "Success";
    $response["notes"] = $notes;
    echo json_encode($response);
}