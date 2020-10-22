<?php

    header('Content-type= applicantion/json; charset=utf-8');
	
    include 'connection.php';

    mysqli_set_charset($conn, "utf-8");

    $i = $_POST["id"];

    $sql = $conn->prepare("SELECT id_mensage FROM tbl_mensagem WHERE destinatarioID = ? ORDER BY id_mensage DESC ");

    $sql->bind_param("s", $i);

    $sql->execute();

    $s = $sql->get_result();
    $linha = $s->fetch_assoc();

    $sql -> close();
    $conn -> close();

    echo json_encode($linha['id_mensage']);
?>