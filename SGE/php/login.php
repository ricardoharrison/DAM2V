<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log in</title>
    <?php
        include("header.php");
    ?>
    <!-- meter bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
    <form action="login.php" method="post">
        Password: <input type="text" name="password" id="">
        <input class="btn btn-primary" type="submit" value="Log in">  <!-- objeto boostrap -->
        <?php
        session_start();
            $password = 1234;
            if(count($_POST) > 0){
                if($password == $_POST["password"]){
                    $_SESSION["login"] = "ok";
                    header("Location: home.php");
                    die(); //exit() es intercambiable con die(), hacen lo mismo
                }
            }            
        ?>        
    </form>
</body>
</html>