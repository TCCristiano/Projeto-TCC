<?php

    $BDservidor = 'localhost';
    $BDusuario = 'root';
    $BDsenha = '';
    $BDbanco = 'Android';

    $conn = mysqli_connect($BDservidor, $BDusuario, $BDsenha, $BDbanco);

    $sql = "SET NAMES 'utf8'";
    mysqli_query($conn, $sql);
    $sql = 'SET character_set_connection=utf8';
    mysqli_query($conn, $sql);
    $sql = 'SET character_set_client=utf8';
    mysqli_query($conn, $sql);
    $sql = 'SET character_set_results=utf8';
    mysqli_query($conn, $sql);
   

    if(mysqli_connect_error($conn))
    {
        echo json_encode ("Erro na conexão: " .mysqli_connect_error());
        die();
    }
    ?>