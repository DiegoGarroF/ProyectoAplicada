<?php 

	// NOTA ::::: EL remitente(int idUsario) que le llegue a este POST tiene existir en la tabla usuario
	// y el destinatario igualmente(nombre del usuario en la tbUsuario) tiene que existir en tbUsuario.. aqui no se hizo con ID de usuario xq ya estaba hecho con el nombre entonces habia que cambiar codigo en algunas partes, y como el proyecto es un muestra nada mas, entonces no nos enfocamos mucho en integridad referencial:::::

	require("cone.php");
	$remitente=$_POST["remitente"];
	$destinatario=$_POST["destinatario"];
	$mensaje=$_POST["mensaje"];
	$estado=$_POST["estado"];

	$sql = "INSERT INTO tbMensajes (remitente,destinatario,mensaje,estado)
	VALUES ('$remitente','$destinatario','$mensaje','$estado')";

	if ($conn->query($sql) === TRUE) {
    echo "Correcto";
	} else {
    echo "Incorrecto";
	}
	$conn->close();

?>