<?php

$connection=new mysqli("localhost","root","","cwsecomtable");

$slectbrands=$connection->prepare("select distinct brand from protable");
$slectbrands->execute();

$result=$slectbrands->get_result();
$brands=array();
 
while ($row=$result->fetch_assoc())
{
    array_push($brands, $row);
    
}
echo json_encode($brands);
        

