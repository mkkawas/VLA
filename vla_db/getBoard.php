<?php
include("db_info.php");

$response = array();

$student_id = $_GET["student_id"];
$board_id = $_GET["board_id"];
$course = $_GET["course"];


$boardQuery = $mysqli->query("SELECT title FROM boards WHERE student_id = '$student_id' AND board_id ='$board_id' AND course = '$course'");

$row = mysqli_fetch_assoc($boardQuery);
$response["title"] = $row["title"];


echo json_encode($response);