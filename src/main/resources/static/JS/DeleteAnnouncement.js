getAllAnnouncements();
function getAllAnnouncements(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/aa/announcement/getAnnouncements",
        async:true,
        success:function (data){
            if(data.code==="00"){
                $('#announcementsList').empty();
                let count =0;
                for(let announce of data.content){
                    let announcementId = announce.announcementId ;
                    let adminId = announce.adminId;
                    let  announcement = announce.announcement;
                    let audience = announce.audience;
                    count++;
                    const announcementsList = document.getElementById('announcementsList');
                    const postAnnouncement = document.createElement('postAnnouncement');
                    postAnnouncement.classList.add('announcementItem');
                    postAnnouncement.innerHTML = `
                        <div class="announceDiv"> 
                        <h3 class="hidden">${announcementId}</h3>
                        <h3 class="hidden">${adminId}</h3>
                        <h3>${count}) ${announcement}</h3>
                        <h4>Aduience: ${audience}</h4><br>  
                        </div>          
                    `;
                    announcementsList.appendChild(postAnnouncement);
                }
            }else if(data.code ==="01") {
                alert(data.message);
                console.log(data.message);
            }
        },error:function (xhr, error) {
            console.log("Error: " + error);
            alert("Student Load fail !");
        }
    })
}
function deleteAnnouncement(){
    const announcementId = localStorage.getItem('announcementId');

    const confirm = window.confirm('Are you sure to delete Announcement?')
    if(confirm===true){
        $.ajax({
            method: "DELETE",
            url: "http://localhost:8080/api/aa/announcement/deleteAnnouncement/"+announcementId,
            async:true,
            success:function (data){
                if(data.code ==="00"){
                    getAllAnnouncements();
                    alert(data.message);
                    console.log(data.message);
                }else if(data.code === "01"){
                    alert(data.message);
                    console.log(data.message);
                }else {
                    alert(data.message);
                    console.log(data.message);
                }
            },error: function (xhr, error) {
                console.log("Error: " + error);
                alert("Server Error");
            }
        });
    }else {
        console.log('Delete cansel!');
        alert('Delete Cansel!');
    }

}

$(document).ready(function (){
    $(document).on('click','.announcementItem', function (){
        let announcementId = $(this).find('h3.hidden:nth-child(1)').text();
        let adminId = $(this).find('h3.hidden:nth-child(2)').text();
        let announcement = $(this).find('h3:nth-child(3)').text();
        let audience = $(this).find('h4').text();

        let announcementTextWithoutCount = announcement.replace(/^\d+\)\s/, '');

        console.log("Announcement ID:", announcementId);
        console.log("Admin ID:", adminId);
        console.log("Announcement Text:", announcementTextWithoutCount);
        console.log("Audience:", audience);

        $('#announcementText').val(announcementTextWithoutCount);

        localStorage.setItem('announcementId',announcementId);
    })
})

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