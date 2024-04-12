// 쿠키에서 key가 일치하는 value 얻어오기 함수

// 쿠키는 "Key = Value; Key=Value; ..." 형식

// 배열.map(함수) : 배열의 각 요소를 이용해 함수 수행 후 
//                 결과 값으로 새로운 배열을 만들어서 반환
const getCookie = (key) => {
    // JS에서도 cookie를 만들 수 있음
    // document.cookie = "test" + "=" + "유저일";

    const cookies = document.cookie; // "Key = Value; Key = Value; ..."

    // cookies 문자열을 배열 형태로 변환
    const cookieList = cookies.split("; ")  // ["K=V", "K=V", ...]
    .map( el => el.split("=") );  // ["K", "V"]...

    // console.log(cookieList);

    // 배열 -> 객체로 변환 (그래야 다루기 수월)
    
    const obj = {}; // 비어있는 객체 선언
    
    for(let i = 0; i < cookieList.length; i++){
        const k = cookieList[i][0];
        const v = cookieList[i][1];
        obj[k] = v; // 객체에 추가
    }

    // console.log(obj);

    return obj[key];  // 매개변수로 전달 받은 key와
                      // obj 객체에 저장된 Key가 일치하는 요소의 Value 값 반환
};

// console.log(getCookie("saveId"));

const loginEmail = document.querySelector("#loginForm input[name = 'memberEmail']");

// 로그인 안 된 상태인 경우에 수행
if(loginEmail != null) { // 로그인 창의 이메일 입력부분이 화면에 있을 때
    // 쿠키 중 key 값이 "saveId" 인 요소의 value 얻어오기
    const saveId = getCookie("saveId"); // undefined 또는 이메일

    // saveId 값이 있을 경우
    if(saveId != undefined) {
        loginEmail.value = saveId;  // 쿠키에서 얻어온 값을 input의 value로 세팅

        // 아이디 저장 체크박스에 체크 해두기
        document.querySelector("input[name='saveId']").checked = true;
    }
};



// ==================================================================================

// 이메일, 비밀번호 미작성 시 로그인 막기
const loginForm = document.querySelector("#loginForm");
const loginPw = document.querySelector("#loginForm input[name='memberPw']");

// #loginForm 이 화면에 존재할 때 (== 로그인 상태 아닐 때)
if(loginForm != null){
    // 제출 이벤트 발생 시
    loginForm.addEventListener("submit", e => {
        // 이메일 미작성
        if(loginEmail.value.trim().length === 0){
            alert("이메일을 작성해주세요!")
            e.preventDefault();  // 기본 이벤트(제출) 막기
            loginEmail.focus();  // 초점 이동
            return;
        }

        // 비밀번호 미작성
        if(loginPw.value.trim().length === 0){
            alert("비밀번호를 작성해주세요!")
            e.preventDefault();  // 기본 이벤트(제출) 막기
            loginPw.focus();  // 초점 이동
            return;
        }
    });
}