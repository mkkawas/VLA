<?php
include("db_info.php");
$response = array();


$id = $_GET["id"];
$course = $_GET["course"];
$type = $_GET["type"];



if($type == "student"){
$taskQuery = $mysqli->query("SELECT tasks_id FROM courses WHERE student_id ='$id' AND course = '$course'");

$fetchedTasks = mysqli_fetch_assoc($taskQuery);

if($fetchedTasks["tasks_id"] == null){

    $response["error"] = true;
    $response["tasks"] = "no tasks assigned yet";

}else{
    $response["error"] = false;
    $response["tasks"] = $fetchedTasks["tasks_id"];
}

echo json_encode($response);
}else{

$taskQuery = $mysqli->query("SELECT tasks_id FROM courses WHERE teacher_id ='$id' AND course = '$course'");

$fetchedTasks = mysqli_fetch_assoc($taskQuery);

if($fetchedTasks["tasks_id"] == null){

    $response["error"] = true;
    $response["tasks"] = "no tasks assigned yet";

}else{
    $response["error"] = false;
    $response["tasks"] = $fetchedTasks["tasks_id"];
}

echo json_encode($response);


}
