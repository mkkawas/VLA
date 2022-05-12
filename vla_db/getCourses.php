<?php
include("db_info.php");
$response = array();

$type = $_GET["type"];
$id = $_GET["id"];

if($type == "student"){

$typeQuery = $mysqli->query("SELECT courses FROM students WHERE id ='$id'");

$fetchedCourses = mysqli_fetch_assoc($typeQuery);

$response["error"] = false;
$response["message"] = "student courses fetched successfully";
$response["courses"] = $fetchedCourses["courses"];


echo json_encode($response);

}else{

    $typeQuery = $mysqli->query("SELECT courses FROM teachers WHERE id ='$id'");

    $fetchedCourses = mysqli_fetch_assoc($typeQuery);
    
    $response["error"] = false;
    $response["message"] = "student courses fetched successfully";
    $response["courses"] = $fetchedCourses["courses"];

    echo json_encode($response);

}
