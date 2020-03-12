<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$dbc=new mysqli("localhost","root","","cwsecomtable");
$checklogininfo=$dbc->prepare("select*from productdetails where productid=?");
//$checklogininfo->bind_param("ss",$_GET["email"],$_GET["password"]);
$checklogininfo->bind_param("i",$_GET["productid"]);
$checklogininfo->execute();

$loginresult=$checklogininfo->get_result();
$parray= array();
while ($row2=$loginresult->fetch_assoc())
{
    array_push($parray, $row2);
}
echo json_encode($parray);