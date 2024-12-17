# API URL 정의

> 관리자 : 조현진\
> 마지막 수정자: 조현진\
> 마지막 수정 날짜: 2024-12-17\
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
    * [10. 쿼리 파라미터와 경로 변수 사용법](#10-쿼리-파라미터와-경로-변수-사용법)
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
    * [tags](#tags)
    * [memoirs](#memoirs)
    * [books](#books)
    * [book-categories](#book-categories)
    * [aladin-books](#aladin-books)
    * [member-books](#member-books)
    * [seeds](#seeds)
    * [oauth](#oauth)
    * [images](#images)
    * [files](#files-)
    * [comments](#comments)
    * [likes](#likes)
    * [alarms](#alarms)
    * [details](#details)
    * [list](#list)
    * [cards](#cards)
    * [search-results](#search-results)
    * [test](#test)
  * [URL 정의](#url-정의)
    * [TestController](#testcontroller)
    * [AladinController](#aladincontroller)
    * [BookCategoryController](#bookcategorycontroller)
    * [BookController](#bookcontroller)
    * [ImageController](#imagecontroller)
    * [LeafBookController](#leafbookcontroller)
    * [LeafController](#leafcontroller)
    * [LeafEtcController](#leafetccontroller)
    * [LeafImageController](#leafimagecontroller)
    * [LeafTagController](#leaftagcontroller)
    * [AuthController](#authcontroller)
    * [MemberController](#membercontroller)
    * [MemoirController](#memoircontroller)
    * [MemoirImageController](#memoirimagecontroller)
    * [SeedController](#seedcontroller)
    * [TreeController](#treecontroller)
    * [TreeTmpController](#treetmpcontroller)
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

### 10. 쿼리 파라미터와 경로 변수 사용법
- 경로 변수는 리소스의 식별자(정적)를 나타낸다.
- 쿼리 파라미터는 리소스의 현재 상태(동적)를 나타낸다.
  ```java
   // bad - 리소스를 식별하기 위해 쿼리 파라미터를 이용했다.
   @GetMapping("/trees?treeId=1")
   // good - 리소스를 식별하기 위해 경로 변수를 이용했다.
   @GetMapping("/trees/{treeId})
  ```

## HTTP Method 정의

---
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

### trees-etc
   - 아직 별도의 리소스로 분류되지 않은 주제(seed)를 나타내는 리소스

### tags
   - 리프에 대한 태그를 나타내는 리소스
   - 태그는 생성, 조회, 수정, 삭제할 수 있다.

### memoirs
   - 도서가 주제인 트리에 대한 회원의 회고록을 나타내는 리소스
   - 회고록은 생성, 조회, 수정, 삭제할 수 있다.

### books
   - 도서 일반에 대한 리소스 

### book-categories
   - 도서의 카테고리를 나타내는 리소스
   - 카테고리는 조회할 수 있다.

### aladin-books
   - 알라딘에서 조회한 도서를 나타내는 리소스
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

### details
   - 상세 정보를 나타내는 특정 리소스의 하위 리소스
   - 상세 정보는 쿼리 파라미터를 통해 선택할 수 있다.
   ```java
    // bad - 상세 정보를 URL에 포함하였다.
    @GetMapping("/trees/1/leafs/1/first")
    // good - 상세 정보를 쿼리 파라미터로 표현하였다.
    @GetMapping("/trees/1/leafs/1?detail?first=true")
   ```

### list
   - 리스트 형태로 출력되는 특정 리소스의 하위 리소스
   - 리스트 정렬 방법은 쿼리 파라미터를 통해 선택할 수 있다.

### cards
   - 카드 형태로 출력되는 특정 리소스의 하위 리소스
   - 트리, 리프, 회고록은 카드 형태로 출력될 수 있다.

### search-results
   - 검색 결과를 나타내는 리소스
   - 검색 결과는 트리, 리프, 회고록, 도서 등이 될 수 있다.

### test
   - 테스트를 위한 표현입니다.

## URL 정의

---
> 다음의 수정사항들은 버전 1.0.0을 확정하기 위한 **제안**입니다.\
> <strong style="color:#BA0C0C">아직 확정된 API URL이 아님을 밝힙니다</strong>

### TestController
- 기존\
`@GetMapping("/test")`
<b>
<br>
- v1.0.0\
`@GetMapping("/test")`
> 테스트용 API URL

### AladinController
- 기존\
`@RequestMapping("aladin")
@PostMapping`
  <br>
  <br>
- v1.0.0\
`@PostMapping("/aladin-books")`
> 검토 필요


### BookCategoryController
- 기존\
`@RequestMapping("book-categories")
@GetMapping`
  <br>
  <br>
- v1.0.0\
`@GetMapping("/book-categories/{booksId}")`
> 도서의 카테고리 조회\
> 검토 필요

### BookController
- 기존\
  `@RequestMapping("books")`
  <br>
  <br>
- v1.0.0\
  제거

> 도서 컨트롤러의 상위 URL\
> 알라딘 도서와 회원 도서를 구분하기 위해 제거

- 기존\
  `@GetMapping`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("books/list")`

> 쿼리 파라미터를 이용한 도서 리스트 조회 API\
> 사용 가능한 쿼리 파라미터 목록

- 기존\
  `@PutMapping("/{bookId}")`
  <br>
  <br>
- v1.0.0\
  `@PutMapping("member-books/{bookId}")`

> 사용자 정의 도서의 수정 API

- 기존\
  `@GetMapping("books/{bookId}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("books/{bookId}")`

> 도서 상세 정보 조회 API.

- 기존\
  `@GetMapping("/{bookId}/info")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("books/{bookId}/details")`

> 특정 도서 상세 정보 조회 API.\
> 사용 가능한 쿼리 파라미터 목록

- 기존\
  `@GetMapping("books/tree/{treeId}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("books/tree/{treeId}")`

> 해당 도서와 연관된 트리 조회 API\
> 결과가 리스트로 나올텐데, 기존에 사용된 API가 적절한지 검토 필요


### ImageController
- 기존\
  `@RequestMapping("images")`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping("images")`

> 이미지 컨트롤러의 상위 URL

- 기존\
  `@GetMapping("/{domain}/{fileName}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/{domain}/{fileName}")`

> domain과 fileName을 이용한 이미지 조회 API


### LeafBookController
- 기존\
  `@RequestMapping`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping`

> 검토 필요\
> books/leaves 형식으로 LeafController에 포함시킬지 검토 필요

- 기존\
  `@PostMapping("/book/first/leaves")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("books/leaves/details")`

> 검토 필요\
> 사용 가능한 쿼리 파라미터 목록
> 1. first - 첫 리프 등록 
> 2. parentLeafId - 부모 리프 등록

- 기존\
  `@PostMapping("books/leaves/{parentLeafId}")`
  <br>
  <br>
- v1.0.0\
  제거

> 검토 필요

- 기존\
  `@PutMapping("/book/leaves/{leafId}")`
  <br>
  <br>
- v1.0.0\
`@PutMapping("books/leaves/details")`

> 검토 필요

- 기존\
  `@DeleteMapping("/book/leaves/{leafId}")`
  <br>
  <br>
- v1.0.0\
  `@DeleteMapping("books/leaves/{leafId}")`

> 검토 필요

- 기존\
  `@GetMapping("/book/leaves/{leafId}/edit")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("books/leaves/{leafId}/edit")`

> 수정 페이지용 데이터 조회
> 검토 필요

 
### LeafController
- 기존\
  `@GetMapping("/leaves/search")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/leaves/search-results")`

> 리프 리스트 조회용 API
> 사용 가능한 쿼리 파라미터
> 1. keyword - 검색어

- 기존\
  `@GetMapping("/leaves/{leafId}/branch")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/leaves/{leafId}/details")`

> 리프 상세 정보 조회용 API
> 사용 가능한 쿼리 파라미터
> 1. branch - 브랜치 조회 
> 2. all - 전체 조회
> 3. end - 마지막 리프 조회

- 기존\
  `@GetMapping("/leaves/{leafId}/all")`
  <br>
  <br>
- v1.0.0\
  제거

> 설명 추가

- 기존\
  `@GetMapping("/leaves/{leafId}/end")`
  <br>
  <br>
- v1.0.0\
  제거

> 설명 추가

- 기존\
  `@GetMapping("/books/leaves/{leafId}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/leaves/{leafId}")`

> 리프 상세 조회용 API
> 사용 가능한 쿼리 파라미터
> 1. book - 도서와 관련있는 리프 조회
> 2. etc - 도서와 관련없는 리프 조회

- 기존\
  `@GetMapping("/etc/leaves/{leafId}")`
  <br>
  <br>
- v1.0.0\
  제거

> 설명 추가

 
### LeafEtcController
- 기존\
  `@RequestMapping`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping("etc-trees")`

> 설명 추가

- 기존\
  `@PostMapping("/etc/first/leaves")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/leaves")`

> 주제가 도서가 아닌 트리의 리프 등록
> 사용 가능한 쿼리 파라미터
> 1. first - 첫 리프 등록
> 2. parentLeafId - 부모 리프 등록
> 3. leafId - 리프 수정

- 기존\
  `@PostMapping("/etc/leaves/{parentLeafId}")`
  <br>
  <br>
- v1.0.0\
  제거

> 설명 추가

- 기존\
  `@PutMapping("/etc/leaves/{leafId}")`
  <br>
  <br>
- v1.0.0\
  제거

> 설명 추가

- 기존\
  `@DeleteMapping("/etc/leaves/{leafId}")`
  <br>
  <br>
- v1.0.0\
  제거

> 검토 필요

- 기존\
  `@GetMapping("/etc/leaves/{leafId}/edit")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/leaves/{leafId}/edit")`

> etc-trees의 리프 수정 페이지 조회용 API


 
### LeafImageController
- 기존\
  `@RequestMapping`
  <br>
  <br>
- v1.0.0\
  `@RequestMapping("leaves")`

> 설명 추가

- 기존\
  `@PostMapping("/image-upload")`
  <br>
  <br>
- v1.0.0\
  `@PostMapping("/image-upload")`

> 컨트롤 API URL 적용
> 리프 작성시 이미지 업로드 용

- 기존\
  `@GetMapping`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/image-print")`

> 컨트롤 API URL 적용
> 확인 필요

 
### LeafTagController
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

> 태그 등록용 API

- 기존\
  `@PutMapping("/{tagId}")`
  <br>
  <br>
- v1.0.0\
  `@PutMapping("/{tagId}")`

> 태그 수정용 API

- 기존\
  `@DeleteMapping("/{tagId}")`
  <br>
  <br>
- v1.0.0\
  `@DeleteMapping("/{tagId}")`

> 태그 삭제용 API

- 기존\
  `@GetMapping("/{tagId}")`
  <br>
  <br>
- v1.0.0\
  `@GetMapping("/{tagId}")`

> 태그 조회용 API

- 기존\
  `@GetMapping`
  <br>
  <br>
- v1.0.0\
  `@GetMapping`

> member의 모든 태그 리스트 조회용 API
> 사용 가능한 쿼리 파라미터 목록


 
### AuthController
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


 
### MemberController
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

### MemoirController
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


 
### MemoirImageController
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

 
### SeedController
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

 
### TreeController
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


 
### TreeTmpController
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
