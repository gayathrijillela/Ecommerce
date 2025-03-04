package com.eshop.sonny.controller;

import com.eshop.sonny.dto.Response.ApiResponseDto;

 import com.eshop.sonny.dto.Response.CartResponseDto;
import com.eshop.sonny.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
@Tag(name = "Shopping Cart", description = "Manages the user's shopping cart")
@PreAuthorize("hasRole('BUYER')")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(summary = "Get user's cart", description = "Retrieves the shopping cart for the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
            @ApiResponse(responseCode = "403", description = "User not authorized"),
            @ApiResponse(responseCode = "404", description = "Cart not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{userName}")
    public ResponseEntity<ApiResponseDto<CartResponseDto>> getCartByUserName(
            @Parameter(description = "Username of the cart owner", required = true) @PathVariable String userName) {
        CartResponseDto cartResponse = cartService.getCartByUserName(userName);
        return ResponseEntity.ok(new ApiResponseDto<>(true, HttpStatus.OK.value(), cartResponse, "Cart retrieved successfully"));
    }

    @Operation(summary = "Add product to cart", description = "Adds a product to the user's shopping cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product or user details"),
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
            @ApiResponse(responseCode = "403", description = "User not authorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/add")
    public ResponseEntity<ApiResponseDto<CartResponseDto>> addProductToCart(
            @Parameter(description = "Username of the cart owner", required = true) @RequestParam String userName,
            @Parameter(description = "ID of the product to add", required = true) @RequestParam Long productId) {
        boolean success = cartService.addProductToCart(userName, productId);
        if (success) {
            CartResponseDto updatedCart = cartService.getCartByUserName(userName);
            return ResponseEntity.ok(new ApiResponseDto<>(true, HttpStatus.OK.value(), updatedCart, "Product added to cart successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponseDto<>(false, HttpStatus.BAD_REQUEST.value(), null, "Failed to add product to cart"));
    }

    @Operation(summary = "Remove product from cart", description = "Removes a product from the user's shopping cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product removed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product or user details"),
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
            @ApiResponse(responseCode = "403", description = "User not authorized") ,
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/remove")
    public ResponseEntity<ApiResponseDto<CartResponseDto>> removeProductFromCart(
            @Parameter(description = "Username of the cart owner", required = true) @RequestParam String userName,
            @Parameter(description = "ID of the product to remove", required = true) @RequestParam Long productId) {
        boolean success = cartService.removeProductFromCart(userName, productId);
        if (success) {
            CartResponseDto updatedCart = cartService.getCartByUserName(userName);
            return ResponseEntity.ok(new ApiResponseDto<>(true, HttpStatus.OK.value(), updatedCart, "Product removed from cart successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponseDto<>(false, HttpStatus.BAD_REQUEST.value(), null, "Failed to remove product from cart"));
    }
}
