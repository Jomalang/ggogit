-- 맴버 생성
INSERT INTO MEMBER (ID, EMAIL, PASSWORD, NICKNAME, INTRODUCTION)
VALUES (998, 'test@gmail.com', '1234', '테스트', '테스트입니다.');

-- 리프 태그 생성
INSERT INTO LEAF_TAG (ID, MEMBER_ID, NAME) VALUES (1, 998, '리프 태그 테스트');
INSERT INTO LEAF_TAG (ID, MEMBER_ID, NAME) VALUES (2, 998, '리프 태그 테스트');
INSERT INTO LEAF_TAG (ID, MEMBER_ID, NAME) VALUES (3, 998, '리프 태그 테스트');