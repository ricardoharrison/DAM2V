<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi primer formulario</title>
</head>
<body>
    <!-- form:get -> la info es visible en la url
         form:post -> la info no es visible (usar para contraseñas, etc)

         El formulario es un array dentro de php ($_POST)
    -->


    <form action="index.php" method="post">
        Nombre: <input type="text" name="nombre" id=""> <!-- atributo 'name' = nombre de la variable -->
        <!-- Apellido: <input type="text" name="apellido" id=""> -->
        <input type="submit" value="Adivinar"> <!-- tiene que ser submit para que envíe la info al pulsar -->
        <br>
        Combinación: <input type="text" name="combi" id="">
    </form>
    <hr>

    
</body>
</html>

<?php
    //el archivo "index.php" siempre será el primero que se busque para ejecutar
    echo "<br>Hola mundo" . "buenas tardes";
    print "Buenas tardes<br>";

    $edad = 133;

    echo "tengo " . $edad . " años";

    $colores = array("rojo", "verde", "azul");

    //sirve para que muestre el contenido y tipo de las variables del array
    var_dump($colores);

    //estructuras de control
    if($edad < 18){
        echo "Eres menor de edad";
    } else if ($edad < 120) {
        echo "Eres adulto";
    } else {
        echo "No puedes tener tantos años";
    }

    echo ($edad > 18) ? " puedes votar<br>" : " no puedes votar<br>";

    for($i = 0; $i < count($colores); $i++){
        echo $colores[$i] . "<br>";
    }

    foreach ($colores as $iterador){
        echo $iterador . "<br>";
    }

    $j = 1;
    while ($j < 4){
        echo "Dentro del while...<br>";
        $j++;
    }

    echo "Terminó el while!<br><br>";

    /* Ejercicio Formulario */
    echo "Formulario:<br>";
    var_dump($_POST);
    if(count($_POST) != 0){
        echo "Me llamo " . $_POST["nombre"] . " " . $_POST["apellido"] . " y tengo " . $edad . " años";
    }
    echo "<hr>"; 
?>