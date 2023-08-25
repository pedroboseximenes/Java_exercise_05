package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.ClienteDAO;
import excecao.ObjetoNaoEncontradoException;
import excecao.ProdutoNaoEncontradoException;
import modelo.Cliente;
import service.ClienteAppService;

public class ClienteAppServiceImpl implements ClienteAppService {

	@Autowired
	private ClienteDAO clienteDAO;

	@Transactional
	public long inclui(Cliente umProduto) {
		clienteDAO.inclui(umProduto);
		return umProduto.getId();
	}

	@Transactional
	public void altera(Cliente umProduto) throws ProdutoNaoEncontradoException {
		try {
			clienteDAO.getPorIdComLock(umProduto.getId());
			clienteDAO.altera(umProduto);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ProdutoNaoEncontradoException("Produto não encontrado");
		}
	}

	@Transactional
	public void exclui(Cliente umProduto) throws ProdutoNaoEncontradoException {
		try {
			Cliente cliente = clienteDAO.recuperaumClienteePassagens(umProduto.getId());

			if(cliente.getPassagems().size() > 0)
			{	throw new ProdutoNaoEncontradoException("Este produto possui lances e não pode ser removido");
			}

			clienteDAO.exclui(cliente);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ProdutoNaoEncontradoException("Produto não encontrado");
		}
	}

	public Cliente recuperaumCliente(long numero) throws ProdutoNaoEncontradoException {
		try {
			return clienteDAO.getPorId(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ProdutoNaoEncontradoException("Produto não encontrado");
		}
	}

	public Cliente recuperaumClienteePassagens(long numero) throws ProdutoNaoEncontradoException {
		try {
			return clienteDAO.recuperaumClienteePassagens(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ProdutoNaoEncontradoException("Produto não encontrado");
		}
	}

//	public Cliente recuperaPrimeiroProduto() throws ProdutoNaoEncontradoException {
//		try {
//			System.out.println("Vai executar produtoDAO.recuperaPrimeiroProduto()");
//			Cliente cliente = clienteDAO.recuperaPrimeiroProduto();
//			System.out.println("Executou produtoDAO.recuperaPrimeiroProduto()");
//			return cliente;
//		} catch (ObjetoNaoEncontradoException e) {
//			throw new ProdutoNaoEncontradoException("Produto não encontrado");
//		}
//	}

	public List<Cliente> recuperaClienteePassagens() {
		System.out.println(clienteDAO.getClass().getName() + " - " + clienteDAO.getClass().hashCode());

		return clienteDAO.recuperaClienteePassagems();
	}
}