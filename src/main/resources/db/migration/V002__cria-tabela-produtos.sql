DROP TABLE IF EXISTS produto;
CREATE TABLE produto (
    produtoID BIGINT NOT NULL AUTO_INCREMENT,
    sku VARCHAR(70),
    nome VARCHAR(70) NOT NULL,
    descricao VARCHAR(100),
    preco VARCHAR(14) NOT NULL,
    quantidade INT(10) NOT NULL,
    
    PRIMARY KEY (produtoID)
);
INSERT INTO produto VALUES(12,'LIV-AZU-ARQ','Livro','Livro de entretenimento','100,00',20);
INSERT INTO produto VALUES(6,'CEL-VER-SAM','Celular','Celular samsung','1.200,00',50);
INSERT INTO produto VALUES(33,'TEL-PRE-SON','Televisao','Televisao sony','1.500,00',10);