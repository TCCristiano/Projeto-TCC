<?php

    header('Content-type= applicantion/json; charset=utf-8');

    include 'connection.php';

    $destinatario = $_POST["destinatario"];;
    mysqli_set_charset($conn, "utf-8");

    $sql = $conn->prepare("SELECT * FROM tbl_mensagem WHERE destinatarioID = ? AND id_mensage >= 1 ");

    $sql->bind_param("s", $destinatario);

    $sql->execute();

    $resultado = $sql->get_result();
    $linha = $resultado->fetch_assoc();

    $sql -> close();
    $conn -> close();

    include 'connection.php';

    $sql = $conn->prepare("DELETE FROM tbl_mensagem WHERE id_mensage = ? ");

    $sql->bind_param("s", $linha['id_mensage']);

    $sql->execute();

    $sql -> close();
    $conn -> close();
    

    echo json_encode($linha);
    
?>