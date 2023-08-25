import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ProdutoNaoEncontradoException;
import modelo.Passagem;
import modelo.Cliente;
import service.ClienteAppService;
import util.Util;

public class PrincipalCliente {
	public static void main(String[] args) {
		String nome;
		String email;
		double lanceMinimo;
		String dataCadastro;
		Cliente umCliente;

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		ClienteAppService clienteAppService = (ClienteAppService) fabrica.getBean("clienteAppService");

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que voc� deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um cliente");
			System.out.println("2. Alterar um cliente");
			System.out.println("3. Remover um cliente");
			System.out.println("4. Listar um Cliente e suas passagens");
			System.out.println("5. Listar todos os clientes e suas passagens");
			System.out.println("6. Sair");

			int opcao = Console.readInt('\n' + "Digite um n�mero entre 1 e 6:");

			switch (opcao) {
			case 1: {
				nome = Console.readLine('\n' + "Informe o nome do cliente: ");
				email = Console.readLine("Informe o email do cliente: ");
//				lanceMinimo = Console.readDouble("Informe o valor do lance m�nimo: ");
				dataCadastro = Console.readLine("Informe a data de cadastramento do produto: ");

				Cliente cliente = new Cliente(nome, email, Util.strToCalendar(dataCadastro));

				long numero = clienteAppService.inclui(cliente);

				System.out.println('\n' + "Cliente numero" + numero + " inclu�do com sucesso!");

				break;
			}

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o n�mero do cliente que voc� deseja alterar: ");

				try {
					umCliente = clienteAppService.recuperaumCliente(resposta);
				} catch (ProdutoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "N�mero = " + umCliente.getId() + 
						              "    Nome = " + umCliente.getNome() + 
						              "    Email = " + umCliente.getEmail());

				System.out.println('\n' + "O que voc� deseja alterar?");
				System.out.println('\n' + "1. Nome");
				System.out.println("2. Email");

				int opcaoAlteracao = Console.readInt('\n' + "Digite um n�mero de 1 a 2:");

				switch (opcaoAlteracao) {
				case 1:
					String novoNome = Console.readLine("Digite o novo nome: ");
					umCliente.setNome(novoNome);

					try {
						clienteAppService.altera(umCliente);

						System.out.println('\n' + "Altera��o de nome efetuada com sucesso!");
					} catch (ProdutoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				case 2:
					String novoEmail = Console.readLine("Digite o novo email: ");
					umCliente.setEmail(novoEmail);

					try {
						clienteAppService.altera(umCliente);

						System.out.println('\n' + "Altera��o de email efetuada " + "com sucesso!");
					} catch (ProdutoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				default:
					System.out.println('\n' + "Op��o inv�lida!");
				}

				break;
			}

			case 3: {
				int resposta = Console.readInt('\n' + "Digite o n�mero do cliente que voc� deseja remover: ");

				try {
					umCliente = clienteAppService.recuperaumCliente(resposta);
				} catch (ProdutoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "N�mero = " + umCliente.getId() + 
						              "    Nome = " + umCliente.getNome() + 
						              "    Email = " + umCliente.getEmail());

				String resp = Console.readLine('\n' + "Confirma a remo��o do cliente?");

				if (resp.equals("s")) {
					try {
						clienteAppService.exclui(umCliente);
						System.out.println('\n' + "Cliente removido com sucesso!");
					} catch (ProdutoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Cliente n�o removido.");
				}

				break;
			}

			case 4: {
				long numero = Console.readInt('\n' + "Informe o n�mero do cliente: ");

				try {
					umCliente = clienteAppService.recuperaumClienteePassagens(numero);
				} catch (ProdutoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Id = " + umCliente.getId() + 
						                "  Nome = " + umCliente.getNome() + 
						                "  Email = " + umCliente.getEmail() + 
						           
						                "  Data Cadastro = " + umCliente.getDataCadastroMasc());

				List<Passagem> passagems = umCliente.getPassagems();

				for (Passagem passagem : passagems) {
				
					System.out.println('\n' + "     Passagem Id / n�mero = " + passagem.getId() + 
							                     "  Valor = " + passagem.getValor() + 
							                     "  Data = " + passagem.getDataCriacaoMasc() );
				}

				break;
			}

			case 5: {
				List<Cliente> clientes = clienteAppService.recuperaClienteePassagens();

				if (clientes.size() != 0) {
					System.out.println("");

					for (Cliente cliente : clientes) {
						System.out.println('\n' + "Produto numero = " + cliente.getId() + 
								                "  Nome = " + cliente.getNome() + 
								                "  Email = " + cliente.getEmail() + 
								                
								                "  Data Cadastro = " + cliente.getDataCadastroMasc());

						List<Passagem> passagems = cliente.getPassagems();

						for (Passagem passagem : passagems) {
							System.out.println('\n' + "      Passagem Id /numero = " + passagem.getId() + 
									                      "  Valor = " + passagem.getValor() + 
									                      "  Data = " + passagem.getDataCriacaoMasc());
						}
					}
				} else {
					System.out.println('\n' + "N�o h� clientes cadastrados com esta descri��o.");
				}

				break;
			}

	case 6: {continua = false;
				break;}
	
				

			default:
				System.out.println('\n' + "Op��o inv�lida!");
			}
		}
	}
}
