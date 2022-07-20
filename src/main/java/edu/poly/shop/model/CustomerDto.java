package edu.poly.shop.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
	
	private Long customerId;
	@NotEmpty
	private String name;
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String phone;
	private Date registeredDate;
	private short status;
	private Boolean isEdit = false;
}
