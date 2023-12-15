<?php
   session_start();
   if($_SESSION["login"] != "ok"){
    header("Location: login.php");
   } 
?>