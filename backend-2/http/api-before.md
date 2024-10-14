
# API 명세서

## 도서
/book/detail?id=book_id

## 리프
/leaf/first/reg?seed=1(book)
/leaf/first/reg?seed=1(book)
/leaf/first/reg?seed=seed_id
/leaf/first/reg?seed=seed_id
/leaf/reg?id=leaf_id&seed=1(book)
/leaf/reg?id=leaf_id&seed=1(book)
/leaf/reg?id=leaf_id&seed=seed_id
/leaf/reg?first=false&id=leaf_id&seed=seed_id
/leaf/edit?id=leaf_id&seed=1(book)
/leaf/edit?id=leaf_id&seed=1(book)
/leaf/edit?id=leaf_id&seed=seed_id
/leaf/edit?id=leaf_id&seed=seed_id
/leaf/list?treeId=tree_id&leafId=leaf_id
/leaf/detail?id=leaf_id

## 태그
/tag/list?id=member_id
/tag/edit?id=tag_id
/tag/edit?id=tag_id

## 회원
/member/login
/member/login
/member/password/reset
/member/password/reset
/member/join?code=wfef123414bnk12h4fsklfaSDGASDf
/member/join?code=wfef123414bnk12h4fsklfaSDGASDf
/memoir?id=tree_id
/memoir?id=tree_id
/memoir/detail?id=memoir_id

## 트리
/seed
/tree/search
/tree/reg?seed=seed_id
/tree/reg?seed=seed_id
/tree?id=tree_id
/tree/book/select
/tree/book/reg?auto=true&id=book_id
/tree/book/reg?auto=false
/tree/book/edit?id=tree_id
/tree/book/edit?id=tree_id
/tree/book/auto/reg
/tree/book/manual/reg
/tree/detail?id=tree_id