package telran.spring;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record Person(
    long id,
    @Pattern(regexp = "[A-Z][a-z]{2,}", message = "Name must start with a capital letter and contain at least 3 characters")
    String name,
    @NotEmpty(message = "City cannot be empty")
    String city,
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    String email,
    @Pattern(regexp = "^\\+?972-?\\d{2,3}-\\d{7}$", message = "Please enter a valid Israel phone number in format +972-XXX-XXXXXXX")
    String phone
) {
}


//package telran.spring;
//
//public record Person(long id, String name, String city) {
//
//}
