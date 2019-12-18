var changemode;
//@TODO for many fields

function addROW() {
    var tab = document.getElementById('tbl');
    var date = document.getElementById("date").value;
    var col = document.getElementById("col").value;
    var length = document.getElementById("length").value;
    var curind;
    if (window.sessionStorage.getItem('index')==null)
        {
            window.sessionStorage.setItem('index','1');
            curind = '1';
        }
    else
        {
            curind = (parseInt(window.sessionStorage.getItem ('index'),10)+1).toString();
            window.sessionStorage.setItem('index', curind);
        }
    if (date!=""&&col!=""&&length!="")
    sendRequest("GET", "http://localhost:1488/insert?date=" + date + "&col=" + col + "&length=" + length+ "&id="+curind, false);
    else
        alert("Введите поля!")
    changemode = false;
}

function deleteROW() {
    var tab = document.getElementById('tbl');
    if (tab.rows.length < 2) {
        alert('В таблице нет данных для удаления');
        return;
    }
    var request = new XMLHttpRequest();
    sendRequest("GET", "http://localhost:1488/delete?id=" + tab.rows[tab.rows.length-1].children[3].id);
    changemode = false;
}

function changeROW(event) {
    var tab = document.getElementById('tbl');
    var date, col, length;
    var row = tab.rows[event.target.parentElement.parentElement.rowIndex];
    if (!changemode) {
        for (i = 0, cell=row.cells[0], I = row.cells.length; i < I; i++,cell=row.cells[i]) {
            var inp = cell.children[0];
            inp.disabled = false;
        }
        changemode = true;
    } else {
        for (i = 0, cell=row.cells[0], I = row.cells.length; i < I; i++,cell = row.cells[i]) {
            var inp = cell.children[0];
            switch (i) {
                case 0:
                    date = inp.value;
                    break;
                case 1:
                    col = inp.value;
                    break;
                case 2:
                    length = inp.value;
                    break;
                default:
                    break;
            }
            inp.disabled = true;
        }
        var request = new XMLHttpRequest();
        if (date != "" && col != "" && length != "")
            {
        sendRequest("GET", "http://localhost:1488/update?date=" + date + "&col=" + col + "&length=" + length + "&id=" + event.target.closest("td").id);
            }
        else
            alert("Ошибка! Есть пустые поля");
        changemode = false;
    } 
}

function deleteROWbyID(event) {
    var tab = document.getElementById('tbl');
    if (tab.rows.length < 2) {
        alert('Ошибка удаления');
        return;
    }
    var request = new XMLHttpRequest();
    sendRequest("GET", "http://localhost:1488/delete?id=" + event.target.closest("td").id);
    changemode = false;
}

onload = function () {
    changemode = false;
    document.getElementById('columndate').disabled = 'true';
    document.getElementById('columncolor').disabled = 'true';
    document.getElementById('columnlength').disabled = 'true';
};


function sendRequest(method, url) {
    var request = new XMLHttpRequest();
    request.open(method, url, false);
    request.send();
    request.onreadystatechange = showRequest(request);
}

function showRequest(request) {
    if (request.statusText == "OK")
    {
        document.open();
        document.write(request.responseText);
    }
    else if (request.statusText == "Not Found")
        alert("Ресурс не найден");
    else
        alert(request.statusText);
}


