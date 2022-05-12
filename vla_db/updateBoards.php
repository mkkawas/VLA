<?php

include("db_info.php");

$response = array();

$student_id = $_POST["student_id"];
$course = $_POST["course"];
$oldboardids = $_POST["oldBoards"];
$newboardid = $_POST["newBoard"];


if($oldboardids == null){
    
    $boardQuery = $mysqli->query("UPDATE courses SET boards = '$newboardid' where student_id = '$student_id' AND course= '$course'");
    $response["message"] = "Boards successfully updated";
    $response["newboards"] = $boardids;
    echo json_encode($response);

}else{

    $boardids = $oldboardids.";".$newboardid;
    $boardQuery = $mysqli->query("UPDATE courses SET boards = '$boardids' where student_id = '$student_id' AND course= '$course'");
    $response["message"] = "Boards successfully updated";
    $response["newboards"] = $boardids;
    echo json_encode($response);

}



