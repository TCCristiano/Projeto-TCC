<?php
    header('Content-type= applicantion/json; charset=utf-8');
	$email = "".$_POST["email"]."";
    $senha = "".$_POST["senha"]."";

    include_once 'connection.php';

    mysqli_set_charset($conn, "utf-8");

    $sql = $conn->prepare("SELECT * FROM tbl_usuario WHERE (email_usuario = ? OR userName_usuario = ?) AND senha_usuario = ?");

    $sql->bind_param("sss",$email, $email, $senha);

    $sql->execute();

    $resultado = $sql->get_result();
    $linha = $resultado->fetch_assoc();
    $r = mysqli_fetch_array($resultado);
    
    $sql -> close();
    $conn -> close();

    if(empty($linha))
    {
        $r["erro"] = true;
        echo json_encode($linha);
    }
    else
    {
        echo json_encode($linha);
    }
?>