const teacherId = localStorage.getItem("teacherId");
getTeacherDetails();
function getTeacherDetails(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/t1/teacher/getTeacherDetails/"+teacherId,
        async:true,
        success:function (data){
            let title = data.content.title;
            let name = data.content.teacherName;
            let email = data.content.emailAddress;
            let contactNumber = data.content.contactNumber;
            let subject = data.content.subject;
            let birthday = data.content.birthday;
            let gender = data.content.gender;
            document.getElementById("name").textContent = title + " " + name;
            document.getElementById("email").textContent = email;
            document.getElementById("contactNumber").textContent = contactNumber;
            document.getElementById("subject").textContent = subject;
            document.getElementById("birthday").textContent = birthday;
            document.getElementById("gender").textContent = gender;

        },error: function (xhr, status, error) {
            console.log("Error: " + error);
            alert("Login Failed");
        }
    });
}

function goBack(){
    window.history.back();
}
function logOut(){
    const confirm = window.confirm('Are you sure to log out?');

    if(confirm === true){
        window.location.href = "http://localhost:8080/studentms/teacherLogin";
    }else {
        alert('Cansel!');
    }
}