package org.project2.tz1_cinema.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.List;

public class BindCheckerUtil {
    public static void checkBind(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new RuntimeException(errorMessage.toString());
        }
    }
}
