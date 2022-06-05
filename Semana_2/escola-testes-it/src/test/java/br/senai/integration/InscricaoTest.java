package br.senai.integration;

import br.senai.dto.AlunoPostDTO;
import br.senai.dto.CursoDTO;
import br.senai.dto.InscricaoReqDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsNull.notNullValue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InscricaoTest {

    private ObjectMapper mapper = new ObjectMapper();
    private static Integer idInscricao = null;
    private static Integer idAluno = null;
    private static String codigo = null;

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

    @Test
    @Order(3)
    public void criarInscricao() throws JsonProcessingException {
        InscricaoReqDTO request = new InscricaoReqDTO(idAluno, codigo);
        String json = mapper.writeValueAsString(request);
        idInscricao = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/inscricoes")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract()
                .path("id");
    }

    @Test
    @Order(4)
    public void deletarInscricao() {
        given()
                .when()
                .delete("/inscricoes/{id}", idInscricao)
                .then()
                .statusCode(204);
    }

}
