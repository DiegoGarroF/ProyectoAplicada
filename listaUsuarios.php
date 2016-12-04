<?php 

require("cone.php");

$result = $conn->query("SELECT usuario FROM tbUsuario ORDER BY usuario ASC" );
$datos="";
	if($conn->affected_rows>0)
	{
		
		while($row = mysqli_fetch_array($result)){
        	$datos=$datos.$row["usuario"]."####";
        }
	}
	echo $datos;

 ?>