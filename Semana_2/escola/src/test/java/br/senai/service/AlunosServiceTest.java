package br.senai.service;

import br.senai.dao.AlunoDAO;
import br.senai.exception.RegistroExistenteException;
import br.senai.exception.RegistroNaoEncontradoException;
import br.senai.model.Aluno;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.senai.TestHelper.obterAluno;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class AlunosServiceTest {

    @Mock
    private AlunoDAO alunoDAO;

    @InjectMocks
    private AlunosService service;

    @Test
    @DisplayName("Quando aluno não existente. Deve lançar exceção.")
    void obter_falha() {
        Mockito.when(alunoDAO.find(anyInt())).thenReturn(Optional.empty());
        assertThrows(RegistroNaoEncontradoException.class, () -> service.obter(1));
    }

    @Test
    @DisplayName("Quando aluno existe. Deve retornar um aluno instanciado")
    void obter_sucesso() {
        Aluno aluno = obterAluno();
        Mockito.when(alunoDAO.find(anyInt())).thenReturn(Optional.of(aluno));
        Aluno result = service.obter(1);
        assertNotNull(result);
        assertInstanceOf(Aluno.class, result);
    }

    @Test
    @DisplayName("Quando aluno não existente. Deve inserir com sucesso")
    void inserir_sucesso() {
        Aluno aluno = obterAluno();
        Aluno result = service.inserir(aluno);
        assertNotNull(result);
        assertInstanceOf(Aluno.class, result);
    }
}