<?php 
	require("cone.php");
	$user=$_POST["usuario"];
	$password=md5($_POST["password"]);
	$nomb=$_POST["nombre"];
	$carn=$_POST["carnet"];
	$corr=$_POST["correo"];

	$sql = "INSERT INTO tbUsuario (usuario,pass,nombre,carnet,correo)
	VALUES ('$user','$password','$nomb','$carn','$corr')";

	if ($conn->query($sql) === TRUE) {
    echo "Correcto";
	} else {
    echo "Incorrecto";
	}
	$conn->close();

?>