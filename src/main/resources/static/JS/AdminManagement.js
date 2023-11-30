getAllAdminsData();
const adminIdToDelete = localStorage.getItem('adminID');
let adminDataArray = [];
let noneAccessAdmins = [];
function getAllAdminsData(){
   // adminDataArray = [];
    $.ajax({
        method:"GET",
        url: "http://localhost:8080/api/a1/admin/getAllAdmins",
        async:true,
        success:function (data){
            if(data.code === "00"){
                for(let a of data.content ){
                    adminDataArray.push(a);
                }
                console.log("Admin Data Array", adminDataArray);
            }else if(data.code === "01"){
                adminDataArray = null;
            }
        },error: function (xhr, error) {
            console.log("Error: " + error);
            alert("Data Load Fail!");
        }
    });
}
$(document).ready(function (){
    $('#dataArea').on('click','#tableData tr', function (){
        let admin = $(this).find('td:eq(0)').text();
        let adminName = $(this).find('td:eq(1)').text();
        let nic = $(this).find('td:eq(2)').text();

        localStorage.setItem('admin',admin);
        localStorage.setItem('adminName',adminName);
        localStorage.setItem('adminNIC',nic);

        if($('.giveAccessTable').length > 0 ){
            printGiveAccessList();
        }else if($('.deleteTable').length > 0){
            printDeleteList();
        }else if($('.allAdminTable').length >0){
            printAdminDetails();
        }
    });
});

// Give Admin Access Functions
function giveAdminAccessList(){
    for(let a of adminDataArray){
        if(a.accessibility === false){
            noneAccessAdmins.push(a);
        }
    }
    console.log("None Access Dat Array",noneAccessAdmins);
    showNoneAccessAdminList();
}

function showNoneAccessAdminList(){
    $('#dataArea').empty();
    $('#functionArea').empty();
    if(noneAccessAdmins.length === 0){
        let msg = `
            <p style="text-align: center;font-size: 20px; color: white">All Admins Can Access the System! ;)...</p>
        `;
        $('#dataArea').append(msg);
    }else{
        let createTable = `
        <h3 class="createTable">Give Admin Access Table</h3>
        <table class="giveAccessTable">
            <thead>
                <tr>
                    <th scope="col">AdminID</th>
                    <th scope="col">Name</th>
                    <th scope="col">NIC</th>
                </tr>
            </thead>
            <tbody id="tableData"></tbody>
        </table>
        `;
        $('#dataArea').append(createTable);
        console.log("none-accessAdminList Array",noneAccessAdmins);
        //let tableBody = $('#dataArea').find('#tableData');
        for(let a of noneAccessAdmins){
            let adminId = a.adminId;
            let adminName = a.adminName;
            let nic = a.nic;

            let row = `
            <tr>
                <td>${adminId}</td>
                <td>${adminName}</td>
                <td>${nic}</td>
            </tr>
        `;
            $('#tableData').append(row);
        }
    }

    noneAccessAdmins = [];
}

function printGiveAccessList(){
    $('#functionArea').empty();
    let adminId = localStorage.getItem('admin');
    let adminName = localStorage.getItem('adminName');
    let nic = localStorage.getItem('adminNIC');

    let functionArea = `
        <p>Admin ID: <span>${adminId}</span></p>
        <p>Admin Name: <span>${adminName}</span></p>
        <p>NIC Number: <span>${nic}</span></p>
        <button type="button" class="button" id="giveAccess" onclick="giveAccess()">Give Access</button>
    `;
    $('#functionArea').append(functionArea);
}

function giveAccess(){
    let adminId = localStorage.getItem('admin');
    if(!adminId){
        alert('Select admin before click "Give Access" button!');
        return;
    }

    const confirm = window.confirm('Are you sure to continue?');
    if(confirm === true){
        $.ajax({
            method: "PUT",
            url: "http://localhost:8080/api/a1/admin/"+encodeURIComponent(adminId)+"/giveAdminAccess",
            async: true,
            success:function (data){
                if(data.code === "00"){
                    alert(data.message);
                    localStorage.setItem('admin',adminIdToDelete);
                    adminDataArray = [];
                    getAllAdminsData();
                    giveAdminAccessList();
                }else if(data.code === "07"){
                    alert(data.message);
                }else if(data.code === "01"){
                    alert(data.message);
                }else{
                    alert('something Went Wrong!');
                }
            },error: function (xhr, error) {
                console.log("Error: " + error);
                alert("Error!");
            }
        });
    }else {
        alert('Give Access Cansel!');
    }
}

// Delete Functions
function deleteAdminList(){
    $('#dataArea').empty();
    $('#functionArea').empty();
    if(adminDataArray.length === 1){
        let msg = `
            <p style="text-align: center;font-size: 20px; color: white">No more Admins Regiser yet! ;(...</p>
        `;
        $('#dataArea').append(msg);
    }else{
        let createTable = `
        <h3 class="createTable">Delete Admin Table</h3>
        <table class="deleteTable">
            <thead>
                <tr>
                    <th scope="col">AdminID</th>
                    <th scope="col">Name</th>
                    <th scope="col">NIC</th>
                </tr>
            </thead>
            <tbody id="tableData"></tbody>
        </table>
        `;
        $('#dataArea').append(createTable);

        console.log("admin data array in delete ",adminDataArray);
        for (let a of adminDataArray){
            if(a.adminId === adminIdToDelete){
                continue;
            }
            let adminId = a.adminId;
            let adminName = a.adminName;
            let nic = a.nic;

            let row = `
                <tr>
                    <td>${adminId}</td>
                    <td>${adminName}</td>
                    <td>${nic}</td>
                </tr>
            `;
            $('#tableData').append(row);
        }
    }
}
function printDeleteList(){
    $('#functionArea').empty();
    let adminId = localStorage.getItem('admin');
    let adminName = localStorage.getItem('adminName');
    let nic = localStorage.getItem('adminNIC');
    console.log('delet Id ',adminId);

    let functionArea = `
        <p>Admin ID: <span>${adminId}</span></p>
        <p>Admin Name: <span>${adminName}</span></p>
        <p>NIC Number: <span>${nic}</span></p>
        <button type="button" class="button" id="deleteAdmin" onclick="deleteAdmin()">Delete</button>
    `;
    $('#functionArea').append(functionArea);
}
function deleteAdmin(){
    let adminId = localStorage.getItem('admin');
    console.log(adminId);
    if(!adminId){
        alert('Select admin before click "Give Access" button!');
        return;
    }

    const confirm = window.confirm('Are you sure to continue?');
    if(confirm === true){
        $.ajax({
            method: "DELETE",
            url: "http://localhost:8080/api/a1/admin/"+encodeURIComponent(adminId)+"/deleteAdmin",
            async: true,
            success:function (data){
                if(data.code === "00"){
                    alert(data.message);
                    localStorage.setItem('admin',adminIdToDelete);
                    adminDataArray = [];
                    getAllAdminsData();
                    deleteAdminList();
                }else if(data.code === "07"){
                    alert(data.message);
                }else if(data.code === "01"){
                    alert(data.message);
                }else{
                    alert('something Went Wrong!');
                }
            },error: function (xhr, error) {
                console.log("Error: " + error);
                alert("Error!");
            }
        });
    }else {
        alert('Delete Admin Cansel!');
    }
}
// Get Admin Details functions
function getAdmindetails(){
    $('#dataArea').empty();
    $('#functionArea').empty();

    let getDetails = `
        <lable for="adminId">Enter Admin Id:</lable>
        <input type="text" id="adminId"><br><br>
        <button type="button" class="button" id="showDetails" onclick="getDetails()">Show Details</button>
    `;
    $('#dataArea').append(getDetails);
}
function getDetails() {
    $('#functionArea').empty();
    let adminId = $('#adminId').val();
    if(!adminId){
        alert('Enter Id before click "Show Details" button!');
        return;
    }
    let adminFound = false;

    for (let a of adminDataArray){
        if(a.adminId === adminId){
            let adminId = a.adminId;
            let name = a.adminName;
            let nic = a.nic;
            let emai = a.emailAddress;
            let address = a.address;
            let contactNumber = a.contactNumber;
            let birthday = a.birthday;

            let showDetails = `
                <p>Admin ID: <span>${adminId}</span></p>
                <p>Admin Name: <span>${name}</span></p>
                <p>NIC Number: <span>${nic}</span></p>
                <p>Email: <span>${emai}</span></p>
                <p>Address: <span>${address}</span></p>
                <p>Contact Number: <span>${contactNumber}</span></p>
                <p>DOB: <span>${birthday}</span></p>
            `;
            $('#functionArea').append(showDetails);
            adminFound = true;
            break;
        }
    }
    if(!adminFound){
        let message = `
            <p style="text-align: center; font-size: 20px; color: white">Admin with ID ${adminId} not found! ;(...</p>
        `;
        $('#functionArea').append(message);
    }
}
// Get All Admins Functions

function getAllAdmins(){
    $('#dataArea').empty();
    $('#functionArea').empty();

    let count = adminDataArray.length;

    let createTable = `
    <h3 class="createTable">All Admin Details Table    <span>Total Admin Count: ${count}</span></h3>
    <table class="allAdminTable">
        <thead>
            <tr>
                <th scope="col">AdminID</th>
                <th scope="col">Name</th>
                <th scope="col">NIC</th>
            </tr>
        </thead>
        <tbody id="tableData"></tbody>
    </table>
    `;
    $('#dataArea').append(createTable);

    for (let a of adminDataArray){
        let adminId = a.adminId;
        let adminName = a.adminName;
        let nic = a.nic;

        let row = `
            <tr>
                <td>${adminId}</td>
                <td>${adminName}</td>
                <td>${nic}</td>
            </tr>
        `;
        $('#tableData').append(row);
    }
    localStorage.setItem('admin',adminIdToDelete);
}

function printAdminDetails(){
    $('#functionArea').empty();
    let adminId = localStorage.getItem('admin');

    for (let a of adminDataArray){
        if(a.adminId === adminId){
            let adminId = a.adminId;
            let name = a.adminName;
            let nic = a.nic;
            let emai = a.emailAddress;
            let address = a.address;
            let contactNumber = a.contactNumber;
            let birthday = a.birthday;

            let showDetails = `
                <p>Admin ID: <span>${adminId}</span></p>
                <p>Admin Name: <span>${name}</span></p>
                <p>NIC Number: <span>${nic}</span></p>
                <p>Email: <span>${emai}</span></p>
                <p>Address: <span>${address}</span></p>
                <p>Contact Number: <span>${contactNumber}</span></p>
                <p>DOB: <span>${birthday}</span></p>
            `;
            $('#functionArea').append(showDetails);
            break;
        }
    }
    localStorage.setItem('admin',adminIdToDelete);
}
function goBack(){
    window.history.back();
}
function logOut(){
    const confirm = window.confirm('Are you sure to log out?');

    if(confirm === true){
        localStorage.removeItem('admin');
        localStorage.removeItem('adminName');
        localStorage.removeItem('adminNIC');
        window.location.href = "http://localhost:8080/studentms/adminLogin";
    }else {
        alert('Cansel!');
    }
}
