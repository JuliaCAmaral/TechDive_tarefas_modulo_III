package br.senai.integration;

import br.senai.dto.AlunoPostDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AlunosTest {

    private ObjectMapper mapper = new ObjectMapper();
    private static Integer idAluno = null;

    @BeforeAll
    public static void preCondicao() {
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/escola-1.0-SNAPSHOT/api";
    }

    @Test
    @Order(1)
    public void criarAluno() throws JsonProcessingException {
        AlunoPostDTO request = new AlunoPostDTO("nome");
        String json = mapper.writeValueAsString(request);
        idAluno = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/alunos")
                .then()
                .statusCode(201)
                .body("matricula", notNullValue())
                .extract()
                .path("matricula");
    }

    @Test
    @Order(2)
    public void getAluno() {
        given()
                .when()
                .get("/alunos/{matricula}", idAluno)
                .then()
                .statusCode(200)
                .body("nome", is("nome"));
    }

    @Test
    @Order(3)
    public void deleteAluno() {
        given()
                .when()
                .delete("/alunos/{matricula}", idAluno)
                .then()
                .statusCode(204);
    }
}
