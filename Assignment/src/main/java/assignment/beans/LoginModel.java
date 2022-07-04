package assignment.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginModel {
	@NotBlank(message = "Vui long nhap email!")
	@Email(message = "Vui long nhap dung dinh dang email")
	private String email;
	@NotBlank(message = "Vui long khong bo trong password")
	private String password;
}
