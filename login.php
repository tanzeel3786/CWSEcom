<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$dbc=new mysqli("localhost","root","","cwsecomtable");
$checklogininfo=$dbc->prepare("select*from ecomtable where email=? and password=?");
//$checklogininfo->bind_param("ss",$_GET["email"],$_GET["password"]);
$checklogininfo->bind_param("ss",$_GET["email"],$_GET["password"]);
$checklogininfo->execute();

$loginresult=$checklogininfo->get_result();

if($loginresult->num_rows==0)
{
    echo "User does not exist";
}
 else {
    echo "User exist";    
}
