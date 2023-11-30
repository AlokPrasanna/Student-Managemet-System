const teacherId = localStorage.getItem("teacherId");

function updateTeacherDetails(){
    let title = $('#title').val();
    let teacherName = $('#teacherName').val();
    let email = $('#emailAddress').val();
    let contactNumber = $('#tel').val();
    let subject = $('#selectSubject').val();
    let birthday = $('#birthday').val();
    let gender = $('#gender').val();

    const confirm = window.confirm("Are you sure to proceed with the update?");
    if(confirm === true){
        $.ajax({
            method:"GET",
            url:"http://localhost:8080/api/t1/teacher/getTeacherDetails/"+teacherId,
            async:true,
            success:function (data){
                if(title ===""){
                    title = data.content.title;
                }
                if(teacherName ===""){
                    teacherName = data.content.teacherName;
                }
                if(email ===""){
                    email = data.content.emailAddress;
                }
                if(contactNumber ===""){
                    contactNumber = data.content.contactNumber;
                }
                if(subject ===""){
                    subject = data.content.subject;
                }
                if(birthday ===""){
                    birthday = data.content.birthday;
                }
                if(gender ===""){
                    gender = data.content.gender;
                }
                const inputData3 ={
                    teacherId:data.content.teacherId,
                    title:title,
                    teacherName: teacherName,
                    emailAddress: email,
                    contactNumber:contactNumber,
                    subject:subject,
                    birthday:birthday,
                    gender:gender,
                    accessibility: data.content.accessibility,
                    password:data.content.password
                }
                $.ajax({
                    method:"PUT",
                    contentType:"application/json",
                    url:"http://localhost:8080/api/t1/teacher/teacherUpdate",
                    data:JSON.stringify(inputData3),
                    async:true,
                    success:function (data){
                        if(data.code ==="00"){
                            alert("Updated Successful!");
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
                alert("Error!");
            }
        });
    }else {
        alert("Cansel Success");
    }
}

function logOut(){
    const confirm = window.confirm('Are you sure to log out?');

    if(confirm === true){
        window.location.href = "http://localhost:8080/studentms/teacherLogin";
    }else {
        alert('Cansel!');
    }
}

function goBack(){
    window.history.back();
}