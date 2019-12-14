function addROW () {
    var tab = document.getElementById ('tbl');
    var ro = tab.insertRow (-1);
    
    var date = document.getElementById("date").value;
    var kil = document.getElementById("kil").value;
    var time = document.getElementById("time").value;
    var count  = 0;
    
    for (var j = 0, J = tab.rows [0].cells.length; j < J; j++) {
        var inp = document.createElement ('input');
        
        if (count == 0){
            inp.name = inp.value = date;
            count++;
        }else if (count == 1){
            inp.name = inp.value = kil;
            count++;
        }else if (count == 2){
            inp.name = inp.value = time;
            count++;
        }
        
        var ce = ro.insertCell (-1)
        ce.appendChild (inp);
   }
    
    var request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8008/insert," + date + "," + kil + "," + time, false);
    request.send();
    var status = request.status;
    var res = document.getElementById("resu");
    
    if(status==200)
        res.innerHTML = request.responseText;
    else if(status==404)
        document.write("Ресурс не найден")
    else
        document.write(request.statusText)
}

function updateROW () {
    var date = document.getElementById("date").value;
    var kil = document.getElementById("kil").value;
    var time = document.getElementById("time").value;
    
    location.reload();
    
    var request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8008/update," + date + "," + kil + "," + time, false);
    request.send();
    var status = request.status;
    
    if(status==200)
        console.log("OK")
    else if(status==404)
        document.write("Ресурс не найден")
    else
        document.write(request.statusText)
}

onload = function () {
    for (var inp = document.getElementsByTagName ('input'), j = 0, J = inp.length; j < J; j++){
        inp [j].style.color = 'black', inp [j].value = inp [j].name;
    }
    
    updateTab();
}

function updateTab(){
    var request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8008/load", false);
    request.send();
    var status = request.status;
    
    if(status==200){
        var line = request.responseText;
        var array_line = line.split(" ");
        console.log(array_line);
    }
    else if(status==404)
        document.write("Ресурс не найден")
    else
        document.write(request.statusText)
    
    var count_row = (array_line.length - 1)/3;
    
    var index = 0;
    
    for (var i = 0; i < count_row; i++){
        var tab = document.getElementById ('tbl');
        var ro = tab.insertRow (-1);
    
        for (var j = 0, J = tab.rows[0].cells.length; j < J; j++) {
            var inp = document.createElement ('input');
            
            inp.name = inp.value = array_line[index];
            index++;
        
            var ce = ro.insertCell (-1)
            ce.appendChild (inp);
        }
    }
}

document.addEventListener("DOMContentLoaded", () => {
    let tab = document.getElementById("tbl");
    tab.ondblclick = e => {
        let cell = e.target.parentNode;
        let row = cell.parentElement;
        let rows = row.parentElement.children;
        var count = document.querySelectorAll("tr").length - 1;
        for (var i = 0; i < count; ++i) {
            if (rows[i] == row) {
                break;
            }
        }
        let cell_2 = e.target;
        let row_2 = cell_2.parentElement;
                
        let columns = row_2.parentElement.children;
        for (var j = 0; j < columns.length; ++j) {
            if (columns[j] == row) {
                break;
            }
        }
        alert('Удалить строку');
                
        var date = document.getElementsByTagName("tr")[i].getElementsByTagName("input")[0].name;
        
        var request = new XMLHttpRequest();
        request.open("GET", "http://localhost:8008/delete," + date, false);
        request.send();
        var status = request.status;
        
        if(status==200){
            alert("Строка удалена")
        }
        else if(status==404)
            document.write("Ресурс не найден")
        else
            document.write(request.statusText)
                
        console.log("" + date);
        
        var tab = document.getElementById ('tbl');
        if (tab.rows.length < 2) {
            alert ('В таблице нет данных для удаления'); 
            return;
        }
        tab.deleteRow (i);
    };
    
    tab.onclick = ev => {
        let cell = ev.target.parentNode;
        let row = cell.parentElement;
        let rows = row.parentElement.children;
        var count = document.querySelectorAll("tr").length - 1;
        for (var i = 0; i < count; ++i) {
            if (rows[i] == row) {
                break;
            }
        }
        let cell_2 = ev.target;
        let row_2 = cell_2.parentElement;
                
        let columns = row_2.parentElement.children;
        for (var j = 0; j < columns.length; ++j) {
            if (columns[j] == row) {
                break;
            }
        }
                
        var date = document.getElementsByTagName("tr")[i].getElementsByTagName("input")[0].name;
        var kil = document.getElementsByTagName("tr")[i].getElementsByTagName("input")[1].name;
        var time = document.getElementsByTagName("tr")[i].getElementsByTagName("input")[2].name;
        
        document.getElementById("date").value = date;
        document.getElementById("kil").value = kil;
        document.getElementById("time").value = time;
    };
});