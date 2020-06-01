window.onload= function(){
    console.log( "in onload");
    document.getElementById("checkApplication").addEventListener("click",getApp,false);
    document.getElementById("approveApplication").addEventListener("click",postAprove,false);
    document.getElementById("denyApplication").addEventListener("click",postDeny,false);
    document.getElementById("showingGrade").addEventListener("click",getApp2,false);
    document.getElementById("finalProve").addEventListener("click",finalApprove,false);
    document.getElementById("finalDeny").addEventListener("click",finalDeny,false);
    document.getElementById("showAward").addEventListener("click",getApp3,false);
}

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
    xhr.open("GET","http://localhost:8080/Project1Yan/benco",true);
    xhr.send();
}

function jsonBuilder1(){
    var elements = document.getElementById("approveForm").elements;
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

function jsonBuilder2(){
    var elements = document.getElementById("denyForm").elements;
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

function postAprove(){
    console.log("in postAprove");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function(){
        console.log("in ORSC " + xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
        }
    }
    xhr.open("POST", "http://localhost:8080/Project1Yan/benco",true);
    var payload=jsonBuilder1();
    xhr.send(payload);
}

function postDeny(){
    console.log("in postDeny");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function(){
        console.log("in ORSC " + xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
        }
    }
    xhr.open("POST", "http://localhost:8080/Project1Yan/benco",true);
    var payload=jsonBuilder2();
    xhr.send(payload);
}

function ugent(date2){
    const Date1 = date2;
    const Date2 = new Date();
    const timeDiff = (new Date(Date1)) - (new Date(Date2));
    const days = timeDiff / (1000 * 60 * 60 * 24)
    console.log(days);
    if (days < 0) {
        return "Event Time Passed"
    } else if (days <= 14){
        return "Urgent"
    } else {
        return `${days.toFixed(0)} days away`
    }
}

function convertedDate(date1){
    var unixts = date1;
    var date = new Date(unixts);
    var fdate = date.getFullYear() + '-' + ("0" + (date.getMonth() + 1)).slice(-2) + '-' + ("0" + date.getDate()).slice(-2);
    return fdate;
}


function bencoTemplate(app1) {
    return `
        <div class="application">
          <h2 class = "employee-name">${app1.employeeName}<span class = "status">(${ugent(convertedDate(app1.eventDate))})</span></h2>
          <p><strong>Application ID: <strong> ${app1.appId} </p>
          <p><strong>Event Date: <strong> ${convertedDate(app1.eventDate)} </p>
          <p><strong>Event Location: <strong> ${app1.eventLocation} </p>
          <p><strong>Event Description: <strong> ${app1.eventDescription} </p>
          <p><strong>Event Cost: $<strong> ${app1.eventCost} </p>
          <p><strong>Grading Format: <strong> ${app1.gradingFormat} </p>
          <p><strong>Type of Event: <strong> ${app1.typeofEvent} </p>
          <p><strong>Justification: <strong> ${app1.justification} </p>
          <p><strong>Reimbursment Amount: $<strong> ${app1.appAmount} </p>
          <p><strong>Reimbursment Form Submit Date: <strong> ${convertedDate(app1.appTime)} </p>
        </div>
        `
}

function loadApp(app){
    document.getElementById("app").innerHTML = `
      <h1 class="app-title">Applications (${Object.keys(app).length} results)</h1>
      ${app.map(bencoTemplate).join('')}
      <p class="footer">There ${Object.keys(app).length} applications were added recently. Check back soon for updates.</p>
    `
}

//showing submitted grade
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
    xhr.open("GET","http://localhost:8080/Project1Yan/benco2",true);
    xhr.send();
}

function loadApp2(app){
    document.getElementById("gradeWrite").innerHTML = `
      <h1 class="app-title">Proved Tuition Reimbursement (${Object.keys(app).length} results)</h1>
      ${app.map(bencoTemplate2).join('')}
    `
}

function bencoTemplate2(app1) {
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
        </div>
        `
}

//final prove
function finalApprove(){
    console.log("in final Approve");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function(){
        console.log("in ORSC " + xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
        }
    }
    xhr.open("POST", "http://localhost:8080/Project1Yan/benco2",true);
    var payload=jsonBuilder3();
    xhr.send(payload);
}

function jsonBuilder3(){
    var elements = document.getElementById("finalProveForm").elements;
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

//final deny
function finalDeny(){
    console.log("in final Deny");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function(){
        console.log("in ORSC " + xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
        }
    }
    xhr.open("POST", "http://localhost:8080/Project1Yan/benco2",true);
    var payload=jsonBuilder4();
    xhr.send(payload);
}

function jsonBuilder4(){
    var elements = document.getElementById("finalDenyForm").elements;
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

//showing Awarded Tuition Reimbursement 
function getApp3(){
    console.log("in get Awarded Reimbursement");
    var xhr= new XMLHttpRequest();
    xhr.onreadystatechange= function(){
        console.log("in ORSC");
        console.log(xhr.readyState);
        if(xhr.readyState== 4 && xhr.status==200){
            console.log(xhr.responseText);
            var app= JSON.parse(xhr.responseText);
            loadApp3(app);
        }
    }
    xhr.open("GET","http://localhost:8080/Project1Yan/benco3",true);
    xhr.send();
}

function loadApp3(app){
    document.getElementById("AwardWrite").innerHTML = `
      <h1 class="app-title">There are ${Object.keys(app).length} employees have applied Tuition Reimbursement.</h1>
      ${app.map(bencoTemplate3).join('')}
    `
}

function bencoTemplate3(app1) {
    return `
        <div class="application">
          <h2 class = "employee-name">${app1.name}<span class = "status" style="color:red;"><strong>(Account ID: <strong>${app1.id})</span></h2>
          <p><strong>Awarded Reimburstments: <strong> ${app1.awarded} </p>
        </div>
        `
}

