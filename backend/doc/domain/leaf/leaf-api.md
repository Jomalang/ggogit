# 리프 API 명세서

## 1. 리프 생성

### 1.1 첫번쨰 도서 리프 생성
POST `/api/v1/leafs/book/first`
- Request: LeafBookFirstCreateRequest
- Response: LeafBookFirstCreateResponse

### 1.2 도서 리프 생성
POST `/api/v1/leafs/book/parent/:leafId`
- Request: LeafBookCreateRequest
- Response: LeafBookCreateResponse

### 1.3 첫번쨰 리프 생성
POST `/api/v1/leafs/first`
- Request: LeafFirstCreateRequest
- Response: LeafFirstCreateResponse

### 1.4 리프 생성
POST `/api/v1/leafs/parent/:leafId`
- Request: LeafCreateRequest
- Response: LeafCreateResponse

## 2. 리프 수정
### 1.1 리프 수정
PUT `/api/v1/leafs/:leafId`
- Request: LeafUpdateRequest
- Response: LeafUpdateResponse

## 3. 리프 삭제
DELETE `/api/v1/leafs/:leafId`

## 4. 리프 조회
GET `/api/v1/leafs/:leafId`