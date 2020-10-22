<?php
	$texto = $_POST["txt"];
	$hora = $_POST["hora"];		
	$remetenteID = $_POST["remetente"];
	$destinatarioID = $_POST["destinatario"];

	include_once 'connection.php';

	$sql = $conn->prepare("INSERT INTO tbl_mensagem(texto,hora,remetenteID,destinatarioID) VALUES(?,?,?,?)");

	$sql->bind_param("ssss",$texto, $hora, $remetenteID, $destinatarioID);

	$sql->execute() or die("ErroBanco");

	$sql -> close();
	$conn -> close();
	
	$r = true;

	echo json_encode($r);

	die();
?>