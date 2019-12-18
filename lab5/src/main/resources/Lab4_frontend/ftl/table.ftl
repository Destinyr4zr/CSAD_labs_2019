<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Таблица</title>
    <link rel="stylesheet" href="/styles/style_table.css"/>
    <script type="text/javascript" src="func_table.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700&display=swap&subset=cyrillic" rel="stylesheet"> 
</head>
<body>
   <nav class="nav">
       ${menu?has_content?then(menu, "")}
    </nav>
    
    <div class="textPicture">
        
        <input type="text" placeholder="Дата поступления" size="19" maxlength="11" id="date"/>
        
        <input type="text" placeholder="Цвет" size="19" maxlength="11" id="col"/>
        
        <input type="text" placeholder="Длина" size="19" maxlength="11" id="length"/>
        
        <button type="button" onClick="javascript: addROW ()">Записать</button>
        <button type="button" onClick="javascript: deleteROW ()">Удалить</button>
    </div>
   
   <div class="table_user">    
    <table id="tbl" cellspacing="0" cellpadding="0" border>
        <tr>
            <td><input name = "Дата поступления" value="Дата поступления" id="columndate" style="color: black" disabled = "true"></td>
            <td><input name = "Цвет" value="Цвет" id="columncolor" style="color: black" disabled = "true"></td>
            <td><input name = "Длина" value="Длина" id="columnlength" style="color: black" disabled = "true"></td>
        </tr>
        <#if table??>
        <#list table as row>
            <tr>
                <td><input name=${row.getDate()!} value=${row.getDate()!}  disabled="true" id=${row.getId()!}></td>
                <td><input name=${row.getColor()!} value=${row.getColor()!} disabled="true" id=${row.getId()!}></td>
                <td><input name=${row.getLength()!} value=${row.getLength()!} disabled="true" id=${row.getId()!}></td>
                <#if row??>
                    <td id =${row.getId()!}>
                        <input type="button" value="Изменить" name="ed" onClick="javascript: changeROW (event)" style="color: rgba(4,4,4,0.69)">
                        <input type="button" value="Удалить" name="del" onClick="javascript: deleteROWbyID (event)" style="color: rgba(4,4,4,0.69)">
                    </td>
                </#if>
            </tr>
        </#list>
        </#if>
    </table>
    </div>
   <b><p align="center" id="result" style="background-color:dimgrey; color: darkorange; width: 30%; height: 10%; margin: auto; margin-top: 40px;">${result?has_content?then(result, "")}</p></b>
</body>
</html>