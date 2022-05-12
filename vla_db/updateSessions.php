<?php
include("db_info.php");

$response = array();

$student_id = $_POST["student_id"];
$oldSessions = $_POST["oldSessions"];
$newSession = $_POST["newSession"];


if($oldSessions == null){
    
    $sessionQuery = $mysqli->query("UPDATE students SET `sessions_id` = '$newSession' where id = '$student_id'");
    $response["message"] = "Sessions successfully updated";
    $response["newSessions"] = $newSession;
    echo json_encode($response);

}else{

    $SessionsIds = $oldSessions.";".$newSession;
    $sessionQuery = $mysqli->query("UPDATE students SET `sessions_id` = '$SessionsIds' where id = '$student_id'");
    $response["message"] = "Sessions successfully updated";
    $response["newSessions"] = $SessionsIds;
    echo json_encode($response);

}