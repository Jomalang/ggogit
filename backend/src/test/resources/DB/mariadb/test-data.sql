INSERT INTO ggogitdb.MEMBER (ID, EMAIL, PASSWORD, NICKNAME, USERNAME, INTRODUCTION, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (999, 'test', 'test', 'testNickName', 'han', 'hello', 0, '2024-09-30 22:05:59', '2024-09-30 22:06:00');

INSERT INTO ggogitdb.SEED (ID, KOR_NAME, ENG_NAME) VALUES (2, '생각', 'idea');
INSERT INTO ggogitdb.SEED (ID, KOR_NAME, ENG_NAME) VALUES (3, '문장', 'phrase');
INSERT INTO ggogitdb.SEED (ID, KOR_NAME, ENG_NAME) VALUES (4, '공부', 'study');
INSERT INTO ggogitdb.SEED (ID, KOR_NAME, ENG_NAME) VALUES (5, '영상', 'video');
INSERT INTO ggogitdb.SEED (ID, KOR_NAME, ENG_NAME) VALUES (1, '도서', 'book');

INSERT INTO ggogitdb.BOOK_CATEGORY (ID, NAME) VALUES (1, '소설');


INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (1, 999, 'tset1', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (2, 999, 'tset2', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (3, 999, 'tset3', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (4, 999, 'tset4', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (5, 999, 'tset5', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (6, 999, 'tset6', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (7, 999, 'tset7', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (8, 999, 'tset8', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (9, 999, 'tset9', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (10, 999, 'tset10', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (11, 999, 'tset11', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (12, 999, 'tset12', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (13, 999, 'tset13', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (14, 999, 'tset14', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (15, 999, 'tset15', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (16, 999, 'tset16', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (17, 999, 'tset17', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');
INSERT INTO ggogitdb.LEAF_TAG (ID, MEMBER_ID, NAME, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (18, 999, 'tset18', 0, '2024-09-30 22:24:22', '2024-09-30 22:24:22');

select *
from LEAF_TAG;

select *
from LEAF;


SELECT *
FROM LEAF
WHERE ID = 1
AND IS_DELETED = FALSE;

select *
from TREE;

select *
from BOOK;

INSERT INTO ggogitdb.BOOK (ID, MEMBER_ID, BOOK_CATEGORY_ID, TITLE, AUTHOR, TRANSLATOR, ISBN, PUBLISHER, PUBLISH_DATE, TOTAL_PAGE, IMAGE_FILE, IS_DELETED, CREATE_TIME, UPDATE_TIME) VALUES (generated, null, null, null, null, null, null, null, null, null, null, default, 'default', 'default');


SELECT *
FROM LEAF_TAG
where MEMBER_ID = 999
AND NAME LIKE CONCAT('%', 'ts', '%')
ORDER BY ID DESC
LIMIT 10 OFFSET 10;

SELECT * FROM LEAF
WHERE TREE_ID = 1
  AND IS_DELETED = FALSE
ORDER BY ID;