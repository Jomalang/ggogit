# 도서 리프 첫 생성

## 도서 리프 첫 생성 유스 케이스

목표: 도서 리프 첫 생성

액터: 사용자(Member)

사전 조건:
- 사용자는 로그인 되어 있다.
- 트리 정보를 입력하여 `TreeSaveTmp`에 데이터가 저장되어 있다.

트리거:
- 사용자가 도서 리프 첫번째 생성을 요청한다.

main flow:

데이터 입력 및 검증
- `Member`는 도서 리프 첫번째 데이터를 입력한다.
- `Member`는 도서 리프 첫번째 생성을 요청한다.
- `System`은 사용자가 입력한 데이터를 검증한다.

`TreeSaveTmp` 데이터
- `System`은 `TreeSaveTmp`에 데이터를 조회한다.
- `System`은 `TreeSaveTmp`에 데이터를 검증한다.

도서 데이터
- `System`은 `TreeSaveTmp`에서 `Book` 데이터를 조회한다.
- `System`은 `TreeSaveTmp`에서 `Book` 데이터를 검증한다.
   - `System`은 `TreeSaveTmp`에서 `bookId`정보가 있으면 `Book` 데이터를 조회한다.
   - `System`은 `TreeSaveTmp`에서 `bookId`정보가 없으면 `Book` 데이터를 생성후 저장한다.
      - `System`은 `TreeSaveTmp`의 이미지를 `Book`이미지로 이동한다.

트리 데이터
- `System`은 `TreeSaveTmp`에서 `Tree` 데이터를 조회한다.
- `System`은 `TreeSaveTmp`에서 `Tree` 데이터를 검증한다.
- `System`은 `TreeSaveTmp`에서 `Tree` 데이터를 생성후 저장한다.

트리 도서 데이터
- `System`은 `TreeBook` 데이터를 생성후 저장한다.

리프 데이터
- `System`은 입력받은 데이터에서 `LeafTag` 데이터를 조회 후 `LeafTag` 데이터의 소유자 정보를 검증한다.
- `System`은 입력받은 데이터에서 `Leaf` 컨텐츠의 이미지 이동 및 경로 변경.
- `System`은 `Leaf` 데이터를 저장한다.
- `System`은 `Leaf` 이미지를 저장한다.

리프 도서 데이터
- `System`은 입력받은 데이터에서 `LeafBook` 데이터를 생성후 저장한다.
- `System`은 `LeafBook` 데이터를 저장한다.

트리 도서 데이터
- `System`은 `TreeBook` 의 읽은 페이지를 업데이트한다.

리프 태그 데이터
- `System`은 입력받은 데이터에서 `LeafTag` 데이터를 저장한다.

임시 데이터 삭제
- `System`은 `TreeSaveTmp` 데이터를 삭제한다.

Exception flow:

- 사용자가 입력한 데이터가 유효하지 않을 때
  1. `System`은 사용자에게 입력한 데이터가 유효하지 않다는 메시지를 전송한다.

- `TreeSaveTmp` 데이터가 없을 때
  1. `System`은 사용자에게 `TreeSaveTmp` 데이터가 없다는 메시지를 전송한다.

- `TreeSaveTmp` 이미지 경로가 있는데 이미지가 없을 때
  1. `System`은 사용자에게 이미지가 없다는 메시지를 전송한다.

- `Book` `bookId`가 들어왔으나 데이터가 없을 때 
  1. `System`은 사용자에게 `Book` 데이터가 없다는 메시지를 전송한다.

PostCondition:
- `Tree` 데이터가 생성되어 있다.
- `TreeBook` 데이터가 생성되어 있다.
- `Leaf` 데이터가 생성되어 있다.
- `Leaf` 이미지가 생성되어 있다.
- `LeafBook` 데이터가 생성되어 있다.
- `Book` 데이터가 생성되어 있다.
- `Book` 이미지가 생성되어 있다.
- `TreeSaveTmp` 데이터가 삭제되어 있다.