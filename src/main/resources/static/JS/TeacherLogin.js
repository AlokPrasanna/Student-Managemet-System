function loginTeacher(){
    let teacherId = $('#teacherId').val();
    let password = $('#password').val();

    if(!teacherId || !password){
        alert('Please fill Required fields');
        return;
    }
    const input = {
        teacherId:teacherId,
        password:password
    }

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/t1/teacher/teacherLogin",
        data:JSON.stringify(input),
        async:true,
        success: function (data){
            if(data.code === "00"){
                localStorage.setItem("teacherId",data.content.teacherId)
                window.location.href = "http://localhost:8080/studentms/teacherDashboard";
            }else if(data.code === "07"){
                window.location.href = "http://localhost:8080/studentms/teacherNotApproved";
            }else if(data.code === "02"){
                alert(data.message);
            }else if(data.code === "01"){
                alert(data.message);
            }else {
                alert("Something Went Wrong!");
            }
        },
        error: function (xhr, error) {
            console.log("Error: " + error);
            alert("Error");
        }

    })
}
function goBack(){
    window.history.back();
}