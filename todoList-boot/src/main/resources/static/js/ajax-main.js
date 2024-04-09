// 요소 얻어와서 변수에 저장

const totalCount = document.querySelector("#totalCount");
const completeCount = document.querySelector("#completeCount");
const reloadBtn = document.querySelector("#reloadBtn");

// 전체 Todo 개수 조회 및 출력하는 함수 정의
function getTotalCount(){
    // 비동기로 서버에서 전체 Todo 개수 조회하는
    // fetch() API 코드 작성 (fetch : 가지고 오다)
    
    fetch("/ajax/totalCount") // 비동기 요청 수행 -> Promise 객체 반환
    .then( response => {
        // response : 비동기 요청에 대한 응답에 담긴 객체
        
        console.log("response : ", response);
        // response.text() : 응답 데이터를 문자열/숫자 형태로 변환한
        //                   결과를 가지는 Promise 객체 반환
        return response.text();
    })
    // 두 번째 then의 매개변수 (result)
    // ** 첫 번째 then에서 반환된 Promise 객체의 PromiseResult 값
    .then( result => {
        // result 매개변수 == Controller 메서드에서 반환된 값
        console.log("result : ", result);

        // #totalCount 요소의 내용을 result 변경
        totalCount.innerText = result;
    });

};

getTotalCount(); // 함수 호출

// completeCount 값 비동기 통신으로 얻어와서 화면 출력
function getCompleteCount(){
    // fetch() : 비동기로 요청해서 결과 데이터 가져오기

    // 첫 번째 then의 response :
    // ~ 응답 결과, 요청 주소, 응답 데이터 등이 담겨있음

    // response.text() : 응답 데이터를 text 형태로 변환

    // 두 번째 then의 result
    // - 첫 번째 then에서 text로 변환된 응답 데이터
    fetch("/ajax/completeCount")
    .then(response => {
        return response.text(); 
    })
    .then(result => {
        completeCount.innerText = result;
    });
};

getCompleteCount();

// 새로고침 버튼이 클릭 되었을 때
reloadBtn.addEventListener("click", () => {
    getTotalCount();        // 비동기로 전체 할 일 개수 조회
    getCompleteCount();     // 비동기로 완료된 할 일 개수 조회
});


// ===================================================================
// 할 일 추가 관련 요소
const todoTitle = document.querySelector("#todoTitle");
const todoContent = document.querySelector("#todoContent");
const addBtn = document.querySelector("#addBtn");

// 할 일 추가 버튼 클릭 시 동작
addBtn.addEventListener("click", () => {
    // 비동기로 할 일 추가하는 fetch() 코드 작성
    // - 요청 주소 : "/ajax/add"
    // - 데이터 전달 방식(method) : "POST" 방식

    // 파라미터를 저장한 JS 객체
    const param = {
        // Key : Value
        "todoTitle" : todoTitle.value,
        "todoContent" : todoContent.value
    };

    fetch("/ajax/add", {
        // Key : Value
        method : "POST", // POST 방식 요청
        headers : {"Content-Type" : "application/json"}, // 요청 데이터의 형식을 JSON으로 지정
        body : JSON.stringify(param) // param 객체를 JSON(String)으로 변환
    })
    .then( resp => resp.text() ) // 반환된 값을 text로 변환
    .then( temp => { // 첫 번째 then에서 반환된 값 중 변환된 text를 temp에 저장
        if(temp > 0){ // 할 일 추가 성공
            alert("추가 성공!");

            // 추가 성공한 제목, 내용 지우기
            todoTitle.value = "";
            todoContent.value = "";

            // 할 일이 추가되었기 때문에 전체 Todo 개수 다시 조회
            getTotalCount();

            // 할 일 목록 다시 조회
            selectTodoList();
        } else { // 실패
            alert("추가 실패...");
        }
    })
});


// ===================================================================


// 할 일 상세 조회 관련 요소
const popupLayer = document.querySelector("#popupLayer");
const popupTodoNo = document.querySelector("#popupTodoNo");
const popupTodoTitle = document.querySelector("#popupTodoTitle");
const popupComplete = document.querySelector("#popupComplete");
const popupRegDate = document.querySelector("#popupRegDate");
const popupTodoContent = document.querySelector("#popupTodoContent");
const popupClose = document.querySelector("#popupClose");

// 상세 저회 버튼
const deleteBtn = document.querySelector("#deleteBtn");
const updateView = document.querySelector("#updateView");
const changeComplete = document.querySelector("#changeComplete");



// 비동기(ajax)로 할 일 상세 조회하는 함수
const selectTodo = (url) => {
    // 매개변수 url == "/ajax/detail?todoNo=1" 형태의 문자열

    // response.json() : 응답 데이터가 JSON인 경우
    //                   이를 자동으로 Object 형태로 반환하는 메서드
    //                   == JSON.parse(JSON 데이터)
    fetch(url)
    .then(resp => resp.json())
    .then(todo => {
        // const todo = JSON.parse(result);

        console.log(todo);

        // 매개변수 todo :
        // - 서버 응답(JSON)이 Object로 변환된 값

        // popup Layer에 조회된 값 출력
        popupTodoNo.innerText = todo.todoNo;
        popupTodoTitle.innerText = todo.todoTitle;
        popupComplete.innerText = todo.complete;
        popupRegDate.innerText = todo.regDate;
        popupTodoContent.innerText = todo.todoContent;

        // popup layer 보이게 하기
        popupLayer.classList.remove("popup-hidden");

        // updateLayer가 혹시라도 열려있으면 숨기기
        updateLayer.classList.add("popup-hidden");
    });
};

// ===================================================================

// 모달 창 닫기
popupClose.addEventListener("click", () => {
    popupLayer.classList.add("popup-hidden");
});

// ===================================================================


// 할 일 목록 조회 관련 요소
const tbody = document.querySelector("#tbody");

// 비동기로 할 일 목록을 조회하는 함수
const selectTodoList = () => {
    fetch("/ajax/selectList")
    .then(resp => resp.text()) // 응답 결과를 text로 변환
    .then(result => {
        // console.log(result);
        // console.log(typeof result); // 객체가 아닌 문자열 형태

        // 문자열은 가공은 할 수 있지만 너무 힘듬
        // -> JSON.parse(JSON데이터) 이용
        
        // JSON.parse(JSON데이터) : String -> Object
        // - String 형태의 JSON 데이터를 JS Object 타입으로 변환

        // JSON.stringify(JS Object) : Object -> String
        // - JS Object 타입을 String 형태의 JSON 데이터로 변환
        const todoList = JSON.parse(result);

        console.log(todoList);

        // ------------------------------------------

        // 기존에 출력되어있던 할 일 목록을 모두 삭제
        tbody.innerHTML = "";

        // #tobody에 tr/td 요소를 생성해서 내용 추가
        for(let todo of todoList){ // 향상된 for문
            // tr 태그 생성
            const tr = document.createElement("tr");

            const arr = ['todoNo', 'todoTitle', 'complete', 'regDate'];

            for(let key of arr){
                const td = document.createElement("td");
                
                // 제목인 경우
                if(key === 'todoTitle'){
                    const a = document.createElement("a"); // a 태그 생성
                    a.innerText = todo[key]; // 제목을 a 태그 내용으로 대입
                    a.href = "/ajax/detail?todoNo=" + todo.todoNo;
                    td.append(a);
                    tr.append(td);

                    // a 태그 클릭 시 기본 이벤트(페이지 이동) 막기
                    a.addEventListener("click", (e) => {
                        e.preventDefault(); // 기본 이벤트 막기

                        // 할 일 상세 조회 비동기 요청
                        // e.target.href : 클릭된 a 태그의 href 속성 값
                        selectTodo(e.target.href);
                        
                    });

                    continue;
                }

                td.innerText = todo[key];
                tr.append(td);
            }

            // tbody의 자식으로 tr(한 행) 추가
            tbody.append(tr);
        }
    })
};

selectTodoList();

// =================================================

deleteBtn.addEventListener("click", () => {
    // 취소 클릭 시 아무것도 안함
    if(!confirm("정말 삭제 하시겠습니까?")){
        return;
    } 
    // 삭제할 할 일 번호 얻어오기
    const todoNo = popupTodoNo.innerText; // #popupTodoNo 내용 얻어오기
    
    fetch("/ajax/delete", {
        method : "DELETE", // DELETE 방식 요청 -> @DeleteMapping 처리

        // 데이터 하나를 전달해도 application/json 작성
        headers : {"Content-type" : "application/json"},
        body : todoNo // todoNo 값을 body에 담아서 전달
    })
    .then(resp => resp.text())
    .then(result => {
        if(result > 0) {
            alert("삭제 성공!!!");

            // 상세 조회 창 닫기
            popupLayer.classList.add("popup-hidden");

            // 전체, 완료된 할 일 개수 다시 조회
            // + 할 일 목록 다시 조회
            getTotalCount();
            getCompleteCount();
            selectTodoList();
        } else {
            alert("삭제 실패...");
        }
    }) 
});


// ==================================================================

// 완료여부 변경 기능 (내 코드) 

// changeComplete.addEventListener("click", () => {
//     // Y <-> N 변경
//     let complete = popupComplete.innerText; // 기존 완료 여부 값 얻어오기

//     complete = (complete === 'Y') ? 'N' : 'Y';

//     const param = {
//         "todoNo" : popupTodoNo.innerText,
//         "complete" : complete
//     };

//     fetch("/ajax/changeComplete", {
//         method : "PUT",
//         headers : {"Content-type" : "application/json"},
//         body : JSON.stringify(param)
//     })
//     .then(resp => resp.text())
//     .then(result => {
//         if(result > 0){
//             alert("변경 성공!");

//             selectTodo("/ajax/detail?todoNo=" + popupTodoNo.innerText);
//             selectTodoList();
//             getCompleteCount();
//         } else {
//             alert("변경 실패...");
//         }
//     });
// });

// 완료여부 변경 버튼 클릭 시 (강사님 코드)
changeComplete.addEventListener("click", () => {
    // 변경할 할 일 번호, 완료 여부 (Y <-> N) 요소 구하기
    const todoNo = popupTodoNo.innerText;
    const complete = popupComplete.innerText === 'Y' ? 'N' : 'Y';

    // SQL 수행에 필요한 값을 JS 객체로 묶기
    const obj = {"todoNo" : todoNo, "complete" : complete};

    // 비동기로 완료 여부 변경
    fetch("/ajax/changeComplete", {
        method : "PUT",
        headers : {"Content-type" : "application/json"},
        body : JSON.stringify(obj)
    })
    .then(resp => resp.text())
    .then(result => {
        if(result > 0){
            // update된 DB 데이터를 다시 조회해서 화면에 출력
            // -> 서버 부하가 큼
            
            // selectTodo();
            // 서버 부하를 줄이기 위해 상세 조회에서 Y/N만 바꾸기
            popupComplete.innerText = complete;

            // getCompleteCount();
            // 서버 부하를 줄이기 위해 완료된 Todo 개수 +- 1
            const count = Number(completeCount.innerText);

            if(complete === 'Y'){
                completeCount.innerText = count + 1;
            } else{
                completeCount.innerText = count - 1;
            }

            // 서버 부하 줄이기 가능 -> 코드 조금 복잡
            selectTodoList(); 

        } else {
            alert("완료 여부 변경 실패...");
        }
    })
}); 




// ==================================================================

// 할 일 수정 (내 코드)
const updateLayer = document.querySelector("#updateLayer");
const updateTitle = document.querySelector("#updateTitle");
const updateContent = document.querySelector("#updateContent");
const updateBtn = document.querySelector("#updateBtn");
const updateCancelBtn = document.querySelector("#updateCancelBtn");

// updateView.addEventListener("click", () => {
//     popupLayer.classList.add("popup-hidden");
//     updateLayer.classList.remove("popup-hidden");

//     updateTitle.value = popupTodoTitle.innerText;
//     // 다른 태그에서 innerText든 innerHTML이든 해당 태그 사이의 text 또는 html(태그포함) 을 가져올 때 문자열 형태로 가져오기 때문에
//     // 그 사이에 있는 개행(문자)나 태그가 인식되지 않고 문자열 그대로 입력이 되버림
//     // ex) innerHTML = "테스트 내용3 <br> 테스트 내용3"
//     // 가운데 있는 <br>은 태그로서가 아닌 문자열로서 들어가기 때문에 <br> 그대로가 입력이 됨
//     updateContent.innerText = popupTodoContent.innerText;
// });

// // 수정취소 기능
// updateCancelBtn.addEventListener("click", () => {
//     popupLayer.classList.remove("popup-hidden");
//     updateLayer.classList.add("popup-hidden");
// });


// // 수정된 내용 저장 (내 코드)
// updateBtn.addEventListener("click", () => {

//     const param = {
//         "todoNo" : popupTodoNo.innerText,
//         "todoTitle" : updateTitle.value,
//         "todoContent" : updateContent.value
//     };

//     fetch("/ajax/update", {
//         method : "PUT",
//         headers : {"Content-type" : "application/json"},
//         body : JSON.stringify(param)
//     })
//     .then(resp => resp.text())
//     .then(result => {
//         if(result > 0){
//             alert("수정 성공!");

//             updateLayer.classList.add("popup-hidden");
//             popupLayer.classList.remove("popup-hidden");
//             selectTodo("/ajax/detail?todoNo=" + popupTodoNo.innerText);
//             selectTodoList();
//         } else {
//             alert("수정 실패...");
//         }
//     });
// });


// 할 일 수정 (강사님 코드)

// 상세 조회에서 수정 버튼 (#updateView) 클릭 시
updateView.addEventListener("click", () => {
    // 기존 팝업 레이어 숨기기
    popupLayer.classList.add("popup-hidden");
    updateLayer.classList.remove("popup-hidden");

    // 수정 레이어 보일 때
    // 팝업 레이어에 작성된 제목, 내용을 얻어와 세팅
    updateTitle.value = popupTodoTitle.innerText;
    updateContent.value = popupTodoContent.innerHTML.replaceAll("<br>", "\n");
    updateContent.value = popupTodoContent.innerText.replaceAll("&lt;", "<");
    updateContent.value = popupTodoContent.innerText.replaceAll("&gt;", ">");

    // HTML 화면에서 줄 바꿈이 <br>로 인식되고 있는데
    // textarea에서는 \n으로 바꿔줘야 줄 바꿈으로 인식된다

    // 수정 레이어 -> 수정 버튼에 data-todo-no 속성 추가
    updateBtn.setAttribute("data-todo-no", popupTodoNo.innerText);
});

// 수정 레이어에서 취소 버튼(#updateCancel)이 클릭되었을 때
updateCancelBtn.addEventListener("click", () => {
    updateLayer.classList.add("popup-hidden");
    popupLayer.classList.remove("popup-hidden");
});

updateBtn.addEventListener("click", e => {
    // 서버로 전달해야하는 값을 객체로 묶어둠
    const obj = {
        "todoNo" : e.target.dataset.todoNo,
        "todoTitle" : updateTitle.value,
        "todoContent" : updateContent.value
    }

    // 비동기 요청
    fetch("/ajax/update", {
        method : "PUT",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(obj)
    })
    .then(resp => resp.text())
    .then(result => {
        if(result > 0){
            alert("수정 성공!");
            
            // 수정 레이어 숨기기
            updateLayer.classList.add("popup-hidden");
            
            // 목록 다시 조회
            selectTodoList();
            
            // selectTodo("/ajax/detail?todoNo=" + e.target.dataset.todoNo);
            // 성능 개선 방법
            popupTodoTitle.innerText = updateTitle.value;
            popupTodoContent.innerHTML = updateContent.value.replaceAll("\n", "<br>");
            
            // 팝업 레이어 띄우기
            popupLayer.classList.remove("popup-hidden");

            // 수정 레이어에 있는 남은 흔적 제거
            updateTitle.value = "";
            updateContent.value = "";
            updateBtn.removeAttribute("data-todo-no"); // 속성 제거

        } else{
            alert("수정 실패..");
        }
    })
});