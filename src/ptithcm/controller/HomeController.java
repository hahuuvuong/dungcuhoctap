package ptithcm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
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
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.*;
import ptithcm.service.serviceimp;
import sun.security.util.Length;

@Transactional
@Controller
public class HomeController {
	@Autowired
	SessionFactory factory;
	@Autowired 
	serviceimp sv;
	@RequestMapping("index")
	public String indexNotebook(ModelMap model) {
		
		Session session = factory.getCurrentSession();
		
		String hql = api.API +"Product?idCategory=1&page=1";
		List<Product> list = sv.getListProduct(hql);
		Collections.reverse(list);
		model.addAttribute("productsNotebook", list);
		
		hql = api.API +"Product?idCategory=2&page=1";
		list = sv.getListProduct(hql);
		Collections.reverse(list);
		model.addAttribute("productsPen", list);
		
		hql = api.API +"Product?idCategory=4&page=1";
		list = sv.getListProduct(hql);
		Collections.reverse(list);
		model.addAttribute("productsRuler", list);
		
		hql = api.API +"Product?idCategory=3&page=1";
		
		list = sv.getListProduct(hql);
		Collections.reverse(list);
		model.addAttribute("productsBox", list);
		
		
		model.put("user", new User());
		model.put("cart", new Cart());
		return "index";
	}

	
	public List<Product> getListNav(int start, int limit) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			String url = api.API+"Product";
			List<Product> list = sv.getListProductWithFirstAndMaxResult(start, limit,url);
			t.commit();
			return list;
		} catch (Exception ex) {
			if (t != null) {
				t.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return null;
	}

	public int totalItem() {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			
			String url =  api.API +"api/Product";
			List<Product> list = sv.getListProduct(url);
			int obj = list.size();
			return obj;
		} catch (Exception ex) {
			if (t != null) {
				t.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return 0;
	}

	public List<Product> getListNavByCate(int start, int limit, int id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			
			
			String url = api.API+"Product?idType="+id;
			List<Product> list = sv.getListProductWithFirstAndMaxResult(start, limit,url);
			t.commit();
			return list;
		} catch (Exception ex) {
			if (t != null) {
				t.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return null;
	}

	public int totalItemByCate(int id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			String url = api.API+"Product?idType="+id;
			List<Product> list = sv.getListProduct(url);
			int obj = list.size();
			t.commit();
			return obj;
		} catch (Exception ex) {
			if (t != null) {
				t.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return 0;
	}

	
	

	@RequestMapping(value = "dungcuhoctap/{id}/{page}", method = RequestMethod.GET)
	public String viewProductListByPageAndCate(ModelMap model, @PathVariable("id") int id,
			@PathVariable("page") int page) {

		int lineCount = 8;

		Session session = factory.getCurrentSession();

		String hqlCate =api.API + "Category";
		List<Category> listCate = sv.getListCategory(hqlCate);
		model.addAttribute("categories", listCate);

//		String hqlUser = "FROM User";
//		Query queryUser = session.createQuery(hqlUser);
//		List<User> listUser = queryUser.list();
//		model.addAttribute("users", listUser);

		model.put("user", new User());
		model.put("cart", new Cart());
		
		model.addAttribute("listProduct", getListNavByCate((page - 1) * lineCount, lineCount, id));
		// số sản phẩm theo loại mặt hàng chia hết cho số sản phẩm trong 1 trang => số trang= chính nó. ngược lại số trang = nó+1
		model.addAttribute("totalPage", (totalItemByCate(id) % lineCount == 0) ? totalItemByCate(id) / lineCount
				: totalItemByCate(id) / lineCount + 1);
		model.addAttribute("currentPage", page);
		model.addAttribute("cateId", id);
		
		
//		Category category = (Category) session.get(Category.class, id);
//		model.addAttribute("category", category);

		return "dungcuhoctap";
	}
	
	
}
