package assignment.beans;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
	private int id;
	@NotBlank(message = "Vui lòng không để trống tên danh mục!")
	private String name;
}
