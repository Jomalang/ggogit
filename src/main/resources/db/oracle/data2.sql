-- ============================================ --
-- 테스트 데이터 예시
-- 회원 수 : 10_000명
-- 도서 수 : 100_000권
-- 트리 수 : 100_000 * 100 = 10_000_000개
-- 리프 수 : 100_000_000 * 1_000 = 100_000_000_000개 1000억개
-- ============================================ --

-- ============================================ --
-- 회원 100_000명 추가
-- ============================================ --
DECLARE
    V_MEMBER_COUNT NUMBER := 100000;
BEGIN
    FOR i IN 1..V_MEMBER_COUNT LOOP
        INSERT INTO MEMBER (
              "ID" --NUMBER
            , "PROFILE_ID" -- VARCHAR2(50)
            , "EMAIL" -- VARCHAR2(100)
            , "PASSWORD" -- VARCHAR2(255)
            , "BACKGROUND_IMAGE" -- VARCHAR2(255)
            , "NICKNAME" -- VARCHAR2(50 CHAR)
            , "INTRODUCTION" -- VARCHAR2(1000 CHAR)
            , "JOIN_AGREE" -- NUMBER(1)
            , "UPDATE_TIME" -- TIMESTAMP
            , "CREATE_TIME" -- TIMESTAMP
        ) VALUES (
            i
            , 'profile_id' || i
            , 'email' || i
            , 'password' || i
            , 'background_image' || i
            , 'nickname' || i
            , 'introduction' || i
            , 1
            , SYSTIMESTAMP
            , SYSTIMESTAMP
        );
    END LOOP;
END;
COMMIT;

-- ============================================ --
-- 팔로우 1_000_000개 추가
-- =========================================== --
DECLARE
    V_MEMBER_COUNT NUMBER; -- 회원 수
    V_FOLLOW_COUNT NUMBER := 1000000; -- 팔로우 수
    -- PK 중복 방지 확인 변수
    V_MEMBER_ID NUMBER;
    V_FOLLOW_ID NUMBER;
    -- 중복 확인
    V_DUPLICATE_CHECK NUMBER;
BEGIN
    -- 회원 수 추출
    SELECT COUNT(*) + 1 INTO V_MEMBER_COUNT FROM MEMBER;

    -- 팔로우 추가
    FOR i IN 1..V_FOLLOW_COUNT LOOP

        -- PK 중복 방지
        LOOP
            V_MEMBER_ID := TRUNC(DBMS_RANDOM.VALUE(1, V_MEMBER_COUNT));
            V_FOLLOW_ID := TRUNC(DBMS_RANDOM.VALUE(1, V_MEMBER_COUNT));

            -- 중복 확인
            SELECT COUNT(*)
            INTO V_DUPLICATE_CHECK
            FROM FOLLOW
            WHERE MEMBER_ID = V_MEMBER_ID AND FOLLOW_ID = V_FOLLOW_ID;

            IF V_MEMBER_ID != V_FOLLOW_ID -- 자기 자신 팔로우 방지
                   AND V_DUPLICATE_CHECK = 0 THEN -- 중복 방지
                EXIT;
            END IF;
        END LOOP;

        -- 데이터 삽입
        INSERT INTO FOLLOW (
              "MEMBER_ID" -- NUMBER
            , "FOLLOW_ID" -- NUMBER
            , "UPDATE_TIME" -- TIMESTAMP
            , "CREATE_TIME" -- TIMESTAMP
        ) VALUES (
              V_MEMBER_ID -- 팔로워 랜덤
            , V_FOLLOW_ID -- 팔로잉 랜덤
            , SYSTIMESTAMP
            , SYSTIMESTAMP
        );
    END LOOP;
END;
COMMIT;

-- ============================================ --
-- 도서 : 100_000권
-- ============================================ --

DECLARE
    V_BOOK_COUNT NUMBER := 100000;
    V_TOTAL_PAGE NUMBER := 1000;
    V_MEMBER_COUNT NUMBER; -- 회원 수
BEGIN

    -- 회원 수 추출
    SELECT COUNT(*) INTO V_MEMBER_COUNT FROM MEMBER;

    -- 도서 추가
    FOR i IN 1..V_BOOK_COUNT LOOP
        INSERT INTO BOOK (
              "ID" -- NUMBER
            , "MEMBER_ID" -- NUMBER
            , "TITLE" -- VARCHAR2(100)
            , "AUTHOR" -- VARCHAR2(100)
            , "PUBLISHER" -- VARCHAR2(100)
            , "RESOURCE_FROM" -- NUMBER(1)
            , "TOTAL_PAGE" -- NUMBER
            , "IMAGE_FILE" -- VARCHAR2(255)
            , "UPDATE_TIME" -- TIMESTAMP
            , "CREATE_TIME" -- TIMESTAMP
        ) VALUES (
                i
                , TRUNC(DBMS_RANDOM.VALUE(1, V_MEMBER_COUNT))
                , 'name' || i
                , 'author' || i
                , 'publisher' || i
                , TRUNC(DBMS_RANDOM.VALUE(0, 2))
                , TRUNC(DBMS_RANDOM.VALUE(1, V_TOTAL_PAGE))
                , 'image' || i
                , SYSTIMESTAMP
                , SYSTIMESTAMP
        );
    END LOOP;
END;
COMMIT;

-- ============================================ --
-- 도서 좋아요 : 1_000_000개
-- ============================================ --
DECLARE
    V_BOOK_LIKE_COUNT NUMBER := 1000000;
    V_MEMBER_COUNT NUMBER; -- 회원 수
    V_BOOK_COUNT NUMBER; -- 도서 수
    -- PK 중복 방지 확인 변수
    V_MEMBER_ID NUMBER;
    V_BOOK_ID NUMBER;
    -- 중복 확인
    V_DUPLICATE_CHECK NUMBER;
BEGIN

    -- 회원 수 추출
    SELECT COUNT(*) INTO V_MEMBER_COUNT FROM MEMBER;
    SELECT COUNT(*) INTO V_BOOK_COUNT FROM BOOK;

    -- 도서 좋아요 중복 방지
    FOR i IN 1..V_BOOK_LIKE_COUNT LOOP

        -- PK 중복 방지
        LOOP
            V_MEMBER_ID := TRUNC(DBMS_RANDOM.VALUE(1, V_MEMBER_COUNT));
            V_BOOK_ID := TRUNC(DBMS_RANDOM.VALUE(1, V_BOOK_COUNT));

            -- 중복 확인
            SELECT COUNT(*)
            INTO V_DUPLICATE_CHECK
            FROM BOOK_LIKE
            WHERE MEMBER_ID = V_MEMBER_ID AND BOOK_ID = V_BOOK_ID;

            IF V_DUPLICATE_CHECK = 0 THEN -- 중복 방지
                EXIT;
            END IF;
        END LOOP;

        -- 데이터 삽입
        INSERT INTO BOOK_LIKE (
              "MEMBER_ID" -- NUMBER
            , "BOOK_ID" -- NUMBER
            , "ACTIVE" -- NUMBER(1)
            , "UPDATE_TIME" -- TIMESTAMP
            , "CREATE_TIME" -- TIMESTAMP
        ) VALUES (
              V_MEMBER_ID -- 회원 아이디
            , V_BOOK_ID -- 도서 아이디
            , TRUNC(DBMS_RANDOM.VALUE(0, 2))
            , SYSTIMESTAMP
            , SYSTIMESTAMP
        );
    END LOOP;
END;
COMMIT;

-- ============================================ --
-- 씨앗 테이블
-- ============================================ --
INSERT INTO "SEED" ("ID" , "NAME") VALUES (1, 'BOOK');
INSERT INTO "SEED" ("ID" , "NAME") VALUES (2, 'IDEA');
INSERT INTO "SEED" ("ID" , "NAME") VALUES (3, 'SENTENCE');
INSERT INTO "SEED" ("ID" , "NAME") VALUES (4, 'STUDY');
COMMIT;

-- ============================================ --
-- 트리 데이터 : 10_000_000개
-- ============================================ --
DECLARE
    V_TREE_COUNT NUMBER := 10000000;
    V_BOOK_MARK_COUNT NUMBER := 5; -- 북마크 수 5개로 지정
    V_MEMBER_COUNT NUMBER; -- 회원 수
    V_SEED_COUNT NUMBER; -- 씨앗 수
BEGIN
    -- 회원 수 추출
    SELECT COUNT(*) + 1 INTO V_MEMBER_COUNT FROM MEMBER;
    SELECT COUNT(*) + 1 INTO V_SEED_COUNT FROM SEED;

    FOR i IN 1..V_TREE_COUNT LOOP
        INSERT INTO TREE (
              "ID" -- NUMBER
            , "MEMBER_ID" -- NUMBER
            , "SEED_ID" -- NUMBER
            , "NAME" -- VARCHAR2(100)
            , "TITLE" -- VARCHAR2(100)
            , "DISCRIPTION" -- VARCHAR2(1000)
            , "BOOK_MARK_COUNT" -- NUMBER
            , VISIBILITY -- NUMBER(1)
            , "UPDATE_TIME" -- TIMESTAMP
            , "CREATE_TIME" -- TIMESTAMP
        ) VALUES (
              i
            , TRUNC(DBMS_RANDOM.VALUE(1, V_MEMBER_COUNT))
            , TRUNC(DBMS_RANDOM.VALUE(1, V_SEED_COUNT))
            , 'name' || i
            , 'title' || i
            , 'discription' || i
            , TRUNC(DBMS_RANDOM.VALUE(0, V_BOOK_MARK_COUNT))
            , TRUNC(DBMS_RANDOM.VALUE(0, 2))
            , SYSTIMESTAMP
            , SYSTIMESTAMP
        );
    END LOOP;
END;
COMMIT;

-- ============================================ --
-- 트리 이미지 데이터 : 100_000개
-- ============================================ --
DECLARE
    V_TREE_IMAGE_COUNT NUMBER := 100000; -- 트리 이미지 수 100,000개로 지정
    V_MEMBER_COUNT NUMBER; -- 회원 수
    V_TREE_COUNT NUMBER; -- 트리 수

    -- PK 중복 방지 확인 변수
    V_TREE_ID NUMBER;
    -- 중복 확인
    V_DUPLICATE_CHECK NUMBER;
    -- 책 도서 아닌 경우
    V_NOT_BOOK_TREE NUMBER := 0;
BEGIN

    SELECT COUNT(*) + 1 INTO V_MEMBER_COUNT FROM MEMBER; -- 회원수 추출
    SELECT COUNT(*) + 1 INTO V_TREE_COUNT FROM TREE; -- 트리수 추출

    -- 트리 이미지 추가
    FOR i IN 1..V_TREE_IMAGE_COUNT LOOP
        -- PK 중복 방지
        LOOP
            V_TREE_ID := TRUNC(DBMS_RANDOM.VALUE(1, V_TREE_COUNT));

            -- 중복 확인
            SELECT COUNT(*)
            INTO V_DUPLICATE_CHECK
            FROM TREE_IMAGE
            WHERE TREE_ID = V_TREE_ID;

            -- 책 트리 아닌 경우
            SELECT COUNT(*)
            INTO V_NOT_BOOK_TREE
            FROM TREE
            WHERE ID = V_TREE_ID AND SEED_ID != 1;

            IF V_DUPLICATE_CHECK = 0
                AND V_NOT_BOOK_TREE = 0 THEN -- 중복 방지
                EXIT;
            END IF;
        END LOOP;

        INSERT INTO TREE_IMAGE (
              "TREE_ID" -- NUMBER
            , "IMAGE" -- VARCHAR2(255)
        ) VALUES (
               V_TREE_ID
            , 'image' || i
        );
    END LOOP;
END;
COMMIT;

-- ============================================ --
-- 트리 도서 정보 : 100_000개
-- ============================================ --
DECLARE
    V_TREE_BOOK_COUNT NUMBER := 100000; -- 트리 도서 정보 100,000개로 지정

    -- 모든 페이지
    V_TOTAL_PAGE NUMBER;
    -- 읽은 페이지
    V_READED_PAGE NUMBER;
    -- 진행률
    V_PROGRESS NUMBER;
BEGIN

    FOR i IN 1..V_TREE_BOOK_COUNT LOOP

        V_TOTAL_PAGE := TRUNC(DBMS_RANDOM.VALUE(1, 1000)); -- 총페이지
        V_READED_PAGE := TRUNC(DBMS_RANDOM.VALUE(1, V_TOTAL_PAGE)); -- 읽은페이지
        V_PROGRESS := TRUNC((V_READED_PAGE / V_TOTAL_PAGE) * 100); -- 진행률

        INSERT INTO TREE_BOOK (
                  "ID"
                , "TOTAL_PAGE"
                , "READED_PAGE"
                , "PROGRESS"
            ) VALUES (
                i
                , V_TOTAL_PAGE
                , V_READED_PAGE
                , V_PROGRESS
            );
    END LOOP;
END;
COMMIT;

-- ============================================ --
-- 트리 좋아요 : 1_000_000개
-- ============================================ --
DECLARE
    V_TREE_LIKE_COUNT NUMBER := 1000000;
    V_MEMBER_COUNT NUMBER; -- 회원 수
    V_TREE_COUNT NUMBER; -- 트리 수
    -- PK 중복 방지 확인 변수
    V_MEMBER_ID NUMBER;
    V_TREE_ID NUMBER;
    -- 중복 확인
    V_DUPLICATE_CHECK NUMBER;
BEGIN

    FOR i IN 1..V_TREE_LIKE_COUNT LOOP

        -- 회원 수 추출
        SELECT COUNT(*) + 1 INTO V_MEMBER_COUNT FROM MEMBER;
        SELECT COUNT(*) + 1 INTO V_TREE_COUNT FROM TREE;

        -- PK 중복 방지
        LOOP
            V_MEMBER_ID := TRUNC(DBMS_RANDOM.VALUE(1, V_MEMBER_COUNT));
            V_TREE_ID := TRUNC(DBMS_RANDOM.VALUE(1, V_TREE_COUNT));

            -- 중복 확인
            SELECT COUNT(*)
            INTO V_DUPLICATE_CHECK
            FROM TREE_LIKE
            WHERE MEMBER_ID = V_MEMBER_ID AND TREE_ID = V_TREE_ID;

            IF V_DUPLICATE_CHECK = 0 THEN -- 중복 방지
                EXIT;
            END IF;
        END LOOP;

        -- 데이터 삽입
        INSERT INTO TREE_LIKE (
              "MEMBER_ID" -- NUMBER
            , "TREE_ID" -- NUMBER
            , "ACTIVE" -- NUMBER(1)
            , "UPDATE_TIME" -- TIMESTAMP
            , "CREATE_TIME" -- TIMESTAMP
        ) VALUES (
              V_MEMBER_ID -- 회원 아이디
            , V_TREE_ID -- 트리 아이디
            , TRUNC(DBMS_RANDOM.VALUE(0, 2))
            , SYSTIMESTAMP
            , SYSTIMESTAMP
        );
    END LOOP;
END;
COMMIT;

-- ============================================ --
-- 회고록 : 100_000개
-- ============================================ --
DECLARE
    V_MEMOIR_COUNT NUMBER := 100000;
    V_TREE_ID NUMBER; -- 트리 아이디
    V_TREE_COUNT NUMBER; -- 트리 수
    V_MEMBER_COUNT NUMBER; -- 회원 수
    V_DUPLICATE_CHECK NUMBER; -- PK 중복 방지 확인 변수
BEGIN

    SELECT COUNT(*) + 1 INTO V_TREE_COUNT FROM TREE;
    SELECT COUNT(*) + 1 INTO V_MEMBER_COUNT FROM MEMBER;

    -- 회고록 추가
    FOR i IN 1..V_MEMOIR_COUNT LOOP

        LOOP
            -- PK 중복 방지
            SELECT COUNT(*)
            INTO V_DUPLICATE_CHECK
            FROM MEMOIR
            WHERE ID = i;

            V_TREE_ID := TRUNC(DBMS_RANDOM.VALUE(1, V_TREE_COUNT));

            IF V_DUPLICATE_CHECK = 0 THEN -- 중복 방지
                EXIT;
            END IF;
        END LOOP;

        INSERT INTO MEMOIR (
              "ID" -- NUMBER
            , "TREE_ID" -- NUMBER
            , "TITLE" -- VARCHAR2(100)
            , "TEXT" -- VARCHAR2(1000)
            , "VISIBILITY" -- NUMBER(1)
            , "UPDATE_TIME" -- TIMESTAMP
            , "CREATE_TIME" -- TIMESTAMP
        ) VALUES (
              i
            , V_TREE_ID
            , 'title' || i
            , 'text' || i
            , TRUNC(DBMS_RANDOM.VALUE(0, 2))
            , SYSTIMESTAMP
            , SYSTIMESTAMP
        );
    END LOOP;
END;
COMMIT;

-- ============================================ --
-- 회고록 좋아요 : 1_000_000개
-- ============================================ --
DECLARE

BEGIN

END;

