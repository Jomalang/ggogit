-- admin 사용자 정보
-- ID : system
-- PW : 1234

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
    FOR i IN (SELECT table_name FROM user_tables) LOOP
        EXECUTE IMMEDIATE 'DROP TABLE ' || i.table_name || ' CASCADE CONSTRAINTS';
    END LOOP;
END;

-- ============================================ --
-- 회원 테이블
-- ============================================ --
CREATE TABLE "MEMBER" (
    "ID"	                NUMBER		            NOT NULL, -- 회원 PK
    "PROFILE_ID"	        VARCHAR2(50)		    NOT NULL, -- 회원 프로필 아이디(영문)
    "EMAIL"	                VARCHAR2(100)		    NOT NULL, -- 회원 이메일(영문)
    "PASSWORD"	            VARCHAR2(255)		    NOT NULL, -- 회원 비밀번호
    "BACKGROUND_IMAGE"      VARCHAR2(255)		    NULL,     -- 내정보 뒷 배경 이미지
    "NICKNAME"	            VARCHAR2(50 CHAR)	    NOT NULL, -- 회원 닉네임(50자)
    "INTRODUCTION"	        VARCHAR2(1000 CHAR)	    NOT NULL, -- 회원 소개글(1000자)
    "JOIN_AGREE"	        NUMBER(1)	        	NOT NULL, -- 개인정보 동의 확인 정보
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 설정
    CONSTRAINT "PK_MEMBER" PRIMARY KEY ("ID") -- PK 설정
);

COMMENT ON COLUMN "MEMBER"."ID" IS '회원 PK';
COMMENT ON COLUMN "MEMBER"."PROFILE_ID" IS '회원 프로필 아이디';
COMMENT ON COLUMN "MEMBER"."EMAIL" IS '회원 이메일';
COMMENT ON COLUMN "MEMBER"."PASSWORD" IS '회원 비밀번호';
COMMENT ON COLUMN "MEMBER"."BACKGROUND_IMAGE" IS '내정보 뒷 배경 이미지';
COMMENT ON COLUMN "MEMBER"."NICKNAME" IS '회원 닉네임';
COMMENT ON COLUMN "MEMBER"."INTRODUCTION" IS '회원 소개글';
COMMENT ON COLUMN "MEMBER"."JOIN_AGREE" IS '개인정보 동의 확인 정보';
COMMENT ON COLUMN "MEMBER"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "MEMBER"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 팔로우 테이블
-- ============================================ --
CREATE TABLE "FOLLOW" (
    "MEMBER_ID"	            NUMBER		            NOT NULL, -- 회원 FK
    "FOLLOW_ID"	            NUMBER		            NOT NULL, -- 팔로우 대상 회원 FK
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 설정
    CONSTRAINT "PK_FOLLOW" PRIMARY KEY ("MEMBER_ID", "FOLLOW_ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_FOLLOW_MEMBER_ID" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"), -- FK 설정
    CONSTRAINT "FK_FOLLOW_FOLLOW_ID" FOREIGN KEY ("FOLLOW_ID") REFERENCES "MEMBER" ("ID")  -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_FOLLOW_MEMBER_ID" ON "FOLLOW" ("MEMBER_ID");
CREATE INDEX "IDX_FOLLOW_FOLLOW_ID" ON "FOLLOW" ("FOLLOW_ID");

COMMENT ON COLUMN "FOLLOW"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "FOLLOW"."FOLLOW_ID" IS '팔로우 대상 회원 FK';
COMMENT ON COLUMN "FOLLOW"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "FOLLOW"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 도서 테이블
-- ============================================ --
CREATE TABLE "BOOK" (
    "ID"                    NUMBER		            NOT NULL, -- 도서 PK
    "MEMBER_ID"	            NUMBER		            NOT NULL, -- 회원 FK
    "NAME"                  VARCHAR2(100)		    NOT NULL, -- 도서 이름
    "AUTHOR"                VARCHAR2(100)		    NOT NULL, -- 도서 저자
    "PUBLISHER"             VARCHAR2(100)		    NOT NULL, -- 도서 출판사
    "REG_METHOD"            NUMBER(1)		        NOT NULL, -- 도서 등록 방법
    "TOTAL_PAGE"            NUMBER		            NOT NULL, -- 도서 전체 페이지
    "IMAGE"                 VARCHAR2(255)		        NULL,     -- 도서 이미지
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL,  -- 데이터 생성 시각
    -- PK 설정
    CONSTRAINT "PK_BOOK" PRIMARY KEY ("ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_BOOK_MEMBER_ID" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID") -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_BOOK_MEMBER_ID" ON "BOOK" ("MEMBER_ID");

COMMENT ON COLUMN "BOOK"."ID" IS '도서 PK';
COMMENT ON COLUMN "BOOK"."NAME" IS '도서 이름';
COMMENT ON COLUMN "BOOK"."AUTHOR" IS '도서 저자';
COMMENT ON COLUMN "BOOK"."PUBLISHER" IS '도서 출판사';
COMMENT ON COLUMN "BOOK"."REG_METHOD" IS '도서 등록 방법';
COMMENT ON COLUMN "BOOK"."TOTAL_PAGE" IS '도서 전체 페이지';
COMMENT ON COLUMN "BOOK"."IMAGE" IS '도서 이미지';
COMMENT ON COLUMN "BOOK"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "BOOK"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 도서 좋아요 테이블
-- ============================================ --
CREATE TABLE "BOOK_LIKE" (
    "MEMBER_ID"	            NUMBER		            NOT NULL, -- 회원 FK
    "BOOK_ID"	            NUMBER		            NOT NULL, -- 도서 FK
    "ACTIVE"	            NUMBER(1)		        NOT NULL, -- 좋아요 활성화 여부
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 설정
    CONSTRAINT "PK_BOOK_LIKE" PRIMARY KEY ("MEMBER_ID", "BOOK_ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_BOOK_LIKE_MEMBER_ID" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"), -- FK 설정
    CONSTRAINT "FK_BOOK_LIKE_BOOK_ID" FOREIGN KEY ("BOOK_ID") REFERENCES "BOOK" ("ID")  -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_BOOK_LIKE_MEMBER_ID" ON "BOOK_LIKE" ("MEMBER_ID");
CREATE INDEX "IDX_BOOK_LIKE_BOOK_ID" ON "BOOK_LIKE" ("BOOK_ID");

COMMENT ON COLUMN "BOOK_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "BOOK_LIKE"."BOOK_ID" IS '도서 FK';
COMMENT ON COLUMN "BOOK_LIKE"."ACTIVE" IS '좋아요 활성화 여부';
COMMENT ON COLUMN "BOOK_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "BOOK_LIKE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 씨앗 테이블
-- ============================================ --
CREATE TABLE "SEED" (
    "ID"                    NUMBER		            NOT NULL, -- 씨앗 PK
    "NAME"	                VARCHAR(100)		    NOT NULL, -- 씨앗 이름
    -- PK 설정
    CONSTRAINT "PK_SEED" PRIMARY KEY ("ID") -- PK 설정
);

COMMENT ON COLUMN "SEED"."ID" IS '씨앗 PK';
COMMENT ON COLUMN "SEED"."NAME" IS '씨앗 이름';

-- ============================================ --
-- 트리 테이블
-- ============================================ --
CREATE TABLE "TREE" (
    "ID"                    NUMBER		            NOT NULL, -- 트리 PK
    "MEMBER_ID"	            NUMBER		            NOT NULL, -- 회원 FK
    "SEED_ID"	            NUMBER		            NOT NULL, -- 씨앗 FK
    "NAME"                  VARCHAR2(100)		    NOT NULL, -- 도서 이름
    "TITLE"                 VARCHAR2(100)		    NOT NULL, -- 트리 제목
    "DISCRIPTION"           VARCHAR2(1000)		    NOT NULL, -- 트리 설명
    "BOOK_MARK_COUNT"       NUMBER		            NOT NULL, -- 북마크 수
    "VISIBILITY"            NUMBER(1)		        NOT NULL, -- 트리 공개 여부
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 설정
    CONSTRAINT "PK_TREE" PRIMARY KEY ("ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_TREE_MEMBER_ID" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"), -- FK 설정
    CONSTRAINT "FK_TREE_SEED_ID" FOREIGN KEY ("SEED_ID") REFERENCES "SEED" ("ID")  -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_TREE_MEMBER_ID" ON "TREE" ("MEMBER_ID");
CREATE INDEX "IDX_TREE_SEED_ID" ON "TREE" ("SEED_ID");

COMMENT ON COLUMN "TREE"."ID" IS '트리 PK';
COMMENT ON COLUMN "TREE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "TREE"."SEED_ID" IS '씨앗 FK';
COMMENT ON COLUMN "TREE"."NAME" IS '도서 이름';
COMMENT ON COLUMN "TREE"."TITLE" IS '트리 제목';
COMMENT ON COLUMN "TREE"."DISCRIPTION" IS '트리 설명';
COMMENT ON COLUMN "TREE"."BOOK_MARK_COUNT" IS '북마크 수';
COMMENT ON COLUMN "TREE"."VISIBILITY" IS '트리 공개 여부';
COMMENT ON COLUMN "TREE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "TREE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 트리 이미지 테이블
-- ============================================ --
CREATE TABLE "TREE_IMAGE" (
    "TREE_ID"	            NUMBER		            NOT NULL, -- 트리 FK
    "IMAGE"                 VARCHAR2(255)		    NOT NULL, -- 트리 이미지
    -- PK 설정
    CONSTRAINT "PK_TREE_IMAGE" PRIMARY KEY ("TREE_ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_TREE_IMAGE_TREE_ID" FOREIGN KEY ("TREE_ID") REFERENCES "TREE" ("ID") -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_TREE_IMAGE_TREE_ID" ON "TREE_IMAGE" ("TREE_ID");

COMMENT ON COLUMN "TREE_IMAGE"."TREE_ID" IS '트리 FK';
COMMENT ON COLUMN "TREE_IMAGE"."IMAGE" IS '트리 이미지';

-- ============================================ --
-- 트리 도서 정보 테이블
-- ============================================ --
CREATE TABLE "TREE_BOOK" (
    "ID"                   NUMBER		            NOT NULL, -- 트리 도서 정보 PK
    "TOTAL_PAGE"	       NUMBER		            NOT NULL, -- 도서 전체 페이지
    "READED_PAGE"	       NUMBER		            NOT NULL, -- 도서 읽은 페이지
    "PROGRESS"	           NUMBER		            NOT NULL, -- 도서 진행률
    -- PK 설정
    CONSTRAINT "PK_TREE_BOOK" PRIMARY KEY ("ID") -- PK 설정
);

COMMENT ON COLUMN "TREE_BOOK"."ID" IS '트리 도서 정보 PK';
COMMENT ON COLUMN "TREE_BOOK"."TOTAL_PAGE" IS '도서 전체 페이지';
COMMENT ON COLUMN "TREE_BOOK"."READED_PAGE" IS '도서 읽은 페이지';
COMMENT ON COLUMN "TREE_BOOK"."PROGRESS" IS '도서 진행률';

-- ============================================ --
-- 트리 좋아요 테이블
-- ============================================ --
CREATE TABLE "TREE_LIKE" (
    "MEMBER_ID"	            NUMBER		            NOT NULL, -- 회원 FK
    "TREE_ID"	            NUMBER		            NOT NULL, -- 트리 FK
    "ACTIVE"	            NUMBER(1)		        NOT NULL, -- 좋아요 활성화 여부
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 생성 시갹
    -- PK 설정
    CONSTRAINT "PK_TREE_LIKE" PRIMARY KEY ("MEMBER_ID", "TREE_ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_TREE_LIKE_MEMBER_ID" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"), -- FK 설정
    CONSTRAINT "FK_TREE_LIKE_TREE_ID" FOREIGN KEY ("TREE_ID") REFERENCES "TREE" ("ID")  -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_TREE_LIKE_MEMBER_ID" ON "TREE_LIKE" ("MEMBER_ID");
CREATE INDEX "IDX_TREE_LIKE_TREE_ID" ON "TREE_LIKE" ("TREE_ID");

COMMENT ON COLUMN "TREE_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "TREE_LIKE"."TREE_ID" IS '트리 FK';
COMMENT ON COLUMN "TREE_LIKE"."ACTIVE" IS '좋아요 활성화 여부';
COMMENT ON COLUMN "TREE_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "TREE_LIKE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 회고록 테이블
-- ============================================ --
CREATE TABLE "MEMOIR" (
    "ID"                    NUMBER		            NOT NULL, -- 회고록 PK
    "TREE_ID"               NUMBER		            NOT NULL, -- 트리 FK
    "TITLE"                 VARCHAR2(100)		    NOT NULL, -- 회고록 제목
    "TEXT"                  VARCHAR2(1000)		    NOT NULL, -- 회고록 내용
    "VISIBILITY"            NUMBER(1)		        NOT NULL, -- 회고록 공개 여부
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 설정
    CONSTRAINT "PK_MEMOIR" PRIMARY KEY ("ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_MEMOIR_TREE_ID" FOREIGN KEY ("TREE_ID") REFERENCES "TREE" ("ID") -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_MEMOIR_TREE_ID" ON "MEMOIR" ("TREE_ID");

COMMENT ON COLUMN "MEMOIR"."ID" IS '회고록 PK';
COMMENT ON COLUMN "MEMOIR"."TREE_ID" IS '트리 FK';
COMMENT ON COLUMN "MEMOIR"."TITLE" IS '회고록 제목';
COMMENT ON COLUMN "MEMOIR"."TEXT" IS '회고록 내용';
COMMENT ON COLUMN "MEMOIR"."VISIBILITY" IS '회고록 공개 여부';
COMMENT ON COLUMN "MEMOIR"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "MEMOIR"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 회고록 좋아요 테이블
-- ============================================ --
CREATE TABLE "MEMOIR_LIKE" (
    "MEMBER_ID"	            NUMBER		            NOT NULL, -- 회원 FK
    "MEMOIR_ID"	            NUMBER		            NOT NULL, -- 회고록 FK
    "ACTIVE"	            NUMBER(1)		        NOT NULL, -- 좋아요 활성화 여부
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 생성 시갹
    -- PK 설정
    CONSTRAINT "PK_MEMOIR_LIKE" PRIMARY KEY ("MEMBER_ID", "MEMOIR_ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_MEMOIR_LIKE_MEMBER_ID" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"), -- FK 설정
    CONSTRAINT "FK_MEMOIR_LIKE_MEMOIR_ID" FOREIGN KEY ("MEMOIR_ID") REFERENCES "MEMOIR" ("ID")  -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_MEMOIR_LIKE_MEMBER_ID" ON "MEMOIR_LIKE" ("MEMBER_ID");
CREATE INDEX "IDX_MEMOIR_LIKE_MEMOIR_ID" ON "MEMOIR_LIKE" ("MEMOIR_ID");

-- ============================================ --
-- 회고록 댓글 테이블
-- ============================================ --
CREATE TABLE "MEMOIR_COMMENT" (
    "ID"                    NUMBER		            NOT NULL, -- 회고록 댓글 PK
    "MEMOIR_ID"             NUMBER		            NOT NULL, -- 회고록 FK
    "LIKE_COUNT"            NUMBER		            NOT NULL, -- 회고록 댓글 좋아요 수
    "LEAF_COMMENT"          VARCHAR2(1000)		    NOT NULL, -- 회고록 댓글 내용
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 설정
    CONSTRAINT "PK_MEMOIR_COMMENT" PRIMARY KEY ("ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_MEMOIR_COMMENT_MEMOIR_ID" FOREIGN KEY ("MEMOIR_ID") REFERENCES "MEMOIR" ("ID") -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_MEMOIR_COMMENT_MEMOIR_ID" ON "MEMOIR_COMMENT" ("MEMOIR_ID");

COMMENT ON COLUMN "MEMOIR_COMMENT"."ID" IS '회고록 댓글 PK';
COMMENT ON COLUMN "MEMOIR_COMMENT"."MEMOIR_ID" IS '회고록 FK';
COMMENT ON COLUMN "MEMOIR_COMMENT"."LIKE_COUNT" IS '회고록 댓글 좋아요 수';
COMMENT ON COLUMN "MEMOIR_COMMENT"."LEAF_COMMENT" IS '회고록 댓글 내용';
COMMENT ON COLUMN "MEMOIR_COMMENT"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "MEMOIR_COMMENT"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 회고록 댓글 좋아요 테이블
-- ============================================ --
CREATE TABLE "MEMOIR_COMMENT_LIKE" (
    "MEMBER_ID"	            NUMBER		            NOT NULL, -- 회원 FK
    "MEMOIR_COMMENT_ID"	    NUMBER		            NOT NULL, -- 회고록 댓글 FK
    "ACTIVE"	            NUMBER(1)		        NOT NULL, -- 좋아요 활성화 여부
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 생성 시갹
    -- PK 설정
    CONSTRAINT "PK_MEMOIR_COMMENT_LIKE" PRIMARY KEY ("MEMBER_ID", "MEMOIR_COMMENT_ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_MEMOIR_COMMENT_LIKE_MEMBER_ID" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"), -- FK 설정
    CONSTRAINT "FK_MEMOIR_COMMENT_LIKE_MEMOIR_COMMENT_ID" FOREIGN KEY ("MEMOIR_COMMENT_ID") REFERENCES "MEMOIR_COMMENT" ("ID")  -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_MEMOIR_COMMENT_LIKE_MEMBER_ID" ON "MEMOIR_COMMENT_LIKE" ("MEMBER_ID");
CREATE INDEX "IDX_MEMOIR_COMMENT_LIKE_MEMOIR_COMMENT_ID" ON "MEMOIR_COMMENT_LIKE" ("MEMOIR_COMMENT_ID");

COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."MEMOIR_COMMENT_ID" IS '회고록 댓글 FK';
COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."ACTIVE" IS '좋아요 활성화 여부';
COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."CREATE_TIME" IS '데이터 생성 시갹';

-- ============================================ --
-- 리프 테이블
-- ============================================ --
CREATE TABLE "LEAF" (
    "ID"                    NUMBER		            NOT NULL, -- 리프 PK
    "TREE_ID"	            NUMBER		            NOT NULL, -- 트리 FK
    "PARENT_LEAF_ID"	    NUMBER		            NULL,     -- 부모 리프 FK
    "VISIBILITY"            NUMBER(1)		        NOT NULL, -- 리프 공개 여부
    "VIEW_COUNT"            NUMBER		            NOT NULL, -- 리프 조회수
    "LIKE_COUNT"            NUMBER		            NOT NULL, -- 리프 좋아요 수
    "TITLE"                 VARCHAR2(100)		    NOT NULL, -- 리프 제목
    "TEXT"                  VARCHAR2(1000)		    NOT NULL, -- 리프 내용
    "CHILD_LEAF_COUNT"      NUMBER		            NOT NULL, -- 리프 자식 수
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 설정
    CONSTRAINT "PK_LEAF" PRIMARY KEY ("ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_LEAF_TREE_ID" FOREIGN KEY ("TREE_ID") REFERENCES "TREE" ("ID"), -- FK 설정
    CONSTRAINT "FK_LEAF_PARENT_LEAF_ID" FOREIGN KEY ("PARENT_LEAF_ID") REFERENCES "LEAF" ("ID")  -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_LEAF_TREE_ID" ON "LEAF" ("TREE_ID");
CREATE INDEX "IDX_LEAF_PARENT_LEAF_ID" ON "LEAF" ("PARENT_LEAF_ID");

COMMENT ON COLUMN "LEAF"."ID" IS '리프 PK';
COMMENT ON COLUMN "LEAF"."TREE_ID" IS '트리 FK';
COMMENT ON COLUMN "LEAF"."PARENT_LEAF_ID" IS '부모 리프 FK';
COMMENT ON COLUMN "LEAF"."VISIBILITY" IS '리프 공개 여부';
COMMENT ON COLUMN "LEAF"."VIEW_COUNT" IS '리프 조회수';
COMMENT ON COLUMN "LEAF"."LIKE_COUNT" IS '리프 좋아요 수';
COMMENT ON COLUMN "LEAF"."TITLE" IS '리프 제목';
COMMENT ON COLUMN "LEAF"."TEXT" IS '리프 내용';
COMMENT ON COLUMN "LEAF"."CHILD_LEAF_COUNT" IS '리프 자식 수';
COMMENT ON COLUMN "LEAF"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "LEAF"."CREATE_TIME" IS '데이터 생성 시각';


-- ============================================ --
-- 리프 태그 테이블
-- ============================================ --
CREATE TABLE "LEAF_TAG" (
    "ID"                    NUMBER		            NOT NULL, -- 리프 태그 PK
    "MBMER_ID"	            NUMBER		            NOT NULL, -- 회원 FK
    "NAME"	                VARCHAR2(100)		    NOT NULL, -- 리프 태그 이름
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 설정
    CONSTRAINT "PK_LEAF_TAG" PRIMARY KEY ("ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_LEAF_TAG_MEMBER_ID" FOREIGN KEY ("MBMER_ID") REFERENCES "MEMBER" ("ID") -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_LEAF_TAG_MEMBER_ID" ON "LEAF_TAG" ("MBMER_ID");

COMMENT ON COLUMN "LEAF_TAG"."ID" IS '리프 태그 PK';
COMMENT ON COLUMN "LEAF_TAG"."MBMER_ID" IS '회원 FK';
COMMENT ON COLUMN "LEAF_TAG"."NAME" IS '리프 태그 이름';
COMMENT ON COLUMN "LEAF_TAG"."UPDATE_TIME" IS '데이터 수정 시각';

-- ============================================ --
-- 리프 태그 관계 테이블
-- ============================================ --
CREATE TABLE "LEAF_TAG_MAP" (
    "LEAF_ID"	            NUMBER		            NOT NULL, -- 리프 FK
    "LEAF_TAG_ID"	        NUMBER		            NOT NULL, -- 리프 태그 FK
    -- PK 설정
    CONSTRAINT "PK_LEAF_TAG_MAP" PRIMARY KEY ("LEAF_ID", "LEAF_TAG_ID") -- PK 설정
);

COMMENT ON COLUMN "LEAF_TAG_MAP"."LEAF_ID" IS '리프 FK';
COMMENT ON COLUMN "LEAF_TAG_MAP"."LEAF_TAG_ID" IS '리프 태그 FK';

-- ============================================ --
-- 리프 좋아요 테이블
-- ============================================ --
CREATE TABLE "LEAF_LIKE" (
    "MEMBER_ID"	            NUMBER		            NOT NULL, -- 회원 FK
    "LEAF_ID"	            NUMBER		            NOT NULL, -- 리프 FK
    "ACTIVE"	            NUMBER(1)		        NOT NULL, -- 좋아요 활성화 여부
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 생성 시갹
    -- PK 설정
    CONSTRAINT "PK_LEAF_LIKE" PRIMARY KEY ("MEMBER_ID", "LEAF_ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_LEAF_LIKE_MEMBER_ID" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"), -- FK 설정
    CONSTRAINT "FK_LEAF_LIKE_LEAF_ID" FOREIGN KEY ("LEAF_ID") REFERENCES "LEAF" ("ID")  -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_LEAF_LIKE_MEMBER_ID" ON "LEAF_LIKE" ("MEMBER_ID");
CREATE INDEX "IDX_LEAF_LIKE_LEAF_ID" ON "LEAF_LIKE" ("LEAF_ID");

COMMENT ON COLUMN "LEAF_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "LEAF_LIKE"."LEAF_ID" IS '리프 FK';
COMMENT ON COLUMN "LEAF_LIKE"."ACTIVE" IS '좋아요 활성화 여부';
COMMENT ON COLUMN "LEAF_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "LEAF_LIKE"."CREATE_TIME" IS '데이터 생성 시갹';

-- ============================================ --
-- 리프 댓글 테이블
-- ============================================ --
CREATE TABLE "LEAF_COMMENT" (
    "ID"                    NUMBER		            NOT NULL, -- 리프 댓글 PK
    "LEAF_ID"	            NUMBER		            NOT NULL, -- 리프 FK
    "LIKE_COUNT"            NUMBER		            NOT NULL, -- 리프 댓글 좋아요 수
    "LEAF_COMMENT"          VARCHAR2(1000)		    NOT NULL, -- 리프 댓글 내용
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 설정
    CONSTRAINT "PK_LEAF_COMMENT" PRIMARY KEY ("ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_LEAF_COMMENT_LEAF_ID" FOREIGN KEY ("LEAF_ID") REFERENCES "LEAF" ("ID") -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_LEAF_COMMENT_LEAF_ID" ON "LEAF_COMMENT" ("LEAF_ID");

COMMENT ON COLUMN "LEAF_COMMENT"."ID" IS '리프 댓글 PK';
COMMENT ON COLUMN "LEAF_COMMENT"."LEAF_ID" IS '리프 FK';
COMMENT ON COLUMN "LEAF_COMMENT"."LIKE_COUNT" IS '리프 댓글 좋아요 수';
COMMENT ON COLUMN "LEAF_COMMENT"."LEAF_COMMENT" IS '리프 댓글 내용';
COMMENT ON COLUMN "LEAF_COMMENT"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "LEAF_COMMENT"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 리프 댓글 좋아요 테이블
-- ============================================ --
CREATE TABLE "LEAF_COMMENT_LIKE" (
    "MEMBER_ID"	            NUMBER		            NOT NULL, -- 회원 FK
    "LEAF_COMMENT_ID"	    NUMBER		            NOT NULL, -- 리프 댓글 FK
    "ACTIVE"	            NUMBER(1)		        NOT NULL, -- 좋아요 활성화 여부
    "UPDATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	        TIMESTAMP		        NOT NULL, -- 데이터 생성 시갹
    -- PK 설정
    CONSTRAINT "PK_LEAF_COMMENT_LIKE" PRIMARY KEY ("MEMBER_ID", "LEAF_COMMENT_ID"), -- PK 설정
    -- 연관 관계 설정
    CONSTRAINT "FK_LEAF_COMMENT_LIKE_MEMBER_ID" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"), -- FK 설정
    CONSTRAINT "FK_LEAF_COMMENT_LIKE_LEAF_COMMENT_ID" FOREIGN KEY ("LEAF_COMMENT_ID") REFERENCES "LEAF_COMMENT" ("ID")  -- FK 설정
);

-- FK 인덱스 설정
CREATE INDEX "IDX_LEAF_COMMENT_LIKE_MEMBER_ID" ON "LEAF_COMMENT_LIKE" ("MEMBER_ID");
CREATE INDEX "IDX_LEAF_COMMENT_LIKE_LEAF_COMMENT_ID" ON "LEAF_COMMENT_LIKE" ("LEAF_COMMENT_ID");

COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."LEAF_COMMENT_ID" IS '리프 댓글 FK';
COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."ACTIVE" IS '좋아요 활성화 여부';
COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."CREATE_TIME" IS '데이터 생성 시갹';