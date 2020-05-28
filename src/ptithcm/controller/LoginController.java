package ptithcm.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.Cart;
import ptithcm.entity.Product;
import ptithcm.entity.User;
import ptithcm.service.serviceimp;

@Transactional
@Controller
public class LoginController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	serviceimp sv;
	List<User> listUser;

	public List<User> getListUser() {
		
		listUser = sv.getListUser();
		return listUser;
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String viewLogin(ModelMap mm) {
		mm.put("user", new User());
		mm.put("cart", new Cart());
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String doLogin(ModelMap mm, @ModelAttribute("user") User user, HttpSession session1, BindingResult errors) {



		if (user.getEmail().trim().length() == 0) {
			errors.rejectValue("email", "user", "Vui lòng nhập email!");
		}
		if (user.getPassword().trim().length() == 0) {
			errors.rejectValue("password", "user", "Vui lòng nhập mật khẩu!");
		}
		if (errors.hasErrors()) {
			mm.addAttribute("message", "Vui lòng sửa các lỗi sau đây !");
		} else {
			User x = sv.getUserbyUsernameAndPassword(user.getEmail(), user.getPassword());
			if (x != null) {
				String pw = x.getPassword();
				int role = x.getRoleId();
				String name = x.getName();
				String username = x.getUsername();
				int id = x.getUserId();
				String address = x.getAddress();
				String phone = x.getPhone();
				System.out.println(phone);
				if ((user.getPassword().equals(pw)) && (role != 1)) {
					session1.setAttribute("name", name);
					session1.setAttribute("username", username);
					session1.setAttribute("roleid", role);
					session1.setAttribute("id", id);
					session1.setAttribute("address", address);
					session1.setAttribute("phone", phone);

					return "redirect:/index.html";
				} else if ((user.getPassword().equals(pw)) && (role == 1)) {
					session1.setAttribute("name", name);
					session1.setAttribute("username", username);
					session1.setAttribute("roleid", role);
					session1.setAttribute("id", id);
					session1.setAttribute("address", x.getAddress());
					session1.setAttribute("phone", x.getPhone());

					return "redirect:/manage.html";
				}
			}
			mm.addAttribute("message", "Tên đăng nhập hoặc mật khẩu chưa đúng! Mời nhập lại!");
		}

		return "login";

	}

	@RequestMapping("error")
	public String errorPage() {
		return "error";
	}

	// hàm xử lí insert
	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("user1", new User());
		return "signup";
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("user1") User user1, HttpSession session1,
			BindingResult errors) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
////		- Bắt đầu bằng chữ cái.
//		- Chỉ chứa chữ cái, chữ số và dấu gạch ngang (-).
//		- Chứa một ký tự @, sau @ là tên miền.
//		- Tên miền có thể là domain.xxx.yyy hoặc domain.xxx. Trong đó xxx và yyy là các chữ cái và có độ dài từ 2 trở lên.
		String EMAIL_PATTERN = 
	            "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
	         
		if (user1.getName().trim().length() == 0) {
			errors.rejectValue("name", "user1", "Vui lòng nhập tên!");
		}
		if (user1.getEmail().trim().length() == 0) {
			errors.rejectValue("email", "user1", "Vui lòng nhập email!");
		} else if(Pattern.matches(EMAIL_PATTERN, user1.getEmail().trim())==false) {
			errors.rejectValue("email", "user1", "Vui lòng nhập đúng định dạng email!");
		}
		if (user1.getPassword().trim().length() == 0) {
			errors.rejectValue("password", "user1", "Vui lòng nhập password!");
		}
		if (user1.getUsername().trim().length() == 0) {
			errors.rejectValue("username", "user1", "Vui lòng nhập username!");
		}
		if (user1.getAddress().trim().length() == 0) {
			errors.rejectValue("address", "user1", "Vui lòng nhập địa chỉ!");
		}
		if (user1.getPhone().trim().length() == 0) {
			errors.rejectValue("phone", "user1", "Vui lòng nhập số điện thoại!");
		}
		
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa các lỗi sau đây !");
		} else {
		try {
			dangKy(user1);

			model.addAttribute("message", "Thêm mới thành công !");
			user1 = sv.getUserbyUsername(user1.getUsername());
			Cart cart = new Cart();
			cart.setUserid(user1.getUserId());
			cart.setIsPaid(0);
			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			cart.setBuyDate(date);
			addCartUser(cart);

		} catch (Exception e) {
			t.rollback();
			System.out.println(e.toString());
			model.addAttribute("message", "Thêm mới thất bại !");
		} finally {
			session.close();
		}
		}
		return "signup";
	}

	@RequestMapping(value = "signout")
	public String signout(HttpSession session) {
		session.removeAttribute("name");
		session.removeAttribute("username");
		session.removeAttribute("roleid");
		session.removeAttribute("id");
		session.removeAttribute("address");
		session.removeAttribute("phone");
		session.removeAttribute("cart");
		return "redirect:/index.html";
	}
	private void dangKy(User user) throws IOException {
		URL url = new URL("http://dungcuhoctapapi.azurewebsites.net/api/User");
		try {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		con.setDoInput(true);
		String input = "{\n" + 
				"        \"Name\": \""+user.getName()+"\",\n" + 
				"        \"Email\": \""+user.getEmail()+"\",\n" + 
				"        \"Phone\": \""+user.getPhone()+"\",\n" + 
				"        \"Address\": \""+user.getAddress()+"\",\n" + 
				"        \"Username\": \""+user.getUsername()+"\",\n" + 
				"        \"Password\": \""+user.getPassword()+"\",\n" + 
				"        \"Avatar\": \""+user.getAvatar()+"\",\n" + 
				"        \"RoleId\": \""+user.getRoleId()+"\",\n" + 
				"}";
		System.out.println( input);  
	    
	    OutputStream os = con.getOutputStream();
	    BufferedWriter writer = new BufferedWriter(
	                new OutputStreamWriter(os, "UTF-8"));
	    writer.write(input);
	    writer.flush();
	    writer.close();
	    os.close();

	    con.connect();
		StringBuilder sb = new StringBuilder();  
		int HttpResult = con.getResponseCode(); 
		if (HttpResult == HttpURLConnection.HTTP_OK) {
		    BufferedReader br = new BufferedReader(
		            new InputStreamReader(con.getInputStream(), "utf-8"));
		    String line = null;  
		    while ((line = br.readLine()) != null) {  
		        sb.append(line + "\n");  
		    }
		    br.close();
		    System.out.println( sb.toString());  
		} else {
			BufferedReader br = new BufferedReader(
		            new InputStreamReader(con.getErrorStream(), "utf-8"));
		    String line = null;  
		    while ((line = br.readLine()) != null) {  
		        sb.append(line + "\n");  
		    }
		    br.close();
		    System.out.println( sb.toString());
		    System.out.println(con.getResponseMessage());  
		}  
	}

	catch(Exception e) {
		System.out.println( e.toString());  
	}
	}
	private void addCartUser(Cart cart) throws IOException {
		URL url = new URL("http://dungcuhoctapapi.azurewebsites.net/api/Cart");
		try {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		con.setDoInput(true);
		String input = "{\n" + 
				"        \"User\": null,\n" + 
				"        \"UserId\": \""+cart.getUserid()+"\",\n" + 
				"        \"BuyDate\": \""+cart.getBuyDate()+"\",\n" + 
				"        \"IsPaid\": \""+cart.getIsPaid()+"\",\n" + 
				"    }";
		System.out.println( input);  
	    
	    OutputStream os = con.getOutputStream();
	    BufferedWriter writer = new BufferedWriter(
	                new OutputStreamWriter(os, "UTF-8"));
	    writer.write(input);
	    writer.flush();
	    writer.close();
	    os.close();

	    con.connect();
		StringBuilder sb = new StringBuilder();  
		int HttpResult = con.getResponseCode(); 
		if (HttpResult == HttpURLConnection.HTTP_OK) {
		    BufferedReader br = new BufferedReader(
		            new InputStreamReader(con.getInputStream(), "utf-8"));
		    String line = null;  
		    while ((line = br.readLine()) != null) {  
		        sb.append(line + "\n");  
		    }
		    br.close();
		    System.out.println( sb.toString());  
		} else {
			BufferedReader br = new BufferedReader(
		            new InputStreamReader(con.getErrorStream(), "utf-8"));
		    String line = null;  
		    while ((line = br.readLine()) != null) {  
		        sb.append(line + "\n");  
		    }
		    br.close();
		    System.out.println( sb.toString());
		    System.out.println(con.getResponseMessage());  
		}  
	}

	catch(Exception e) {
		System.out.println( e.toString());  
	}
	}
}
