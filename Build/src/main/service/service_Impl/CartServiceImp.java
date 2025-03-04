package com.eshop.sonny.service.service_Impl;

import com.eshop.sonny.dto.Response.CartResponseDto;
import com.eshop.sonny.mapper.mappersClasses.CartMapper;
import com.eshop.sonny.model.Buyer;
import com.eshop.sonny.model.Cart;
import com.eshop.sonny.model.CartProduct;
import com.eshop.sonny.model.Product;
import com.eshop.sonny.repository.BuyerRepository;
import com.eshop.sonny.repository.CartRepository;
import com.eshop.sonny.repository.ProductRepository;
import com.eshop.sonny.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CartServiceImp implements CartService {

    private CartRepository cartRepository;
    private BuyerRepository buyerRepository;
    private ProductRepository productRepository;


    @Override
    public CartResponseDto getCartByUserName(String userName) {
        Buyer buyer = buyerRepository.findByUserName(userName);
        if (buyer == null) {
            throw new RuntimeException("Buyer not found");
        }
        Cart cart = buyer.getCart();

        return CartMapper.carttoCartResponseDto(cart);

    }

    @Override
    // we should make sure that the cascade between cart and cardproduct is ALL
    public boolean addProductToCart(String userName, Long productId) {
        // Fetch buyer and validate
        Optional<Buyer> buyer = Optional.ofNullable(buyerRepository.findByUserNameWithCart(userName).orElseThrow(
                () -> new RuntimeException("Buyer not found")
        ));

        // Fetch product and validate
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Product not found"));

        if(product.getInventoryCount() == 0)
            throw new RuntimeException("Product out of stock");

        Cart cart = buyer.get().getCart();

        CartProduct cartProduct = cart.getCartProducts().stream()
                    .filter(cp -> cp.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElse(null);

        // adding price to the cart


        if (cartProduct != null) {
                // Update quantity if product already exists
                cartProduct.setQuantity((short) (cartProduct.getQuantity() + 1));
        } else {
                // Create new CartProduct and associate with cart
                CartProduct newCartProduct = new CartProduct();
                newCartProduct.setProduct(product);
                newCartProduct.setCart(cart);
                newCartProduct.setQuantity((short) 1);
                cart.getCartProducts().add(newCartProduct);
        }

        // add to the cart total price
        cart.setTotalPrice(cart.getTotalPrice()+product.getProductPrice());

            // Save the cart (or cascade via buyer save)
        cartRepository.save(cart);
        return true;

    }


    @Override
    public boolean removeProductFromCart(String userName, Long productId) {
        Optional<Buyer> buyer = Optional.ofNullable(buyerRepository.findByUserNameWithCart(userName).orElseThrow(
                () -> new RuntimeException("Buyer not found")
        ));


        Product product = productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Product not found"));

        Cart cart = buyer.get().getCart();

        CartProduct cartProduct = cart.getCartProducts().stream()
                .filter(cp -> cp.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (cartProduct == null) {
            throw new RuntimeException("Product not found in the cart");
        } else {
            if(cartProduct.getQuantity() == 1){
                cart.getCartProducts().remove(cartProduct);
            }else {
                cartProduct.setQuantity((short) (cartProduct.getQuantity() - 1));
            }
        }
        // update the cart total price
        cart.setTotalPrice(cart.getTotalPrice()-product.getProductPrice());

        cartRepository.save(cart);
        return true;
    }


}
