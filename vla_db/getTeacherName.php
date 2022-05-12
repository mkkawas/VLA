<?php
include("db_info.php");
$response = array();


$id = $_GET["id"];
$course = $_GET["course"];

$idQuery = $mysqli->query("SELECT teacher_id FROM courses WHERE student_id ='$id' AND course = '$course'");
    
$fetchedId = mysqli_fetch_assoc($idQuery);
    
$teacher_id = $fetchedId["teacher_id"];
    
$teacherQuery = $mysqli->query("SELECT name FROM teachers WHERE id = '$teacher_id'");
    
$fetchedTeacher = mysqli_fetch_assoc($teacherQuery);
    
    
$response["error"] = false;
$response["name"] = $fetchedTeacher["name"];
$response["teacher_id"] = $fetchedId["teacher_id"];
    
echo json_encode($response);
    
    