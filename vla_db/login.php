<?php

include("db_info.php");

$response = array();

$email = $_GET["email"];
$password = $_GET["password"];

$emailQuery = $mysqli->query("SELECT email from users where email='$email'");

if($emailQuery->num_rows>0){

    $passwordQuery = $mysqli->query("SELECT password from users where email = '$email'");

    $fetched = mysqli_fetch_assoc($passwordQuery);
    $fetchedPassword = $fetched["password"];

    if($password == $fetchedPassword ){
        $user = $mysqli->query("SELECT  id,type,name from users where email = '$email'");

        $response["error"]= false;
        $response["message"]= "user successfully logged in";
        $response["user"] = mysqli_fetch_assoc($user);
        echo json_encode($response);
    }else{
        $response["error"]= true;
        $response["message"]= "password doesn't match email";
        echo json_encode($response);
    }



}else{

}