-- Criação da tabela de usuários
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('USER', 'ADMIN') NOT NULL
);

-- Criação da tabela de produtos
CREATE TABLE product (
    id_product BIGINT AUTO_INCREMENT PRIMARY KEY,
    name_product VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price DOUBLE NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    sub_categoria VARCHAR(255) NOT NULL,
    genero ENUM('MASCULINO', 'FEMININO') NOT NULL,
    image_url VARCHAR(255)
);

-- Criação da tabela de pedidos
CREATE TABLE orders (
    id_order BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    total DOUBLE DEFAULT 0.0,
    status ENUM('PENDING', 'CONFIRMED', 'DELIVERED', 'CANCELED') DEFAULT 'PENDING',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Criação da tabela de itens do pedido
CREATE TABLE items_order (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id_order) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id_product) ON DELETE CASCADE
);