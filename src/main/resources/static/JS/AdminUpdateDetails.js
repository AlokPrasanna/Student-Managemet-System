const adminId = localStorage.getItem("adminId");

function updateAdminDetails(){
    let adminName = $('#adminName').val();
    let nic = $('#nic').val();
    let email = $('#emailAddress').val();
    let address = $('#address').val();
    let contactNumber = $('#tel').val();
    let birthday = $('#birthday').val();
    let gender = $('#gender').val();

    const confirm = window.confirm("Are you sure to proceed with the update?");
    if(confirm === true){
        $.ajax({
            method:"GET",
            url:"http://localhost:8080/api/a1/admin/getAdminById/"+encodeURIComponent(adminId),
            async:true,
            success:function (data){
                if(adminName ===""){
                    adminName = data.content.adminName;
                }
                if(nic ===""){
                    nic = data.content.nic;
                }
                if(email ===""){
                    email = data.content.emailAddress;
                }
                if(address ===""){
                    address = data.content.address;
                }
                if(contactNumber ===""){
                    contactNumber = data.content.contactNumber;
                }
                if(birthday ===""){
                    birthday = data.content.birthday;
                }
                if(gender ===""){
                    gender = data.content.gender;
                }
                const inputData ={
                    adminId:data.content.adminId,
                    adminName: adminName,
                    nic:nic,
                    emailAddress: email,
                    address:address,
                    contactNumber:contactNumber,
                    birthday:birthday,
                    gender:gender,
                    rightToWork: data.content.rightToWork,
                    accessibility: data.content.accessibility,
                    password:data.content.password
                }
                $.ajax({
                    method:"PUT",
                    contentType:"application/json",
                    url:"http://localhost:8080/api/a1/admin/updateAdminDetails",
                    data:JSON.stringify(inputData),
                    async:true,
                    success:function (data){
                        if(data.code ==="00"){
                            alert(data.message);
                        }else if(data.code === "01"){
                            alert(data.message);
                        }
                    },error: function (xhr, status, error) {
                        console.log("Error: " + error);
                        alert("Something went wrong");
                    }

                })

            },error: function (xhr, status, error) {
                console.log("Error: " + error);
                alert("Error!");
            }
        });
    }else {
        alert("Cansel Success");
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

function goBack(){
    window.history.back();
}