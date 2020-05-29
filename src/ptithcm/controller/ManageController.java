package ptithcm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.Cart;
import ptithcm.entity.CartItem;
import ptithcm.entity.Product;

import ptithcm.entity.User;
import ptithcm.entity.api;
import ptithcm.service.serviceimp;


@Transactional
@Controller
public class ManageController {
	
	@Autowired
	SessionFactory factory;
	@Autowired
	serviceimp sv;
	
	List<Product> listProduct;
	public List<Product> getListProduct()
	{
//		Session session = factory.getCurrentSession();
//		String hql = "FROM Product";
//		Query query = session.createQuery(hql);
		listProduct= sv.getListProduct(api.API + "Product");
		System.out.print(listProduct.size());
		return listProduct;
	}
	
	
	@RequestMapping(value = "manage", method = RequestMethod.GET)
	public String viewLogin(ModelMap mm, HttpSession session1) {
		String username=(String) session1.getAttribute("username");
    	int role=(Integer) session1.getAttribute("roleid");
		if(username==null||role!=1) {
			return "error";
		}
		mm.put("user", new User());
		return "manage";
	}
	@RequestMapping(value = "manage", method = RequestMethod.POST)
	public String manages(ModelMap model,HttpSession session1) {
		Session session = factory.getCurrentSession();
		String username=(String) session1.getAttribute("username");
    	int role=(Integer) session1.getAttribute("roleid");
		if(username==null||role!=1) {
			return "error";
		}
		model.put("user", new User());
		
		
//		int role=
		return "manage";
	}
	
	
	
	

	@RequestMapping(value="manageproduct")
	public String manageProductList(ModelMap model, HttpSession session1) {
		
		Session session = factory.getCurrentSession();
		model.put("user", new User());
		
		String username=(String) session1.getAttribute("username");
    	int role=(Integer) session1.getAttribute("roleid");
		if(username==null||role!=1) {
			return "error";
		}

//		Query query = session.createQuery("FROM Product");
		List<Product> list = getListProduct();
		int totalitems= list.size();
		model.addAttribute("listProduct", list);
		model.addAttribute("totalitems", totalitems);
	
		return "manageproduct";
	}

	
	
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String insert(ModelMap model, HttpSession session1) {
		String username=(String) session1.getAttribute("username");
    	int role=(Integer) session1.getAttribute("roleid");
		if(username==null||role!=1) {
			return "error";
		}
		model.addAttribute("product", new Product());
		return "manageproduct_form";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("product") Product product, HttpSession session1) {
		
		String username=(String) session1.getAttribute("username");
    	int role=(Integer) session1.getAttribute("roleid");
		if(username==null||role!=1) {
			return "error";
		}
		
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
		try {
//			session.saveOrUpdate(product);
//			t.commit();
			sv.POSTProduct(product);
			model.addAttribute("message", "Thêm thành công !");
		} catch (Exception e) {
//			t.rollback();
			model.addAttribute("message", "Thêm thất bại !");
		}
		
		return "manageproduct_form";
	}
	
	@RequestMapping(value = "update/{proId}", method = RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("product") Product product, @PathVariable("proId") int proId, HttpSession session1) {
		
		String username=(String) session1.getAttribute("username");
    	int role=(Integer) session1.getAttribute("roleid");
		if(username==null||role!=1) {
			return "error";
		}
		
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
		try {
//			session.saveOrUpdate(product);
//			t.commit();
			sv.PUTProduct(product,proId);
			model.addAttribute("message", "update thành công !");
		} catch (Exception e) {
//			t.rollback();
			model.addAttribute("message", "update thất bại !");
		}
		
		return "manageproduct_form";
	}
	
	@RequestMapping("{proId}")
	public String edit(ModelMap model, @PathVariable("proId") int proId, HttpSession session1) {
		
		String username=(String) session1.getAttribute("username");
    	int role=(Integer) session1.getAttribute("roleid");
		if(username==null||role!=1) {
			return "error";
		}
//		
//		Session session = factory.getCurrentSession();
		Product product = sv.getListAProductByID(proId);
		model.addAttribute("proid", proId);
		model.addAttribute("product", product);
		//model.addAttribute("users", getUsers());
		return "manageproduct_update";
	}
	
	
	@RequestMapping(value= "delete/{proId}",method = RequestMethod.GET)
	public String delete(ModelMap model, @PathVariable("proId") int proId, HttpSession session1) {
		
		String username=(String) session1.getAttribute("username");
    	int role=(Integer) session1.getAttribute("roleid");
		if(username==null||role!=1) {
			return "error";
		}
		
//		Session session = factory.getCurrentSession();
//		Product product = (Product) session.get(Product.class, proId );
		//model.addAttribute("user", user);
		//Transaction s = session.beginTransaction();
		try {
//			session.delete(product);
			//s.commit();
			String url = api.API+ "Product/"+proId;
			sv.runURL(url, "DELETE");
			model.addAttribute("message", "Xóa thành công !");
			model.addAttribute("listProduct", getListProduct());
			return "manageproduct";
			
		} catch (Exception e) {
			//s.rollback();
			model.addAttribute("message", "Xóa thất bại ! Sản phẩm có thể đang ở trong đơn hàng đang đặt mua.");
		}
//		} finally {
//			session.close();
//		}
		return "manageproduct";
	}
	
	@RequestMapping(value="managecart")
	public String managecart(ModelMap model, HttpSession session1) {

		Session session = factory.getCurrentSession();
		model.put("user", new User());
		
		String username=(String) session1.getAttribute("username");
    	int role=(Integer) session1.getAttribute("roleid");
		if(username==null||role!=1) {
			return "error";
		}

//		Query query = session.createQuery("FROM Cart");
		List<Cart> listCart = sv.getListCard();
		int totalitems=listCart.size();
		model.addAttribute("listCart", listCart);
		model.addAttribute("totalitems", totalitems);
		
		return "manage_cart";
	}
	
	@RequestMapping(value="cart/{cartid}")
	public String chart(ModelMap model, @PathVariable("cartid") int cartid, HttpSession session1) {
		
		model.put("user", new User());
		
		String username=(String) session1.getAttribute("username");
    	int role=(Integer) session1.getAttribute("roleid");
		if(username==null||role!=1) {
			return "error";
		}
		int id=cartid;
//		Session session = factory.getCurrentSession();
//		String hql = "FROM Cart WHERE cartId = '" + cartid + "'";
//		Query query = session.createQuery(hql);
		Cart h = sv.getCardByCardID(id);
		int isPaid = h.getIsPaid();
		model.addAttribute("isPaid", isPaid);
		model.addAttribute("id",id);
		
//		Session session2 = factory.getCurrentSession();
//		String hql2 = "FROM CartItem WHERE cartid.cartId = '" + id + "'";
//		Query query2 = session2.createQuery(hql2);
	

		List<CartItem> l  = sv.getCartOfUser(h.getUserid(), isPaid);
		model.addAttribute("listItem", l);

		int cart = 0;

		float money = 0;
		for (CartItem item : l) {
			float discount1 = (float) item.getDiscount() / 100;

			float totaldiscount1 = item.getUnitPrice() * discount1;

			float money1 = item.getUnitPrice() - totaldiscount1;

			money += money1 * item.getQuantity();
			cart = item.getCartid();

		}
		model.addAttribute("total", money);
		return "chart";
	}
	
	@RequestMapping(value="accept/{cartid}")
	public String accept(ModelMap model, @PathVariable("cartid") int cartid, HttpSession session1) {
		
		model.put("user", new User());
		
		String username=(String) session1.getAttribute("username");
    	int role=(Integer) session1.getAttribute("roleid");
		if(username==null||role!=1) {
			return "error";
		}
		
//		Session session = factory.getCurrentSession();
//		String hql = "FROM Cart WHERE cartId = '" + cartid + "'";
//		Query query = session.createQuery(hql);
//		Cart h = (Cart) query.uniqueResult();
//		h.setIsPaid(3);
//		User userId=h.getUserid();
		Cart x = sv.getCardByCardID(cartid);
		
		Cart cart=new Cart();
		cart.setUserid(x.getUserid());
		cart.setIsPaid(0);
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		cart.setBuyDate(date); 
//		session.save(cart);
//		session.saveOrUpdate(h);
		String url = api.API + "Cart?idCart="+cartid+"&isPaid=3";
		
		sv.runURL(url, "PUT");
		sv.addCartUser(cart);
		
		return "redirect:/cart/{cartid}.html";
	}
	
	@RequestMapping(value="finish/{id}")
	public String finish(ModelMap model, @PathVariable("id") int id, HttpSession session1) {
		
		model.put("user", new User());
		
		String username=(String) session1.getAttribute("username");
    	int role=(Integer) session1.getAttribute("roleid");
		if(username==null||role!=1) {
			return "error";
		}
		
//		Session session = factory.getCurrentSession();
//		String hql = "FROM Cart WHERE cartId = '" + id + "'";
//		Query query = session.createQuery(hql);
//		Cart h = (Cart) query.uniqueResult();
//		h.setIsPaid(1);
//		session.saveOrUpdate(h);
		String url1 = api.API + "Cart?idCart="+id+"&isPaid=1";
		sv.runURL(url1, "PUT");
		return "redirect:/cart/{id}.html";
	}
}