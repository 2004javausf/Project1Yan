window.onload= function(){
    console.log( "in onload");
    document.getElementById("checkApplication").addEventListener("click",getApp,false);
    document.getElementById("approveApplication").addEventListener("click",postAprove,false);
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
    xhr.open("GET","http://localhost:8080/Project1Yan/supervisor",true);
    xhr.send();
}

function jsonBuilder1(){
    var elements = document.getElementById("approveForm").elements;
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

function postAprove(){
    console.log("in postAprove");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function(){
        console.log("in ORSC " + xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
        }
    }
    xhr.open("POST", "http://localhost:8080/Project1Yan/supervisor",true);
    var payload=jsonBuilder1();
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
        return "Ugent"
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


function superTemplate(app1) {
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
      <h1 class="app-title">Applications (${Object.keys( app.superInfo ).length} results)</h1>
      ${app.superInfo.map(superTemplate).join('')}
      <p class="footer">There ${Object.keys( app.superInfo ).length} applications were added recently. Check back soon for updates.</p>
    `
}

