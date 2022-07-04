package assignment.beans;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
	@NotBlank(message = "Vui lòng nhập đại chỉ nhận hàng!")
	private String address;
	@Min(value = 1, message = "Số lượng sản phẩm không được bé hơn 0")
	@NotNull(message = "Vui lòng không bỏ trống số lượng sản phẩm!")
	private int quatity;
}
