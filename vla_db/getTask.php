<?php

include("db_info.php");
$response = array();


$task_id = $_GET["id"];
$course = $_GET["course"];
$teacher_id = $_GET["teacher_id"];


$taskQuery = $mysqli->query("SELECT description FROM tasks WHERE task_id = '$task_id' AND course = '$course' AND teacher_id = '$teacher_id'");

$fetchedDesc = mysqli_fetch_assoc($taskQuery);

$response["desc"] = $fetchedDesc["description"];

echo json_encode($response);

