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
    nome, descricao, tempo_duracao, valor_servico, valor_sinal
) VALUES (
             'Corte de Cabelo',
             'Corte estilo degradê com finalização',
             '01:30:00',
             50.00,
             10.00
         );

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
             0.0,
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