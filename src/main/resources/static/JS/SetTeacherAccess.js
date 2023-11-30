getAllNotApprovedTeachers();
let teacherList = [];
function getAllNotApprovedTeachers(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/t1/teacher/getAllNotApprovedTeachers",
        async:true,
        success:function (data){
            if(data.code ==="00") {
                $('#teacherTable').empty();
                for (let teacher of data.content) {
                    let teacherId = teacher.teacherId;
                    let title = teacher.title;
                    let name = teacher.teacherName;
                    let email = teacher.emailAddress;
                    let tel = teacher.contactNumber;
                    let subject = teacher.subject;
                    let birthday = teacher.birthday;
                    let gender = teacher.gender;
                    let teacherName = title+" "+name;

                    let row = `<tr><td>${teacherId}</td><td>${teacherName}</td><td>${email}</td><td>${tel}</td><td>${subject}</td><td>${birthday}</td><td>${gender}</td></tr>`;
                    $('#teacherTable').append(row);
                    console.log(row);

                    teacherList.push(teacher);
                }
            }else if(data.code ==="01"){
                alert(data.message);
            }
        },error:function (xhr, error) {
            console.log("Error: " + error);
            alert("Approved Fail!");
        }
    });
}

$(document).ready(function (){
    $(document).on('click','#teacherTable tr',function (){

        $('#teacherTable tr').removeClass('selected');

        $(this).addClass('selected');

        let teacherId = $(this).find('td:eq(0)').text();
        let teacherName = $(this).find('td:eq(1)').text();
        let email =$(this).find('td:eq(2)').text();
        let tel = $(this).find('td:eq(3)').text();
        let subject = $(this).find('td:eq(4)').text();
        let birthday = $(this).find('td:eq(5)').text();
        let gender = $(this).find('td:eq(6)').text();

        $('#teacherId').val(teacherId);
        $('#name').val(teacherName);
        $('#email').val(email);
        $('#tel').val(tel);
        $('#subject').val(subject);
        $('#birthday').val(birthday);
        $('#gender').val(gender);

        localStorage.setItem('teacherId',teacherId);
    });
});
function giveAccess(){
    let teacherId = localStorage.getItem('teacherId');
    if(!teacherId){
        alert('Please select teacher before click "Give Access" button!');
        return;
    }
    const confirm = window.confirm("Are you sure to proceed with the update?");
    const input = {};
    if (confirm === true){
        for(let t of teacherList){
            if(t.teacherId === teacherId){
                input.teacherId = t.teacherId;
                input.title = t.title;
                input.teacherName = t.teacherName;
                input.emailAddress = t.emailAddress;
                input.contactNumber = t.contactNumber;
                input.subject = t.subject;
                input.birthday = t.birthday;
                input.gender = t.gender;
                input.accessibility = true;
                input.password = t.password;
            }
        }
        console.log(input);
        $.ajax({
            method: "PUT",
            contentType: "application/json",
            url: "http://localhost:8080/api/t1/teacher/teacherUpdate",
            data:JSON.stringify(input),
            async: true,
            success:function (data){
                if(data.code === "00"){
                   alert(data.message);
                    teacherList = [];
                   getAllNotApprovedTeachers();
                }else if (data.code === "01"){
                    alert(data.message);
                }else{
                    alert('something went wrong!');
                }
            },error: function (xhr, status, error) {
                console.log("Error: " + error);
                alert("Error");
            }
        });
    }else {
        alert('Cansel!')
    }
}

function goBack(){
    window.history.back();
}
function logOut(){
    const confirm = window.confirm('Are you sure to log out?');

    if(confirm === true){
        window.location.href = "http://localhost:8080/studentms/adminLogin";
    }else {
        alert('Cansel!');
    }
}