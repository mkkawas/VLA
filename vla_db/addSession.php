<?php

include("db_info.php");

$response = array();

$student_id = $_POST["student_id"];
$session_id = $_POST["session_id"];
$day        = $_POST["day"];
$start_time = $_POST["start_time"];
$duration   = $_POST["duration"];
$course     = $_POST["course"];


$checkQuery = $mysqli->query("SELECT * FROM study_sessions 
                        WHERE student_id = '$student_id' AND `day` = '$day' AND start_time = '$start_time' AND course = '$course'");

if($checkQuery->num_rows>0){

    $response["error"] = true;
    $response["message"]  = "Session already exists";
    echo json_encode($response);

}else{
    
    $courseQuery = $mysqli->query("SELECT course FROM study_sessions 
    WHERE student_id = '$student_id' AND `day` = '$day' AND course = '$course'");

    if($courseQuery->num_rows>0){
        $response["error"] = true;
        $response["message"]  = "Only 1 session per course in the same day is allowed";
        echo json_encode($response);
    }else{

        $timeQuery = $mysqli->query("SELECT course FROM study_sessions 
            WHERE student_id = '$student_id' AND `day` = '$day' AND start_time = '$start_time'");
        
        if($timeQuery->num_rows>0){
            $response["error"] = true;
            $response["message"]  = "Sessions Intervene, please change start time";
            echo json_encode($response);
        }else{
            
            $query = $mysqli->query("INSERT INTO `study_sessions`(`student_id`, `start_time`, `session_id`, `duration`, `course`, `day`) VALUES ('$student_id','$start_time','$session_id','$duration','$course','$day')");
            $response["error"] = false;
            $response["message"]  = "Sessions added successfully";
            echo json_encode($response);
            
        }


    }




}