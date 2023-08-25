package service.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.PassagemDAO;
import dao.ClienteDAO;
import excecao.DataDeLanceInvalidaException;
import excecao.LanceNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import excecao.ProdutoNaoEncontradoException;
import excecao.ValorDeLanceInvalidoException;
import modelo.Passagem;
import modelo.Cliente;
import service.PassagemAppService;
import util.Util;

public class PassagemAppServiceImpl implements PassagemAppService {

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private PassagemDAO passagemDAO;

	@Transactional
	public long inclui(Passagem umaPassagem)
			throws ProdutoNaoEncontradoException, ValorDeLanceInvalidoException, DataDeLanceInvalidaException {
		// A implementação do método getPorIdComLock(umProduto.getId())
		// impede que dois lances sejam cadastrados em paralelo, i. é,
		// os lances devem ser cadastrados obedecendo a uma fila. Isto
		// impede que o valor do segundo lance seja inferior ao valor do
		// primeiro.

		Cliente umCliente = umaPassagem.getCliente();

		try {
			umCliente = clienteDAO.getPorIdComLock(umCliente.getId());
		} catch (ObjetoNaoEncontradoException e) {
			throw new ProdutoNaoEncontradoException("Cliente não encontrado");
		}

//		Passagem ultimaPassagem;
//		try {
//			ultimaPassagem = passagemDAO.recuperaUltimaPassagem(umCliente);
//		} catch (ObjetoNaoEncontradoException e) {
//			ultimaPassagem = null;
//		}

//		double valorUltimoLance;
//		Calendar dataUltimoLance;
//
//		if (ultimoLance == null) {
//			valorUltimoLance = umCliente.getLanceMinimo() - 1;
//			dataUltimoLance = umCliente.getDataCadastro();
//		} else {
//			valorUltimoLance = ultimoLance.getValor();
//			dataUltimoLance = ultimoLance.getDataCriacao();
//		}
//
//		if (umaPassagem.getValor() <= valorUltimoLance) {
//			throw new ValorDeLanceInvalidoException("O valor do lance tem que ser superior a " + valorUltimoLance);
//		}

		
		GregorianCalendar hoje = new GregorianCalendar();
		if (umaPassagem.getDataCriacao().before(hoje)) {
			throw new DataDeLanceInvalidaException(
					"A data da passagem não pode ser anterior a " + Util.calendarToStr(hoje));
		}

		
//
//		if (umaPassagem.getDataCriacao().after(hoje)) {
//			throw new DataDeLanceInvalidaException(
//					"A data de emissão do lance não pode ser posterior à data de hoje: " + Util.calendarToStr(hoje));
//		}

		Passagem passagem = passagemDAO.inclui(umaPassagem);

		return passagem.getId();
	}

	@Transactional
	public void exclui(Passagem umLance) throws LanceNaoEncontradoException {
		try {
			umLance = passagemDAO.getPorId(umLance.getId());
			passagemDAO.exclui(umLance);
		} catch (ObjetoNaoEncontradoException e) {
			throw new LanceNaoEncontradoException("Passagem não encontrada.");
		}
	}

	public Passagem recuperaUmaPassagem(long numero) throws LanceNaoEncontradoException {
		try {
			return passagemDAO.getPorId(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new LanceNaoEncontradoException("Passagem não encontrada.");
		}
	}

	public List<Passagem> recuperaPassagems() {
		return passagemDAO.recuperaListaPassagems();
	}
}