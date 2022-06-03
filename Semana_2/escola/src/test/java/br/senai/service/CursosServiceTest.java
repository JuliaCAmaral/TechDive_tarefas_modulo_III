package br.senai.service;

import br.senai.TestHelper;
import br.senai.dao.CursoDAO;
import br.senai.exception.RegistroExistenteException;
import br.senai.exception.RegistroNaoEncontradoException;
import br.senai.model.Curso;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.senai.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class CursosServiceTest {

    @Mock
    private CursoDAO cursosDAO;

    @InjectMocks
    private CursosService service;

    @Test
    @DisplayName("Quando curso não existente. Deve lançar exceção.")
    void obter_falha() {
        Mockito.when(cursosDAO.find(anyString())).thenReturn(Optional.empty());
        assertThrows(RegistroNaoEncontradoException.class, () -> service.obter("codigo"));
    }

    @Test
    @DisplayName("Quando curso existente. Deve retornar curso instanciado.")
    void obter_sucesso() {
        //given
        Curso curso = obterCurso();
        Mockito.when(cursosDAO.find(anyString())).thenReturn(Optional.of(curso));
        //when
        Curso result = service.obter("codigo");
        assertNotNull(result);
        assertInstanceOf(Curso.class, result);
    }

    @Test
    @DisplayName("Quando curso já existente. Deve lançar exceção")
    void inserirCurso_falha() {
        Curso curso = obterCurso();
        Mockito.when(cursosDAO.find(anyString())).thenReturn(Optional.of(curso));
        assertThrows(RegistroExistenteException.class, () -> service.inserir(curso));
    }

    @Test
    @DisplayName("Quando dados válidos. Deve gravar e retornar Curso")
    void inserirCurso_sucesso() {
        Curso curso = obterCurso();
        Mockito.when(cursosDAO.find(anyString())).thenReturn(Optional.empty());
        Curso result = service.inserir(curso);
        assertNotNull(result);
        assertInstanceOf(Curso.class, result);
    }
}