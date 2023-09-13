package com.fivengers.blooming.global.response;

public record ErrorResponse(int status,
                            String errorCode,
                            String message) {

}
