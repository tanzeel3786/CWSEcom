<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$connection=new mysqli("localhost","root","","cwsecomtable");
// $sqlCommand=$connection->prepare("insert into ecomtable values(?,?,?,?,?)");
// $sqlCommand->bind_param("issss",$_GET["id"],$_GET["name"],$_GET["email"],$_GET["phone"],$_GET["password"]);
// $sqlCommand->execute();

 $emailCheckSqlCommand=$connection->prepare("select*from ecomtable where email=?");
 $emailCheckSqlCommand->bind_param("s",$_GET["email"]);
 $emailCheckSqlCommand->execute();
 $emailRes=$emailCheckSqlCommand->get_result();
 
 if($emailRes->num_rows==0)
 {echo "User registred";
      $sqlCommand=$connection->prepare("insert into ecomtable values(?,?,?,?)");
 $sqlCommand->bind_param("ssss",$_GET["name"],$_GET["email"],$_GET["phone"],$_GET["password"]);
 $sqlCommand->execute();
 }
 else {
     
     echo "Email already exist";
     
 }