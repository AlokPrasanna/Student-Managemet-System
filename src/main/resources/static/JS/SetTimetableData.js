let teacherDataFetched = false;
function getTeacherData(){

    if(teacherDataFetched){
        return;
    }
    let teacherId = $('#teacherId').val();
    if(!teacherId){
        alert('Enter Teacher Id');
        return;
    }
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/t1/teacher/getTeacherDetails/"+teacherId,
        async:true,
        success:function (data){
            if(data.code === "00"){
                let teacherName = data.content.teacherName;
                let subject = data.content.subject;

                let showDetails = `
                    <p class="teacherDetails" id="teacherData">Teacher Name: ${teacherName}</p><br>
                    <p class="teacherDetails" id="teacherData">Subject: ${subject}</p>
                `;
                $('.teacherDetails').append(showDetails);

                teacherDataFetched = true;

                localStorage.setItem('teacherId',teacherId);
                localStorage.setItem('teacherName',teacherName);
                localStorage.setItem('subject',subject);

            }else if (data.code === "01"){
                alert(data.message);
            }else {
                alert('Something Went wrong');
            }
        },
        error: function (xhr, error) {
            console.log("Error: " + error);
            alert("Error");
        }
    });
}
function  saveTableData(){
    let id = localStorage.getItem('teacherId');
    let name = localStorage.getItem('teacherName');
    let sub = localStorage.getItem('subject');
    let day = $('#selectDay').val();
    let time = $('#selectTime').val();
    let grade = $('#selectGrade').val();
    let classRoom = $('#selectClassRoom').val();

    console.log('Day:', day);
    console.log('Time:', time);
    console.log('Grade:', grade);
    console.log('ClassRoom:', classRoom);

    if(!id || !name || ! sub){
        alert('Enter Teacher Id First');
        return;
    }

    if(day==='null' || time==='null' || grade==='null' || classRoom==='null'){
        alert('Select required filed before click "Save" button');
        return;
    }

    const input ={
        rowId:0,
        teacherId:id,
        teacherName:name,
        subject:sub,
        day:day,
        time:time,
        grade:grade,
        classRoom:classRoom
    }

    const confirm = window.confirm("Are you sure to save data?");

    if(confirm === true){
        $.ajax({
            method:"POST",
            contentType: "application/json",
            url: "http://localhost:8080/api/td/timetableData/saveTimetableData",
            data:JSON.stringify(input),
            async:true,
            success:function (data){
                if(data.code === "00"){
                    alert(data.message);
                }else if(data.code === "06"){
                    alert(data.message);
                }else if(data.code === "07"){
                    alert(data.message);
                }else {
                    alert('Something Went Wrong!');
                }
            },error:function (xhr, error) {
                console.log("Error: " + error);
                alert("Error!");
            }
        });
    }else {
        alert('Saving Cansel!');
    }
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
