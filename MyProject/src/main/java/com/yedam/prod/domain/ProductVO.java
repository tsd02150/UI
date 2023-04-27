package com.yedam.prod.domain;

import lombok.Data;

@Data
public class ProductVO {
	private int productCode;
	private String productName;
	private String productContent;
	private int price;
	private int discountPrice;
	private int grade;
}
