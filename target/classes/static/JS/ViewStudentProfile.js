const studentID = localStorage.getItem("studentID");

function getStudentDetails(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/s1/student/getStudentDetails/"+studentID,
        async:true,
        success:function (data){
             let name = data.content.student_name;
             let email = data.content.emailAddress;
             let grade = data.content.grade;
             let studentClass = data.content.studentClass;
             let birthday = data.content.birthday;
             let gender = data.content.gender;

             document.getElementById("name").textContent = name;
             document.getElementById("email").textContent = email;
             document.getElementById("grade").textContent = grade;
             document.getElementById("studentClass").textContent = studentClass;
             document.getElementById("birthday").textContent = birthday;
             document.getElementById("gender").textContent = gender;

        },error: function (xhr, status, error) {
            console.log("Error: " + error);
            alert("Login Failed");
        }
    });
}
window.onload = function () {
    getStudentDetails();
};
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