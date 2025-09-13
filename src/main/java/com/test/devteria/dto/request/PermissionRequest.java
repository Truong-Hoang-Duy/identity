package com.test.devteria.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequest { // Kiểm tra token khi người dùng request
    String name;
    String description;
}
