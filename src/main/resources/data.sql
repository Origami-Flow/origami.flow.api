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
) VALUES
         (
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