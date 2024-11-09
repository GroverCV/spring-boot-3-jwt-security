package com.alibou.security.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
    private Boolean success;
    private String message;
    private String status;
    private Object data;
}