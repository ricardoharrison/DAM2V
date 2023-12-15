<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Caja fuerte</title>
    <?php
        include("header.php");
    ?>
</head>
<body>
    <form action="cajafuerte.php" method="post">
        Adivinar: <input type="text" name="combi" id="">
        <?php
            session_start(); //hay que guardar la sesión para saber cuantos intentos restantes quedan
        
        
            $password = 1234;

            if(count($_POST) == 0){ //comprobación de que no se haya enviado nada antes
                $_SESSION["intentos"] = 5;
                $_SESSION["flag"] = true;
            }

            if(count($_POST) > 0){
                if($_SESSION["intentos"] > 0 && $_POST["combi"] != $password){
                    echo "Te quedan " . $_SESSION["intentos"] . " intentos";
                } else if ($_SESSION["intentos"] > 0 && $_POST["combi"] == $password){
                    echo "Alohomora!";
                    $_SESSION["flag"] = false;
                } else {
                    echo "Te has quedado sin intentos";
                    $_SESSION["flag"] = false;
                }
            }


            $_SESSION["intentos"]--;

            if($_SESSION["flag"]){
                echo '<input type="submit" value="Adivinar">';
            } else {
                echo '<input type="submit" value="Adivinar" disabled>';
            }

        ?>

    </form>
    
</body>
</html>