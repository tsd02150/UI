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

public class getProductControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductService service = new ProductServiceImpl();
		int pCode = Integer.parseInt(req.getParameter("productCode"));
		ProductVO vo = service.getProduct(pCode);

		String json = "{\"productCode\":" + vo.getProductCode() + "," + "\"productName\":\"" + vo.getProductName() + "\","
				+ "\"productContent\":\"" + vo.getProductContent() + "\"," + "\"price\":" + vo.getPrice() + ","
				+ "\"discountPrice\":" + vo.getDiscountPrice() + "," + "\"grade\":" + vo.getGrade() + "}";



		return json + ".json";
	}

}
