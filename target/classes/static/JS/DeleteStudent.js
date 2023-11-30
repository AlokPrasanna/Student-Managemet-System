getAllStudents();
function getAllStudents(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/s1/student/getAllStudents",
        async:true,
        success:function (data){
            if(data.code ==="00") {
                $('#studentTableDelete').empty();
                for (let student of data.content) {
                    let studentId = student.student_id;
                    let name = student.student_name;
                    let email = student.emailAddress;
                    let grade = student.grade;
                    let studentClass = student.studentClass;
                    let birthday = student.birthday;
                    let gender = student.gender;

                    let row = `<tr><td>${studentId}</td><td>${name}</td><td>${email}</td><td>${grade}</td><td>${studentClass}</td><td>${birthday}</td><td>${gender}</td></tr>`;
                    $('#studentTableDelete').append(row);
                    console.log(row);
                }
            }else if(data.code ==="01"){
                alert(data.message);
            }
        },error:function (xhr, error) {
            console.log("Error: " + error);
            alert("Student Load fail !");
        }

    });

}
function deleteStudent(){
    const studentId = localStorage.getItem('studentID');

    const confirm = window.confirm('Are you sure to detele student? ');
    if(confirm ===true){
        $.ajax({
            method: "GET",
            url:"http://localhost:8080/api/s1/student/getStudentDetails/"+studentId,
            async: true,
            success:function (data){
                let studentID = data.content.student_id;
                let  name = data.content.student_name;
                let email = data.content.emailAddress;
                let grade = data.content.grade;
                let studentClass = data.content.studentClass;
                let birthday = data.content.birthday;
                let gender = data.content.gender;
                let accessibility = data.content.accessibility;
                let password = data.content.password;
                const input={
                    student_id:studentID,
                    student_name: name,
                    emailAddress: email,
                    grade:grade,
                    studentClass:studentClass,
                    birthday:birthday,
                    gender:gender,
                    accessibility:accessibility,
                    password:data.content.password
                }
                $.ajax({
                    method:"DELETE",
                    contentType:"application/json",
                    url:"http://localhost:8080/api/s1/student/deleteStudent",
                    async:true,
                    data:JSON.stringify(input),
                    success:function (data){
                        if(data.code ==="00"){
                            alert(data.message);
                        }
                        getAllStudents();
                    },error: function (xhr, status, error) {
                        console.log("Error: " + error);
                        alert("Student Not Deleted");
                    }
                })
            },error: function (xhr, status, error) {
                console.log("Error: " + error);
                alert("Something went wrong");
            }
        });

    }else {
        alert('Delete Cansel!')
    }
}

$(document).ready(function (){
    $(document).on('click','#studentTableDelete tr', function (){
        $('#studentTable tr').removeClass('selected');

        $(this).addClass('selected');

        let studentID = $(this).find('td:eq(0)').text();
        let name = $(this).find('td:eq(1)').text();
        let email =$(this).find('td:eq(2)').text();
        let grade = $(this).find('td:eq(3)').text();
        let studentClass = $(this).find('td:eq(4)').text();
        let birthday = $(this).find('td:eq(5)').text();
        let gender = $(this).find('td:eq(6)').text();

        $('#studentId').val(studentID);
        $('#name').val(name);
        $('#email').val(email);
        $('#grade').val(grade);
        $('#studentClass').val(studentClass);
        $('#birthday').val(birthday);
        $('#gender').val(gender);

        localStorage.setItem('studentID',studentID);

    });
});

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