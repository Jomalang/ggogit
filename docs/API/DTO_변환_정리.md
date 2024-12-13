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
    * [메소드: `public ResponseEntity<EtcLeafResponse> createFirstEtcLeaf()`](#메소드-public-responseentityetcleafresponse-createfirstetcleaf)
    * [메소드: `public ResponseEntity<EtcLeafResponse> createEtcLeaf()`](#메소드-public-responseentityetcleafresponse-createetcleaf)
    * [메소드: `public ResponseEntity<EtcLeafResponse> updateEtcLeaf()`](#메소드-public-responseentityetcleafresponse-updateetcleaf)
    * [메소드: `public ResponseEntity<EtcLeafResponse> deleteEtcLeaf()`](#메소드-public-responseentityetcleafresponse-deleteetcleaf)
    * [메소드: `public ResponseEntity<EtcLeafEditResponse> getEtcLeafEdit()`](#메소드-public-responseentityetcleafeditresponse-getetcleafedit)
  * [`LeafImageController.java`](#leafimagecontrollerjava)
    * [메소드: `public String upload()`](#메소드-public-string-upload)
    * [메소드: `public byte[] print()`](#메소드-public-byte-print)
  * [`LeafTagController.java`](#leaftagcontrollerjava)
    * [메소드: `public ResponseEntity<LeafTagResponse> register()`](#메소드-public-responseentityleaftagresponse-register)
    * [메소드: `public ResponseEntity<LeafTagResponse> modify()`](#메소드-public-responseentityleaftagresponse-modify)
    * [메소드: `public ResponseEntity<LeafTagResponse> remove()`](#메소드-public-responseentityleaftagresponse-remove)
    * [메소드: `public ResponseEntity<LeafTagDetailResponse> get()`](#메소드-public-responseentityleaftagdetailresponse-get)
    * [메소드: `public ResponseEntity<LeafTagListResponse> list()`](#메소드-public-responseentityleaftaglistresponse-list)
  * [`AuthController.java`](#authcontrollerjava)
    * [메소드: `public ResponseEntity<AuthInfoResponse> oauthGoogle()`](#메소드-public-responseentityauthinforesponse-oauthgoogle)
    * [메소드: `public ResponseEntity<AuthInfoResponse> oauthNaver()`](#메소드-public-responseentityauthinforesponse-oauthnaver)
    * [메소드: `public ResponseEntity<AuthInfoResponse> oauthKakao()`](#메소드-public-responseentityauthinforesponse-oauthkakao)
  * [`MemberController.java`](#membercontrollerjava)
    * [메소드: `public ResponseEntity<MemberSendEmailResponse> joinSendEmail()`](#메소드-public-responseentitymembersendemailresponse-joinsendemail)
    * [메소드: `public ResponseEntity<MemberCheckEmailResponse> checkEmail()`](#메소드-public-responseentitymembercheckemailresponse-checkemail)
    * [메소드: `public ResponseEntity<MemberCheckTokenResponse> checkToken()`](#메소드-public-responseentitymemberchecktokenresponse-checktoken)
    * [메소드: `public ResponseEntity<MemberCheckEmailResponse> passwordCheckEmail(_`](#메소드-public-responseentitymembercheckemailresponse-passwordcheckemail_)
    * [메소드: `public ResponseEntity<MemberLoginResponse> join()`](#메소드-public-responseentitymemberloginresponse-join)
    * [메소드: `public ResponseEntity<MemberLoginResponse> oauthJoin()`](#메소드-public-responseentitymemberloginresponse-oauthjoin)
    * [메소드: `public ResponseEntity<MemberLoginResponse> login()`](#메소드-public-responseentitymemberloginresponse-login)
    * [메소드: `public ResponseEntity<MemberRefreshResponse> refresh()`](#메소드-public-responseentitymemberrefreshresponse-refresh)
    * [메소드: `public ResponseEntity<MemberEditResponse> edit()`](#메소드-public-responseentitymembereditresponse-edit)
    * [메소드: `public ResponseEntity<MemberPasswordResetSendEmailResponse> findSendEmail()`](#메소드-public-responseentitymemberpasswordresetsendemailresponse-findsendemail)
    * [메소드: `public ResponseEntity<MemberResponse> findByNickname()`](#메소드-public-responseentitymemberresponse-findbynickname)
    * [메소드: `public ResponseEntity<MemberResponse> findByUsername()`](#메소드-public-responseentitymemberresponse-findbyusername-)
    * [메소드: `public ResponseEntity<MemberResponse> findByEmail()`](#메소드-public-responseentitymemberresponse-findbyemail)
    * [메소드: `public ResponseEntity<MemberResponse> findById()`](#메소드-public-responseentitymemberresponse-findbyid)
    * [메소드: `public ResponseEntity<MemberDomainCntResponse> countDomainId()`](#메소드-public-responseentitymemberdomaincntresponse-countdomainid)
  * [`MemoirController.java`](#memoircontrollerjava)
    * [메소드: `public ResponseEntity<MemoirResponse> getMemoir()`](#메소드-public-responseentitymemoirresponse-getmemoir)
    * [메소드: `public ResponseEntity<MemoirDto> createMemoirResponse()`](#메소드-public-responseentitymemoirdto-creatememoirresponse)
    * [메소드: `public ResponseEntity<MemoirDto> updateMemoirResponse()`](#메소드-public-responseentitymemoirdto-updatememoirresponse)
    * [메소드: `public ResponseEntity<MemoirDto> deleteMemoirResponse()`](#메소드-public-responseentitymemoirdto-deletememoirresponse)
    * [메소드: `public ResponseEntity<MemoirBookCardDtoResponseList> getMemoirCards()`](#메소드-public-responseentitymemoirbookcarddtoresponselist-getmemoircards)
    * [메소드: `public ResponseEntity<MemoirCardDtoResponse> getMemoirCards()`](#메소드-public-responseentitymemoircarddtoresponse-getmemoircards)
  * [`MemoirImageController.java`](#memoirimagecontrollerjava)
    * [메소드: `public String uploadImageToTemp()`](#메소드-public-string-uploadimagetotemp)
    * [메소드: `public byte[] printEditorImage()`](#메소드-public-byte-printeditorimage)
    * [메소드: `public ResponseEntity<String> getImageFullPath()`](#메소드-public-responseentitystring-getimagefullpath)
    * [메소드: `public void moveImage()`](#메소드-public-void-moveimage)
  * [`SeedController.java`](#seedcontrollerjava)
    * [메소드: `public ResponseEntity<SeedResponse> getSeeds()`](#메소드-public-responseentityseedresponse-getseeds)
    * [메소드: `public ResponseEntity<SeedDetailResponse> getSeed()`](#메소드-public-responseentityseeddetailresponse-getseed)
    * [메소드: `public ResponseEntity<SeedDetailResponse> getSeedByTreeId()`](#메소드-public-responseentityseeddetailresponse-getseedbytreeid)
  * [`TreeController.java`](#treecontrollerjava)
    * [메소드: `public Page<TreeSearchResultResponse> treeSearch()`](#메소드-public-pagetreesearchresultresponse-treesearch)
    * [메소드: `public ResponseEntity<TreeInfoResponse> getTreeInfoResponse()`](#메소드-public-responseentitytreeinforesponse-gettreeinforesponse)
    * [메소드: `public ResponseEntity<TreeInfoResponse> getTreeInfoResponseByLeafId()`](#메소드-public-responseentitytreeinforesponse-gettreeinforesponsebyleafid)
    * [메소드: `public ResponseEntity<TreeDetailResponse> getBranchList()`](#메소드-public-responseentitytreedetailresponse-getbranchlist)
    * [메소드: `public Page<Leaf> getLeafList()`](#메소드-public-pageleaf-getleaflist)
    * [메소드: `public ResponseEntity<TreeInfoResponseHome> getTreeInfoResponses()`](#메소드-public-responseentitytreeinforesponsehome-gettreeinforesponses)
    * [메소드: `public ResponseEntity<TreeListHome> getTreeInfoResponsesSort()`](#메소드-public-responseentitytreelisthome-gettreeinforesponsessort)
    * [메소드: `public ResponseEntity<TreeBookCardResponseList> getTreeBookCardResponse()`](#메소드-public-responseentitytreebookcardresponselist-gettreebookcardresponse)
    * [메소드: `public ResponseEntity<TreeBookCardResponseList> getBookTreeResponse()`](#메소드-public-responseentitytreebookcardresponselist-getbooktreeresponse)
    * [메소드: `public ResponseEntity<TreeCardDtoResponse> getTreeCardDtoResponse()`](#메소드-public-responseentitytreecarddtoresponse-gettreecarddtoresponse)
    * [메소드: `public ResponseEntity<EditResponse> editEtcTree()`](#메소드-public-responseentityeditresponse-editetctree)
    * [메소드: `public ResponseEntity<EditResponse> editEtcTree()`](#메소드-public-responseentityeditresponse-editetctree-1)
    * [메소드: `public ResponseEntity<EditResponse> deleteTree()`](#메소드-public-responseentityeditresponse-deletetree)
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
    - After 메소드명: `createFirst()`
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
    - After 메소드명: `create()`
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
    - After 메소드명: `update()`
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
    - After 응답 DTO: ListLeafResponse

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

### 메소드: `public ResponseEntity<EtcLeafResponse> createFirstEtcLeaf()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `createFirstEtcLeaf()`
    - After 메소드명: `createFirst()`
- 요청
    - Before 요청 DTO: @RequestBody EtcLeafRequest
    - After 요청 DTO: @RequestBody CreateEtcLeafCommand
- 응답
    - Before 응답 DTO: EtcLeafResponse
    - After 응답 DTO: CreateEtcLeafResponse

### 메소드: `public ResponseEntity<EtcLeafResponse> createEtcLeaf()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `createEtcLeaf()`
    - After 메소드명: `create()`
- 요청
    - Before 요청 DTO: @RequestBody EtcLeafRequest, @PathVariable Long parentId
    - After 요청 DTO: @RequestBody CreateEtcLeafCommand, @PathVariable Long parentId
- 응답
    - Before 응답 DTO: EtcLeafResponse
    - After 응답 DTO: CreateEtcLeafResponse

### 메소드: `public ResponseEntity<EtcLeafResponse> updateEtcLeaf()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `updateEtcLeaf()`
    - After 메소드명: `update()`
- 요청
    - Before 요청 DTO: @RequestBody EtcLeafRequest, @PathVariable Long leafId
    - After 요청 DTO: @RequestBody UpdateEtcLeafCommand, @PathVariable Long leafId
- 응답
    - Before 응답 DTO: EtcLeafResponse
    - After 응답 DTO: UpdateEtcLeafResponse

### 메소드: `public ResponseEntity<EtcLeafResponse> deleteEtcLeaf()`

- 변동 사항: 메소드 제거 (사용하지 않는 API)

### 메소드: `public ResponseEntity<EtcLeafEditResponse> getEtcLeafEdit()`

- 변동 사항: 메소드 제거 (중복으로 존재하는 API)

## `LeafImageController.java`

### 메소드: `public String upload()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `upload()`
    - After 메소드명: `upload()`
- 요청
    - Before 요청 DTO: @RequestParam MultipartFile file
    - After 요청 DTO: @RequestParam MultipartFile file
- 응답
    - Before 응답 DTO: String
    - After 응답 DTO: CreateLeafImageResponse

### 메소드: `public byte[] print()`

- 변동 사항: 없음
- 메소드 명
    - Before 메소드명: `print()`
    - After 메소드명: `print()`
- 요청
    - Before 요청 DTO: @PathVariable String domain, @PathVariable String fileName
    - After 요청 DTO: @PathVariable String domain, @PathVariable String fileName
- 응답
    - Before 응답 DTO: byte[]
    - After 응답 DTO: GetLeafImageResponse

## `LeafTagController.java`

### 메소드: `public ResponseEntity<LeafTagResponse> register()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `register()`
    - After 메소드명: `create()`
- 요청
    - Before 요청 DTO: @RequestBody LeafTagRequest
    - After 요청 DTO: @RequestBody CreateLeafTagCommand
- 응답
    - Before 응답 DTO: LeafTagResponse
    - After 응답 DTO: CreateLeafTagResponse

### 메소드: `public ResponseEntity<LeafTagResponse> modify()`

- 변동 사항: 있음
- 메소드 명 
  - Before 메소드명: `modify()`
  - After 메소드명: `update()`
- 요청
  - Before 요청 DTO: @RequestBody LeafTagRequest, @PathVariable Long tagId
  - After 요청 DTO: @RequestBody UpdateLeafTagCommand, @PathVariable Long tagId
- 응답
  - Before 응답 DTO: LeafTagResponse
  - After 응답 DTO: UpdateLeafTagResponse

### 메소드: `public ResponseEntity<LeafTagResponse> remove()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `remove()`
  - After 메소드명: `delete()`
- 요청
  - Before 요청 DTO: @PathVariable Long tagId
  - After 요청 DTO: @PathVariable Long tagId
- 응답
  - Before 응답 DTO: LeafTagResponse
  - After 응답 DTO: DeleteLeafTagResponse

### 메소드: `public ResponseEntity<LeafTagDetailResponse> get()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `get()`
  - After 메소드명: `getLeafTagDetail()`
- 요청
  - Before 요청 DTO: @PathVariable Long tagId
  - After 요청 DTO: @PathVariable Long tagId
- 응답
  - Before 응답 DTO: LeafTagDetailResponse
  - After 응답 DTO: GetLeafTagDetailResponse

### 메소드: `public ResponseEntity<LeafTagListResponse> list()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `list()`
  - After 메소드명: `listLeafTags()`
- 요청
  - Before 요청 DTO: @RequestParam int page, @RequestParam int size
  - After 요청 DTO: @ModelAttribute ListLeafTagFilterCommand
- 응답
  - Before 응답 DTO: LeafTagListResponse
  - After 응답 DTO: ListLeafTagResponse

## `AuthController.java`

### 메소드: `public ResponseEntity<AuthInfoResponse> oauthGoogle()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `oauthGoogle()`
  - After 메소드명: `google()`
- 요청
  - Before 요청 DTO: String accessToken
  - After 요청 DTO: @RequestBody GoogleAuthCommand
- 응답
  - Before 응답 DTO: AuthInfoResponse
  - After 응답 DTO: AuthInfoResponse

### 메소드: `public ResponseEntity<AuthInfoResponse> oauthNaver()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `oauthNaver()`
  - After 메소드명: `naver()`
- 요청
  - Before 요청 DTO: String accessToken
  - After 요청 DTO: @RequestBody NaverAuthCommand
- 응답
  - Before 응답 DTO: AuthInfoResponse
  - After 응답 DTO: AuthInfoResponse

### 메소드: `public ResponseEntity<AuthInfoResponse> oauthKakao()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `oauthKakao()`
  - After 메소드명: `kakao()`
- 요청
  - Before 요청 DTO: String accessToken
  - After 요청 DTO: @RequestBody KakaoAuthCommand
- 응답
  - Before 응답 DTO: AuthInfoResponse
  - After 응답 DTO: AuthInfoResponse

## `MemberController.java`

### 메소드: `public ResponseEntity<MemberSendEmailResponse> joinSendEmail()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `joinSendEmail()`
    - After 메소드명: `joinSendEmail()`
- 요청
  - Before 요청 DTO: @RequestBody MemberSendEmailRequest
  - After 요청 DTO: @RequestBody SendEmailForJoinCommand
- 응답
  - Before 응답 DTO: MemberSendEmailResponse
  - After 응답 DTO: SendEmailForJoinResponse

### 메소드: `public ResponseEntity<MemberCheckEmailResponse> checkEmail()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `checkEmail()`
    - After 메소드명: `checkEmail()`
- 요청
  - Before 요청 DTO: @RequestBody MemberCheckEmailRequest
  - After 요청 DTO: @RequestBody CheckEmailMemberCommand
- 응답
  - Before 응답 DTO: MemberCheckEmailResponse
  - After 응답 DTO: CheckEmailMemberResponse

### 메소드: `public ResponseEntity<MemberCheckTokenResponse> checkToken()`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `checkToken()`
    - After 메소드명: `checkToken()`
- 요청
  - Before 요청 DTO: @RequestBody MemberCheckTokenRequest
  - After 요청 DTO: @RequestBody CheckTokenMemberCommand
- 응답
  - Before 응답 DTO: MemberCheckTokenResponse
  - After 응답 DTO: CheckTokenMemberResponse

### 메소드: `public ResponseEntity<MemberCheckEmailResponse> passwordCheckEmail(_`

- 변동 사항: 있음
- 메소드 명
    - Before 메소드명: `passwordCheckEmail()`
    - After 메소드명: `passwordCheckEmail()`
- 요청
  - Before 요청 DTO: @RequestBody MemberCheckEmailRequest
  - After 요청 DTO: @RequestBody CheckEmailPasswordCommand
- 응답
  - Before 응답 DTO: MemberCheckEmailResponse
  - After 응답 DTO: CheckEmailPasswordResponse

### 메소드: `public ResponseEntity<MemberLoginResponse> join()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `join()`
  - After 메소드명: `join()`
- 요청
  - Before 요청 DTO: @RequestBody MemberJoinRequest
  - After 요청 DTO: @RequestBody CreateMemberCommand
- 응답
  - Before 응답 DTO: MemberLoginResponse
  - After 응답 DTO: CreateMemberResponse

### 메소드: `public ResponseEntity<MemberLoginResponse> oauthJoin()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `oauthJoin()`
  - After 메소드명: `oauthJoin()`
- 요청
  - Before 요청 DTO: @RequestBody MemberOauthJoinRequest
  - After 요청 DTO: @RequestBody CreateOauthMemberCommand
- 응답
  - Before 응답 DTO: MemberLoginResponse
  - After 응답 DTO: CreateOauthMemberResponse

### 메소드: `public ResponseEntity<MemberLoginResponse> login()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `login()`
  - After 메소드명: `login()`
- 요청
  - Before 요청 DTO: @RequestBody MemberLoginRequest
  - After 요청 DTO: @RequestBody LoginMemberCommand
- 응답
  - Before 응답 DTO: MemberLoginResponse
  - After 응답 DTO: LoginMemberResponse

### 메소드: `public ResponseEntity<MemberRefreshResponse> refresh()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `refresh()`
  - After 메소드명: `refresh()`
- 요청
  - Before 요청 DTO: @CookieValue("refreshToken") String refreshToken
  - After 요청 DTO: @CookieValue("refreshToken") String refreshToken
- 응답
  - Before 응답 DTO: MemberRefreshResponse
  - After 응답 DTO: RefreshMemberResponse

### 메소드: `public ResponseEntity<MemberEditResponse> edit()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `edit()`
  - After 메소드명: `update()`
- 요청
  - Before 요청 DTO: @PathVariable Long memberId, @RequestBody MemberEditRequest
  - After 요청 DTO: @PathVariable Long memberId, @RequestBody UpdateMemberCommand
- 응답
  - Before 응답 DTO: MemberEditResponse
  - After 응답 DTO: UpdateMemberResponse

### 메소드: `public ResponseEntity<MemberPasswordResetSendEmailResponse> findSendEmail()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `findSendEmail()`
  - After 메소드명: `sendEmailForIdRecovery()`
- 요청
  - Before 요청 DTO: @RequestBody MemberPasswordResetSendEmailRequest
  - After 요청 DTO: @RequestBody SendEmailForIdRecoveryCommand
- 응답
  - Before 응답 DTO: MemberPasswordResetSendEmailResponse
  - After 응답 DTO: SendEmailForIdRecoveryResponse

### 메소드: `public ResponseEntity<MemberResponse> findByNickname()`
- 제거 (비효율적인 API)
### 메소드: `public ResponseEntity<MemberResponse> findByUsername()`  
- 제거 (비효율적인 API)`
### 메소드: `public ResponseEntity<MemberResponse> findByEmail()`
- 제거 (비효율적인 API)

### 메소드: `public ResponseEntity<MemberResponse> findById()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `findById()`
  - After 메소드명: `getMemberInfo()`
- 요청
  - Before 요청 DTO: @PathVariable Long memberId
  - After 요청 DTO: @PathVariable Long memberId
- 응답
  - Before 응답 DTO: MemberResponse
  - After 응답 DTO: GetMemberInfoResponse

### 메소드: `public ResponseEntity<MemberDomainCntResponse> countDomainId()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `countDomainId()`
  - After 메소드명: `countDomain()`
- 요청
  - Before 요청 DTO: @PathVariable Long memberId
  - After 요청 DTO: @PathVariable Long memberId
- 응답
  - Before 응답 DTO: MemberDomainCntResponse
  - After 응답 DTO: GetMemberDomainCntResponse

## `MemoirController.java`

### 메소드: `public ResponseEntity<MemoirResponse> getMemoir()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getMemoir()`
  - After 메소드명: `getMemoir()`
- 요청
  - Before 요청 DTO: @PathVariable Long memoirId
  - After 요청 DTO: @PathVariable Long memoirId
- 응답
  - Before 응답 DTO: MemoirResponse
  - After 응답 DTO: GetMemoirResponse

### 메소드: `public ResponseEntity<MemoirDto> createMemoirResponse()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `createMemoirResponse()`
  - After 메소드명: `create()`
- 요청
  - Before 요청 DTO: @RequestBody MemoirRequest
  - After 요청 DTO: @RequestBody CreateMemoirCommand
- 응답
  - Before 응답 DTO: MemoirDto
  - After 응답 DTO: CreateMemoirResponse

### 메소드: `public ResponseEntity<MemoirDto> updateMemoirResponse()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `updateMemoirResponse()`
  - After 메소드명: `update()`
- 요청
  - Before 요청 DTO: @RequestBody MemoirRequest, @PathVariable Long memoirId
  - After 요청 DTO: @RequestBody UpdateMemoirCommand, @PathVariable Long memoirId
- 응답
  - Before 응답 DTO: MemoirDto
  - After 응답 DTO: UpdateMemoirResponse

### 메소드: `public ResponseEntity<MemoirDto> deleteMemoirResponse()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `deleteMemoirResponse()`
  - After 메소드명: `delete()`
- 요청
  - Before 요청 DTO: @PathVariable Long memoirId
  - After 요청 DTO: @PathVariable Long memoirId
- 응답
  - Before 응답 DTO: MemoirDto
  - After 응답 DTO: DeleteMemoirResponse

### 메소드: `public ResponseEntity<MemoirBookCardDtoResponseList> getMemoirCards()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getMemoirCards()`
  - After 메소드명: `listMemoirCards()`
- 요청
  - Before 요청 DTO: @PathVariable Long memoirId, @RequestParam int page, @RequestParam int size
  - After 요청 DTO: @PathVariable Long memoirId, @ModelAttribute ListMemoirCardFilterCommand
- 응답
  - Before 응답 DTO: MemoirBookCardDtoResponseList
  - After 응답 DTO: ListMemoirBookCardResponse

### 메소드: `public ResponseEntity<MemoirCardDtoResponse> getMemoirCards()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getMemoirCards()`
  - After 메소드명: `listMemoirCards()`
- 요청
  - Before 요청 DTO: @PathVariable Long bookId, @RequestParam int page, @RequestParam int size
  - After 요청 DTO: @PathVariable Long bookId, @ModelAttribute ListMemoirCardFilterCommand
- 응답
  - Before 응답 DTO: MemoirCardDtoResponse
  - After 응답 DTO: ListMemoirCardResponse

## `MemoirImageController.java`

### 메소드: `public String uploadImageToTemp()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `uploadImageToTemp()`
  - After 메소드명: `upload()`
- 요청
  - Before 요청 DTO: @RequestParam MultipartFile file
  - After 요청 DTO: @RequestParam MultipartFile image
- 응답
  - Before 응답 DTO: String
  - After 응답 DTO: CreateMemoirImageResponse

### 메소드: `public byte[] printEditorImage()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `printEditorImage()`
  - After 메소드명: `print()`
- 요청
  - Before 요청 DTO: @RequestParam final String filePath
  - After 요청 DTO: @RequestParam final String filePath
- 응답
  - Before 응답 DTO: byte[]
  - After 응답 DTO: GetMemoirImageResponse

### 메소드: `public ResponseEntity<String> getImageFullPath()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getImageFullPath()`
  - After 메소드명: `getImageFullPath()`
- 요청
  - Before 요청 DTO: @PathVariable String fileName
  - After 요청 DTO: @PathVariable String fileName
- 응답
  - Before 응답 DTO: String
  - After 응답 DTO: GetMemoirImageFullPathResponse

### 메소드: `public void moveImage()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `moveImage()`
  - After 메소드명: `move()`
- 요청
  - Before 요청 DTO: @PathVariable String fileName
  - After 요청 DTO: @PathVariable String fileName
- 응답
  - Before 응답 DTO: void
  - After 응답 DTO: void

## `SeedController.java`

### 메소드: `public ResponseEntity<SeedResponse> getSeeds()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getSeeds()`
  - After 메소드명: `list()`
- 요청
  - Before 요청 DTO: null
  - After 요청 DTO: null
- 응답
  - Before 응답 DTO: SeedResponse
  - After 응답 DTO: ListSeedResponse

### 메소드: `public ResponseEntity<SeedDetailResponse> getSeed()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getSeed()`
  - After 메소드명: `getSeedDetail()`
- 요청
  - Before 요청 DTO: @PathVariable Long seedId
  - After 요청 DTO: @PathVariable Long seedId
- 응답
  - Before 응답 DTO: SeedDetailResponse
  - After 응답 DTO: GetSeedDetailResponse

### 메소드: `public ResponseEntity<SeedDetailResponse> getSeedByTreeId()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getSeedByTreeId()`
  - After 메소드명: `getSeedByTreeId()`
- 요청
  - Before 요청 DTO: @PathVariable Long treeId
  - After 요청 DTO: @PathVariable Long treeId
- 응답
  - Before 응답 DTO: SeedDetailResponse
  - After 응답 DTO: GetSeedByTreeIdResponse

## `TreeController.java`

### 메소드: `public Page<TreeSearchResultResponse> treeSearch()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `treeSearch()`
  - After 메소드명: `listTrees()`
- 요청
  - Before 요청 DTO: @ModelAttribute TreeSearchQuery
  - After 요청 DTO: ListTreeFilterCommand
- 응답
  - Before 응답 DTO: Page<TreeSearchResultResponse>
  - After 응답 DTO: ListTreeResponse

### 메소드: `public ResponseEntity<TreeInfoResponse> getTreeInfoResponse()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getTreeInfoResponse()`
  - After 메소드명: `getTreeInfo()`
- 요청
  - Before 요청 DTO: @PathVariable Long treeId
  - After 요청 DTO: @PathVariable Long treeId
- 응답
  - Before 응답 DTO: TreeInfoResponse
  - After 응답 DTO: GetTreeInfoResponse

### 메소드: `public ResponseEntity<TreeInfoResponse> getTreeInfoResponseByLeafId()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getTreeInfoResponseByLeafId()`
  - After 메소드명: `getTreeInfoByLeafId()`
- 요청
  - Before 요청 DTO: @PathVariable Long leafId
  - After 요청 DTO: @PathVariable Long leafId
- 응답
  - Before 응답 DTO: TreeInfoResponse
  - After 응답 DTO: GetTreeInfoByLeafIdResponse

### 메소드: `public ResponseEntity<TreeDetailResponse> getBranchList()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getBranchList()`
  - After 메소드명: `listBranches()`
- 요청
  - Before 요청 DTO: @PathVariable Long treeId
  - After 요청 DTO: @PathVariable Long treeId
- 응답
  - Before 응답 DTO: TreeDetailResponse
  - After 응답 DTO: ListBranchResponse

### 메소드: `public Page<Leaf> getLeafList()`
- 제거 (비효율적인 API)

### 메소드: `public ResponseEntity<TreeInfoResponseHome> getTreeInfoResponses()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getTreeInfoResponses()`
  - After 메소드명: `getTreeInfoHome()`
- 요청
  - Before 요청 DTO: @PathVariable Long treeId
  - After 요청 DTO: @PathVariable Long memberId
- 응답
  - Before 응답 DTO: TreeInfoResponseHome
  - After 응답 DTO: GetTreeInfoHomeResponse

### 메소드: `public ResponseEntity<TreeListHome> getTreeInfoResponsesSort()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getTreeInfoResponsesSort()`
  - After 메소드명: `listTreeInfoHomeSort()`
- 요청
  - Before 요청 DTO: @PathVariable Long memberId
  - After 요청 DTO: @PathVariable Long memberId
- 응답
  - Before 응답 DTO: TreeListHome
  - After 응답 DTO: ListTreeInfoHomeSortResponse

### 메소드: `public ResponseEntity<TreeBookCardResponseList> getTreeBookCardResponse()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getTreeBookCardResponse()`
  - After 메소드명: `listTreeBookCards()`
- 요청
  - Before 요청 DTO: @PathVariable Long memberId
  - After 요청 DTO: @PathVariable Long memberId
- 응답
  - Before 응답 DTO: TreeBookCardResponseList
  - After 응답 DTO: ListTreeBookCardResponse

### 메소드: `public ResponseEntity<TreeBookCardResponseList> getBookTreeResponse()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getBookTreeResponse()`
  - After 메소드명: `listBookTrees()`
- 요청
  - Before 요청 DTO: @PathVariable Long bookId
  - After 요청 DTO: @PathVariable Long bookId
- 응답
  - Before 응답 DTO: TreeBookCardResponseList
  - After 응답 DTO: ListTreeBookCardResponse

### 메소드: `public ResponseEntity<TreeCardDtoResponse> getTreeCardDtoResponse()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `getTreeCardDtoResponse()`
  - After 메소드명: `listTreeCards()`
- 요청
  - Before 요청 DTO: @PathVariable Long bookId
  - After 요청 DTO: @PathVariable Long bookId, ListTreeCardFilterCommand
- 응답
  - Before 응답 DTO: TreeCardDtoResponse
  - After 응답 DTO: ListTreeCardResponse

### 메소드: `public ResponseEntity<EditResponse> editEtcTree()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `editEtcTree()`
  - After 메소드명: `updateEtcTree()`
- 요청
  - Before 요청 DTO: @RequestBody EditRequest, @PathVariable Long treeId
  - After 요청 DTO: @RequestBody UpdateEtcTreeCommand, @PathVariable Long treeId
- 응답
  - Before 응답 DTO: EditResponse
  - After 응답 DTO: UpdateEtcTreeResponse

### 메소드: `public ResponseEntity<EditResponse> editEtcTree()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `editEtcTree()`
  - After 메소드명: `updateBootTree()`
- 요청
  - Before 요청 DTO: @RequestBody TreeBookEdiitRequest, @PathVariable Long treeId
  - After 요청 DTO: @RequestBody UpdateBootTreeCommand, @PathVariable Long treeId
- 응답
  - Before 응답 DTO: EditResponse
  - After 응답 DTO: UpdateBootTreeResponse

### 메소드: `public ResponseEntity<EditResponse> deleteTree()`

- 변동 사항: 있음
- 메소드 명
  - Before 메소드명: `deleteTree()`
  - After 메소드명: `delete()`
- 요청
  - Before 요청 DTO: @PathVariable Long treeId
  - After 요청 DTO: @PathVariable Long treeId
- 응답
  - Before 응답 DTO: EditResponse
  - After 응답 DTO: DeleteTreeResponse

## `TreeTmpController.java`