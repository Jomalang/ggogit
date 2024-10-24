-- ============================================ --
-- CREATE SCHEMA
-- ID:
-- PASSWORD:
-- 사용 데이터 베이스
-- DATABASE:
-- ============================================ --

-- ============================================ --
-- 모든 테이블 제거
-- ============================================ --
DROP TABLE IF EXISTS `LEAF_COMMENT_LIKE`;
DROP TABLE IF EXISTS `LEAF_COMMENT`;
DROP TABLE IF EXISTS `LEAF_LIKE`;
DROP TABLE IF EXISTS `LEAF_IMAGE`;
DROP TABLE IF EXISTS `LEAF_BOOK`;
DROP TABLE IF EXISTS `LEAF_TAG_MAP`;
DROP TABLE IF EXISTS `LEAF_TAG`;
DROP TABLE IF EXISTS `LEAF`;
DROP TABLE IF EXISTS `MEMOIR_COMMENT_LIKE`;
DROP TABLE IF EXISTS `MEMOIR_COMMENT`;
DROP TABLE IF EXISTS `MEMOIR_LIKE`;
DROP TABLE IF EXISTS `MEMOIR`;
DROP TABLE IF EXISTS `TREE_SAVE_TMP`;
DROP TABLE IF EXISTS `TREE_LIKE`;
DROP TABLE IF EXISTS `TREE_IMAGE`;
DROP TABLE IF EXISTS `TREE_BOOK`;
DROP TABLE IF EXISTS `TREE`;
DROP TABLE IF EXISTS `BOOK_COMMENT_LIKE`;
DROP TABLE IF EXISTS `BOOK_COMMENT`;
DROP TABLE IF EXISTS `BOOK_LIKE`;
DROP TABLE IF EXISTS `BOOK`;
DROP TABLE IF EXISTS `SEED`;
DROP TABLE IF EXISTS `FOLLOW`;
DROP TABLE IF EXISTS `MEMBER_BACKGROUND_IMAGE`;
DROP TABLE IF EXISTS `MEMBER_PROFILE_IMAGE`;
DROP TABLE IF EXISTS `MEMBER`;
DROP TABLE IF EXISTS `BOOK_CATEGORY`;


-- ============================================ --
-- 회원 테이블 생성
-- ============================================ --
CREATE TABLE `MEMBER` (
    `ID`            BIGINT(20)      NOT NULL AUTO_INCREMENT             COMMENT '회원 PK',
    `EMAIL`         VARCHAR(255)    NOT NULL                            COMMENT '회원 이메일',
    `PASSWORD`      VARCHAR(64)     NOT NULL                            COMMENT '회원 비밀번호',
    `NICKNAME`      VARCHAR(255)    NOT NULL                            COMMENT '회원 닉네임',
    `USERNAME`      VARCHAR(255)    NOT NULL                            COMMENT '회원 이름',
    `INTRODUCTION`  VARCHAR(3000)   NULL                                COMMENT '회원 소개글',
    `IS_DELETED`    BOOLEAN         NOT NULL DEFAULT FALSE              COMMENT '삭제 여부',
    `CREATE_TIME`   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`ID`),
    INDEX `INDEX_EMAIL` (`EMAIL`),
    UNIQUE KEY `UNIQUE_EMAIL` (`EMAIL`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 회원 프로필 이미지 생성
-- ============================================ --
CREATE TABLE `MEMBER_PROFILE_IMAGE` (
    `MEMBER_ID`     BIGINT(20)      NOT NULL                            COMMENT '회원 FK',
    `NAME`          VARCHAR(255)    NOT NULL                            COMMENT '파일 이름',
    `IS_DELETED`    BOOLEAN         NOT NULL DEFAULT FALSE              COMMENT '삭제 여부',
    `CREATE_TIME`   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`MEMBER_ID`),
    CONSTRAINT `FK_MEMBER_PROFILE_IMAGE_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 회원 배경 이미지 생성
-- ============================================ --
CREATE TABLE `MEMBER_BACKGROUND_IMAGE` (
    `MEMBER_ID`     BIGINT(20)      NOT NULL                            COMMENT '회원 FK',
    `NAME`          VARCHAR(255)    NOT NULL                            COMMENT '파일 이름',
    `IS_DELETED`    BOOLEAN         NOT NULL DEFAULT FALSE              COMMENT '삭제 여부',
    `CREATE_TIME`   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`MEMBER_ID`),
    CONSTRAINT `FK_MEMBER_BACKGROUND_IMAGE_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 팔로우 테이블 생성
-- ============================================ --
CREATE TABLE `FOLLOW` (
    `MEMBER_ID`     BIGINT(20)      NOT NULL                            COMMENT '회원 FK',
    `FOLLOW_ID`     BIGINT(20)      NOT NULL                            COMMENT '팔로우 FK',
    `ACTIVE`        BOOLEAN         NOT NULL DEFAULT TRUE               COMMENT '활성화 여부',
    `CREATE_TIME`   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`MEMBER_ID`, `FOLLOW_ID`),
    CONSTRAINT `FK_FOLLOW_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_FOLLOW_FOLLOW` FOREIGN KEY (`FOLLOW_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 도서 카테고리 테이블 생성
-- ============================================ --
CREATE TABLE `BOOK_CATEGORY` (
    `ID`            BIGINT(20)      NOT NULL AUTO_INCREMENT             COMMENT '도서 카테고리 PK',
    `NAME`          VARCHAR(255)    NOT NULL                            COMMENT '카테고리 이름',
    PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 도서 테이블 생성
-- ============================================ --
CREATE TABLE `BOOK` (
    `ID`                BIGINT(20)      NOT NULL AUTO_INCREMENT             COMMENT '도서 PK',
    `MEMBER_ID`         BIGINT(20)      NULL                                COMMENT '회원 FK', -- NULL 허용
    `BOOK_CATEGORY_ID`  BIGINT(20)      NOT NULL                            COMMENT '도서 카테고리 FK',
    `TITLE`             VARCHAR(255)    NOT NULL                            COMMENT '도서 제목',
    `AUTHOR`            VARCHAR(255)    NOT NULL                            COMMENT '도서 저자',
    `TRANSLATOR`        VARCHAR(255)    NULL                                COMMENT '도서 번역가',
    `ISBN`              VARCHAR(255)    NULL                                COMMENT 'ISBN',
    `PUBLISHER`         VARCHAR(255)    NOT NULL                            COMMENT '출판사',
    `PUBLISH_DATE`      DATE            NOT NULL                            COMMENT '출판일',
    `TOTAL_PAGE`        INT             NOT NULL                            COMMENT '총 페이지 수',
    `IMAGE_FILE`        VARCHAR(255)    NULL                                COMMENT '도서 이미지 파일',
    `IS_DELETED`        BOOLEAN         NOT NULL DEFAULT FALSE              COMMENT '삭제 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`ID`),
    INDEX `INDEX_TITLE` (`TITLE`),
    CONSTRAINT `FK_BOOK_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_BOOK_BOOK_CATEGORY` FOREIGN KEY (`BOOK_CATEGORY_ID`) REFERENCES `BOOK_CATEGORY` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 도서 좋아요 테이블 생성
-- ============================================ --
CREATE TABLE `BOOK_LIKE` (
    `MEMBER_ID`         BIGINT(20)      NOT NULL                            COMMENT '회원 FK',
    `BOOK_ID`           BIGINT(20)      NOT NULL                            COMMENT '도서 FK',
    `ACTIVE`            BOOLEAN         NOT NULL DEFAULT TRUE               COMMENT '활성화 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`MEMBER_ID`, `BOOK_ID`),
    CONSTRAINT `FK_BOOK_LIKE_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_BOOK_LIKE_BOOK` FOREIGN KEY (`BOOK_ID`) REFERENCES `BOOK` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 도서 댓글 테이블 생성
-- ============================================ --
CREATE TABLE `BOOK_COMMENT` (
    `ID`                BIGINT(20)      NOT NULL AUTO_INCREMENT             COMMENT '도서 댓글 PK',
    `MEMBER_ID`         BIGINT(20)      NOT NULL                            COMMENT '회원 FK',
    `BOOK_ID`           BIGINT(20)      NOT NULL                            COMMENT '도서 FK',
    `CONTENT`           VARCHAR(1000)   NOT NULL                            COMMENT '댓글 내용',
    `LIKE_COUNT`        INT             NOT NULL DEFAULT 0                  COMMENT '좋아요 수',
    `IS_DELETED`        BOOLEAN         NOT NULL DEFAULT FALSE              COMMENT '삭제 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`ID`),
    CONSTRAINT `FK_BOOK_COMMENT_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_BOOK_COMMENT_BOOK` FOREIGN KEY (`BOOK_ID`) REFERENCES `BOOK` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 도서 댓글 좋아요 테이블 생성
-- ============================================ --
CREATE TABLE `BOOK_COMMENT_LIKE` (
    `MEMBER_ID`         BIGINT(20)      NOT NULL                            COMMENT '회원 FK',
    `BOOK_COMMENT_ID`   BIGINT(20)      NOT NULL                            COMMENT '도서 댓글 FK',
    `ACTIVE`            BOOLEAN         NOT NULL DEFAULT TRUE               COMMENT '활성화 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`MEMBER_ID`, `BOOK_COMMENT_ID`),
    CONSTRAINT `FK_BOOK_COMMENT_LIKE_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_BOOK_COMMENT_LIKE_BOOK_COMMENT` FOREIGN KEY (`BOOK_COMMENT_ID`) REFERENCES `BOOK_COMMENT` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 씨앗 테이블 생성
-- ============================================ --
CREATE TABLE `SEED` (
    `ID`                BIGINT(20)      NOT NULL AUTO_INCREMENT             COMMENT '씨앗 PK',
    `KOR_NAME`              VARCHAR(255)    NOT NULL                            COMMENT '씨앗 한글 이름',
    `ENG_NAME`              VARCHAR(255)    NOT NULL                            COMMENT '씨앗 영어 이름',
    PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 트리 생성 탬프 테이블
-- ============================================ --
CREATE TABLE `TREE_SAVE_TMP` (
    `ID`                BIGINT(20)      NOT NULL AUTO_INCREMENT COMMENT '트리 PK',
    `MEMBER_ID`         BIGINT(20)      NOT NULL                COMMENT '회원 FK',
    -- 도서 정보 (도서 트리 생성시에만 사용)
    `BOOK_CATEGORY_ID`  BIGINT(20)      NULL                    COMMENT '도서 카테고리 FK',
    `BOOK_TITLE`        VARCHAR(255)    NULL                    COMMENT '도서 제목',
    `AUTHOR`            VARCHAR(255)    NULL                    COMMENT '도서 저자',
    `PUBLISHER`         VARCHAR(255)    NULL                    COMMENT '출판사',
    `TOTAL_PAGE`        INT             NULL                    COMMENT '총 페이지 수',
    -- 선택 도서 트리 생성
    `BOOK_ID`           BIGINT(20)      NULL                    COMMENT '도서 FK',
    -- 트리 정보
    `SEED_ID`           BIGINT(20)      NOT NULL                COMMENT '씨앗 FK',
    `TREE_TITLE`        VARCHAR(3000)   NOT NULL                COMMENT '트리 제목',
    `DESCRIPTION`       VARCHAR(3000)   NOT NULL                COMMENT '트리 설명',
    `IMAGE_FILE`        VARCHAR(255)    NULL                COMMENT '트리 이미지 파일',
    `VISIBILITY`        BOOLEAN         NOT NULL DEFAULT TRUE   COMMENT '공개 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 날짜',
    PRIMARY KEY (`ID`),
    CONSTRAINT `FK_TREE_SAVE_TMP_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_TREE_SAVE_TMP_BOOK_CATEGORY` FOREIGN KEY (`BOOK_CATEGORY_ID`) REFERENCES `BOOK_CATEGORY` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_TREE_SAVE_TMP_SEED` FOREIGN KEY (`SEED_ID`) REFERENCES `SEED` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_TREE_SAVE_TMP_BOOK` FOREIGN KEY (`BOOK_ID`) REFERENCES `BOOK` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- ============================================ --
-- 트리 테이블 생성
-- ============================================ --
CREATE TABLE `TREE` (
    `ID`                BIGINT(20)      NOT NULL AUTO_INCREMENT             COMMENT '트리 PK',
    `MEMBER_ID`         BIGINT(20)      NOT NULL                            COMMENT '회원 FK',
    `BOOK_ID`           BIGINT(20)      NOT NULL                            COMMENT '도서 FK',
    `SEED_ID`           BIGINT(20)      NOT NULL                            COMMENT '씨앗 FK',
    `TITLE`             VARCHAR(3000)   NOT NULL                            COMMENT '트리 제목',
    `DESCRIPTION`       VARCHAR(3000)   NOT NULL                            COMMENT '트리 설명',
    `BOOK_MARK_COUNT`   INT             NOT NULL DEFAULT 0                  COMMENT '북마크 수',
    `VISIBILITY`        BOOLEAN         NOT NULL DEFAULT TRUE               COMMENT '공개 여부',
    `IS_DELETED`        BOOLEAN         NOT NULL DEFAULT FALSE              COMMENT '삭제 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`ID`),
    INDEX `INDEX_TITLE` (`TITLE`),
    CONSTRAINT `FK_TREE_BOOK` FOREIGN KEY (`BOOK_ID`) REFERENCES `BOOK` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_TREE_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_TREE_SEED` FOREIGN KEY (`SEED_ID`) REFERENCES `SEED` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 도서 트리 추가 정보 테이블 생성
-- ============================================ --
CREATE TABLE `TREE_BOOK` (
    `TREE_ID`           BIGINT(20)      NOT NULL                            COMMENT '트리 FK',
    `READING_PAGE`      BIGINT(20)      NOT NULL                            COMMENT '읽은 페이지 수',
    `IS_DELETED`        BOOLEAN         NOT NULL DEFAULT FALSE              COMMENT '삭제 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`TREE_ID`),
    CONSTRAINT `FK_TREE_BOOK_TREE` FOREIGN KEY (`TREE_ID`) REFERENCES `TREE` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 트리 이미지 테이블 생성
-- ============================================ --
CREATE TABLE `TREE_IMAGE` (
    `TREE_ID`           BIGINT(20)      NOT NULL                            COMMENT '트리 FK',
    `NAME`              VARCHAR(255)    NOT NULL                            COMMENT '파일 이름',
    `IS_DELETED`        BOOLEAN         NOT NULL DEFAULT FALSE              COMMENT '삭제 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`TREE_ID`),
    CONSTRAINT `FK_TREE_IMAGE_TREE` FOREIGN KEY (`TREE_ID`) REFERENCES `TREE` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 트리 좋아요 테이블 생성
-- ============================================ --
CREATE TABLE `TREE_LIKE` (
    `MEMBER_ID`         BIGINT(20)      NOT NULL                            COMMENT '회원 FK',
    `TREE_ID`           BIGINT(20)      NOT NULL                            COMMENT '트리 FK',
    `ACTIVE`            BOOLEAN         NOT NULL DEFAULT TRUE               COMMENT '활성화 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`MEMBER_ID`, `TREE_ID`),
    CONSTRAINT `FK_TREE_LIKE_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_TREE_LIKE_TREE` FOREIGN KEY (`TREE_ID`) REFERENCES `TREE` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 회고록 테이블 생성
-- ============================================ --
CREATE TABLE `MEMOIR` (
    `ID`                BIGINT(20)      NOT NULL AUTO_INCREMENT             COMMENT '회고록 PK',
    `TREE_ID`           BIGINT(20)      NOT NULL                            COMMENT '트리 FK',
    `TITLE`             VARCHAR(255)    NOT NULL                            COMMENT '회고록 제목',
    `CONTENT`           VARCHAR(3000)   NOT NULL                            COMMENT '회고록 내용',
    `VISIBILITY`        BOOLEAN         NOT NULL DEFAULT TRUE               COMMENT '공개 여부',
    `IS_DELETED`        BOOLEAN         NOT NULL DEFAULT FALSE              COMMENT '삭제 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`ID`),
    INDEX `INDEX_TITLE` (`TITLE`),
    CONSTRAINT `FK_MEMOIR_TREE` FOREIGN KEY (`TREE_ID`) REFERENCES `TREE` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 회고록 좋아요 테이블 생성
-- ============================================ --
CREATE TABLE `MEMOIR_LIKE` (
    `MEMBER_ID`         BIGINT(20)      NOT NULL                            COMMENT '회원 FK',
    `MEMOIR_ID`         BIGINT(20)      NOT NULL                            COMMENT '회고록 FK',
    `ACTIVE`            BOOLEAN         NOT NULL DEFAULT TRUE               COMMENT '활성화 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`MEMBER_ID`, `MEMOIR_ID`),
    CONSTRAINT `FK_MEMOIR_LIKE_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_MEMOIR_LIKE_MEMOIR` FOREIGN KEY (`MEMOIR_ID`) REFERENCES `MEMOIR` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 회고록 댓글 테이블 생성
-- ============================================ --
CREATE TABLE `MEMOIR_COMMENT` (
    `ID`                BIGINT(20)      NOT NULL AUTO_INCREMENT             COMMENT '회고록 댓글 PK',
    `MEMBER_ID`         BIGINT(20)      NOT NULL                            COMMENT '회원 FK',
    `MEMOIR_ID`         BIGINT(20)      NOT NULL                            COMMENT '회고록 FK',
    `LIKE_COUNT`        INT             NOT NULL DEFAULT 0                  COMMENT '좋아요 수',
    `CONTENT`           VARCHAR(1000)   NOT NULL                            COMMENT '댓글 내용',
    `IS_DELETED`        BOOLEAN         NOT NULL DEFAULT FALSE              COMMENT '삭제 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`ID`),
    CONSTRAINT `FK_MEMOIR_COMMENT_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_MEMOIR_COMMENT_MEMOIR` FOREIGN KEY (`MEMOIR_ID`) REFERENCES `MEMOIR` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 회고록 댓글 좋아요 테이블 생성
-- ============================================ --
CREATE TABLE `MEMOIR_COMMENT_LIKE` (
    `MEMBER_ID`         BIGINT(20)      NOT NULL                            COMMENT '회원 FK',
    `MEMOIR_COMMENT_ID` BIGINT(20)      NOT NULL                            COMMENT '회고록 댓글 FK',
    `ACTIVE`            BOOLEAN         NOT NULL DEFAULT TRUE               COMMENT '활성화 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`MEMBER_ID`, `MEMOIR_COMMENT_ID`),
    CONSTRAINT `FK_MEMOIR_COMMENT_LIKE_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_MEMOIR_COMMENT_LIKE_MEMOIR_COMMENT` FOREIGN KEY (`MEMOIR_COMMENT_ID`) REFERENCES `MEMOIR_COMMENT` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 리프 태그 테이블 생성
-- ============================================ --
CREATE TABLE `LEAF_TAG` (
    `ID`                BIGINT(20)      NOT NULL AUTO_INCREMENT                 COMMENT '리프 태그 PK',
    `MEMBER_ID`         BIGINT(20)      NOT NULL                                COMMENT '회원 FK',
    `NAME`              VARCHAR(255)    NOT NULL                                COMMENT '태그 이름',
    `IS_DELETED`        BOOLEAN         NOT NULL DEFAULT FALSE                  COMMENT '삭제 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP      COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`ID`),
    CONSTRAINT `FK_LEAF_TAG_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 리프 테이블 생성
-- ============================================ --
CREATE TABLE `LEAF` (
    `ID`                BIGINT(20)      NOT NULL AUTO_INCREMENT             COMMENT '리프 PK',
    `TREE_ID`           BIGINT(20)      NOT NULL                            COMMENT '트리 FK',
    `PARENT_LEAF_ID`    BIGINT(20)      NULL                                COMMENT '부모 리프 FK',
    `TITLE`             VARCHAR(255)    NOT NULL                            COMMENT '리프 제목',
    `CONTENT`           VARCHAR(3000)   NOT NULL                            COMMENT '리프 내용',
    `VIEW_COUNT`        INT             NOT NULL DEFAULT 0                  COMMENT '조회수',
    `LIKE_COUNT`        INT             NOT NULL DEFAULT 0                  COMMENT '좋아요 수',
    `CHILD_LEAF_COUNT`  INT             NOT NULL DEFAULT 0                  COMMENT '자식 리프 수',
    `VISIBILITY`        BOOLEAN         NOT NULL DEFAULT TRUE               COMMENT '공개 여부',
    `BOOK_MARK`         BOOLEAN         NOT NULL DEFAULT FALSE              COMMENT '북마크 여부',
    `IS_DELETED`        BOOLEAN         NOT NULL DEFAULT FALSE              COMMENT '삭제 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP      COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`ID`),
    CONSTRAINT `FK_LEAF_TREE` FOREIGN KEY (`TREE_ID`) REFERENCES `TREE` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_LEAF_PARENT_LEAF` FOREIGN KEY (`PARENT_LEAF_ID`) REFERENCES `LEAF` (`ID`) ON DELETE RESTRICT
);

-- ============================================ --
-- 리프 태그 맵핑 테이블 생성
-- ============================================ --
CREATE TABLE `LEAF_TAG_MAP` (
    `LEAF_ID`           BIGINT(20)      NOT NULL                    COMMENT '리프 FK',
    `LEAF_TAG_ID`       BIGINT(20)      NOT NULL                    COMMENT '리프 태그 FK',
    `IS_DELETED`        BOOLEAN         NOT NULL DEFAULT FALSE      COMMENT '삭제 여부',
    PRIMARY KEY (`LEAF_ID`, `LEAF_TAG_ID`),
    CONSTRAINT `FK_LEAF_TAG_MAP_LEAF` FOREIGN KEY (`LEAF_ID`) REFERENCES `LEAF` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_LEAF_TAG_MAP_LEAF_TAG` FOREIGN KEY (`LEAF_TAG_ID`) REFERENCES `LEAF_TAG` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 도서 트리 리프 추가 정보 테이블 생성
-- ============================================ --
CREATE TABLE `LEAF_BOOK` (
    `LEAF_ID`           BIGINT(20)      NOT NULL                    COMMENT '리프 FK',
    `START_PAGE`        INT             NOT NULL                    COMMENT '시작 페이지',
    `END_PAGE`          INT             NOT NULL                    COMMENT '끝 페이지',
    `IS_DELETED`        BOOLEAN         NOT NULL DEFAULT FALSE      COMMENT '삭제 여부',
    PRIMARY KEY (`LEAF_ID`),
    CONSTRAINT `FK_LEAF_BOOK_LEAF` FOREIGN KEY (`LEAF_ID`) REFERENCES `LEAF` (`ID`) ON DELETE RESTRICT
);

-- ============================================ --
-- 리프 이미지 테이블 생성
-- ============================================ --
CREATE TABLE `LEAF_IMAGE` (
    `ID`                BIGINT(20)      NOT NULL AUTO_INCREMENT             COMMENT '리프 이미지 PK',
    `LEAF_ID`           BIGINT(20)      NOT NULL                            COMMENT '리프 FK',
    `NAME`              VARCHAR(255)    NOT NULL                            COMMENT '파일 이름',
    `IS_DELETED`        BOOLEAN         NOT NULL DEFAULT FALSE              COMMENT '삭제 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`ID`),
    CONSTRAINT `FK_LEAF_IMAGE_LEAF` FOREIGN KEY (`LEAF_ID`) REFERENCES `LEAF` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 리프 좋아요 테이블 생성
-- ============================================ --
CREATE TABLE `LEAF_LIKE` (
    `MEMBER_ID`         BIGINT(20)      NOT NULL                            COMMENT '회원 FK',
    `LEAF_ID`           BIGINT(20)      NOT NULL                            COMMENT '리프 FK',
    `ACTIVE`            BOOLEAN         NOT NULL DEFAULT TRUE               COMMENT '활성화 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`MEMBER_ID`, `LEAF_ID`),
    CONSTRAINT `FK_LEAF_LIKE_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_LEAF_LIKE_LEAF` FOREIGN KEY (`LEAF_ID`) REFERENCES `LEAF` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 리프 댓글 테이블 생성
-- ============================================ --
CREATE TABLE `LEAF_COMMENT` (
    `ID`                BIGINT(20)      NOT NULL AUTO_INCREMENT             COMMENT '리프 댓글 PK',
    `MEMBER_ID`         BIGINT(20)      NOT NULL                            COMMENT '회원 FK',
    `LEAF_ID`           BIGINT(20)      NOT NULL                            COMMENT '리프 FK',
    `LIKE_COUNT`        INT             NOT NULL DEFAULT 0                  COMMENT '좋아요 수',
    `CONTENT`           VARCHAR(1000)   NOT NULL                            COMMENT '댓글 내용',
    `IS_DELETED`        BOOLEAN         NOT NULL DEFAULT FALSE              COMMENT '삭제 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`ID`),
    CONSTRAINT `FK_LEAF_COMMENT_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_LEAF_COMMENT_LEAF` FOREIGN KEY (`LEAF_ID`) REFERENCES `LEAF` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ============================================ --
-- 리프 댓글 좋아요 테이블 생성
-- ============================================ --
CREATE TABLE `LEAF_COMMENT_LIKE` (
    `MEMBER_ID`         BIGINT(20)      NOT NULL                            COMMENT '회원 FK',
    `LEAF_COMMENT_ID`   BIGINT(20)      NOT NULL                            COMMENT '리프 댓글 FK',
    `ACTIVE`            BOOLEAN         NOT NULL DEFAULT TRUE               COMMENT '활성화 여부',
    `CREATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '생성 날짜',
    `UPDATE_TIME`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜',
    PRIMARY KEY (`MEMBER_ID`, `LEAF_COMMENT_ID`),
    CONSTRAINT `FK_LEAF_COMMENT_LIKE_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`) ON DELETE RESTRICT,
    CONSTRAINT `FK_LEAF_COMMENT_LIKE_LEAF_COMMENT` FOREIGN KEY (`LEAF_COMMENT_ID`) REFERENCES `LEAF_COMMENT` (`ID`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=UTF8;