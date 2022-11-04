import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TedUnidade2 {

    @BeforeEach
    public void configtest(){
        RestAssured.baseURI = "https://reqres.in/";
    }

    //############ DELETE ##############
    @Test
    public void testeDelete(){
        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .delete("/api/users/2")
                .then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    //################## GET ###################
    @Test
    public void testeGet01() {
        RestAssured.given()
                .when()
                .get("api/users?page=2")
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void testeGet02() {
        RestAssured.given()
                .when()
                .get("api/users/2")
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void testeGet03() {
        int teste3 = RestAssured.given()
                .when()
                .get("api/users/23").getStatusCode();

        Assertions.assertEquals(404, teste3);

    }

    @Test
    public void testeGet04() {
        RestAssured.given()
                .when()
                .get("/api/unknown")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testeGet05() {
        RestAssured.given()
                .when()
                .get("/api/unknown/2")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testeGet06() {
        int teste6 = RestAssured.given()
                .when()
                .get("/api/unknown/23").getStatusCode();

        Assertions.assertEquals(404,teste6);
    }

    //############ POST ##############
    @Test
    public void testePost01(){
        UserRequest parametros = new UserRequest("André", "Programador");

        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .body(parametros)
                .post("api/users")
                .then().statusCode(HttpStatus.SC_CREATED).log().all();

    }

    @Test
    public void testePost02(){
        UserRegisterTed2 user = new UserRegisterTed2("eve.holt@reqres.in", "pistol");
        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .body(user)
                .post("/api/register")
                .then().statusCode(HttpStatus.SC_OK).log().all();

    }

    @Test
    public void testePost03(){
        Map<String, Object> parameros = new HashMap<>();
        parameros.put("email","sydney@fife");
        int status = RestAssured.given().contentType(ContentType.JSON)
                .when()
                .body(parameros)
                .post("/api/register").getStatusCode();

        Assertions.assertEquals(400, status);
    }

    @Test
    public void testePost04(){
        UserRegisterTed2 user = new UserRegisterTed2("eve.holt@reqres.in", "pistol");
        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .body(user)
                .post("/api/login")
                .then().statusCode(HttpStatus.SC_OK).log().all();

    }

    @Test
    public void testePost05(){
        Map<String, Object> parameros = new HashMap<>();
        parameros.put("email","peter@klaven");
        int status = RestAssured.given().contentType(ContentType.JSON)
                .when()
                .body(parameros).log().all()
                .post("/api/register").getStatusCode();

        Assertions.assertEquals(400, status);

    }


}
