<?php

include("db_info.php");

$response = array();

$student_id = $_POST["student_id"];
$course = $_POST["course"];
$board_id = $_POST["board_id"];
$noteids = $_POST["noteids"];
$newnoteid = $_POST["newnoteid"];


if($noteids == null){

    $notesQuery = $mysqli->query("UPDATE boards SET notes = '$newnoteid' where student_id = '$student_id' AND course= '$course' AND board_id = '$board_id'");
    $response["message"] = "Notes successfully updated";
    $response["newNotes"] = $newnoteid;
    echo json_encode($response);


}else{

    $newnoteids = $noteids.";".$newnoteid;
    $notesQuery = $mysqli->query("UPDATE boards SET notes = '$newnoteids' where student_id = '$student_id' AND course= '$course' AND board_id = '$board_id'");
    $response["message"] = "Notes successfully updated";
    $response["newNotes"] = $newnoteids;
    echo json_encode($response);




}