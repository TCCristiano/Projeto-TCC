<?php

    header('Content-type= applicantion/json; charset=utf-8');
	
    include 'connection.php';

    mysqli_set_charset($conn, "utf-8");

    $i = $_POST["tipo"];

    $sql = $conn->prepare("SELECT cod_servico FROM tbl_servico WHERE tipo_servico = ? ORDER BY cod_servico DESC ");

    $sql->bind_param("s", $i);

    $sql->execute();

    $s = $sql->get_result();
    $linha = $s->fetch_assoc();
    $r = mysqli_fetch_array($s);

    $sql -> close();
    $conn -> close();

    echo json_encode($linha['cod_servico']);
?>