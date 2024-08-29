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
             4, -- 씨앗 수 (고정)
             -- 연관
             100, -- 트리 수
             100, -- 책 트리 추가 정보 (트리수 미만으로 랜덤으로 설정)
             100, -- 트리 이미지 수 (트리수 미만으로 랜덤으로 설정)
             -- 연관
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
                "ID",
                "EMAIL",
                "PASSWORD",
                "NICKNAME",
                "INTRODUCTION",
                "UPDATE_TIME",
                "CREATE_TIME"
            ) VALUES (
                i,
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
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 회원 배경 이미지 수 조회
    SELECT MEMBER_BACKGROUND_IMAGE_COUNT INTO V_MEMBER_BACKGROUND_IMAGE_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    -- 데이터 삽입
    FOR i IN 1..V_MEMBER_COUNT LOOP

        -- 회원 배경 이미지 개수 제한
        IF V_MEMBER_BACKGROUND_IMAGE_COUNT <= V_INPUT_COUNT THEN
            EXIT;
        END IF;

        -- 회원 배경 이미지 추가
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
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 팔로우 수 조회
    SELECT FOLLOW_COUNT INTO V_FOLLOW_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

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
                    "UPDATE_TIME",
                    "CREATE_TIME"
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
-- 데이터 입려
-- 절반 도서는 API로 입력, 나머지 절반은 직접 입력
-- ============================================ --
DECLARE
    V_BOOK_COUNT NUMBER;
    V_MEMBER_COUNT NUMBER;
    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN

    -- 회원 수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';

    -- 도서 수 조회
    SELECT BOOK_COUNT INTO V_BOOK_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    -- API 데이터 삽입
    FOR i IN 1..FLOOR(V_BOOK_COUNT / 2) LOOP
            INSERT INTO BOOK (
                ID,
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
                i,
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
    FOR i IN CEIL(V_BOOK_COUNT / 2) + 1..V_BOOK_COUNT LOOP
            INSERT INTO BOOK (
                ID,
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
                i,
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
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 회원 수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
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
                "ACTIVATE",
                "UPDATE_TIME",
                "CREATE_TIME"
            ) VALUES (
                i,
                j,
                1,
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

-- ============================================ --
-- 도서 댓글 데이터 추가
-- ============================================ --
DECLARE
    V_BOOK_COUNT NUMBER;
    V_MEMBER_COUNT NUMBER;
    V_BOOK_COMMENT_COUNT NUMBER;
    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN

    -- 도서 수 조회
    SELECT BOOK_COUNT INTO V_BOOK_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 회원 수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 도서 댓글 수 조회
    SELECT BOOK_COMMENT_COUNT INTO V_BOOK_COMMENT_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    -- 데이터 삽입
    FOR i IN 1..V_BOOK_COUNT LOOP
        FOR j IN 1..V_MEMBER_COUNT LOOP

            -- 도서 댓글 수 제한
            IF V_BOOK_COMMENT_COUNT <= V_INPUT_COUNT THEN
                EXIT;
            END IF;

            -- 도서 댓글 추가
            INSERT INTO BOOK_COMMENT (
                "ID",
                "MEMBER_ID",
                "BOOK_ID",
                "LIKE_COUNT",
                "COMMENT",
                "UPDATE_TIME",
                "CREATE_TIME"
            ) VALUES (
                V_INPUT_COUNT + 1,
                j,
                i,
                0,
                'test' || V_INPUT_COUNT,
                SYSDATE,
                SYSDATE
            );
            V_INPUT_COUNT := V_INPUT_COUNT + 1;
        END LOOP;
    END LOOP;

    -- 데이터 적재 수 업데이트
    UPDATE DATA_SETTING
    SET BOOK_COMMENT_COUNT = V_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;

-- ============================================ --
-- 도서 댓글 좋아요 데이터 추가
-- 데이터 수 : N(도서 댓글 * 회원 수)
-- ============================================ --
DECLARE
    V_MEMBER_COUNT NUMBER;
    V_BOOK_COMMENT_COUNT NUMBER;
    V_BOOK_COMMENT_LIKE_COUNT NUMBER;
    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN
    -- 회원 수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 도서 댓글 수 조회
    SELECT BOOK_COMMENT_COUNT INTO V_BOOK_COMMENT_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 도서 댓글 좋아요 수 조회
    SELECT BOOK_COMMENT_LIKE_COUNT INTO V_BOOK_COMMENT_LIKE_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    -- 데이터 삽입
    FOR i IN 1..V_BOOK_COMMENT_COUNT LOOP
        FOR j IN 1..V_MEMBER_COUNT LOOP

            -- 도서 댓글 좋아요 수 제한
            IF V_BOOK_COMMENT_LIKE_COUNT <= V_INPUT_COUNT THEN
                EXIT;
            END IF;

            -- 도서 댓글 좋아요 추가
            INSERT INTO BOOK_COMMENT_LIKE (
                "MEMBER_ID",
                "BOOK_COMMENT_ID",
                "ACTIVATE",
                "UPDATE_TIME",
                "CREATE_TIME"
            ) VALUES (
                j,
                i,
                1,
                SYSDATE,
                SYSDATE
            );

            UPDATE BOOK_COMMENT
            SET LIKE_COUNT = LIKE_COUNT + 1
            WHERE ID = i;

            V_INPUT_COUNT := V_INPUT_COUNT + 1;
        END LOOP;
    END LOOP;

    -- 데이터 적재 수 업데이트
    UPDATE DATA_SETTING
    SET BOOK_COMMENT_LIKE_COUNT = V_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;

-- ============================================ --
-- 씨앗 데이터 추가
-- ============================================ --
INSERT INTO "SEED" ("ID", "NAME", "DESCRIPTION") VALUES (1, 'BOOK', '도서'); -- 도서 값은 1이여야함
INSERT INTO "SEED" ("ID", "NAME", "DESCRIPTION") VALUES (2, 'IDEA', '생각');
INSERT INTO "SEED" ("ID", "NAME", "DESCRIPTION") VALUES (3, 'SENTENCE', '문장');
INSERT INTO "SEED" ("ID", "NAME", "DESCRIPTION") VALUES (4, 'VIDEO', '영상');

UPDATE DATA_SETTING
SET SEED_COUNT = 4
WHERE DATA_TYPE = 'RESULT';

-- ============================================ --
-- 트리 데이터 추가
-- ============================================ --
DECLARE
    V_SEED_COUNT NUMBER;
    V_TREE_COUNT NUMBER;
    V_BOOK_COUNT NUMBER;
    V_MEMBER_COUNT NUMBER;

    -- TMP 데이터
    V_SEED_ID NUMBER;
    V_BOOK_ID NUMBER;
    V_BOOK_RESOURCE_FROM NUMBER;
    V_MEMBER_ID NUMBER;
    V_TOTAL_PAGE NUMBER;
    V_READING_PAGE NUMBER;

    -- 데이터 적재 수 확인
    V_TREE_INPUT_COUNT NUMBER := 0;
    V_TREE_BOOK_INPUT_COUNT NUMBER := 0;
    V_TREE_IMAGE_INPUT_COUNT NUMBER := 0;
    V_LEAF_INPUT_COUNT NUMBER := 0;
    V_LEAF_BOOK_INPUT_COUNT NUMBER := 0;
BEGIN

    -- 회원 수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 씨앗 수 조회
    SELECT SEED_COUNT INTO V_SEED_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 도서 수 조회
    SELECT BOOK_COUNT INTO V_BOOK_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 트리 수 조회
    SELECT TREE_COUNT INTO V_TREE_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';
    -- 트리 책 추가 정보 수 조회

    FOR i IN 1..V_TREE_COUNT LOOP
        -- 씨앗 선택
        V_SEED_ID := ROUND(DBMS_RANDOM.VALUE(1, V_SEED_COUNT));
        -- 회원 선택
        V_MEMBER_ID := ROUND(DBMS_RANDOM.VALUE(1, V_MEMBER_COUNT));

        -- 도서 트리 아님 데이터 추가
        IF V_SEED_ID != 1 THEN
            INSERT INTO TREE (
                "ID",
                "MEMBER_ID",
                "SEED_ID",
                "TITLE",
                "DESCRIPTION",
                "UPDATE_TIME",
                "CREATE_TIME"
            ) VALUES (
                i,
                V_MEMBER_ID,
                V_SEED_ID,
                'test' || i,
                'test' || i,
                SYSDATE,
                SYSDATE
            );
            V_TREE_INPUT_COUNT := V_TREE_INPUT_COUNT + 1;

            -- 도서 트리 아님 데이터 트리 이미지 추가
            INSERT INTO TREE_IMAGE (
                "ID",
                "NAME"
            ) VALUES (
                i,
                'test.jpg'
            );
            V_TREE_IMAGE_INPUT_COUNT := V_TREE_IMAGE_INPUT_COUNT + 1;

            -- 최초 리프 데이터 생성
            INSERT INTO LEAF (
                "ID",
                "TREE_ID",
                "PARENT_LEAF_ID",
                "VISIBILITY",
                "VIEW_COUNT",
                "LIKE_COUNT",
                "TITLE",
                "TEXT",
                "CHILD_LEAF_COUNT",
                "BOOK_MARK_COUNT",
                "UPDATE_TIME",
                "CREATE_TIME"
            ) VALUES (
                         V_LEAF_INPUT_COUNT + 1,
                         i,
                         NULL,
                         1,
                         0,
                         0,
                         'test' || i,
                         'test' || i,
                         0,
                         0,
                         SYSDATE,
                         SYSDATE
                     );
            V_LEAF_INPUT_COUNT := V_LEAF_INPUT_COUNT + 1;
            CONTINUE;
        END IF;

        -- 도서 추출
        V_BOOK_ID := ROUND(DBMS_RANDOM.VALUE(1, V_BOOK_COUNT));
        -- 도서 타입 추출 (직접 입력, API 입력)
        SELECT RESOURCE_FROM INTO V_BOOK_RESOURCE_FROM
        FROM BOOK
        WHERE ID = V_BOOK_ID;

        -- 도서 트리 데이터 추가
        INSERT INTO TREE (
            "ID",
            "BOOK_ID",
            "MEMBER_ID",
            "SEED_ID",
            "TITLE",
            "DESCRIPTION",
            "BOOK_MARK_COUNT",
            "VISIBILITY",
            "UPDATE_TIME",
            "CREATE_TIME"
        ) VALUES (
            i,
            V_BOOK_ID,
            V_MEMBER_ID,
            V_SEED_ID,
            'test' || i,
            'test' || i,
            0,
            1,
            SYSDATE,
            SYSDATE
        );
        V_TREE_INPUT_COUNT := V_TREE_INPUT_COUNT + 1;

        -- 트리 도서 정보
        V_TOTAL_PAGE := ROUND(DBMS_RANDOM.VALUE(100, 1000));
        V_READING_PAGE := ROUND(DBMS_RANDOM.VALUE(0, V_TOTAL_PAGE));

        INSERT INTO TREE_BOOK (
            "ID",
            "TOTAL_PAGE",
            "READING_PAGE",
            "PROGRESS"
        ) VALUES (
            i,
            V_TOTAL_PAGE,
            V_READING_PAGE,
            ROUND((V_READING_PAGE / V_TOTAL_PAGE) * 100)
        );
        V_TREE_BOOK_INPUT_COUNT := V_TREE_BOOK_INPUT_COUNT + 1;

        -- 최초 리프 데이터 생성
        V_LEAF_INPUT_COUNT := V_LEAF_INPUT_COUNT + 1;
        INSERT INTO LEAF (
            "ID",
            "TREE_ID",
            "PARENT_LEAF_ID",
            "VISIBILITY",
            "VIEW_COUNT",
            "LIKE_COUNT",
            "TITLE",
            "TEXT",
            "CHILD_LEAF_COUNT",
            "BOOK_MARK_COUNT",
            "UPDATE_TIME",
            "CREATE_TIME"
        ) VALUES (
            V_LEAF_INPUT_COUNT,
            i,
            NULL,
            1,
            0,
            0,
            'test' || i,
            'test' || i,
            0,
            0,
            SYSDATE,
            SYSDATE
        );

        INSERT INTO LEAF_BOOK (
            "LEAF_ID",
            "START_PAGE",
            "END_PAGE"
        ) VALUES (
            V_LEAF_INPUT_COUNT,
            1,
            V_READING_PAGE
        );
        V_LEAF_BOOK_INPUT_COUNT := V_LEAF_BOOK_INPUT_COUNT + 1;
    END LOOP;

    UPDATE DATA_SETTING
    SET TREE_COUNT = V_TREE_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    UPDATE DATA_SETTING
    SET TREE_BOOK_COUNT = V_TREE_BOOK_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    UPDATE DATA_SETTING
    SET TREE_IMAGE_COUNT = V_TREE_IMAGE_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    UPDATE DATA_SETTING
    SET LEAF_COUNT = V_LEAF_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    UPDATE DATA_SETTING
    SET LEAF_BOOK_COUNT = V_LEAF_BOOK_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;

-- ============================================ --
-- 트리 좋아요 데이터 추가
-- ============================================ --
DECLARE
    V_TREE_COUNT NUMBER;
    V_MEMBER_COUNT NUMBER;
    V_TREE_LIKE_COUNT NUMBER;
    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN

    -- 트리 수 조회
    SELECT TREE_COUNT INTO V_TREE_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 회원 수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 트리 좋아요 수 조회
    SELECT TREE_LIKE_COUNT INTO V_TREE_LIKE_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    -- 데이터 삽입
    FOR i IN 1..V_TREE_COUNT LOOP
        FOR j IN 1..V_MEMBER_COUNT LOOP

            -- 트리 좋아요 수 제한
            IF V_TREE_LIKE_COUNT <= V_INPUT_COUNT THEN
                EXIT;
            END IF;

            -- 트리 좋아요 추가
            INSERT INTO TREE_LIKE (
                "TREE_ID",
                "MEMBER_ID",
                "ACTIVATE",
                "UPDATE_TIME",
                "CREATE_TIME"
            ) VALUES (
                i,
                j,
                1,
                SYSDATE,
                SYSDATE
            );

            V_INPUT_COUNT := V_INPUT_COUNT + 1;
        END LOOP;
    END LOOP;

    UPDATE DATA_SETTING
    SET TREE_LIKE_COUNT = V_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;

-- ============================================ --
-- 회고록 데이터 추가
-- ============================================ --
DECLARE
    V_MEMOIR_COUNT NUMBER;
    V_MEMBER_COUNT NUMBER;
    V_TREE_COUNT NUMBER;
    -- 도서 트리 ID
    V_TREE_ID NUMBER;
    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN

    -- 회원 수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 트리 수 조회
    SELECT TREE_COUNT INTO V_TREE_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 회고록 수 조회
    SELECT MEMOIR_COUNT INTO V_MEMOIR_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    -- 도서 트리 ID 1개 값 랜덤 추출
    SELECT ID INTO V_TREE_ID
    FROM TREE
    ORDER BY DBMS_RANDOM.VALUE
    FETCH FIRST 1 ROWS ONLY;

    -- 데이터 삽입
    FOR i IN 1..V_MEMOIR_COUNT LOOP
        INSERT INTO MEMOIR (
            "ID",
            "TREE_ID",
            "TITLE",
            "TEXT",
            "VISIBILITY",
            "UPDATE_TIME",
            "CREATE_TIME"
        ) VALUES (
            i,
            V_TREE_ID,
            'test' || i,
            'test' || i,
            1,
            SYSDATE,
            SYSDATE
        );
        V_INPUT_COUNT := V_INPUT_COUNT + 1;
    END LOOP;

    -- 데이터 적재 수 업데이트
    UPDATE DATA_SETTING
    SET MEMOIR_COUNT = V_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;

-- ============================================ --
-- 회고록 좋아요 데이터 추가
-- ============================================ --
DECLARE
    V_MEMOIR_COUNT NUMBER;
    V_MEMBER_COUNT NUMBER;
    V_MEMOIR_LIKE_COUNT NUMBER;
    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN

    -- 회원 수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 회고록 수 조회
    SELECT MEMOIR_COUNT INTO V_MEMOIR_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 회고록 좋아요 수 조회
    SELECT MEMOIR_LIKE_COUNT INTO V_MEMOIR_LIKE_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    -- 데이터 삽입
    FOR i IN 1..V_MEMOIR_COUNT LOOP
        FOR j IN 1..V_MEMBER_COUNT LOOP

            -- 회고록 좋아요 수 제한
            IF V_MEMOIR_LIKE_COUNT <= V_INPUT_COUNT THEN
                EXIT;
            END IF;

            -- 회고록 좋아요 추가
            INSERT INTO MEMOIR_LIKE (
                "MEMOIR_ID",
                "MEMBER_ID",
                "ACTIVATE",
                "UPDATE_TIME",
                "CREATE_TIME"
            ) VALUES (
                i,
                j,
                1,
                SYSDATE,
                SYSDATE
            );

            V_INPUT_COUNT := V_INPUT_COUNT + 1;
        END LOOP;
    END LOOP;

    UPDATE DATA_SETTING
    SET MEMOIR_LIKE_COUNT = V_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;

-- ============================================ --
-- 회고록 댓글 데이터 추가
-- ============================================ --
DECLARE
    V_MEMOIR_COUNT NUMBER;
    V_MEMBER_COUNT NUMBER;
    V_MEMOIR_COMMENT_COUNT NUMBER;
    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN

    -- 회원 수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 회고록 수 조회
    SELECT MEMOIR_COUNT INTO V_MEMOIR_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 회고록 댓글 수 조회
    SELECT MEMOIR_COMMENT_COUNT INTO V_MEMOIR_COMMENT_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    -- 데이터 삽입
    FOR i IN 1..V_MEMOIR_COUNT LOOP
        FOR j IN 1..V_MEMBER_COUNT LOOP

            -- 회고록 댓글 수 제한
            IF V_MEMOIR_COMMENT_COUNT <= V_INPUT_COUNT THEN
                EXIT;
            END IF;

            -- 회고록 댓글 추가
            INSERT INTO MEMOIR_COMMENT (
                "ID",
                "MEMBER_ID",
                "MEMOIR_ID",
                "LIKE_COUNT",
                "COMMENT",
                "UPDATE_TIME",
                "CREATE_TIME"
            ) VALUES (
                V_INPUT_COUNT + 1,
                j,
                i,
                0,
                'test' || V_INPUT_COUNT,
                SYSDATE,
                SYSDATE
            );
            V_INPUT_COUNT := V_INPUT_COUNT + 1;
        END LOOP;
    END LOOP;

    -- 데이터 적재 수 업데이트
    UPDATE DATA_SETTING
    SET MEMOIR_COMMENT_COUNT = V_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;

-- ============================================ --
-- 회고록 댓글 좋아요 데이터 추가
-- ============================================ --
DECLARE
    V_MEMBER_COUNT NUMBER;
    V_MEMOIR_COMMENT_COUNT NUMBER;
    V_MEMOIR_COMMENT_LIKE_COUNT NUMBER;
    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN
    -- 회원 수 조회
    SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 회고록 댓글 수 조회
    SELECT MEMOIR_COMMENT_COUNT INTO V_MEMOIR_COMMENT_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';
    -- 회고록 댓글 좋아요 수 조회
    SELECT MEMOIR_COMMENT_LIKE_COUNT INTO V_MEMOIR_COMMENT_LIKE_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    -- 데이터 삽입
    FOR i IN 1..V_MEMOIR_COMMENT_COUNT LOOP
        FOR j IN 1..V_MEMBER_COUNT LOOP

            -- 회고록 댓글 좋아요 수 제한
            IF V_MEMOIR_COMMENT_LIKE_COUNT <= V_INPUT_COUNT THEN
                EXIT;
            END IF;

            -- 회고록 댓글 좋아요 추가
            INSERT INTO MEMOIR_COMMENT_LIKE (
                "MEMBER_ID",
                "MEMOIR_COMMENT_ID",
                "ACTIVATE",
                "UPDATE_TIME",
                "CREATE_TIME"
            ) VALUES (
                j,
                i,
                1,
                SYSDATE,
                SYSDATE
            );

            UPDATE MEMOIR_COMMENT
            SET LIKE_COUNT = LIKE_COUNT + 1
            WHERE ID = i;

            V_INPUT_COUNT := V_INPUT_COUNT + 1;
        END LOOP;
    END LOOP;

    -- 데이터 적재 수 업데이트
    UPDATE DATA_SETTING
    SET MEMOIR_COMMENT_LIKE_COUNT = V_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;

-- ============================================ --
-- 리프 태그 데이터 추가
-- ============================================ --
DECLARE
    V_LEAF_TAG_COUNT NUMBER;
    V_MEMBER_COUNT NUMBER;
    -- 회원 추출
    V_MEMBER_ID NUMBER;
    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN
        -- 회원 수
        SELECT MEMBER_COUNT INTO V_MEMBER_COUNT
        FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';

        -- 리프 태그 수 조회
        SELECT LEAF_TAG_COUNT INTO V_LEAF_TAG_COUNT
        FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

        -- 데이터 삽입
        FOR V_MEMBER_ID IN 1..V_MEMBER_COUNT LOOP
            FOR j IN 1..3 LOOP
                V_INPUT_COUNT := V_INPUT_COUNT + 1;
                INSERT INTO LEAF_TAG (
                    "ID",
                    "MEMBER_ID",
                    "NAME",
                    "UPDATE_TIME",
                    "CREATE_TIME"
                ) VALUES (
                    V_INPUT_COUNT,
                    V_MEMBER_ID,
                    'test' || j,
                    SYSDATE,
                    SYSDATE
                );
            END LOOP;
        END LOOP;

        -- 데이터 적재 수 업데이트
        UPDATE DATA_SETTING
        SET LEAF_TAG_COUNT = V_INPUT_COUNT
        WHERE DATA_TYPE = 'RESULT';

        COMMIT;
END;

-- ============================================ --
-- 리프 데이터 추가
-- ============================================ --
DECLARE
    V_LEAF_COUNT NUMBER;
    V_TREE_COUNT NUMBER;
    -- 트리 씨앗 타입
    V_TREE_ID NUMBER;
    V_SEED_ID NUMBER;
    V_PARENT_LEAF_ID NUMBER;

    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN
    -- 트리 수 조회
    SELECT TREE_COUNT INTO V_TREE_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';

    -- 리프 수 조회
    SELECT LEAF_COUNT INTO V_LEAF_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'INPUT';

    FOR i IN V_TREE_COUNT + 1..V_TREE_COUNT + V_LEAF_COUNT LOOP

        -- 데이터 수 제한
        IF V_LEAF_COUNT <= V_INPUT_COUNT THEN
            EXIT;
        END IF;

        -- 임의의 트리 1개 추출
        SELECT ID INTO V_TREE_ID
        FROM TREE
        ORDER BY DBMS_RANDOM.VALUE
        FETCH FIRST 1 ROWS ONLY;

        -- 임의의 씨앗 타입 추출
        SELECT SEED_ID INTO V_SEED_ID
        FROM TREE
        WHERE ID = V_TREE_ID;

        -- 임의의 리프 1개 추출
        SELECT ID INTO V_PARENT_LEAF_ID
        FROM LEAF
        WHERE TREE_ID = V_TREE_ID
            AND CHILD_LEAF_COUNT < 3 -- (자식이 3개 미만 3진 트리로 만들어짐)
        ORDER BY DBMS_RANDOM.VALUE
        FETCH FIRST 1 ROWS ONLY;

        -- 도서 리프가 아닌 경우 데이터 입력
        IF V_SEED_ID != 1 THEN
            INSERT INTO LEAF (
                "ID",
                "TREE_ID",
                "PARENT_LEAF_ID",
                "VISIBILITY",
                "VIEW_COUNT",
                "LIKE_COUNT",
                "TITLE",
                "TEXT",
                "CHILD_LEAF_COUNT",
                "BOOK_MARK_COUNT",
                "UPDATE_TIME",
                "CREATE_TIME"
            ) VALUES (
                i,
                V_TREE_ID,
                V_PARENT_LEAF_ID,
                1,
                0,
                0,
                'test' || i,
                'test' || i,
                0,
                0,
                SYSDATE,
                SYSDATE
            );
            V_INPUT_COUNT := V_INPUT_COUNT + 1;
            CONTINUE;
        END IF;

        -- 도서 리프인 경우
        INSERT INTO LEAF (
            "ID",
            "TREE_ID",
            "PARENT_LEAF_ID",
            "VISIBILITY",
            "VIEW_COUNT",
            "LIKE_COUNT",
            "TITLE",
            "TEXT",
            "CHILD_LEAF_COUNT",
            "BOOK_MARK_COUNT",
            "UPDATE_TIME",
            "CREATE_TIME"
        ) VALUES (
            i,
            V_TREE_ID,
            V_PARENT_LEAF_ID,
            1,
            0,
            0,
            'test' || i,
            'test' || i,
            0,
            0,
            SYSDATE,
            SYSDATE
        );

        INSERT INTO LEAF_BOOK (
            "LEAF_ID",
            "START_PAGE",
            "END_PAGE"
        ) VALUES (
            i,
            1,
            100
        );
        V_INPUT_COUNT := V_INPUT_COUNT + 1;
    END LOOP;

    UPDATE DATA_SETTING
    SET LEAF_COUNT = LEAF_COUNT + V_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;

-- ============================================ --
-- 리프 태그 연동 데이터 추가
-- ============================================ --
DECLARE
    V_TREE_COUNT NUMBER;
    V_LEAF_COUNT NUMBER;
    -- 탬프 데이터
    V_MEMBER_ID NUMBER;
    -- 데이터 적재 수 확인
    V_INPUT_COUNT NUMBER := 0;
BEGIN

    -- 트리 수 조회
    SELECT TREE_COUNT INTO V_TREE_COUNT
    FROM DATA_SETTING WHERE DATA_TYPE = 'RESULT';

    -- 태그 연동 데이터 삽입
    FOR i IN 1..V_TREE_COUNT LOOP
        -- 회원 아이디 추출
        SELECT MEMBER_ID INTO V_MEMBER_ID
        FROM TREE
        WHERE ID = i;

        SELECT COUNT(*) INTO V_LEAF_COUNT
        FROM LEAF
        WHERE TREE_ID = i;

        -- 트리 리프 태그 연동
        FOR j IN 1..V_LEAF_COUNT LOOP
            FOR k IN 1..3 LOOP
                INSERT INTO LEAF_TAG_MAP (
                    "LEAF_ID",
                    "LEAF_TAG_ID"
                ) VALUES (
                    j,
                    k
                );
                V_INPUT_COUNT := V_INPUT_COUNT + 1;
            END LOOP;
        END LOOP;
    END LOOP;

    UPDATE DATA_SETTING
    SET LEAF_TAG_MAP_COUNT = V_INPUT_COUNT
    WHERE DATA_TYPE = 'RESULT';

    COMMIT;
END;