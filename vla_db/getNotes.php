<?php

include("db_info.php");

$response = array();

$student_id = $_GET["student_id"];
$board_id = $_GET["board_id"];
$course = $_GET["course"];
$note = $_GET["note_id"];

$notesQuery = $mysqli->query("SELECT description FROM notes WHERE student_id = '$student_id' AND board_id ='$board_id' AND course = '$course' AND note_id = '$note'");

$row = mysqli_fetch_assoc($notesQuery);
$response["desc"] = $row["description"];
echo json_encode($response);