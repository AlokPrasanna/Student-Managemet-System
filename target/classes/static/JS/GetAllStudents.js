getAllStudents();
function getAllStudents(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/s1/student/getAllStudents",
        async:true,
        success:function (data){
            if(data.code ==="00") {
                $('#studentDetailsTable').empty();
                let count =0;
                for (let student of data.content) {
                    let studentId = student.student_id;
                    let name = student.student_name;
                    let email = student.emailAddress;
                    let grade = student.grade;
                    let studentClass = student.studentClass;
                    let birthday = student.birthday;
                    let gender = student.gender;

                    let row = `<tr><td>${studentId}</td><td>${name}</td><td>${email}</td><td>${grade}</td><td>${studentClass}</td><td>${birthday}</td><td>${gender}</td></tr>`;
                    $('#studentDetailsTable').append(row);
                    count++;
                    console.log(row);
                }
                let studentCount = `<p>Total Student Count: ${count} </p>`;
                $('#studentsCount').append(studentCount);
            }else if(data.code ==="01"){
                alert(data.message);
            }
        },error:function (xhr, error) {
            console.log("Error: " + error);
            alert("Student Load fail !");
        }

    });

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