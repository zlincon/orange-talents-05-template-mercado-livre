INSERT INTO categoria(nome) VALUES ('Celular');
INSERT INTO categoria(nome) VALUES ('Eletrônicos');
INSERT INTO categoria(nome) VALUES ('Roupas');

INSERT INTO usuarios(email, senha) VALUES ('adm@adm.com',
'$2a$10$8vni5dDcy6mV0zRt/ZRG8eK.vbBQC.0jBvBiZQ3asKv30D0S3AXgm');
INSERT INTO usuarios(email, senha) VALUES ('b@b.com',
'$2a$10$8vni5dDcy6mV0zRt/ZRG8eK.vbBQC.0jBvBiZQ3asKv30D0S3AXgm');

INSERT INTO produto(descricao, nome, quantidade, valor, categoria_id, dono_id, TIMESTAMP) VALUES ('Redmi Note 9', 'Xiaomi', 2, 233, 1, 1, CURRENT_TIME());

INSERT INTO caracteristica_produto(nome, descricao, produto_id) VALUES ('Tela', '6.2', 1);
INSERT INTO caracteristica_produto(nome, descricao, produto_id) VALUES ('Processador', 'Snapdragon 626', 1);
INSERT INTO caracteristica_produto(nome, descricao, produto_id) VALUES ('Bateria', '6000 mAmp/h', 1);

INSERT INTO opiniao(descricao, nota, titulo, consumidor_id, produto_id) VALUES ('Gostei', 4, 'Produto bom', 2, 1);
INSERT INTO opiniao(descricao, nota, titulo, consumidor_id, produto_id) VALUES ('Muito bom', 5, 'Excelente', 1, 1);

INSERT INTO pergunta(titulo, usuario_id, produto_id, timestamp) VALUES ('Produto original?', 2, 1, CURRENT_TIMESTAMP());