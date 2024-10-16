-- ====================================================== --
-- 회원 데이터
-- ====================================================== --
INSERT INTO `member`
(`id`, `create_time`, `email`, `introduction`, `is_deleted`, `nickname`, `password`, `update_time`, `username`, `version`)
VALUES
    (1, '2024-10-01 10:00:00', 'user1@example.com', 'Introduction of user1', false, 'nickname1', 'password1hash', '2024-10-01 10:00:00', 'user1', 1),
    (2, '2024-10-02 11:00:00', 'user2@example.com', 'Introduction of user2', false, 'nickname2', 'password2hash', '2024-10-02 11:00:00', 'user2', 1),
    (3, '2024-10-03 12:00:00', 'user3@example.com', 'Introduction of user3', false, 'nickname3', 'password3hash', '2024-10-03 12:00:00', 'user3', 1),
    (4, '2024-10-04 13:00:00', 'user4@example.com', 'Introduction of user4', false, 'nickname4', 'password4hash', '2024-10-04 13:00:00', 'user4', 1),
    (5, '2024-10-05 14:00:00', 'user5@example.com', 'Introduction of user5', false, 'nickname5', 'password5hash', '2024-10-05 14:00:00', 'user5', 1),
    (6, '2024-10-06 15:00:00', 'user6@example.com', 'Introduction of user6', false, 'nickname6', 'password6hash', '2024-10-06 15:00:00', 'user6', 1),
    (7, '2024-10-07 16:00:00', 'user7@example.com', 'Introduction of user7', false, 'nickname7', 'password7hash', '2024-10-07 16:00:00', 'user7', 1),
    (8, '2024-10-08 17:00:00', 'user8@example.com', 'Introduction of user8', false, 'nickname8', 'password8hash', '2024-10-08 17:00:00', 'user8', 1),
    (9, '2024-10-09 18:00:00', 'user9@example.com', 'Introduction of user9', false, 'nickname9', 'password9hash', '2024-10-09 18:00:00', 'user9', 1),
    (10, '2024-10-10 19:00:00', 'user10@example.com', 'Introduction of user10', false, 'nickname10', 'password10hash', '2024-10-10 19:00:00', 'user10', 1),
    (999, '2024-10-10 19:00:00', 'user10@example.com', 'Introduction of user10', false, 'API', 'API', '2024-10-10 19:00:00', 'API', 1);

ALTER TABLE `member` ALTER COLUMN id RESTART WITH 100000;

-- ====================================================== --
-- 팔로우 데이터
-- ====================================================== --
INSERT INTO `follow`
(`active`, `create_time`, `update_time`, `version`, `FOLLOW_ID`, `MEMBER_ID`)
VALUES
    (1, '2024-10-01 10:00:00', '2024-10-01 10:00:00', 1, 1, 2),
    (1, '2024-10-02 10:00:00', '2024-10-02 10:00:00', 1, 1, 3),
    (1, '2024-10-03 10:00:00', '2024-10-03 10:00:00', 1, 2, 1),
    (1, '2024-10-04 10:00:00', '2024-10-04 10:00:00', 1, 2, 4),
    (1, '2024-10-05 10:00:00', '2024-10-05 10:00:00', 1, 3, 1),
    (0, '2024-10-06 10:00:00', '2024-10-06 10:00:00', 1, 3, 2), -- 비활성화된 팔로우
    (1, '2024-10-07 10:00:00', '2024-10-07 10:00:00', 1, 4, 5),
    (1, '2024-10-08 10:00:00', '2024-10-08 10:00:00', 1, 4, 6),
    (1, '2024-10-09 10:00:00', '2024-10-09 10:00:00', 1, 5, 7),
    (1, '2024-10-10 10:00:00', '2024-10-10 10:00:00', 1, 5, 8),
    (1, '2024-10-11 10:00:00', '2024-10-11 10:00:00', 1, 6, 9),
    (0, '2024-10-12 10:00:00', '2024-10-12 10:00:00', 1, 6, 10); -- 비활성화된 팔로우

-- ====================================================== --
-- 회원 배경 데이터
-- ====================================================== --
INSERT INTO `member_background_image`
(`member_id`, `create_time`, `is_deleted`, `name`, `update_time`, `version`)
VALUES
    (1, '2024-10-01 10:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRTuzwnsO7lyoky8uX-foTR0eO51pgwVhciw&s', '2024-10-01 10:00:00', 1),
    (2, '2024-10-02 11:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRTuzwnsO7lyoky8uX-foTR0eO51pgwVhciw&s', '2024-10-02 11:00:00', 1),
    (3, '2024-10-03 12:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRTuzwnsO7lyoky8uX-foTR0eO51pgwVhciw&s', '2024-10-03 12:00:00', 1),
    (4, '2024-10-04 13:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRTuzwnsO7lyoky8uX-foTR0eO51pgwVhciw&s', '2024-10-04 13:00:00', 1),
    (5, '2024-10-05 14:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRTuzwnsO7lyoky8uX-foTR0eO51pgwVhciw&s', '2024-10-05 14:00:00', 1),
    (6, '2024-10-06 15:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRTuzwnsO7lyoky8uX-foTR0eO51pgwVhciw&s', '2024-10-06 15:00:00', 1),
    (7, '2024-10-07 16:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRTuzwnsO7lyoky8uX-foTR0eO51pgwVhciw&s', '2024-10-07 16:00:00', 1),
    (8, '2024-10-08 17:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRTuzwnsO7lyoky8uX-foTR0eO51pgwVhciw&s', '2024-10-08 17:00:00', 1),
    (9, '2024-10-09 18:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRTuzwnsO7lyoky8uX-foTR0eO51pgwVhciw&s', '2024-10-09 18:00:00', 1),
    (10, '2024-10-10 19:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRTuzwnsO7lyoky8uX-foTR0eO51pgwVhciw&s', '2024-10-10 19:00:00', 1);

-- ====================================================== --
-- 회원 프로필 데이터
-- ====================================================== --
INSERT INTO `member_profile_image`
(`member_id`, `create_time`, `is_deleted`, `name`, `update_time`, `version`)
VALUES
    (1, '2024-10-01 10:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxzE0-W4cDj64hPIMW9d5Jfc86aocVsxYq9Q&s', '2024-10-01 10:00:00', 1),
    (2, '2024-10-02 11:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxzE0-W4cDj64hPIMW9d5Jfc86aocVsxYq9Q&s', '2024-10-02 11:00:00', 1),
    (3, '2024-10-03 12:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxzE0-W4cDj64hPIMW9d5Jfc86aocVsxYq9Q&s', '2024-10-03 12:00:00', 1),
    (4, '2024-10-04 13:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxzE0-W4cDj64hPIMW9d5Jfc86aocVsxYq9Q&s', '2024-10-04 13:00:00', 1),
    (5, '2024-10-05 14:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxzE0-W4cDj64hPIMW9d5Jfc86aocVsxYq9Q&s', '2024-10-05 14:00:00', 1),
    (6, '2024-10-06 15:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxzE0-W4cDj64hPIMW9d5Jfc86aocVsxYq9Q&s', '2024-10-06 15:00:00', 1),
    (7, '2024-10-07 16:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxzE0-W4cDj64hPIMW9d5Jfc86aocVsxYq9Q&s', '2024-10-07 16:00:00', 1),
    (8, '2024-10-08 17:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxzE0-W4cDj64hPIMW9d5Jfc86aocVsxYq9Q&s', '2024-10-08 17:00:00', 1),
    (9, '2024-10-09 18:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxzE0-W4cDj64hPIMW9d5Jfc86aocVsxYq9Q&s', '2024-10-09 18:00:00', 1),
    (10, '2024-10-10 19:00:00', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxzE0-W4cDj64hPIMW9d5Jfc86aocVsxYq9Q&s', '2024-10-10 19:00:00', 1);

-- ====================================================== --
-- 도서 카테고리 데이터
-- ====================================================== --

INSERT INTO `book_category`
(`id`, `name`, `version`)
VALUES
    (1, '소설', 1),
    (2, '비소설', 1),
    (3, '과학', 1),
    (4, '판타지', 1),
    (5, '역사', 1),
    (6, '전기', 1),
    (7, '기술', 1),
    (8, '자기계발', 1),
    (9, '미스터리', 1),
    (10, '로맨스', 1);

ALTER TABLE `book_category` ALTER COLUMN id RESTART WITH 100000;

-- ====================================================== --
-- 도서 데이터
-- ====================================================== --
INSERT INTO `book`
(`id`, `author`, `create_time`, `image_file`, `is_deleted`, `isbn`, `publish_date`, `publisher`, `title`, `total_page`, `translator`, `update_time`, `version`, `book_category_id`, `member_id`)
VALUES
    (1, 'J.K. 롤링', '2024-10-01 10:00:00', 'harry_potter_cover.jpg', false, '978-3-16-148410-0', '2007-07-21', '블룸즈버리', '해리 포터와 죽음의 성물', 759, NULL, '2024-10-01 10:00:00', 1, 4, 1),
    (2, '조지 오웰', '2024-10-02 11:00:00', '1984_cover.jpg', false, '978-0-452-28423-4', '1949-06-08', '세커 & 워버그', '1984', 328, NULL, '2024-10-02 11:00:00', 1, 1, 2),
    (3, '유발 하라리', '2024-10-03 12:00:00', 'sapiens_cover.jpg', false, '978-0-06-231609-7', '2014-09-04', '하퍼', '사피엔스: 인류의 간략한 역사', 443, NULL, '2024-10-03 12:00:00', 1, 2, 3),
    (4, 'J.R.R. 톨킨', '2024-10-04 13:00:00', 'lord_of_the_rings_cover.jpg', false, '978-0-618-00222-8', '1954-07-29', '조지 앨런 & 언윈', '반지의 제왕', 1216, NULL, '2024-10-04 13:00:00', 1, 4, 4),
    (5, '스티븐 호킹', '2024-10-05 14:00:00', 'brief_history_of_time_cover.jpg', false, '978-0-553-17521-9', '1988-04-01', '반탐 북스', '시간의 역사', 256, NULL, '2024-10-05 14:00:00', 1, 3, 5),
    (6, '월터 아이작슨', '2024-10-06 15:00:00', 'steve_jobs_cover.jpg', false, '978-1-4516-4853-9', '2011-10-24', '사이먼 & 슈스터', '스티브 잡스', 656, NULL, '2024-10-06 15:00:00', 1, 6, 6),
    (7, '엘리프 샤팍', '2024-10-07 16:00:00', 'forty_rules_of_love_cover.jpg', false, '978-0-670-02145-2', '2009-01-01', '펭귄', '사랑의 40가지 법칙', 368, NULL, '2024-10-07 16:00:00', 1, 10, 7),
    (8, '말콤 글래드웰', '2024-10-08 17:00:00', 'outliers_cover.jpg', false, '978-0-316-01792-3', '2008-11-18', '리틀, 브라운 앤 컴퍼니', '아웃라이어: 성공의 이야기', 309, NULL, '2024-10-08 17:00:00', 1, 8, 8),
    (9, '애거사 크리스티', '2024-10-09 18:00:00', 'murder_on_the_orient_express_cover.jpg', false, '978-0-06-207350-5', '1934-01-01', '콜린스 범죄 클럽', '오리엔트 특급 살인', 256, NULL, '2024-10-09 18:00:00', 1, 9, 9),
    (10, '니콜라스 스파크스', '2024-10-10 19:00:00', 'the_notebook_cover.jpg', false, '978-0-446-52080-5', '1996-10-01', '워너 북스', '노트북', 214, NULL, '2024-10-10 19:00:00', 1, 10, 10);

ALTER TABLE `book` ALTER COLUMN id RESTART WITH 100000;

-- ====================================================== --
-- 도서 좋아요 데이터
-- ====================================================== --
INSERT INTO `book_like`
(`active`, `create_time`, `update_time`, `version`, `BOOK_ID`, `MEMBER_ID`)
VALUES
    (1, '2024-10-01 10:00:00', '2024-10-01 10:00:00', 1, 1, 1),
    (1, '2024-10-02 10:00:00', '2024-10-02 10:00:00', 1, 1, 2),
    (1, '2024-10-03 10:00:00', '2024-10-03 10:00:00', 1, 2, 1),
    (1, '2024-10-04 10:00:00', '2024-10-04 10:00:00', 1, 2, 3),
    (1, '2024-10-05 10:00:00', '2024-10-05 10:00:00', 1, 3, 4),
    (0, '2024-10-06 10:00:00', '2024-10-06 10:00:00', 1, 3, 5), -- 비활성화된 좋아요
    (1, '2024-10-07 10:00:00', '2024-10-07 10:00:00', 1, 4, 6),
    (1, '2024-10-08 10:00:00', '2024-10-08 10:00:00', 1, 4, 7),
    (1, '2024-10-09 10:00:00', '2024-10-09 10:00:00', 1, 5, 8),
    (1, '2024-10-10 10:00:00', '2024-10-10 10:00:00', 1, 5, 9),
    (1, '2024-10-11 10:00:00', '2024-10-11 10:00:00', 1, 6, 10),
    (0, '2024-10-12 10:00:00', '2024-10-12 10:00:00', 1, 6, 1), -- 비활성화된 좋아요
    (1, '2024-10-13 10:00:00', '2024-10-13 10:00:00', 1, 7, 2),
    (1, '2024-10-14 10:00:00', '2024-10-14 10:00:00', 1, 7, 3),
    (1, '2024-10-15 10:00:00', '2024-10-15 10:00:00', 1, 8, 4),
    (1, '2024-10-16 10:00:00', '2024-10-16 10:00:00', 1, 8, 5),
    (1, '2024-10-17 10:00:00', '2024-10-17 10:00:00', 1, 9, 6),
    (0, '2024-10-18 10:00:00', '2024-10-18 10:00:00', 1, 9, 7); -- 비활성화된 좋아요

-- ====================================================== --
-- 도서 댓글 데이터
-- ====================================================== --
INSERT INTO `book_comment`
(`id`, `content`, `create_time`, `is_deleted`, `like_count`, `update_time`, `version`, `book_id`, `member_id`)
VALUES
    (1, '이 책은 걸작입니다! 모든 페이지가 즐거웠어요.', '2024-10-01 10:00:00', false, 10, '2024-10-01 10:00:00', 1, 1, 1),
    (2, '끝까지 긴장감 넘치는 읽기였습니다.', '2024-10-02 11:00:00', false, 5, '2024-10-02 11:00:00', 1, 2, 2),
    (3, '등장인물들이 매우 공감되었고, 줄거리도 흥미로웠습니다.', '2024-10-03 12:00:00', false, 8, '2024-10-03 12:00:00', 1, 3, 3),
    (4, '내 취향은 아니었지만, 괜찮은 읽기였습니다.', '2024-10-04 13:00:00', false, 2, '2024-10-04 13:00:00', 1, 4, 4),
    (5, '역사에 대한 통찰력이 뛰어난 책입니다. 강력 추천합니다!', '2024-10-05 14:00:00', false, 15, '2024-10-05 14:00:00', 1, 5, 5),
    (6, '내 취향에는 조금 느린 템포였습니다.', '2024-10-06 15:00:00', false, 1, '2024-10-06 15:00:00', 1, 5, 6),
    (7, '작문 스타일이 정말 마음에 들었습니다!', '2024-10-07 16:00:00', false, 20, '2024-10-07 16:00:00', 1, 6, 7),
    (8, '결말이 좀 더 좋았다면 5점을 주겠어요.', '2024-10-08 17:00:00', false, 3, '2024-10-08 17:00:00', 1, 7, 8),
    (9, '장르 팬에게는 좋지만, 아주 독창적이지는 않았습니다.', '2024-10-09 18:00:00', false, 4, '2024-10-09 18:00:00', 1, 8, 9),
    (10, '정말 공감되는 아름다운 이야기였습니다.', '2024-10-10 19:00:00', false, 12, '2024-10-10 19:00:00', 1, 9, 10);

ALTER TABLE `book_comment` ALTER COLUMN id RESTART WITH 100000;

-- ====================================================== --
-- 도서 댓글 좋아요 데이터
-- ====================================================== --
INSERT INTO `book_comment_like`
(`active`, `create_time`, `update_time`, `version`, `BOOK_COMMENT_ID`, `MEMBER_ID`)
VALUES
    (1, '2024-10-01 10:00:00', '2024-10-01 10:00:00', 1, 1, 1),
    (1, '2024-10-02 10:00:00', '2024-10-02 10:00:00', 1, 1, 2),
    (1, '2024-10-03 10:00:00', '2024-10-03 10:00:00', 1, 2, 1),
    (1, '2024-10-04 10:00:00', '2024-10-04 10:00:00', 1, 2, 2),
    (1, '2024-10-05 10:00:00', '2024-10-05 10:00:00', 1, 3, 3),
    (0, '2024-10-06 10:00:00', '2024-10-06 10:00:00', 1, 3, 4), -- 비활성화된 좋아요
    (1, '2024-10-07 10:00:00', '2024-10-07 10:00:00', 1, 4, 1),
    (1, '2024-10-08 10:00:00', '2024-10-08 10:00:00', 1, 4, 2),
    (1, '2024-10-09 10:00:00', '2024-10-09 10:00:00', 1, 5, 5),
    (1, '2024-10-10 10:00:00', '2024-10-10 10:00:00', 1, 5, 6),
    (1, '2024-10-11 10:00:00', '2024-10-11 10:00:00', 1, 6, 7),
    (0, '2024-10-12 10:00:00', '2024-10-12 10:00:00', 1, 6, 8), -- 비활성화된 좋아요
    (1, '2024-10-13 10:00:00', '2024-10-13 10:00:00', 1, 7, 1),
    (1, '2024-10-14 10:00:00', '2024-10-14 10:00:00', 1, 7, 2),
    (1, '2024-10-15 10:00:00', '2024-10-15 10:00:00', 1, 8, 3),
    (1, '2024-10-16 10:00:00', '2024-10-16 10:00:00', 1, 8, 4),
    (1, '2024-10-17 10:00:00', '2024-10-17 10:00:00', 1, 9, 5),
    (0, '2024-10-18 10:00:00', '2024-10-18 10:00:00', 1, 9, 6), -- 비활성화된 좋아요
    (1, '2024-10-19 10:00:00', '2024-10-19 10:00:00', 1, 10, 1),
    (1, '2024-10-20 10:00:00', '2024-10-20 10:00:00', 1, 10, 2),
    (1, '2024-10-21 10:00:00', '2024-10-21 10:00:00', 1, 10, 3),
    (1, '2024-10-22 10:00:00', '2024-10-22 10:00:00', 1, 10, 4),
    (1, '2024-10-23 10:00:00', '2024-10-23 10:00:00', 1, 10, 5),
    (1, '2024-10-24 10:00:00', '2024-10-24 10:00:00', 1, 10, 6);

-- ====================================================== --
-- 씨앗 데이터
-- ====================================================== --
INSERT INTO `seed`
(`id`, `eng_name`, `kor_name`, `version`)
VALUES
    (1, 'book', '도서', 1),
    (2, 'Cucumber', '오이', 1),
    (3, 'Carrot', '당근', 1),
    (4, 'Spinach', '시금치', 1),
    (5, 'Lettuce', '상추', 1),
    (6, 'Bell Pepper', '피망', 1),
    (7, 'Broccoli', '브로콜리', 1),
    (8, 'Zucchini', '호박', 1),
    (9, 'Eggplant', '가지', 1),
    (10, 'Pumpkin', '호박', 1);

ALTER TABLE `seed` ALTER COLUMN id RESTART WITH 100000;

-- ====================================================== --
-- 트리 데이터
-- ====================================================== --
INSERT INTO `tree`
(`id`, `book_mark_count`, `create_time`, `description`, `is_deleted`, `title`, `update_time`, `version`, `visibility`, `book_id`, `member_id`, `seed_id`)
VALUES
    (1, 5, '2024-10-01 10:00:00', '토마토 나무의 성장 과정을 나타내는 나무입니다.', false, '토마토 나무', '2024-10-01 10:00:00', 1, true, 1, 1, 1),
    (2, 3, '2024-10-02 11:00:00', '오이 나무의 성장 과정을 나타내는 나무입니다.', true, '오이 나무', '2024-10-02 11:00:00', 1, true, 2, 1, 2),  -- is_deleted = true
    (3, 10, '2024-10-03 12:00:00', '당근 나무의 성장 과정을 나타내는 나무입니다.', false, '당근 나무', '2024-10-03 12:00:00', 1, true, 3, 2, 3),
    (4, 7, '2024-10-04 13:00:00', '시금치 나무의 성장 과정을 나타내는 나무입니다.', true, '시금치 나무', '2024-10-04 13:00:00', 1, true, 4, 2, 4),  -- is_deleted = true
    (5, 2, '2024-10-05 14:00:00', '상추 나무의 성장 과정을 나타내는 나무입니다.', false, '상추 나무', '2024-10-05 14:00:00', 1, true, 5, 3, 5),
    (6, 4, '2024-10-06 15:00:00', '피망 나무의 성장 과정을 나타내는 나무입니다.', true, '피망 나무', '2024-10-06 15:00:00', 1, true, 6, 3, 6),  -- is_deleted = true
    (7, 6, '2024-10-07 16:00:00', '브로콜리 나무의 성장 과정을 나타내는 나무입니다.', false, '브로콜리 나무', '2024-10-07 16:00:00', 1, true, 7, 4, 7),
    (8, 1, '2024-10-08 17:00:00', '호박 나무의 성장 과정을 나타내는 나무입니다.', true, '호박 나무', '2024-10-08 17:00:00', 1, true, 8, 4, 8),  -- is_deleted = true
    (9, 9, '2024-10-09 18:00:00', '가지 나무의 성장 과정을 나타내는 나무입니다.', false, '가지 나무', '2024-10-09 18:00:00', 1, true, 9, 5, 9),
    (10, 8, '2024-10-10 19:00:00', '무 나무의 성장 과정을 나타내는 나무입니다.', false, '무 나무', '2024-10-10 19:00:00', 1, true, 10, 5, 10);

ALTER TABLE `tree` ALTER COLUMN id RESTART WITH 100000;

-- ====================================================== --
-- 트리 도서 데이터
-- ====================================================== --
INSERT INTO `tree_book`
(`tree_id`, `create_time`, `is_deleted`, `reading_page`, `update_time`, `version`)
VALUES
    (1, '2024-10-01 09:00:00', false, 5, '2024-10-01 09:00:00', 1),
    (2, '2024-10-02 10:00:00', false, 10, '2024-10-02 10:00:00', 1),
    (3, '2024-10-03 11:00:00', false, 15, '2024-10-03 11:00:00', 1),
    (4, '2024-10-04 12:00:00', true, 20, '2024-10-04 12:00:00', 1),  -- is_deleted = true
    (5, '2024-10-05 13:00:00', false, 25, '2024-10-05 13:00:00', 1),
    (6, '2024-10-06 14:00:00', false, 30, '2024-10-06 14:00:00', 1),
    (7, '2024-10-07 15:00:00', true, 35, '2024-10-07 15:00:00', 1),  -- is_deleted = true
    (8, '2024-10-08 16:00:00', false, 40, '2024-10-08 16:00:00', 1),
    (9, '2024-10-09 17:00:00', false, 45, '2024-10-09 17:00:00', 1),
    (10, '2024-10-10 18:00:00', true, 50, '2024-10-10 18:00:00', 1);  -- is_deleted = true

-- ====================================================== --
-- 트리 씨앗 데이터
-- ====================================================== --
INSERT INTO `tree_image`
(`tree_id`, `create_time`, `is_deleted`, `name`, `update_time`, `version`)
VALUES
    (1, '2024-10-01 08:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-01 08:00:00', 1),
    (2, '2024-10-02 09:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-02 09:00:00', 1),
    (3, '2024-10-03 10:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-03 10:00:00', 1),
    (4, '2024-10-04 11:00:00', true, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-04 11:00:00', 1),  -- is_deleted = true
    (5, '2024-10-05 12:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-05 12:00:00', 1),
    (6, '2024-10-06 13:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-06 13:00:00', 1),
    (7, '2024-10-07 14:00:00', true, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-07 14:00:00', 1),  -- is_deleted = true
    (8, '2024-10-08 15:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-08 15:00:00', 1),
    (9, '2024-10-09 16:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-09 16:00:00', 1),
    (10, '2024-10-10 17:00:00', true, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-10 17:00:00', 1);  -- is_deleted = true

-- ====================================================== --
-- 트리 좋아요 데이터
-- ====================================================== --
INSERT INTO `tree_like`
(`active`, `create_time`, `update_time`, `version`, `MEMBER_ID`, `TREE_ID`)
VALUES
    (true, '2024-10-01 09:00:00', '2024-10-01 09:00:00', 1, 1, 1),
    (true, '2024-10-02 10:00:00', '2024-10-02 10:00:00', 1, 2, 2),
    (false, '2024-10-03 11:00:00', '2024-10-03 11:00:00', 1, 3, 3),
    (true, '2024-10-04 12:00:00', '2024-10-04 12:00:00', 1, 1, 4),
    (false, '2024-10-05 13:00:00', '2024-10-05 13:00:00', 1, 2, 5),
    (true, '2024-10-06 14:00:00', '2024-10-06 14:00:00', 1, 3, 6),
    (false, '2024-10-07 15:00:00', '2024-10-07 15:00:00', 1, 4, 7),
    (true, '2024-10-08 16:00:00', '2024-10-08 16:00:00', 1, 5, 8),
    (false, '2024-10-09 17:00:00', '2024-10-09 17:00:00', 1, 1, 9),
    (true, '2024-10-10 18:00:00', '2024-10-10 18:00:00', 1, 2, 10);

-- ====================================================== --
-- 리프 데이터
-- ====================================================== --
INSERT INTO `leaf`
(`id`, `book_mark`, `child_leaf_count`, `content`, `create_time`, `is_deleted`, `like_count`, `title`, `update_time`, `version`, `view_count`, `visibility`, `parent_leaf_id`, `tree_id`)
VALUES
    (1, false, 2, '첫 번째 잎의 내용입니다. 이 잎은 나무의 생장에 중요한 역할을 합니다.', '2024-10-01 08:00:00', false, 10, '첫 번째 잎', '2024-10-01 08:00:00', 1, 50, true, null, 1),
    (2, true, 1, '두 번째 잎은 자주 사용됩니다. 이 잎은 많은 주목을 받습니다.', '2024-10-02 09:00:00', false, 20, '두 번째 잎', '2024-10-02 09:00:00', 1, 30, true, 1, 1),
    (3, false, 0, '세 번째 잎은 조용히 자라고 있습니다.', '2024-10-03 10:00:00', false, 5, '세 번째 잎', '2024-10-03 10:00:00', 1, 15, true, 1, 2),
    (4, false, 0, '네 번째 잎은 다양한 기능을 가지고 있습니다.', '2024-10-04 11:00:00', false, 15, '네 번째 잎', '2024-10-04 11:00:00', 1, 25, true, 2, 2),
    (5, true, 3, '다섯 번째 잎은 중요한 데이터를 포함하고 있습니다.', '2024-10-05 12:00:00', false, 30, '다섯 번째 잎', '2024-10-05 12:00:00', 1, 40, true, null, 3),
    (6, false, 0, '여섯 번째 잎은 적은 관심을 받고 있습니다.', '2024-10-06 13:00:00', false, 2, '여섯 번째 잎', '2024-10-06 13:00:00', 1, 5, true, 5, 3),
    (7, true, 0, '일곱 번째 잎은 신비로운 존재입니다.', '2024-10-07 14:00:00', false, 1, '일곱 번째 잎', '2024-10-07 14:00:00', 1, 3, true, null, 4),
    (8, false, 2, '여덟 번째 잎은 나무의 생장과 관련이 있습니다.', '2024-10-08 15:00:00', false, 8, '여덟 번째 잎', '2024-10-08 15:00:00', 1, 10, true, 3, 4),
    (9, true, 1, '아홉 번째 잎은 독특한 특징을 가지고 있습니다.', '2024-10-09 16:00:00', false, 12, '아홉 번째 잎', '2024-10-09 16:00:00', 1, 20, true, 4, 5),
    (10, false, 0, '열 번째 잎은 특별한 경험을 제공합니다.', '2024-10-10 17:00:00', false, 0, '열 번째 잎', '2024-10-10 17:00:00', 1, 1, true, 5, 5);

ALTER TABLE `leaf` ALTER COLUMN id RESTART WITH 100000;

-- ====================================================== --
-- 리프 도서 데이터
-- ====================================================== --
INSERT INTO `leaf_book`
(`leaf_id`, `end_page`, `is_deleted`, `start_page`, `version`)
VALUES
    (1, 10, false, 1, 1),
    (2, 20, false, 11, 1),
    (3, 5, false, 1, 1),
    (4, 15, false, 6, 1),
    (5, 25, false, 1, 1),
    (6, 30, false, 10, 1),
    (7, 12, false, 1, 1),
    (8, 8, false, 1, 1),
    (9, 18, false, 1, 1),
    (10, 22, false, 1, 1);

-- ====================================================== --
-- 리프 이미지
-- ====================================================== --
INSERT INTO `leaf_image`
(`id`, `create_time`, `is_deleted`, `name`, `update_time`, `version`, `leaf_id`)
VALUES
    (1, '2024-10-01 10:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-01 10:00:00', 1, 1),
    (2, '2024-10-02 11:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-02 11:00:00', 1, 2),
    (3, '2024-10-03 12:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-03 12:00:00', 1, 3),
    (4, '2024-10-04 13:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-04 13:00:00', 1, 4),
    (5, '2024-10-05 14:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-05 14:00:00', 1, 5),
    (6, '2024-10-06 15:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-06 15:00:00', 1, 6),
    (7, '2024-10-07 16:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-07 16:00:00', 1, 7),
    (8, '2024-10-08 17:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-08 17:00:00', 1, 8),
    (9, '2024-10-09 18:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-09 18:00:00', 1, 9),
    (10, '2024-10-10 19:00:00', false, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788995379431.jpg', '2024-10-10 19:00:00', 1, 10);

ALTER TABLE `leaf_image` ALTER COLUMN id RESTART WITH 100000;

-- ====================================================== --
-- 리프 좋아요 데이터
-- ====================================================== --
INSERT INTO `leaf_tag`
(`id`, `create_time`, `is_deleted`, `name`, `update_time`, `version`, `member_id`)
VALUES
    (1, '2024-10-01 09:00:00', false, '토마토 태그', '2024-10-01 09:00:00', 1, 1),
    (2, '2024-10-02 10:00:00', false, '오이 태그', '2024-10-02 10:00:00', 1, 1),
    (3, '2024-10-03 11:00:00', false, '당근 태그', '2024-10-03 11:00:00', 1, 2),
    (4, '2024-10-04 12:00:00', false, '시금치 태그', '2024-10-04 12:00:00', 1, 2),
    (5, '2024-10-05 13:00:00', false, '상추 태그', '2024-10-05 13:00:00', 1, 3),
    (6, '2024-10-06 14:00:00', false, '피망 태그', '2024-10-06 14:00:00', 1, 3),
    (7, '2024-10-07 15:00:00', false, '브로콜리 태그', '2024-10-07 15:00:00', 1, 4),
    (8, '2024-10-08 16:00:00', false, '호박 태그', '2024-10-08 16:00:00', 1, 4),
    (9, '2024-10-09 17:00:00', false, '가지 태그', '2024-10-09 17:00:00', 1, 5),
    (10, '2024-10-10 18:00:00', false, '무 태그', '2024-10-10 18:00:00', 1, 5);

ALTER TABLE `leaf_tag` ALTER COLUMN id RESTART WITH 100000;

-- ====================================================== --
-- 리프 태그 맵핑 데이터
-- ====================================================== --
INSERT INTO `leaf_tag_map`
(`active`, `version`, `LEAF_ID`, `LEAF_TAG_ID`)
VALUES
    (false, 1, 1, 1),  -- 토마토 나무에 토마토 태그 연결
    (false, 1, 1, 2),  -- 토마토 나무에 오이 태그 연결
    (false, 1, 2, 2),  -- 오이 나무에 오이 태그 연결
    (false, 1, 2, 3),  -- 오이 나무에 당근 태그 연결
    (false, 1, 3, 3),  -- 당근 나무에 당근 태그 연결
    (false, 1, 3, 4),  -- 당근 나무에 시금치 태그 연결
    (false, 1, 4, 4),  -- 시금치 나무에 시금치 태그 연결
    (false, 1, 5, 5),  -- 상추 나무에 상추 태그 연결
    (false, 1, 6, 6),  -- 피망 나무에 피망 태그 연결
    (false, 1, 7, 7);  -- 브로콜리 나무에 브로콜리 태그 연결

-- ====================================================== --
-- 리프 좋아요 데이터
-- ====================================================== --
INSERT INTO `leaf_like`
(`active`, `create_time`, `update_time`, `version`, `LEAF_ID`, `MEMBER_ID`)
VALUES
    (true, '2024-10-01 10:00:00', '2024-10-01 10:00:00', 1, 1, 1),  -- 회원 1이 토마토 나무를 좋아함
    (true, '2024-10-02 11:00:00', '2024-10-02 11:00:00', 1, 2, 1),  -- 회원 1이 오이 나무를 좋아함
    (true, '2024-10-03 12:00:00', '2024-10-03 12:00:00', 1, 3, 2),  -- 회원 2가 당근 나무를 좋아함
    (false, '2024-10-04 13:00:00', '2024-10-04 13:00:00', 1, 4, 2),  -- 회원 2가 시금치 나무를 싫어함
    (true, '2024-10-05 14:00:00', '2024-10-05 14:00:00', 1, 5, 3),  -- 회원 3가 상추 나무를 좋아함
    (true, '2024-10-06 15:00:00', '2024-10-06 15:00:00', 1, 6, 3),  -- 회원 3가 피망 나무를 좋아함
    (true, '2024-10-07 16:00:00', '2024-10-07 16:00:00', 1, 7, 4),  -- 회원 4가 브로콜리 나무를 좋아함
    (true, '2024-10-08 17:00:00', '2024-10-08 17:00:00', 1, 8, 4),  -- 회원 4가 호박 나무를 좋아함
    (false, '2024-10-09 18:00:00', '2024-10-09 18:00:00', 1, 9, 5),  -- 회원 5가 가지 나무를 싫어함
    (true, '2024-10-10 19:00:00', '2024-10-10 19:00:00', 1, 10, 5);  -- 회원 5가 무 나무를 좋아함

-- ====================================================== --
-- 리프 댓글 데이터
-- ====================================================== --
INSERT INTO `leaf_comment`
(`id`, `content`, `create_time`, `is_deleted`, `like_count`, `update_time`, `version`, `leaf_id`, `member_id`)
VALUES
    (1, '이 토마토 나무는 정말 멋져요!', '2024-10-01 10:00:00', false, 5, '2024-10-01 10:00:00', 1, 1, 1),  -- 회원 1이 토마토 나무에 대한 댓글
    (2, '오이 나무가 이렇게 자란다니 놀라워요.', '2024-10-02 11:00:00', false, 3, '2024-10-02 11:00:00', 1, 2, 1),  -- 회원 1이 오이 나무에 대한 댓글
    (3, '당근 나무는 정말 귀엽네요!', '2024-10-03 12:00:00', false, 2, '2024-10-03 12:00:00', 1, 3, 2),  -- 회원 2가 당근 나무에 대한 댓글
    (4, '시금치 나무가 이렇게 성장하다니 놀라워요.', '2024-10-04 13:00:00', false, 0, '2024-10-04 13:00:00', 1, 4, 2),  -- 회원 2가 시금치 나무에 대한 댓글
    (5, '상추 나무의 색깔이 정말 아름다워요.', '2024-10-05 14:00:00', false, 1, '2024-10-05 14:00:00', 1, 5, 3),  -- 회원 3가 상추 나무에 대한 댓글
    (6, '피망 나무에 대해 더 알고 싶어요.', '2024-10-06 15:00:00', false, 2, '2024-10-06 15:00:00', 1, 6, 3),  -- 회원 3가 피망 나무에 대한 댓글
    (7, '브로콜리 나무는 정말 건강해 보이네요.', '2024-10-07 16:00:00', false, 4, '2024-10-07 16:00:00', 1, 7, 4),  -- 회원 4가 브로콜리 나무에 대한 댓글
    (8, '호박 나무는 정말 신기하네요.', '2024-10-08 17:00:00', false, 3, '2024-10-08 17:00:00', 1, 8, 4),  -- 회원 4가 호박 나무에 대한 댓글
    (9, '가지 나무는 너무 귀엽습니다!', '2024-10-09 18:00:00', false, 0, '2024-10-09 18:00:00', 1, 9, 5),  -- 회원 5가 가지 나무에 대한 댓글
    (10, '무 나무는 어떻게 이렇게 잘 자라나요?', '2024-10-10 19:00:00', false, 2, '2024-10-10 19:00:00', 1, 10, 5);  -- 회원 5가 무 나무에 대한 댓글

ALTER TABLE `leaf_comment` ALTER COLUMN id RESTART WITH 100000;

-- ====================================================== --
-- 리프 댓글 좋아요 데이터
-- ====================================================== --
INSERT INTO `leaf_comment_like`
(`active`, `create_time`, `update_time`, `version`, `LEAF_COMMENT_ID`, `MEMBER_ID`)
VALUES
    (true, '2024-10-01 10:00:00', '2024-10-01 10:00:00', 1, 1, 1),  -- 회원 1이 댓글 1을 좋아요
    (true, '2024-10-01 11:00:00', '2024-10-01 11:00:00', 1, 2, 2),  -- 회원 2가 댓글 2를 좋아요
    (false, '2024-10-02 12:00:00', '2024-10-02 12:00:00', 1, 3, 1),  -- 회원 1이 댓글 3을 좋아요 하지 않음
    (true, '2024-10-03 13:00:00', '2024-10-03 13:00:00', 1, 4, 3),  -- 회원 3가 댓글 4를 좋아요
    (true, '2024-10-04 14:00:00', '2024-10-04 14:00:00', 1, 5, 2),  -- 회원 2가 댓글 5를 좋아요
    (false, '2024-10-05 15:00:00', '2024-10-05 15:00:00', 1, 6, 3),  -- 회원 3가 댓글 6을 좋아요 하지 않음
    (true, '2024-10-06 16:00:00', '2024-10-06 16:00:00', 1, 7, 4),  -- 회원 4가 댓글 7을 좋아요
    (true, '2024-10-07 17:00:00', '2024-10-07 17:00:00', 1, 8, 4),  -- 회원 4가 댓글 8을 좋아요
    (false, '2024-10-08 18:00:00', '2024-10-08 18:00:00', 1, 9, 5),  -- 회원 5가 댓글 9을 좋아요 하지 않음
    (true, '2024-10-09 19:00:00', '2024-10-09 19:00:00', 1, 10, 5);  -- 회원 5가 댓글 10을 좋아요

-- ====================================================== --
-- 회고록
-- ====================================================== --
INSERT INTO `memoir`
(`id`, `create_time`, `is_deleted`, `text`, `title`, `update_time`, `version`, `visibility`, `tree_id`)
VALUES
    (1, '2024-10-01 10:00:00', false, '어렸을 때 나무 밑에서 놀았던 추억이 떠오른다.', '나무 아래의 추억', '2024-10-01 10:00:00', 1, true, 1),
    (2, '2024-10-02 11:00:00', false, '가을의 나무는 항상 마음을 편안하게 해준다.', '가을 나무', '2024-10-02 11:00:00', 1, true, 2),
    (3, '2024-10-03 12:00:00', false, '나무 한 그루에도 수백 년의 역사가 담겨 있다.', '나무의 역사', '2024-10-03 12:00:00', 1, true, 3),
    (4, '2024-10-04 13:00:00', false, '나무가 주는 그늘에서 하루를 보내고 싶다.', '나무 그늘', '2024-10-04 13:00:00', 1, true, 4),
    (5, '2024-10-05 14:00:00', false, '어느 날 나무를 바라보며 새로운 영감을 얻었다.', '나무에서 온 영감', '2024-10-05 14:00:00', 1, true, 5),
    (6, '2024-10-06 15:00:00', false, '겨울이 오면 나무들은 어떻게 변할까 궁금하다.', '겨울의 나무', '2024-10-06 15:00:00', 1, true, 6),
    (7, '2024-10-07 16:00:00', false, '나무를 바라보며 느낀 감정들을 글로 적어본다.', '나무와의 대화', '2024-10-07 16:00:00', 1, true, 7),
    (8, '2024-10-08 17:00:00', false, '한 그루의 나무가 자라나는 과정을 지켜봤다.', '나무의 성장', '2024-10-08 17:00:00', 1, true, 8),
    (9, '2024-10-09 18:00:00', false, '나무 아래서 책을 읽는 시간이 참 좋다.', '나무와 책', '2024-10-09 18:00:00', 1, true, 9),
    (10, '2024-10-10 19:00:00', false, '나무와 자연에 대해 더 깊이 생각하게 되었다.', '나무와 자연', '2024-10-10 19:00:00', 1, true, 10);

ALTER TABLE `memoir` ALTER COLUMN id RESTART WITH 100000;

-- ====================================================== --
-- 회고록 좋아요
-- ====================================================== --
INSERT INTO `memoir_like`
(`active`, `create_time`, `update_time`, `version`, `MEMBER_ID`, `MEMOIR_ID`)
VALUES
    (true, '2024-10-01 09:00:00', '2024-10-01 09:00:00', 1, 1, 1),
    (true, '2024-10-02 10:00:00', '2024-10-02 10:00:00', 1, 2, 2),
    (true, '2024-10-03 11:00:00', '2024-10-03 11:00:00', 1, 3, 3),
    (true, '2024-10-04 12:00:00', '2024-10-04 12:00:00', 1, 4, 4),
    (true, '2024-10-05 13:00:00', '2024-10-05 13:00:00', 1, 5, 5),
    (true, '2024-10-06 14:00:00', '2024-10-06 14:00:00', 1, 6, 6),
    (true, '2024-10-07 15:00:00', '2024-10-07 15:00:00', 1, 7, 7),
    (true, '2024-10-08 16:00:00', '2024-10-08 16:00:00', 1, 8, 8),
    (true, '2024-10-09 17:00:00', '2024-10-09 17:00:00', 1, 9, 9),
    (true, '2024-10-10 18:00:00', '2024-10-10 18:00:00', 1, 10, 10);

-- ====================================================== --
-- 회고록 댓글
-- ====================================================== --
INSERT INTO `memoir_comment`
(`id`, `content`, `create_time`, `is_deleted`, `like_count`, `update_time`, `version`, `member_id`, `memoir_id`)
VALUES
    (1, '이 회고록 정말 감명 깊어요!', '2024-10-01 09:00:00', false, 5, '2024-10-01 09:00:00', 1, 1, 1),
    (2, '저도 이 글에 공감합니다.', '2024-10-02 10:00:00', false, 3, '2024-10-02 10:00:00', 1, 2, 2),
    (3, '정말 잘 쓰신 글입니다.', '2024-10-03 11:00:00', false, 7, '2024-10-03 11:00:00', 1, 3, 3),
    (4, '고민을 덜어주는 글이에요.', '2024-10-04 12:00:00', false, 4, '2024-10-04 12:00:00', 1, 4, 4),
    (5, '큰 위로가 되었습니다.', '2024-10-05 13:00:00', false, 6, '2024-10-05 13:00:00', 1, 5, 5),
    (6, '다시 한 번 읽어봐야겠어요.', '2024-10-06 14:00:00', false, 8, '2024-10-06 14:00:00', 1, 6, 6),
    (7, '정말 좋네요. 감사합니다.', '2024-10-07 15:00:00', false, 2, '2024-10-07 15:00:00', 1, 7, 7),
    (8, '글의 내용이 너무 좋았어요.', '2024-10-08 16:00:00', false, 9, '2024-10-08 16:00:00', 1, 8, 8),
    (9, '감사합니다. 많은 도움이 됐어요.', '2024-10-09 17:00:00', false, 10, '2024-10-09 17:00:00', 1, 9, 9),
    (10, '좋은 글 잘 읽었습니다.', '2024-10-10 18:00:00', false, 1, '2024-10-10 18:00:00', 1, 10, 10);

ALTER TABLE `memoir_comment` ALTER COLUMN id RESTART WITH 100000;

-- ====================================================== --
-- 회고록 댓글 좋아요
-- ====================================================== --
INSERT INTO `memoir_comment_like`
(`active`, `create_time`, `update_time`, `version`, `MEMBER_ID`, `MEMOIR_COMMENT_ID`)
VALUES
    (true, '2024-10-01 09:00:00', '2024-10-01 09:00:00', 1, 1, 1),
    (true, '2024-10-02 10:00:00', '2024-10-02 10:00:00', 1, 2, 2),
    (true, '2024-10-03 11:00:00', '2024-10-03 11:00:00', 1, 3, 3),
    (true, '2024-10-04 12:00:00', '2024-10-04 12:00:00', 1, 4, 4),
    (true, '2024-10-05 13:00:00', '2024-10-05 13:00:00', 1, 5, 5),
    (true, '2024-10-06 14:00:00', '2024-10-06 14:00:00', 1, 6, 6),
    (true, '2024-10-07 15:00:00', '2024-10-07 15:00:00', 1, 7, 7),
    (true, '2024-10-08 16:00:00', '2024-10-08 16:00:00', 1, 8, 8),
    (true, '2024-10-09 17:00:00', '2024-10-09 17:00:00', 1, 9, 9),
    (true, '2024-10-10 18:00:00', '2024-10-10 18:00:00', 1, 10, 10);

-- ====================================================== --
-- 트리 생성 임시 데이터
-- ====================================================== --
INSERT INTO tree_save_tmp
(total_page, visibility, book_category_id, book_id, create_time, id, member_id, seed_id, version, description, tree_title, author, book_title, image_file, publisher)
VALUES
    (120, TRUE, 1, 1, '2024-01-01 10:00:00', 1, 1, 1, 1, '이것은 첫 번째 나무에 대한 설명입니다.', '첫 번째 나무 제목', '홍길동', '첫 번째 책 제목', 'image1.jpg', '첫 번째 출판사'),
    (200, FALSE, 2, 2, '2024-02-15 11:30:00', 2, 2, 2, 1, '두 번째 나무에 대한 설명입니다.', '두 번째 나무 제목', '김철수', '두 번째 책 제목', 'image2.png', '두 번째 출판사'),
    (150, TRUE, 3, 3, '2024-03-20 14:45:00', 3, 3, 3, 1, '세 번째 나무에 대한 설명입니다.', '세 번째 나무 제목', '이영희', '세 번째 책 제목', 'image3.bmp', '세 번째 출판사'),
    (90, TRUE, 4, 4, '2024-04-25 09:20:00', 4, 4, 4, 1, '네 번째 나무에 대한 설명입니다.', '네 번째 나무 제목', '박영수', '네 번째 책 제목', 'image4.gif', '네 번째 출판사');


ALTER TABLE `tree_save_tmp` ALTER COLUMN id RESTART WITH 100000;

-- ====================================================== --
-- 태규의 테스트 START
-- ====================================================== --
INSERT INTO `member`
(`id`, `create_time`, `email`, `introduction`, `is_deleted`, `nickname`, `password`, `update_time`, `username`, `version`)
VALUES
    (1000, '2024-10-01 10:00:00', 'hantest@example.com', 'Introduction of user1', false, 'nickname1', 'password1hash', '2024-10-01 10:00:00', 'user1', 1);

-- ======================== --
-- 태규의 테스트 tree_save_tmp
-- INSERT INTO `tree_save_tmp`
--     (`total_page`, `visibility`, `book_category_id`, `book_id`, `create_time`, `id`, `member_id`, `seed_id`, `version`, `description`, `tree_title`, `author`, `book_title`, `image_file`, `publisher`)
-- VALUES
--     (200, TRUE, 1, NULL, '2024-01-01 10:00:00', 1000, 1000, 1, 1, '태규의 테스트', '태규의 테스트 제목', '태규 작가', '도서 제목', 'C:\Users\gksxo\Desktop\github\ggogit\backend-2\src\main\webapp\image\tmp\test.jpg', '첫 번째 출판사');

-- ====================================================== --
-- 도서 트리 테스트
-- ====================================================== --

INSERT INTO `tree`
(`id`, `book_mark_count`, `create_time`, `description`, `is_deleted`, `title`, `update_time`, `version`, `visibility`, `book_id`, `member_id`, `seed_id`)
VALUES
    (10000, 0, '2024-10-01 10:00:00', '테스트 데이터', false, '토마토 나무', '2024-10-01 10:00:00', 1, true, 1, 1000, 1);

INSERT INTO `leaf`
(`id`, `book_mark`, `child_leaf_count`, `content`, `create_time`, `is_deleted`, `like_count`, `title`, `update_time`, `version`, `view_count`, `visibility`, `parent_leaf_id`, `tree_id`)
VALUES
    (10000, false, 0, '첫 번째 잎의 내용입니다. 이 잎은 나무의 생장에 중요한 역할을 합니다.', '2024-10-01 08:00:00', false, 10, '첫 번째 잎', '2024-10-01 08:00:00', 1, 50, true, null, 10000),
    (10001, false, 0, '첫 번째 잎의 내용입니다. 이 잎은 나무의 생장에 중요한 역할을 합니다.', '2024-10-01 08:00:00', false, 10, '첫 번째 잎', '2024-10-01 08:00:00', 1, 50, true, 10000, 10000);

INSERT INTO `leaf_book`
(`leaf_id`, `end_page`, `is_deleted`, `start_page`, `version`)
VALUES
    (10000, 10, false, 1, 1);

INSERT INTO `leaf_tag`
(`id`, `create_time`, `is_deleted`, `name`, `update_time`, `version`, `member_id`)
VALUES
    (10001, '2024-10-01 09:00:00', false, '태그 1', '2024-10-01 09:00:00', 1, 1000),
    (10002, '2024-10-02 10:00:00', false, '태그 2', '2024-10-02 10:00:00', 1, 1000),
    (10003, '2024-10-03 11:00:00', false, '태그 3', '2024-10-03 11:00:00', 1, 1000),
    (10004, '2024-10-01 09:00:00', false, '태그 4', '2024-10-01 09:00:00', 1, 1000),
    (10005, '2024-10-02 10:00:00', false, '태그 5', '2024-10-02 10:00:00', 1, 1000),
    (10006, '2024-10-03 11:00:00', false, '태그 6', '2024-10-03 11:00:00', 1, 1000),
    (10011, '2024-10-02 10:00:00', false, '조회 테스트 5', '2024-10-02 10:00:00', 1, 1000),
    (10012, '2024-10-03 11:00:00', false, '조회 테스트 6', '2024-10-03 11:00:00', 1, 1000),
    (30000, '2024-10-03 11:00:00', false, '태그 수정 테스트 1', '2024-10-03 11:00:00', 1, 1000),
    (30001, '2024-10-03 11:00:00', false, '태그 삭제 테스트 1', '2024-10-03 11:00:00', 1, 1000);

-- ====================================================== --
-- 기타 트리 테스트
-- ====================================================== --

INSERT INTO `tree`
(`id`, `book_mark_count`, `create_time`, `description`, `is_deleted`, `title`, `update_time`, `version`, `visibility`, `book_id`, `member_id`, `seed_id`)
VALUES
    (20000, 0, '2024-10-01 10:00:00', '테스트 데이터', false, '토마토 나무', '2024-10-01 10:00:00', 1, true, 1, 1000, 2);

INSERT INTO `leaf`
(`id`, `book_mark`, `child_leaf_count`, `content`, `create_time`, `is_deleted`, `like_count`, `title`, `update_time`, `version`, `view_count`, `visibility`, `parent_leaf_id`, `tree_id`)
VALUES
    (20000, false, 1, '첫 번째 잎의 내용입니다. 이 잎은 나무의 생장에 중요한 역할을 합니다.', '2024-10-01 08:00:00', false, 10, '첫 번째 잎', '2024-10-01 08:00:00', 1, 50, true, null, 20000),
    (20001, false, 0, '첫 번째 잎의 내용입니다. 이 잎은 나무의 생장에 중요한 역할을 합니다.', '2024-10-01 08:00:00', false, 10, '첫 번째 잎', '2024-10-01 08:00:00', 1, 50, true, null, 20000);

-- ====================================================== --
-- 태규의 테스트 END
-- ====================================================== --

