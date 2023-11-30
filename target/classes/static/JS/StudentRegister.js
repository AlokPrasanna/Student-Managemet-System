function registerStudent(){
    let studentId =$('#studentId').val();
    let studentName =$('#studentName').val();
    let grade =$('#grade').val();
    let studentClass =$('#studentClass').val();
    let email =$('#emailAddress').val();
    let birthday =$('#birthday').val();
    let gender =$('#gender').val();
    let password =$('#password').val();
    if (!studentId || !studentName || grade=='none' || studentClass=='none' || !email || !birthday || !gender || !password || !accessibility) {
        alert("Please fill in all required fields.");
        return;
    }
    const confirm = window.confirm("Are you sure to continue?");
    if(confirm === true){
        const inputData1 = {
            student_id: studentId,
            student_name: studentName,
            grade: grade,
            studentClass: studentClass,
            emailAddress: email,
            birthday: birthday,
            gender: gender,
            password: password,
            accessibility: false
        };
        $.ajax({
            method:"POST",
            contentType:"application/json",
            url: "http://localhost:8080/api/s1/student/reg",
            async:true,
            data:JSON.stringify(inputData1),
            success:function (data){
                if(data.code === "00"){
                    alert(data.message);
                }
                else if(data.code === "06"){
                    alert(data.message);
                }
                else if(data.code === "07"){
                    alert(data.message);
                }
                else{
                    alert("Something went wrong!");
                }
            },error: function (xhr, error) {
                console.log("Error: " + error);
                alert("Registration Failed");
            }
        });
    }else {
        alert("Cansel Registration!");
    }
}
function goBack(){
    window.history.back();
}
