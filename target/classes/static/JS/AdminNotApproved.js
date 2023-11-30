let adminId = localStorage.getItem('adminId');
getAdminDetails();
function getAdminDetails(){
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/a1/admin/getAdminById/" + encodeURIComponent(adminId),
        async: true,
        success: function (data) {
            if(data.code ==="00"){
                let name = data.content.adminName;
                let nameArray = name.split(" ");
                let firstName = nameArray[0];
                $('#name').append(firstName);
            }
        },error: function (xhr, status, error) {
            console.log("Error: " + error);
            alert("Error!");
        }

    });
}
function goToHomePage(){
    window.location.href = "http://localhost:8080/studentms/adminLogin";
}