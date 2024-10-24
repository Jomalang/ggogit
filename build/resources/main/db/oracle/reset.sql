-- ============================================ --
-- 테이블 초기화
-- ============================================ --
-- 리프
TRUNCATE TABLE LEAF_COMMENT_LIKE;
TRUNCATE TABLE LEAF_COMMENT;
TRUNCATE TABLE LEAF_LIKE;
TRUNCATE TABLE LEAF_BOOK;
TRUNCATE TABLE LEAF_TAG_MAP;
TRUNCATE TABLE LEAF_TAG;
TRUNCATE TABLE LEAF;

-- 회고록
TRUNCATE TABLE MEMOIR_COMMENT_LIKE;
TRUNCATE TABLE MEMOIR_COMMENT;
TRUNCATE TABLE MEMOIR_LIKE;
TRUNCATE TABLE MEMOIR;

-- 트리
TRUNCATE TABLE TREE_LIKE;
TRUNCATE TABLE TREE_IMAGE;
TRUNCATE TABLE TREE_BOOK;
TRUNCATE TABLE TREE;
TRUNCATE TABLE SEED;

-- 도서
TRUNCATE TABLE BOOK_COMMENT_LIKE;
TRUNCATE TABLE BOOK_COMMENT;
TRUNCATE TABLE BOOK_LIKE;
TRUNCATE TABLE BOOK;
TRUNCATE TABLE BOOK_CATEGORY;

-- 회원
TRUNCATE TABLE FOLLOW;
TRUNCATE TABLE MEMBER_BACKGROUND_IMAGE;
TRUNCATE TABLE MEMBER_PROFILE_IMAGE;
TRUNCATE TABLE MEMBER;

-- 설정
COMMIT;