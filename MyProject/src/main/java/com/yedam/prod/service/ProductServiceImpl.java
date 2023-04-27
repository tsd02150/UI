package com.yedam.prod.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.prod.domain.ProductVO;
import com.yedam.prod.mapper.ProductMapper;

public class ProductServiceImpl implements ProductService{
	SqlSession session = DataSource.getInstance().openSession(true);
	ProductMapper mapper = session.getMapper(ProductMapper.class);
	
	@Override
	public List<ProductVO> getProductList() {
		// TODO Auto-generated method stub
		return mapper.selectProductList();
	}

	@Override
	public ProductVO getProduct(int productCode) {
		// TODO Auto-generated method stub
		return mapper.selectProduct(productCode);
	}

	@Override
	public List<ProductVO> getRecommendList(int productCode) {
		// TODO Auto-generated method stub
		return mapper.selectRecommendProduct(productCode);
	}

}
