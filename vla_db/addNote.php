<?php

include("db_info.php");

$response = array();

$note_id = $_POST["note_id"];
$description = $_POST["description"];
$student_id = $_POST["student_id"];
$board_id = $_POST["board_id"];
$course = $_POST["course"];

$noteQuery = $mysqli->query("INSERT INTO `notes`(`note_id`, `description`, `student_id`, `board_id`, `course`) VALUES ('$note_id','$description','$student_id','$board_id','$course')");
$response["error"] = false;
$response["message"] = "Note Added Successfully";

echo json_encode($response);