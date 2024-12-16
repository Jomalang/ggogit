# API URL 정의

> 마지막 수정자: 조현진\
> 마지막 수정 날짜: 2024-12-16\
> 버전: 1.0.0



## 목차
<!-- TOC -->
* [API URL 정의](#api-url-정의)
  * [목차](#목차)
  * [개요](#개요)
  * [규칙](#규칙)
    * [1. URL은 소문자로 작성한다.](#1-url은-소문자로-작성한다)
    * [2. 리소스 명은 복수형으로 작성한다.](#2-리소스-명은-복수형으로-작성한다)
    * [3. URL은 가급적 명사로 작성하고, 동사는 사용하지 않는다.](#3-url은-가급적-명사로-작성하고-동사는-사용하지-않는다)
    * [4. HTTP Method로 표현이 불가할 경우에만 동사를 사용할 수 있다.](#4-http-method로-표현이-불가할-경우에만-동사를-사용할-수-있다)
    * [5. 리소스의 포함관계는 URL에 표현한다.](#5-리소스의-포함관계는-url에-표현한다)
    * [6. 리소스의 상태를 필터링해서 표현하고 싶을땐 쿼리 파라미터를 이용한다.](#6-리소스의-상태를-필터링해서-표현하고-싶을땐-쿼리-파라미터를-이용한다)
    * [7. 본 문서에 정의되지 않은 리소스는 URL에 표현하지 않는다.](#7-본-문서에-정의되지-않은-리소스는-url에-표현하지-않는다)
    * [8. URL은 하이픈(-)을 사용하여 단어를 구분한다.](#8-url은-하이픈-을-사용하여-단어를-구분한다)
    * [9. URL 마지막에는 슬래시(/)를 붙이지 않는다.](#9-url-마지막에는-슬래시를-붙이지-않는다)
  * [HTTP Method 정의](#http-method-정의)
    * [GET](#get)
    * [POST](#post)
    * [PUT](#put)
    * [PATCH](#patch)
    * [DELETE](#delete)
  * [리소스(표현)명 정의](#리소스표현명-정의)
    * [members](#members)
    * [trees](#trees)
    * [leaves](#leaves)
    * [memoirs](#memoirs)
    * [aladin-books](#aladin-books)
    * [member-books](#member-books)
    * [seeds](#seeds)
    * [oauth](#oauth)
    * [images](#images)
    * [files](#files-)
    * [detail](#detail)
    * [cards](#cards)
  * [URL 정의](#url-정의)
    * [TestController.java](#testcontrollerjava)
    * [AladinController.java](#aladincontrollerjava)
    * [BookCategoryController.java](#bookcategorycontrollerjava)
    * [BookController.java](#bookcontrollerjava)
    * [ImageController.java](#imagecontrollerjava)
    * [LeafBookController.java](#leafbookcontrollerjava)
    * [LeafController.java](#leafcontrollerjava)
    * [LeafEtcController.java](#leafetccontrollerjava)
    * [LeafImageController.java](#leafimagecontrollerjava)
    * [LeafTagController.java](#leaftagcontrollerjava)
    * [AuthController.java](#authcontrollerjava)
    * [MemberController.java](#membercontrollerjava)
    * [MemoirController.java](#memoircontrollerjava)
    * [MemoirImageController.java](#memoirimagecontrollerjava)
    * [SeedController.java](#seedcontrollerjava)
    * [TreeController.java](#treecontrollerjava)
    * [TreeTmpController.java](#treetmpcontrollerjava)
<!-- TOC -->

---
## 개요

---
본 문서는 **API의 규칙과 URL을 정의**합니다.\
본 문서는 GGoggit의 API를 Restfull하게 유지하기 위해 작성되었습니다.

## 규칙

---
### 1. URL은 소문자로 작성한다.
    ```java
    // bad - 대문자로 작성되어 있다.
   @GetMapping("/Trees/1")
    // good - 소문자로 작성되어 있다.
   @GetMapping("/trees/1")
    ```
### 2. 리소스 명은 복수형으로 작성한다.
   ```java
    // bad - 단수형으로 작성되어 있다.
    @GetMapping("/tree")
    // good - 복수형으로 작성되어 있다.
    @GetMapping("/trees")
   ```

### 3. URL은 가급적 명사로 작성하고, 동사는 사용하지 않는다.
   **대신 행위는 HTTP Method로 표현한다.**
   ```java
    // bad - save라는 행위가 URL에 포함되어 있다.
   @GetMapping("/leafs/save")
    // good - POST Method를 사용하여 리소스를 생성한다.
   @PostMapping("/leafs")
   ```
   
### 4. HTTP Method로 표현이 불가할 경우에만 동사를 사용할 수 있다.
   이런 API를 **컨트롤 API URL**라고 한다.
   ```java
    // bad - HTTP Method로 표현이 가능한데 동사를 사용하였다.
    @PutMapping("/members/{memberId}/update")
    // good - HTTP Method로 표현이 불가능하여 동사를 사용하였다.
    @PostMapping("/members/join")
   ```


### 5. 리소스의 포함관계는 URL에 표현한다.
   다만 이때 해당 API는 가장 상위의 리소스와 매핑된 컨트롤러에 작성한다.\
   **무엇이 가장 상위의 리소스인지는 본 문서에 따른다.**
   ```java
    // bad - LeafController.java에 작성되어있다.
    @GetMapping("/trees/cards")
    // good - TreeController.java에 작성되어있다.
    @GetMapping("/trees/1/leafs")
   ```

### 6. 리소스의 상태를 필터링해서 표현하고 싶을땐 쿼리 파라미터를 이용한다.
   ```java
    // bad - 리소스의 상태를 URL에 포함하였다.
    @GetMapping("/trees/1/leafs/recent")
    // good - 리소스의 상태를 쿼리 파라미터로 표현하였다.
    @GetMapping("/trees/1/leafs/recent=true")
   ```

### 7. 본 문서에 정의되지 않은 리소스는 URL에 표현하지 않는다.
   ```java
    // bad - 본 문서에 정의되지 않은 리소스가 URL에 포함되어 있다.
    @GetMapping("/trees/1/leafs/1/urls")
    // good - 본 문서에 정의된 리소스만 URL에 포함되어 있다.
    @GetMapping("/trees/1/leafs/1")
   ```

### 8. URL은 하이픈(-)을 사용하여 단어를 구분한다.
   ```java
    // bad - 단어를 구분하는 대신 단어를 붙여서 작성하였다.
    @GetMapping("/homeTrees")
    // good - 단어를 하이픈으로 구분하여 작성하였다.
    @GetMapping("/home-trees")
   ```

### 9. URL 마지막에는 슬래시(/)를 붙이지 않는다.
   ```java
    // bad - URL 마지막에 슬래시가 붙어있다.
    @GetMapping("/trees/")
    // good - URL 마지막에 슬래시가 붙어있지 않다.
    @GetMapping("/trees")
   ```
---

## HTTP Method 정의
### GET
   - 리소스의 상태를 조회할때 사용한다.
   - 리소스의 상태를 변경하지 않아야 한다.

### POST
   - 리소스를 생성할때 사용한다.
   - 리소스의 상태를 변경할 수 있다.
   - 무엇이 해당 리소스의 POST 메서드의 의미인지는 클라이언트와 서버간의 약속에 따라 달라진다.

### PUT
   - 리소스를 새로 덮어씌우는 방식으로 수정할 때 사용한다.

### PATCH
   - 리소스의 일부만 변경하는 방식으로 수정할 때 사용한다.

### DELETE
   - 리소스를 삭제할 때 사용한다.

## 리소스(표현)명 정의

---
> 리소스란 API URL로 식별 가능한 엔티티 단위의 정보를 의미한다.\
더 나아가, **표현(Representation)은** 리소스의 현재 상태를 클라이언트의 요구에 따라 나타낸 정보를 의미한다.


### members
   - 회원 정보를 나타내는 리소스
   - 회원 정보를 생성, 조회, 수정, 삭제할 수 있다.

### trees
   - 리프(메모)들의 주제인 트리를 나타내는 리소스
   - 가능한 주제는 도서, 영감, 영상, **일상**, 기타 등이 있다.
   - 트리는 생성, 조회, 수정, 삭제할 수 있다.

### leaves
   - 리프(메모)를 나타내는 리소스
   - 리프는 트리에 속해있으며, 리프는 생성, 조회, 수정할 수 있다.
   - **리프는 삭제할 수 없다.**

### tags
   - 리프에 대한 태그를 나타내는 리소스
   - 태그는 생성, 조회, 수정, 삭제할 수 있다.

### memoirs
   - 도서가 주제인 트리에 대한 회원의 회고록을 나타내는 리소스
   - 회고록은 생성, 조회, 수정, 삭제할 수 있다.

### aladin-books
   - 도서를 나타내는 리소스
   - 조회, 저장할 수 있다.

### member-books
   - 회원이 생성한 도서를 나타내는 리소스
   - 생성, 조회할 수 있다.

### seeds
   - 트리의 주제를 나타내는 리소스

### oauth
   - OAuth 인증을 위한 리소스
   - OAuth 인증을 위한 URL을 제공한다.

### images
   - 이미지 리소스

### files 
   - 파일 리소스
   
### comments
- 특정 리소스에 대한 댓글 상태를 나타내는 하위 리소스

### likes
- 특정 리소스에 대한 좋아요 상태를 나타내는 하위 리소스

### alarms
- 특정 리소스에 대한 알람 상태를 나타내는 하위 리소스

### detail
   - 상세 정보를 나타내는 특정 리소스의 하위 리소스
   - 상세 정보는 쿼리 파라미터를 통해 선택할 수 있다.
   ```java
    // bad - 상세 정보를 URL에 포함하였다.
    @GetMapping("/trees/1/leafs/1/first")
    // good - 상세 정보를 쿼리 파라미터로 표현하였다.
    @GetMapping("/trees/1/leafs/1?detail?first=true")
   ```

### cards
   - 카드 형태로 출력되는 특정 리소스의 하위 리소스
   - 트리, 리프, 회고록은 카드 형태로 출력될 수 있다.

### search-result
   - 검색 결과를 나타내는 리소스
   - 검색 결과는 트리, 리프, 회고록, 도서 등이 될 수 있다.

### test
   - 테스트를 위한 표현입니다.

## URL 정의

---
> 다음의 수정사항들은 버전 1.0.0을 확정하기 위한 **제안**입니다.\
> <strong style="color:#BA0C0C">아직 확정된 API URL이 아님을 밝힙니다</strong>

### TestController.java
- 기존\
`@GetMapping("/test")`
<b>
<br>
- v1.0.0\
`@GetMapping("/test")`
> 테스트용 API URL

### AladinController.java
- 기존\
`@RequestMapping("aladin")
@PostMapping`
  <br>
  <br>
- v1.0.0\
`@PostMapping("/aladin-books")`
> 설명 추가


### BookCategoryController.java
- 기존\
`@RequestMapping("book-categories")
@GetMapping`
  <br>
  <br>
- v1.0.0\
> 설명 추가



### BookController.java
- 기존\
  `@RequestMapping("books")`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping("books")`

> 설명 추가

- 기존\
  `@GetMapping`
  <br>
  <br>
- v1.0.0\
  `@GetMapping`

> 설명 추가

- 기존\
  `@PutMapping("/{bookId}")`
  <br>
  <br>
- v1.0.0\
  `@PutMapping("/{bookId}")`

> 설명 추가

- 기존\
  `@GetMapping("/{bookId}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/{bookId}")`

> 설명 추가

- 기존\
  `@GetMapping("/{bookId}/info")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/{bookId}/info")`

> 설명 추가

- 기존\
  `@GetMapping("/tree/{treeId}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/tree/{treeId}")`

> 설명 추가


### ImageController.java
- 기존\
  `@RequestMapping("images")`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping("images")`

> 설명 추가

- 기존\
  `@GetMapping("/{domain}/{fileName}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/{domain}/{fileName}")`

> 설명 추가


### LeafBookController.java
- 기존\
  `@RequestMapping`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping`

> 설명 추가

- 기존\
  `@PostMapping("/book/first/leaves")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/book/first/leaves")`

> 설명 추가

- 기존\
  `@PostMapping("/book/leaves/{parentLeafId}")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/book/leaves/{parentLeafId}")`

> 설명 추가

- 기존\
  `@PutMapping("/book/leaves/{leafId}")`
  <br>
  <br>
- v1.0.0\
  `@PutMapping("/book/leaves/{leafId}")`

> 설명 추가

- 기존\
  `@DeleteMapping("/book/leaves/{leafId}")`
  <br>
  <br>
- v1.0.0\
  `@DeleteMapping("/book/leaves/{leafId}")`

> 설명 추가

- 기존\
  `@GetMapping("/book/leaves/{leafId}/edit")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/book/leaves/{leafId}/edit")`

> 설명 추가

 
### LeafController.java
- 기존\
  `@GetMapping("/leaves/search")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/leaves/search")`

> 설명 추가

- 기존\
  `@GetMapping("/leaves/{leafId}/branch")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/leaves/{leafId}/branch")`

> 설명 추가

- 기존\
  `@GetMapping("/leaves/{leafId}/all")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/leaves/{leafId}/all")`

> 설명 추가

- 기존\
  `@GetMapping("/leaves/{leafId}/end")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/leaves/{leafId}/end")`

> 설명 추가

- 기존\
  `@GetMapping("/book/leaves/{leafId}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/book/leaves/{leafId}")`

> 설명 추가

- 기존\
  `@GetMapping("/etc/leaves/{leafId}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/etc/leaves/{leafId}")`

> 설명 추가

 
### LeafEtcController.java
- 기존\
  `@RequestMapping`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping`

> 설명 추가

- 기존\
  `@PostMapping("/etc/first/leaves")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/etc/first/leaves")`

> 설명 추가

- 기존\
  `@PostMapping("/etc/leaves/{parentLeafId}")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/etc/leaves/{parentLeafId}")`

> 설명 추가

- 기존\
  `@PutMapping("/etc/leaves/{leafId}")`
  <br>
  <br>
- v1.0.0\
  `@PutMapping("/etc/leaves/{leafId}")`

> 설명 추가

- 기존\
  `@DeleteMapping("/etc/leaves/{leafId}")`
  <br>
  <br>
- v1.0.0\
  `@DeleteMapping("/etc/leaves/{leafId}")`

> 설명 추가

- 기존\
  `@GetMapping("/etc/leaves/{leafId}/edit")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/etc/leaves/{leafId}/edit")`

> 설명 추가


 
### LeafImageController.java
- 기존\
  `@RequestMapping`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping`

> 설명 추가

- 기존\
  `@PostMapping("/leaf/image-upload")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/leaf/image-upload")`

> 설명 추가

- 기존\
  `@GetMapping`
  <br>
  <br>
- v1.0.0\
  `@GetMapping`

> 설명 추가

 
### LeafTagController.java
- 기존\
  `@RequestMapping("tags")`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping("tags")`

> 설명 추가

- 기존\
  `@PostMapping`
  <br>
  <br>
- v1.0.0\
  `@PostMapping`

> 설명 추가

- 기존\
  `@PutMapping("/{tagId}")`
  <br>
  <br>
- v1.0.0\
  `@PutMapping("/{tagId}")`

> 설명 추가

- 기존\
  `@DeleteMapping("/{tagId}")`
  <br>
  <br>
- v1.0.0\
  `@DeleteMapping("/{tagId}")`

> 설명 추가

- 기존\
  `@GetMapping("/{tagId}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/{tagId}")`

> 설명 추가

- 기존\
  `@GetMapping`
  <br>
  <br>
- v1.0.0\
  `@GetMapping`

> 설명 추가


 
### AuthController.java
- 기존\
  `@RequestMapping(value = "/auth", produces = "application/json")`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping(value = "/auth", produces = "application/json")`

> 설명 추가

- 기존\
  `@PostMapping("/oauthGoogle")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/oauthGoogle")`

> 설명 추가

- 기존\
  `@PostMapping("/oauthNaver")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping


 
### MemberController.java
- 기존\
  `@RequestMapping("/members")`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping("/members")`

> 설명 추가

- 기존\
  `@PostMapping("/join/send-email")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/join/send-email")`

> 설명 추가

- 기존\
  `@PostMapping("/join/check-email")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/join/check-email")`

> 설명 추가

- 기존\
  `@PostMapping("/join/check-token")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/join/check-token")`

> 설명 추가

- 기존\
  `@PostMapping("/password/check-email")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/password/check-email")`

> 설명 추가

- 기존\
  `@PostMapping("/join")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/join")`

> 설명 추가

### MemoirController.java
- 기존\
  `@RequestMapping("memoirs")`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping("memoirs")`

> 설명 추가

- 기존\
  `@GetMapping("{id}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("{id}")`

> 설명 추가

- 기존\
  `@PostMapping("{id}")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("{id}")`

> 설명 추가

- 기존\
  `@PutMapping("{id}")`
  <br>
  <br>
- v1.0.0\
  `@PutMapping("{id}")`

> 설명 추가

- 기존\
  `@DeleteMapping("{id}")`
  <br>
  <br>
- v1.0.0\
  `@DeleteMapping("{id}")`

> 설명 추가


 
### MemoirImageController.java
- 기존\
  `@RequestMapping("memoir-image")`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping("memoir-image")`

> 설명 추가

- 기존\
  `@PostMapping("upload-tmp")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("upload-tmp")`

> 설명 추가

- 기존\
  `@GetMapping(value = "return-byte", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})`
  <br>
  <br>
- v1.0.0\
  `@GetMapping(value = "return-byte", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})`

> 설명 추가

- 기존\
  `@GetMapping(value = {"path-tmp", "path-memoir"})`
  <br>
  <br>
- v1.0.0\
  `@GetMapping(value = {"path-tmp", "path-memoir"})`

> 설명 추가

 
### SeedController.java
- 기존\
  `@RequestMapping("/seeds")`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping("/seeds")`

> 설명 추가

- 기존\
  `@GetMapping`
  <br>
  <br>
- v1.0.0\
  `@GetMapping`

> 설명 추가

- 기존\
  `@GetMapping("{seedId}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("{seedId}")`

> 설명 추가

- 기존\
  `@GetMapping("/trees/{treeId}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/trees/{treeId}")`

> 설명 추가

 
### TreeController.java
- 기존\
  `@RequestMapping("/trees")`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping("/trees")`

> 설명 추가

- 기존\
  `@GetMapping("/search")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/search")`

> 설명 추가

- 기존\
  `@GetMapping("/search/result/{treeSearchText}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/search/result/{treeSearchText}")`

> 설명 추가

- 기존\
  `@PostMapping("/search/result/{treeSearchText}")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/search/result/{treeSearchText}")`

> 설명 추가

- 기존\
  `@GetMapping("{id}/info")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("{id}/info")`

> 설명 추가

- 기존\
  `@GetMapping("leaves/{leafId}/info")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("leaves/{leafId}/info")`

> 설명 추가

- 기존\
  `@GetMapping("/{treeId}/branches")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/{treeId}/branches")`

> 설명 추가

- 기존\
  `@GetMapping("/{treeId}/leafs")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/{treeId}/leafs")`

> 설명 추가

- 기존\
  `@GetMapping("tree-home")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("tree-home")`

> 설명 추가

- 기존\
  `@GetMapping("tree-home-sort")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("tree-home-sort")`

> 설명 추가

- 기존\
  `@GetMapping("/members/{memberId}/trees/book/cards")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/members/{memberId}/trees/book/cards")`

> 설명 추가

- 기존\
  `@GetMapping("members/books/{bookId}/trees/cards")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("members/books/{bookId}/trees/cards")`

> 설명 추가

- 기존\
  `@GetMapping("books/{bookId}/trees/cards")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("books/{bookId}/trees/cards")`

> 설명 추가

- 기존\
  `@PutMapping("etc/edit")`
  <br>
  <br>
- v1.0.0\
  `@PutMapping("etc/edit")`

> 설명 추가

- 기존\
  `@PutMapping("book/edit")`
  <br>
  <br>
- v1.0.0\
  `@PutMapping("book/edit")`

> 설명 추가

- 기존\
  `@DeleteMapping("/{treeId}")`
  <br>
  <br>
- v1.0.0\
  `@DeleteMapping("/{treeId}")`

> 설명 추가


 
### TreeTmpController.java
- 기존\
  `@RequestMapping("/trees")`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping("/trees")`

> 설명 추가

- 기존\
  `@PostMapping`
  <br>
  <br>
- v1.0.0\
  `@PostMapping`

> 설명 추가

- 기존\
  `@PostMapping("/etc")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/etc")`

> 설명 추가

- 기존\
  `@PostMapping("/auto")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/auto")`

> 설명 추가

- 기존\
  `@GetMapping("/tmp/{memberId}/total-page")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/tmp/{memberId}/total-page")`

> 설명 추가
