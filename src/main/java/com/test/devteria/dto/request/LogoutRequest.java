package com.test.devteria.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class LogoutRequest { // Kiểm tra token khi người dùng request
    String token;
}
