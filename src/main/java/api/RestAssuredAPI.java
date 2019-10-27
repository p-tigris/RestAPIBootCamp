package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredAPI {

    public String baseURI = RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employees";
    String employees = "employees";
    String employeeWithId = "employee/";
    String create = "create";
    String update = "update/";
    String delete = "delete/";

    @Test(enabled = false)
    public void getAllEmployees() {
        Response response = given().when().get(employees).then().assertThat().statusCode(200).extract().response();
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());

        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println(jsonPath.get("employee_salary"));
    }


    @Test(enabled = false)
    public void getOneEmployee() {
        Response response = given().when().get(employeeWithId + 166).then().assertThat().statusCode(200).extract().response();
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());

        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println(jsonPath.get("employee_name"));
    }

    @Test(enabled = false)
    public void testPostData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Shoumik Dhar");
        jsonObject.put("salary", "80000");
        jsonObject.put("age", "28");

        Response response = given().header("Content-Type", "application/json")
                .body(jsonObject.toString()).when().post(create).then().statusCode(200).extract().response();
        System.out.println(response.asString());
    }

    @Test(enabled =false)
    public void testPutCall() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "someone");
        jsonObject.put("salary", "100000");
        jsonObject.put("age", "32");

        Response response = given().contentType(ContentType.JSON).body(jsonObject.toString()).put(update + 2063);
        System.out.println(response.getStatusCode());

    }

    @Test(enabled = true)
    public void testDelete() {
        Response response = given().contentType(ContentType.JSON).delete(delete + 2063);
        System.out.println(response.statusCode());
    }
}