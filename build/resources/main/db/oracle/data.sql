-- ============================================ --
-- 테스트 데이터 예시

-- #회원#
-- 회원 수 : 10명
-- 회원 배경 이미지 수 : 10개
-- 회원 팔로우 수 : 10명

-- #도서#
-- 도서 수 : 10권
-- 도서 좋아요 수 : 10개
-- 도서 댓글 수 : 10개
-- 도서 댓글 좋아요 수 : 10개

-- #트리#
-- 씨앗 수 : 10개
-- 트리 수 : 10 * 10 = 100개
-- 트리 책 추가 정보 : 10개
-- 트리 이미지 수 : 10개
-- 트리 좋아요 수 : 10 * 10 = 100개

-- #회고록#
-- 회고록 수 : 10개
-- 회고록 좋아요 수 : 10개
-- 회고록 댓글 수 : 10개
-- 회고록 댓글 좋아요 수 : 10개

-- #리프#
-- 리프 수 : 10 * 10 = 100개
-- 리프 태그 수 : 10개
-- 리프 태그 연동 데이터 수 : 10개
-- 리프 도서 추가 정보 : 10개
-- 리프 좋아요 수 : 10 * 10 = 100개
-- 리프 댓글 수 : 10개
-- 리프 댓글 좋아요 수 : 10개
-- ============================================ --

-- ============================================ --
-- 데이터 설정 테이블 초기화
-- ============================================ --
BEGIN
    FOR i IN (SELECT table_name FROM user_tables) LOOP
        IF i.table_name = 'DATA_SETTING' THEN
            EXECUTE IMMEDIATE 'DROP TABLE ' || i.table_name || ' CASCADE CONSTRAINTS';
        END IF;
    END LOOP;
END;

CREATE TABLE DATA_SETTING (
    -- 데이터 타입
    DATA_TYPE VARCHAR2(100),
    -- 회원
    MEMBER_COUNT NUMBER,
    MEMBER_BACKGROUND_IMAGE_COUNT NUMBER,
    FOLLOW_COUNT NUMBER,
    -- 도서
    BOOK_COUNT NUMBER,
    BOOK_LIKE_COUNT NUMBER,
    BOOK_COMMENT_COUNT NUMBER,
    BOOK_COMMENT_LIKE_COUNT NUMBER,
    -- 트리
    SEED_COUNT NUMBER,
    TREE_COUNT NUMBER,
    TREE_BOOK_COUNT NUMBER,
    TREE_IMAGE_COUNT NUMBER,
    TREE_LIKE_COUNT NUMBER,
    -- 회고록
    MEMOIR_COUNT NUMBER,
    MEMOIR_LIKE_COUNT NUMBER,
    MEMOIR_COMMENT_COUNT NUMBER,
    MEMOIR_COMMENT_LIKE_COUNT NUMBER,
    -- 리프
    LEAF_COUNT NUMBER,
    LEAF_TAG_COUNT NUMBER,
    LEAF_TAG_MAP_COUNT NUMBER,
    LEAF_BOOK_COUNT NUMBER,
    LEAF_LIKE_COUNT NUMBER,
    LEAF_COMMENT_COUNT NUMBER,
    LEAF_COMMENT_LIKE_COUNT NUMBER
);
COMMIT;

INSERT INTO DATA_SETTING (
    DATA_TYPE,
    MEMBER_COUNT,
    MEMBER_BACKGROUND_IMAGE_COUNT,
    FOLLOW_COUNT,
    BOOK_COUNT,
    BOOK_LIKE_COUNT,
    BOOK_COMMENT_COUNT,
    BOOK_COMMENT_LIKE_COUNT,
    SEED_COUNT,
    TREE_COUNT,
    TREE_BOOK_COUNT,
    TREE_IMAGE_COUNT,
    TREE_LIKE_COUNT,
    MEMOIR_COUNT,
    MEMOIR_LIKE_COUNT,
    MEMOIR_COMMENT_COUNT,
    MEMOIR_COMMENT_LIKE_COUNT,
    LEAF_COUNT,
    LEAF_TAG_COUNT,
    LEAF_TAG_MAP_COUNT,
    LEAF_BOOK_COUNT,
    LEAF_LIKE_COUNT,
    LEAF_COMMENT_COUNT,
    LEAF_COMMENT_LIKE_COUNT
) VALUES (
            'INPUT', -- 데이터 타입
             10, -- 회원 수
             10, -- 회원 배경 이미지 수(회원수와 동일하거나 적어야 함)
             10, -- 회원 팔로우 수
             10, -- 도서 수
             10, -- 도서 좋아요 수
             10, -- 도서 댓글 수
             10, -- 도서 댓글 좋아요 수
             10, -- 씨앗 수
             100, -- 트리 수
             10, -- 트리 책 추가 정보
             10, -- 트리 이미지 수
             100, -- 트리 좋아요 수
             10, -- 회고록 수
             10, -- 회고록 좋아요 수
             10, -- 회고록 댓글 수
             10, -- 회고록 댓글 좋아요 수
             100, -- 리프 수
             10, -- 리프 태그 수
             10, -- 리프 태그 연동 데이터 수
             10, -- 리프 도서 추가 정보
             100, -- 리프 좋아요 수
             10, -- 리프 댓글 수
             10 -- 리프 댓글 좋아요 수
);

INSERT INTO DATA_SETTING (
    DATA_TYPE,
    MEMBER_COUNT,
    MEMBER_BACKGROUND_IMAGE_COUNT,
    FOLLOW_COUNT,
    BOOK_COUNT,
    BOOK_LIKE_COUNT,
    BOOK_COMMENT_COUNT,
    BOOK_COMMENT_LIKE_COUNT,
    SEED_COUNT,
    TREE_COUNT,
    TREE_BOOK_COUNT,
    TREE_IMAGE_COUNT,
    TREE_LIKE_COUNT,
    MEMOIR_COUNT,
    MEMOIR_LIKE_COUNT,
    MEMOIR_COMMENT_COUNT,
    MEMOIR_COMMENT_LIKE_COUNT,
    LEAF_COUNT,
    LEAF_TAG_COUNT,
    LEAF_TAG_MAP_COUNT,
    LEAF_BOOK_COUNT,
    LEAF_LIKE_COUNT,
    LEAF_COMMENT_COUNT,
    LEAF_COMMENT_LIKE_COUNT
) VALUES (
             'RESULT', -- 데이터 타입
             NULL, -- 회원 수
             NULL, -- 회원 배경 이미지 수(회원수와 동일하거나 적어야 함)
             NULL, -- 회원 팔로우 수
             NULL, -- 도서 수
             NULL, -- 도서 좋아요 수
             NULL, -- 도서 댓글 수
             NULL, -- 도서 댓글 좋아요 수
             NULL, -- 씨앗 수
             NULL, -- 트리 수
             NULL, -- 트리 책 추가 정보
             NULL, -- 트리 이미지 수
             NULL, -- 트리 좋아요 수
             NULL, -- 회고록 수
             NULL, -- 회고록 좋아요 수
             NULL, -- 회고록 댓글 수
             NULL, -- 회고록 댓글 좋아요 수
             NULL, -- 리프 수
             NULL, -- 리프 태그 수
             NULL, -- 리프 태그 연동 데이터 수
             NULL, -- 리프 도서 추가 정보
             NULL, -- 리프 좋아요 수
             NULL, -- 리프 댓글 수
             NULL -- 리프 댓글 좋아요 수
);
COMMIT;

SELECT * FROM DATA_SETTING;

-- ============================================ --
-- 회원 테이블 데이터 추가
-- ============================================ --
DECLARE
    V_MEMBER_COUNT NUMBER;
    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN
    -- 회원수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    -- 데이터 삽입
    FOR i IN 1..V_MEMBER_COUNT LOOP
            INSERT INTO MEMBER (
                "EMAIL",
                "PASSWORD",
                "NICKNAME",
                "INTRODUCTION",
                "UPDATE_TIME",
                "CREATE_TIME"
            ) VALUES (
                         'test' || i || '@gmail.com',
                         '1234',
                         'test' || i,
                         '안녕하세요. 저는 test' || i || '입니다.',
                         SYSDATE,
                         SYSDATE
            );
            V_INPUT_COUNT := V_INPUT_COUNT + 1;
        END LOOP;

    -- 데이터 적재 수 업데이트
    UPDATE DATA_SETTING
    SET MEMBER_COUNT = V_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;

-- ============================================ --
-- 회원 배경 이미지 테이블 데이터 추가
-- ============================================ --
DECLARE
    V_MEMBER_COUNT NUMBER;
    V_MEMBER_BACKGROUND_IMAGE_COUNT NUMBER;
    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN

    -- 회원수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';
    -- 회원 배경 이미지 수 조회
    SELECT MEMBER_BACKGROUND_IMAGE_COUNT INTO V_MEMBER_BACKGROUND_IMAGE_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    -- 데이터 삽입
    FOR i IN 1..V_MEMBER_COUNT LOOP
        INSERT INTO MEMBER_BACKGROUND_IMAGE (
            "ID",
            "NAME"
        ) VALUES (
            i,
            'test.jpg'
        );
        V_INPUT_COUNT := V_INPUT_COUNT + 1;
    END LOOP;

    -- 데이터 적재 수 업데이트
    UPDATE DATA_SETTING
    SET MEMBER_BACKGROUND_IMAGE_COUNT = V_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;

-- ============================================ --
-- 팔로우 데이터 추가
-- ============================================ --
DECLARE
    V_MEMBER_COUNT NUMBER;
    V_FOLLOW_COUNT NUMBER;
    -- 데이터 적재 수 확인
    V_INSERT_COUNT NUMBER := 0;
BEGIN
    -- 회원 수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT FROM DATA_SETTING;
    -- 팔로우 수 조회
    SELECT FOLLOW_COUNT INTO V_FOLLOW_COUNT FROM DATA_SETTING;

    -- 데이터 삽입
    FOR V_FROM IN 1..V_MEMBER_COUNT LOOP
        FOR V_TO IN 1..V_MEMBER_COUNT LOOP

            -- 팔로우 수 제한
            IF V_FOLLOW_COUNT <= V_INSERT_COUNT THEN
                EXIT;
            END IF;

            -- 팔로우 추가
            IF V_FROM != V_TO THEN
                INSERT INTO FOLLOW (
                    "MEMBER_ID",
                    "FOLLOW_ID",
                    "CREATE_TIME",
                    "UPDATE_TIME"
                ) VALUES (
                    V_FROM,
                    V_TO,
                    SYSDATE,
                    SYSDATE
                );
                V_INSERT_COUNT := V_INSERT_COUNT + 1;
            END IF;
        END LOOP;
    END LOOP;

    UPDATE DATA_SETTING
    SET FOLLOW_COUNT = V_INSERT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;

-- ============================================ --
-- 도서 테이블 데이터 추가
-- ============================================ --
DECLARE
    V_BOOK_COUNT NUMBER;
    V_MEMBER_COUNT NUMBER;
    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN

    -- 도서 수 조회
    SELECT BOOK_COUNT INTO V_BOOK_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    -- 회원 수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    -- API 데이터 삽입
    FOR i IN 1..FLOOR(V_BOOK_COUNT / 2) LOOP
            INSERT INTO BOOK (
                MEMBER_ID,
                NAME,
                AUTHOR,
                PUBLISHER,
                TOTAL_PAGE,
                IMAGE_FILE,
                CREATE_TIME,
                UPDATE_TIME,
                RESOURCE_FROM
            ) VALUES (
                1, -- API 입력 계정
                'test' || i,
                'test' || i,
                'test' || i,
                100,
                'test.jpg',
                SYSDATE,
                SYSDATE,
                1 -- API: 1, 직접: 2
            );
            V_INPUT_COUNT := V_INPUT_COUNT + 1;
    END LOOP;

    -- 직접 도서 입력 데이터 삽입
    FOR i IN CEIL(V_BOOK_COUNT / 2)..V_BOOK_COUNT LOOP
            INSERT INTO BOOK (
                MEMBER_ID,
                NAME,
                AUTHOR,
                PUBLISHER,
                TOTAL_PAGE,
                IMAGE_FILE,
                CREATE_TIME,
                UPDATE_TIME,
                RESOURCE_FROM
            ) VALUES (
                ROUND(DBMS_RANDOM.VALUE(2, V_MEMBER_COUNT)), -- API 입력 계정
                'test' || i,
                'test' || i,
                'test' || i,
                100,
                'test.jpg',
                SYSDATE,
                SYSDATE,
                1 -- API: 1, 직접: 2
            );
            V_INPUT_COUNT := V_INPUT_COUNT + 1;
    END LOOP;

    -- 데이터 적재 수 업데이트
    UPDATE DATA_SETTING
    SET BOOK_COUNT = V_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;

SELECT * FROM MEMBER;

SELECT *
FROM DATA_SETTING;

-- ============================================ --
-- 도서 좋아요 데이터 추가
-- ============================================ --
DECLARE
    V_BOOK_COUNT NUMBER;
    V_MEMBER_COUNT NUMBER;
    V_BOOK_LIKE_COUNT NUMBER;
    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN

    -- 도서 수 조회
    SELECT BOOK_COUNT INTO V_BOOK_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';
    -- 회원 수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';
    -- 도서 좋아요 수 조회
    SELECT BOOK_LIKE_COUNT INTO V_BOOK_LIKE_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    -- 데이터 삽입
    FOR i IN 1..V_BOOK_COUNT LOOP
        FOR j IN 1..V_MEMBER_COUNT LOOP
            -- 도서 좋아요 수 제한
            IF V_BOOK_LIKE_COUNT <= V_INPUT_COUNT THEN
                EXIT;
            END IF;

            -- 도서 좋아요 추가
            INSERT INTO BOOK_LIKE (
                "BOOK_ID",
                "MEMBER_ID",
                "CREATE_TIME",
                "UPDATE_TIME"
            ) VALUES (
                i,
                j,
                SYSDATE,
                SYSDATE
            );
            V_INPUT_COUNT := V_INPUT_COUNT + 1;
        END LOOP;
    END LOOP;

    -- 데이터 적재 수 업데이트
    UPDATE DATA_SETTING
    SET BOOK_LIKE_COUNT = V_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;

