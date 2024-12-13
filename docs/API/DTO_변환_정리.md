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
    * [메소드: `public ResponseEntity<BookDetailResponse>  detail()`](#메소드-public-responseentitybookdetailresponse--detail)
    * [메소드: `public ResponseEntity<BookInfoResponse>  findBookInfo()`](#메소드-public-responseentitybookinforesponse--findbookinfo)
    * [메소드: `public ResponseEntity<BookInfoResponse>  findBookByTreeId()`](#메소드-public-responseentitybookinforesponse--findbookbytreeid)
  * [`ImageController.java`](#imagecontrollerjava)
    * [메소드: `public byte[] getImage()`](#메소드-public-byte-getimage)
  * [`LeafBookController.java`](#leafbookcontrollerjava)
    * [메소드: `public ResponseEntity<BookLeafResponse> createFirstBookLeaf()`](#메소드-public-responseentitybookleafresponse-createfirstbookleaf)
    * [메소드: `public ResponseEntity<BookLeafResponse> createBookLeaf()`](#메소드-public-responseentitybookleafresponse-createbookleaf)
    * [메소드: `public ResponseEntity<BookLeafResponse> updateBookLeaf()`](#메소드-public-responseentitybookleafresponse-updatebookleaf)
    * [메소드: `public ResponseEntity<BookLeafResponse> deleteBookLeaf()`](#메소드-public-responseentitybookleafresponse-deletebookleaf)
    * [메소드: `public ResponseEntity<BookLeafEditResponse> getEdit()`](#메소드-public-responseentitybookleafeditresponse-getedit)
  * [`LeafController.java`](#leafcontrollerjava)
    * [메소드: `public Page<LeafSearchResultResponse> searchLeaves()`](#메소드-public-pageleafsearchresultresponse-searchleaves)
    * [메소드: `public ResponseEntity<LeafBranchInfoResponse> getLeafBranch()`](#메소드-public-responseentityleafbranchinforesponse-getleafbranch)
    * [메소드: `public ResponseEntity<LeafItemResponse> getLeafNodesRootToEnd()`](#메소드-public-responseentityleafitemresponse-getleafnodesroottoend)
    * [메소드: `public ResponseEntity<LeafItemToEndResponse> getLeafNodesToEnd()`](#메소드-public-responseentityleafitemtoendresponse-getleafnodestoend)
    * [메소드: `public ResponseEntity<LeafBookDetailResponse> getLeafBookDetail()`](#메소드-public-responseentityleafbookdetailresponse-getleafbookdetail)
    * [메소드: `public ResponseEntity<LeafEtcDetailResponse> getEtcLeafDetail()`](#메소드-public-responseentityleafetcdetailresponse-getetcleafdetail)
    * [메소드: `public ResponseEntity<LeafBreadcrumbResponse> getLeafBreadcrumb()`](#메소드-public-responseentityleafbreadcrumbresponse-getleafbreadcrumb)
    * [메소드: `public ResponseEntity<LeafBeforeNodeInfoResponse> getLeafBefore()`](#메소드-public-responseentityleafbeforenodeinforesponse-getleafbefore)
    * [메소드: `public ResponseEntity<LeafBookCardResponse> getLeafBookCards()`](#메소드-public-responseentityleafbookcardresponse-getleafbookcards)
    * [메소드: `public ResponseEntity<LeafCardResponse> getLeafCards()`](#메소드-public-responseentityleafcardresponse-getleafcards)
    * [메소드: `public ResponseEntity<LeafCardResponse> getLeafCardsByTag()`](#메소드-public-responseentityleafcardresponse-getleafcardsbytag)
    * [메소드: `public ResponseEntity<LeafSeedResponse> getLeafSeed()`](#메소드-public-responseentityleafseedresponse-getleafseed)
    * [메소드: `public ResponseEntity<Integer> getBookPage()`](#메소드-public-responseentityinteger-getbookpage)
    * [메소드: `public ResponseEntity<LeafDetailResponse> getLeafDetail()`](#메소드-public-responseentityleafdetailresponse-getleafdetail)
    * [메소드: `public ResponseEntity<MemberInfoResponse> getMemberInfo()`](#메소드-public-responseentitymemberinforesponse-getmemberinfo)
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
- 필요없는 API 이므로 삭제

### 메소드: `public ResponseEntity<BookDetailResponse>  detail()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `detail()`
    - After 메소드명: `detail()`
- 요청
    - Before 요청 DTO: @PathVariable Long id
    - After 요청 DTO: @PathVariable Long id
- 응답
    - Before 응답 DTO: BookDetailResponse
    - After 응답 DTO: GetBookDetailResponse

### 메소드: `public ResponseEntity<BookInfoResponse>  findBookInfo()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `findBookInfo()`
    - After 메소드명: `getBookInfoByBookId()`
- 요청
    - Before 요청 DTO: @PathVariable Long bookId
    - After 요청 DTO: @PathVariable Long bookId
- 응답
    - Before 응답 DTO: BookInfoResponse
    - After 응답 DTO: GetBookInfoResponse

### 메소드: `public ResponseEntity<BookInfoResponse>  findBookByTreeId()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `findBookByTreeId()`
    - After 메소드명: `getBookInfoByTreeId()`
- 요청
    - Before 요청 DTO: @PathVariable Long treeId
    - After 요청 DTO: @PathVariable Long treeId
- 응답
    - Before 응답 DTO: BookInfoResponse
    - After 응답 DTO: GetBookInfoResponse

## `ImageController.java`

### 메소드: `public byte[] getImage()`

- 변동 사항: 없음
- 메소드 명
    - Before 메소드명: `getImage()`
    - After 메소드명: `getImage()`
- 요청
    - Before 요청 DTO: @PathVariable String domain, @PathVariable String fileName
    - After 요청 DTO: @PathVariable String domain, @PathVariable String fileName
- 응답
    - Before 응답 DTO: byte[]
    - After 응답 DTO: byte[]

## `LeafBookController.java`

### 메소드: `public ResponseEntity<BookLeafResponse> createFirstBookLeaf()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `createFirstBookLeaf()`
    - After 메소드명: `createBookLeafFirst()`
- 요청
    - Before 요청 DTO: @RequestBody BookLeafRequest
    - After 요청 DTO: @RequestBody CreateBookLeafCommand
- 응답
    - Before 응답 DTO: BookLeafResponse
    - After 응답 DTO: CreateBookLeafResponse

### 메소드: `public ResponseEntity<BookLeafResponse> createBookLeaf()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `createBookLeaf()`
    - After 메소드명: `createBookLeaf()`
- 요청
    - Before 요청 DTO: @RequestBody BookLeafRequest, @PathVariable Long parentId
    - After 요청 DTO: @RequestBody CreateBookLeafCommand
- 응답
    - Before 응답 DTO: BookLeafResponse
    - After 응답 DTO: CreateBookLeafResponse

### 메소드: `public ResponseEntity<BookLeafResponse> updateBookLeaf()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `updateBookLeaf()`
    - After 메소드명: `updateBookLeaf()`
- 요청
    - Before 요청 DTO: @RequestBody BookLeafRequest, @PathVariable Long leafId
    - After 요청 DTO: @RequestBody UpdateBookLeafCommand
- 응답
    - Before 응답 DTO: BookLeafResponse
    - After 응답 DTO: UpdateBookLeafResponse

### 메소드: `public ResponseEntity<BookLeafResponse> deleteBookLeaf()`

- 변동 사항: 메소드 제거 (사용하지 않는 API)

### 메소드: `public ResponseEntity<BookLeafEditResponse> getEdit()`

- 변동 사항: 메소드 제거 (중복으로 존재하는 API)

## `LeafController.java`

### 메소드: `public Page<LeafSearchResultResponse> searchLeaves()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `searchLeaves()`
    - After 메소드명: `listLeaves()`
- 요청
    - Before 요청 DTO: @ModelAttribute LeafSearchQuery
    - After 요청 DTO: ListLeafFilterCommand
- 응답
    - Before 응답 DTO: Page<LeafSearchResultResponse>
    - After 응답 DTO: ListLeafFilterResponse

### 메소드: `public ResponseEntity<LeafBranchInfoResponse> getLeafBranch()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getLeafBranch()`
    - After 메소드명: `getLeafBranchInfo()`
- 요청
    - Before 요청 DTO: @PathVariable Long leafId
    - After 요청 DTO: @PathVariable Long leafId
- 응답
    - Before 응답 DTO: LeafBranchInfoResponse
    - After 응답 DTO: GetLeafBranchInfoResponse

### 메소드: `public ResponseEntity<LeafItemResponse> getLeafNodesRootToEnd()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getLeafNodesRootToEnd()`
    - After 메소드명: `listLeafRootToEnd()`
- 요청
    - Before 요청 DTO: @PathVariable Long leafId
    - After 요청 DTO: @PathVariable Long leafId
- 응답
    - Before 응답 DTO: LeafItemResponse
    - After 응답 DTO: ListLeafRootToEndResponse

### 메소드: `public ResponseEntity<LeafItemToEndResponse> getLeafNodesToEnd()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getLeafNodesToEnd()`
    - After 메소드명: `listLeafToEnd()`
- 요청
    - Before 요청 DTO: @PathVariable Long leafId
    - After 요청 DTO: @PathVariable Long leafId
- 응답
    - Before 응답 DTO: LeafItemToEndResponse
    - After 응답 DTO: ListLeafToEndResponse

### 메소드: `public ResponseEntity<LeafBookDetailResponse> getLeafBookDetail()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getLeafBookDetail()`
    - After 메소드명: `getLeafBookDetail()`
- 요청
    - Before 요청 DTO: @PathVariable Long leafId
    - After 요청 DTO: @PathVariable Long leafId
- 응답
    - Before 응답 DTO: LeafBookDetailResponse
    - After 응답 DTO: GetLeafBookDetailResponse

### 메소드: `public ResponseEntity<LeafEtcDetailResponse> getEtcLeafDetail()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getEtcLeafDetail()`
    - After 메소드명: `getLeafEtcDetail()`
- 요청
    - Before 요청 DTO: @PathVariable Long leafId
    - After 요청 DTO: @PathVariable Long leafId
- 응답
    - Before 응답 DTO: LeafEtcDetailResponse
    - After 응답 DTO: GetLeafEtcDetailResponse

### 메소드: `public ResponseEntity<LeafBreadcrumbResponse> getLeafBreadcrumb()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getLeafBreadcrumb()`
    - After 메소드명: `getLeafBreadcrumb()`
- 요청
    - Before 요청 DTO: @PathVariable Long leafId
    - After 요청 DTO: @PathVariable Long leafId
- 응답
    - Before 응답 DTO: LeafBreadcrumbResponse
    - After 응답 DTO: GetLeafBreadcrumbResponse

### 메소드: `public ResponseEntity<LeafBeforeNodeInfoResponse> getLeafBefore()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getLeafBefore()`
    - After 메소드명: `getLeafBeforeNodeInfo()`
- 요청
    - Before 요청 DTO: @PathVariable Long leafId
    - After 요청 DTO: @PathVariable Long leafId
- 응답
    - Before 응답 DTO: LeafBeforeNodeInfoResponse
    - After 응답 DTO: GetLeafBeforeNodeInfoResponse

### 메소드: `public ResponseEntity<LeafBookCardResponse> getLeafBookCards()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getLeafBookCards()`
    - After 메소드명: `listLeafBookCards()`
- 요청
    - Before 요청 DTO: @PathVariable Long leafId, @RequestParam int page, @RequestParam int size
    - After 요청 DTO: @PathVariable Long leafId
- 응답
    - Before 응답 DTO: LeafBookCardResponse
    - After 응답 DTO: ListLeafBookCardResponse
  
### 메소드: `public ResponseEntity<LeafCardResponse> getLeafCards()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getLeafCards()`
    - After 메소드명: `listLeafCards()`
- 요청
    - Before 요청 DTO: @PathVariable Long leafId, @RequestParam int page, @RequestParam int size
    - After 요청 DTO: @PathVariable Long leafId
- 응답
    - Before 응답 DTO: LeafCardResponse
    - After 응답 DTO: ListLeafCardResponse
  
### 메소드: `public ResponseEntity<LeafCardResponse> getLeafCardsByTag()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getLeafCardsByTag()`
    - After 메소드명: `listLeafCardsByTag()`
- 요청
    - Before 요청 DTO: @PathVariable Long tagId, @RequestParam int page, @RequestParam int size
    - After 요청 DTO: @PathVariable Long tagId
- 응답
    - Before 응답 DTO: LeafCardResponse
    - After 응답 DTO: ListLeafCardResponse

### 메소드: `public ResponseEntity<LeafSeedResponse> getLeafSeed()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getLeafSeed()`
    - After 메소드명: `getLeafSeed()`
- 요청
    - Before 요청 DTO: @PathVariable Long leafId
    - After 요청 DTO: @PathVariable Long leafId
- 응답
    - Before 응답 DTO: LeafSeedResponse
    - After 응답 DTO: GetLeafSeedResponse

### 메소드: `public ResponseEntity<Integer> getBookPage()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getBookPage()`
    - After 메소드명: `getBookPage()`
- 요청
    - Before 요청 DTO: @PathVariable Long leafId
    - After 요청 DTO: @PathVariable Long leafId
- 응답
    - Before 응답 DTO: Integer
    - After 응답 DTO: GetBookPageResponse

### 메소드: `public ResponseEntity<LeafDetailResponse> getLeafDetail()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getLeafDetail()`
    - After 메소드명: `getLeafDetail()`
- 요청
    - Before 요청 DTO: @PathVariable Long leafId
    - After 요청 DTO: @PathVariable Long leafId
- 응답
    - Before 응답 DTO: LeafDetailResponse
    - After 응답 DTO: GetLeafDetailResponse

### 메소드: `public ResponseEntity<MemberInfoResponse> getMemberInfo()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `getMemberInfo()`
    - After 메소드명: `getMemberInfo()`
- 요청
    - Before 요청 DTO: @PathVariable Long leafId
    - After 요청 DTO: @PathVariable Long leafId
- 응답
    - Before 응답 DTO: MemberInfoResponse
    - After 응답 DTO: GetMemberInfoResponse

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