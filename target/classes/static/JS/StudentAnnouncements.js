getStudentAnnouncements();
function  getStudentAnnouncements(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/aa/announcement/getStudentAnnouncements",
        async:true,
        success: function (data){
            if(data.code === "00"){
                $('#announcementsList').empty();
                let count =0;
                for(let announcements of data.content){
                    let  announcement = announcements.announcement;
                    count++;

                    let postAnnouncement = `
                        <p id="postAnnouncement">${count}) ${announcement}</p>
                    `;
                    $('#announcementsList').append(postAnnouncement);
                }
            }else if(data.code==="01"){
                let nonPost = `
                    <p id="nonPost"> No Announcements Yet!</p>
                `;
                $('#announcementsList').append(nonPost);
            }else {
                let errorMsg = `
                    <p id="errorMsg"> Server Error!</p>
                `;
                $('#announcementsList').append(errorMsg);
            }
        },error: function (xhr, status, error) {
            console.log("Error: " + error);
            alert("Something went wrong");
        }
    });
}
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