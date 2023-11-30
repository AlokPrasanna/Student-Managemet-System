getAllTeachers();
function getAllTeachers(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/t1/teacher/getAllTeachers",
        async:true,
        success:function (data){
            if(data.code ==="00") {
                $('#teacherDetailsTable').empty();
                let count =0;
                for (let teacher of data.content) {
                    let teacherId = teacher.teacherId;
                    let title = teacher.title;
                    let name = teacher.teacherName;
                    let email = teacher.emailAddress;
                    let tel = teacher.contactNumber;
                    let subject = teacher.subject;
                    let birthday = teacher.birthday;
                    let gender = teacher.gender;

                    let newName = title+" "+name;

                    let row = `<tr><td>${teacherId}</td><td>${newName}</td><td>${email}</td><td>${tel}</td><td>${subject}</td><td>${birthday}</td><td>${gender}</td></tr>`;
                    $('#teacherDetailsTable').append(row);
                    count++;
                    console.log(row);
                }
                let teacherCount = `<p>Total Teacher Count: ${count} </p>`;
                $('#teachersCount').append(teacherCount);
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