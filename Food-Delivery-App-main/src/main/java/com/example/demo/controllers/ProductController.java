package com.example.demo.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.entities.Product;
import com.example.demo.services.ProductServices;

@Controller
public class ProductController 
{
	@Autowired
	private ProductServices productServices;

	//	AddProduct
	@PostMapping("/addingProduct")
	public String addProduct(@ModelAttribute Product product)
	{

		this.productServices.addProduct(product);
		return "redirect:/admin/services";
	}

	// View All Products
//	@GetMapping("/products")
//	public String viewProducts(Model model) {
//		List<Product> products = productServices.getAllProducts();
//		model.addAttribute("products", products);
//		return "Products"; // products.html in templates folder
//	}

//	@PostMapping("/product/search")
//	public String searchProduct(@ModelAttribute("productName") String productName, Model model) {
//		Product product = productServices.getProductByName(productName);
//		if (product != null) {
//			model.addAttribute("product", product);
//		} else {
//			model.addAttribute("message", "Product not available");
//		}
//		return "buy-product"; // Thymeleaf template name
//	}



	//	UpdateProduct
	@GetMapping("/updatingProduct/{productId}")
	public String updateProduct(@ModelAttribute Product product,@PathVariable("productId") int id)
	{

		this.productServices.updateproduct(product, id);
		return "redirect:/admin/services";
	}
	//DeleteProduct
	@GetMapping("/deleteProduct/{productId}")
	public String delete(@PathVariable("productId") int id)
	{
		this.productServices.deleteProduct(id);
		return "redirect:/admin/services";
	}
	
}