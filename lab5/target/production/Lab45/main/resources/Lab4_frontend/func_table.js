var changemode, curdate, curcol, curlength;

function addROW() {
    var date = document.getElementById("date").value;
    var col = document.getElementById("col").value;
    var length = document.getElementById("length").value;
    sendRequest("GET", "http://localhost:1488/insert?date=" + date + "&col=" + col + "&length=" + length, false);
}

function deleteROW() {
    var tab = document.getElementById('tbl');
    if (tab.rows.length < 2) {
        alert('В таблице нет данных для удаления');
        return;
    }
    var request = new XMLHttpRequest();
    sendRequest("GET", "http://localhost:1488/delete?id=" + (tab.rows.length).toString());
}

function changeROW(index) {
    var tab = document.getElementById('tbl');
    var date, col, length;
    var row = tab.rows[index];
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
        sendRequest("GET", "http://localhost:1488/update?date=" + date + "&col=" + col + "&length=" + length + "&id=" + index);
            }
        else
            alert("Ошибка! Есть пустые поля");
        changemode = false;
    } 
}

function deleteROWbyID(index) {
    var tab = document.getElementById('tbl');
    if (tab.rows.length < 2) {
        alert('Ошибка удаления');
        return;
    }
    var request = new XMLHttpRequest();
    sendRequest("GET", "http://localhost:1488/delete?id=" + index);
}

onload = function () {
    for (var inp = document.getElementsByTagName('input'), j = 3, J = inp.length; j < J; j++) {
        inp[j].style.color = 'black', inp[j].value = inp[j].name;
    }
    changemode = false;
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


