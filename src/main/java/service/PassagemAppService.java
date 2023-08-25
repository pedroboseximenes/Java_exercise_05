package service;

import java.util.List;

import excecao.DataDeLanceInvalidaException;
import excecao.LanceNaoEncontradoException;
import excecao.ProdutoNaoEncontradoException;
import excecao.ValorDeLanceInvalidoException;
import modelo.Passagem;

public interface PassagemAppService {
	long inclui(Passagem umLance) 
		throws ProdutoNaoEncontradoException, ValorDeLanceInvalidoException, DataDeLanceInvalidaException;

	void exclui(Passagem umLance) throws LanceNaoEncontradoException;
	
	Passagem recuperaUmaPassagem(long numero) throws LanceNaoEncontradoException;
	
	List<Passagem> recuperaPassagems();
}
