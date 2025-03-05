package com.eshop.E2ETest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CategoryE2ETest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/categories";
    }

    @Test
    public void testCategoryOperations() {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Get user input for category name
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();

        String categoryBody = "{ \"name\": \"" + categoryName + "\" }";

        // Step 2: Create a new category
        int categoryId =
            given()
                .contentType(ContentType.JSON)
                .body(categoryBody)
            .when()
                .post("/save")
            .then()
                .statusCode(200)
                .body("message", equalTo("Category saved successfully"))
                .extract()
                .path("data.id");  // Extract ID dynamically

        System.out.println("Category created with ID: " + categoryId);

        // Step 3: Retrieve the category
        given()
        .when()
            .get("/get/" + categoryId)
        .then()
            .statusCode(200)
            .body("data.name", equalTo(categoryName));

        // Step 4: Get user input for updated name
        System.out.print("Enter updated category name: ");
        String updatedCategoryName = scanner.nextLine();

        String updatedCategoryBody = "{ \"id\": " + categoryId + ", \"name\": \"" + updatedCategoryName + "\" }";
        given()
            .contentType(ContentType.JSON)
            .body(updatedCategoryBody)
        .when()
            .put("/update")
        .then()
            .statusCode(200)
            .body("message", equalTo("Category updated successfully"));

        System.out.println("Category updated successfully.");

        // Step 5: Ask the user if they want to delete
        System.out.print("Do you want to delete this category? (yes/no): ");
        String deleteChoice = scanner.nextLine();

        if (deleteChoice.equalsIgnoreCase("yes")) {
            given()
            .when()
                .delete("/delete/" + categoryId)
            .then()
                .statusCode(200)
                .body("message", equalTo("Category deleted successfully"));

            System.out.println("Category deleted successfully.");
        } else {
            System.out.println("Category not deleted.");
        }

        scanner.close();
    }
}
