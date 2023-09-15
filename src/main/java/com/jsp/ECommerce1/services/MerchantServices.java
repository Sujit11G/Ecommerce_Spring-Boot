package com.jsp.ECommerce1.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jsp.ECommerce1.dao.MerchantDao;
import com.jsp.ECommerce1.dto.Merchant;
import com.jsp.ECommerce1.dto.Product;
import com.jsp.ECommerce1.repository.MerchantRepository;
import com.jsp.ECommerce1.repository.ProductRepository;

@Service
public class MerchantServices {

	@Autowired
	MerchantDao merchantDao;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	private MerchantRepository merchantRepository;

	public Merchant saveMerchant(Merchant merchant) {

		return merchantDao.saveMerchant(merchant);

	}

	public String saveProductbyMerchant(Product product, int mId) {
		Optional<Merchant> merchant = merchantRepository.findById(mId);

		if (merchant.isPresent()) {

			String productName = product.getName();
			int productQuantity = product.getQuantity();

			List<Product> products = merchant.get().getProducts();

			for (Product existingProduct : products) {
				if (existingProduct.getName().equals(productName)) {
					existingProduct.setQuantity(existingProduct.getQuantity() + productQuantity);
					productRepository.save(existingProduct);
					return "Product updated:\n" + productName + " - Quantity: " + existingProduct.getQuantity() + " in "
							+ merchant.get().getId() + "'s store";
				}
			}

			product.setMerchant(merchant.get());
			merchant.get().getProducts().add(product);
			productRepository.save(product);
			merchantRepository.save(merchant.get());

			return "Product added:\n" + product.getName() + " - Quantity: " + product.getQuantity() + " in "
					+ merchant.get().getId() + "'s store";
		}

		else {
			return "Merchant with ID " + mId + " not found";

		}

	}

}
