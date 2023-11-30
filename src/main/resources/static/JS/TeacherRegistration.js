
/*function addNewGradeAndClass() {
    let container = document.getElementById("gradeWithClass");

    let gradeClassPair = document.createElement("div");
    gradeClassPair.className = "grade-class-pair";

    let gradeLabel = document.createElement("label");
    gradeLabel.htmlFor = "grade";
    gradeLabel.textContent = "Grade:";

    let gradeInput = document.createElement("input");
    gradeInput.type = "number";
    gradeInput.name = "grade";
    gradeInput.placeholder = "E.g: 10";
    gradeInput.required = true;
    gradeInput.className = "grade-input";

    let classLabel = document.createElement("label");
    classLabel.htmlFor = "class";
    classLabel.textContent = "Class:";

    let classInput = document.createElement("input");
    classInput.type = "text";
    classInput.name = "class";
    classInput.placeholder = "E.g: A";
    classInput.required = true;
    classInput.className = "class-input";

    gradeClassPair.appendChild(gradeLabel);
    gradeClassPair.appendChild(gradeInput);
    gradeClassPair.appendChild(classLabel);
    gradeClassPair.appendChild(classInput);

    container.appendChild(gradeClassPair);
}*/
/*function addNewGradeAndClass() {
    let container = document.getElementById("gradeWithClass");

    let gradeClassPair = document.createElement("div");
    gradeClassPair.className = "grade-class-pair";

    let gradeLabel = document.createElement("label");
    gradeLabel.id = "grade";
    gradeLabel.htmlFor = "grade";
    gradeLabel.textContent = "Select Grade:";

    let gradeSelect = document.createElement("select");
    gradeSelect.className = "grade-select";
    gradeSelect.name = "grade";
    gradeSelect.required = true;
    gradeSelect.innerHTML = `
        <option value="6">Grade 6</option>
        <option value="7">Grade 7</option>
        <option value="8">Grade 8</option>
        <option value="9">Grade 9</option>
        <option value="10">Grade 10</option>
        <option value="11">Grade 11</option>
    `;

    let classLabel = document.createElement("label");
    classLabel.id = "class";
    classLabel.htmlFor = "class";
    classLabel.textContent = "Select Class:";

    let classSelect = document.createElement("select");
    classSelect.className = "class-select";
    classSelect.name = "class";
    classSelect.required = true;
    classSelect.innerHTML = `
        <option value="A">Class A</option>
        <option value="B">Class B</option>
        <option value="C">Class C</option>
        <option value="D">Class D</option>
        <option value="E">Class E</option>
    `;

    gradeClassPair.appendChild(gradeLabel);
    gradeClassPair.appendChild(gradeSelect);
    gradeClassPair.appendChild(classLabel);
    gradeClassPair.appendChild(classSelect);

    container.appendChild(gradeClassPair);
}



function removeLastGradeAndClass() {
    let container = document.getElementById("gradeWithClass");

    // Check if there is at least one grade-class-pair to remove
    if (container.lastChild && container.lastChild.className === "grade-class-pair") {
        container.removeChild(container.lastChild);
    }
}*/
function registerTeacher(){
    let teacherId = $('#teacherId').val();
    let title = $('#title').val();
    let teacherName = $('#teacherName').val();
    let email = $('#emailAddress').val();
    let contactNumber = $('#tel').val();
    let subject = $('#subject').val();

    /*let gradesAndClasses = [];

    // Loop through each grade-class-pair and add it to the array
    $(".grade-class-pair").each(function () {
        let grade = $(this).find(".grade-select").val();
        let className = $(this).find(".class-select").val();

        // Add the grade and class to the array
        gradesAndClasses.push({
            grade: grade,
            teacherClass: className
        });
    });*/

    let birthday = $('#birthday').val();
    let gender = $('#gender').val();
    let password = $('#password').val();
    let accessibility = $('#accessibility').val();

    if(!teacherId || !title || teacherName || !email || !contactNumber || !subject || !birthday || !gender || !password){
        alert('Please fill in all required fields!');
        return;
    }

    console.log("Teacher ID: ", teacherId);
    console.log("Title: ", title);
    console.log("Teacher Name: ", teacherName);
    console.log("Email: ", email);
    console.log("Contact Number: ", contactNumber);
    console.log("Subject: ", subject);
    console.log("Birthday: ", birthday);
    console.log("Gender: ", gender);
    console.log("Password: ", password);
    console.log("accesibility" , accessibility);

    const confirm = window.confirm("Are you sure to continue?");
    if(confirm === true){
        const input ={

            teacherId:teacherId,
            title:title,
            teacherName:teacherName,
            emailAddress:email,
            contactNumber:contactNumber,
            subject:subject,
            birthday:birthday,
            gender:gender,
            password:password,
            accessibility:accessibility

        }

        $.ajax({
            method:"POST",
            contentType:"application/json",
            url:"http://localhost:8080/api/t1/teacher/teacherReg",
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
