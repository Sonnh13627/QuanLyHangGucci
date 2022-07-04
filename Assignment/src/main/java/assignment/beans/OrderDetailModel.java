package assignment.beans;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailModel {
	@NotNull(message="Vui lòng không bỏ trống ô số lượng sản phẩm")
	@Min(value=1, message = "Số lượng sản phẩm không được bé hơn 1")
	private int quatity;
}
