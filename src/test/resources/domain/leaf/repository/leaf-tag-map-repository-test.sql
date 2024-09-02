-- 맴버 생성
INSERT INTO MEMBER (ID, EMAIL, PASSWORD, NICKNAME, INTRODUCTION)
VALUES (999, 'test@gmail.com', '1234', '테스트', '테스트입니다.');

-- 씨드 생성
INSERT INTO SEED (ID, NAME) VALUES (997, 'BOOK');
INSERT INTO SEED (ID, NAME) VALUES (996, 'ETC');

-- 트리 생성 (ETC 트리)
INSERT INTO TREE (ID, MEMBER_ID, SEED_ID, TITLE, DESCRIPTION, BOOK_MARK_COUNT, VISIBILITY)
VALUES (999, 999, 996, 'testTree', 'testDesc', 0, 1);

-- 리프 태그 생성
INSERT INTO LEAF_TAG (ID, MEMBER_ID, NAME) VALUES (1, 999, '리프 태그 테스트');
INSERT INTO LEAF_TAG (ID, MEMBER_ID, NAME) VALUES (2, 999, '리프 태그 테스트');
INSERT INTO LEAF_TAG (ID, MEMBER_ID, NAME) VALUES (3, 999, '리프 태그 테스트');
INSERT INTO LEAF_TAG (ID, MEMBER_ID, NAME) VALUES (4, 999, '리프 태그 테스트');

-- 리프 생성
INSERT INTO LEAF (ID, TREE_ID, PARENT_LEAF_ID, VISIBILITY, VIEW_COUNT, LIKE_COUNT, TITLE, CONTENT, CHILD_LEAF_COUNT, BOOK_MARK) VALUES (9990, 999, NULL, 1, 0, 0, 'testLeaf9970', 'testLeafContent9975', 0, 0);
INSERT INTO LEAF (ID, TREE_ID, PARENT_LEAF_ID, VISIBILITY, VIEW_COUNT, LIKE_COUNT, TITLE, CONTENT, CHILD_LEAF_COUNT, BOOK_MARK) VALUES (9991, 999, 9990, 1, 0, 0, 'testLeaf9970', 'testLeafContent9975', 0, 0);

-- 리프 태그 맵 생성
INSERT INTO LEAF_TAG_MAP (LEAF_ID, LEAF_TAG_ID) VALUES (9990, 1);
INSERT INTO LEAF_TAG_MAP (LEAF_ID, LEAF_TAG_ID) VALUES (9990, 2);
INSERT INTO LEAF_TAG_MAP (LEAF_ID, LEAF_TAG_ID) VALUES (9990, 3);

INSERT INTO LEAF_TAG_MAP (LEAF_ID, LEAF_TAG_ID) VALUES (9991, 1);
INSERT INTO LEAF_TAG_MAP (LEAF_ID, LEAF_TAG_ID) VALUES (9991, 2);
INSERT INTO LEAF_TAG_MAP (LEAF_ID, LEAF_TAG_ID) VALUES (9991, 3);