package ptithcm.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import ptithcm.entity.Cart;
import ptithcm.entity.CartItem;
import ptithcm.entity.Category;
import ptithcm.entity.Product;
import ptithcm.entity.User;
import ptithcm.entity.api;

@Repository
public class serviceimp implements service {

	@Override
	public List<Product> getListProduct(String url) {

		List<Product> list = new ArrayList<Product>();

		HttpHandler sh = new HttpHandler();

		String jsonStr = sh.makeServiceCall(url);
		if (jsonStr != null) {
			try {
				JSONArray jsonArray = new JSONArray(jsonStr);
				// looping through All Contacts
				int i = 0;
				while (i < jsonArray.length()) {
					JSONObject c = jsonArray.getJSONObject(i++);
					int proId = c.getInt("Id");
					String name = c.getString("Name");
					Float price = c.getFloat("Price");
					Integer discount = c.getInt("Discount");
					Integer category = c.getInt("CateId");
					String description = c.getString("Description");
					String information = c.getString("Information");
					String image = c.getString("Image");
					System.out.println(description);
					list.add(new Product(proId, name, price, discount, category, description, information, image));
				}
			} catch (final JSONException e) {

			}
		}

		return list;
	}

	@Override
	public List<Product> getListProductWithFirstAndMaxResult(int first, int max, String url) {
		List<Product> list = new ArrayList<Product>();

		HttpHandler sh = new HttpHandler();

		String jsonStr = sh.makeServiceCall(url);
		if (jsonStr != null) {
			try {
				JSONArray jsonArray = new JSONArray(jsonStr);
				// looping through All Contacts
				int i = 0;
				int temp = first;
				while (i < jsonArray.length() && i < max && jsonArray.length() < first) {
					JSONObject c = jsonArray.getJSONObject(first++);
					int proId = c.getInt("Id");
					String name = c.getString("Name");
					Float price = c.getFloat("Price");
					Integer discount = c.getInt("Discount");
					Integer category = c.getInt("CateId");
					String description = c.getString("Description");
					String information = c.getString("Information");
					String image = c.getString("Image");

					list.add(new Product(proId, name, price, discount, category, description, information, image));
				}
			} catch (final JSONException e) {

			}
		}
		return list;
	}

	@Override
	public List<Category> getListCategory(String url) {
		List<Category> list = new ArrayList<Category>();

		HttpHandler sh = new HttpHandler();

		String jsonStr = sh.makeServiceCall(url);
		if (jsonStr != null) {
			try {
				JSONArray jsonArray = new JSONArray(jsonStr);
				// looping through All Contacts
				int i = 0;
				while (i < jsonArray.length()) {
					JSONObject c = jsonArray.getJSONObject(i++);

					Integer CateId = c.getInt("CateId");
					String CateName = c.getString("CateName");
					String CateImage = c.getString("CateImage");

					list.add(new Category(CateId, CateName, CateImage));
				}
			} catch (final JSONException e) {

			}
		}
		return null;
	}

	@Override
	public Product getListAProductByID(int id) {

		Product p = null;
		HttpHandler sh = new HttpHandler();
		String url = api.API + "Product/" + String.valueOf(id);
		String jsonStr = sh.makeServiceCall(url);
		System.out.println(jsonStr);
		if (jsonStr != null) {
			try {

				JSONObject c = new JSONObject(jsonStr);
				int proId = c.getInt("Id");

				String name = c.getString("Name");
				Float price = c.getFloat("Price");
				Integer discount = c.getInt("Discount");
				Integer category = c.getInt("CateId");
				String description = c.getString("Description");
				String information = c.getString("Information");
				String image = c.getString("Image");

				p = new Product(proId, name, price, discount, category, description, information, image);
				return p;
			} catch (final JSONException e) {
				System.out.println(e.getMessage().toString());
				return null;
			}
		}
		return p;
	}

	@Override
	public List<User> getListUser() {
		List<User> list = new ArrayList<User>();

		HttpHandler sh = new HttpHandler();
		String url = api.API + "User";
		String jsonStr = sh.makeServiceCall(url);
		if (jsonStr != null) {
			try {
				JSONArray jsonArray = new JSONArray(jsonStr);
				// looping through All Contacts
				int i = 0;
				while (i < jsonArray.length()) {
					JSONObject c = jsonArray.getJSONObject(i++);
					int Id = c.getInt("Id");
					String name = c.getString("Name");
					String Email = c.getString("Email");
					String Phone = c.getString("Phone");
					String Address = c.getString("Address");
					String Username = c.getString("Username");
					String Password = c.getString("Password");
					String Avatar = c.getString("Avatar");
					int RoleId = c.getInt("RoleId");
					list.add(new User(Id, name, Email, Phone, Address, Username, Password, Avatar, RoleId));
				}
			} catch (final JSONException e) {

			}
		}
		return list;
	}

	@Override
	public User getUserbyUsernameAndPassword(String userName, String passWord) {
		User p = null;
		HttpHandler sh = new HttpHandler();
		String url = api.API + "User?userName=" + userName + "&passWord=" + passWord;
		System.out.println(url);
		String jsonStr = sh.makeServiceCall(url);
		System.out.println(jsonStr);
		if (jsonStr != null) {
			try {
				JSONObject c = new JSONObject(jsonStr);

				int Id = c.getInt("Id");
				String name = c.getString("Name");
				String Email = c.getString("Email");
				String Phone = c.getString("Phone");
				String Address = c.getString("Address");
				String Username = c.getString("Username");
				String Password = c.getString("Password");
				String Avatar = null;
				int RoleId = c.getInt("RoleId");
				p = new User(Id, name, Email, Phone, Address, Username, Password, Avatar, RoleId);
				return p;
			} catch (final JSONException e) {
				System.out.println(e.getMessage().toString());
				return null;
			}
		}
		return p;
	}

	@Override
	public User getUserbyUsername(String userName) {
		User p = null;
		HttpHandler sh = new HttpHandler();
		String url = api.API + "User?userName=" + userName;
		String jsonStr = sh.makeServiceCall(url);
		System.out.println(jsonStr);
		if (jsonStr != null) {
			try {
				JSONObject c = new JSONObject(jsonStr);

				int Id = c.getInt("Id");
				String name = c.getString("Name");
				String Email = c.getString("Email");
				String Phone = c.getString("Phone");
				String Address = c.getString("Address");
				String Username = c.getString("Username");
				String Password = c.getString("Password");
				String Avatar = c.getString("Avatar");
				int RoleId = c.getInt("RoleId");
				p = new User(Id, name, Email, Phone, Address, Username, Password, Avatar, RoleId);
				return p;
			} catch (final JSONException e) {
				System.out.println(e.getMessage().toString());
				return null;
			}
		}
		return p;
	}

	@Override
	public List<CartItem> getCartOfUser(int id, int isPaid) {
		List<CartItem> list = new ArrayList<CartItem>();

		HttpHandler sh = new HttpHandler();
		String url = api.API + "CartItem?userID=" + id + "&isPaid=" + isPaid;
		String jsonStr = sh.makeServiceCall(url);
		if (jsonStr != null) {
			try {
				JSONArray jsonArray = new JSONArray(jsonStr);
				// looping through All Contacts
				int i = 0;
				while (i < jsonArray.length()) {
					JSONObject c = jsonArray.getJSONObject(i++);
					String name = c.getString("Name");
					Float price = c.getFloat("Price");
					Integer discount = c.getInt("Discount");
					Integer Quantity = c.getInt("Quantity");
					Integer Id = c.getInt("Id");
					Integer idCartitem = c.getInt("idCartitem");

					list.add(new CartItem(name, discount, idCartitem, Quantity, price, Id));
				}
			} catch (final JSONException e) {

			}
		}

		return list;
	}

	@Override
	public int returnAmountCartOfUsername(int user, int isPaid) {
		HttpHandler sh = new HttpHandler();
		String url = api.API + "Cart?userID=" + user + "&isPaid=" + isPaid;
		String jsonStr = sh.makeServiceCall(url);
		return Integer.parseInt(jsonStr.trim());
	}

	@Override
	public int PUTincreaseCartItemByID(int idCartItem) {
		String tempt = api.API + "CartItem?cartItemId=" + idCartItem;
		URL url = null;
		try {
			url = new URL(tempt);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("PUT");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);

			OutputStream os = con.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.flush();
			writer.close();
			os.close();

			con.connect();
			StringBuilder sb = new StringBuilder();
			int HttpResult = con.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println(sb.toString());
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println(sb.toString());
				System.out.println(con.getResponseMessage());
			}
		}

		catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}

	@Override
	public Cart getCartbyUseridAndIspaid(int user, int isPaid) {
		Cart list = new Cart();

		HttpHandler sh = new HttpHandler();
		String url = api.API + "Cart?userID2=" + user + "&isPaid=" + isPaid;
		String jsonStr = sh.makeServiceCall(url);
		if (jsonStr != null) {
			try {
				JSONArray arr = new JSONArray(jsonStr);
				JSONObject c = arr.getJSONObject(0);

				Integer Id = c.getInt("Id");
				System.out.print(Id);
				Cart ct = new Cart();
				ct.setCartId(Id);
				return ct;
			} catch (final JSONException e) {

			}
		}

		return null;
	}

	@Override
	public int POSTCartItem(CartItem c) {
		String tempt = api.API + "CartItem";
		URL url = null;
		try {
			url = new URL(tempt);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);
			String input = "{\n" + "        \"Quantity\": \"" + c.getQuantity() + "\",\n" + "        \"UnitPrice\": \""
					+ c.getUnitPrice() + "\",\n" + "        \"ProId\": \"" + c.getProid() + "\",\n"
					+ "        \"CartId\": \"" + c.getCartid() + "\",\n" + "    }";
			OutputStream os = con.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(input);
			writer.flush();
			writer.close();
			os.close();

			con.connect();
			StringBuilder sb = new StringBuilder();
			int HttpResult = con.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println(sb.toString());
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println(sb.toString());
				System.out.println(con.getResponseMessage());
			}
		}

		catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}

	@Override
	public int DELETECartItem(int id) {
		String tempt = api.API + "CartItem?cartItem=" + id;
		URL url = null;
		try {
			url = new URL(tempt);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);

			OutputStream os = con.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.flush();
			writer.close();
			os.close();

			con.connect();
			StringBuilder sb = new StringBuilder();
			int HttpResult = con.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println(sb.toString());
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println(sb.toString());
				System.out.println(con.getResponseMessage());
			}
		}

		catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}

	@Override
	public int runURL(String url1, String type) {
		System.out.println(url1);
		URL url = null;
		try {
			url = new URL(url1);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(type);
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);

			OutputStream os = con.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.flush();
			writer.close();
			os.close();

			con.connect();
			StringBuilder sb = new StringBuilder();
			int HttpResult = con.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println(sb.toString());
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println(sb.toString());
				System.out.println(con.getResponseMessage());
			}
		}

		catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}

	@Override
	public List<Cart> getCartByUser(int userID) {
		List<Cart> list = new ArrayList<Cart>();
		HttpHandler sh = new HttpHandler();
		String url = api.API + "Cart?userID3=" + userID;
		String jsonStr = sh.makeServiceCall(url);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		if (jsonStr != null) {
			try {
				JSONArray jsonArray = new JSONArray(jsonStr);
				// looping through All Contacts
				int i = 0;
				while (i < jsonArray.length()) {
					JSONObject c = jsonArray.getJSONObject(i++);
					int Id = c.getInt("Id");
					int UserId = c.getInt("UserId");
					Date BuyDate = (Date) dateFormat.parse(c.getString("BuyDate"));
					int IsPaid = c.getInt("IsPaid");
					list.add(new Cart(Id, UserId, BuyDate, IsPaid));
				}
			} catch (final JSONException | ParseException e) {

			}
		}
		return list;
	}

	@Override
	public List<Cart> getCartByUserAndIspaid(int userID, int isPaid) {
		List<Cart> list = new ArrayList<Cart>();
		HttpHandler sh = new HttpHandler();
		String url = api.API + "Cart?userID1=" + userID + "&isPaid=" + isPaid;
		String jsonStr = sh.makeServiceCall(url);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		if (jsonStr != null) {
			try {
				JSONArray jsonArray = new JSONArray(jsonStr);
				// looping through All Contacts
				int i = 0;
				while (i < jsonArray.length()) {
					JSONObject c = jsonArray.getJSONObject(i++);
					int Id = c.getInt("Id");
					int UserId = c.getInt("UserId");
					Date BuyDate = (Date) dateFormat.parse(c.getString("BuyDate"));
					int IsPaid = c.getInt("IsPaid");
					list.add(new Cart(Id, UserId, BuyDate, IsPaid));
				}
			} catch (final JSONException | ParseException e) {

			}
		}
		return list;
	}

	// quản lý
	@Override
	public int POSTProduct(Product p) {
		String tempt = api.API + "Product";
		URL url = null;
		try {
			url = new URL(tempt);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);
			String input = "{\n" + "        \"Name\": \"" + p.getName() + "\",\n" + "        \"Price\": \""
					+ p.getPrice() + "\",\n" + "        \"Discount\": \"" + p.getDiscount() + "\",\n"
					+ "        \"CateId\": \"" + p.getCategory() + "\",\n" + "        \"Description\": \""
					+ p.getDescription() + "\",\n" + "        \"Information\": \"" + p.getInformation() + "\",\n"
					+ "        \"Image\": \"" + p.getImage() + "\",\n" + "    }";
			OutputStream os = con.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(input);
			writer.flush();
			writer.close();
			os.close();

			con.connect();
			StringBuilder sb = new StringBuilder();
			int HttpResult = con.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println(sb.toString());
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println(sb.toString());
				System.out.println(con.getResponseMessage());
			}
		}

		catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}
	@Override
	public int PUTProduct(Product p,int proid) {
		String tempt = api.API + "Product/"+proid;
		URL url = null;
		try {
			url = new URL(tempt);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("PUT");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);
			String input = "{\n" + "        \"Name\": \"" + p.getName() + "\",\n" + "        \"Price\": \""
					+ p.getPrice() + "\",\n" + "        \"Discount\": \"" + p.getDiscount() + "\",\n"
					+ "        \"CateId\": \"" + p.getCategory() + "\",\n" + "        \"Description\": \""
					+ p.getDescription() + "\",\n" + "        \"Information\": \"" + p.getInformation() + "\",\n"
					+ "        \"Image\": \"" + p.getImage() + "\",\n" + "    }";
			OutputStream os = con.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(input);
			writer.flush();
			writer.close();
			os.close();

			con.connect();
			StringBuilder sb = new StringBuilder();
			int HttpResult = con.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println(sb.toString());
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println(sb.toString());
				System.out.println(con.getResponseMessage());
			}
		}

		catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}
	@Override
	public List<Cart> getListCard() {
		{
			DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
			List<Cart> list = new ArrayList<Cart>();
			HttpHandler sh = new HttpHandler();
			String url = api.API + "Cart";
			String jsonStr = sh.makeServiceCall(url);
			if (jsonStr != null) {
				try {
					JSONArray jsonArray = new JSONArray(jsonStr);
					// looping through All Contacts
					int i = 0;
					while (i < jsonArray.length()) {
						JSONObject c = jsonArray.getJSONObject(i++);
						int Id = c.getInt("Id");
						int UserId = c.getInt("UserId");
						java.util.Date BuyDate = formatter2.parse(c.getString("BuyDate"));
						int IsPaid = c.getInt("IsPaid");
						list.add(new Cart(Id, UserId, BuyDate, IsPaid));
					}
				} catch (final JSONException | ParseException e) {

				}
			}
			return list;
		}
	}

	@Override
	public Cart getCardByCardID(int id) {
		{
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
			HttpHandler sh = new HttpHandler();
			String url = api.API + "Cart?cartID=" + id;
			System.out.print(url);
			String jsonStr = sh.makeServiceCall(url);
			System.out.print(jsonStr);
			if (jsonStr != null) {
				try {
					JSONArray array = new JSONArray(jsonStr);
					JSONObject c = array.getJSONObject(0);
					int Id = c.getInt("Id");
					int UserId = c.getInt("UserId");
					java.util.Date BuyDate = formatter2.parse(c.getString("BuyDate"));
					int IsPaid = c.getInt("IsPaid");
					System.out.println(IsPaid);
					return new Cart(Id, UserId, BuyDate, IsPaid);
				} catch (final JSONException | ParseException e) {

				}
			}
			return null;
		}
	}

	@Override
	public List<CartItem> getCartItemOfCardID(int id) {
		List<CartItem> list = new ArrayList<CartItem>();

		HttpHandler sh = new HttpHandler();
		String url = api.API + "CartItem?cartID=" + id;
		String jsonStr = sh.makeServiceCall(url);
		if (jsonStr != null) {
			try {
				JSONArray jsonArray = new JSONArray(jsonStr);
				// looping through All Contacts
				int i = 0;
				while (i < jsonArray.length()) {
					JSONObject c = jsonArray.getJSONObject(i++);
					String name = c.getString("Name");
					Float price = c.getFloat("Price");
					Integer discount = c.getInt("Discount");
					Integer Quantity = c.getInt("Quantity");
					Integer Id = c.getInt("Id");
					Integer idCartitem = c.getInt("idCartitem");

					list.add(new CartItem(name, discount, idCartitem, Quantity, price, Id));
				}
			} catch (final JSONException e) {

			}
		}

		return list;
	}

	public void addCartUser(Cart c) {
		String tempt = api.API + "Cart";
		URL url = null;
		try {
			url = new URL(tempt);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);
			String input = "{\n" + "        \"User\": null,\n" + "        \"UserId\": \"" + c.getUserid() + "\",\n"
					+ "        \"BuyDate\": \"" + c.getBuyDate() + "\",\n" + "        \"IsPaid\": \"" + c.getIsPaid()
					+ "\",\n" + "    }";
			System.out.println(input);

			OutputStream os = con.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(input);
			writer.flush();
			writer.close();
			os.close();

			con.connect();
			StringBuilder sb = new StringBuilder();
			int HttpResult = con.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println(sb.toString());
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println(sb.toString());
				System.out.println(con.getResponseMessage());
			}
		}

		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
