package com.fivengers.blooming.global.response;

import org.springframework.http.HttpStatus;

/**
 * REST API Response
 * @author WonHo Seo
 * @version 1.0
 */
public record ApiResponse<T>(int status,
                             T results) {

    public record ApiResponseBuilder(HttpStatus status) {
        public <T> ApiResponse<T> body(T body) {
            return new ApiResponse<T>(status.value(), body);
        }

        public <T> ApiResponse<T> build() {
            return new ApiResponse<>(status.value(), null);
        }
    }

    public static ApiResponseBuilder ok() {
        return new ApiResponseBuilder(HttpStatus.OK);
    }

    public static ApiResponseBuilder noContent() {
        return new ApiResponseBuilder(HttpStatus.NO_CONTENT);
    }

    public static ApiResponseBuilder notFound() {
        return new ApiResponseBuilder(HttpStatus.NOT_FOUND);
    }

    public static <T> ApiResponse<T> ok(T body) {
        return new ApiResponse<>(HttpStatus.OK.value(), body);
    }
}
