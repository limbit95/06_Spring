// 글쓰기 버튼 클릭시
const insertBtn = document.querySelector("#insertBtn");

// 글쓰기 버튼이 존재할 때 (로그인 했을 때만 보이도록 했기 때문에 비회원은 버튼이 보이지 않음)
if(insertBtn != null){
    insertBtn.addEventListener("click", () => {
        // get 방식 요청
        // /editBoard/1/insert
        location.href = `/editBoard/${boardCode}/insert`;
    });
};