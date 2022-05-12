<?php

include("db_info.php");
$response = array();

$student_id = $_GET["student_id"];
$session_id = $_GET["session_id"];

$sessionQuery = $mysqli->query("SELECT `day`, `course`, `start_time`, `duration` FROM study_sessions WHERE student_id = '$student_id' AND `session_id`= '$session_id'");


$row = mysqli_fetch_assoc($sessionQuery);

$response["day"] = $row["day"];
$response["course"] = $row["course"];
$response["start_time"] = $row["start_time"];
$response["duration"] = $row["duration"];

echo json_encode($response);