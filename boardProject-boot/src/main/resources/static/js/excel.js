const uplodaExcel = document.querySelector(".uplodaExcel"); 
const excelFile = document.querySelector(".excelFile"); 

uplodaExcel.addEventListener("submit", e => {
    console.log(excelFile)
    if(excelFile.value.length == 0){
        alert("파일을 선택해주세요.");
        e.preventDefault();
    }
})

const cancelBtn = document.querySelector(".cancel-btn");
const inviteContainer = document.querySelector(".invite-container");

if(cancelBtn != null){
    cancelBtn.addEventListener("click", e => {
        if(!confirm("취소하시겠습니까?")){
            return;
        }
        inviteContainer.remove();
    });
}

const inviteBtn = document.querySelector(".invite-btn");

if(inviteBtn != null){
    inviteBtn.addEventListener("click", e => {
        if(inputEmployeeList.length == 0){
            alert("등록한 사원의 정보가 없습니다");
            return;
        }

        inputEmployeeList.forEach( (employee) => {
            console.log(employee.empNo);
        });

        fetch("/user/excel/regist", {
            method : "POST",
            headers : {"Content-Type" : "application/json"},
            body : JSON.stringify(inputEmployeeList)
        })
        .then(resp => resp.text())
        .then(result => {
            if(result == 0){
                alert("사원 정보 등록 실패");
                return;
            }
            if(result == -1){
                alert("중복된 사번이 있습니다. 엑셀 파일 수정 후 다시 등록 진행해주세요.")
                return;
            }

            alert("등록 완료");
            inviteContainer.remove();
        });
    })
};

// const entireBtn = document.querySelector(".entireBtn");
const entireCheckbox = document.querySelector(".entire-checkbox");
const deleteBtn = document.querySelector(".deleteBtn");
let employeeCheckbox = document.querySelectorAll(".employeeCheckbox");

if(entireCheckbox != null){
    entireCheckbox.addEventListener("input", e => {
        if(entireCheckbox.checked == true){
            employeeCheckbox.forEach( (i) => {
                i.checked = true;
            });
        }
        if(entireCheckbox.checked == false){
            employeeCheckbox.forEach( (i) => {
                i.checked = false;
            });
        }
    });
}

if(deleteBtn != null){
    deleteBtn.addEventListener("click", e => {
        for (let i = employeeCheckbox.length - 1; i >= 0; i--) {
            if (employeeCheckbox[i].checked == true) {
                inputEmployeeList.splice(i, 1);
                employeeCheckbox[i].parentElement.parentElement.remove();
            }
        }
        console.log(inputEmployeeList);
    });
}

if(entireCheckbox != null){
    entireCheckbox.addEventListener("input", e => {
        if(entireCheckbox.checked == true){
            employeeCheckbox.forEach( (i) => {
                i.checked = true;
            });
        }
        if(entireCheckbox.checked == false){
            employeeCheckbox.forEach( (i) => {
                i.checked = false;
            });
        }
    });
}