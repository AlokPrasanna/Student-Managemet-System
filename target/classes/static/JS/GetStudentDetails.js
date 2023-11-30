function getStudentDetails(){
    let studentId = $('#studentId').val();
    if(!studentId){
        alert('Please Enter Student ID!');
        return;
    }

    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/s1/student/getStudentDetails/"+studentId,
        async:true,
        success:function (data){
            if(data.code ==="00"){
                displayStudentDetails(data.content);
            }
            else if(data.code ==="01"){
                let notFindStudent = `<p class="errorMessage">There is no student from that ID</p>`;
                $('#studentDetails').html(notFindStudent);
            }
        },
        error: function (xhr, error) {
            console.log("Error: " + error);
            alert("Failed to fetch student details!");
        }
    })
}
function displayStudentDetails(data){
    let detail =`
        <h1>Student Details</h1>
        <p><span>Student ID: </span> ${data.student_id}</p>
        <p><span>Name: </span> ${data.student_name}</p>
        <p><span>Grade: </span> ${data.grade}</p>
        <p><span>Class: </span> ${data.studentClass}</p>
        <p><span>Email Address: </span> ${data.emailAddress}</p>
        <p><span>DOB: </span> ${data.birthday}</p>
        <p><span>Gender: </span> ${data.gender}</p>
    `;
    $('#studentDetails').html(detail);
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