SELECT * FROM LEAF;
SELECT * FROM TREE; -- ID : 182

-- ==================================================== --
-- insert()
-- ==================================================== --
INSERT INTO LEAF (ID, TREE_ID, PARENT_LEAF_ID, VISIBILITY, VIEW_COUNT, LIKE_COUNT, TITLE, CONTENT, CHILD_LEAF_COUNT, BOOK_MARK)
VALUES (1, 182, NULL, 1, 0, 0, 'title', 'content', 0, 0);
INSERT INTO LEAF (ID, TREE_ID, PARENT_LEAF_ID, VISIBILITY, VIEW_COUNT, LIKE_COUNT, TITLE, CONTENT, CHILD_LEAF_COUNT, BOOK_MARK)
VALUES (2, 182, 1, 1, 0, 0, 'title', 'content', 0, 0);
INSERT INTO LEAF (TREE_ID, PARENT_LEAF_ID, VISIBILITY, VIEW_COUNT, LIKE_COUNT, TITLE, CONTENT, CHILD_LEAF_COUNT, BOOK_MARK)
VALUES (182, 2, 1, 0, 0, 'title', 'content', 0, 0);

COMMIT;

-- ==================================================== --
-- update()
-- ==================================================== --
UPDATE LEAF
SET
    TITLE = 'update title',
    CONTENT = 'update content',
    VISIBILITY = 0,
    VIEW_COUNT = 1,
    LIKE_COUNT = 1,
    CHILD_LEAF_COUNT = 1,
    BOOK_MARK = 1
WHERE ID = 1;
COMMIT;

-- ==================================================== --
-- delete()
-- ==================================================== --
DELETE FROM LEAF
WHERE ID = 1;

-- ==================================================== --
-- existsById()
-- ==================================================== --
SELECT COUNT(*) FROM LEAF
WHERE ID = 1;

-- ==================================================== --
-- findById()
-- ==================================================== --
SELECT * FROM LEAF
WHERE ID = 1;

-- ==================================================== --
-- findByLeafId()
-- ==================================================== --
SELECT * FROM LEAF
WHERE ID = 1;

-- ==================================================== --
-- findAll()
-- ==================================================== --
SELECT * FROM LEAF;

-- ==================================================== --
-- findLeafCardViewByBookId()
-- ==================================================== --
SELECT
    L2.TREE_ID,
    L2.LEAF_ID,
    L2.TITLE,
    L2.CONTENT,
    L2.VIEW_COUNT,
    M2.USER_NICKNAME,
    M2.USER_EMAIL_ID,
    NVL(LK2.LIKE_ACTIVATE, 0) AS LIKE_ACTIVATE,
    L2.UPDATE_TIME
FROM (
    -- 리프 내용
    SELECT L.ID AS LEAF_ID,
           L.TREE_ID,
           L.TITLE,
           L.CONTENT,
           L.VIEW_COUNT,
           L.UPDATE_TIME
    FROM LEAF L
    WHERE TREE_ID IN (
        -- 도서 트리 만 필터링
        SELECT T1.ID
        FROM TREE T1
        WHERE BOOK_ID = 18
    )
) L2
LEFT JOIN (
    -- 트리에서 회원정보 추출
    SELECT T.ID AS TREE_ID,
           T.MEMBER_ID
    FROM TREE T
) T2
ON T2.TREE_ID = L2.TREE_ID
LEFT JOIN (
    -- 회원정보
    SELECT M.ID AS MEMBER_ID,
           M.NICKNAME AS USER_NICKNAME,
           SUBSTR(M.EMAIL, 1, INSTR(EMAIL, '@') - 1) AS USER_EMAIL_ID
    FROM MEMBER M
) M2
ON T2.MEMBER_ID = M2.MEMBER_ID
LEFT JOIN (
    -- 좋아요 여부
    SELECT  LK.LEAF_ID,
            LK.ACTIVATE AS LIKE_ACTIVATE
    FROM LEAF_LIKE LK
    WHERE MEMBER_ID = 14
) LK2
ON L2.LEAF_ID = LK2.LEAF_ID;