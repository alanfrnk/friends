# Friends
Friends é um projeto cliente-servidor, utilizando a tecnologia RESTful para disponibilizar os dados para o cliente, desenvolvido para participar do processo seletivo da vaga de Desenvolvedor Web da empresa Luiza Labs. 

O sistema consiste em importar a lista de amigos de um usuário do Facebook, usando a [Graph Api](https://developers.facebook.com/docs/graph-api) fazendo login usando o [Login no Facebook](https://developers.facebook.com/docs/facebook-login), os dados importados e todos os outros serão salvos em banco [MySQL](https://www.mysql.com/).

Permitirá ainda que o usuário adicione novos amigos usando a API própria e envie mensagens para esses amigos através da ferramenta. Mensagens que além de serem salvas localmente, também serão enviadas para o Facebook.

O projeto está dividido em 2 partes: 

[Back End](#back-end) que é responsável por persistir os dados no banco e pela lógica de negócio, disponibilizando através dos métodos GET, POST, PUT e DELETE as operações necessária. Será responsável pelos métodos GET e POST no Facebook.

[Front End](#front-end) que é responsável por consumir essa API e todos os seus métodos e mostrar para o usuário. Será responsável também por fazer login no Facebook.

- [Ferramentas Usadas](#ferramentas-usadas)
- [Como executar](#como-executar)
- [Front End](#front-end)
- [Back End (API v1)](#back-end)

## Ferramentas Usadas

1 - IDE de Desenvolvimento: [NetBeans 8.2 com Java EE 7](https://netbeans.org)
2 - Persistência dos Dados: [Hibernate 4.3.1](http://hibernate.org/)
3 - Banco de Dados Relacional: [MySQL 5.7.18](https://www.mysql.com)
4 - Gerenciador de Dependências: [Maven 3.1](https://maven.apache.org/)
5 - Construção do Front End: [JSF 2.2](https://javaee.github.io/javaserverfaces-spec/)
6 - UI Design do Front End: [PrimeFaces 6.1](https://www.primefaces.org/)
7 - Implementação do RESTful Front e Back End: [Jersey 2.22.1](https://github.com/jersey)
8 - Login no Facebook e Acesso à Graph API: [Socialauth 4.12](https://github.com/3pillarlabs/socialauth)
9 - Servidor de Aplicação: [Apache Tomcat 8.5](http://tomcat.apache.org/)
10 - Testes da API RESTful: [SoapUI](https://www.soapui.org/) 

## Como Executar

- Instalar as ferramentas 1, 2 e 9 da lista acima.
- Criar um banco de dados chamado FRIENDS_DB e rodar os dois script abaixo para criar as duas tabelas do projeto:

```SQL
     CREATE TABLE `Friend` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `name` varchar(100) NOT NULL,
     `profileLink` varchar(500) NOT NULL,
     `profileImage` varchar(500) DEFAULT NULL,
     `birthDate` date NOT NULL,
     `email` varchar(80) DEFAULT NULL,
     PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1

  CREATE TABLE `Post` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `message` varchar(45) NOT NULL,
    `sendDate` datetime DEFAULT NULL,
    `friendReceiver` int(11) NOT NULL,
    `friendSender` int(11) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `friendReceiver_Friend_FK_idx` (`friendReceiver`),
    KEY `friendSender_Friend_FK` (`friendSender`),
    CONSTRAINT `friendReceiver_Friend_FK` FOREIGN KEY (`friendReceiver`) REFERENCES `Friend` (`id`) ON DELETE NO ACTION O N     UPDATE NO ACTION,
    CONSTRAINT `friendSender_Friend_FK` FOREIGN KEY (`friendSender`) REFERENCES `Friend` (`id`) ON DELETE NO ACTION ON           UPDATE NO ACTION
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1
```
- Abrir os dois projetos FriendsServer e FriendsClient e rodá-los respectivamente no NetBeans.

    URL do Client: http://localhost:8080/FriendsClient
    URL do Server: http://localhost:8080/FriendsServer

- Devido à uma "limitação" do método GET me/friends da API do Facebook só é possível listar os amigos que também estão autorizados no aplicativo, criei um usuário fake para teste e deixo as credenciais abaixo para que possam testar:
    
    email: mariacarmemdesouza41@gmail.com
    senha: 29102002

- Ao fazer login o sistema acessa o facebook e importa a lista de amigos com algumas informações, é possível editar os contatos importados para a base, ou também criar novos, tanto a importação dos amigos do Facebook quanto a criação de novo acessa a API/v1 criada.

## Front End

É composto por duas telas e três dialogs modal, todas estão no mesmo arquivo, index.html.

A primeira tela lista os amigos da tabela Friend, que foram criados manualmanet eou importados do Facebook. O botão Novo Amigo abre um Dialog para a criação de um novo registro. Na lista de Amigos além das informações pessoais temos um link para o Perfil do Facebook cadastrado, um botão Mensagens que lista as mensagens enviadas pra esse amigo(*), um boto Editar que abre o Dialog para edição dos Amigos e outro botão Excluir que exclui um registro.

Clicando no botão Todas as Mensagens é aberta um dialog que exibe todas as mensagens enviadas, e nesse dialog temos um boto Excluir para cada mensagem.

(*) Essa tela abre um dialog com as mensagens enviadas para um determinado amigo, e também seria responsável por enviar as mensagens pra esse amigo, usando a API do Facebook para postar na linha do tempo, porém não tive tempo hábil para implementar essa funcionalidade.

## Back End (API v1)

O Back End é o responsável por receber e listar os dados, fazendo a comunicação com o DB. É no Back End que está toda a regra de negócio bem como os métodos responsáveis por validar as informações e criar, ler, editar e deletar os dados. É ele quem disponibiliza a [API Friends v1](https://github.com/alanfrnk/friends/wiki). Mais informações sobre a API e seus métodos é só acessar a [Wiki do Projeto](https://github.com/alanfrnk/friends/wiki).

