let studentId = localStorage.getItem('studentID');
let chatHistory = [];
getMessageHistory();

function getMessageHistory() {
    $('.history').empty();
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/sc/studentChat/getstudentMsg/" + encodeURIComponent(studentId),
        async: true,
        success: function (data) {
            if (data.code === "00") {
                let count = 0;
                for (let d of data.content) {
                    let chatId = d.chatId;
                    let receiver = d.receiver;
                    let msg = d.message;
                    let readStatus = d.readStatus;
                    count++;
                    let sentMessage = {
                        count: count,
                        chatId: chatId,
                        receiver: receiver,
                        msg: msg,
                        readStatus: readStatus
                    }
                    chatHistory.push(sentMessage);
                }

                setHistory();

            } else if(data.code === "01"){
                let msg = `
                    <h2 style="font-size: 25px;padding: 10px; text-align: center;color: red;">History is empty! </h2>
                `;
                $('.history').append(msg);

            }
        }, error: function (xhr, error) {
            console.log("Error: " + error);
            alert("Message History Load fail !");
        }
    });
}

function setHistory() {
    // Create an array to store promises
    let promises = [];

    for (let d of chatHistory) {
        let receiver = d.receiver;
        let title;
        let name;
        let promise = $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/t1/teacher/getTeacherDetails/" + encodeURIComponent(receiver),
            async: true,
            success: function (data) {
                console.log("Teacher Data:", data);
                if (data.code === "00") {
                    title = data.content.title;
                    name = data.content.teacherName;
                    console.log(title, name);
                } else {
                    title = "Unknown";
                    name = "";
                }
            }, error: function (xhr, error) {
                console.log("Error: " + error);
                alert("Message History Load fail !");
            }
        });

        // Push the promise to the array
        promises.push(promise);
        console.log(promises);
    }

    // Use $.when to wait for all promises to be resolved
    $.when.apply($, promises).then(function () {
        // Arguments are the resolved values of the promises
        let resolvedData = Array.from(arguments);

        if (promises.length === 1) {
            // If there is only one promise, wrap it in an array
            resolvedData = [resolvedData];
        }

        // Now you can safely update the UI with the data
        for (let i = 0; i < resolvedData.length; i++) {
            let data = resolvedData[i][0];
            // Handle data here
        }

        for (let d of chatHistory) {
            let count = d.count;
            let chatId = d.chatId;
            let receiver = d.receiver;
            let msg = d.msg;
            let read;
            let title = "Unknown";
            let name = "";
            if (d.readStatus) {
                read = "Read";
            } else {
                read = "Not Read";
            }

            for (let i = 0; i < resolvedData.length; i++) {
                let data = resolvedData[i][0];
                if (data.code === "00" && data.content.teacherId === receiver && data.content) {
                    title = data.content.title;
                    name = data.content.teacherName;
                    break;
                }
            }

            console.log(chatId);
            let postHistory = `
                <div class="msg" data-chatId="${chatId}">
                     <p id="name" >${count}. To, ${title} ${name}</p>
                     <p  id="content">${msg}</p>
                     <p  id="read">${read}</p><br>
                </div>          
            `;
            $('.history').append(postHistory);
        }
    });
}
$(document).ready(function (){
    $(document).on('click','.msg',function (){
        let chatId = $(this).attr('data-chatId')
        console.log('Clicked chatId:', chatId);
        localStorage.setItem('chatId',chatId);
    });
});

function deleteMessage(){
   let chatId = localStorage.getItem('chatId');
   const confirm = window.confirm('Are you sure to delete this message?');
   console.log(chatId);

   if(confirm === true){
       $.ajax({
           method:"DELETE",
           url:"http://localhost:8080/api/sc/studentChat/"+encodeURIComponent(chatId)+"/deleteStudentChat",
           async:true,
           success:function (data){
               if(data.code === "00"){
                   alert(data.message);
                   chatHistory = [];
                   getMessageHistory();
               }else if(data.code === "01"){
                   alert(data.message);
               }else {
                   alert('Something Went Wrong!');
               }
           }, error: function (xhr, error) {
               console.log("Error: " + error);
               alert("Message Delete fail !");
           }
       });
   }else {
       alert('Delete Cansel!');
   }
}

function goBack() {
    window.history.back();
}

function logOut(){
    const confirm = window.confirm('Are you sure to log out?');

    if(confirm === true){
        window.location.href = "http://localhost:8080/studentms/loginStudent";
    }else {
        alert('Cansel!');
    }
}
