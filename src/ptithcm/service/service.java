package ptithcm.service;

import java.util.List;

import org.json.simple.parser.ParseException;

import ptithcm.entity.*;
public interface service {

	public List<Product> getListProduct(String url) throws ParseException;
	public Product getListAProductByID(int id);
	public List<Product> getListProductWithFirstAndMaxResult(int first, int max,String url);
	public List<Category> getListCategory(String url);
	public List<User> getListUser();
	public User getUserbyUsernameAndPassword(String userName, String passWord);
	public User getUserbyUsername(String userName);
	public List<CartItem> getCartOfUser(int id, int isPaid);
	public int returnAmountCartOfUsername(int user,int isPaid);
	public int PUTincreaseCartItemByID(int idCartItem);
	public int POSTCartItem(CartItem c);
	public int DELETECartItem(int id);
	public Cart getCartbyUseridAndIspaid(int user,int isPaid);
	public List<Cart>  getCartByUser(int userID);
	public List<Cart>  getCartByUserAndIspaid(int userID,int isPaid);
	public int runURL(String url,String type);
	
	// quản lý
	
	public int POSTProduct(Product p);
	public List<Cart>  getListCard();
	public Cart  getCardByCardID(int id);
	public List<CartItem> getCartItemOfCardID(int id);
	public void addCartUser(Cart c);
	public int PUTProduct(Product p,int proid);
}
