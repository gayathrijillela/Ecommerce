package com.eshop.E2ETest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CartE2ETest {

    @BeforeAll
    public static void setup() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";  // API Base URL
        RestAssured.basePath = "/cart";  // Base path for cart-related operations
    }

    @Test
    public void testCartOperationsWithUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter product ID to add to cart: ");
        Long productId = scanner.nextLong();

        // Create JSON request body
        String cartBody = "{ \"userName\": \"" + username + "\", \"productId\": " + productId + " }";

        // Step 1: Add product to cart
        System.out.println("\nAdding product to cart...");
        given()
            .contentType(ContentType.JSON)
            .body(cartBody)
        .when()
            .post("/add")  
        .then()
            .statusCode(200)
            .body("message", equalTo("Product added to cart successfully"));

        // Step 2: Retrieve the cart
        System.out.println("\nRetrieving cart contents...");
        given()
            .queryParam("userName", username)
        .when()
            .get("")
        .then()
            .statusCode(200)
            .body("message", equalTo("Cart retrieved successfully"));

        // Step 3: Remove product from cart
        System.out.println("\nRemoving product from cart...");
        given()
            .contentType(ContentType.JSON)
            .body(cartBody)
        .when()
            .delete("/remove")
        .then()
            .statusCode(200)
            .body("message", equalTo("Product removed from cart successfully"));

        // Step 4: Verify cart is empty
        System.out.println("\nVerifying if cart is empty...");
        given()
            .queryParam("userName", username)
        .when()
            .get("")
        .then()
            .statusCode(404)
            .body("message", equalTo("Cart not found"));

        scanner.close(); // Close scanner after input
    }
}
