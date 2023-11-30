const studentID = localStorage.getItem("studentID");
function updateStudentDetails(){
    let name = $('#studentName').val();
    let email = $('#email').val();
    let grade = $('#grade').val();
    let studentClass = $('#studentClass').val();
    let birthday = $('#birthday').val();
    let gender = $('#gender').val();

    const confirm = window.confirm("Are you sure to proceed with the update?");

    if(confirm === true){
        $.ajax({
            method:"GET",
            url:"http://localhost:8080/api/s1/student/getStudentDetails/"+studentID,
            async:true,
            success:function (data){
                if(name ===""){
                    name = data.content.student_name;
                }
                if(email ===""){
                    email = data.content.emailAddress;
                }
                if(grade ===""){
                    grade = data.content.grade;
                }
                if(studentClass ===""){
                    studentClass = data.content.studentClass;
                }
                if(birthday ===""){
                    birthday = data.content.birthday;
                }
                if(gender ===""){
                    gender = data.content.gender;
                }
                const inputData3 ={
                    student_id:data.content.student_id,
                    student_name: name,
                    emailAddress: email,
                    grade:grade,
                    studentClass:studentClass,
                    birthday:birthday,
                    gender:gender,
                    accessibility: data.content.accessibility,
                    password:data.content.password
                }
                $.ajax({
                    method:"PUT",
                    contentType:"application/json",
                    url:"http://localhost:8080/api/s1/student/update",
                    data:JSON.stringify(inputData3),
                    async:true,
                    success:function (data){
                        if(data.code ==="00"){
                            alert("Updated Successful")
                        }else if(data.code === "01"){
                            alert(data.message);
                        }
                    },error: function (xhr, status, error) {
                        console.log("Error: " + error);
                        alert("Something went wrong");
                    }

                })

            },error: function (xhr, status, error) {
                console.log("Error: " + error);
                alert("Login Failed");
            }
        })
    }else {
        alert("Cansel Success");
    }

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