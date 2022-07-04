package assignment.beans;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
	@NotBlank(message = "Vui lòng không bỏ trống tên sản phẩm!")
	private String name;
	@NotNull(message = "Vui lòng không bỏ trống ảnh sản phẩm!")
	private MultipartFile photoFile;
	private String image;
	@Min(value = 0, message = "Giá không được bé hơn 0")
	@NotNull(message = "Vui lòng không bỏ trống giá sản phẩm!")
	private float price;
	private Date createDate;
	@NotNull(message = "Vui lòng chọn phân loại sản phẩm!")
	private int available;
	@NotNull(message = "Vui lòng chọn danh mục sản phẩm!")
	private int categoryId;
}
