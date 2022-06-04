package br.senai.controller;

import br.senai.TestHelper;
import br.senai.dto.CursoDTO;
import br.senai.exception.RegistroExistenteException;
import br.senai.exception.RegistroNaoEncontradoException;
import br.senai.model.Curso;
import br.senai.service.CursosService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class CursosControllerTest {

    @Mock
    private CursosService service;

    @InjectMocks
    private CursosController controller;

    @Test
    @DisplayName("Quando curso existente. Deve lançar exceção")
    void inserir_falha() {
        CursoDTO cursoDTO = TestHelper.obterCursoDTO();
        Mockito.doThrow(new RegistroExistenteException("Curso", cursoDTO.getCodigo())).when(service).inserir(Mockito.any(Curso.class));
        assertThrows(RegistroExistenteException.class, () -> controller.inserir(cursoDTO));
    }

    @Test
    @DisplayName("Quando curso não existente. Deve inserir com sucesso")
    void inserir_sucesso() {
        CursoDTO cursoDTO = TestHelper.obterCursoDTO();
        Response response = controller.inserir(cursoDTO);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        assertInstanceOf(CursoDTO.class, response.getEntity());
    }

    @Test
    @DisplayName("Quando curso não existente. Deve lançar exceção")
    void remover_falha() {
        Mockito.doThrow(new RegistroNaoEncontradoException("Curso", "codigo")).when(service).excluir(anyString());
        assertThrows(RegistroNaoEncontradoException.class, () -> controller.remover("codigo"));
    }

    @Test
    @DisplayName("Quando curso não existente. Deve lançar exceção")
    void remover_sucesso() {
        Response response = controller.remover("codigo");
        assertNotNull(response);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        assertNull(response.getEntity());
    }
}