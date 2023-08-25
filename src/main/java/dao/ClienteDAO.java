package dao;

import java.util.List;
import java.util.Set;

import modelo.Cliente;
import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
//import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;

public interface ClienteDAO extends DaoGenerico<Cliente, Long> {
	/* ****** Métodos Genéricos ******* */

	@RecuperaObjeto
	Cliente recuperaumClienteePassagens(long numero) throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Cliente> recuperaListaCliente();

//	@RecuperaUltimoOuPrimeiro
//	Cliente recuperaPrimeiroProduto() throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Cliente> recuperaClienteePassagems();

	@RecuperaConjunto
	Set<Cliente> recuperaConjuntoClientesPassagems();

	/* ****** Métodos não Genéricos ******* */

	// Um método definido aqui, que não seja anotado, deverá ser
	// implementado como final em ProdutoDAOImpl.
}
