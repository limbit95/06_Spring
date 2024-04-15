// ------------------------------------------------------------------------------------------------------
// 회원 정보 수정 페이지 //
const updateInfo = document.querySelector("#updateInfo");

// #updateInfo 요소가 존재 할 때만 수행
if(updateInfo != null) {
    // form 제출 시
    updateInfo.addEventListener("submit", e => {
        const memberNickname = document.querySelector("#memberNickname");
        const memberTel = document.querySelector("#memberTel");
        const memberAddress = document.querySelectorAll("[name='memberAddress']");

        // 닉네임 유효성 검사
        if(memberNickname.value.trim().length === 0) {
            alert("닉네임을 입력해주세요");
            e.preventDefault();
            return;
        }
        
        let regExp = /^[가-힣\w\d]{2,10}$/;
        
        if(!regExp.test(memberNickname.value)){
            alert("닉네임이 유효하지 않습니다");
            e.preventDefault();
            return;
        }

        // 닉네임 중복검사는 개별적으로 하기
        // 테스트 시 닉네임 중복 안되게 조심하기

        // 전화번호 유효성 검사
        if(memberTel.value.trim().length === 0) {
            alert("전화번호를 입력해주세요");
            e.preventDefault();
            return;
        }

        regExp = /^01[0-9]{1}[0-9]{3,4}[0-9]{4}$/;

        if(!regExp.test(memberTel.value)){
            alert("전화번호가 유효하지 않습니다");
            e.preventDefault();
            return;
        }

        // 주소 유효성 검사
        // 입력을 안하면 전부 안해야되고
        // 입력을 하면 전부 해야됨

        const addr0 = memberAddress[0].value.trim().length == 0;
        const addr1 = memberAddress[1].value.trim().length == 0;
        const addr2 = memberAddress[2].value.trim().length == 0;

        // 모두 true 인 경우만 true 저장
        const result1 = addr0 && addr1 && addr2; // 아무것도 입력 X
        
        // 모두 false 인 경우만 true 저장
        const result2 = !(addr0 || addr1 || addr2); // 모두 다 입력
        
        // 모두 입력 또는 모두 미입력이 아니면
        if( !(result1 || result2) ) {
            alert("주소를 모두 작성 또는 미작성 해주세요")
            e.preventDefault();
        }
    });
};


// 다음 주소 API
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}

// 회원 정보 수정 페이지에서만 존재하는 주소 검색 버튼이 다른 사이드 메뉴 페이지에는 없기 때문에
// document.querySelector("#searchAddress") 요소가 없다며 오류 발생 -> 이 코드 밑의 코드들 실행되지 않음
// 그래서 버튼 요소를 얻어오려할 때 요소가 있으면 이라는 if문을 추가하여 오류 발생 막음
if(document.querySelector("#searchAddress") != null){
    document.querySelector("#searchAddress").addEventListener("click", execDaumPostcode);
};


// ------------------------------------------------------------------------------------------------------

// 비밀번호 수정 //

// 비밀번호 변경 form 태그
const changePw = document.querySelector("#changePw");

if(changePw != null){
    // 제출 되었을 때
    changePw.addEventListener("submit", e => {
        const currentPw = document.querySelector("#currentPw");
        const newPw = document.querySelector("#newPw");
        const newPwConfirm = document.querySelector("#newPwConfirm");

        // - 값을 모두 입력했는가

        let str; // undefined 상태
        if(currentPw.value.trim().length == 0)          str = "현재 비밀번호를 입력해주세요";
        else if(newPw.value.trim().length == 0)         str = "새 비밀번호를 입력해주세요";
        else if(newPwConfirm.value.trim().length == 0)  str = "새 비밀번호 확인을 입력해주세요";

        if(str != undefined){ // str에 값이 대입됨 == if 중 하나가 실행됨
            alert(str);
            e.preventDefault();
            return;
        }
        
        // 새 비밀번호 정규식
        const regExp = /^[a-zA-Z0-9!@#_-]{6,20}$/;
        
        if( !regExp.test(newPw.value) ){
            alert("새 비밀번호가 유효하지 않습니다");
            e.preventDefault();
            return;
        }
        
        // 새 비밀번호 == 새 비밀번호 확인 (일치하는지 검사)
        if( newPw.value != newPwConfirm.value ) {
            alert("새 비밀번호가 일치하지 않습니다");
            e.preventDefault();
            return;
        }
    });
};



// ------------------------------------------------------------------------------------------------------

// 회원 탈퇴 유효성 검사

// 탈퇴 form 태그
const secession = document.querySelector("#secession");

if(secession != null) {
    // 제출 되었을 때
    secession.addEventListener("submit", e => {
        const memberPw = document.querySelector("#memberPw");
        const agree = document.querySelector("#agree");

        if(memberPw.value.trim().length == 0){
            alert("비밀번호를 입력해주세요");
            e.preventDefault();
            return;
        }

        // checkbox 또는 radio는 checked 속성 이용 가능
        // checked -> 체크 시 true / 미체크시 false 반환
        if(!agree.checked) {
            alert("약관에 동의해주세요");
            e.preventDefault();
            return;
        }

        // 정말 탈퇴할 것인지 물어보기
        if(!confirm("정말 탈퇴 하시겠습니까?")){
            alert("취소 되었습니다");
            e.preventDefault();
            return;
        }
    });
}


// ------------------------------------------------------------------------------------------------------

// 프로필 이미지 변경
const profile = document.querySelector("#profile");

if(profile != null){
    profile.addEventListener("submit", e => {
        const imageInput = document.querySelector("#imageInput");
        
        console.log(imageInput);

        e.preventDefault();
        return;
    });
};