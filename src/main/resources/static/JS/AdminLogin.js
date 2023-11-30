function adminLogin(){
    let adminId = $('#adminId').val();
    let password = $('#password').val();
    console.log(adminId);
    console.log(password);
    localStorage.setItem('adminId',adminId);
    if(!adminId || !password){
        alert("Please fill in all required fields!");
        return;
    }
    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/a1/admin/login",
        async:true,
        data:JSON.stringify({
            adminId: adminId,
            password: password
        }),
        success:function (data){
            if(data.code ==="02"){
                alert(data.message);
            }
            else if(data.code ==="01") {
                alert(data.message);
            }
            else if(data.code ==="00"){
                console.log("Authentication Success!");
                localStorage.setItem('adminId',adminId);
                window.location.href ="http://localhost:8080/studentms/adminDashboard";
            }
            else if(data.code ==="04"){
                window.location.href ="http://localhost:8080/studentms/admin/NotApproved";
            }
            else {
                alert(data.message);
            }
        },error:function (xhr, error) {
            console.log("Error: " + error);
            alert("Login Failed");
        }
    })
}