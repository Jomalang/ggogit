-- ============================================ --
-- Create schema
-- id: root
-- password: 1234
-- 사용 데이터 베이스
-- database: ggogit
-- ============================================ --

-- ============================================ --
-- 회원 테이블 데이터 생성
-- ============================================ --
DELIMITER //
DROP PROCEDURE IF EXISTS SET_DATA_MEMBER;
CREATE PROCEDURE SET_DATA_MEMBER(
    IN V_MEMBER_COUNT INT
)
BEGIN
    DECLARE i INT DEFAULT 1;

    WHILE i <= V_MEMBER_COUNT DO
        INSERT INTO `MEMBER` (
            `ID`,
            `EMAIL`,
            `PASSWORD`,
            `NICKNAME`,
            `INTRODUCTION`,
            `CREATE_DATE`,
            `UPDATE_DATE`
        )
        VALUES (
            i,
            CONCAT('test', i, '@test.com'),
            '1234',
            CONCAT('nickName', i),
            CONCAT('introduction', i),
            NOW(),
            NOW()
        );
        SET i = i + 1;
    END WHILE;
END //
DELIMITER ;
CALL ggogit.SET_DATA_MEMBER(100);

-- ============================================ --
-- 회원 프로필 이미지 생성
-- ============================================ --
DELIMITER //
DROP PROCEDURE IF EXISTS SET_DATA_MEMBER_PROFILE_IMAGE;
CREATE PROCEDURE SET_DATA_MEMBER_PROFILE_IMAGE()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE V_MEMBER_COUNT INT;
    SELECT COUNT(*) INTO V_MEMBER_COUNT FROM `MEMBER`;

    WHILE i <= V_MEMBER_COUNT DO
        INSERT INTO `MEMBER_PROFILE_IMAGE` (
            `MEMBER_ID`,
            `NAME`,
            `CREATE_DATE`,
            `UPDATE_DATE`
        )
        VALUES (
            i,
            CONCAT('profileImage', i, '.jpg'),
            NOW(),
            NOW()
        );
        SET i = i + 1;
    END WHILE;
END //
DELIMITER ;
CALL ggogit.SET_DATA_MEMBER_PROFILE_IMAGE();

-- ============================================ --
-- 회원 배경 이미지 생성
-- ============================================ --
DELIMITER //
DROP PROCEDURE IF EXISTS SET_DATA_MEMBER_BACKGROUND_IMAGE;
CREATE PROCEDURE SET_DATA_MEMBER_BACKGROUND_IMAGE()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE V_MEMBER_COUNT INT;
    SELECT COUNT(*) INTO V_MEMBER_COUNT FROM `MEMBER`;

    WHILE i <= V_MEMBER_COUNT DO
        INSERT INTO `MEMBER_BACKGROUND_IMAGE` (
            `MEMBER_ID`,
            `NAME`,
            `CREATE_DATE`,
            `UPDATE_DATE`
        )
        VALUES (
            i,
            CONCAT('backgroundImage', i, '.jpg'),
            NOW(),
            NOW()
        );
        SET i = i + 1;
    END WHILE;
END //
DELIMITER ;
CALL ggogit.SET_DATA_MEMBER_BACKGROUND_IMAGE();

-- ============================================ --
-- 팔로우 테이블 생성
-- ============================================ --
DELIMITER //
DROP PROCEDURE IF EXISTS SET_DATA_FOLLOW;
CREATE PROCEDURE SET_DATA_FOLLOW(
    IN V_FOLLOW_LIMIT_COUNT INT
)
BEGIN
    DECLARE V_INPUT_COUNT INT DEFAULT 0;
    DECLARE V_FROM INT DEFAULT 1;
    DECLARE V_TO INT DEFAULT 1;
    DECLARE V_MEMBER_COUNT INT;
    DECLARE V_FOLLOW_COUNT INT;
    SELECT COUNT(*) INTO V_MEMBER_COUNT FROM `MEMBER`;
    SELECT COUNT(*) INTO V_FOLLOW_COUNT FROM `MEMBER`;

    LOOP_FROM: WHILE V_FROM <= V_MEMBER_COUNT DO
        LOOP_TO: WHILE V_TO <= V_MEMBER_COUNT DO
            IF V_FROM = V_TO THEN -- 자기 자신 팔로우 제외
                SET V_TO = V_TO + 1;
                ITERATE LOOP_TO;
            END IF;

            -- 팔로우 최대 제한
            IF 0 < V_FOLLOW_LIMIT_COUNT
                AND V_FOLLOW_LIMIT_COUNT <= V_INPUT_COUNT THEN
                LEAVE LOOP_FROM;
            END IF;

            -- 팔로우 데이터 생성
            INSERT INTO `FOLLOW` (
                `MEMBER_ID`,
                `FOLLOW_ID`,
                `CREATE_DATE`,
                `UPDATE_DATE`
            ) VALUE (
                V_FROM,
                V_TO,
                NOW(),
                NOW()
            );

            SET V_INPUT_COUNT = V_INPUT_COUNT + 1;
            SET V_TO = V_TO + 1;
        END WHILE LOOP_TO;
        SET V_TO = 1;
        SET V_FROM = V_FROM + 1;
    END WHILE LOOP_FROM;
END //
DELIMITER ;
CALL ggogit.SET_DATA_FOLLOW(-1);

-- ============================================ --
-- 도서 테이블 데이터 생성
-- ============================================ --
DELIMITER //
DROP PROCEDURE IF EXISTS SET_DATA_BOOK;
CREATE PROCEDURE SET_DATA_BOOK(
    IN V_BOOK_COUNT INT
)
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE V_BOOK_COUNT INT;

    WHILE i <= V_BOOK_COUNT DO
        INSERT INTO `BOOK` (
            `ID`,
            `TITLE`,
            `AUTHOR`,
            `PUBLISHER`,
            `PUBLISHED_DATE`,
            `DESCRIPTION`,
            `CREATE_DATE`,
            `UPDATE_DATE`
        )
        VALUES (
            i,
            CONCAT('title', i),
            CONCAT('author', i),
            CONCAT('publisher', i),
            CONCAT('publishedDate', i),
            CONCAT('description', i),
            NOW(),
            NOW()
        );
        SET i = i + 1;
    END WHILE;
END //