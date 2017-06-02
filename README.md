# Friends
Friends é um projeto cliente-servidor, utilizando a tecnologia RESTful para disponibilizar os dados para o cliente, desenvolvido para participar do processo seletivo da vaga de Desenvolvedor Web da empresa Luiza Labs. 

O sistema consiste em importar a lista de amigos de um usuário do Facebook, usando a [Graph Api](https://developers.facebook.com/docs/graph-api) fazendo login usando o [Login no Facebook](https://developers.facebook.com/docs/facebook-login), os dados importados e todos os outros serão salvos em banco [MySQL](https://www.mysql.com/).

Permitirá ainda que o usuário adicione novos amigos usando a API própria e envie mensagens para esses amigos através da ferramenta. Mensagens que além de serem salvas localmente, também serão enviadas para o Facebook.

O projeto está dividido em 2 partes: 

[Back end](#back-end) que é responsável por persistir os dados no banco e pela lógica de negócio, disponibilizando através dos métodos GET, POST, PUT e DELETE as operações necessária. Será responsável pelos métodos GET e POST no Facebook.

[Front end](#front-end) que é responsável por consumir essa API e todos os seus métodos e mostrar para o usuário. Será responsável também por fazer login no Facebook.


