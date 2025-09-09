package com.test.devteria.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

// Khi field null sẽ không trả về
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse <T>{
    // 1000: success
    int code = 1000;
    String message;
    T result;
}
