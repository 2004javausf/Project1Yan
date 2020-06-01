window.onload= function(){
    console.log( "in onload");
    document.getElementById("checkApplication").addEventListener("click",getApp,false);
    document.getElementById("cancelApplication").addEventListener("click",cancel,false);
    document.getElementById("confirmApplication").addEventListener("click",confirm,false);
    document.getElementById("provedApplication").addEventListener("click",getApp2,false);
    document.getElementById("submitgrade").addEventListener("click",grade,false);
    document.getElementById("submitpresentation").addEventListener("click",presentation,false);
}

//showing pending application
function getApp(){
    console.log("in get Application");
    var xhr= new XMLHttpRequest();
    xhr.onreadystatechange= function(){
        console.log("in ORSC");
        console.log(xhr.readyState);
        if(xhr.readyState== 4 && xhr.status==200){
            console.log(xhr.responseText);
            var app= JSON.parse(xhr.responseText);
            loadApp(app);
        }
    }
    xhr.open("GET","http://localhost:8080/Project1Yan/profile",true);
    xhr.send();
}

function convertedStatus(substatus){
    var a = substatus;
    if (a == "EMPLOYEE") {
        return "Denid! Please respond for the feedback to get further approval."
    } else if (a == "SUPERVISOR"){
        return "Waiting for your Supervisor to approve."
    } else if (a == "DEPARTMENT"){
        return "Waiting for your Department Head to approve."
    } else if (a == "BENCO"){
        return "Waiting for Benefits Coordinator to approve."
    }
}

function convertedDate(date1){
    var unixts = date1;
    var date = new Date(unixts);
    var fdate = date.getFullYear() + '-' + ("0" + (date.getMonth() + 1)).slice(-2) + '-' + ("0" + date.getDate()).slice(-2);
    return fdate;
}


function superTemplate(app1) {
    return `
        <div class="application">
          <h2 class = "employee-name">${app1.status}<span class = "status" style="color:red;">(${convertedStatus(app1.subStatus)})</span></h2>
          <p><strong>Application ID: <strong> ${app1.appId} </p>
          <p><strong>Event Date: <strong> ${convertedDate(app1.eventDate)} </p>
          <p><strong>Event Location: <strong> ${app1.eventLocation} </p>
          <p><strong>Event Description: <strong> ${app1.eventDescription} </p>
          <p><strong>Event Cost: $<strong> ${app1.eventCost} </p>
          <p><strong>Grading Format: <strong> ${app1.gradingFormat} </p>
          <p><strong>Type of Event: <strong> ${app1.typeofEvent} </p>
          <p><strong>Reimbursment Amount: $<strong> ${app1.appAmount} </p>
          <p><strong>Reimbursment Form Submit Date: <strong> ${convertedDate(app1.appTime)} </p>
          <p><strong>Feedback: <strong> ${app1.denyInfo} </p>
        </div>
        `
}



function loadApp(app){
    document.getElementById("app").innerHTML = `
      <h1 class="app-title">Applications (${Object.keys(app).length} results)</h1>
      ${app.map(superTemplate).join('')}
    `
}

//cancel the pending application
function cancel(){
    console.log("in cancel application");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function(){
        console.log("in ORSC " + xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
        }
    }
    xhr.open("POST", "http://localhost:8080/Project1Yan/profile",true);
    var payload=jsonBuilder1();
    xhr.send(payload);
}

function jsonBuilder1(){
    var elements = document.getElementById("cancelForm").elements;
    var obj={};
    for (var i=0; i<elements.length;i++){
        var item = elements.item(i);
        obj[item.name] = item.value;
        console.log(obj);
    }
    var json = JSON.stringify(obj);
    console.log(json);
    return json;
}

//confirm the denied application
function confirm(){
    console.log("in confirm");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function(){
        console.log("in ORSC " + xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
        }
    }
    xhr.open("POST", "http://localhost:8080/Project1Yan/profile",true);
    var payload=jsonBuilder2();
    xhr.send(payload);
}

function jsonBuilder2(){
    var elements = document.getElementById("confirmForm").elements;
    var obj={};
    for (var i=0; i<elements.length;i++){
        var item = elements.item(i);
        obj[item.name] = item.value;
        console.log(obj);
    }
    var json = JSON.stringify(obj);
    console.log(json);
    return json;
}

//showing Approved application
function getApp2(){
    console.log("in get Application2");
    var xhr= new XMLHttpRequest();
    xhr.onreadystatechange= function(){
        console.log("in ORSC");
        console.log(xhr.readyState);
        if(xhr.readyState== 4 && xhr.status==200){
            console.log(xhr.responseText);
            var app= JSON.parse(xhr.responseText);
            loadApp2(app);
        }
    }
    xhr.open("GET","http://localhost:8080/Project1Yan/profile2",true);
    xhr.send();
}

function loadApp2(app){
    document.getElementById("approved").innerHTML = `
      <h1 class="app-title">Proved Tuition Reimbursement (${Object.keys(app).length} results)</h1>
      ${app.map(superTemplate2).join('')}
    `
}

function superTemplate2(app1) {
    return `
        <div class="application">
          <h2 class = "employee-name">${app1.status}<span class = "status" style="color:red;"><strong>(Grade: <strong>${app1.grade})</span></h2>
          <p><strong>Application ID: <strong> ${app1.appId} </p>
          <p><strong>Event Date: <strong> ${convertedDate(app1.eventDate)} </p>
          <p><strong>Event Description: <strong> ${app1.eventDescription} </p>
          <p><strong>Event Cost: $<strong> ${app1.eventCost} </p>
          <p><strong>Reimbursment Proved Amount: $<strong> ${app1.provedAmount} </p>
          <p><strong>Grading Format: <strong> ${app1.gradingFormat} </p>
          <p><strong>Reimbursment Form Submit Date: <strong> ${convertedDate(app1.appTime)} </p>
          <p><strong>Presentation Link: <strong> ${app1.presentation} </p>
        </div>
        `
}

//submit grade
function grade(){
    console.log("in submit grade");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function(){
        console.log("in ORSC " + xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
        }
    }
    xhr.open("POST", "http://localhost:8080/Project1Yan/profile2",true);
    var payload=jsonBuilder3();
    xhr.send(payload);
}

function jsonBuilder3(){
    var elements = document.getElementById("gradeForm").elements;
    var obj={};
    for (var i=0; i<elements.length;i++){
        var item = elements.item(i);
        obj[item.name] = item.value;
        console.log(obj);
    }
    var json = JSON.stringify(obj);
    console.log(json);
    return json;
}

//submit presentation
function presentation(){
    console.log("in submit presentation");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function(){
        console.log("in ORSC " + xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
        }
    }
    xhr.open("POST", "http://localhost:8080/Project1Yan/profile2",true);
    var payload=jsonBuilder4();
    xhr.send(payload);
}

function jsonBuilder4(){
    var elements = document.getElementById("presentationForm").elements;
    var obj={};
    for (var i=0; i<elements.length;i++){
        var item = elements.item(i);
        obj[item.name] = item.value;
        console.log(obj);
    }
    var json = JSON.stringify(obj);
    console.log(json);
    return json;
}