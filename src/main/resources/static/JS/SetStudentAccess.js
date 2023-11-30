getAllNotApprovedStudent();
function getAllNotApprovedStudent(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/s1/student/getAllNotApprovedStudents",
        async:true,
        success:function (data){
            if(data.code ==="00") {
                $('#studentTable').empty();
                for (let student of data.content) {
                    let studentId = student.student_id;
                    let name = student.student_name;
                    let email = student.emailAddress;
                    let grade = student.grade;
                    let studentClass = student.studentClass;
                    let birthday = student.birthday;
                    let gender = student.gender;

                    let row = `<tr><td>${studentId}</td><td>${name}</td><td>${email}</td><td>${grade}</td><td>${studentClass}</td><td>${birthday}</td><td>${gender}</td></tr>`;
                    $('#studentTable').append(row);
                    console.log(row);
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
function giveAccess(){
    let studentId = localStorage.getItem('studentID');

    const confirm = window.confirm("Are you sure to proceed with the update?");

    if(confirm === true){
        $.ajax({
            method:"GET",
            url:"http://localhost:8080/api/s1/student/getStudentDetails/"+studentId,
            async:true,
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
                const inputData ={
                    student_id:studentID,
                    student_name: name,
                    emailAddress: email,
                    grade:grade,
                    studentClass:studentClass,
                    birthday:birthday,
                    gender:gender,
                    accessibility:true,
                    password:data.content.password
                }
                $.ajax({
                    method:"PUT",
                    contentType:"application/json",
                    url:"http://localhost:8080/api/s1/student/update",
                    data:JSON.stringify(inputData),
                    async:true,
                    success:function (data){
                        if(data.code ==="00"){
                            alert("The student was granted access!");
                            getAllNotApprovedStudent();
                        }
                    },error: function (xhr, status, error) {
                        console.log("Error: " + error);
                        alert("Something went wrong");
                    }

                });

            },error: function (xhr, status, error) {
                console.log("Error: " + error);
                alert("Error");
            }
        });
    }else {
        alert("Cansel Success");
    }
}

$(document).ready(function (){
    $(document).on('click','#studentTable tr',function (){

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

