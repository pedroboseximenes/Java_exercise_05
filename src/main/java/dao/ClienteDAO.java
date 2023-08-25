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
	/* ****** M�todos Gen�ricos ******* */

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

	/* ****** M�todos n�o Gen�ricos ******* */

	// Um m�todo definido aqui, que n�o seja anotado, dever� ser
	// implementado como final em ProdutoDAOImpl.
}
