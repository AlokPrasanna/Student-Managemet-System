const teacherId = localStorage.getItem('teacherId');
getTeacherTimetableData();
function getTeacherTimetableData(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/td/timetableData/getTeacherTimetableData/"+teacherId,
        async:true,
        success:function (data){
            for(let d of data.content){
                let i = 0; // i mean day
                let j = 0; // j mean time

                if(d.day === "Monday"){
                    i = 1;
                }else if(d.day === "Tuesday"){
                    i = 2;
                }else if(d.day === "Wednesday"){
                    i = 3;
                }else if(d.day === "Thursday"){
                    i = 4;
                }else if (d.day === "Friday"){
                    i = 5;
                }

                if(d.time === "07:40-08:20"){
                    j = 1;
                }else if(d.time === "08:20-09:00"){
                    j = 2;
                }else if (d.time === "09:00-09:40"){
                    j = 3;
                }else if(d.time === "09:40-10:20"){
                    j = 4;
                }else if(d.time === "10:50-11:30"){
                    j = 5;
                }else if(d.time === "11:30-12:10"){
                    j = 6;
                }else if(d.time === "12:10-12:50"){
                    j = 7;
                }else  if(d.time === "12:50-1:30"){
                    j = 8;
                }

                let cellId = `c${j}${i}`;
                let c = d.grade+"-"+d.classRoom;
                /*let cell = `
                    <p>${d.grade} - ${d.classRoom}</p>
                `;*/
                console.log(cellId);
                //console.log(cell);
                $(`#${cellId}`).append(c);
            }
        },error:function (xhr, error) {
            console.log("Error: " + error);
            alert("Error !");
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