<?php
    header('Content-type= applicantion/json; charset=utf-8');

    include_once 'connection.php';

    $i = $_POST["cod"];

    mysqli_set_charset($conn, "utf-8");

    $sql = $conn->prepare("SELECT * FROM tbl_usuario WHERE cod_usuario = ? ");

    $sql->bind_param("s", $i);

    $sql->execute();

    $resultado = $sql->get_result();
    $linha = $resultado->fetch_assoc();
    $r = mysqli_fetch_array($resultado);

    $sql -> close();
    $conn -> close();

    $Nuser = $linha['userName_usuario'];
    $linha['img'] = file_get_contents("./img/UserPerfil/$Nuser.jpg");

    $linha['img'] = base64_encode($linha['img']);

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