<?php
include("db_info.php");
$response = array();

$course = $_POST["course"];
$desc = $_POST["description"];
$teacher = $_POST["teacher"];
$task_id = $_POST["task_id"];

$addQuery = $mysqli->query("INSERT INTO `tasks`(`course`, `description`, `teacher_id`, `task_id`) VALUES ('$course','$desc','$teacher','$task_id') ");

$response["error"] = false;
$response["message"] = "Task Added Successfully";

echo json_encode($response);

