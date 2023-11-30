function getTeacherDetails(){
    let teacherId = $('#teacherId').val();
    if(!teacherId){
        alert('Please Enter teacher ID!');
        return;
    }

    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/t1/teacher/getTeacherDetails/"+teacherId,
        async:true,
        success:function (data){
            if(data.code ==="00"){
                displayTeacherDetails(data.content);
            }
            else if(data.code ==="01"){
                let notFindTeacher = `<p class="errorMessage">There is no teacher from that ID</p>`;
                $('#teacherDetails').html(notFindTeacher);
            }
        },
        error: function (xhr, error) {
            console.log("Error: " + error);
            alert("Failed to fetch teacher details!");
        }
    })
}
function displayTeacherDetails(data){
    let detail =`
        <h1>Teacher Details</h1>
        <p><span>teacher ID: </span> ${data.teacherId}</p>
        <p><span>Name: </span>${data.title} ${data.teacherName}</p>
        <p><span>Email Address: </span> ${data.emailAddress}</p>
        <p><span>Contact Number: </span> ${data.contactNumber}</p>
        <p><span>Subject: </span> ${data.subject}</p>
        <p><span>DOB: </span> ${data.birthday}</p>
        <p><span>Gender: </span> ${data.gender}</p>
    `;
    $('#teacherDetails').html(detail);
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