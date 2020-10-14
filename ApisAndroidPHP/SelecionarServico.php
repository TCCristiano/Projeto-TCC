<?php
    header('Content-type= applicantion/json; charset=utf-8');

    include_once 'connection.php';

    $i = $_POST["cod"];
    $tipo = $_POST["tipo"];


    mysqli_set_charset($conn, "utf-8");

    $sql = $conn->prepare("SELECT * FROM tbl_servico WHERE cod_servico >= ? AND tipo_servico = ?");

    $sql->bind_param("ss", $i, $tipo);

    $sql->execute();

    $resultado = $sql->get_result();
    $linha = $resultado->fetch_assoc();
    $r = mysqli_fetch_array($resultado);


    $Nuser = $linha['cod_usuario'];
    $linha['img'] = file_get_contents("./img/Servico/$Nuser.jpg");

    $linha['img'] = base64_encode($linha['img']);

    $sql -> close();
    $conn -> close();

    echo json_encode($linha);

?>