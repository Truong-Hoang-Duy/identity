package com.test.devteria.dto.request;

import com.test.devteria.exception.ErrorCode;
import com.test.devteria.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

//@Getter
//@Setter
@Data // Setter + Getter
@Builder // Bai #5
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;

    @Size(min = 6, max = 20, message = "PASSWORD_INVALID")
    String password;
    String firstName;
    String lastName;

    // Custom anotation
    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;
}
