INSERT INTO cliente (
    id, nome, email, senha, token,
    data_nascimento, telefone, genero, tipo_cabelo,
    comprimento_cabelo, cor_cabelo, progressiva,
    primeira_tranca, ocupacao, endereco_id
) VALUES (
             1,
          'João Silva',
          'joao.silva@example.com',
          'senha123',
          'tokenExemplo123',
          '1990-05-15',
          '1234567890',
          'MASCULINO',
          'A1',
          'COMPRIDO',
          'CASTANHO',
          true,
          false,
          'Engenheiro',
          null
         );

INSERT INTO servico (
    id, nome, descricao, tempo_duracao, valor_servico, valor_sinal
) VALUES (
             1,
          'Corte de Cabelo',
          'Corte estilo degradê com finalização',
          '01:30:00',
          50.00,
          10.00
         );

INSERT INTO trancista (
    id, nome, email, senha, token, tipo
) VALUES (
             1,
          'Maria Silva',
          'maria.silva@exemplo.com',
          'senhaSegura123',
          'tokenExemplo123',
          'Trancista'
         );

