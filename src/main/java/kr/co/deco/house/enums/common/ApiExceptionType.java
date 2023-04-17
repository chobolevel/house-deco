package kr.co.deco.house.enums.common;

import org.springframework.http.HttpStatus;

public enum ApiExceptionType {

    MISSING_PARAMETER(HttpStatus.BAD_REQUEST, "#0[Type: #1]이/가 누락되었습니다."),
    CANNOT_FETCH_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "#0 서버의 ACCESS_TOKEN 정보를 가져올 수 없습니다."),
    CANNOT_FETCH_EMAIL(HttpStatus.UNAUTHORIZED, "#0 서버에서 email 정보를 가져올 수 없습니다."),
    FAIL_TO_FIND(HttpStatus.BAD_REQUEST, "#0[Type: #1] 정보를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;

    private final String message;

    ApiExceptionType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
