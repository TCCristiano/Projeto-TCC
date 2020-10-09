<?php
	$nome = $_POST["nome"];
	$cod = $_POST["cod"];		
	$tipo = $_POST["tipo"];
	$preco = $_POST["preco"];
	$descricao = $_POST["descricao"];

	include_once 'connection.php';
	if (isset($_POST["img"])) {
		$data = $_POST['img'];
		$filePath = "/opt/lampp/htdocs/ApisAndroidPHP/img/Servico/$cod.jpg";
		$myfile = fopen($filePath, "wb") or die("Unable to open file!");
		file_put_contents($filePath, base64_decode($data));
	}

	$sql = $conn->prepare("INSERT INTO tbl_servico (nome_servico, cod_usuario, preco_servico, descricao_servico, tipo_servico) VALUES(?,?,?,?,?)");

	$sql->bind_param("sssss",$nome, $cod, $preco, $descricao, $tipo);

	$sql->execute() or die("ErroBanco");

	$sql -> close();
	$conn -> close();
	
	$r = true;

	echo json_encode($r);

	die();
?>