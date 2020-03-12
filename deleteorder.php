<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$connection=new mysqli("localhost","root","","cwsecomtable");
 $sqlCommand=$connection->prepare("delete from placeorder where email=? and productid=?");
 $sqlCommand->bind_param("si",$_GET["email"],$_GET["productid"]);
 $sqlCommand->execute();