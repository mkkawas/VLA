<?php
include("db_info.php");
$response = array();

$student_id = $_GET["student_id"];

$sessionsQuery = $mysqli->query("SELECT sessions_id FROM students WHERE id = '$student_id'");

$fetchedSessions = mysqli_fetch_assoc($sessionsQuery);
$sessions = $fetchedSessions["sessions_id"];

if($sessions == null){
    //return no boards created by user
    $response["error"] = true;
    $response["sessions"] = "";
    $response["message"] = "No Study Sessions Created Yet!!";
    echo json_encode($response);

}else{
    
    $response["error"] = false;

    $response["sessions"] = $sessions;
    $response["message"] = "Sessions Successfully Fetched";
    echo json_encode($response);
}