package com.test.devteria.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data // Setter + Getter
@Builder // Bai #5
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    String token;
    boolean authenticated;
}
