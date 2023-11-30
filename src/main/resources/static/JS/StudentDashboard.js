let studentName;
const studentID = localStorage.getItem("studentID");
function  getStudentDetails(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/s1/student/getStudentDetails/"+studentID,
        async:true,
        success: function (data){
            if(data.code ==="00"){
                studentName = data.content.student_name;
                let grade = data.content.grade;
                let classRoom = data.content.studentClass;
                console.log(data.code);
                console.log(grade);
                console.log(classRoom);
                updateWelcomeMessage();

                localStorage.setItem('grade',grade);
                localStorage.setItem('classRoom',classRoom);
            }
        }
    });
}
function updateWelcomeMessage() {
    const welcomeMsg = document.getElementById('welcomeMsg');
    welcomeMsg.innerHTML = `<h2>Welcome, ${studentName}</h2>`;
}
function getMessageDetails(){
    let subject = $('#selectSubject').val();
    let grade = $('#selectGrade').val();
    let classRoom = $('#selectClassRoom').val();
    let msg = $('#textMsg').val();


    if(subject === "none" || grade === "none" || classRoom === "none" || !msg){
        alert('Fill all related field!');
        return;
    }else{
        const studentInput = {
            subject: subject,
            grade:grade,
            classRoom:classRoom,
            msg:msg
        }
        getTeacherId(studentInput);
    }
}
function getTeacherId(studentInput){
    let subject = studentInput.subject;
    let grade = studentInput.grade;
    let classRoom = studentInput.classRoom;

    $.ajax({
        method:"GET",
        url: "http://localhost:8080/api/td/timetableData/getTeacherIdByTimetableData/"+encodeURIComponent(subject) + "/" + encodeURIComponent(grade) + "/" + encodeURIComponent(classRoom),
        async: true,
        success:function (data){
            if(data.code === "00"){
                let teacherId = data.content;
                console.log(teacherId);
                saveStudentChat(studentInput,teacherId);

            }else if(data.code === "01"){
                alert(data.message);
            }else {
                alert('Something Went Wrong');
            }
        },error: function (xhr, error) {
            console.log("Error: " + error);
            alert("Error!");
        }
    });
}

function saveStudentChat(studentInput,teacherId){
    let teacherID = teacherId;
    let studentId = studentID;
    let msg = studentInput.msg;
    let teacherRead = false;
    console.log(teacherID);
    console.log(studentId);
    console.log(msg);
    console.log(teacherRead);
    const saveData = {
        chatId:0,
        receiver:teacherID,
        sender:studentId,
        message:msg,
        readStatus:false
    }
    localStorage.setItem('saveData',JSON.stringify(saveData));
    $.ajax({
        method:"POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/sc/studentChat/saveStudentChat",
        data:JSON.stringify(saveData),
        async:true,
        success:function (data){
            if(data.code === "00"){
                alert(data.message);
                let chatId = data.content;
                getStudentMsgTableId(chatId);
                console.log(data.content);
            }else if(data.code === "04"){
                alert(data.message);
            }else if (data.code === "07") {
                alert(data.message);
            }
        },error: function (xhr, error) {
            console.log("Error: " + error);
            alert("Error");
        }
    });
}
function getStudentMsgTableId(chatId){
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

window.onload = function () {
    getStudentDetails();
}
function studentAnouncementPage(){
    window.location.href = "http://localhost:8080/studentms/getStudentAnnouncements";
}
function studentTimeTablePage(){
    window.location.href = "http://localhost:8080/studentms/studentTimetable";
}
function goToInboxPage(){
    window.location.href = "http://localhost:8080/studentms/studentInboxPage";
}
function  goToMessageHistoryPage(){
    window.location.href = "http://localhost:8080/studentms/StudentMessageHistorypage";
}
function logOut(){
    const confirm = window.confirm('Are you sure to log out?');

    if(confirm === true){
        window.location.href = "http://localhost:8080/studentms/loginStudent";
    }else {
        alert('Cansel!');
    }
}
