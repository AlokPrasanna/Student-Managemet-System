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
function editAnnouncement(){
    const announcementId = localStorage.getItem('announcementId');
    const  adminId = localStorage.getItem('adminId');
    let announcement = $('#announcementText').val();
    let audience = document.querySelector('input[name="announcementAudience"]:checked').value;

    const input = {
        announcementId:announcementId,
        adminId:adminId,
        announcement:announcement,
        audience:audience
    }
    const confirmed = window.confirm('Are you sure you chose the correct audience?');
    if(confirmed===true){
        $.ajax({
            method: "PUT",
            contentType: "application/json",
            url: "http://localhost:8080/api/aa/announcement/editAnnouncement",
            data:JSON.stringify(input),
            async:true,
            success:function (data){
                if(data.code ==="00"){
                    getAllAnnouncements();
                    alert(data.message);
                }else if(data.code ==="04"){
                    alert(data.message);
                }else if(data.message==="07"){
                    alert(data.message);
                }else{
                    alert('Something went wrong');
                }
            }, error: function (xhr, error) {
                console.log("Error: " + error);
                alert("Server Error");
            }
        });

    }else {
        console.log("Announcement Edit Cansel!");
        alert('Announcement edit cansel!');
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
        $('input[name="announcementAudience"]').prop('checked', false);
        $('input[name="announcementAudience"][value="' + audience.trim() + '"]').prop('checked', true);

        localStorage.setItem('announcementId',announcementId);
        localStorage.setItem('adminId',adminId);
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