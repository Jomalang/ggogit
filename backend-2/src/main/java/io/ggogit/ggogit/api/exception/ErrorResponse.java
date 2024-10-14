package io.ggogit.ggogit.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ErrorResponse 오류 응답 객체
 * 컨트롤러 외 예외 처리를 위한 응답 객체
 * 프로세스 도중에 발생한 예외를 처리하기 위한 응답 객체
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String message;
}