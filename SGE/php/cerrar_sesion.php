<?php
session_start();
session_destroy();
echo "<h3>Hasta otra!</h3>";
echo "En 3 segundos serás redirigido a la pantalla de iniciar sesión";
echo '<meta http-equiv="refresh" content="3;url=login.php">'; //redirigir a home en 3 segundos
?>