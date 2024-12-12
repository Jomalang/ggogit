# DTO 스펙 정의

> 마지막 수정자: 한태규\
마지막 수정 날짜: 2024-12-11

API의 `DTO 클래스`명과 `필드명`의 규칙을 정의하고, `DTO`에서 `Entity`로 변환하는 방법, `Entity`에서 `DTO`로 변환하는 방법, `DTO`에서 Command validation 하는 방법을 정의한다.

---

## 목차

<!-- TOC -->
* [DTO 스펙 정의](#dto-스펙-정의)
  * [목차](#목차)
  * [`DTO` 버전 관리](#dto-버전-관리)
  * [규칙](#규칙)
    * [`DTO` 클래스명은 `CamelCase`로 작성한다.](#dto-클래스명은-camelcase로-작성한다)
    * [`DTO` 클래스명은 `Command`, `Response`로 끝나야 한다.](#dto-클래스명은-command-response로-끝나야-한다)
    * [`DTO` 클래스명 작명 규칙](#dto-클래스명-작명-규칙)
      * [생성: `Create`](#생성-create)
      * [수정: `Update`](#수정-update)
      * [삭제: `Delete`](#삭제-delete)
      * [조회: `Get` or `List`](#조회-get-or-list)
    * [`DTO` 필드명은 `pascalCase`로 작성한다.](#dto-필드명은-pascalcase로-작성한다)
    * [`API`에서 반환할 수 있는 `DTO`는 2가지로 제한한다.](#api에서-반환할-수-있는-dto는-2가지로-제한한다)
    * [`Controller`의 API Endpoint의 반환 값은 `ResponseEntity<ApiResponse<T>>`로 반환한다.](#controller의-api-endpoint의-반환-값은-responseentityapiresponset로-반환한다)
    * [공용 `DTO`를 제외한 데이터 `DTO`는 서로 의존할 수 없다.](#공용-dto를-제외한-데이터-dto는-서로-의존할-수-없다)
    * [공용으로 사용하는 객체는 `common` 패키지에 정의한다.](#공용으로-사용하는-객체는-common-패키지에-정의한다)
  * [`DTO`에서 `Entity`로 변환하는 방법](#dto에서-entity로-변환하는-방법)
  * [`Entity`에서 `DTO`로 변환하는 방법](#entity에서-dto로-변환하는-방법)
  * [`DTO`에서 `Command` `validation` 하는 방법](#dto에서-command-validation-하는-방법)
<!-- TOC -->

---

## `DTO` 버전 관리
`DTO`의 버전은 `api` 패키지 하위에 버전을 명시하여 작성한다.
패키지 이름은 시멘틱 버전의 `Major` 버전을 사용한다.

- version 1: `src.main.java.io.ggogit.api.v1.각종 도메인`
- version 2: `src.main.java.io.ggogit.api.v2.각종 도메인`

## 규칙

### `DTO` 클래스명은 `CamelCase`로 작성한다.
```java
class UserDetailResponse { // CamelCase 정의
    private String userId;
    private String userName;
}
```

### `DTO` 클래스명은 `Command`, `Response`로 끝나야 한다.
```java

class UserDetailCommand { // Command로 끝나야 한다.
    private String userId;
    private String userName;
}

class UserDetailResponse { // Response로 끝나야 한다.
    private String userId;
    private String userName;
}

```

### `DTO` 클래스명 작명 규칙
기본적인 작명 규칙은 다음과 같다.
- `동작` + `대상 도메인` + `Command` or `Response`

#### 생성: `Create`
- `Create` + `User` + `Command` = `CreateUserCommand`
- `Create` + `User` + `Response` = `CreateUserResponse`

#### 수정: `Update`
- `Update` + `User` + `Command` = `UpdateUserCommand`
- `Update` + `User` + `Response` = `UpdateUserResponse`

#### 삭제: `Delete`
- `Delete` + `User` + `Command` = `DeleteUserCommand`
- `Delete` + `User` + `Response` = `DeleteUserResponse`

#### 조회: `Get` or `List`
- 단일 객체 형식의 데이터를 조회하는 경우
  - `Get` + `대상 객체명` + `Detail` + `Response` (상세 정보) 기능이 존재하는 경우
- 리스트 형식의 데이터를 조회하는 경우
  - `List` + `대상 객체명` + `Filter` + `Command` (필터) 기능이 존재하는 경우, 해당요청은 `GET` 요쳥으로 처리하며 객체의 값은 쿼리 스트링으로 전달한다.
  - `List` + `대상 객체명` + + `Response` (리스트) 기능이 존재하는 경우

### `DTO` 필드명은 `pascalCase`로 작성한다.
```java
class UserDetailResponse {
    private String userId; // pascalCase 정의
    private String userName;
}
```

### `API`에서 반환할 수 있는 `DTO`는 2가지로 제한한다.

- `BaseResponse` 추상 클래스: 모든 응답에 사용하는 클래스
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseResponse {
    private String status;      // SUCCESS or FAIL
    private Integer statusCode; // HTTP 상태 코드
    private String message;     // 메시지 (옵션)
}
```

- `ApiResponse` 클래스: 성공적으로 데이터를 반환할 때 사용하는 객체
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> extends BaseResponse {
    
    private T data; // 실제 데이터

    public ApiResponse(String status, Integer statusCode, String message, T data) {
        super(status, statusCode, message);
        this.data = data;
    }
    
    public static <T> ApiResponse<T> of(Integer statusCode, String message, T data) { 
     return new ApiResponse<>("SUCCESS", statusCode, message, data);
    }
}
```

- `ErrorResponse` 클래스: 실패했을 때 사용하는 객체
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse extends BaseResponse {
    
    public ErrorResponse(String status, Integer statusCode, String message) {
        super(status, statusCode, message);
    }
    
    public static ErrorResponse of(Integer statusCode, String message) {
        return new ErrorResponse("FAIL", statusCode, message);
    }
}
```

### `Controller`의 API Endpoint의 반환 값은 `ResponseEntity<ApiResponse<T>>`로 반환한다.
- `AOP`를 이용하여 `ResponseEntity<UserDetailResponse>`를 `ResponseEntity<ApiResponse<UserDetailResponse>>`로 변환하여 반환한다.

```java
@RestController
@CommandMapping("/api/v1/users")
public class UserController {
    
    @PostMapping // 해당 메소드의 반환 값은 ResponseEntity<ApiResponse<UserResponse>>로 반환
    public ResponseEntity<UserResponse> createUser(
            @CommandBody @Valid UserCommand userCommand
    ) {
        User user = userCommand.toUser(); // Entity로 변환
        User savedUser = userService.createUser(user);
        UserResponse userResponse = UserResponse.of(savedUser); // Response로 변환
        return ResponseEntity.ok(userResponse);
    }
}
```

### 공용 `DTO`를 제외한 데이터 `DTO`는 서로 의존할 수 없다.
- 클래스 간의 복잡도를 줄이기 위해서 공용 `DTO`를 제외한 `DTO`는 서로 의존할 수 없다.
- 가장 외부에 있는 `DTO` 객체의 이름은 `Command`, `Response`로 끝나야 한다.
- 내부 객체는 `Dto`로 끝나야 한다.

```java
@Data // 옳은 예시
public class UserDetailResponse {
    private Long userId;
    private String username;
    private List<OrderDto> orders;

    @Data
    public static class OrderDto {
        private Long orderId;
        private String productName;
        private Integer quantity;
    }
}
```

```java
@Data // 옳은 예시
public class UserDetailResponse {
    private Long userId;
    private String username;
    private PaginationInfo paginationInfo;
}
    
@Data
public class PaginationInfo {
    private int page;
    private int size;
    private int totalPage;
    private long totalElements;
}
```

```java
@Data // 잘못된 예시
public class UserDetailResponse {
    private Long userId;
    private String username;
    private List<OrderDto> orders;
}

@Data
public class OrderDto {
    private Long orderId;
    private String productName;
    private Integer quantity;
}
```

### 공용으로 사용하는 객체는 `common` 패키지에 정의한다.
- `common` 페키지의 경로는 `com.example.api.common`로 정의한다.
- `PaginationInfo`, `ApiResponse`등 공통으로 사용하는 객체는 `common` 패키지에 정의한다.

```java
// `PaginationInfo` 예시
@Data
public class PaginationInfo {
    private int page;
    private int size;
    private int totalPage;
    private long totalElements;
}
```

## `DTO`에서 `Entity`로 변환하는 방법
- `DTO`에서 `Entity`로 변환하는 방법은 `DTO` 클래스에 `to엔티티이름()` 메소드를 정의하여 사용한다.
- `to엔티티이름()` 메소드는 `Command` 객체에서만 사용한다.
- `to엔티티이름()` 메소드는 `Response` 객체에서 사용하지 않는다.
- `to엔티티이름()` 메소드는 `Entity` 객체를 반환한다.

```java
@Data
public class UserDetailCommand {
    private Long userId;
    private String username;
    private List<OrderDto> orders;

    @Data
    public static class OrderDto {
        private Long orderId;
        private String productName;
        private Integer quantity;
    }

    public User toUser() { // toEntity 메소드 정의
        return User.builder()
                .userId(userId)
                .username(username)
                .orders(orders.stream().map(OrderDto::toOrder).collect(Collectors.toList()))
                .build();
    }
}
```

## `Entity`에서 `DTO`로 변환하는 방법
- `Entity`에서 `DTO`로 변환하는 방법은 `DTO` 클래스에 `of()` 메소드를 정의하여 사용한다.

```java
@Data
public class UserResponse {
    private Long userId;
    private String username;
    private List<OrderDto> orders;

    @Data
    public static class OrderDto {
        private Long orderId;
        private String productName;
        private Integer quantity;
    }
    
    public static UserResponse of(User user) { // of 메소드 정의
        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .orders(user.getOrders().stream().map(OrderDto::of).collect(Collectors.toList()))
                .build();
    }
}
```

## `DTO`에서 `Command` `validation` 하는 방법
- 값을 통한 검증은 `DTO` 클래스의 필드에 `@NotNull`, `@NotEmpty`, `@Size` 등의 어노테이션을 사용한다.
- 논리적인 검증은 `DTO` 클래스에 `validate()` 메소드를 정의하여 사용한다.
- `validate()`의 논리가 안맞을 경우 `IllegalArgumentException`에 메시지를 작성하여 `throw` 한다.

```java
public class UserCommand {
    @NotNull(message = "사용자 ID는 필수 값입니다.") // 값 검증
    private Long userId;

    @NotEmpty(message = "사용자 이름은 필수 값입니다.")
    @Size(min = 3, max = 20, message = "사용자 이름은 3자 이상 20자 이하로 입력해주세요.")
    private String username;

    @NotNull(message = "주문 목록은 필수 값입니다.")
    private Integer age;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    
    public void validate() { // 논리적 검증
        // 논리 검증 부분에서는 올바른 값이 들어왔다고 가정하고 검증한다.
        if (age < 0) {
            throw new IllegalArgumentException("나이는 0보다 작을 수 없습니다.");
        }
    }
}
```

`UserCommand` 을 사용하는 `Controller`에서는 다음과 같이 사용한다.
```java
@RestController
@CommandMapping("/api/v1/users")
public class UserController {
    
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(
            @CommandBody @Valid UserCommand userCommand
    ) {
        userCommand.validate(); // 논리적 검증
        User user = userCommand.toUser(); // Entity로 변환
        User savedUser = userService.createUser(user);
        UserResponse userResponse = UserResponse.of(savedUser); // Response로 변환
        ApiResponse<UserResponse> response = new ApiResponse<>(HttpStatus.CREATED.value(), userResponse);
        return ResponseEntity.ok(response);
    }
}
```