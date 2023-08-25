package service;

import java.util.List;

import excecao.ProdutoNaoEncontradoException;
import modelo.Cliente;

public interface ClienteAppService {
	long inclui(Cliente umProduto);
	
	void altera(Cliente umProduto) throws ProdutoNaoEncontradoException;
	
	void exclui(Cliente umProduto) throws ProdutoNaoEncontradoException;
	
	Cliente recuperaumCliente(long numero) throws ProdutoNaoEncontradoException;
	
	Cliente recuperaumClienteePassagens(long numero) throws ProdutoNaoEncontradoException;
	
//	Cliente recuperaPrimeiroProduto() throws ProdutoNaoEncontradoException;
	
	List<Cliente> recuperaClienteePassagens();
}
