package dao;

import java.util.List;

import modelo.Passagem;
import modelo.Cliente;
import anotacao.RecuperaLista;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;

public interface PassagemDAO extends DaoGenerico<Passagem, Long> {
	@RecuperaLista
	List<Passagem> recuperaListaPassagems();

	@RecuperaUltimoOuPrimeiro
	Passagem recuperaUltimaPassagem(Cliente cliente) throws ObjetoNaoEncontradoException;
}
