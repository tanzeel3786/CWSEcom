<?php

$connection=new mysqli("localhost","root","","cwsecomtable");
 $sqlCommand=$connection->prepare("insert into placeorder values(?,?,?,?,?)");
 $sqlCommand->bind_param("siiss",$_GET["email"],$_GET["productid"],$_GET["amount"],$_GET["productname"],$_GET["image"]);
 $sqlCommand->execute();
