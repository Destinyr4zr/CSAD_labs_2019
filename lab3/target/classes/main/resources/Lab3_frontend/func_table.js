var changemode, res, curdate, curcol, curlength;

function addROW() {
    var date = document.getElementById("date").value;
    var col = document.getElementById("col").value;
    var length = document.getElementById("length").value;
    if (createInputROW(date, col, length))
        sendRequest("GET", "http://localhost:1488/insert?date=" + date + "&col=" + col + "&length=" + length, false);
}

function deleteROW() {
    var tab = document.getElementById('tbl');
    if (tab.rows.length < 2) {
        alert('В таблице нет данных для удаления');
        return;
    }
    tab.deleteRow(tab.rows.length - 1);
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
    tab.deleteRow(index);
    var request = new XMLHttpRequest();
    sendRequest("GET", "http://localhost:1488/delete?id=" + index);
}

onload = function () {
    docload();
    res = document.getElementById('result');
    var request = new XMLHttpRequest();
    request.open("GET", "http://localhost:1488/table/load", false);

    request.onreadystatechange = function () {
        if (request.statusText == "OK") {
            if (request.responseText.length > 4) {
            var tabarray = JSON.parse(request.responseText);
            tabarray.forEach(function (row) {
                var date = row["date"];
                var col = row["col"];
                var length = row["length"];
                createInputROW(date, col, length);
            })
            }
            res.innerHTML = request.getResponseHeader("tabstatus");
        } else if (request.statusText == "Not Found")
            res.innerHTML = "Ресурс не найден";
        else
            alert(request.statusText);
    };
    request.send();
}

function createInputROW(date, col, length) {
    var tab = document.getElementById('tbl');
    var ro = tab.insertRow(-1);
    var rowlength = tab.rows[0].cells.length;
    var count = 0;

    if (date != "" && col != "" && length != "") {

        for (var j = 0, J = rowlength + 2; j < J; j++) {

            var inp = document.createElement('input');
            switch (count) {
                case 0:
                    inp.name = inp.value = date;
                    inp.disabled = true;
                    var ce = ro.insertCell(-1)
                    break;
                case 1:
                    inp.name = inp.value = col;
                    inp.disabled = true;
                    var ce = ro.insertCell(-1);
                    break;
                case 2:
                    inp.name = inp.value = length;
                    inp.disabled = true;
                    var ce = ro.insertCell(-1);
                    break;
                case 3:
                    inp.type = 'button';
                    inp.name = inp.value = 'Изменить';
                    inp.addEventListener('click', (event) => {
                        const rowIndex = Array.from(tab.rows).findIndex(row => row.contains(event.target));
                        if (rowIndex > 0) {
                            changeROW(rowIndex);
                        }
                    })
                    break;
                case 4:
                    inp.type = 'button';
                    inp.name = inp.value = 'Удалить';
                    inp.addEventListener('click', (event) => {
                        const rowIndex = Array.from(tab.rows).findIndex(row => row.contains(event.target));
                        if (rowIndex > 0) {
                            deleteROWbyID(rowIndex);
                        }
                    })
                    break;
                default:
                    break;
            }
            count++;
            ce.appendChild(inp);
        }
        return true;
    } else {
        alert("Ошибка! Есть пустые поля");
        return false;
    }
}

function sendRequest(method, url) {
    var request = new XMLHttpRequest();
    request.open(method, url, false);
    request.send();
    request.onreadystatechange = showRequest(request);
}

function showRequest(request) {
    if (request.statusText == "OK")
        res.innerHTML = request.responseText;
    else if (request.statusText == "Not Found")
        alert("Ресурс не найден");
    else
        alert(request.statusText);
}

function docload()
{
    for (var inp = document.getElementsByTagName('input'), j = 3, J = inp.length; j < J; j++) {
        inp[j].style.color = 'black', inp[j].value = inp[j].name;
    }
    changemode = false;
    document.getElementById('columndate').disabled = true;
    document.getElementById('columncolor').disabled = true;
    document.getElementById('columnlength').disabled = true;
}
