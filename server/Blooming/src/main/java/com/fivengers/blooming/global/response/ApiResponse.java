package com.fivengers.blooming.global.response;

public record ApiResponse<T>(int status,
                             T results) {

}
