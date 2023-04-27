package com.yedam.prod.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.prod.domain.ProductVO;
import com.yedam.prod.service.ProductService;
import com.yedam.prod.service.ProductServiceImpl;

public class getProdListControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductService service = new ProductServiceImpl();
		List<ProductVO> list = service.getProductList();

		String json = "[";
		for (int i = 0; i < list.size(); i++) {
			json += "{\"productCode\":" + list.get(i).getProductCode() + "," + "\"productName\":\""
					+ list.get(i).getProductName() + "\"," + "\"productContent\":\"" + list.get(i).getProductContent()
					+ "\"," + "\"price\":" + list.get(i).getPrice() + "," + "\"discountPrice\":"
					+ list.get(i).getDiscountPrice() + "," + "\"grade\":" + list.get(i).getGrade() + "}";
			if (i != list.size() - 1) {
				json += ",";
			}
		}
		json += "]";

		return json + ".json";
	}

}
