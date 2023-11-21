
## Configurando maquina desenvolvedor

*Necessário instalar o Dev-Env: 
\\bndes.net\bndes\Grupos\AmbienteDesenvolvedor\bnd-levain-pkg\instaladores\instalaDevEnv.cmd*

*Instalando versão do eclipse mais recente
**Abra o diretório (J:).
**Abra a pasta GAUT1.

>Atenção: Diretório do workspace do eclipse pode estar configurado em um local inexistente na sua máquina.

2 - Configurando o plugin Liberty:
    2.1 - Abra o eclipse;
    2.2 - Clique em “Servers” na parte inferior do eclipse;
    2.3 - Clique no botão  create a new server;
    Imagem!
    2.4 - Escolha IBM e em seguida Liberty;
    2.5- Clicar em Adicionar um server runtime environment, caso não possua um:
    Imagem!
    2.6– Nessa tela pode definir qual nome e versão do Liberty e  do JEE  que irá utilizar, os quais se encontram com suas versões no dev-env;
    Imagem!
    2.7 - Clique opções avançadas para definir a aplicação;
    2.8- Clique em new para configurar o wlp do seu projeto, um padrão do BNDES; 
    2.9 – Clique em Use an external user directory;
    2.10 – Nessa tela, procure pela pasta wlp-usr e clique em finish;
    Imagem!
    2.11 – Veja se foi adicionado mais um diretório em  User directories, em seguida clique em OK e clique em Next na tela inicial;
    2.12 - Se ficar essa tela, clica em finish e aguarde a criação do liberty no eclipse;

3 - Importando o projeto para o eclipse:
	3.1 - Na parte superior clique em import;
	3.2 - Selecione Maven e Existing mave projects;
	3.3 - Selecione a pasta do back do projeto FGI com git da sua máquina;
	3.4 - Para remover os erros de validadores do eclipse, clique com botão direito na pasta raiz do projeto no Enterprise Explorer e clique no Properties;
	 Imagem
    3.5 - Procure a opção Validation e marque as opções Enable Project spcecific settings e Suspend all validators. Após isso apenas clicar em Apply and Close; 
    Imagem
    3.6 - Clique com botão direito novamente no projeto e clique no Validate para limpar esses erros existentes;

4 - Configurações finais:
	4.1 – Vá na pasta wlp-user, entre em servers, fg2, apps;
	4.2 – Remova o fg2.war
    Imagem
	4.3 – Volte para o eclipse, em servers clique com botão direito no liberty roxo e clique em Add and Remove;
    Imagem
	4.4 - Troque o projeto configurado para o que está no seu workspace e clique em finish;
	4.5 -  Clique em Publish to the server;
    Imagem
	4.6 – Clique 2 vezes com botão esquerdo no Liberty roxo novamente e adicione essa configuração. Para salvar, use CONTROL + S;
    Imagem

	4.7 - Clique no start server e espere aparecer a linha do init.

>Atenção: Caso não esteja funcionando, tente excluir o arquivo War.xml na pasta do wlp-usr. Após isso, rode o arquivo atualiza.cmd na pasta do back do projeto fgi. Depois disso, volte para pasta wlp-user, exclua o fg2.war e no eclipse clique no publish server novamente. Tente inicializar o server.

>Atenção 2: : Para eliminar os processos no eclipse do tipo "JPA Event change handler... ": 
    1.	abra ./eclipse/configuration/org.eclipse.equinox.simpleconfigurator/bundles.info
    2.	deletar ou comentar as linhas começando com org.eclipse.jpt, conforme a imagem abaixo:
    Imagem
 


