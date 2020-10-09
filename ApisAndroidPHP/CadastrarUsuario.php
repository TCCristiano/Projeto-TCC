<?php
	$nome = $_POST["nome"];	
	$email = $_POST["email"];
	$username = $_POST["username"];
	$senha = $_POST["senha"];	
	$telefone = $_POST["tel"];
	$cpf = $_POST["cpf"];
	$idade = $_POST["idade"];

	include_once 'connection.php';
	if (isset($_POST["img"])) {
		$data = $_POST['img'];
		$filePath = "/opt/lampp/htdocs/ApisAndroidPHP/img/UserPerfil/$username.jpg";
		$myfile = fopen($filePath, "wb") or die("nu deu pra abrir");
		file_put_contents($filePath, base64_decode($data));
	}

	$sql = $conn->prepare("INSERT INTO tbl_usuario (nome_usuario,email_usuario,userName_usuario,senha_usuario,telefone_usuario,cpf_usuario,idade_usuario) VALUES(?,?,?,?,?,?,?)");

	$sql->bind_param("sssssss",$nome, $email,  $username, $senha, $telefone, $cpf, $idade);

	$sql->execute() or die("ErroBanco");

	$sql -> close();
	$conn -> close();

	echo json_encode("foi");

	die();
?>
