<?php
    header('Content-type= applicantion/json; charset=utf-8');

    include_once 'connection.php';

    $i = $_POST["cod"];

    mysqli_set_charset($conn, "utf-8");

    $sql = $conn->prepare("SELECT * FROM tbl_servico WHERE cod_usuario = ? ");

    $sql->bind_param("s", $i);

    $sql->execute();

    $resultado = $sql->get_result();
    $linha = $resultado->fetch_assoc();
    $r = mysqli_fetch_array($resultado);

    $sql -> close();
    $conn -> close();

    echo json_encode($linha);

?>