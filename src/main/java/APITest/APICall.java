package APITest;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import javafx.beans.binding.When;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class APICall {
    @Test
    public void getMethod(){

        RestAssured.baseURI="https://reqres.in/api/";

        given().contentType("application/json")
                .when().get("/users/")
                .then().statusCode(200).assertThat()
                .log().all();

    }

    @Test
    public void postMethod(){


        int randomid = new Random().nextInt(1000);

        JsonInputData jsonInputData = new JsonInputData(randomid,"abc@rest.in","Bella","Italiana","abc.jpg" );

        RestAssured.baseURI="https://reqres.in/api/";

        given().header("content-Type","application/json")
                .and()
                .body(jsonInputData)

                .when().post("/post")
                .then()
                .statusCode(201).assertThat()
                .and().log().all();

    }


    @Test
    public void putMethod(){

        RestAssured.baseURI = "https://reqres.in/api/users/";

       Response response;
        response = given()
                .header("Content-Type", "application/json")
                .and()
                .body("{" +
                        "\"id\": 1,"+
                "\"email\":\"george.bluth@reqres.in\" ," +
                "\"first_name\": \"George1\"," +
                "\"last_name\": \"Bluth2\"," +
                "\"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg\"" +
        "}")
                .when().put("/posts/1")
                        .then().statusCode(200).assertThat()
                         .and().log().all()
                         .extract().response();
       JsonPath jsXpath = new JsonPath(response.asString());
       System.out.println("ID is" + jsXpath.get("id"));




    }


    @Test
    public void deleteMethod(){

        RestAssured.baseURI = "https://reqres.in/api/users/";

        given()
                .header("Content-Type", "application/json")
                .when().delete("/posts/2")
                .then().statusCode(204).assertThat()
                .and().log().all();

    }

}
