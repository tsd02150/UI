package com.yedam.prod.service;

import java.util.List;

import com.yedam.prod.domain.ProductVO;

public interface ProductService {
	public List<ProductVO> getProductList();
	public ProductVO getProduct(int productCode);
	public List<ProductVO> getRecommendList(int productCode);
}
