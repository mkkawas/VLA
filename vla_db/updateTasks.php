<?php
include("db_info.php");

$response = array();

$teacher_id = $_POST["teacher_id"];
$oldTasks = $_POST["oldTasks"];
$course = $_POST["course"];
$newTask = $_POST["newTask"];


if($oldTasks == null){
    
    $taskQuery = $mysqli->query("UPDATE courses SET `tasks_id` = '$newTask' where teacher_id = '$teacher_id' AND course = '$course'");
    $response["message"] = "Tasks successfully updated";
    $response["newTasks"] = $newTask;
    echo json_encode($response);

}else{

    $TasksIds = $oldTasks.";".$newTask;
    $taskQuery = $mysqli->query("UPDATE courses SET `tasks_id` = '$TasksIds' where teacher_id = '$teacher_id' AND course = '$course'");
    $response["message"] = "Tasks successfully updated";
    $response["newTasks"] = $TasksIds;
    echo json_encode($response);

}