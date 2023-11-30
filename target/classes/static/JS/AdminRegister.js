function registerAdmin(){
    let adminId = $('#adminId').val();
    let adminName = $('#adminName').val();
    let nic = $('#nic').val();
    let email = $('#emailAddress').val();
    let address = $('#address').val();
    let contactNumber = $('#tel').val();
    let birthday = $('#birthday').val();
    let gender = $('#gender').val();
    let rightToWork = $('#rightToWork').val();
    let password = $('#password').val();
    let accessibility = $('#accessibility').val();

    if(!adminId || !nic || !adminName || !email || !contactNumber || !address || !birthday || !rightToWork  || !password){
        alert('Please fill in all required fields!');
        return;
    }
    const confirm = window.confirm("Are you sure to continue?");
    if(confirm === true){
        const input ={

            adminId:adminId,
            adminName:adminName,
            nic:nic,
            emailAddress:email,
            address:address,
            contactNumber:contactNumber,
            birthday:birthday,
            gender:gender,
            rightToWork:rightToWork,
            password:password,
            accessibility:accessibility

        }

        $.ajax({
            method:"POST",
            contentType:"application/json",
            url:"http://localhost:8080/api/a1/admin/reg",
            data:JSON.stringify(input),
            async:true,
            success:function (data){
                if(data.code === "00"){
                    alert(data.message);
                }else if(data.code === "06"){
                    alert(data.message);
                }else if(data.code === "07"){
                    alert(data.message);
                }else{
                    alert("Something went wrong!");
                }
            },error: function (xhr, error) {
                console.log("Error: " + error);
                alert("Registration Failed");
            }
        });
    }else {
        alert("Cansel Registration!");
    }
}

function goBack(){
    window.history.back();
}