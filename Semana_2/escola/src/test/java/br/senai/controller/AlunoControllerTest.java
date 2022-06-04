package br.senai.controller;

import br.senai.dto.AlunoDTO;
import br.senai.exception.RegistroExistenteException;
import br.senai.exception.RegistroNaoEncontradoException;
import br.senai.model.Aluno;
import br.senai.service.AlunosService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static br.senai.TestHelper.obterAlunoDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class AlunoControllerTest {

    @Mock
    private AlunosService service;

    @InjectMocks
    private AlunoController controller;

    @Test
    @DisplayName("Quando aluno existente. Deve lançar exceção")
    void inserir_falha() {
        AlunoDTO aluno = obterAlunoDTO();
        Mockito.doThrow(new RegistroExistenteException("Aluno", aluno.getMatricula().toString())).when(service).inserir(Mockito.any(Aluno.class));
        assertThrows(RegistroExistenteException.class, () -> controller.inserir(aluno));
    }

    @Test
    @DisplayName("Quando aluno não existente. Deve inserir com sucesso")
    void inserir_sucesso() {
        AlunoDTO aluno = obterAlunoDTO();
        Response response = controller.inserir(aluno);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        assertInstanceOf(AlunoDTO.class, response.getEntity());
    }

    @Test
    @DisplayName("Quando aluno não existente. Deve lançar exceção")
    void remover_falha() {
        Mockito.doThrow(new RegistroNaoEncontradoException("Aluno", "matricula")).when(service).excluir(anyInt());
        assertThrows(RegistroNaoEncontradoException.class, () -> controller.remover(anyInt()));
    }

    @Test
    @DisplayName("Quando aluno existente.  Deve retornar NO CONTENT")
    void remover_sucesso() {
        Response response = controller.remover(anyInt());
        assertNotNull(response);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        assertNull(response.getEntity());
    }
}