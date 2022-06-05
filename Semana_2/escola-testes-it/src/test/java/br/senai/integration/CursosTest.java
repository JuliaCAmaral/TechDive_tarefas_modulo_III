package br.senai.integration;

import br.senai.dto.CursoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsNull.notNullValue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CursosTest {

    private ObjectMapper mapper = new ObjectMapper();
    private static String codigo = null;

    @BeforeAll
    public static void preCondicao() {
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/escola-1.0-SNAPSHOT/api";
    }

    @Test
    @Order(1)
    public void criarCurso() throws JsonProcessingException {
        CursoDTO request = new CursoDTO("codigo", "assunto", 1);
        String json = mapper.writeValueAsString(request);
        codigo = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/cursos")
                .then()
                .statusCode(201)
                .body("codigo", notNullValue())
                .extract()
                .path("codigo");
    }
}
