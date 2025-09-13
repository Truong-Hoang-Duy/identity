package com.test.devteria.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest { // Kiểm tra token khi người dùng request
    String name;
    String description;
    Set<String> permissions;
}
