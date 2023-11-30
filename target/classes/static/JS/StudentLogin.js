
function loginStudent(){
    $('#password').empty();
    let studentId = $('#studentId').val();
    let password = $('#password').val();
    if (!studentId || !password) {
        alert("Please fill in all required fields.");
        return;
    }
    const inputData2 = {
        student_id: studentId,
        password: password
    }
    $.ajax({
       method:"POST",
       contentType:"application/json",
       url:"http://localhost:8080/api/s1/student/login",
       async:true,
       data:JSON.stringify(inputData2),
       success:function (data){
            if(data.code ==="02"){
                alert(data.message);
           }
           else if(data.code ==="01") {
                alert(data.message);
           }
           else if(data.code ==="00"){
               localStorage.setItem("studentID",studentId);
               window.location.href = "http://localhost:8080/studentms/StudentDashboard";
           }
           else if(data.code ==="04"){
               window.location.href = "http://localhost:8080/studentms/notApproved";
           }
           else {
               alert(data.message);
           }
       },error:function (xhr, error) {
            console.log("Error: " + error);
            alert("Login Failed");
       }
   });
}
function goBack(){
    window.history.back();
}


