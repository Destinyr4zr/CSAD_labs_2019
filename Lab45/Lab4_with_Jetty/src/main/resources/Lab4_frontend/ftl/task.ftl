<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/style_task.css"/>
    <title>Индивидуальное задание</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700&display=swap&subset=cyrillic" rel="stylesheet"> 
    <script type="text/javascript" src="function.js"></script>
</head>
<body>
   <nav class="nav">
        <a class="nav_link" href="/index"><strong><i>Главная</i></strong></a>
        <a class="nav_link" href="/task"><i>Индивидуальное задание</i></a>
        <a class="nav_link" href="/table"><i>Работа с таблицей</i></a>
    </nav>
    
    <div class="textPicture">
        <form>
            <p><input type="text" name="in_system" placeholder="Введите начальную СС" size="22" maxlength="3" id="in" style="text-align: center; "/></p>
            <p><input type="text" name="out_system" placeholder="Введите конечную СС" size="22" maxlength="3" id="out" style="text-align: center;"/></p>
            <p><input type="text" name="num" placeholder="Введите числа" size="22" id="num"/></p>
                <button type="button" onClick="reqReadyStateChange();">Перевести</button> 
            <b><p align="center" id="result" style="background-color:dimgrey; color: darkorange; width: 10%; height: 10%; margin: auto; margin-top: 40px;">${result?has_content?then(result, "")}</p></b>
        </form>
    </div>
    
    <div class="textArea">
    
    </div>
</body>
</html>