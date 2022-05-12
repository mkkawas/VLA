<?php

include("db_info.php");
$response = array();


$newBoardId = $_POST["board_id"];
$studentId = $_POST["student_id"];
$title = $_POST["title"];
$notes = "";
$course = $_POST["course"];

if($title==null){
    $response["error"] = true;
    $response["message"] = "Please Enter Title";
    echo json_encode($response);
}else{

    $titleQuery = $mysqli->query("SELECT title FROM boards WHERE student_id ='$studentId' AND title = '$title' AND course = '$course'");

    if($titleQuery->num_rows>0){

        $response["error"] = true;
        $response["message"] = "Board Already Exists";
        echo json_encode($response);

    }else{

        $addBoardQuery = $mysqli->query("INSERT INTO boards(board_id, student_id, title, notes, course) VALUES ('$newBoardId', '$studentId','$title','$notes', '$course')");
        $response["error"] = false;
        $response["message"] = "Board Added Successfully";
        echo json_encode($response);


}



}


