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

INSERT INTO servico (
    nome, descricao, tempo_duracao, valor_minimo_servico, valor_maximo_servico, valor_sinal
) VALUES (
             'Corte de Cabelo',
             'Corte estilo degradê com finalização',
             '01:30:00',
             50.00,
             50.00,
             10.00
         ),
         (
             'Naggo',
             'Trança no estilo naggo',
             '02:00:00',
             100.00,
            100.00,
             20.00
         ),
         ('Nagô', 'nago-2', '01:30:00', 150.00, 150.00, 30.00),
         ('Nagô', 'nago-3', '01:30:00', 150.00, 150.00, 30.00),
         ('Nagô', 'nago-4', '01:30:00', 150.00, 150.00, 30.00),
         ('Nagô', 'nago-5', '01:30:00', 150.00, 150.00, 30.00),
         ('Nagô', 'nago-6', '01:30:00', 150.00, 150.00, 30.00),
         ('Nagô', 'nago-7', '01:30:00', 150.00, 150.00, 30.00),
         ('Nagô', 'nago-8', '01:30:00', 150.00, 150.00, 30.00),
         ('Nagô', 'nago-9', '01:30:00', 150.00, 150.00, 30.00),
         ('Nagô', 'nago-10', '01:30:00', 150.00, 150.00, 30.00),
         ('Twist', 'twist-1', '01:30:00', 200.00, 200.00, 50.00),
         ('Box Braids', 'Trança', '01:30:00', 300.00, 300.00,70.00),
         ('Barrel Twist', 'barrel-twist-1', '01:30:00', 220.00, 220.00,60.00),
         ('Fulani Braids', 'Trança', '01:30:00', 330.00, 330.00,60.00),
         ('Boho Braids', 'Trança', '01:30:00', 380.00, 380.00,60.00),
         ('Lemonade', 'Trança', '01:30:00', 300.00, 300.00,50.00),
         ('Ghana Braids', 'Trança', '01:30:00', 350.00, 350.00,70.00),
         ('Gypsy Braids', 'gypsy-1', '01:30:00', 400.00, 400.00,80.00),
         ('Knotles', 'Trança', '01:30:00', 330.00, 330.00,60.00);
;

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