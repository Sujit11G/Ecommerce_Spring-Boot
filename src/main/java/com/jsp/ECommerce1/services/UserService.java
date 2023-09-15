package com.jsp.ECommerce1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jsp.ECommerce1.dao.UserDao;
import com.jsp.ECommerce1.dto.CartProduct;
import com.jsp.ECommerce1.dto.Cart;
import com.jsp.ECommerce1.dto.Orders;
import com.jsp.ECommerce1.dto.Product;
import com.jsp.ECommerce1.dto.User;
import com.jsp.ECommerce1.repository.CartProductRepository;
import com.jsp.ECommerce1.repository.CartRepository;
import com.jsp.ECommerce1.repository.OrdersRepository;
import com.jsp.ECommerce1.repository.ProductRepository;
import com.jsp.ECommerce1.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CartProductRepository cartProductRepository;

	@Autowired
	OrdersRepository ordersRepository;

	public User saveUser(User user) {

		User saveUser = userDao.SaveUser(user);
		Cart cart = new Cart();

		cart.setUser(saveUser);
		saveUser.setCart(cart);

		cartRepository.save(cart);

		return userDao.SaveUser(saveUser);
	}

	public List<Product> getAllProducts() {

		return productRepository.findAll();
	}

	public String addProductToCart(int uId, int pId) {

		Optional<User> userOptional = userRepository.findById(uId);
		Optional<Product> productOptional = productRepository.findById(pId);

		if (userOptional.isPresent()) {

			if (productOptional.isPresent()) {

				User user = userOptional.get();
				Product product = productOptional.get();

				Cart cart = user.getCart();
				List<CartProduct> cartProduct = cart.getCartProduct();

				for (CartProduct cartproduct : cartProduct) {
					if (cartproduct.getProduct().equals(product)) {
						cartproduct.setQuantity(cartproduct.getQuantity() + 1);

						userRepository.save(user);

//						StringBuilder message = new StringBuilder();
//
//						for (CartProduct cartBag : cartProduct) {
//							message.append(cartBag.getProduct().getName()).append(" added to your cart (")
//									.append(cartBag.getQuantity()).append(")\n");
//						}
//
//						return message.toString();
					}
				}

				CartProduct newBag = new CartProduct();
				newBag.setCart(cart);
				newBag.setProduct(product);
				newBag.setQuantity(1);

				cartProduct.add(newBag);

				userRepository.save(user);

				StringBuilder message = new StringBuilder();

				for (CartProduct cartBag : cartProduct) {
					message.append(cartBag.getProduct().getName()).append(" added to your cart (")
							.append(cartBag.getQuantity()).append(")\n");
				}

				return message.toString();

			} else {
				return "product not found ";
			}

		} else {
			return "User not found ";
		}

	}

//	public String addProductToCart(int uId, int pId) {
//	    Optional<User> user = userRepository.findById(uId);
//	    Optional<Product> product = productRepository.findById(pId);
//
//	    if (user.isPresent() && product.isPresent()) {
//	        Bags existingProduct = product.get().getBags();
//
//	        if (existingProduct == null) {
//	            // Product not in cart, create a new Bags entity
//	            existingProduct = new Bags();
//	            existingProduct.setCart(user.get().getCart());
//	            existingProduct.setProduct(product.get());
//	            existingProduct.setQuantity(1);
//
//	            // Add the new Bags entity to the cart
//	            user.get().getCart().getBags().add(existingProduct);
//	        } else {
//	            // Product already in cart, increment quantity
//	            existingProduct.setQuantity(existingProduct.getQuantity() + 1);
//	        }
//
//	        // Persist changes to the user and existingProduct
//	        userRepository.save(user.get());
//
//	        // Print cart contents in the console
//	        List<Bags> bagsList = user.get().getCart().getBags();
//	        StringBuilder message = new StringBuilder();
//
//	        for (Bags cartProduct : bagsList) {
//	            message.append(cartProduct.getProduct().getName()).append(" added to your cart (")
//	                   .append(cartProduct.getQuantity()).append(")\n");
//	        }
//
//	        // Print the cart contents in the console
//	        System.out.println("Cart Contents:");
//	        System.out.println(message.toString());
//
//	        return message.toString();
//	    } else {
//	        return "User or product not found ";
//	    }
//	}

//	public String orderPlaced(int pId, int uId) {
//	    Optional<User> userOptional = userRepository.findById(uId);
//	    Optional<Product> productOptional = productRepository.findById(pId);
//
//	    if (userOptional.isPresent()) {
//	        User user = userOptional.get();
//	       
//            boolean productInCart = user.getCart().getBags().stream().anyMatch(bag -> bag.getProduct().getId() == pId);
//	        
//	        if (productInCart) {
//	            if (productOptional.isPresent()) {
//	            	 Product product = productOptional.get();
//
//	                if (product.getQuantity() > 0) {
//	                    Orders order = new Orders();
//	                    order.setUser(user);
//
//	                    List<Bags> bags = user.getCart().getBags();
//	                    List<OrderedProduct> orderedProducts = new ArrayList<>();
//	                    
//	                    for (Bags bag : bags) {
//	                        OrderedProduct orderedProduct = new OrderedProduct();
//	                        orderedProduct.setProduct(bag.getProduct());
//	                        orderedProduct.setQuantity(bag.getQuantity());
//	                        orderedProduct.setOrders(order);
//
//	                        orderedProducts.add(orderedProduct);
//	                    }
//	                    
//	                    product.setQuantity(product.getQuantity() - 1);
//
//	                    ordersRepository.save(order);
//	                    userRepository.save(user);
//	                    productRepository.save(product);
//	                    
//
//
//	                    return "Order of " + product.getName() + " has been placed!!";
//	                } else {
//	                    return "Product is out of stock.";
//	                }
//	            } else {
//	                return "No product found with ID " + pId;
//	            }
//	        } else {
//	            return "No product found in your cart.";
//	        }
//	    } else {
//	        return "User Not Found";
//	    }
//	}

	public String orderPlace(int uId,int pId) {
		Optional<User> optionalUser=userRepository.findById(uId);
		Optional<Product> optionalProduct=productRepository.findById(pId);
		
		if(optionalUser.isPresent()) {
			User user=optionalUser.get();
			Cart cart=user.getCart();
			List<CartProduct> cartProducts=cart.getCartProduct();

			boolean productInCart = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getProduct().getId()==pId);
			if(productInCart) {
				if(optionalProduct.isPresent()) {
					Product product=optionalProduct.get();
					
					if(product.getQuantity()>0) {
						Orders orders=new Orders();
						orders.setUser(user);
		                orders.getProducts().add(product); 
		                product.getOrders().add(orders); 
		                product.setQuantity(product.getQuantity()-1);
		                
		                for (CartProduct cartProduct : cartProducts) {
	                        if (cartProduct.getProduct().getId() == pId) {
	                            cartProduct.setQuantity(cartProduct.getQuantity() - 1);
	                            break; // Exit the loop once we've found and updated the CartProduct
	                        }
	                    }
		                
		                ordersRepository.save(orders);
		                productRepository.save(product);
	                    cartProductRepository.saveAll(cartProducts);


		                return "Order placed successfully";
					}else {
		                return "Product is out of stock";

					}
					
				}else {
					return "Product not Found";
				}
			}else {
				return "Product not in Cart";
			}
		}else {
			return "User Not Found";
		}
	}

}
