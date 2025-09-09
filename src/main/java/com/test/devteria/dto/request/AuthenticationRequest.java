package com.test.devteria.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data // Setter + Getter
@Builder // Bai #5
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    String username;
    String password;
}
