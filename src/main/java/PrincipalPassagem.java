import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.DataDeLanceInvalidaException;
import excecao.LanceNaoEncontradoException;
import excecao.ProdutoNaoEncontradoException;
import excecao.ValorDeLanceInvalidoException;
import modelo.Passagem;
import modelo.Voo;
import modelo.Cliente;
import service.PassagemAppService;
import service.ClienteAppService;
import util.Util;

public class PrincipalPassagem {
	public static void main(String[] args) {
		double valor;
		String dataCriacao;
		String destino;

		Cliente umCliente;
		Passagem umaPassagem;
		Integer d = 1;
		Voo umvoo = new Voo(d.longValue(),"B15", "P10",  Util.strToCalendar("09/07/2023"));

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		ClienteAppService clienteAppService = (ClienteAppService) fabrica.getBean("clienteAppService");
		PassagemAppService passagemAppService = (PassagemAppService) fabrica.getBean("passagemAppService");

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar uma Passagem de um cliente");
			System.out.println("2. Remover uma PASSAGEM");
			System.out.println("3. Recuperar todos AS PASSAGEMS");
			System.out.println("4. Sair");

			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");

			switch (opcao) {
			case 1: {
				
				long idCliente = Console.readInt('\n' + "Informe o id do cliente: ");

				try {
					umCliente = clienteAppService.recuperaumCliente(idCliente);
				} catch (ProdutoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				valor = Console.readDouble('\n' + "Informe o valor da passagem: ");
				dataCriacao = Console.readLine("Informe a data da passagem: ");
				destino = Console.readLine("Informe o destino da passagem: ");
				
				umaPassagem = new Passagem(valor, Util.strToCalendar(dataCriacao), umCliente, destino, umvoo);

				try {
					passagemAppService.inclui(umaPassagem);

					System.out.println('\n' + "Passagem adiciona com sucesso");
				} catch (ProdutoNaoEncontradoException | DataDeLanceInvalidaException
						| ValorDeLanceInvalidoException e) {
					System.out.println(e.getMessage());
				}

				break;
			}

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número da Passagem que você deseja remover: ");

				try {
					umaPassagem = passagemAppService.recuperaUmaPassagem(resposta);
				} catch (LanceNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umaPassagem.getId() + 
						              "    Valor = " + umaPassagem.getValor() + 
						              "    Data de Emissão = " + umaPassagem.getDataCriacaoMasc() +
						              "    Destino = "+ umaPassagem.getDestino());

				String resp = Console.readLine('\n' + "Confirma a remoção da Passagem?");

				if (resp.equals("s")) {
					try {
						passagemAppService.exclui(umaPassagem);
						System.out.println('\n' + "Passagem removido com sucesso!");
					} catch (LanceNaoEncontradoException e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println('\n' + "Passagem não removido.");
				}

				break;
			}

			case 3: {
				List<Passagem> arrayLances = passagemAppService.recuperaPassagems();

				if (arrayLances.size() == 0) {
					System.out.println('\n' + "Nao há Passagems cadastrados.");
					break;
				}

				System.out.println("");
				for (Passagem passagem : arrayLances) {
					System.out.println("Número = " + passagem.getId() + 
							         "  Valor = " + passagem.getValor() + 
							         "  Data de Emissão = " + passagem.getDataCriacaoMasc()
							         +
						              "  Destino = "+ passagem.getDestino()
						              + "VOO = "+ passagem.getVoo().getId());
				}

				break;
			}

			case 4: {
				continua = false;
				break;
			}

			default:
				System.out.println('\n' + "Opção inválida!");
			}
		}
	}
}
