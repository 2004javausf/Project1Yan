window.onload = function(){
    console.log("in first onload");
    document.getElementById("apFormSubmit").addEventListener("click",postApp,false);
}

function jsonBuilder(){
    var elements = document.getElementById("apForm").elements;
    var obj={};
    for (var i=0; i<elements.length-1;i++){
        var item = elements.item(i);
        obj[item.name] = item.value;
        console.log(obj);
    }
    var json = JSON.stringify(obj);
    console.log(json);
    return json;
}

function postApp(){
    console.log("in postApp");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function(){
        console.log("in ORSC " + xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
        }
    }
    xhr.open("POST", "http://localhost:8080/Project1Yan/application",true);
    var payload=jsonBuilder();
    xhr.send(payload);
}