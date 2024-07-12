package com.felipesantos.ecommerce.msproduct.product;

import com.felipesantos.ecommerce.msproduct.category.Category;
import com.felipesantos.ecommerce.msproduct.category.CategoryService;
import com.felipesantos.ecommerce.msproduct.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductMappper productMapper;

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the ID :: " + productId));
    }

    @Transactional
    public Integer createProduct(ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        var categoryResponse = categoryService.findById(productRequest.categoryId());
        product.setCategory(Category.of(categoryResponse));
        return productRepository.save(product).getId();
    }

    @Transactional
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productsPurchaseRequest) {
        var productIds = getProductIds(productsPurchaseRequest);
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        validateProductsExistence(productIds, storedProducts);
        var storedProductRequest = sortStoredProductRequest(productsPurchaseRequest);
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        updateAvailableQuantityFromStoredProducts(storedProducts, storedProductRequest, purchasedProducts);
        return purchasedProducts;
    }

    private void updateAvailableQuantityFromStoredProducts(List<Product> storedProducts,
                                                           List<ProductPurchaseRequest> storedProductRequest,
                                                           List<ProductPurchaseResponse> purchasedProducts) {
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedProductRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID :: " + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
    }

    private List<Integer> getProductIds(List<ProductPurchaseRequest> productsPurchaseRequest) {
        return productsPurchaseRequest.stream()
                .map(ProductPurchaseRequest::productId)
                .collect(Collectors.toList());
    }

    private List<ProductPurchaseRequest> sortStoredProductRequest(List<ProductPurchaseRequest> productsPurchaseRequest) {
        return productsPurchaseRequest.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .collect(Collectors.toList());
    }

    private void validateProductsExistence(List<Integer> productIds, List<Product> storedProducts) {
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists");
        }
    }
}
