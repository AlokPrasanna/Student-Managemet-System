const adminId = localStorage.getItem('adminId');
getAdminDetails();
function getAdminDetails(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/a1/admin/getAdminById/"+encodeURIComponent(adminId),
        async:true,
        success:function (data){
            let name = data.content.adminName;
            let nic = data.content.nic;
            let email = data.content.emailAddress;
            let address = data.content.address;
            let contactNumber = data.content.contactNumber;
            let birthday = data.content.birthday;
            let gender = data.content.gender;
            document.getElementById("name").textContent = name;
            document.getElementById("nic").textContent = nic;
            document.getElementById("email").textContent = email;
            document.getElementById("hAddress").textContent = address;
            document.getElementById("contactNumber").textContent = contactNumber;
            document.getElementById("birthday").textContent = birthday;
            document.getElementById("gender").textContent = gender;

        },error: function (xhr, status, error) {
            console.log("Error: " + error);
            alert("Login Failed");
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
