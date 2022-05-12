<?php

include("db_info.php");
$response = array();

$student_id = $_GET["student_id"];
$course = $_GET["course"];


$BoardsQuery = $mysqli->query("SELECT boards FROM courses WHERE student_id = '$student_id' AND course = '$course'");

$fetchedBoards = mysqli_fetch_assoc($BoardsQuery);
$boards = $fetchedBoards["boards"];

if($boards == null){
    //return no boards created by user
    $response["error"] = true;
    $response["boards"] = "";
    $response["message"] = "No Boards Created Yet!!";
    echo json_encode($response);

}else{
    
    $response["error"] = false;

    $response["boards"] = $boards;
    $response["message"] = "Boards Successfully Fetched";
    echo json_encode($response);
}