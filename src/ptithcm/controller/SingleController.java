package ptithcm.controller;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.Product;
import ptithcm.entity.User;
import ptithcm.entity.api;
import ptithcm.service.serviceimp;

@Transactional
@Controller
public class SingleController {

	@Autowired
	SessionFactory factory;
	@Autowired 
	serviceimp sv;
	@RequestMapping("single/{proId}")
	public String getSinglePage(ModelMap model, @PathVariable("proId") int proId) {
		model.put("user", new User());

		
		Product x = sv.getListAProductByID(proId);
		model.addAttribute("product", x);

		int cate=x.getCategory();
		
		String url = api.API + "Product?idType="+cate;
		List<Product> list = sv.getListProduct(url);
		List<Product> listStart = list.subList(0, 4);
		model.addAttribute("lProduct", listStart);

		return "single";

	}

}
