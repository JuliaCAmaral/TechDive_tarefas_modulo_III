package br.senai.service;

import br.senai.dao.InscricoesDAO;
import br.senai.exception.RegistroExistenteException;
import br.senai.exception.RegistroNaoEncontradoException;
import br.senai.model.Inscricao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.senai.TestHelper.obterInscricao;
import static br.senai.TestHelper.obterInscricoes;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class InscricoesServiceTest {

    @Mock
    InscricoesDAO inscricoesDAO;

    @InjectMocks
    InscricoesService service;

    @Test
    @DisplayName("Quando inscrição existente. Deve lançar exceção")
    void inserir_falha() {
        List<Inscricao> inscricoes = obterInscricoes();
        Mockito.when(inscricoesDAO.findAll()).thenReturn(inscricoes);
        Inscricao inscricao = obterInscricao();
        assertThrows(RegistroExistenteException.class, () -> service.inserir(inscricao));
    }

    @Test
    @DisplayName("Quando inscrição não existente. Deve salvar com sucesso")
    void inserir_sucesso() {
        Inscricao inscricao = obterInscricao();
        Mockito.when(inscricoesDAO.findAll()).thenReturn(new ArrayList<>());
        Inscricao result = service.inserir(inscricao);
        assertNotNull(result);
        assertInstanceOf(Inscricao.class, result);
    }

    @Test
    @DisplayName("Quando inscrição não existente. Deve lançar exceção")
    void excluir_falha() {
        Mockito.when(inscricoesDAO.find(anyInt())).thenReturn(Optional.empty());
        assertThrows(RegistroNaoEncontradoException.class, () -> service.excluir(anyInt()));
    }

    @Test
    @DisplayName("Quando inscrição existente. Deve excluir inscrição")
    void excluir_sucesso() {
        Inscricao inscricao = obterInscricao();
        Mockito.when(inscricoesDAO.find(anyInt())).thenReturn(Optional.of(inscricao));
        assertDoesNotThrow(() -> service.excluir(anyInt()));
    }
}