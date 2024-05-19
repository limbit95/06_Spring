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
        
    })
    
}