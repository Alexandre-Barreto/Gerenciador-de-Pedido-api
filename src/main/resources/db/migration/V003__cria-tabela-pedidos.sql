DROP TABLE IF EXISTS pedido;
CREATE TABLE pedido (
    pedidoID BIGINT NOT NULL AUTO_INCREMENT,
    clienteID BIGINT,
    produtoID BIGINT,
    total_da_compra VARCHAR(70) NOT NULL,
    data_da_compra DATE,    
    
    PRIMARY KEY (pedidoID)        
);
    
INSERT INTO pedido VALUES(5,1,12,'100,00','2020-09-06');
INSERT INTO pedido VALUES(6,1,6,'1200,00','2020-09-06');
INSERT INTO pedido VALUES(7,2,6,'1200,00','2020-09-06');
INSERT INTO pedido VALUES(9,3,33,'1500,00','2020-09-06');

ALTER TABLE pedido ADD CONSTRAINT FK_ClientePedido FOREIGN KEY (clienteID) REFERENCES cliente (clienteID);
ALTER TABLE pedido ADD CONSTRAINT FK_ProdutoPedido FOREIGN KEY (produtoID) REFERENCES produto (produtoID);