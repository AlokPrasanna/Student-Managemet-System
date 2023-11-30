function giveStudentAccessPage(){
    window.location.href = "http://localhost:8080/studentms/admin/setStudentAccess";
}
function giveTeacherAccessPage(){
    window.location.href = "http://localhost:8080/studentms/admin/setTeacherAccess";
}
function deleteTeacherPage(){
    window.location.href = "http://localhost:8080/studentms/admin/deleteTeacher";
}
function deleteStudentPage(){
    window.location.href = "http://localhost:8080/studentms/admin/deleteStudent";
}
function getTeacherDetailsPage(){
    window.location.href = "http://localhost:8080/studentms/admin/getTeacherDetails";
}
function  getAllTeacherPage(){
    window.location.href = "http://localhost:8080/studentms/admin/getAllTeachers";
}
function getStudentDetailsPage(){
    window.location.href = "http://localhost:8080/studentms/admin/getStudentDetails";
}
function getTimetableDataPage() {
    window.location.href = "http://localhost:8080/studentms/setTimetableData";
}
function editAnnouncementPage(){
    window.location.href = "http://localhost:8080/studentms/admin/editAnnouncements";
}
function deleteAnnouncementPage(){
    window.location.href = "http://localhost:8080/studentms/admin/deleteAnnouncements";
}
function getAllStudentsPage(){
    window.location.href = "http://localhost:8080/studentms/admin/getAllStudents";
}


function createAnnouncement() {
    let announcement = document.getElementById('announcementText').value;
    let audience = document.querySelector('input[name="announcementAudience"]:checked').value;

    console.log("Announcement:", announcement);
    console.log("Audience:", audience);

    if (announcement.trim() === "") {
        alert('Type Announcement before clicking "Create Announcement" Button!');
        return;
    }

    const adminId = localStorage.getItem('adminId');
    const input = {
        announcementId: 0,
        adminId: adminId,
        announcement: announcement,
        audience: audience
    };

    const confirmed = window.confirm('Are you sure you chose the correct audience?');

    if (confirmed) {
        $.ajax({
            method: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/api/aa/announcement/postAnnouncement",
            async: true,
            data: JSON.stringify(input),
            success: function (data) {
                if (data.code === "00") {
                    alert(data.message);
                }
                if (data.code === "04") {
                    alert(data.message);
                }
                if (data.code === "07") {
                    alert(data.message);
                }
            },
            error: function (xhr, error) {
                console.log("Error: " + error);
                alert("Server Error");
            }
        });
    } else {
        alert('Announcement Cancelled!');
    }
}
function logOut(){
    const confirm = window.confirm('Are you sure to log out?');

    if(confirm === true){
        window.location.href = "http://localhost:8080/studentms/adminLogin";
    }else {
        alert('Cansel!');
    }
}
