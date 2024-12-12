# DTO 스펙 정의

> 마지막 수정자: 한태규\
> 마지막 수정 날짜: 2024-12-12

현재 정의 되어 있는 DTO 클래스명과 필드명의 규칙을 정리한다.

---

## 목차

<!-- TOC -->
* [DTO 스펙 정의](#dto-스펙-정의)
  * [목차](#목차)
  * [`TestController.java`](#testcontrollerjava)
    * [메소드: `public String test()`](#메소드-public-string-test)
  * [`AladinController.java`](#aladincontrollerjava)
    * [메소드: `public ResponseEntity<AladinApiSearchResponse> search(@RequestParam String query)`](#메소드-public-responseentityaladinapisearchresponse-searchrequestparam-string-query)
  * [`BookCategoryController.java`](#bookcategorycontrollerjava)
    * [메소드: `public ResponseEntity<BookCategoryResponse> getBookCategories()`](#메소드-public-responseentitybookcategoryresponse-getbookcategories)
  * [`BookController.java`](#bookcontrollerjava)
    * [메소드: `public ResponseEntity<BookListResponse> getList()`](#메소드-public-responseentitybooklistresponse-getlist)
    * [메소드: `public ResponseEntity<BookResponse> modify()`](#메소드-public-responseentitybookresponse-modify)
  * [`ImageController.java`](#imagecontrollerjava)
  * [`LeafBookController.java`](#leafbookcontrollerjava)
  * [`LeafController.java`](#leafcontrollerjava)
  * [`LeafEtcController.java`](#leafetccontrollerjava)
  * [`LeafImageController.java`](#leafimagecontrollerjava)
  * [`LeafTagController.java`](#leaftagcontrollerjava)
  * [`MainController.java`](#maincontrollerjava)
  * [`AuthController.java`](#authcontrollerjava)
  * [`MemberController.java`](#membercontrollerjava)
  * [`MemoirController.java`](#memoircontrollerjava)
  * [`MemoirImageController.java`](#memoirimagecontrollerjava)
  * [`SeedController.java`](#seedcontrollerjava)
  * [`TreeController.java`](#treecontrollerjava)
  * [`TreeTmpController.java`](#treetmpcontrollerjava)
<!-- TOC -->

---

## `TestController.java`

### 메소드: `public String test()`

- 변동 사항: 없음
- 요청
    - Before 요청 DTO: null
    - After 요청 DTO: null
- 응답
    - Before 응답 DTO: String
    - After 응답 DTO: String

## `AladinController.java`

### 메소드: `public ResponseEntity<AladinApiSearchResponse> search(@RequestParam String query)`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `search()`
    - After 메소드명: `search()`
- 요청
    - Before 요청 DTO: `String`
    - After 요청 DTO: `AladinApiSearchCommand`
- 응답
    - Before 응답 DTO: `AladinApiSearchResponse`
    - After 응답 DTO: `AladinApiSearchResponse`

## `BookCategoryController.java`

### 메소드: `public ResponseEntity<BookCategoryResponse> getBookCategories()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getBookCategories()`
    - After 메소드명: `list()`
- 요청
    - Before 요청 DTO: String query, int page, int size
    - After 요청 DTO: ListBookCategoryFilterCommand
- 응답
    - Before 응답 DTO: BookCategoryResponse
        - Before 응답 내부 클래스 DTO: BookCategoryDto
    - After 응답 DTO: ListBookCategoryResponse
        - After 응답 내부 클래스 DTO: BookCategoryDto

## `BookController.java`

### 메소드: `public ResponseEntity<BookListResponse> getList()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getList()`
    - After 메소드명: `list()`
- 요청
    - Before 요청 DTO: String query, String filter, int page, int size
    - After 요청 DTO: ListBookFilterCommand
- 응답
    - Before 응답 DTO: BookListResponse
        - Before 응답 내부 클래스 DTO: BookDto
    - After 응답 DTO: ListBookResponse
        - After 응답 내부 클래스 DTO: BookDto

### 메소드: `public ResponseEntity<BookResponse> modify()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `modify()`
    - After 메소드명: `update()`
- 요청
    - Before 요청 DTO: Long id, BookRequest bookRequest, MultiPartFile image
    - After 요청 DTO: Long id, BookUpdateCommand bookUpdateCommand, MultipartFile image
- 응답
    - Before 응답 DTO: BookResponse
    - After 응답 DTO: BookUpdateResponse

## `ImageController.java`

## `LeafBookController.java`

## `LeafController.java`

## `LeafEtcController.java`

## `LeafImageController.java`

## `LeafTagController.java`

## `MainController.java`

## `AuthController.java`

## `MemberController.java`

## `MemoirController.java`

## `MemoirImageController.java`

## `SeedController.java`

## `TreeController.java`

## `TreeTmpController.java`