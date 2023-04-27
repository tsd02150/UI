package com.yedam.prod.mapper;

import java.util.List;

import com.yedam.prod.domain.ProductVO;

public interface ProductMapper {
	public List<ProductVO> selectProductList();
	public ProductVO selectProduct(int productCode);
	public List<ProductVO> selectRecommendProduct(int productCode);
}
