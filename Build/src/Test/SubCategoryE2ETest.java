package com.eshop.E2ETest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SubCategoryE2ETest {

    private static String categoryName;   
    private static String subCategoryName;

    @BeforeAll
    public static void setup() {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for category and subcategory names
        System.out.print("Enter Category Name: ");
        categoryName = scanner.nextLine();

        System.out.print("Enter Subcategory Name: ");
        subCategoryName = scanner.nextLine();

        scanner.close();

        // Use the user-provided values in API requests
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/categories/" + categoryName + "/subcategories";
    }

    @Test
    public void testSubCategoryOperations() {
        String subCategoryBody = "{ \"name\": \"" + subCategoryName + "\", \"description\": \"Latest smartphones\" }";

        // Step 1: Add a subcategory
        given()
            .contentType(ContentType.JSON)
            .body(subCategoryBody)
        .when()
            .post()
        .then()
            .statusCode(200)
            .body("message", equalTo("Subcategory added successfully"));

        // Step 2: Retrieve the subcategory
        given()
        .when()
            .get("/" + subCategoryName)
        .then()
            .statusCode(200)
            .body("data.name", equalTo(subCategoryName));

        // Step 3: Update the subcategory
        String updatedSubCategoryBody = "{ \"name\": \"" + subCategoryName + "\", \"description\": \"Updated description\" }";

        given()
            .contentType(ContentType.JSON)
            .body(updatedSubCategoryBody)
        .when()
            .put()
        .then()
            .statusCode(200)
            .body("message", equalTo("Subcategory updated successfully"));

        // Step 4: Delete the subcategory
        given()
        .when()
            .delete("/" + subCategoryName)
        .then()
            .statusCode(200)
            .body("message", equalTo("Subcategory deleted successfully"));

        // Step 5: Verify subcategory is deleted
        given()
        .when()
            .get("/" + subCategoryName)
        .then()
            .statusCode(404)
            .body("message", equalTo("Subcategory not found"));
    }
}
