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
    "ID"	            NUMBER		            NOT NULL, -- 회원 PK
    "EMAIL"	            VARCHAR2(255)	        NOT NULL, -- 회원 이메일
    "PASSWORD"	        VARCHAR2(64)	        NOT NULL, -- 회원 비밀번호
    "NICKNAME"	        VARCHAR2(255)		    NOT NULL, -- 회원 닉네임
    "INTRODUCTION"	    NVARCHAR2(2000) 	    NULL, -- 회원 소개글
    "UPDATE_TIME"	    TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 정의
    CONSTRAINT "PK_MEMBER" PRIMARY KEY ("ID")
);

COMMENT ON COLUMN "MEMBER"."ID" IS '회원 PK';
COMMENT ON COLUMN "MEMBER"."EMAIL" IS '회원 이메일';
COMMENT ON COLUMN "MEMBER"."PASSWORD" IS '회원 비밀번호';
COMMENT ON COLUMN "MEMBER"."NICKNAME" IS '회원 닉네임';
COMMENT ON COLUMN "MEMBER"."INTRODUCTION" IS '회원 소개글';
COMMENT ON COLUMN "MEMBER"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "MEMBER"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 회원 배경 이미지 테이블
-- ============================================ --
CREATE TABLE "MEMBER_BACKGROUND_IMAGE" (
    "ID"	        NUMBER		    NOT NULL, -- 회원 배경 이미지 PK
    "NAME"	        VARCHAR2(255)	NOT NULL, -- 회원 배경 이미지 이름
    -- PK 정의
    CONSTRAINT "PK_MEMBER_BACKGROUND_IMAGE" PRIMARY KEY ("ID")
);

COMMENT ON COLUMN "MEMBER_BACKGROUND_IMAGE"."ID" IS '회원 배경 이미지 PK';
COMMENT ON COLUMN "MEMBER_BACKGROUND_IMAGE"."NAME" IS '회원 배경 이미지 이름';

-- ============================================ --
-- 팔로우 테이블
-- ============================================ --
CREATE TABLE "FOLLOW" (
    "MEMBER_ID"	    NUMBER		            NOT NULL, -- 회원 FK
    "FOLLOW_ID"	    NUMBER		            NOT NULL, -- 팔로우 대상 회원 FK
    "UPDATE_TIME"	TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 정의
    CONSTRAINT "PK_FOLLOW" PRIMARY KEY ("MEMBER_ID", "FOLLOW_ID"),
    -- FK 정의
    CONSTRAINT "FK_FOLLOW_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_FOLLOW_FOLLOW" FOREIGN KEY ("FOLLOW_ID") REFERENCES "MEMBER" ("ID")
);

COMMENT ON COLUMN "FOLLOW"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "FOLLOW"."FOLLOW_ID" IS '팔로우 대상 회원 FK';
COMMENT ON COLUMN "FOLLOW"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "FOLLOW"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 도서 테이블
-- ============================================ --
CREATE TABLE "BOOK" (
    "ID"             NUMBER		            NOT NULL, -- 도서 PK
    "MEMBER_ID"      NUMBER		            NOT NULL, -- 회원 FK
    "NAME"           NVARCHAR2(1024)	    NOT NULL, -- 도서 제목
    "AUTHOR"         NVARCHAR2(1024)	    NOT NULL, -- 도서 저자
    "PUBLISHER"      NVARCHAR2(1024)	    NOT NULL, -- 도서 출판사
    "TOTAL_PAGE"     NUMBER		            NOT NULL, -- 도서 총 페이지 수
    "IMAGE_FILE"     VARCHAR2(1024)	        NULL, -- 도서 이미지 파일
    "UPDATE_TIME"    TIMESTAMP	            NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"    TIMESTAMP	            NOT NULL, -- 데이터 생성 시각
    "RESOURCE_FROM"  NUMBER(1)	            NOT NULL, -- 도서 출처
    -- PK 정의
    CONSTRAINT "PK_BOOK" PRIMARY KEY ("ID"),
    -- FK 정의
    CONSTRAINT "FK_BOOK_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID")
);

COMMENT ON COLUMN "BOOK"."ID" IS '도서 PK';
COMMENT ON COLUMN "BOOK"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "BOOK"."NAME" IS '도서 제목';
COMMENT ON COLUMN "BOOK"."AUTHOR" IS '도서 저자';
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
    "MEMBER_ID"     NUMBER		            NOT NULL, -- 회원 FK
    "BOOK_ID"	    NUMBER		            NOT NULL, -- 도서 FK
    "ACTIVATE"	    NUMBER(1)	DEFAULT 1	NOT NULL, -- 도서 좋아요 활성화 여부
    "UPDATE_TIME"	TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 정의
    CONSTRAINT "PK_BOOK_LIKE" PRIMARY KEY ("MEMBER_ID", "BOOK_ID"),
    -- FK 정의
    CONSTRAINT "FK_BOOK_LIKE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_BOOK_LIKE_BOOK" FOREIGN KEY ("BOOK_ID") REFERENCES "BOOK" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_BOOK_LIKE_MEMBER_ID" ON "BOOK_LIKE" ("MEMBER_ID");
CREATE INDEX "IDX_BOOK_LIKE_BOOK_ID" ON "BOOK_LIKE" ("BOOK_ID");

COMMENT ON COLUMN "BOOK_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "BOOK_LIKE"."BOOK_ID" IS '도서 FK';
COMMENT ON COLUMN "BOOK_LIKE"."ACTIVATE" IS '도서 좋아요 활성화 여부';
COMMENT ON COLUMN "BOOK_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "BOOK_LIKE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 도서 댓글 테이블
-- ============================================ --
CREATE TABLE "BOOK_COMMENT" (
    "ID"	        NUMBER		                    NOT NULL, -- 도서 댓글 PK
    "MEMBER_ID"	    NUMBER		                    NOT NULL, -- 회원 FK
    "BOOK_ID"	    NUMBER		                    NOT NULL, -- 도서 FK
    "LIKE_COUNT"	NUMBER	            DEFAULT 0	NOT NULL, -- 도서 댓글 좋아요 수
    "COMMENT"	    NVARCHAR2(2000)		            NOT NULL, -- 도서 댓글 내용
    "UPDATE_TIME"	TIMESTAMP		                NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	TIMESTAMP		                NOT NULL, -- 데이터 생성 시각
    -- PK 정의
    CONSTRAINT "PK_BOOK_COMMENT" PRIMARY KEY ("ID"),
    -- FK 정의
    CONSTRAINT "FK_BOOK_COMMENT_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_BOOK_COMMENT_BOOK" FOREIGN KEY ("BOOK_ID") REFERENCES "BOOK" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_BOOK_COMMENT_MEMBER_ID" ON "BOOK_COMMENT" ("MEMBER_ID");
CREATE INDEX "IDX_BOOK_COMMENT_BOOK_ID" ON "BOOK_COMMENT" ("BOOK_ID");

COMMENT ON COLUMN "BOOK_COMMENT"."ID" IS '도서 댓글 PK';
COMMENT ON COLUMN "BOOK_COMMENT"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "BOOK_COMMENT"."BOOK_ID" IS '도서 FK';
COMMENT ON COLUMN "BOOK_COMMENT"."LIKE_COUNT" IS '도서 댓글 좋아요 수';
COMMENT ON COLUMN "BOOK_COMMENT"."COMMENT" IS '도서 댓글 내용';
COMMENT ON COLUMN "BOOK_COMMENT"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "BOOK_COMMENT"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 도서 댓글 좋아요 테이블
-- ============================================ --
CREATE TABLE "BOOK_COMMENT_LIKE" (
    "MEMBER_ID"	        NUMBER		            NOT NULL, -- 회원 FK
    "BOOK_COMMENT_ID"   NUMBER		            NOT NULL, -- 도서 댓글 FK
    "ACTIVATE"	        NUMBER(1)	DEFAULT 1	NOT NULL, -- 도서 댓글 좋아요 활성화 여부
    "UPDATE_TIME"	    TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 정의
    CONSTRAINT "PK_BOOK_COMMENT_LIKE" PRIMARY KEY ("MEMBER_ID", "BOOK_COMMENT_ID"),
    -- FK 정의
    CONSTRAINT "FK_BOOK_COMMENT_LIKE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_BOOK_COMMENT_LIKE_BOOK_COMMENT" FOREIGN KEY ("BOOK_COMMENT_ID") REFERENCES "BOOK_COMMENT" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_BOOK_COMMENT_LIKE_MEMBER_ID" ON "BOOK_COMMENT_LIKE" ("MEMBER_ID");
CREATE INDEX "IDX_BOOK_COMMENT_LIKE_BOOK_COMMENT_ID" ON "BOOK_COMMENT_LIKE" ("BOOK_COMMENT_ID");

COMMENT ON COLUMN "BOOK_COMMENT_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "BOOK_COMMENT_LIKE"."BOOK_COMMENT_ID" IS '도서 댓글 FK';
COMMENT ON COLUMN "BOOK_COMMENT_LIKE"."ACTIVATE" IS '도서 댓글 좋아요 활성화 여부';
COMMENT ON COLUMN "BOOK_COMMENT_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "BOOK_COMMENT_LIKE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 씨드 테이블
-- ============================================ --
CREATE TABLE "SEED" (
    "ID"	            NUMBER		        NOT NULL, -- 씨앗 PK
    "NAME"	            NVARCHAR2(30)		NOT NULL, -- 씨앗 제목
    -- PK 정의
    CONSTRAINT "PK_SEED" PRIMARY KEY ("ID")
);

COMMENT ON COLUMN "SEED"."ID" IS '씨앗 PK';
COMMENT ON COLUMN "SEED"."NAME" IS '씨앗 제목';

-- ============================================ --
-- 트리 테이블
-- ============================================ --
CREATE TABLE "TREE" (
    "ID"	            NUMBER		        NOT NULL,
    "MEMBER_ID"	        NUMBER		        NOT NULL,
    "SEED_ID"	        NUMBER		        NOT NULL,
    "TITLE"	            NVARCHAR2(300)		NOT NULL,
	"DESCRIPTION"	    NVARCHAR2(2000)		NOT NULL,
	"BOOK_MARK_COUNT"   NUMBER(1)	        DEFAULT 0	NOT NULL,
	"VISIBILITY"	    NUMBER(1)	        DEFAULT 1	NOT NULL,
	"UPDATE_TIME"	    TIMESTAMP           DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"CREATE_TIME"	    TIMESTAMP	        DEFAULT CURRENT_TIMESTAMP	NOT NULL,
    -- PK 정의
    CONSTRAINT "PK_TREE" PRIMARY KEY ("ID"),
    -- FK 정의
    CONSTRAINT "FK_TREE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_TREE_SEED" FOREIGN KEY ("SEED_ID") REFERENCES "SEED" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_TREE_MEMBER_ID" ON "TREE" ("MEMBER_ID");
CREATE INDEX "IDX_TREE_SEED_ID" ON "TREE" ("SEED_ID");

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
    "ID"	        NUMBER		    NOT NULL, -- 책 트리 PK
    "TOTAL_PAGE"	NUMBER(5)		NOT NULL, -- 책 트리 총 페이지 수
    "READING_PAGE"	NUMBER(5)		NOT NULL, -- 책 트리 읽은 페이지 수
    "PROGRESS"	    NUMBER(3)		NOT NULL, -- 책 트리 진행률
    -- PK 정의
    CONSTRAINT "PK_TREE_BOOK" PRIMARY KEY ("ID")
);

COMMENT ON COLUMN "TREE_BOOK"."ID" IS '책 트리 PK';
COMMENT ON COLUMN "TREE_BOOK"."TOTAL_PAGE" IS '책 트리 총 페이지 수';
COMMENT ON COLUMN "TREE_BOOK"."READING_PAGE" IS '책 트리 읽은 페이지 수';
COMMENT ON COLUMN "TREE_BOOK"."PROGRESS" IS '책 트리 진행률';

-- ============================================ --
-- 트리 이미지 테이블
-- ============================================ --
CREATE TABLE "TREE_IMAGE" (
    "ID"	NUMBER		    NOT NULL, -- 트리 이미지 PK
    "NAME"	VARCHAR2(255)	NOT NULL, -- 트리 이미지 이름
    --- PK 정의
    CONSTRAINT "PK_TREE_IMAGE" PRIMARY KEY ("ID")
);

COMMENT ON COLUMN "TREE_IMAGE"."ID" IS '트리 이미지 PK';
COMMENT ON COLUMN "TREE_IMAGE"."NAME" IS '트리 이미지 이름';

-- ============================================ --
-- 트리 좋아요 테이블
-- ============================================ --
CREATE TABLE "TREE_LIKE" (
    "MEMBER_ID"	    NUMBER		            NOT NULL, -- 회원 FK
    "TREE_ID"	    NUMBER		            NOT NULL, -- 트리 FK
    "ACTIVATE"	    NUMBER(1)	DEFAULT 1	NOT NULL, -- 트리 좋아요 활성화 여부
    "UPDATE_TIME"	TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 정의
    CONSTRAINT "PK_TREE_LIKE" PRIMARY KEY ("MEMBER_ID", "TREE_ID"),
    -- FK 정의
    CONSTRAINT "FK_TREE_LIKE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_TREE_LIKE_TREE" FOREIGN KEY ("TREE_ID") REFERENCES "TREE" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_TREE_LIKE_MEMBER_ID" ON "TREE_LIKE" ("MEMBER_ID");
CREATE INDEX "IDX_TREE_LIKE_TREE_ID" ON "TREE_LIKE" ("TREE_ID");

COMMENT ON COLUMN "TREE_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "TREE_LIKE"."TREE_ID" IS '트리 FK';
COMMENT ON COLUMN "TREE_LIKE"."ACTIVATE" IS '트리 좋아요 활성화 여부';
COMMENT ON COLUMN "TREE_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "TREE_LIKE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 회고록 테이블
-- ============================================ --
CREATE TABLE "MEMOIR" (
                          "ID"	            NUMBER		            NOT NULL, -- 회고록 PK
                          "TREE_ID"	        NUMBER		            NOT NULL, -- 트리 FK
                          "TITLE"	            VARCHAR(255)		    NOT NULL, -- 회고록 제목
                          "TEXT"	            CLOB		            NOT NULL, -- 회고록 내용
                          "VISIBILITY"	    NUMBER(1)	DEFAULT 1	NOT NULL, -- 사용자 공개 여부
                          "UPDATE_TIME"	    TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
                          "CREATE_TIME"	    TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 정의
                          CONSTRAINT "PK_MEMOIR" PRIMARY KEY ("ID"),
    -- FK 정의
                          CONSTRAINT "FK_MEMOIR_TREE" FOREIGN KEY ("TREE_ID") REFERENCES "TREE" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_MEMOIR_TREE_ID" ON "MEMOIR" ("TREE_ID");

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
                               "MEMBER_ID"	    NUMBER		            NOT NULL, -- 회원 FK
                               "MEMOIR_ID"	    NUMBER		            NOT NULL, -- 회고록 FK
                               "ACTIVATE"	    NUMBER(1)	DEFAULT 1	NOT NULL, -- 회고록 좋아요 활성화 여부
                               "UPDATE_TIME"	TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
                               "CREATE_TIME"	TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 정의
                               CONSTRAINT "PK_MEMOIR_LIKE" PRIMARY KEY ("MEMBER_ID", "MEMOIR_ID"),
    -- FK 정의
                               CONSTRAINT "FK_MEMOIR_LIKE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
                               CONSTRAINT "FK_MEMOIR_LIKE_MEMOIR" FOREIGN KEY ("MEMOIR_ID") REFERENCES "MEMOIR" ("ID")
);

COMMENT ON COLUMN "MEMOIR_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "MEMOIR_LIKE"."MEMOIR_ID" IS '회고록 FK';
COMMENT ON COLUMN "MEMOIR_LIKE"."ACTIVATE" IS '회고록 좋아요 활성화 여부';
COMMENT ON COLUMN "MEMOIR_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "MEMOIR_LIKE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 회고록 댓글 테이블
-- ============================================ --
CREATE TABLE "MEMOIR_COMMENT" (
                                  "ID"	            NUMBER		            NOT NULL, -- 회고록 댓글 PK
                                  "MEMBER_ID"	        NUMBER		            NOT NULL, -- 회원 FK
                                  "MEMOIR_ID"	        NUMBER		            NOT NULL, -- 회고록 FK
                                  "LIKE_COUNT"	    NUMBER	DEFAULT 0	NOT NULL, -- 회고록 댓글 좋아요 수
                                  "COMMENT"	        NVARCHAR2(2000)		    NOT NULL, -- 회고록 댓글 내용
                                  "UPDATE_TIME"	    TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
                                  "CREATE_TIME"	    TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 정의
                                  CONSTRAINT "PK_MEMOIR_COMMENT" PRIMARY KEY ("ID"),
    -- FK 정의
                                  CONSTRAINT "FK_MEMOIR_COMMENT_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
                                  CONSTRAINT "FK_MEMOIR_COMMENT_MEMOIR" FOREIGN KEY ("MEMOIR_ID") REFERENCES "MEMOIR" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_MEMOIR_COMMENT_MEMBER_ID" ON "MEMOIR_COMMENT" ("MEMBER_ID");
CREATE INDEX "IDX_MEMOIR_COMMENT_MEMOIR_ID" ON "MEMOIR_COMMENT" ("MEMOIR_ID");

COMMENT ON COLUMN "MEMOIR_COMMENT"."ID" IS '회고록 댓글 PK';
COMMENT ON COLUMN "MEMOIR_COMMENT"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "MEMOIR_COMMENT"."MEMOIR_ID" IS '회고록 FK';
COMMENT ON COLUMN "MEMOIR_COMMENT"."LIKE_COUNT" IS '회고록 댓글 좋아요 수';
COMMENT ON COLUMN "MEMOIR_COMMENT"."COMMENT" IS '회고록 댓글 내용';
COMMENT ON COLUMN "MEMOIR_COMMENT"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "MEMOIR_COMMENT"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 회고록 댓글 좋아요 테이블
-- ============================================ --
CREATE TABLE "MEMOIR_COMMENT_LIKE" (
                                       "MEMBER_ID"	        NUMBER		            NOT NULL, -- 회원 FK
                                       "MEMOIR_COMMENT_ID"	NUMBER		            NOT NULL, -- 회고록 댓글 FK
                                       "ACTIVATE"	        NUMBER(1)	DEFAULT 1	NOT NULL, -- 회고록 댓글 좋아요 활성화 여부
                                       "UPDATE_TIME"	    TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
                                       "CREATE_TIME"	    TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 정의
                                       CONSTRAINT "PK_MEMOIR_COMMENT_LIKE" PRIMARY KEY ("MEMBER_ID", "MEMOIR_COMMENT_ID"),
    -- FK 정의
                                       CONSTRAINT "FK_MEMOIR_COMMENT_LIKE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
                                       CONSTRAINT "FK_MEMOIR_COMMENT_LIKE_MEMOIR_COMMENT" FOREIGN KEY ("MEMOIR_COMMENT_ID") REFERENCES "MEMOIR_COMMENT" ("ID")
);

COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."MEMOIR_COMMENT_ID" IS '회고록 댓글 FK';
COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."ACTIVATE" IS '회고록 댓글 좋아요 활성화 여부';
COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "MEMOIR_COMMENT_LIKE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 리프 태그 테이블
-- ============================================ --
CREATE TABLE "LEAF_TAG" (
    "ID"	        NUMBER		    NOT NULL, -- 리프 태그 PK
    "MEMBER_ID"	    NUMBER		    NOT NULL, -- 회원 FK
    "NAME"	        NVARCHAR2(20)	NOT NULL, -- 리프 태그 이름
    "UPDATE_TIME"	TIMESTAMP		NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	TIMESTAMP 		NOT NULL, -- 데이터 생성 시각
    -- PK 정의
    CONSTRAINT "PK_LEAF_TAG" PRIMARY KEY ("ID"),
    -- FK 정의
    CONSTRAINT "FK_LEAF_TAG_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_LEAF_TAG_MEMBER_ID" ON "LEAF_TAG" ("MEMBER_ID");

COMMENT ON COLUMN "LEAF_TAG"."ID" IS '리프 태그 PK';
COMMENT ON COLUMN "LEAF_TAG"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "LEAF_TAG"."NAME" IS '리프 태그 이름';
COMMENT ON COLUMN "LEAF_TAG"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "LEAF_TAG"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 리프 테이블
-- ============================================ --
CREATE TABLE "LEAF" (
    "ID"	                NUMBER		                        NOT NULL,
    "TREE_ID"	            NUMBER		                        NOT NULL,
    "PARENT_LEAF_ID"	    NUMBER		                        NULL,
    "VISIBILITY"	        NUMBER(1)	    DEFAULT 1	        NOT NULL,
    "VIEW_COUNT"	        NUMBER		                        NOT NULL,
    "LIKE_COUNT"	        NUMBER	        DEFAULT 0	        NOT NULL,
    "TITLE"	                NVARCHAR2(100)		                NOT NULL,
    "TEXT"	                NVARCHAR2(2000)		                NOT NULL,
	"CHILD_LEAF_COUNT"	    NUMBER(1)	    DEFAULT 0	        NOT NULL,
	"UPDATE_TIME"	        TIMESTAMP		                    NOT NULL,
	"CREATE_TIME"	        TIMESTAMP		                    NOT NULL,
	"BOOK_MARK"	            NUMBER(1)	    DEFAULT 0	        NOT NULL,
    -- PK 정의
    CONSTRAINT "PK_LEAF" PRIMARY KEY ("ID"),
    -- FK 정의
    CONSTRAINT "FK_LEAF_TREE" FOREIGN KEY ("TREE_ID") REFERENCES "TREE" ("ID"),
    CONSTRAINT "FK_LEAF_PARENT_LEAF" FOREIGN KEY ("PARENT_LEAF_ID") REFERENCES "LEAF" ("ID")
);

COMMENT ON COLUMN "LEAF"."ID" IS '리프 PK';
COMMENT ON COLUMN "LEAF"."TREE_ID" IS '트리 FK';
COMMENT ON COLUMN "LEAF"."PARENT_LEAF_ID" IS '부모 리프 FK';
COMMENT ON COLUMN "LEAF"."VISIBILITY" IS '사용자 공개 여부';
COMMENT ON COLUMN "LEAF"."VIEW_COUNT" IS '리프 조회수';
COMMENT ON COLUMN "LEAF"."LIKE_COUNT" IS '리프 좋아요 수';
COMMENT ON COLUMN "LEAF"."TITLE" IS '리프 제목';
COMMENT ON COLUMN "LEAF"."TEXT" IS '리프 내용';
COMMENT ON COLUMN "LEAF"."CHILD_LEAF_COUNT" IS '자식 리프 수';
COMMENT ON COLUMN "LEAF"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "LEAF"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 리프 태그 맵 테이블
-- ============================================ --
CREATE TABLE "LEAF_TAG_MAP" (
                                "LEAF_ID"	    NUMBER		    NOT NULL, -- 리프 FK
                                "LEAF_TAG_ID"	NUMBER		    NOT NULL, -- 리프 태그 FK
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
    "MEMBER_ID"	    NUMBER		            NOT NULL, -- 회원 FK
    "LEAF_ID"	    NUMBER		            NOT NULL, -- 리프 FK
    "ACTIVATE"	    NUMBER(1)	DEFAULT 1	NOT NULL, -- 리프 좋아요 활성화 여부
    "UPDATE_TIME"	TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 정의
    CONSTRAINT "PK_LEAF_LIKE" PRIMARY KEY ("MEMBER_ID", "LEAF_ID"),
    -- FK 정의
    CONSTRAINT "FK_LEAF_LIKE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_LEAF_LIKE_LEAF" FOREIGN KEY ("LEAF_ID") REFERENCES "LEAF" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_LEAF_LIKE_MEMBER_ID" ON "LEAF_LIKE" ("MEMBER_ID");

COMMENT ON COLUMN "LEAF_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "LEAF_LIKE"."LEAF_ID" IS '리프 FK';
COMMENT ON COLUMN "LEAF_LIKE"."ACTIVATE" IS '리프 좋아요 활성화 여부';
COMMENT ON COLUMN "LEAF_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "LEAF_LIKE"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 리프 댓글 테이블
-- ============================================ --
CREATE TABLE "LEAF_COMMENT" (
    "ID"	            NUMBER		            NOT NULL, -- 리프 댓글 PK
    "MEMBER_ID"	        NUMBER		            NOT NULL, -- 회원 FK
    "LEAF_ID"	        NUMBER		            NOT NULL, -- 리프 FK
    "LIKE_COUNT"	    NUMBER	DEFAULT 0	NOT NULL, -- 리프 댓글 좋아요 수
    "COMMENT"	        NVARCHAR2(2000)		    NOT NULL, -- 리프 댓글 내용
    "UPDATE_TIME"	    TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 정의
    CONSTRAINT "PK_LEAF_COMMENT" PRIMARY KEY ("ID"),
    -- FK 정의
    CONSTRAINT "FK_LEAF_COMMENT_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_LEAF_COMMENT_LEAF" FOREIGN KEY ("LEAF_ID") REFERENCES "LEAF" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_LEAF_COMMENT_MEMBER_ID" ON "LEAF_COMMENT" ("MEMBER_ID");
CREATE INDEX "IDX_LEAF_COMMENT_LEAF_ID" ON "LEAF_COMMENT" ("LEAF_ID");

COMMENT ON COLUMN "LEAF_COMMENT"."ID" IS '리프 댓글 PK';
COMMENT ON COLUMN "LEAF_COMMENT"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "LEAF_COMMENT"."LEAF_ID" IS '리프 FK';
COMMENT ON COLUMN "LEAF_COMMENT"."LIKE_COUNT" IS '리프 댓글 좋아요 수';
COMMENT ON COLUMN "LEAF_COMMENT"."COMMENT" IS '리프 댓글 내용';
COMMENT ON COLUMN "LEAF_COMMENT"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "LEAF_COMMENT"."CREATE_TIME" IS '데이터 생성 시각';

-- ============================================ --
-- 리프 댓글 좋아요 테이블
-- ============================================ --
CREATE TABLE "LEAF_COMMENT_LIKE" (
    "MEMBER_ID"	        NUMBER		            NOT NULL, -- 회원 FK
    "LEAF_COMMENT_ID"	NUMBER		            NOT NULL, -- 리프 댓글 FK
    "ACTIVATE"	        NUMBER(1)	DEFAULT 1	NOT NULL, -- 리프 댓글 좋아요 활성화 여부
    "UPDATE_TIME"	    TIMESTAMP		        NOT NULL, -- 데이터 수정 시각
    "CREATE_TIME"	    TIMESTAMP		        NOT NULL, -- 데이터 생성 시각
    -- PK 정의
    CONSTRAINT "PK_LEAF_COMMENT_LIKE" PRIMARY KEY ("MEMBER_ID", "LEAF_COMMENT_ID"),
    -- FK 정의
    CONSTRAINT "FK_LEAF_COMMENT_LIKE_MEMBER" FOREIGN KEY ("MEMBER_ID") REFERENCES "MEMBER" ("ID"),
    CONSTRAINT "FK_LEAF_COMMENT_LIKE_LEAF_COMMENT" FOREIGN KEY ("LEAF_COMMENT_ID") REFERENCES "LEAF_COMMENT" ("ID")
);

-- 인덱스 정의
CREATE INDEX "IDX_LEAF_COMMENT_LIKE_MEMBER_ID" ON "LEAF_COMMENT_LIKE" ("MEMBER_ID");
CREATE INDEX "IDX_LEAF_COMMENT_LIKE_LEAF_COMMENT_ID" ON "LEAF_COMMENT_LIKE" ("LEAF_COMMENT_ID");

COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."MEMBER_ID" IS '회원 FK';
COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."LEAF_COMMENT_ID" IS '리프 댓글 FK';
COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."ACTIVATE" IS '리프 댓글 좋아요 활성화 여부';
COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."UPDATE_TIME" IS '데이터 수정 시각';
COMMENT ON COLUMN "LEAF_COMMENT_LIKE"."CREATE_TIME" IS '데이터 생성 시각';