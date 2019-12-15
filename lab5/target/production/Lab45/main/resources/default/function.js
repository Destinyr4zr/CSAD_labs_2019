function reqReadyStateChange() {
    var numbers = document.getElementById("num").value;
    var in_sys = document.getElementById("in").value;
    var out_sys = document.getElementById("out").value;

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:1488/post");
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send('inputradix='+in_sys+'&outputradix='+out_sys+'&entry='+numbers);
    xhr.onreadystatechange = function() { 
    if(xhr.statusText=="OK")
    {
        document.open();
        document.write(xhr.responseText);
    }
    else if(xhr.statusText=="Not Found")
        alert("Ресурс не найден");
    else
        alert(xhr.statusText);
    };
}