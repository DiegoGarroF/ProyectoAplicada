<?php

	require("cone.php");
	$usuario=$_POST["user_name"];
	$pass=md5($_POST["password"]);
$result = $conn->query("SELECT nombre,correo FROM tbUsuario where usuario='$usuario' and pass='$pass'" );

	if($conn->affected_rows>0)
	{
		$datos="";
		while($row = mysqli_fetch_array($result)){
        	$datos=$datos.$row["nombre"]."#";
        	$datos=$datos.$row["correo"]."*";
        }
		$result = $conn->query("SELECT u.nombre,u.correo, m.mensaje FROM tbUsuario u,tbMensajes m where u.nombre =m.destinatario and u. usuario='$usuario' and u.pass='$pass' ");

	if($conn->affected_rows>0)
	{
		$datos="";
		$cont=0;
		while($row = mysqli_fetch_array($result)){
			if($cont<1)
			{
			$datos=$datos.$row["nombre"]."#";
      $datos=$datos.$row["correo"]."*";
			$datos=$datos.$row["mensaje"]."~";
        	$cont=$cont+1;
			}else
			{
			$datos=$datos.$row["mensaje"]."~";
			}
        }
	}
	else {
	//echo "";
	}

	echo $datos;
	}
	else {
	echo "Usuario Incorrecto";
	}

?>
