package br.senai.controller;

import br.senai.dto.AlunoDTO;
import br.senai.dto.AlunoPostDTO;
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
import static br.senai.TestHelper.obterAlunoPostDTO;
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