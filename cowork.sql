CREATE TABLE "EMPLOYEE" (
	"EMP_CODE"	NUMBER		NOT NULL,
	"EMP_NO"	NVARCHAR2(20)		NOT NULL,
	"EMP_ID"	NVARCHAR2(100)		NOT NULL,
	"EMP_PW"	NVARCHAR2(20)		NOT NULL,
	"EMP_LAST_NAME"	NVARCHAR2(20)		NOT NULL,
	"EMP_FIRST_NAME"	NVARCHAR2(30)		NOT NULL,
	"PHONE"	NVARCHAR2(20)		NOT NULL,
	"LANGUAGE"	NVARCHAR2(20)		NULL,
	"EMP_ADDRESS"	NVARCHAR2(150)		NULL,
	"EMP_BIRTH"	DATE		NOT NULL,
	"HIRE_DATE"	DATE		NOT NULL,
	"CONTRACT_TYPE"	NVARCHAR2(10)		NULL,
	"EMP_TEL"	NVARCHAR2(20)		NULL,
	"WORK_PLACE"	NVARCHAR2(150)		NULL,
	"EMP_EMAIL"	NVARCHAR2(100)		NULL,
	"COM_NO"	NUMBER		NOT NULL,
	"TEAM_NO"	NUMBER		NOT NULL,
	"POSITION_NO"	NUMBER		NOT NULL,
	"PROFILE_IMG"	NVARCHAR2(100)		NULL
);

COMMENT ON COLUMN "EMPLOYEE"."EMP_CODE" IS '사원 식별키';
COMMENT ON COLUMN "EMPLOYEE"."EMP_NO" IS '사원 번호';
COMMENT ON COLUMN "EMPLOYEE"."EMP_ID" IS '아이디';
COMMENT ON COLUMN "EMPLOYEE"."EMP_PW" IS '비밀번호';
COMMENT ON COLUMN "EMPLOYEE"."EMP_LAST_NAME" IS '사원 성';
COMMENT ON COLUMN "EMPLOYEE"."EMP_FIRST_NAME" IS '사원 이름';
COMMENT ON COLUMN "EMPLOYEE"."PHONE" IS '휴대전화';
COMMENT ON COLUMN "EMPLOYEE"."LANGUAGE" IS '언어';
COMMENT ON COLUMN "EMPLOYEE"."EMP_ADDRESS" IS '근무처';
COMMENT ON COLUMN "EMPLOYEE"."EMP_BIRTH" IS '생일';
COMMENT ON COLUMN "EMPLOYEE"."HIRE_DATE" IS '입사일';
COMMENT ON COLUMN "EMPLOYEE"."CONTRACT_TYPE" IS '계약 형태';
COMMENT ON COLUMN "EMPLOYEE"."EMP_TEL" IS '내선 번호';
COMMENT ON COLUMN "EMPLOYEE"."WORK_PLACE" IS '근무처';
COMMENT ON COLUMN "EMPLOYEE"."EMP_EMAIL" IS '이메일';
COMMENT ON COLUMN "EMPLOYEE"."COM_NO" IS '회사번호';
COMMENT ON COLUMN "EMPLOYEE"."TEAM_NO" IS '팀 기본키';
COMMENT ON COLUMN "EMPLOYEE"."POSITION_NO" IS '직급 번호';
COMMENT ON COLUMN "EMPLOYEE"."PROFILE_IMG" IS '프로필사진';

CREATE TABLE "DEPARTMENT" (
	"DEPT_NO"	NUMBER		NOT NULL,
	"DEPT_NM"	NVARCHAR2(30)		NOT NULL,
	"COM_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "DEPARTMENT"."DEPT_NO" IS '부서 번호';
COMMENT ON COLUMN "DEPARTMENT"."DEPT_NM" IS '부서명';
COMMENT ON COLUMN "DEPARTMENT"."COM_NO" IS '회사번호';

CREATE TABLE "NOTICE" (
	"NOTICE_NO"	NUMBER		NOT NULL,
	"NOTICE_TITLE"	NVARCHAR2(100)		NOT NULL,
	"NOTICE_CONTENT"	CLOB		NOT NULL,
	"NOTICE_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"NOTICE_UPDATE_DATE"	DATE		NULL,
	"NOTICE_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "NOTICE"."NOTICE_NO" IS '공지게시글 번호';
COMMENT ON COLUMN "NOTICE"."NOTICE_TITLE" IS '공지 제목';
COMMENT ON COLUMN "NOTICE"."NOTICE_CONTENT" IS '공지 내용';
COMMENT ON COLUMN "NOTICE"."NOTICE_WRITE_DATE" IS '공지 작성일';
COMMENT ON COLUMN "NOTICE"."NOTICE_UPDATE_DATE" IS '공지 수정일';
COMMENT ON COLUMN "NOTICE"."NOTICE_DEL_FL" IS '공지 삭제 여부';
COMMENT ON COLUMN "NOTICE"."EMP_CODE" IS '사원 식별키';

CREATE TABLE "TEAM_BOARD" (
	"TEAM_BOARD_NO"	NUMBER		NOT NULL,
	"TEAM_BOARD_TITLE"	NVARCHAR2(100)		NOT NULL,
	"TEAM_BOARD_CONTENT"	CLOB		NOT NULL,
	"TEAM_BOARD_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"TEAM_BOARD_UPDATE_DATE"	DATE		NULL,
	"TEAM_BOARD_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL,
	"TEAM_NO"	NUMBER		NOT NULL,
	"TEAM_FLAG"	CHAR(1)		NOT NULL
);

COMMENT ON COLUMN "TEAM_BOARD"."TEAM_BOARD_NO" IS '공지게시글 번호';
COMMENT ON COLUMN "TEAM_BOARD"."TEAM_BOARD_TITLE" IS '공지 제목';
COMMENT ON COLUMN "TEAM_BOARD"."TEAM_BOARD_CONTENT" IS '공지 내용';
COMMENT ON COLUMN "TEAM_BOARD"."TEAM_BOARD_WRITE_DATE" IS '공지 작성일';
COMMENT ON COLUMN "TEAM_BOARD"."TEAM_BOARD_UPDATE_DATE" IS '공지 수정일';
COMMENT ON COLUMN "TEAM_BOARD"."TEAM_BOARD_DEL_FL" IS '공지 삭제 여부';
COMMENT ON COLUMN "TEAM_BOARD"."EMP_CODE" IS '사원 식별키';
COMMENT ON COLUMN "TEAM_BOARD"."TEAM_NO" IS '팀 기본키';
COMMENT ON COLUMN "TEAM_BOARD"."TEAM_FLAG" IS '구분코드(N, D)';

CREATE TABLE "TEAM" (
	"TEAM_NO"	NUMBER		NOT NULL,
	"TEAM_NM"	NVARCHAR2(30)		NOT NULL,
	"DEPT_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "TEAM"."TEAM_NO" IS '팀 기본키';
COMMENT ON COLUMN "TEAM"."TEAM_NM" IS '팀 명';
COMMENT ON COLUMN "TEAM"."DEPT_NO" IS '부서 번호';

CREATE TABLE "CHAT_ROOM" (
	"ROOM_NO"	NUMBER		NOT NULL,
	"ROOM_NAME"	NVARCHAR2(300)		NOT NULL,
	"CREATED_AT"	DATE		NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "CHAT_ROOM"."ROOM_NO" IS '채팅방 기본키';
COMMENT ON COLUMN "CHAT_ROOM"."ROOM_NAME" IS '채팅방 이름';
COMMENT ON COLUMN "CHAT_ROOM"."CREATED_AT" IS '채팅방 생성일';
COMMENT ON COLUMN "CHAT_ROOM"."EMP_CODE" IS '채팅방생성자';

CREATE TABLE "SURVEY" (
	"SURVEY_NO"	NUMBER		NOT NULL,
	"SURVEY_MAIN_TITLE"	NVARCHAR2(100)		NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "SURVEY"."SURVEY_NO" IS '설문 기본키';
COMMENT ON COLUMN "SURVEY"."SURVEY_MAIN_TITLE" IS '설문제목';
COMMENT ON COLUMN "SURVEY"."EMP_CODE" IS '작성자코드';

CREATE TABLE "POSITION" (
	"POSITION_NO"	NUMBER		NOT NULL,
	"POSITION_NM"	NVARCHAR2(20)		NOT NULL,
	"LEVEL"	NUMBER		NOT NULL,
	"COM_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "POSITION"."POSITION_NO" IS '직급 번호';
COMMENT ON COLUMN "POSITION"."POSITION_NM" IS '직급명';
COMMENT ON COLUMN "POSITION"."LEVEL" IS '레벨';
COMMENT ON COLUMN "POSITION"."COM_NO" IS '회사번호';

CREATE TABLE "AUTHORITY" (
	"AUTHORITY_NO"	NUMBER		NOT NULL,
	"AUTHORITY_NAME"	NVARCHAR2(30)		NOT NULL
);

COMMENT ON COLUMN "AUTHORITY"."AUTHORITY_NO" IS '권한 식별키';
COMMENT ON COLUMN "AUTHORITY"."AUTHORITY_NAME" IS '권한명';

CREATE TABLE "COMMENT" (
	"COMMENT__NO"	NUMBER		NOT NULL,
	"COMMENT_CONTENT"	NVARCHAR2(2000)		NOT NULL,
	"COMMENT_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"COMMENT_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"PARENT_COMMENT_NO"	NUMBER		NOT NULL,
	"TEAM_BOARD_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "COMMENT"."COMMENT__NO" IS '댓글 번호';
COMMENT ON COLUMN "COMMENT"."COMMENT_CONTENT" IS '댓글 내용';
COMMENT ON COLUMN "COMMENT"."COMMENT_WRITE_DATE" IS '댓글 작성일';
COMMENT ON COLUMN "COMMENT"."COMMENT_DEL_FL" IS '댓글 삭제 여부';
COMMENT ON COLUMN "COMMENT"."PARENT_COMMENT_NO" IS '부모 댓글 번호';
COMMENT ON COLUMN "COMMENT"."TEAM_BOARD_NO" IS '공지게시글 번호';

CREATE TABLE "SUBSCRIBE_ADDR" (
	"SUB_ADDR_NO"	NUMBER		NOT NULL,
	"SUB_ADDR"	NUMBER		NOT NULL,
	"ROOM_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "SUBSCRIBE_ADDR"."SUB_ADDR_NO" IS '구독주소 기본키';
COMMENT ON COLUMN "SUBSCRIBE_ADDR"."SUB_ADDR" IS '구독주소';
COMMENT ON COLUMN "SUBSCRIBE_ADDR"."ROOM_NO" IS '채팅방 기본키';

CREATE TABLE "AUTHORITY_MEMBER" (
	"AUTHORITY_MEMBER_NO"	NUMBER		NOT NULL,
	"AUTHORITY_NO"	NUMBER		NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "AUTHORITY_MEMBER"."AUTHORITY_MEMBER_NO" IS '멤버 권한 식별키';
COMMENT ON COLUMN "AUTHORITY_MEMBER"."AUTHORITY_NO" IS '권한 식별키';
COMMENT ON COLUMN "AUTHORITY_MEMBER"."EMP_CODE" IS '사원 식별키';

CREATE TABLE "SURVEY_MULTIPLE" (
	"SURVEY_INFO_NO"	NUMBER		NOT NULL,
	"SURVEY_INFO_CONTENT"	NVARCHAR2(100)		NOT NULL,
	"SURVEY_ORDER"	NUMBER		NOT NULL,
	"SURVEY_SUB_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "SURVEY_MULTIPLE"."SURVEY_INFO_CONTENT" IS '객관식 보기';
COMMENT ON COLUMN "SURVEY_MULTIPLE"."SURVEY_ORDER" IS '순서';
COMMENT ON COLUMN "SURVEY_MULTIPLE"."SURVEY_SUB_NO" IS '소제목 기본키';

CREATE TABLE "COMPANY" (
	"COM_NO"	NUMBER		NOT NULL,
	"COM_NM"	NVARCHAR2(20)		NOT NULL,
	"COM_TEL"	NVARCHAR2(20)		NULL,
	"LICENSE_NO"	NVARCHAR2(10)		NULL,
	"CEO_NM"	NVARCHAR2(15)		NULL,
	"COM_ADDR"	NVARCHAR2(150)		NULL,
	"COM_LOGO"	NVARCHAR2(100)		NULL,
	"LICENSE_IMG"	NVARCHAR2(100)		NULL,
	"COM_EMAIL"	NVARCHAR2(100)		NULL,
	"DOMAIN"	NVARCHAR2(20)		NULL
);

COMMENT ON COLUMN "COMPANY"."COM_NO" IS '회사번호';
COMMENT ON COLUMN "COMPANY"."COM_NM" IS '회사명';
COMMENT ON COLUMN "COMPANY"."COM_TEL" IS '대표 번호';
COMMENT ON COLUMN "COMPANY"."LICENSE_NO" IS '사업자 등록 번호';
COMMENT ON COLUMN "COMPANY"."CEO_NM" IS '대표자명';
COMMENT ON COLUMN "COMPANY"."COM_ADDR" IS '회사 주소';
COMMENT ON COLUMN "COMPANY"."COM_LOGO" IS '회사 로고';
COMMENT ON COLUMN "COMPANY"."LICENSE_IMG" IS '사업자등록증';
COMMENT ON COLUMN "COMPANY"."COM_EMAIL" IS '회사이메일';
COMMENT ON COLUMN "COMPANY"."DOMAIN" IS '도메인';

CREATE TABLE "CALENDAR" (
	"CALENDAR_NO"	NUMBER		NOT NULL,
	"CALENDAR_TITLE"	NVARCHAR2(50)		NOT NULL,
	"CALENDAR_CONTENT"	NVARCHAR2(500)		NULL,
	"CALENDAR_START"	DATE		NOT NULL,
	"CALENDAR_END"	DATE		NOT NULL,
	"CALENDAR_COLOR"	NVARCHAR2(50)	DEFAULT 'blue'	NOT NULL,
	"CALENDAR_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"CALENDAR_UPDATE_DATE"	DATE		NULL,
	"EMP_CODE"	NUMBER		NOT NULL,
	"TEAM_SHARE"	CHAR(1)	DEFAULT 'N'	NOT NULL
);

COMMENT ON COLUMN "CALENDAR"."CALENDAR_NO" IS '캘린터 기본키';
COMMENT ON COLUMN "CALENDAR"."CALENDAR_TITLE" IS '일정 제목';
COMMENT ON COLUMN "CALENDAR"."CALENDAR_CONTENT" IS '일정 상세';
COMMENT ON COLUMN "CALENDAR"."CALENDAR_START" IS '일정 시작일';
COMMENT ON COLUMN "CALENDAR"."CALENDAR_END" IS '일정 마감일';
COMMENT ON COLUMN "CALENDAR"."CALENDAR_COLOR" IS '일정 지정색';
COMMENT ON COLUMN "CALENDAR"."CALENDAR_WRITE_DATE" IS '일정생성일';
COMMENT ON COLUMN "CALENDAR"."CALENDAR_UPDATE_DATE" IS '일정 수정일';
COMMENT ON COLUMN "CALENDAR"."EMP_CODE" IS '사원 식별키';
COMMENT ON COLUMN "CALENDAR"."TEAM_SHARE" IS '팀공유여부';

CREATE TABLE "BUISNESS_CARD" (
	"CARD_NO"	NUMBER		NOT NULL,
	"CARD_IMG"	NVARCHAR2(100)		NULL
);

COMMENT ON COLUMN "BUISNESS_CARD"."CARD_NO" IS '명함 기본키';
COMMENT ON COLUMN "BUISNESS_CARD"."CARD_IMG" IS '명함이미지';

CREATE TABLE "BOARD_FILE" (
	"FILE_NO"	NUMBER		NOT NULL,
	"FILE_PATH"	NVARCHAR2(300)		NOT NULL,
	"FILE_ORIGIN_NAME"	NVARCHAR2(300)		NOT NULL,
	"FILE_RENAME"	NVARCHAR2(100)		NOT NULL,
	"FILE_UPLOAD_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"FILE_ORDER"	NUMBER	DEFAULT 1	NOT NULL,
	"BOARD_NO"	NUMBER		NOT NULL,
	"TEAM_BOARD_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "BOARD_FILE"."FILE_NO" IS '파일 번호';
COMMENT ON COLUMN "BOARD_FILE"."FILE_PATH" IS '파일 경로';
COMMENT ON COLUMN "BOARD_FILE"."FILE_ORIGIN_NAME" IS '파일 원본명';
COMMENT ON COLUMN "BOARD_FILE"."FILE_RENAME" IS '파일 변경명';
COMMENT ON COLUMN "BOARD_FILE"."FILE_UPLOAD_DATE" IS '파일 업로드 날짜';
COMMENT ON COLUMN "BOARD_FILE"."FILE_ORDER" IS '파일 순서';
COMMENT ON COLUMN "BOARD_FILE"."BOARD_NO" IS '게시글번호(팀,공지)';
COMMENT ON COLUMN "BOARD_FILE"."TEAM_BOARD_NO" IS '공지게시글 번호';

CREATE TABLE "TODO" (
	"TODO_NO"	NUMBER		NOT NULL,
	"TODO_TITLE"	NVARCHAR2(100)		NOT NULL,
	"TODO_CONTENT"	NVARCHAR2(2000)		NOT NULL,
	"TODO_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"TODO_END_DATE"	DATE		NULL,
	"TODO_COMPLETE"	CHAR(1)		NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "TODO"."TODO_NO" IS '할일번호';
COMMENT ON COLUMN "TODO"."TODO_TITLE" IS '할일제목';
COMMENT ON COLUMN "TODO"."TODO_CONTENT" IS '할일제목';
COMMENT ON COLUMN "TODO"."TODO_WRITE_DATE" IS '할일작성일';
COMMENT ON COLUMN "TODO"."TODO_END_DATE" IS '할일마감일';
COMMENT ON COLUMN "TODO"."TODO_COMPLETE" IS '할일여부(진행중,완료,만료)';
COMMENT ON COLUMN "TODO"."EMP_CODE" IS '사원 식별키';

CREATE TABLE "TODO_FILE" (
	"FILE_NO"	NUMBER		NOT NULL,
	"FILE_PATH"	NVARCHAR2(300)		NOT NULL,
	"FILE_ORIGIN_NAME"	NVARCHAR2(300)		NOT NULL,
	"FILE_RENAME"	NVARCHAR2(100)		NOT NULL,
	"FILE_UPLOAD_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"FILE_ORDER"	NUMBER	DEFAULT 1	NOT NULL,
	"TODO_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "TODO_FILE"."FILE_NO" IS '파일 번호';
COMMENT ON COLUMN "TODO_FILE"."FILE_PATH" IS '파일 경로';
COMMENT ON COLUMN "TODO_FILE"."FILE_ORIGIN_NAME" IS '파일 원본명';
COMMENT ON COLUMN "TODO_FILE"."FILE_RENAME" IS '파일 변경명';
COMMENT ON COLUMN "TODO_FILE"."FILE_UPLOAD_DATE" IS '파일 업로드 날짜';
COMMENT ON COLUMN "TODO_FILE"."FILE_ORDER" IS '파일 순서';
COMMENT ON COLUMN "TODO_FILE"."TODO_NO" IS '할일번호';

CREATE TABLE "TODO_MANAGER" (
	"TODO_MGR_NO"	NUMBER		NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL,
	"TODO_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "TODO_MANAGER"."TODO_MGR_NO" IS '담당자식별번호';
COMMENT ON COLUMN "TODO_MANAGER"."EMP_CODE" IS '담당자명';
COMMENT ON COLUMN "TODO_MANAGER"."TODO_NO" IS '할일번호';

CREATE TABLE "CHAT_PARTICIPANT" (
	"PARTICIPANT_NO"	NUMBER		NOT NULL,
	"JOINED_AT"	DATE		NOT NULL,
	"ROOM_NO"	NUMBER		NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "CHAT_PARTICIPANT"."PARTICIPANT_NO" IS '참여자 기본키';
COMMENT ON COLUMN "CHAT_PARTICIPANT"."JOINED_AT" IS '채팅 참여 일시';
COMMENT ON COLUMN "CHAT_PARTICIPANT"."ROOM_NO" IS '채팅방 기본키';
COMMENT ON COLUMN "CHAT_PARTICIPANT"."EMP_CODE" IS '참여자';

CREATE TABLE "CHAT_MESSAGE" (
	"MESSAGE_NO"	NUMBER		NOT NULL,
	"CONTENT"	NVARCHAR2(2000)		NOT NULL,
	"SENT_AT"	DATE	DEFAULT SYSDATE	NOT NULL,
	"ROOM_NO"	NUMBER		NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "CHAT_MESSAGE"."MESSAGE_NO" IS '메세지 기본키';
COMMENT ON COLUMN "CHAT_MESSAGE"."CONTENT" IS '내용';
COMMENT ON COLUMN "CHAT_MESSAGE"."SENT_AT" IS '보낸시각';
COMMENT ON COLUMN "CHAT_MESSAGE"."ROOM_NO" IS '채팅방 기본키';
COMMENT ON COLUMN "CHAT_MESSAGE"."EMP_CODE" IS '보낸사람';

CREATE TABLE "CHAT_FILE" (
	"FILE_NO"	NUMBER		NOT NULL,
	"FILE_PATH"	NVARCHAR2(300)		NOT NULL,
	"FILE_ORIGIN_NAME"	NVARCHAR2(300)		NOT NULL,
	"FILE_RENAME"	NVARCHAR2(100)		NOT NULL,
	"FILE_UPLOAD_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"MESSAGE_NO"	NUMBER		NOT NULL,
	"EMP_ID"	NVARCHAR2(100)		NOT NULL
);

COMMENT ON COLUMN "CHAT_FILE"."FILE_NO" IS '파일 번호';
COMMENT ON COLUMN "CHAT_FILE"."FILE_PATH" IS '파일 경로';
COMMENT ON COLUMN "CHAT_FILE"."FILE_ORIGIN_NAME" IS '파일 원본명';
COMMENT ON COLUMN "CHAT_FILE"."FILE_RENAME" IS '파일 변경명';
COMMENT ON COLUMN "CHAT_FILE"."FILE_UPLOAD_DATE" IS '파일 업로드 날짜';
COMMENT ON COLUMN "CHAT_FILE"."MESSAGE_NO" IS '메세지 기본키';
COMMENT ON COLUMN "CHAT_FILE"."EMP_ID" IS '아이디';

CREATE TABLE "RESERVE_INFO" (
	"RESERVE_INFO_NO"	NUMBER		NOT NULL,
	"RESERVE_INFO_TITLE"	NVARCHAR2(50)		NOT NULL,
	"RESERVE_INFO_CONTENT"	NVARCHAR2(500)		NULL,
	"RESERVE_INFO_START"	DATE		NOT NULL,
	"RESERVE_INFO_END"	DATE		NOT NULL,
	"RESERVE_INFO_COLOR"	NVARCHAR2(50)	DEFAULT 'blue'	NOT NULL,
	"RESERVE_INFO_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"RESERVE_INFO_UPDATE_DATE"	DATE		NULL,
	"EMP_CODE"	NUMBER		NOT NULL,
	"TEAM_SHARE"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"MEETING_ROOM_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "RESERVE_INFO"."RESERVE_INFO_NO" IS '캘린터 기본키';
COMMENT ON COLUMN "RESERVE_INFO"."RESERVE_INFO_TITLE" IS '일정 제목';
COMMENT ON COLUMN "RESERVE_INFO"."RESERVE_INFO_CONTENT" IS '일정 상세';
COMMENT ON COLUMN "RESERVE_INFO"."RESERVE_INFO_START" IS '일정 시작일';
COMMENT ON COLUMN "RESERVE_INFO"."RESERVE_INFO_END" IS '일정 마감일';
COMMENT ON COLUMN "RESERVE_INFO"."RESERVE_INFO_COLOR" IS '일정 지정색';
COMMENT ON COLUMN "RESERVE_INFO"."RESERVE_INFO_WRITE_DATE" IS '일정생성일';
COMMENT ON COLUMN "RESERVE_INFO"."RESERVE_INFO_UPDATE_DATE" IS '일정 수정일';
COMMENT ON COLUMN "RESERVE_INFO"."EMP_CODE" IS '사원 식별키';
COMMENT ON COLUMN "RESERVE_INFO"."TEAM_SHARE" IS '팀공유여부';
COMMENT ON COLUMN "RESERVE_INFO"."MEETING_ROOM_NO" IS '회의실 기본키';

CREATE TABLE "MEETING_ROOM" (
	"MEETING_ROOM_NO"	NUMBER		NOT NULL,
	"MEETING_ROOM_NM"	NVARCHAR2(30)		NOT NULL,
	"COM_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "MEETING_ROOM"."MEETING_ROOM_NO" IS '회의실 기본키';
COMMENT ON COLUMN "MEETING_ROOM"."MEETING_ROOM_NM" IS '회의실명';
COMMENT ON COLUMN "MEETING_ROOM"."COM_NO" IS '회사번호';

CREATE TABLE "SURVEY_SUB" (
	"SURVEY_SUB_NO"	NUMBER		NOT NULL,
	"SURVEY_SUB_TITLE"	NVARCHAR2(100)		NOT NULL,
	"SURVEY_NO"	NUMBER		NOT NULL,
	"SUB_FLAG"	CHAR(1)		NOT NULL
);

COMMENT ON COLUMN "SURVEY_SUB"."SURVEY_SUB_NO" IS '소제목 기본키';
COMMENT ON COLUMN "SURVEY_SUB"."SURVEY_SUB_TITLE" IS '소제목';
COMMENT ON COLUMN "SURVEY_SUB"."SURVEY_NO" IS '설문 기본키';
COMMENT ON COLUMN "SURVEY_SUB"."SUB_FLAG" IS '구분키(객관식, 주관식 1,2)';

CREATE TABLE "SURVEY_ANSWER" (
	"ANSWER_NO"	NUMBER		NOT NULL,
	"ANSWER_CONTENT"	NVARCHAR2(300)		NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL,
	"SURVEY_SUB_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "SURVEY_ANSWER"."ANSWER_NO" IS '답변 기본키';
COMMENT ON COLUMN "SURVEY_ANSWER"."ANSWER_CONTENT" IS '답변 내용';
COMMENT ON COLUMN "SURVEY_ANSWER"."EMP_CODE" IS '사원 식별키';
COMMENT ON COLUMN "SURVEY_ANSWER"."SURVEY_SUB_NO" IS '소제목 기본키';

CREATE TABLE "MAIL" (
	"MAIL_NO"	NUMBER		NOT NULL,
	"MAIL_TITLE"	NVARCHAR2(100)		NOT NULL,
	"MAIL_CONTENT"	CLOB		NOT NULL,
	"MAIL_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"MAIL_FLAG"	CHAR(1)	DEFAULT 1	NULL,
	"EMP_CODE"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "MAIL"."MAIL_NO" IS '메일 번호';
COMMENT ON COLUMN "MAIL"."MAIL_TITLE" IS '메일 제목';
COMMENT ON COLUMN "MAIL"."MAIL_CONTENT" IS '메일 내용';
COMMENT ON COLUMN "MAIL"."MAIL_WRITE_DATE" IS '보낸 시간';
COMMENT ON COLUMN "MAIL"."MAIL_FLAG" IS '메일 구분(1 기본, 2임시, 3휴지통)';
COMMENT ON COLUMN "MAIL"."EMP_CODE" IS '보낸 사람';

CREATE TABLE "MAIL_FILE" (
	"FILE_NO"	NUMBER		NOT NULL,
	"FILE_PATH"	NVARCHAR2(300)		NOT NULL,
	"FILE_ORIGIN_NAME"	NVARCHAR2(300)		NOT NULL,
	"FILE_RENAME"	NVARCHAR2(100)		NOT NULL,
	"FILE_UPLOAD_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"FILE_ORDER"	NUMBER	DEFAULT 1	NOT NULL,
	"MAIL_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "MAIL_FILE"."FILE_NO" IS '파일 번호';
COMMENT ON COLUMN "MAIL_FILE"."FILE_PATH" IS '파일 경로';
COMMENT ON COLUMN "MAIL_FILE"."FILE_ORIGIN_NAME" IS '파일 원본명';
COMMENT ON COLUMN "MAIL_FILE"."FILE_RENAME" IS '파일 변경명';
COMMENT ON COLUMN "MAIL_FILE"."FILE_UPLOAD_DATE" IS '파일 업로드 날짜';
COMMENT ON COLUMN "MAIL_FILE"."FILE_ORDER" IS '파일 순서';
COMMENT ON COLUMN "MAIL_FILE"."MAIL_NO" IS '메일 번호';

CREATE TABLE "RECIPIENT" (
	"RECIPIENT_NO"	NUMBER		NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL,
	"MAIL_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "RECIPIENT"."RECIPIENT_NO" IS '메일 받은 사람';
COMMENT ON COLUMN "RECIPIENT"."EMP_CODE" IS '사원 식별키';
COMMENT ON COLUMN "RECIPIENT"."MAIL_NO" IS '메일 번호';

CREATE TABLE "EDSM" (
	"EDSM_NO"	NUMBER		NOT NULL,
	"EDSM_TITLE"	NVARCHAR2(100)		NOT NULL,
	"EDSM_CONTENT"	CLOB		NOT NULL,
	"EDSM_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"EDSM_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL,
	"EDSM_FLAG"	CHAR(1)		NOT NULL
);

COMMENT ON COLUMN "EDSM"."EDSM_NO" IS '전자결재 식별키';
COMMENT ON COLUMN "EDSM"."EDSM_TITLE" IS '전자결재 제목';
COMMENT ON COLUMN "EDSM"."EDSM_CONTENT" IS '전자결재 내용';
COMMENT ON COLUMN "EDSM"."EDSM_WRITE_DATE" IS '전자결재 작성일';
COMMENT ON COLUMN "EDSM"."EDSM_DEL_FL" IS '전자결재 삭제 여부';
COMMENT ON COLUMN "EDSM"."EMP_CODE" IS '사원 식별키';
COMMENT ON COLUMN "EDSM"."EDSM_FLAG" IS '전자결재 구분키(1 승인, 2반려, 3회수)';

CREATE TABLE "EDSM_FILE" (
	"FILE_NO"	NUMBER		NOT NULL,
	"FILE_PATH"	NVARCHAR2(300)		NOT NULL,
	"FILE_ORIGIN_NAME"	NVARCHAR2(300)		NOT NULL,
	"FILE_RENAME"	NVARCHAR2(100)		NOT NULL,
	"FILE_UPLOAD_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"FILE_ORDER"	NUMBER	DEFAULT 1	NOT NULL,
	"BOARD_NO"	NUMBER		NOT NULL,
	"EDSM_NO"	NUMBER		NOT NULL,
	"EDSM_NO2"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "EDSM_FILE"."FILE_NO" IS '파일 번호';
COMMENT ON COLUMN "EDSM_FILE"."FILE_PATH" IS '파일 경로';
COMMENT ON COLUMN "EDSM_FILE"."FILE_ORIGIN_NAME" IS '파일 원본명';
COMMENT ON COLUMN "EDSM_FILE"."FILE_RENAME" IS '파일 변경명';
COMMENT ON COLUMN "EDSM_FILE"."FILE_UPLOAD_DATE" IS '파일 업로드 날짜';
COMMENT ON COLUMN "EDSM_FILE"."FILE_ORDER" IS '파일 순서';
COMMENT ON COLUMN "EDSM_FILE"."BOARD_NO" IS '게시글번호(팀,공지)';
COMMENT ON COLUMN "EDSM_FILE"."EDSM_NO" IS '전자결재 식별키';
COMMENT ON COLUMN "EDSM_FILE"."EDSM_NO2" IS '전자결재 식별키';

CREATE TABLE "DRAFT" (
	"DRAFT_NO"	NUMBER		NOT NULL,
	"DRAFT_TITLE"	NVARCHAR2(100)		NOT NULL,
	"EDSM_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "DRAFT"."DRAFT_NO" IS '기안 식별키';
COMMENT ON COLUMN "DRAFT"."DRAFT_TITLE" IS '기안 제목';
COMMENT ON COLUMN "DRAFT"."EDSM_NO" IS '전자결재 식별키';

CREATE TABLE "APPROVER" (
	"APPROVER_NO"	NUMBER		NOT NULL,
	"APPROVER_FLAG"	CHAR(1)		NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL,
	"PROGRESS_FLAG"	CHAR(1)		NULL,
	"APPROVE_FLAG"	CHAR(1)		NULL,
	"EDSM_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "APPROVER"."APPROVER_NO" IS '결재자 식별키';
COMMENT ON COLUMN "APPROVER"."APPROVER_FLAG" IS '구분키(1결재자 2 참조인)';
COMMENT ON COLUMN "APPROVER"."EMP_CODE" IS '사원 식별키';
COMMENT ON COLUMN "APPROVER"."PROGRESS_FLAG" IS '진행 여부 (1진행중 2 완료)';
COMMENT ON COLUMN "APPROVER"."APPROVE_FLAG" IS '승인여부(1 승인 2 반려)';
COMMENT ON COLUMN "APPROVER"."EDSM_NO" IS '전자결재 식별키';

CREATE TABLE "REFER" (
	"REFER_NO"	NUMBER		NOT NULL,
	"REASON"	NVARCHAR2(500)		NOT NULL,
	"EDSM_NO"	NUMBER		NOT NULL,
	"APPROVER_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "REFER"."REFER_NO" IS '반려 식별키';
COMMENT ON COLUMN "REFER"."REASON" IS '반려 사유';
COMMENT ON COLUMN "REFER"."EDSM_NO" IS '전자결재 식별키';
COMMENT ON COLUMN "REFER"."APPROVER_NO" IS '결재자 식별키';

CREATE TABLE "ATTENDENCE" (
	"ATTENDANCE_KEY"	NUMBER		NOT NULL,
	"ATTENDANCE_TIME"	DATE	DEFAULT SYSDATE	NOT NULL,
	"OFF_TIME"	DATE		NULL,
	"EMP_CODE"	NUMBER		NOT NULL,
	"ATTENDANCE_FLAG"	CHAR(1)		NULL
);

COMMENT ON COLUMN "ATTENDENCE"."ATTENDANCE_KEY" IS '근태 식별키';
COMMENT ON COLUMN "ATTENDENCE"."ATTENDANCE_TIME" IS '출근시간';
COMMENT ON COLUMN "ATTENDENCE"."OFF_TIME" IS '퇴근 시간';
COMMENT ON COLUMN "ATTENDENCE"."EMP_CODE" IS '사원 식별키';
COMMENT ON COLUMN "ATTENDENCE"."ATTENDANCE_FLAG" IS '근태 구분(1 휴가, 2 정상, 3 지각, 4 조퇴 , 5 결근)';

CREATE TABLE "STANDARD_ATD" (
	"STANDARD_ATD_NO"	NUMBER		NOT NULL,
	"STANDARD_IN_TIME"	DATE		NOT NULL,
	"STANDARD_OFF_TIME"	DATE		NOT NULL,
	"COM_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "STANDARD_ATD"."STANDARD_ATD_NO" IS '근태 기준 식별키';
COMMENT ON COLUMN "STANDARD_ATD"."STANDARD_IN_TIME" IS '기준 출근시간';
COMMENT ON COLUMN "STANDARD_ATD"."STANDARD_OFF_TIME" IS '기준 퇴근 시간';
COMMENT ON COLUMN "STANDARD_ATD"."COM_NO" IS '회사번호';

CREATE TABLE "IP" (
	"IP_NO"	NUMBER		NOT NULL,
	"IP"	NVARCHAR2(30)		NOT NULL,
	"COM_NO"	NUMBER		NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "IP"."IP_NO" IS '아이피 식별키';
COMMENT ON COLUMN "IP"."IP" IS '아이피';
COMMENT ON COLUMN "IP"."COM_NO" IS '회사번호';
COMMENT ON COLUMN "IP"."EMP_CODE" IS '사원 식별키';

CREATE TABLE "USER_CARD" (
	"USER_CARD_NO"	NUMBER		NOT NULL,
	"EMP_CODE"	NUMBER		NOT NULL,
	"CARD_NO2"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "USER_CARD"."USER_CARD_NO" IS '사용자 명함 기본키';
COMMENT ON COLUMN "USER_CARD"."EMP_CODE" IS '사원 식별키';
COMMENT ON COLUMN "USER_CARD"."CARD_NO2" IS '명함 기본키';

CREATE TABLE "UPDATE_ATD" (
	"UPDATE_ATD_NO"	NUMBER		NOT NULL,
	"UPDATE_ATD_TIME"	DATE		NOT NULL,
	"UPDATE_OFF_TIME"	DATE		NOT NULL,
	"UPDATE_ATD_DATE"	DATE		NOT NULL,
	"REASON"	NVARCHAR2(500)		NOT NULL,
	"APPROVE_FLAG"	CHAR(1)	DEFAULT 3	NOT NULL,
	"REFER_REASON"	NVARCHAR2(500)		NULL,
	"EMP_CODE"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "UPDATE_ATD"."UPDATE_ATD_NO" IS '수정 식별키';
COMMENT ON COLUMN "UPDATE_ATD"."UPDATE_ATD_TIME" IS '요청 출근 시간';
COMMENT ON COLUMN "UPDATE_ATD"."UPDATE_OFF_TIME" IS '요청 퇴근 시간';
COMMENT ON COLUMN "UPDATE_ATD"."UPDATE_ATD_DATE" IS '요청 근무 일자';
COMMENT ON COLUMN "UPDATE_ATD"."REASON" IS '사유';
COMMENT ON COLUMN "UPDATE_ATD"."APPROVE_FLAG" IS '승인여부(1.승인 2반려 3확인중))';
COMMENT ON COLUMN "UPDATE_ATD"."REFER_REASON" IS '반려 사유';
COMMENT ON COLUMN "UPDATE_ATD"."EMP_CODE" IS '사원 식별키';

------------------------------------------------------------------------------

ALTER TABLE "EMPLOYEE" ADD CONSTRAINT "PK_EMPLOYEE" PRIMARY KEY (
	"EMP_CODE"
);

ALTER TABLE "DEPARTMENT" ADD CONSTRAINT "PK_DEPARTMENT" PRIMARY KEY (
	"DEPT_NO"
);

ALTER TABLE "NOTICE" ADD CONSTRAINT "PK_NOTICE" PRIMARY KEY (
	"NOTICE_NO"
);

ALTER TABLE "TEAM_BOARD" ADD CONSTRAINT "PK_TEAM_BOARD" PRIMARY KEY (
	"TEAM_BOARD_NO"
);

ALTER TABLE "TEAM" ADD CONSTRAINT "PK_TEAM" PRIMARY KEY (
	"TEAM_NO"
);

ALTER TABLE "CHAT_ROOM" ADD CONSTRAINT "PK_CHAT_ROOM" PRIMARY KEY (
	"ROOM_NO"
);

ALTER TABLE "SURVEY" ADD CONSTRAINT "PK_SURVEY" PRIMARY KEY (
	"SURVEY_NO"
);

ALTER TABLE "POSITION" ADD CONSTRAINT "PK_POSITION" PRIMARY KEY (
	"POSITION_NO"
);

ALTER TABLE "AUTHORITY" ADD CONSTRAINT "PK_AUTHORITY" PRIMARY KEY (
	"AUTHORITY_NO"
);

ALTER TABLE "COMMENT" ADD CONSTRAINT "PK_COMMENT" PRIMARY KEY (
	"COMMENT__NO"
);

ALTER TABLE "SUBSCRIBE_ADDR" ADD CONSTRAINT "PK_SUBSCRIBE_ADDR" PRIMARY KEY (
	"SUB_ADDR_NO"
);

ALTER TABLE "AUTHORITY_MEMBER" ADD CONSTRAINT "PK_AUTHORITY_MEMBER" PRIMARY KEY (
	"AUTHORITY_MEMBER_NO"
);

ALTER TABLE "SURVEY_MULTIPLE" ADD CONSTRAINT "PK_SURVEY_MULTIPLE" PRIMARY KEY (
	"SURVEY_INFO_NO"
);

ALTER TABLE "COMPANY" ADD CONSTRAINT "PK_COMPANY" PRIMARY KEY (
	"COM_NO"
);

ALTER TABLE "CALENDAR" ADD CONSTRAINT "PK_CALENDAR" PRIMARY KEY (
	"CALENDAR_NO"
);

ALTER TABLE "BUISNESS_CARD" ADD CONSTRAINT "PK_BUISNESS_CARD" PRIMARY KEY (
	"CARD_NO"
);

ALTER TABLE "BOARD_FILE" ADD CONSTRAINT "PK_BOARD_FILE" PRIMARY KEY (
	"FILE_NO"
);

ALTER TABLE "TODO" ADD CONSTRAINT "PK_TODO" PRIMARY KEY (
	"TODO_NO"
);

ALTER TABLE "TODO_FILE" ADD CONSTRAINT "PK_TODO_FILE" PRIMARY KEY (
	"FILE_NO"
);

ALTER TABLE "TODO_MANAGER" ADD CONSTRAINT "PK_TODO_MANAGER" PRIMARY KEY (
	"TODO_MGR_NO"
);

ALTER TABLE "CHAT_PARTICIPANT" ADD CONSTRAINT "PK_CHAT_PARTICIPANT" PRIMARY KEY (
	"PARTICIPANT_NO"
);

ALTER TABLE "CHAT_MESSAGE" ADD CONSTRAINT "PK_CHAT_MESSAGE" PRIMARY KEY (
	"MESSAGE_NO"
);

ALTER TABLE "CHAT_FILE" ADD CONSTRAINT "PK_CHAT_FILE" PRIMARY KEY (
	"FILE_NO"
);

ALTER TABLE "RESERVE_INFO" ADD CONSTRAINT "PK_RESERVE_INFO" PRIMARY KEY (
	"RESERVE_INFO_NO"
);

ALTER TABLE "MEETING_ROOM" ADD CONSTRAINT "PK_MEETING_ROOM" PRIMARY KEY (
	"MEETING_ROOM_NO"
);

ALTER TABLE "SURVEY_SUB" ADD CONSTRAINT "PK_SURVEY_SUB" PRIMARY KEY (
	"SURVEY_SUB_NO"
);

ALTER TABLE "SURVEY_ANSWER" ADD CONSTRAINT "PK_SURVEY_ANSWER" PRIMARY KEY (
	"ANSWER_NO"
);

ALTER TABLE "MAIL" ADD CONSTRAINT "PK_MAIL" PRIMARY KEY (
	"MAIL_NO"
);

ALTER TABLE "MAIL_FILE" ADD CONSTRAINT "PK_MAIL_FILE" PRIMARY KEY (
	"FILE_NO"
);

ALTER TABLE "RECIPIENT" ADD CONSTRAINT "PK_RECIPIENT" PRIMARY KEY (
	"RECIPIENT_NO"
);

ALTER TABLE "EDSM" ADD CONSTRAINT "PK_EDSM" PRIMARY KEY (
	"EDSM_NO"
);

ALTER TABLE "EDSM_FILE" ADD CONSTRAINT "PK_EDSM_FILE" PRIMARY KEY (
	"FILE_NO"
);

ALTER TABLE "DRAFT" ADD CONSTRAINT "PK_DRAFT" PRIMARY KEY (
	"DRAFT_NO"
);

ALTER TABLE "APPROVER" ADD CONSTRAINT "PK_APPROVER" PRIMARY KEY (
	"APPROVER_NO"
);

ALTER TABLE "REFER" ADD CONSTRAINT "PK_REFER" PRIMARY KEY (
	"REFER_NO"
);

ALTER TABLE "ATTENDENCE" ADD CONSTRAINT "PK_ATTENDENCE" PRIMARY KEY (
	"ATTENDANCE_KEY"
);

ALTER TABLE "STANDARD_ATD" ADD CONSTRAINT "PK_STANDARD_ATD" PRIMARY KEY (
	"STANDARD_ATD_NO"
);

ALTER TABLE "IP" ADD CONSTRAINT "PK_IP" PRIMARY KEY (
	"IP_NO"
);

ALTER TABLE "USER_CARD" ADD CONSTRAINT "PK_USER_CARD" PRIMARY KEY (
	"USER_CARD_NO"
);

ALTER TABLE "UPDATE_ATD" ADD CONSTRAINT "PK_UPDATE_ATD" PRIMARY KEY (
	"UPDATE_ATD_NO"
);

------------------------------------------------------------------------------

DROP TABLE APPROVER;
DROP TABLE ATTENDENCE;
DROP TABLE AUTHORITY;
DROP TABLE AUTHORITY_MEMBER;
DROP TABLE BOARD_FILE;
DROP TABLE BUISNESS_CARD;
DROP TABLE CALENDAR;
DROP TABLE CHAT_FILE;
DROP TABLE CHAT_MESSAGE;
DROP TABLE CHAT_PARTICIPANT;
DROP TABLE CHAT_ROOM;
DROP TABLE "COMMENT";
DROP TABLE COMPANY;
DROP TABLE DEPARTMENT;
DROP TABLE DRAFT;
DROP TABLE EDSM;
DROP TABLE EDSM_FILE;
DROP TABLE EMPLOYEE;
DROP TABLE IP;
DROP TABLE MAIL;
DROP TABLE MAIL_FILE;
DROP TABLE MEETING_ROOM;
DROP TABLE NOTICE;
DROP TABLE "POSITION";
DROP TABLE RECIPIENT;
DROP TABLE REFER;
DROP TABLE RESERVE_INFO;
DROP TABLE STANDARD_ATD;
DROP TABLE SUBSCRIBE_ADDR;
DROP TABLE SURVEY;
DROP TABLE SURVEY_ANSWER;
DROP TABLE SURVEY_MULTIPLE;
DROP TABLE SURVEY_SUB;
DROP TABLE TEAM;
DROP TABLE TEAM_BOARD;
DROP TABLE TODO;
DROP TABLE TODO_FILE;
DROP TABLE TODO_MANAGER;
DROP TABLE UPDATE_ATD;
DROP TABLE USER_CARD;