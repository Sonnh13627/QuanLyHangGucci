package assignment.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel {
	@NotBlank(message = "Vui lòng nhập username!")
	private String username;
	@NotBlank(message = "Vui lòng nhập password!")
	private String password;
	@NotBlank(message = "Vui lòng nhập họ tên!")
	private String fullname;
	@NotBlank(message = "Vui lòng nhập email!")
	@Email(message = "Sai định dạng email")
	private String email;
	private String photo;
	private MultipartFile photoFile;
	private int activated;
	@NotNull(message = "vui lòng phân quyền cho account")
	private int admin;
}
