# FootballBattle

### RPG de futebol desenvolvido em Java

## 1. Apresentação do projeto

O **FootballBattle** é um jogo de RPG desenvolvido em Java, que mistura jogadores de futebol com seus apelidos recebidos durante a carreira. Nele os personagens jogáveis são jogadores de linha históricos enquanto os chefes enfrentados são goleiros também históricos.

O jogo utiliza um sistema de batalhas por turnos, no qual cada personagem possui níveis de ataque, defesa e vida, além de um poder especial que pode modificar temporariamente as condições do combate.

O projeto foi desenvolvido com o objetivo de aplicar, na prática, conceitos de **Programação Orientada a Objetos (POO)**, desenvolvimento de interfaces gráficas, organização de software em camadas (MVC) e persistência de dados utilizando banco de dados relacional.

Além das mecânicas de combate, o FootballBattle possui cadastro e autenticação de usuários, gerenciamento de múltiplas partidas salvas e progressão entre níveis.

## 2. Tecnologias utilizadas

 Java Swing - Construção das interfaces gráficas 
 Apache NetBeans - Ambiente de desenvolvimento (IDE)
 PostgreSQL - Armazenamento dos usuários e saves 
 JDBC - Comunicação entre Java e PostgreSQL 
 Git e GitHub - Versionamento e disponibilização do projeto 
 draw.io - Modelagem do diagrama UML de classes 

## 3. Arquitetura do sistema

O FootballBattle foi organizado seguindo o padrão arquitetural **MVC (Model-View-Controller)**.

### 3.1. Model

A camada Model contém as classes responsáveis pelos dados e pelas regras do jogo.

Entre as principais classes, destacam-se:

- `Personagem`: classe abstrata com maior nivel na hierarquia que reúne os atributos e comportamentos compartilhados pelos personagens.
- `Protagonista`: classe abstrata que herda Personagem e representa os personagens controlados pelo jogador e implementa o gerenciamento de energia.
- `Inimigo`: classe abstrata que herda Personagem e representa os chefes enfrentados durante a campanha.
- `Batalha`: controla a alternância de turnos, as ações, os efeitos temporários e o encerramento dos confrontos.
- `Jogo`: gerencia os níveis, os inimigos e a progressão da campanha.
- `Usuario`: representa as informações dos usuários cadastrados.
- `Save`: armazena os dados relacionados ao progresso de uma partida.

Além das classes herdeiras de Inimigo e Protagonista que representam especificamente cada personagem do jogo.

### 3.2. View

A camada View foi desenvolvida utilizando Java Swing e é responsável pela interação visual com o usuário.

O projeto possui as seguintes interfaces:

 `JFrameTelaInicial` - Login e acesso inicial 
 `JFrameTelaCadastro` - Cadastro de usuários 
 `JFrameMenuPrincipal` - Menu principal do jogo 
 `JFrameNovoJogo` - Seleção do protagonista 
 `JFrameSaves` - Gerenciamento das partidas salvas 
 `JFrameBatalha` - Interface de combate 
 `JFrameResultado` - Exibição de vitória e derrota 

### 3.3. Controller

A camada Controller realiza a comunicação entre as interfaces e as classes do model.

Os controllers são:

- `UsuarioController`: gerencia cadastro, autenticação e acesso ao sistema.
- `JogoController`: controla a criação e o carregamento de partidas, a progressão e a navegação entre telas.
- `SaveController`: gerencia a visualização, seleção e exclusão de saves.
- `BatalhaController`: interpreta as ações do jogador, executa as operações de combate e atualiza a interface.

### 3.4. Persistência de dados

A persistência é organizada por meio de classes DAO (Data Access Object):

- `Conexao`: estabelece a conexão com o PostgreSQL.
- `UsuarioDAO`: realiza operações de cadastro e consulta de usuários.
- `SaveDAO`: realiza operações de inserção, consulta, atualização e exclusão de partidas.


## 4. Personagens e sistema de combate

### 4.1. Protagonistas

O jogador pode selecionar um dos quatro protagonistas:

| Personagem | Identificação | Poder especial |
|---|---|---|
| Lionel Messi | O Extraterrestre | Abdução |
| Pelé | O Rei | A Força do Trono |
| Ronaldinho Gaúcho | O Bruxo | Magia Pura |
| Sergio Ramos | O Xerife | A Muralha |

Cada protagonista possui atributos e habilidades próprias.

**Abdução:** permite que Messi realize um ataque desconsiderando a defesa do adversário.

**A Força do Trono:** duplica temporariamente o ataque de Pelé.

**Magia Pura:** permite que Ronaldinho altere temporariamente os atributos do adversário, com uma penalidade no próprio ataque.

**A Muralha:** duplica a defesa de Sergio Ramos e aumenta seu ataque em 50% durante a utilização da habilidade.

### 4.2. Chefes e progressão

A campanha possui cinco níveis, cada um com um chefe diferente:

| Nível | Goleiro | Identificação |
|---|---|---|
| 1 | Oliver Kahn | Titan |
| 2 | Gianluigi Buffon | Superman |
| 3 | Lev Yashin | Aranha Negra |
| 4 | Manuel Neuer | Neuer |
| 5 | Rogério Ceni | Mito |

Os inimigos possuem características e poderes especiais próprios.

Ao vencer uma batalha, o jogador avança para o próximo nível. Após derrotar o quinto chefe, a campanha é concluída e o jogo apresenta a tela de vitória final.

### 4.3. Mecânicas de combate

As batalhas são realizadas por turnos. O protagonista possui três ações principais:

- **Atacar:** causa dano ao adversário e recupera 25 pontos de energia.
- **Defender:** reduz pela metade o dano do próximo ataque recebido e recupera 15 pontos de energia.
- **Poder Especial:** executa a habilidade específica do personagem, consumindo 100 pontos de energia.

O dano básico é calculado pela fórmula:

`Dano = (2 × Ataque) + 10 − Defesa do adversário`

Nos ataques dos inimigos, é acrescentado um modificador aleatório entre −3 e +3, aumentando a imprevisibilidade dos confrontos.

Os efeitos especiais podem alterar temporariamente os atributos de ataque e defesa. A classe `Batalha` controla esses efeitos e restaura os valores originais quando necessário.

### 4.4. Comportamento dos inimigos

Os inimigos realizam suas ações automaticamente de forma aleatória com probabilidades iguais, ou seja, pode aleatoriamente atacar, defender ou usar o poder especial.

Para balancear o jogo, após a utilização do super, ele só poderá ser ativado novamente depois de 3 rodadas e enquanto está desabilitado, a escolha automática continua sendo aleatória entre atacar e defender, mas com probabilidades diferentes: dois terços pra atacar e um terço pra defender, deixando o jogo mais agressivo.


### 4.5. Interface e feedback das batalhas

A interface de combate apresenta barras de vida e energia, imagens dos personagens e botões correspondentes às ações disponíveis.

Também são exibidas mensagens informando a ação realizada e os efeitos especiais ativos.

Para melhorar a visualização dos acontecimentos, foi implementado um intervalo de cinco segundos entre a ação do protagonista e a resposta do inimigo, utilizando `javax.swing.Timer`.

Durante esse intervalo, os botões são temporariamente desabilitados, impedindo que o jogador realize múltiplas ações no mesmo turno.

## 5. Cadastro e sistema de saves

O FootballBattle utiliza PostgreSQL para armazenar informações de usuários e partidas.

O sistema permite cadastrar usuários, realizar login e gerenciar diferentes saves vinculados a uma mesma conta.

Cada save armazena informações como:

- Identificador único.
- Nome do save.
- Usuário responsável.
- Protagonista selecionado.
- Nível atual.

Os saves são apresentados em uma tabela na interface do jogo, permitindo que o usuário selecione uma partida existente para continuar sua campanha ou exclua um registro.

Ao carregar um save, o sistema recupera os dados armazenados através do id do save, recria o protagonista correspondente e restaura o progresso até o nível salvo.

Após vencer uma batalha, o progresso é atualizado para permitir a continuidade da campanha.

Em caso de derrota, o jogador pode tentar novamente a fase. Após concluir a última batalha, a tela de vitória final apresenta a opção de sair, realizando também a exclusão do save concluído.

## 6. Como executar o projeto

### 6.1. Pré-requisitos

Para executar o FootballBattle, é necessário possuir:

- Java JDK 25.
- Apache NetBeans ou outra IDE compatível com Maven.
- PostgreSQL instalado e em execução.
- Banco de dados configurado com as tabelas utilizadas pelo projeto.

### 6.2. Obter o código-fonte

Clone o repositório pelo GitHub ou baixe o projeto em formato ZIP.

Em seguida, abra a pasta do FootballBattle no Apache NetBeans como um projeto Maven.

### 6.3. Configurar o banco de dados

No PostgreSQL, crie um banco de dados chamado `FootballBattle`, ou utilize outro nome de sua preferência.

É necessário que as tabelas `usuario` e `save` estejam criadas com a estrutura esperada pelas classes DAO.

### 6.4. Configurar o arquivo `.env`

O projeto utiliza um arquivo `.env` para armazenar as informações necessárias à conexão com o PostgreSQL, evitando que as credenciais fiquem diretamente no código-fonte.

Na pasta principal do projeto, no mesmo nível do `pom.xml`, localize o arquivo `.env.example`.

Crie uma cópia desse arquivo e renomeie-a para `.env`.

O conteúdo deverá seguir o modelo:

```dotenv
DB_URL=jdbc:postgresql://localhost:5432/FootballBattle
DB_USER=postgres
DB_PASSWORD=sua_senha_aqui
```

Substitua os valores conforme a configuração do PostgreSQL instalado em sua máquina.

**Descrição das variáveis:**

- `DB_URL`: endereço JDBC utilizado para conectar ao banco de dados.
- `DB_USER`: nome do usuário do PostgreSQL.
- `DB_PASSWORD`: senha correspondente ao usuário informado.

O arquivo `.env` contém configurações locais e não deve ser enviado ao GitHub. Por esse motivo, está incluído no `.gitignore`.

O arquivo `.env.example`, por sua vez, é disponibilizado no repositório como modelo, sem conter credenciais reais.

### 6.5. Executar a aplicação

Após configurar o banco de dados e o arquivo `.env`:

1. Abra o projeto no Apache NetBeans.
2. Aguarde o Maven carregar as dependências.
3. Execute a classe principal `FootballBattle.java`.
4. Na tela inicial, realize o cadastro ou login.
5. Crie uma partida, selecione um protagonista e inicie a campanha.

**Observação:** cada pessoa que executar o projeto deverá configurar suas próprias credenciais do PostgreSQL no arquivo `.env`.

## 7. Considerações finais

O desenvolvimento do FootballBattle permitiu aplicar conhecimentos de Programação Orientada a Objetos em um projeto com diferentes funcionalidades integradas.

A implementação das classes de personagens possibilitou trabalhar conceitos de abstração, encapsulamento, herança e polimorfismo. A organização em MVC contribuiu para a separação das responsabilidades e para a manutenção do código.

A utilização de Java Swing permitiu desenvolver uma interface gráfica interativa, enquanto a integração com PostgreSQL e JDBC possibilitou implementar o cadastro de usuários e a persistência do progresso.

Durante o desenvolvimento, também foram realizados ajustes relacionados ao balanceamento dos personagens, ao gerenciamento dos efeitos especiais, à inteligência dos inimigos e à apresentação das informações durante as batalhas.

Como resultado, o FootballBattle reúne conceitos de orientação a objetos, interfaces gráficas, bancos de dados e organização de software em uma aplicação funcional de RPG por turnos com temática de futebol.