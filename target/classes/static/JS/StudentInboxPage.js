
let receiver = localStorage.getItem('studentID');
let msgList = [];
let uniqueSenders = [];
getChatList();
function getChatList(){
    $.ajax({
        method:"GET",
        url: "http://localhost:8080/api/rm/replyMessage/"+encodeURIComponent(receiver)+"/getReplyMessage",
        async:true,
        success:function (data){
            if(data.code === "00"){
                let count =0;
                for(let d of data.content){
                    let chatId = d.chatId;
                    let sender = d.sender;
                    let receiver = d.receiver;
                    let senderTableChatId = d.senderTableChatId;
                    let message = d.message;
                    let receiverCanSee = d.receiverCanSee;

                    count++;
                    let chatMessage = {
                        count:count,
                        chatId: chatId,
                        sender:sender,
                        receiver:receiver,
                        senderTableChatId:senderTableChatId,
                        message:message,
                        receiverCanSee:receiverCanSee
                    };
                    msgList.push(chatMessage);
                }
                identifyTeacherOrStudent()

            }else if(data.code === "01"){
                alert(data.message);
                console.log(data.message);
            }else{
                alert('Something went Wrong!');
                console.log('Something went Wrong!');
            }
        },error: function (xhr, status, error) {
            console.log("Error: " + error);
            alert("Error!");
        }
    });
}
function identifyTeacherOrStudent(){
    for(let i of msgList){
        if(i.sender.charAt(0)==="T" && !uniqueSenders.includes(i.sender)){
            uniqueSenders.push(i.sender);
            teacherChatList(i.count,i.sender);
        }
        if(i.sender.charAt(0) === "S" && !uniqueSenders.includes(i.sender)){
            uniqueSenders.push(i.sender);
            studentChatList(i.count,i.sender);
        }
    }
}
function studentChatList(count,sender){

    $.ajax({
        method:"GET",
        url: "http://localhost:8080/api/s1/student/getStudentDetails/"+encodeURIComponent(sender),
        async: true,
        success:function (data){
            if(data.code === "00"){
                let name = data.content.student_name;
                let grade = data.content.grade;
                let classRoom = data.content.studentClass;
                let list = `
                    <p data-sender="${sender}" id="chatList">${count}. ${name}<br>${grade}-${classRoom}</p>
                `;
                $('#chatList').append(list);
            }
        }
    });

}
function teacherChatList(count,sender){
    $.ajax({
        method:"GET",
        url: "http://localhost:8080/api/t1/teacher/getTeacherDetails/"+encodeURIComponent(sender),
        async: true,
        success:function (data){
            if(data.code === "00"){
                let title = data.content.title;
                let name = data.content.teacherName;
                let list = `
                    <p data-sender="${sender}" id="chatList">${count}. ${title} ${name}</p>
                `;
                $('#chatList').append(list);
            }
        }
    });

}

$(document).ready(function () {
    $(document).on('click', '.chatList p', function () {
        let sender = $(this).data('sender');
        getChatBySender(sender);
        localStorage.setItem('sender1',sender);
    });
});

function getChatBySender(sender){
    let message = [];
    let readStatus = [];
    for (let id of msgList){
        if(id.sender === sender){
            message.push(id.message);
            readStatus.push({ senderTableChatId: id.senderTableChatId, sender: id.sender });
        }
    }
    console.log(message);
    console.log(readStatus);
    loadMessageList(message);
    setReadStatus(readStatus);
}
function loadMessageList(message){
    $('#messageListArea').empty();
    for(let id of message){
        let messages = `
            <p id="messageListArea">${id}</p>
        `;
        $('#messageListArea').append(messages);

    }
}
function setReadStatus(readStatus){
    for(let id of readStatus)
        if(id.sender.charAt(0) === "S"){
            $.ajax({
                method:"PATCH",
                url: "http://localhost:8080/api/sc/studentChat/"+encodeURIComponent(id.senderTableChatId)+"/updateReadStatus",
                async:true,
                success:function (data){
                    if(data.code === "00"){
                        console.log(data.code);
                        console.log(data.message);
                    }else if(data.code === "04"){
                        console.log(data.code);
                        console.log(data.message);
                    }else if(data.code === "07"){
                        console.log(data.code);
                        console.log(data.message);
                    }else if(data.code === "05"){
                        console.log(data.code);
                        console.log(data.message);
                    }
                },error: function (xhr, status, error) {
                    console.log("Error: " + error);
                    alert("Error!");
                }
            });
        }else if(id.sender.charAt(0) === "T"){
            $.ajax({
                method:"PATCH",
                url: "http://localhost:8080/api/tc/teacher/teacherChat/"+encodeURIComponent(id.senderTableChatId)+"/updateReadStatus",
                async:true,
                success:function (data){
                    if(data.code === "00"){
                        console.log(data.code);
                        console.log(data.message);
                    }else if(data.code === "04"){
                        console.log(data.code);
                        console.log(data.message);
                    }else if(data.code === "07"){
                        console.log(data.code);
                        console.log(data.message);
                    }else if(data.code === "05"){
                        console.log(data.code);
                        console.log(data.message);
                    }
                },error: function (xhr, status, error) {
                    console.log("Error: " + error);
                    alert("Error!");
                }
            });
        }else{
            console.log("Id Invalid!")
        }

}

function saveMessageInStudentChatTable(){
    const sender = localStorage.getItem('sender1');
    let teacherMessage = $('#replyMsg').val();
    if(!teacherMessage){
        alert('Please write Message before click "Send" button!');
        return;
    }
    const confirm = window.confirm("Are You sure to send Message?");
    if(confirm === true){
        const input = {
            chatId:0,
            sender:receiver,
            receiver:sender,
            message:teacherMessage,
            readStatus:false
        }
        localStorage.setItem('input',JSON.stringify(input));

        $.ajax({
            method:"POST",
            contentType: "application/json",
            url:"http://localhost:8080/api/sc/studentChat/saveStudentChat",
            data:JSON.stringify(input),
            async:true,
            success:function (data){
                if(data.code === "00"){
                    alert(data.message);
                    console.log(data.message);
                    let chatId = data.content;
                    sendMessageStudent(chatId);
                }else if(data.code === "04"){
                    alert(data.message);
                    console.log(data.message);
                }
            },error: function (xhr, status, error) {
                console.log("Error: " + error);
                alert("Error!");
            }
        });
    }else{
        alert("Message send Cansel!");
    }

}
function sendMessageStudent(chatId){
    const input = JSON.parse(localStorage.getItem('input'));
    input.senderTableChatId = chatId;
    input.receiverCanSee =true;
    console.log(input);
    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/rm/replyMessage/saveReplyMessage",
        data:JSON.stringify(input),
        async:true,
        success:function (data){
            if (data.code === "00") {
                alert(data.message);
            }else if(data.code === "01"){
                alert(data.message);
            }else if(data.code === "03"){
                alert(data.message);
            }else if(data.code === "05"){
                alert(data.message);
            }
        },error: function (xhr, status, error) {
            console.log("Error: " + error);
            alert("Error!");
        }
    });
}

function goBack(){
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
