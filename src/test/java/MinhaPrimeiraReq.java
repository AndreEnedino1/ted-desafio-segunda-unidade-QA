import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MinhaPrimeiraReq {

    @BeforeEach
    public void configTest(){
        RestAssured.baseURI = "https://reqres.in/";
    }


    @Test
    public void teste01(){

        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(HttpStatus.SC_OK).log().all();

    }

    @Test
    public void teste02(){

        String json = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .body(json)
                .post("api/users")
                .then().statusCode(HttpStatus.SC_CREATED).log().all();
    }

    @Test
    public void teste03(){
        Map<String, Object> parameros = new HashMap<>();
        parameros.put("name","morpheus");
        parameros.put("job", "leader");

        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .body(parameros)
                .post("api/users")
                .then().statusCode(HttpStatus.SC_CREATED).log().all();
    }

    @Test
    public void teste04(){

        UserRequest parametros = new UserRequest("André", "Programador");

        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .body(parametros)
                .post("api/users")
                .then().statusCode(HttpStatus.SC_CREATED).log().all();

    }

    @Test
    public void teste05(){
        Map<String, Object> parameros = new HashMap<>();
        parameros.put("name","morpheus");
        parameros.put("job", "pedro");
        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .body(parameros)
                .put("api/users/2")
                .then().statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void testeDelete(){
        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .delete("/api/users/12")
                .then().statusCode(HttpStatus.SC_NO_CONTENT).log().all();
    }
}
