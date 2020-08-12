package com.mk.springdata.product;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.mk.springdata.product.entities.Product;
import com.mk.springdata.product.repos.ProductRepository;

@SpringBootTest
class ProductdataApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreate() {
		Product product = new Product("P1", "Product 1", 1000.00);
		productRepository.save(product);
	}

	@Test
	public void testRead() {
		Optional<Product> productOptional = productRepository.findById(1);

		Product product = null;

		if (productOptional.isPresent()) {
			product = productOptional.get();
			System.out.println("==================> " + product);
		}

		assertNotNull(product);
	}

	@Test
	public void testUpdate() {
		Optional<Product> productOptional = productRepository.findById(1);

		Product product = null;

		if (productOptional.isPresent()) {
			product = productOptional.get();
			System.out.println("Update ==================> " + product);
			product.setPrice(5000.00);
			productRepository.save(product);
		}

	}

	@Test
	public void testDelete() {
		Optional<Product> productOptional = productRepository.findById(1);

		Product product = null;

		if (productOptional.isPresent()) {
			product = productOptional.get();
			System.out.println("Delete ==================> " + product);
			productRepository.delete(product);
		}

	}

	@Test
	public void testCount() {
		System.out.println("Product Count : " + productRepository.count());
	}

	@Test
	public void testFindByName() {

		List<Product> products = productRepository.findByName("P1");
		products.forEach(p -> System.out.println(p));
	}

	@Test
	public void testFindByNameAndDesc() {

		List<Product> products = productRepository.findByNameAndDesc("P2", "Product 2");
		products.forEach(p -> System.out.println(p));
	}

	@Test
	public void testfindByPriceGreaterThan() {
		List<Product> products = productRepository.findByPriceGreaterThan(200.00);
		products.forEach(p -> System.out.println(p));
	}

	@Test
	public void testFindByDescContains() {
		List<Product> products = productRepository.findByDescContains("Pro");
		products.forEach(p -> System.out.println(p));
	}

	@Test
	public void testfindByPriceBetween() {
		List<Product> products = productRepository.findByPriceBetween(200d, 800d);
		products.forEach(p -> System.out.println(p));
	}

	@Test
	public void testFindByDescLike() {
		List<Product> products = productRepository.findByDescLike("%Pro%");
		products.forEach(p -> System.out.println(p));
	}

	@Test
	public void testFindByIdIn() {
		List<Product> products = productRepository.findByIdIn(Arrays.asList(1, 2, 3, 4, 5), PageRequest.of(0, 2));
		products.forEach(p -> System.out.println(p));
	}

	@Test
	public void testFindAllPaging() {

		Page<Product> results = productRepository.findAll(PageRequest.of(1, 2));

		results.forEach(p -> System.out.println(p));
	}

	@Test
	public void testFindAllSorting() {
		/*
		 * productRepository.findAll(Sort.by(Direction.DESC, "name", "price"))
		 * .forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
		 */
		
		productRepository.findAll(Sort.by(new Sort.Order(Direction.DESC,"name"), new Sort.Order(Direction.DESC, "price")))
		.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
	}
	
	@Test
	public void testFindAllPagingAndSorting() {
		productRepository.findAll(PageRequest.of(0, 1, Direction.DESC, "name"))
		.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
	}

}
