package ptithcm.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CartController {

	@Autowired
	SessionFactory factory;
	@Autowired
	serviceimp sv;
	List<CartItem> listProduct;

	public List<CartItem> getList() {

		HttpSession session = null;
		int id = (int) session.getAttribute("id");
		listProduct = sv.getCartOfUser(id, 0);
		return listProduct;

	}

	@RequestMapping(value = "cart")
	public String viewCart(ModelMap model, HttpSession session) {

		model.put("cart", new Cart());
		model.put("user", new User());
		String username = (String) session.getAttribute("username");

		if (username == null) {
			return "error";
		}
		int id = (int) session.getAttribute("id");
		
		
//		Session session6 = factory.getCurrentSession();
//		String hql6 = "FROM Cart WHERE userid.userId = '" + id +"'";
//		Query query6 = session6.createQuery(hql6);
//		List<Cart> l = query6.list();
//		int n=l.size();
//		model.addAttribute("number_cart", n);

//		Session session1 = factory.getCurrentSession();
//		String hql = "FROM Cart WHERE userid.userId = '" + id + "' AND isPaid='2'";
//		Query query = session1.createQuery(hql);
//		List<Cart> listCart = query.list();

		// tìm trong cart cái cart nào có id =2 thì in ra giỏ hàng và trả về trang giỏ
		// hàng đang chờ xử lí
		int temp = sv.returnAmountCartOfUsername(id, 2);
		System.out.println(temp);
		if (temp != 0) {

			
			listProduct = sv.getCartOfUser(id, 2);
			model.addAttribute("listItem", listProduct);

			int cart = 0;

			float money = 0;
			for (CartItem item : listProduct) {
				float discount1 = (float) item.getDiscount() / 100;

				float totaldiscount1 = item.getUnitPrice() * discount1;

				float money1 = item.getUnitPrice() - totaldiscount1;

				money += money1 * item.getQuantity();
				cart = item.getCartid();

			}
			model.addAttribute("money", money);
			session.setAttribute("cart", cart);
			return "handling_cart";

		}

		// nếu ispaid không = 2 thì:

//		Session session2 = factory.getCurrentSession();
//		String hql2 = "FROM CartItem WHERE cartid.userid = '" + id + "' AND cartid.isPaid='0'";
//		Query query2 = session2.createQuery(hql2);
		
		listProduct = sv.getCartOfUser(id, 0);
		model.addAttribute("listProduct", listProduct);

		float total = 0;
		for (CartItem number : listProduct) {
			float discount = (float) number.getDiscount() / 100;
			System.out.println(discount);
			float totaldiscount = number.getUnitPrice() * discount;
			System.out.println(totaldiscount);
			float money = number.getUnitPrice() - totaldiscount;
			System.out.println(money);
			total += money * number.getQuantity();
			System.out.println(total);
		}
		model.addAttribute("total", total);
		
		
		
		return "cart";

	}

	@RequestMapping(value = "add/{productId}", method = RequestMethod.GET)
	public String viewAdd(ModelMap mm, HttpSession session, @PathVariable("productId") int productId) {
		mm.put("user", new User());
		mm.put("cart", new Cart());

		String username = (String) session.getAttribute("username");

		if (username == null) {
			mm.addAttribute("message", "Vùi long đăng nhập để thêm sản phẩm");
			return "login";
		}

		return "redirect:/cart.html";
	}

	@RequestMapping(value = "add/{productId}", method = RequestMethod.POST)
	public String view(ModelMap mm, HttpSession session, @PathVariable("productId") int productId) {

		String username = (String) session.getAttribute("username");
		mm.put("user", new User());
		mm.put("cart", new Cart());
		if (username == null) {
			mm.addAttribute("message", "Vùi long đăng nhập để thêm sản phẩm");
			return "login";
		}

		int id = (int) session.getAttribute("id");

//		Session session3 = factory.getCurrentSession();
//		String hql3 = "FROM Cart WHERE userid.userId = '" + id + "' AND isPaid='2'";
//		Query query3 = session3.createQuery(hql3);
		List<Cart> listCart = sv.getCartByUserAndIspaid(id,2);

		if (listCart.size() != 0) {
			return "redirect:/cart.html";
		}

		// tìm trong cartitem nếu có sản phẩm đó rồi thì tăng lên 1 đơn vị, nếu chưa có
		// tạo sản phẩm mới
//		Session session1 = factory.getCurrentSession();
//		String hql = "FROM CartItem WHERE cartid.userid = '" + id + "' AND cartid.isPaid='0'";
//		Query query = session1.createQuery(hql);
		
		List<CartItem> listPro = sv.getCartOfUser(id, 0);

		// tìm trong list CartItem có CartItem nào có id = id product hiện tại k
		CartItem exist = null;
		for (CartItem number : listPro) {
			if (number.getProid() == productId) {
				exist = number;
			}
		}

		// nếu có thì xử lí tăng số lượng lên 1 đv
		if (exist != null) {
			sv.PUTincreaseCartItemByID(exist.getCartItemId());
		}

		// exist=null thì thêm mới
		else {
			
			Product x = sv.getListAProductByID(productId);

			Cart y = sv.getCartbyUseridAndIspaid(id, 0);

			CartItem item = new CartItem();
			item.setQuantity(1);
			item.setProid(x.getProId());
			item.setCartid(y.getCartId());
			sv.POSTCartItem(item);
		}

		return "redirect:/cart.html";
	}
//---------------------------------------------------------------
	// delete chưa sửa
	@RequestMapping(value = "move/{proId}", method = RequestMethod.GET)
	public String delete(ModelMap model, @PathVariable("proId") int proId, HttpSession session1) {

		int id = (int) session1.getAttribute("id");
		String username = (String) session1.getAttribute("username");
		if (username == null) {
			return "error";
		}

//		Session session = factory.getCurrentSession();
//		String hql = "FROM CartItem WHERE cartid.userid = '" + id + "' AND cartid.isPaid='0' AND proid.proId= '" + proId
//				+ "'";
//		Query query = session.createQuery(hql);
//		CartItem k = (CartItem) query.uniqueResult();
		try {
			sv.DELETECartItem(proId);
			// s.commit();

			model.addAttribute("listProduct", getList());
			return "redirect:/cart.html";

		} catch (Exception e) {
			// s.rollback();

		}
//		} finally {
//			session.close();
//		}
		return "redirect:/cart.html";
	}

	@RequestMapping(value = "increase/{productId}", method = RequestMethod.GET)
	public String setquantity(ModelMap mm, HttpSession session, @PathVariable("productId") int productId) {

		String username = (String) session.getAttribute("username");
		int id = (int) session.getAttribute("id");

		if (username == null) {
			return "error";
		}

//		Session session1 = factory.getCurrentSession();
//		String hql = "FROM CartItem WHERE cartid.userid = '" + id + "' AND cartid.isPaid='0' AND proid.proId= '"
//				+ productId + "'";
//		Query query = session1.createQuery(hql);
//		CartItem z = (CartItem) query.uniqueResult();
//
//		int total2 = z.getQuantity();
//		z.setQuantity(total2 + 1);
//
//		session1.saveOrUpdate(z);
		
		String url1 = api.API + "CartItem?idCartItem="+productId+"&state=increase";
		sv.runURL(url1, "PUT");
		return "redirect:/cart.html";
	}

	@RequestMapping(value = "decrease/{productId}", method = RequestMethod.GET)
	public String decreaseItem(ModelMap mm, HttpSession session, @PathVariable("productId") int productId) {

		String username = (String) session.getAttribute("username");
		int id = (int) session.getAttribute("id");

		if (username == null) {
			return "error";
		}

//		Session session1 = factory.getCurrentSession();
//		String hql = "FROM CartItem WHERE cartid.userid = '" + id + "' AND cartid.isPaid='0' AND proid.proId= '"
//				+ productId + "'";
//		Query query = session1.createQuery(hql);
//		CartItem z = (CartItem) query.uniqueResult();
//
//		int total2 = z.getQuantity();
//		if (total2 > 1) {
//			z.setQuantity(total2 - 1);
//		}
//		session1.saveOrUpdate(z);
		String url1 = api.API + "CartItem?idCartItem="+productId+"&state=decrease";
		sv.runURL(url1, "PUT");
		return "redirect:/cart.html";
	}

	@RequestMapping(value = "confirm")
	public String confirm(ModelMap model, HttpSession session) {

		model.put("cart", new Cart());
		model.put("user", new User());
		String username = (String) session.getAttribute("username");

		if (username == null) {
			return "error";
		}
		int id = (int) session.getAttribute("id");
//		Session session1 = factory.getCurrentSession();
//		String hql = "FROM CartItem WHERE cartid.userid = '" + id + "' AND cartid.isPaid='0'";
//		Query query = session1.createQuery(hql);
		List<CartItem> list = sv.getCartOfUser(id, 0);
		model.addAttribute("list", list);

		Cart y = sv.getCartbyUseridAndIspaid(id, 0);

		float money = 0;
		for (CartItem item : list) {
			float discount1 = (float) item.getDiscount() / 100;

			float totaldiscount1 = item.getUnitPrice() * discount1;

			float money1 = item.getUnitPrice() - totaldiscount1;

			money += money1 * item.getQuantity();

		}
		model.addAttribute("money", money);
		session.setAttribute("cart1", y.getCartId());
		return "confirm_cart";
	}

	@RequestMapping(value = "handling")
	public String handling(ModelMap model, HttpSession session) {

		model.put("cart", new Cart());
		model.put("user", new User());
		String username = (String) session.getAttribute("username");

		if (username == null) {
			return "error";
		}
		int id = (int) session.getAttribute("id");
		return "handling_cart";
	}

	// nếu nhấn nút thanh toán thì chuyển trạng thái ispaid=2
	@RequestMapping(value = "buy/{cart}")
	public String buyCart(ModelMap model, HttpSession session, @PathVariable("cart") int cart) {

		model.put("cart", new Cart());
		model.put("user", new User());
		String username = (String) session.getAttribute("username");

		if (username == null) {
			return "error";
		}
//		Session session1 = factory.getCurrentSession();
//		String hql = "FROM Cart WHERE cartId = '" + cart + "'";
//		Query query = session1.createQuery(hql);
//		Cart h = (Cart) query.uniqueResult();
//
//		h.setIsPaid(2);
//		session1.saveOrUpdate(h);
		String url = api.API + "Cart?idCart="+cart+"&isPaid="+2;
		sv.runURL(url,"PUT");
		return "redirect:/cart.html";
	}

	@RequestMapping(value = "history/{id}")
	public String viewHistory(ModelMap model, HttpSession session, @PathVariable("id") int id) {
		model.put("cart", new Cart());
		model.put("user", new User());
		String username = (String) session.getAttribute("username");

		if (username == null) {
			return "error";
		}
//		Session session1 = factory.getCurrentSession();
//		
//		Query query = session1.createQuery("FROM Cart WHERE userid.userId= '" + id+"'");
		
		List<Cart> listCart = sv.getCartByUser(id);
		listCart.remove(listCart.size()-1);
	
		model.addAttribute("listCart", listCart);
	return "history";
	}
	
	@RequestMapping(value = "history_cart/{idCart}")
	public String viewHistoryCart(ModelMap model, HttpSession session, @PathVariable("idCart") int idCart) {
		
		
		model.put("cart", new Cart());
		model.put("user", new User());
		String username = (String) session.getAttribute("username");

		if (username == null) {
			return "error";
		}
		
//		Session session1 = factory.getCurrentSession();
//		String hql2 = "FROM CartItem WHERE cartid.cartId = '" + idCart + "'";
//		Query query2 = session1.createQuery(hql2);
//		List<CartItem> listItem =  query2.list();
//		model.addAttribute("listItem", listItem);
//		
//		float total=0;
//		for (CartItem number : listItem) {
//			float discount=(float)number.getProid().getDiscount()/100;
//			System.out.println(discount);
//			float totaldiscount=number.getProid().getPrice()*discount;
//			System.out.println(totaldiscount);
//			float money=number.getProid().getPrice()-totaldiscount;
//			System.out.println(money);
//			total+=money*number.getQuantity();
//			System.out.println(total);
//		}
		model.addAttribute("total", 100000);
		return "history_cart";

	}
}
