function reqReadyStateChange() {
    var number = document.getElementById("num").value;
    var in_sys = document.getElementById("in").value;
    var out_sys = document.getElementById("out").value;
    
    var request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8008/convert," + number + "," + in_sys + "," + out_sys, false);
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