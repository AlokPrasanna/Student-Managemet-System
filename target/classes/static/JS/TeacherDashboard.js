const teacherId = localStorage.getItem("teacherId");
localStorage.removeItem('studentId');
localStorage.removeItem('studentName');
/*let title;
let teacherName;*/
getTeacherDetails();
function getTeacherDetails(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/t1/teacher/getTeacherDetails/"+teacherId,
        async:true,
        success:function (data){
            if(data.code === "00"){
                let title = data.content.title;
                let teacherName = data.content.teacherName;
                console.log(title);
                console.log(teacherName);
                updateWelcomeMsg(title,teacherName);
            }
        }
    });
}

function updateWelcomeMsg(title,teacherName){
    const welcomeMsg = document.getElementById('welcomeMsg');
    welcomeMsg.innerHTML = `<h1>Welcome, ${title} ${teacherName}</h1>`;
}

let studentList = [];
function getStudentList(){
    studentList = [];
    localStorage.removeItem('studentId');
    localStorage.removeItem('studentName');
    $('#studentNameList').empty();
    let grade = $('#selectGrade').val();
    let classRoom = $('#selectClassRoom').val();
    if(grade==="null" || classRoom === "null"){
        alert('Select "Grade" and "Class" before click Get Student List Button!');
        return;
    }
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/s1/student/getStudents/grade="+encodeURIComponent(grade)+"/classRoom="+encodeURIComponent(classRoom),
        async: true,
        success:function (data){
            if(data.code === "00"){
                for(let s of data.content){
                    let studentName = s.student_name;
                    let studentId = s.student_id;

                    const student = {
                        studentId:studentId,
                        studentName:studentName
                    }
                    studentList.push(student);
                }
                showStudentList();
            }else if(data.code === "01"){
                alert('No students found!');
            }
        }, error: function (xhr, error) {
            console.log("Error: " + error);
            alert("Error!");
        }

    });
}
function showStudentList(){
    let dropdown = $("#studentListDropdown");

    dropdown.html("");

    let select = $("<div class='custom-dropdown-select'>Select Student</div>");
    dropdown.append(select);

    let optionsContainer = $("<div class='custom-dropdown-options'></div>");
    dropdown.append(optionsContainer);

    for(let r of studentList){
        let studentId = r.studentId;
        let studentName = r.studentName;

        let option = `<p data-studentId="${studentId}"data-studentname="${studentName}">${studentName}</p>`;
        optionsContainer.append(option);

    }

    $(document).ready(function (){
        $(document).on('click','.custom-dropdown-options p',function (){
            let studentId = $(this).attr('data-studentId');
            let studentName = $(this).attr('data-studentName');
            console.log(studentId);
            console.log(studentName);
            localStorage.setItem('studentId',studentId);
            localStorage.setItem('studentName',studentName);
            printName();
        });
    });

    select.click(function () {
        optionsContainer.toggle();
    });

    $(document).click(function (e) {
        if (!$(e.target).closest(".custom-dropdown").length) {
            optionsContainer.hide();
        }
    });
}

function printName(){
    $('#printName').empty()
    let name = localStorage.getItem('studentName');;
    let printName = `<p>${name}</p>`;
    $('#printName').append(printName);
}
function getMessageDetails(){
    let receiver = localStorage.getItem('studentId');
    let msg = $('#textMsg').val();
    console.log(receiver);

    if(!msg && receiver===null){
        alert('Filled all Required fields before click "Create New Chat" button.');
        return;
    }else if(!msg){
        alert('Please Type message before click "Create New Chat" button.');
        return;
    }else if(receiver===null){
        alert('Please select Student before click "Create new Chat" button.');
        return;
    }

    const confirm = window.confirm('Are you sure to send this message?');
    if(confirm===true){
        const saveData = {
            chatId:0,
            sender:teacherId,
            receiver:receiver,
            message:msg,
            readStatus:false
        }
        localStorage.setItem('saveData',JSON.stringify(saveData));

        $.ajax({
            method:"POST",
            contentType: "application/json",
            url: "http://localhost:8080/api/tc/teacher/teacherChat/saveTeacherChat",
            data:JSON.stringify(saveData),
            async:true,
            success:function (data){
                if(data.code === "00"){
                    let chatId = data.content;
                    sendMessageToReplyTable(chatId);
                    alert(data.message);
                }else {
                    alert('Message Invalid!');
                }
            }, error: function (xhr, error) {
                console.log("Error: " + error);
                alert("Error!");
            }
        });
    }else {
        alert('send message Cansel!');
    }
}
function sendMessageToReplyTable(chatId){
    const input = JSON.parse(localStorage.getItem('saveData'));
    input.senderTableChatId = chatId;
    input.receiverCanSee =true;
    $.ajax({
        method:"POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/rm/replyMessage/saveReplyMessage",
        data:JSON.stringify(input),
        async:true,
        success:function (data){
            if(data.code === "00"){
                alert(data.message);
            }else if(data.code === "04"){
                alert(data.message);
            }else if(data.code === "05"){
                alert(data.message);
            }
        },error: function (xhr, error) {
            console.log("Error: " + error);
            alert("Error");
        }
    });
}


function teacherAnnouncementPage(){
    window.location.href = "http://localhost:8080/studentms/teacherAnnouncements";
}

function teacherTimeTablePage(){
    window.location.href = "http://localhost:8080/studentms/teacherTimetable";
}
function  goToTeacherInboxPage(){
    window.location.href = "http://localhost:8080/studentms/teacherInboxPage";
}
function  goToMessageHistoryPage(){
    window.location.href = "http://localhost:8080/studentms/TeacherMessgeHistorypage";
}
function logOut(){
    const confirm = window.confirm('Are you sure to log out?');

    if(confirm === true){
        window.location.href = "http://localhost:8080/studentms/teacherLogin";
    }else {
        alert('Cansel!');
    }
}