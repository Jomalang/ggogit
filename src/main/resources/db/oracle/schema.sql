-- database: oracle
-- admin 사용자 정보
-- ID: system
-- PW: 1234

-- 사용자 아이디 C## 제거 설정
-- ALTER SESSION SET "_ORACLE_SCRIPT"=true;

-- 새로운 사용자 생성
-- CREATE USER root IDENTIFIED BY "1234";

-- 권한 부여
-- GRANT ALL PRIVILEGES TO root;
-- ============================================ --
-- 모든 테이블 제거
-- ============================================ --

BEGIN
    -- 테이블 제거
    FOR i IN (SELECT table_name FROM user_tables) LOOP
        EXECUTE IMMEDIATE 'DROP TABLE ' || i.table_name || ' CASCADE CONSTRAINTS';
    END LOOP;

    -- 시퀀스 제거
    FOR i IN (SELECT sequence_name FROM user_sequences) LOOP
        EXECUTE IMMEDIATE 'DROP SEQUENCE ' || i.sequence_name;
    END LOOP;

    COMMIT;
END;

-- 시퀀스 제거
PURGE RECYCLEBIN;

-- ============================================ --
-- 회원 테이블
-- ============================================ --
CREATE TABLE "MEMBER" (
    "ID"	            NUMBER		                            PRIMARY KEY, -- 회원 PK
    "EMAIL"	            VARCHAR2(255)	                        NOT NULL, -- 회원 이메일
    "PASSWORD"	        VARCHAR2(64)	                        NOT NULL, -- 회원 비밀번호
    "NICKNAME"	        VARCHAR2(255)		                    NOT NULL, -- 회원 닉네임
    "USERNAME"          NVARCHAR2(10)                           NOT NULL, -- 회원 이름
    "INTRODUCTION"	    NVARCHAR2(2000) 	                    NULL, -- 회원 소개글
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"	    NUMBER(1)	    DEFAULT 0	            NOT NULL -- 회원 삭제 여부
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_MEMBER
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;

-- 시퀀스 등록
ALTER TABLE "MEMBER" MODIFY ("ID" DEFAULT SEQ_MEMBER.NEXTVAL);

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_MEMBER_UPDATE_TIME
BEFORE UPDATE ON MEMBER
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

-- 코맨트
COMMENT ON COLUMN "MEMBER"."ID" IS '회원 PK';
COMMENT ON COLUMN "MEMBER"."EMAIL" IS '회원 이메일';
COMMENT ON COLUMN "MEMBER"."PASSWORD" IS '회원 비밀번호';
COMMENT ON COLUMN "MEMBER"."NICKNAME" IS '회원 닉네임';
COMMENT ON COLUMN "MEMBER"."USERNAME" IS '회원 이름';
COMMENT ON COLUMN "MEMBER"."INTRODUCTION" IS '회원 소개글';
COMMENT ON COLUMN "MEMBER"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "MEMBER"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 회원 프로필 이미지 생성
-- ============================================ --
CREATE TABLE "MEMBER_PROFILE_IMAGE" (
    "MEMBER_ID"	        NUMBER		    NOT NULL, -- 회원 프로필 이미지 PK
    "NAME"	            VARCHAR2(255)	NOT NULL, -- 회원 프로필 이미지 이름
    "IS_DELETED"	    NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 회원 프로필 이미지 삭제 여부
    -- PK 정의
    CONSTRAINT "PK_MEMBER_PROFILE_IMAGE" PRIMARY KEY ("MEMBER_ID"),
    -- FK 정의
    CONSTRAINT "FK_MEMBER_PROFILE_IMAGE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID")
);

COMMENT ON COLUMN "MEMBER_PROFILE_IMAGE"."MEMBER_ID" IS '회원 프로필 이미지 PK';
COMMENT ON COLUMN "MEMBER_PROFILE_IMAGE"."NAME" IS '회원 프로필 이미지 이름';

-- ============================================ --
-- 회원 배경 이미지 테이블
-- ============================================ --
CREATE TABLE "MEMBER_BACKGROUND_IMAGE" (
    "MEMBER_ID"	        NUMBER		    NOT NULL, -- 회원 배경 이미지 PK
    "NAME"	            VARCHAR2(255)	NOT NULL, -- 회원 배경 이미지 이름
    "IS_DELETED"	    NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 회원 배경 이미지 삭제 여부
    -- PK 정의
    CONSTRAINT "PK_MEMBER_BACKGROUND_IMAGE" PRIMARY KEY ("MEMBER_ID"),
    -- FK 정의
    CONSTRAINT "FK_MEMBER_BACKGROUND_IMAGE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID")
);

COMMENT ON COLUMN "MEMBER_BACKGROUND_IMAGE"."MEMBER_ID" IS '회원 배경 이미지 PK';
COMMENT ON COLUMN "MEMBER_BACKGROUND_IMAGE"."NAME" IS '회원 배경 이미지 이름';

-- ============================================ --
-- 팔로우 테이블
-- ============================================ --
CREATE TABLE "FOLLOW" (
    "MEMBER_ID"	        NUMBER		                            NOT NULL, -- 회원 FK
    "FOLLOW_ID"	        NUMBER		                            NOT NULL, -- 팔로우 대상 회원 FK
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 팔로우 삭제 여부
    -- PK 정의
    CONSTRAINT "PK_FOLLOW" PRIMARY KEY ("MEMBER_ID", "FOLLOW_ID"),
    -- FK 정의
    CONSTRAINT "FK_FOLLOW_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_FOLLOW_FOLLOW" FOREIGN KEY ("FOLLOW_ID") REFERENCES "MEMBER" ("ID")
);

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_FOLLOW_UPDATE_TIME
BEFORE UPDATE ON FOLLOW
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "FOLLOW"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "FOLLOW"."FOLLOW_ID" IS '팔로우 대상 회원 FK';
COMMENT ON COLUMN "FOLLOW"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "FOLLOW"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 도서 카테고리 테이블
-- ============================================ --
CREATE TABLE "BOOK_CATEGORY" (
    "ID"	            NUMBER		                            PRIMARY KEY, -- 도서 카테고리 PK
    "NAME"	            NVARCHAR2(255)		                    NOT NULL, -- 도서 카테고리 이름
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL -- 도서 카테고리 삭제 여부
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_BOOK_CATEGORY
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;

-- 시퀀스 등록
ALTER TABLE "BOOK_CATEGORY" MODIFY ("ID" DEFAULT SEQ_BOOK_CATEGORY.NEXTVAL);

COMMENT ON COLUMN "BOOK_CATEGORY"."ID" IS '도서 카테고리 PK';
COMMENT ON COLUMN "BOOK_CATEGORY"."NAME" IS '도서 카테고리 이름';

-- ============================================ --
-- 도서 테이블
-- ============================================ --
CREATE TABLE "BOOK" (
    "ID"	            NUMBER		                            PRIMARY KEY, -- 도서 PK
    "MEMBER_ID"         NUMBER		                            NOT NULL, -- 회원 FK
    "BOOK_CATEGORY_ID"	NUMBER		                            NOT NULL, -- 도서 카테고리 FK
    "TITLE"             NVARCHAR2(1024)	                        NOT NULL, -- 도서 제목
    "AUTHOR"            NVARCHAR2(1024)	                        NOT NULL, -- 도서 저자
    "ISBN"              VARCHAR2(13)	                        NULL, -- 도서 ISBN
    "PUBLISHER"         NVARCHAR2(1024)	                        NOT NULL, -- 도서 출판사
    "PUBLISH_DATE"      DATE		                            NULL, -- 도서 출판일
    "TOTAL_PAGE"        NUMBER		                            NOT NULL, -- 도서 총 페이지 수
    "IMAGE_FILE"        VARCHAR2(1024)	                        NULL, -- 도서 이미지 파일
    "RESOURCE_FROM"     NUMBER(1)	                            NOT NULL, -- 도서 등록 여부
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 도서 삭제 여부
    -- UNIQUE 정의
    UNIQUE ("ISBN"),
    -- FK 정의
    CONSTRAINT "FK_BOOK_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_BOOK_BOOK_CATEGORY" FOREIGN KEY ("BOOK_CATEGORY_ID") REFERENCES "BOOK_CATEGORY" ("ID")
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_BOOK
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;

-- 시퀀스 등록
ALTER TABLE "BOOK" MODIFY ("ID" DEFAULT SEQ_BOOK.NEXTVAL);

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_BOOK_UPDATE_TIME
BEFORE UPDATE ON BOOK
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "BOOK"."ID" IS '도서 PK';
COMMENT ON COLUMN "BOOK"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "BOOK"."BOOK_CATEGORY_ID" IS '도서 카테고리 FK';
COMMENT ON COLUMN "BOOK"."TITLE" IS '도서 제목';
COMMENT ON COLUMN "BOOK"."AUTHOR" IS '도서 저자';
COMMENT ON COLUMN "BOOK"."ISBN" IS '도서 ISBN';
COMMENT ON COLUMN "BOOK"."PUBLISHER" IS '도서 출판사';
COMMENT ON COLUMN "BOOK"."TOTAL_PAGE" IS '도서 총 페이지 수';
COMMENT ON COLUMN "BOOK"."IMAGE_FILE" IS '도서 이미지 파일';
COMMENT ON COLUMN "BOOK"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "BOOK"."CREATE_TIME" IS '데이터 생성 시각';
COMMENT ON COLUMN "BOOK"."RESOURCE_FROM" IS '도서 출처';

-- ============================================ --
-- 도서 좋아요 테이블
-- ============================================ --
CREATE TABLE "BOOK_LIKE" (
    "MEMBER_ID"         NUMBER		                            NOT NULL, -- 회원 FK
    "BOOK_ID"	        NUMBER		                            NOT NULL, -- 도서 FK
    "ACTIVATE"	        NUMBER(1)	    DEFAULT 1	            NOT NULL, -- 도서 좋아요 활성화 여부
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 도서 좋아요 삭제 여부
    -- PK 정의
    CONSTRAINT "PK_BOOK_LIKE" PRIMARY KEY ("MEMBER_ID", "BOOK_ID"),
    -- FK 정의
    CONSTRAINT "FK_BOOK_LIKE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_BOOK_LIKE_BOOK" FOREIGN KEY ("BOOK_ID") REFERENCES "BOOK" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_BOOK_LIKE_MEMBER_ID" ON "BOOK_LIKE" ("MEMBER_ID");
CREATE INDEX "IDX_BOOK_LIKE_BOOK_ID" ON "BOOK_LIKE" ("BOOK_ID");

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_BOOK_LIKE_UPDATE_TIME
BEFORE UPDATE ON BOOK_LIKE
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "BOOK_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "BOOK_LIKE"."BOOK_ID" IS '도서 FK';
COMMENT ON COLUMN "BOOK_LIKE"."ACTIVATE" IS '도서 좋아요 활성화 여부';
COMMENT ON COLUMN "BOOK_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "BOOK_LIKE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 도서 댓글 테이블
-- ============================================ --
CREATE TABLE "BOOK_COMMENT" (
    "ID"	            NUMBER		                            PRIMARY KEY, -- 도서 댓글 PK
    "MEMBER_ID"	        NUMBER		                            NOT NULL, -- 회원 FK
    "BOOK_ID"	        NUMBER		                            NOT NULL, -- 도서 FK
    "LIKE_COUNT"	    NUMBER	        DEFAULT 0	            NOT NULL, -- 도서 댓글 좋아요 수
    "CONTENT"	        NVARCHAR2(2000)		                    NOT NULL, -- 도서 댓글 내용
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 도서 댓글 삭제 여부
    -- FK 정의
    CONSTRAINT "FK_BOOK_COMMENT_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_BOOK_COMMENT_BOOK" FOREIGN KEY ("BOOK_ID") REFERENCES "BOOK" ("ID")
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_BOOK_COMMENT
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;

-- 시퀀스 등록
ALTER TABLE "BOOK_COMMENT" MODIFY ("ID" DEFAULT SEQ_BOOK_COMMENT.NEXTVAL);

-- 인덱스 정의
CREATE INDEX "IDX_BOOK_COMMENT_MEMBER_ID" ON "BOOK_COMMENT" ("MEMBER_ID");
CREATE INDEX "IDX_BOOK_COMMENT_BOOK_ID" ON "BOOK_COMMENT" ("BOOK_ID");

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_BOOK_COMMENT_UPDATE_TIME
BEFORE UPDATE ON BOOK_COMMENT
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "BOOK_COMMENT"."ID" IS '도서 댓글 PK';
COMMENT ON COLUMN "BOOK_COMMENT"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "BOOK_COMMENT"."BOOK_ID" IS '도서 FK';
COMMENT ON COLUMN "BOOK_COMMENT"."LIKE_COUNT" IS '도서 댓글 좋아요 수';
COMMENT ON COLUMN "BOOK_COMMENT"."CONTENT" IS '도서 댓글 내용';
COMMENT ON COLUMN "BOOK_COMMENT"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "BOOK_COMMENT"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 도서 댓글 좋아요 테이블
-- ============================================ --
CREATE TABLE "BOOK_COMMENT_LIKE" (
    "MEMBER_ID"	        NUMBER		                            NOT NULL, -- 회원 FK
    "BOOK_COMMENT_ID"   NUMBER		                            NOT NULL, -- 도서 댓글 FK
    "ACTIVATE"	        NUMBER(1)	    DEFAULT 1	            NOT NULL, -- 도서 댓글 좋아요 활성화 여부
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 도서 댓글 좋아요 삭제 여부
    -- PK 정의
    CONSTRAINT "PK_BOOK_COMMENT_LIKE" PRIMARY KEY ("MEMBER_ID", "BOOK_COMMENT_ID"),
    -- FK 정의
    CONSTRAINT "FK_BOOK_COMMENT_LIKE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_BOOK_COMMENT_LIKE_BOOK_COMMENT" FOREIGN KEY ("BOOK_COMMENT_ID") REFERENCES "BOOK_COMMENT" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_BOOK_COMMENT_LIKE_MEMBER_ID" ON "BOOK_COMMENT_LIKE" ("MEMBER_ID");
CREATE INDEX "IDX_BOOK_COMMENT_LIKE_BOOK_COMMENT_ID" ON "BOOK_COMMENT_LIKE" ("BOOK_COMMENT_ID");

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_BOOK_COMMENT_LIKE_UPDATE_TIME
BEFORE UPDATE ON BOOK_COMMENT_LIKE
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "BOOK_COMMENT_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "BOOK_COMMENT_LIKE"."BOOK_COMMENT_ID" IS '도서 댓글 FK';
COMMENT ON COLUMN "BOOK_COMMENT_LIKE"."ACTIVATE" IS '도서 댓글 좋아요 활성화 여부';
COMMENT ON COLUMN "BOOK_COMMENT_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "BOOK_COMMENT_LIKE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 씨드 테이블
-- ============================================ --
CREATE TABLE "SEED" (
    "ID"	            NUMBER		        PRIMARY KEY, -- 씨앗 PK
    "NAME"	            NVARCHAR2(30)		NOT NULL, -- 씨앗 제목
    "DESCRIPTION"	    NVARCHAR2(30)		NOT NULL, -- 씨앗 설명
    "IS_DELETED"        NUMBER(1)	        DEFAULT 0	    NOT NULL -- 씨앗 삭제 여부
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_SEED
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;

-- 시퀀스 등록
ALTER TABLE "SEED" MODIFY ("ID" DEFAULT SEQ_SEED.NEXTVAL);

COMMENT ON COLUMN "SEED"."ID" IS '씨앗 PK';
COMMENT ON COLUMN "SEED"."NAME" IS '씨앗 제목';
COMMENT ON COLUMN "SEED"."DESCRIPTION" IS '씨앗 설명';

-- ============================================ --
-- 트리 생성 탬프 테이블
-- ============================================ --
CREATE TABLE "TREE_SAVE_TMP" (
    "ID"	            NUMBER		                            PRIMARY KEY,
    "MEMBER_ID"	        NUMBER		                            NOT NULL,
    -- 도서 정보 (도서 트리 생성시에만 사용)
    "BOOK_CATEGORY_ID"	NUMBER		                            NULL, -- 도서 카테고리 FK
    "BOOK_TITLE"        NVARCHAR2(300)		                    NULL, -- 직접 입력 도서 제목
    "AUTHOR"            NVARCHAR2(300)		                    NULL, -- 직접 입력 도서 저자
    "PUBLISHER"         NVARCHAR2(300)		                    NULL, -- 직접 입력 도서 출판사
    "TOTAL_PAGE"        NUMBER		                            NULL, -- 직접 입력 도서 총 페이지 수
    -- 선택 도서 트리 생성
    "BOOK_ID"           NUMBER                                  NULL, -- 선택 도서 저장시 사용
    -- 트리 정보
    "SEED_ID"	        NUMBER		                            NOT NULL,
    "TREE_TITLE"	    NVARCHAR2(300)		                    NOT NULL,
    "DESCRIPTION"	    NVARCHAR2(2000)		                    NOT NULL,
    "IMAGE_FILE"        VARCHAR2(1024)	                        NULL,
    "VISIBILITY"	    NUMBER(1)	    DEFAULT 1	            NOT NULL,
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL,
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL,
    -- FK 정의
    CONSTRAINT "FK_TREE_SAVE_TMP_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_TREE_SAVE_TMP_BOOK" FOREIGN KEY ("BOOK_ID") REFERENCES "BOOK" ("ID"),
    CONSTRAINT "FK_TREE_SAVE_TMP_BOOK_CATEGORY" FOREIGN KEY ("BOOK_CATEGORY_ID") REFERENCES "BOOK_CATEGORY" ("ID"),
    CONSTRAINT "FK_TREE_SAVE_TMP_SEED" FOREIGN KEY ("SEED_ID") REFERENCES "SEED" ("ID")
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_TREE_SAVE_TMP
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;

-- 시퀀스 등록
ALTER TABLE "TREE_SAVE_TMP" MODIFY ("ID" DEFAULT SEQ_TREE_SAVE_TMP.NEXTVAL);

-- 인덱스 정의
CREATE INDEX "IDX_TREE_SAVE_TMP_MEMBER_ID" ON "TREE_SAVE_TMP" ("MEMBER_ID");

-- 주석
COMMENT ON COLUMN "TREE_SAVE_TMP"."ID" IS '트리 생성 탬프 PK';
COMMENT ON COLUMN "TREE_SAVE_TMP"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "TREE_SAVE_TMP"."BOOK_CATEGORY_ID" IS '도서 카테고리 FK';
COMMENT ON COLUMN "TREE_SAVE_TMP"."BOOK_TITLE" IS '직접 입력 도서 제목';
COMMENT ON COLUMN "TREE_SAVE_TMP"."AUTHOR" IS '직접 입력 도서 저자';
COMMENT ON COLUMN "TREE_SAVE_TMP"."PUBLISHER" IS '직접 입력 도서 출판사';
COMMENT ON COLUMN "TREE_SAVE_TMP"."TOTAL_PAGE" IS '직접 입력 도서 총 페이지 수';
COMMENT ON COLUMN "TREE_SAVE_TMP"."BOOK_ID" IS '선택 도서 저장시 사용';
COMMENT ON COLUMN "TREE_SAVE_TMP"."SEED_ID" IS '씨앗 FK';
COMMENT ON COLUMN "TREE_SAVE_TMP"."TREE_TITLE" IS '트리 제목';
COMMENT ON COLUMN "TREE_SAVE_TMP"."DESCRIPTION" IS '트리 설명';
COMMENT ON COLUMN "TREE_SAVE_TMP"."IMAGE_FILE" IS '트리 이미지 파일';
COMMENT ON COLUMN "TREE_SAVE_TMP"."VISIBILITY" IS '사용자 공개 여부';
COMMENT ON COLUMN "TREE_SAVE_TMP"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 트리 테이블
-- ============================================ --
CREATE TABLE "TREE" (
    "ID"	            NUMBER		                            PRIMARY KEY,
    "MEMBER_ID"	        NUMBER		                            NOT NULL,
    "BOOK_ID"           NUMBER                                  NULL,
    "SEED_ID"	        NUMBER		                            NOT NULL,
    "TITLE"	            NVARCHAR2(300)		                    NOT NULL,
	"DESCRIPTION"	    NVARCHAR2(2000)		                    NOT NULL,
	"BOOK_MARK_COUNT"   NUMBER(1)	    DEFAULT 0	            NOT NULL,
	"VISIBILITY"	    NUMBER(1)	    DEFAULT 1	            NOT NULL,
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL,
    -- FK 정의
    CONSTRAINT "FK_TREE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_TREE_BOOK" FOREIGN KEY ("BOOK_ID") REFERENCES "BOOK" ("ID"),
    CONSTRAINT "FK_TREE_SEED" FOREIGN KEY ("SEED_ID") REFERENCES "SEED" ("ID")
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_TREE
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;

-- 시퀀스 등록
ALTER TABLE "TREE" MODIFY ("ID" DEFAULT SEQ_TREE.NEXTVAL);

-- 인덱스 정의
CREATE INDEX "IDX_TREE_MEMBER_ID" ON "TREE" ("MEMBER_ID");
CREATE INDEX "IDX_TREE_SEED_ID" ON "TREE" ("SEED_ID");

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_TREE_UPDATE_TIME
BEFORE UPDATE ON TREE
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "TREE"."ID" IS '트리 PK';
COMMENT ON COLUMN "TREE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "TREE"."SEED_ID" IS '씨앗 고유 FK';
COMMENT ON COLUMN "TREE"."TITLE" IS '트리 제목';
COMMENT ON COLUMN "TREE"."DESCRIPTION" IS '트리 설명';
COMMENT ON COLUMN "TREE"."BOOK_MARK_COUNT" IS '브랜치 책갈피 수';
COMMENT ON COLUMN "TREE"."VISIBILITY" IS '사용자 공개 여부';
COMMENT ON COLUMN "TREE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "TREE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 트리 책 추가 정보 테이블
-- ============================================ --
CREATE TABLE "TREE_BOOK" (
    "TREE_ID"	        NUMBER		    NOT NULL, -- 트리 FK
    "READING_PAGE"	    NUMBER(5)		NOT NULL, -- 책 트리 읽은 페이지 수
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 책 트리 삭제 여부
    -- PK 정의
    CONSTRAINT "PK_TREE_BOOK" PRIMARY KEY ("TREE_ID"),
    -- FK 정의
    CONSTRAINT "FK_TREE_BOOK_TREE" FOREIGN KEY ("TREE_ID") REFERENCES "TREE" ("ID")
);

COMMENT ON COLUMN "TREE_BOOK"."TREE_ID" IS '책 트리 PK';
COMMENT ON COLUMN "TREE_BOOK"."READING_PAGE" IS '책 트리 읽은 페이지 수';

-- ============================================ --
-- 트리 이미지 테이블
-- ============================================ --
CREATE TABLE "TREE_IMAGE" (
    "TREE_ID"	        NUMBER		    NOT NULL, -- 트리 FK
    "NAME"	            VARCHAR2(255)	NOT NULL, -- 트리 이미지 이름
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 트리 이미지 삭제 여부
    -- PK 정의
    CONSTRAINT "PK_TREE_IMAGE" PRIMARY KEY ("TREE_ID"),
    -- FK 정의
    CONSTRAINT "FK_TREE_IMAGE_TREE" FOREIGN KEY ("TREE_ID") REFERENCES "TREE" ("ID")
);

COMMENT ON COLUMN "TREE_IMAGE"."TREE_ID" IS '트리 이미지 PK';
COMMENT ON COLUMN "TREE_IMAGE"."NAME" IS '트리 이미지 이름';

-- ============================================ --
-- 트리 좋아요 테이블
-- ============================================ --
CREATE TABLE "TREE_LIKE" (
    "MEMBER_ID"	        NUMBER		                            NOT NULL, -- 회원 FK
    "TREE_ID"	        NUMBER		                            NOT NULL, -- 트리 FK
    "ACTIVATE"	        NUMBER(1)	    DEFAULT 1	            NOT NULL, -- 트리 좋아요 활성화 여부
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 트리 좋아요 삭제 여부
    -- PK 정의
    CONSTRAINT "PK_TREE_LIKE" PRIMARY KEY ("MEMBER_ID", "TREE_ID"),
    -- FK 정의
    CONSTRAINT "FK_TREE_LIKE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_TREE_LIKE_TREE" FOREIGN KEY ("TREE_ID") REFERENCES "TREE" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_TREE_LIKE_MEMBER_ID" ON "TREE_LIKE" ("MEMBER_ID");
CREATE INDEX "IDX_TREE_LIKE_TREE_ID" ON "TREE_LIKE" ("TREE_ID");

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_TREE_LIKE_UPDATE_TIME
BEFORE UPDATE ON TREE_LIKE
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "TREE_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "TREE_LIKE"."TREE_ID" IS '트리 FK';
COMMENT ON COLUMN "TREE_LIKE"."ACTIVATE" IS '트리 좋아요 활성화 여부';
COMMENT ON COLUMN "TREE_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "TREE_LIKE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 회고록 테이블
-- ============================================ --
CREATE TABLE "MEMOIR" (
    "ID"	            NUMBER		                            PRIMARY KEY, -- 회고록 PK
    "TREE_ID"	        NUMBER		                            NOT NULL, -- 트리 FK
    "TITLE"	            VARCHAR(255)		                    NOT NULL, -- 회고록 제목
    "TEXT"	            CLOB		                            NOT NULL, -- 회고록 내용
    "VISIBILITY"	    NUMBER(1)	    DEFAULT 1	            NOT NULL, -- 사용자 공개 여부
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 회고록 삭제 여부
    -- FK 정의
    CONSTRAINT "FK_MEMOIR_TREE" FOREIGN KEY ("TREE_ID") REFERENCES "TREE" ("ID")
);

-- 시퀀스
CREATE SEQUENCE SEQ_MEMOIR
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;

-- 시퀀스 등록
ALTER TABLE "MEMOIR" MODIFY ("ID" DEFAULT SEQ_MEMOIR.NEXTVAL);

-- 인덱스 정의
CREATE INDEX "IDX_MEMOIR_TREE_ID" ON "MEMOIR" ("TREE_ID");

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_MEMOIR_UPDATE_TIME
BEFORE UPDATE ON MEMOIR
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "MEMOIR"."ID" IS '회고록 PK';
COMMENT ON COLUMN "MEMOIR"."TREE_ID" IS '트리 FK';
COMMENT ON COLUMN "MEMOIR"."TITLE" IS '회고록 제목';
COMMENT ON COLUMN "MEMOIR"."TEXT" IS '회고록 내용';
COMMENT ON COLUMN "MEMOIR"."VISIBILITY" IS '사용자 공개 여부';
COMMENT ON COLUMN "MEMOIR"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "MEMOIR"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 회고록 좋아요 테이블
-- ============================================ --
CREATE TABLE "MEMOIR_LIKE" (
    "MEMBER_ID"	        NUMBER		                            NOT NULL, -- 회원 FK
    "MEMOIR_ID"	        NUMBER		                            NOT NULL, -- 회고록 FK
    "ACTIVATE"	        NUMBER(1)	    DEFAULT 1	            NOT NULL, -- 회고록 좋아요 활성화 여부
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 회고록 좋아요 삭제 여부
    -- PK 정의
    CONSTRAINT "PK_MEMOIR_LIKE" PRIMARY KEY ("MEMBER_ID", "MEMOIR_ID"),
    -- FK 정의
    CONSTRAINT "FK_MEMOIR_LIKE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_MEMOIR_LIKE_MEMOIR" FOREIGN KEY ("MEMOIR_ID") REFERENCES "MEMOIR" ("ID")
);

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_MEMOIR_LIKE_UPDATE_TIME
BEFORE UPDATE ON MEMOIR_LIKE
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "MEMOIR_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "MEMOIR_LIKE"."MEMOIR_ID" IS '회고록 FK';
COMMENT ON COLUMN "MEMOIR_LIKE"."ACTIVATE" IS '회고록 좋아요 활성화 여부';
COMMENT ON COLUMN "MEMOIR_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "MEMOIR_LIKE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 회고록 댓글 테이블
-- ============================================ --
CREATE TABLE "MEMOIR_COMMENT" (
    "ID"	            NUMBER		                            PRIMARY KEY, -- 회고록 댓글 PK
    "MEMBER_ID"	        NUMBER		                            NOT NULL, -- 회원 FK
    "MEMOIR_ID"	        NUMBER		                            NOT NULL, -- 회고록 FK
    "LIKE_COUNT"	    NUMBER	        DEFAULT 0	            NOT NULL, -- 회고록 댓글 좋아요 수
    "CONTENT"	        NVARCHAR2(2000)		                    NOT NULL, -- 회고록 댓글 내용
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 회고록 댓글 삭제 여부
    -- FK 정의
    CONSTRAINT "FK_MEMOIR_COMMENT_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_MEMOIR_COMMENT_MEMOIR" FOREIGN KEY ("MEMOIR_ID") REFERENCES "MEMOIR" ("ID")
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_MEMOIR_COMMENT
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;

-- 시퀀스 등록
ALTER TABLE "MEMOIR_COMMENT" MODIFY ("ID" DEFAULT SEQ_MEMOIR_COMMENT.NEXTVAL);

-- 인덱스 정의
CREATE INDEX "IDX_MEMOIR_COMMENT_MEMBER_ID" ON "MEMOIR_COMMENT" ("MEMBER_ID");
CREATE INDEX "IDX_MEMOIR_COMMENT_MEMOIR_ID" ON "MEMOIR_COMMENT" ("MEMOIR_ID");

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_MEMOIR_COMMENT_UPDATE_TIME
BEFORE UPDATE ON MEMOIR_COMMENT
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "MEMOIR_COMMENT"."ID" IS '회고록 댓글 PK';
COMMENT ON COLUMN "MEMOIR_COMMENT"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "MEMOIR_COMMENT"."MEMOIR_ID" IS '회고록 FK';
COMMENT ON COLUMN "MEMOIR_COMMENT"."LIKE_COUNT" IS '회고록 댓글 좋아요 수';
COMMENT ON COLUMN "MEMOIR_COMMENT"."CONTENT" IS '회고록 댓글 내용';
COMMENT ON COLUMN "MEMOIR_COMMENT"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "MEMOIR_COMMENT"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 회고록 댓글 좋아요 테이블
-- ============================================ --
CREATE TABLE "MEMOIR_COMMENT_LIKE" (
    "MEMBER_ID"	        NUMBER		                            NOT NULL, -- 회원 FK
    "MEMOIR_COMMENT_ID"	NUMBER		                            NOT NULL, -- 회고록 댓글 FK
    "ACTIVATE"	        NUMBER(1)	    DEFAULT 1	            NOT NULL, -- 회고록 댓글 좋아요 활성화 여부
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 회고록 댓글 좋아요 삭제 여부
    -- PK 정의
    CONSTRAINT "PK_MEMOIR_COMMENT_LIKE" PRIMARY KEY ("MEMBER_ID", "MEMOIR_COMMENT_ID"),
    -- FK 정의
    CONSTRAINT "FK_MEMOIR_COMMENT_LIKE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_MEMOIR_COMMENT_LIKE_MEMOIR_COMMENT" FOREIGN KEY ("MEMOIR_COMMENT_ID") REFERENCES "MEMOIR_COMMENT" ("ID")
);

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_MEMOIR_COMMENT_LIKE_UPDATE_TIME
BEFORE UPDATE ON MEMOIR_COMMENT_LIKE
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."MEMOIR_COMMENT_ID" IS '회고록 댓글 FK';
COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."ACTIVATE" IS '회고록 댓글 좋아요 활성화 여부';
COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 리프 태그 테이블
-- ============================================ --
CREATE TABLE "LEAF_TAG" (
    "ID"	            NUMBER		                            PRIMARY KEY, -- 리프 태그 PK
    "MEMBER_ID"	        NUMBER		                            NOT NULL, -- 회원 FK
    "NAME"	            NVARCHAR2(20)	                        NOT NULL, -- 리프 태그 이름
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 리프 태그 삭제 여부
    -- FK 정의
    CONSTRAINT "FK_LEAF_TAG_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID")
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_LEAF_TAG
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;

-- 시퀀스 등록
ALTER TABLE "LEAF_TAG" MODIFY ("ID" DEFAULT SEQ_LEAF_TAG.NEXTVAL);

-- 인덱스 정의
CREATE INDEX "IDX_LEAF_TAG_MEMBER_ID" ON "LEAF_TAG" ("MEMBER_ID");

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_LEAF_TAG_UPDATE_TIME
BEFORE UPDATE ON LEAF_TAG
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "LEAF_TAG"."ID" IS '리프 태그 PK';
COMMENT ON COLUMN "LEAF_TAG"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "LEAF_TAG"."NAME" IS '리프 태그 이름';
COMMENT ON COLUMN "LEAF_TAG"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "LEAF_TAG"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 리프 테이블
-- ============================================ --
CREATE TABLE "LEAF" (
    "ID"	            NUMBER		                            PRIMARY KEY,
    "TREE_ID"	        NUMBER		                            NOT NULL,
    "PARENT_LEAF_ID"	NUMBER		                            NULL,
    "VISIBILITY"	    NUMBER(1)	    DEFAULT 1	            NOT NULL,
    "VIEW_COUNT"	    NUMBER		    DEFAULT 0               NOT NULL,
    "LIKE_COUNT"	    NUMBER	        DEFAULT 0	            NOT NULL,
    "TITLE"	            NVARCHAR2(100)		                    NOT NULL,
    "CONTENT"	        NVARCHAR2(2000)		                    NOT NULL,
	"CHILD_LEAF_COUNT"	NUMBER(1)	    DEFAULT 0	            NOT NULL,
    "BOOK_MARK"	        NUMBER(1)	    DEFAULT 0	            NOT NULL,
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL,
    -- FK 정의
    CONSTRAINT "FK_LEAF_TREE" FOREIGN KEY ("TREE_ID") REFERENCES "TREE" ("ID"),
    CONSTRAINT "FK_LEAF_PARENT_LEAF" FOREIGN KEY ("PARENT_LEAF_ID") REFERENCES "LEAF" ("ID")
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_LEAF
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;

-- 시퀀스 등록
ALTER TABLE "LEAF" MODIFY ("ID" DEFAULT SEQ_LEAF.NEXTVAL);

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_LEAF_UPDATE_TIME
BEFORE UPDATE ON LEAF
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "LEAF"."ID" IS '리프 PK';
COMMENT ON COLUMN "LEAF"."TREE_ID" IS '트리 FK';
COMMENT ON COLUMN "LEAF"."PARENT_LEAF_ID" IS '부모 리프 FK';
COMMENT ON COLUMN "LEAF"."VISIBILITY" IS '사용자 공개 여부';
COMMENT ON COLUMN "LEAF"."VIEW_COUNT" IS '리프 조회수';
COMMENT ON COLUMN "LEAF"."LIKE_COUNT" IS '리프 좋아요 수';
COMMENT ON COLUMN "LEAF"."TITLE" IS '리프 제목';
COMMENT ON COLUMN "LEAF".CONTENT IS '리프 내용';
COMMENT ON COLUMN "LEAF"."CHILD_LEAF_COUNT" IS '자식 리프 수';
COMMENT ON COLUMN "LEAF"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "LEAF"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 리프 태그 맵 테이블
-- ============================================ --
CREATE TABLE "LEAF_TAG_MAP" (
    "LEAF_ID"	    NUMBER		    NOT NULL, -- 리프 FK
    "LEAF_TAG_ID"	NUMBER		    NOT NULL, -- 리프 태그 FK
    "IS_DELETED"    NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 리프 태그 삭제 여부
    -- PK 정의
    CONSTRAINT "PK_LEAF_TAG_MAP" PRIMARY KEY ("LEAF_ID", "LEAF_TAG_ID"),
    -- FK 정의
    CONSTRAINT "FK_LEAF_TAG_MAP_LEAF" FOREIGN KEY ("LEAF_ID") REFERENCES "LEAF" ("ID"),
    CONSTRAINT "FK_LEAF_TAG_MAP_LEAF_TAG" FOREIGN KEY ("LEAF_TAG_ID") REFERENCES "LEAF_TAG" ("ID")
);

COMMENT ON COLUMN "LEAF_TAG_MAP"."LEAF_ID" IS '리프 FK';
COMMENT ON COLUMN "LEAF_TAG_MAP"."LEAF_TAG_ID" IS '리프 태그 FK';

-- ============================================ --
-- 리프 도서 추가 정보 테이블
-- ============================================ --
CREATE TABLE "LEAF_BOOK" (
    "LEAF_ID"	    NUMBER		NOT NULL, -- 리프 FK
    "START_PAGE"	NUMBER		NOT NULL, -- 리프 도서 시작 페이지
    "END_PAGE"	    NUMBER		NOT NULL, -- 리프 도서 종료 페이지
    "IS_DELETED"    NUMBER(1)	DEFAULT 0	NOT NULL, -- 리프 도서 삭제 여부
    -- PK 정의
    CONSTRAINT "PK_LEAF_BOOK" PRIMARY KEY ("LEAF_ID"),
    -- FK 정의
    CONSTRAINT "FK_LEAF_BOOK_LEAF" FOREIGN KEY ("LEAF_ID") REFERENCES "LEAF" ("ID")
);

COMMENT ON COLUMN "LEAF_BOOK"."LEAF_ID" IS '리프 FK';
COMMENT ON COLUMN "LEAF_BOOK"."START_PAGE" IS '리프 도서 시작 페이지';
COMMENT ON COLUMN "LEAF_BOOK"."END_PAGE" IS '리프 도서 종료 페이지';

-- ============================================ --
-- 리프 좋아요 테이블
-- ============================================ --
CREATE TABLE "LEAF_LIKE" (
    "MEMBER_ID"	        NUMBER		                            NOT NULL, -- 회원 FK
    "LEAF_ID"	        NUMBER		                            NOT NULL, -- 리프 FK
    "ACTIVATE"	        NUMBER(1)	    DEFAULT 1	            NOT NULL, -- 리프 좋아요 활성화 여부
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 리프 좋아요 삭제 여부
    -- PK 정의
    CONSTRAINT "PK_LEAF_LIKE" PRIMARY KEY ("MEMBER_ID", "LEAF_ID"),
    -- FK 정의
    CONSTRAINT "FK_LEAF_LIKE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_LEAF_LIKE_LEAF" FOREIGN KEY ("LEAF_ID") REFERENCES "LEAF" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_LEAF_LIKE_MEMBER_ID" ON "LEAF_LIKE" ("MEMBER_ID");

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_LEAF_LIKE_UPDATE_TIME
BEFORE UPDATE ON LEAF_LIKE
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "LEAF_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "LEAF_LIKE"."LEAF_ID" IS '리프 FK';
COMMENT ON COLUMN "LEAF_LIKE"."ACTIVATE" IS '리프 좋아요 활성화 여부';
COMMENT ON COLUMN "LEAF_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "LEAF_LIKE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 리프 댓글 테이블
-- ============================================ --
CREATE TABLE "LEAF_COMMENT" (
    "ID"	            NUMBER		                            PRIMARY KEY, -- 리프 댓글 PK
    "MEMBER_ID"	        NUMBER		                            NOT NULL, -- 회원 FK
    "LEAF_ID"	        NUMBER		                            NOT NULL, -- 리프 FK
    "LIKE_COUNT"	    NUMBER	        DEFAULT 0	            NOT NULL, -- 리프 댓글 좋아요 수
    "CONTENT"	        NVARCHAR2(2000)		                    NOT NULL, -- 리프 댓글 내용
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 리프 댓글 삭제 여부
    -- FK 정의
    CONSTRAINT "FK_LEAF_COMMENT_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_LEAF_COMMENT_LEAF" FOREIGN KEY ("LEAF_ID") REFERENCES "LEAF" ("ID")
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_LEAF_COMMENT
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;

-- 시퀀스 등록
ALTER TABLE "LEAF_COMMENT" MODIFY ("ID" DEFAULT SEQ_LEAF_COMMENT.NEXTVAL);

-- 인덱스 정의
CREATE INDEX "IDX_LEAF_COMMENT_MEMBER_ID" ON "LEAF_COMMENT" ("MEMBER_ID");
CREATE INDEX "IDX_LEAF_COMMENT_LEAF_ID" ON "LEAF_COMMENT" ("LEAF_ID");

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_LEAF_COMMENT_UPDATE_TIME
BEFORE UPDATE ON LEAF_COMMENT
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "LEAF_COMMENT"."ID" IS '리프 댓글 PK';
COMMENT ON COLUMN "LEAF_COMMENT"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "LEAF_COMMENT"."LEAF_ID" IS '리프 FK';
COMMENT ON COLUMN "LEAF_COMMENT"."LIKE_COUNT" IS '리프 댓글 좋아요 수';
COMMENT ON COLUMN "LEAF_COMMENT"."CONTENT" IS '리프 댓글 내용';
COMMENT ON COLUMN "LEAF_COMMENT"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "LEAF_COMMENT"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 리프 댓글 좋아요 테이블
-- ============================================ --
CREATE TABLE "LEAF_COMMENT_LIKE" (
    "MEMBER_ID"	        NUMBER		                            NOT NULL, -- 회원 FK
    "LEAF_COMMENT_ID"	NUMBER		                            NOT NULL, -- 리프 댓글 FK
    "ACTIVATE"	        NUMBER(1)	    DEFAULT 1	            NOT NULL, -- 리프 댓글 좋아요 활성화 여부
    "UPDATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP       DEFAULT SYSTIMESTAMP    NOT NULL, -- 데이터 생성 시각
    "IS_DELETED"        NUMBER(1)	    DEFAULT 0	            NOT NULL, -- 리프 댓글 좋아요 삭제 여부
    -- PK 정의
    CONSTRAINT "PK_LEAF_COMMENT_LIKE" PRIMARY KEY ("MEMBER_ID", "LEAF_COMMENT_ID"),
    -- FK 정의
    CONSTRAINT "FK_LEAF_COMMENT_LIKE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_LEAF_COMMENT_LIKE_LEAF_COMMENT" FOREIGN KEY ("LEAF_COMMENT_ID") REFERENCES "LEAF_COMMENT" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_LEAF_COMMENT_LIKE_MEMBER_ID" ON "LEAF_COMMENT_LIKE" ("MEMBER_ID");
CREATE INDEX "IDX_LEAF_COMMENT_LIKE_LEAF_COMMENT_ID" ON "LEAF_COMMENT_LIKE" ("LEAF_COMMENT_ID");

-- 수정 시각 자동 업데이트 트리거
CREATE OR REPLACE TRIGGER TRG_LEAF_COMMENT_LIKE_UPDATE_TIME
BEFORE UPDATE ON LEAF_COMMENT_LIKE
FOR EACH ROW
BEGIN
    :NEW.UPDATE_TIME := SYSTIMESTAMP;
END;

COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."LEAF_COMMENT_ID" IS '리프 댓글 FK';
COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."ACTIVATE" IS '리프 댓글 좋아요 활성화 여부';
COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."CREATE_TIME" IS '데이터 생성 시각';


-- 트리거 확인
SELECT TRIGGER_NAME, TRIGGER_TYPE, TRIGGERING_EVENT, TABLE_NAME, STATUS
FROM USER_TRIGGERS;