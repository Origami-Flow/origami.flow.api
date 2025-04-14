SET character_set_client = utf8;
SET character_set_connection = utf8;
SET character_set_results = utf8;
SET collation_connection = utf8_general_ci;

CREATE DATABASE IF NOT EXISTS salgadotrancas;

-- salgadotrancas.auxiliar definição

USE salgadotrancas;

CREATE TABLE IF NOT EXISTS `auxiliar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255),
  `nome` varchar(255),
  `valor_mao_de_obra` double,
  PRIMARY KEY (`id`)
);


-- salgadotrancas.endereco definição

CREATE TABLE IF NOT EXISTS `endereco` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255),
  `cep` varchar(255),
  `cidade` varchar(255),
  `complemento` varchar(255),
  `estado` varchar(255),
  `logradouro` varchar(255),
  `numero` int,
  `regiao` varchar(255),
  `uf` varchar(255),
  PRIMARY KEY (`id`)
);


-- salgadotrancas.imagem definição

CREATE TABLE IF NOT EXISTS `imagem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `asset_id` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL,
  `format` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `public_id` varchar(255),
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);


-- salgadotrancas.trancista definição

CREATE TABLE IF NOT EXISTS `trancista` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255),
  `nome` varchar(255),
  `role` enum('ADMIN','USER'),
  `senha` varchar(255),
  `telefone` varchar(255),
  `tipo` varchar(255),
  PRIMARY KEY (`id`)
);


-- salgadotrancas.cliente definição

CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `email` varchar(255),
  `nome` varchar(255),
  `role` enum('ADMIN','USER'),
  `senha` varchar(255),
  `comprimento_cabelo` enum('COMPRIDO','CURTO','MEDIO'),
  `cor_cabelo` varchar(255),
  `data_criacao` date,
  `data_nascimento` date,
  `genero` enum('FEMININO','MASCULINO','OUTRO'),
  `ocupacao` varchar(255),
  `primeira_tranca` bit(1),
  `progressiva` bit(1) NOT NULL,
  `telefone` varchar(255),
  `tipo_cabelo` enum('A1','A2','A3','A4','B1','B2','B3','B4','C1','C2','C3','C4'),
  `endereco_id` int UNIQUE,
  `imagem_id` int,
  FOREIGN KEY (endereco_id) REFERENCES endereco(id),
  FOREIGN KEY (imagem_id) REFERENCES imagem(id)
);


-- salgadotrancas.produto definição
CREATE TABLE IF NOT EXISTS produto (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  marca VARCHAR(255),
  nome VARCHAR(255),
  quantidade_embalagem INT,
  tipo ENUM('LOJA', 'SALAO'),
  unidade_medida VARCHAR(255),
  valor_compra DOUBLE,
  valor_venda DOUBLE,
  imagem_id INT,
  FOREIGN KEY (imagem_id) REFERENCES imagem(id)
);


-- salgadotrancas.salao definição

CREATE TABLE IF NOT EXISTS salao (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255),
  endereco_id INT UNIQUE,
  FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);


-- salgadotrancas.servico definição

CREATE TABLE IF NOT EXISTS servico (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  descricao VARCHAR(255),
  nome VARCHAR(255),
  tempo_duracao TIME(6),
  valor_maximo_servico DOUBLE,
  valor_minimo_servico DOUBLE,
  valor_sinal DOUBLE,
  imagem_id INT,
  FOREIGN KEY (imagem_id) REFERENCES imagem(id)
);


-- salgadotrancas.caixa definição

CREATE TABLE IF NOT EXISTS caixa (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  data_abertura DATE,
  data_fechamento DATE,
  despesa_total DOUBLE,
  receita_total DOUBLE,
  salao_id INT,
  FOREIGN KEY (salao_id) REFERENCES salao(id)
);


-- salgadotrancas.despesa definição

CREATE TABLE IF NOT EXISTS despesa (
  id INT PRIMARY KEY AUTO_INCREMENT,
  data DATE,
  descricao VARCHAR(255),
  nome VARCHAR(255),
  valor DOUBLE,
  caixa_id INT,
  produto_id INT,
  FOREIGN KEY (caixa_id) REFERENCES caixa(id),
  FOREIGN KEY (produto_id) REFERENCES produto(id)
);



-- salgadotrancas.estoque definição

CREATE TABLE IF NOT EXISTS estoque (
  id INT PRIMARY KEY AUTO_INCREMENT,
  quantidade INT,
  produto_id INT,
  salao_id INT,
  FOREIGN KEY (produto_id) REFERENCES produto(id),
  FOREIGN KEY (salao_id) REFERENCES salao(id)
);



-- salgadotrancas.evento definição

CREATE TABLE IF NOT EXISTS evento (
  id INT PRIMARY KEY AUTO_INCREMENT,
  datahora_inicio DATETIME(6),
  datahora_termino DATETIME(6),
  status_evento ENUM('FINALIZADO', 'PROGRAMADO'),
  tipo_evento ENUM('ATENDIMENTO', 'PESSOAL'),
  valor_cobrado DOUBLE,
  auxiliar_id INT,
  cliente_id INT,
  servico_id INT,
  trancista_id INT,
  FOREIGN KEY (auxiliar_id) REFERENCES auxiliar(id),
  FOREIGN KEY (cliente_id) REFERENCES cliente(id),
  FOREIGN KEY (servico_id) REFERENCES servico(id),
  FOREIGN KEY (trancista_id) REFERENCES trancista(id)
);


-- 1. Cria as tabelas sem as FKs que causam a dependência circular

CREATE TABLE IF NOT EXISTS atendimento_realizado (
  id INT PRIMARY KEY AUTO_INCREMENT,
  receita DOUBLE,
  avaliacao_id INT UNIQUE,
  caixa_id INT,
  cliente_id INT,
  evento_id INT,
  FOREIGN KEY (caixa_id) REFERENCES caixa(id),
  FOREIGN KEY (cliente_id) REFERENCES cliente(id),
  FOREIGN KEY (evento_id) REFERENCES evento(id)
);

CREATE TABLE IF NOT EXISTS avaliacao (
  id INT PRIMARY KEY AUTO_INCREMENT,
  comentario VARCHAR(255),
  nota DOUBLE NOT NULL,
  atendimento_realizado_id INT,
  cliente_id INT,
  salao_id INT,
  FOREIGN KEY (cliente_id) REFERENCES cliente(id),
  FOREIGN KEY (salao_id) REFERENCES salao(id)
);


ALTER TABLE avaliacao
  ADD CONSTRAINT fk_avaliacao_atendimento
  FOREIGN KEY (atendimento_realizado_id) REFERENCES atendimento_realizado(id);

ALTER TABLE atendimento_realizado
  ADD CONSTRAINT fk_atendimento_avaliacao
  FOREIGN KEY (avaliacao_id) REFERENCES avaliacao(id);

-- salgadotrancas.produto_atendimento_utilizado definição

CREATE TABLE IF NOT EXISTS produto_atendimento_utilizado (
  id INT PRIMARY KEY AUTO_INCREMENT,
  finalidade ENUM('ACABOU', 'UTILIZADO', 'VENDIDO'),
  quantidade INT,
  atendimento_realizado_id INT,
  produto_id INT,
  FOREIGN KEY (atendimento_realizado_id) REFERENCES atendimento_realizado(id),
  FOREIGN KEY (produto_id) REFERENCES produto(id)
);

INSERT INTO cliente (
    nome, email, senha, role,
    data_nascimento, telefone, genero, tipo_cabelo,
    comprimento_cabelo, cor_cabelo, progressiva,
    primeira_tranca, ocupacao, endereco_id
) VALUES (
             'João Silva',
             'joao.silva@example.com',
             'senha123',
             'USER',
             '1990-05-15',
             '1234567890',
             'MASCULINO',
             'A1',
             'COMPRIDO',
             'CASTANHO',
             1,
             0,
             'Engenheiro',
             null
         );

INSERT INTO imagem
(id, asset_id, created_at, format, nome, `path`, url, public_id)
VALUES
(1, 'b28338168d98c82b0e53b693b1608765', '2025-04-05 19:26:42.775721', 'png', 'imagem_default_tranca', 'servicos', 'https://res.cloudinary.com/dt5smeslb/image/upload/v1743892304/servicos/fia65bmkbcxd7pkxqrhy.png', 'servicos/chdrnv0ggcw1oxwq5uq2'),
(2, '1c1f3df35e16834825dcf9b96e5d21d4', '2025-04-05 20:07:27.531854', 'jpg', 'gypsy', 'servicos', 'http://res.cloudinary.com/dt5smeslb/image/upload/v1743894446/servicos/pxwqfwh3wmjwxyrjfege.jpg', 'servicos/pxwqfwh3wmjwxyrjfege'),
(3, '04c89212459ae4b0ce12bfc574b20587', '2025-04-05 20:10:19.875098', 'jpg', 'barrel-twist', 'servicos', 'http://res.cloudinary.com/dt5smeslb/image/upload/v1743894619/servicos/zxiwewlleqrwhndfgunp.jpg', 'servicos/zxiwewlleqrwhndfgunp'),
(4, 'c2c1eed6ad0963ca0a4df1dac0bfcb52', '2025-04-05 20:13:02.900885', 'jpg', 'nago-9', 'servicos', 'http://res.cloudinary.com/dt5smeslb/image/upload/v1743894782/servicos/ddfqnpwcqmdds8ka9qhf.jpg', 'servicos/ddfqnpwcqmdds8ka9qhf'),
(5, '8727dbbb975ab56b1ea6312908945002', '2025-04-05 20:13:46.918687', 'jpg', 'twist-1', 'servicos', 'http://res.cloudinary.com/dt5smeslb/image/upload/v1743894826/servicos/dnvhbe4vintts6slvfym.jpg', 'servicos/dnvhbe4vintts6slvfym');

INSERT INTO servico (
    nome, descricao, tempo_duracao, valor_minimo_servico, valor_maximo_servico, valor_sinal, imagem_id
) VALUES (
             'Naggo',
             'Trança no estilo naggo',
             '02:00:00',
             100.00,
             100.00,
             20.00,
             4
         ),
         ('Nagô', 'nago-2', '01:30:00', 150.00, 150.00, 30.00, 1),
         ('Nagô', 'nago-3', '01:30:00', 150.00, 150.00, 30.00, 1),
         ('Nagô', 'nago-4', '01:30:00', 150.00, 150.00, 30.00, 1),
         ('Nagô', 'nago-5', '01:30:00', 150.00, 150.00, 30.00, 1),
         ('Nagô', 'nago-6', '01:30:00', 150.00, 150.00, 30.00, 1),
         ('Nagô', 'nago-7', '01:30:00', 150.00, 150.00, 30.00, 1),
         ('Nagô', 'nago-8', '01:30:00', 150.00, 150.00, 30.00, 1),
         ('Nagô', 'nago-9', '01:30:00', 150.00, 150.00, 30.00, 1),
         ('Nagô', 'nago-10', '01:30:00', 150.00, 150.00, 30.00, 1),
         ('Twist', 'twist-1', '01:30:00', 200.00, 200.00, 50.00, 5),
         ('Box Braids', 'Trança', '01:30:00', 300.00, 300.00,70.00, 1),
         ('Barrel Twist', 'barrel-twist-1', '01:30:00', 220.00, 220.00,60.00, 3),
         ('Fulani Braids', 'Trança', '01:30:00', 330.00, 330.00,60.00, 1),
         ('Boho Braids', 'Trança', '01:30:00', 380.00, 380.00,60.00, 1),
         ('Lemonade', 'Trança', '01:30:00', 300.00, 300.00,50.00, 1),
         ('Ghana Braids', 'Trança', '01:30:00', 350.00, 350.00,70.00, 1),
         ('Gypsy Braids', 'gypsy-1', '01:30:00', 400.00, 400.00,80.00, 2),
         ('Knotles', 'Trança', '01:30:00', 330.00, 330.00,60.00, 1);


INSERT INTO trancista (
    nome, email, senha, role, tipo
) VALUES (
             'Maria Silva',
             'maria.silva@exemplo.com',
             'senhaSegura123',
             'ADMIN',
             'Trancista'
         );


INSERT INTO auxiliar (
     nome, email, valor_mao_de_obra
) VALUES (
          'João da Silva',
          'joao.silva@email.com',
          150.75
);

INSERT INTO endereco (
    numero, bairro, cep, cidade, complemento, estado, logradouro, regiao, uf
) VALUES (
             518,
             'Vila Ré',
             '03658-000',
             'São Paulo',
             'A',
             'São Paulo',
             'Rua Itinguçu',
             'Leste',
             'SP'
         );

INSERT INTO salao (
    nome, endereco_id
) VALUES (
             'Salgado Tranças',
             1
         );

INSERT INTO caixa (
    data_abertura, data_fechamento, salao_id, receita_total, despesa_total
) VALUES (
             '2024-12-01',
             '2024-12-31',
              1,
              0,
              0
         ),
         (
             '2024-11-01',
             '2024-11-30',
             1,
             0,
             0
         ),
         (
             '2024-10-01',
             '2024-10-31',
             1,
             0,
             0
         );

INSERT INTO produto (
    quantidade_embalagem, valor_compra, valor_venda, marca, nome, tipo, unidade_medida
) VALUES (
             300,
             20.9,
             25.5,
             'Salon Line',
             'Pomada',
            'LOJA',
            'ml'
         ),
         (
             500,
             30.5,
             10.0,
             'Salon Line',
             'Creme',
             'SALAO',
             'ml'
         );

INSERT INTO estoque (
    produto_id, quantidade, salao_id
) VALUES (
             1,
             10,
             1
         ),
         (
             2,
             30,
             1
      );