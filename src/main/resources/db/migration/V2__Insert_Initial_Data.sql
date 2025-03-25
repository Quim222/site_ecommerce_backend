-- Inserindo usuários
INSERT INTO users (nome, email, password, role) VALUES 
('Admin', 'admin@email.com', 'admin123', 'ADMIN'),
('User1', 'user1@email.com', 'user123', 'USER');

-- Inserindo produtos
INSERT INTO product (name_product, description, price, categoria, sub_categoria, genero, image_url) VALUES 
('Calças Jeans Feminina', 'Calça de ganga azul', 39.99, 'Roupa', 'Calças', 'FEMININO', 'https://images.unsplash.com/photo-1624378439575-d8705ad7ae80?q=80&w=1994&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
('Camisa Cinza', 'Camisa Cinza de ganga comprida', 19.90, 'Roupa', 'Camisa', 'FEMININO', 'https://images.unsplash.com/photo-1589359425603-dfe010cf3ffa?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
('Camisola Azul', 'Camisola Azul de malha', 19.90, 'Roupa', 'Camisa', 'MASCULINO', 'https://images.unsplash.com/photo-1714023498660-e4c470b285b7?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
('Camisola Vermelha', 'Camisola Vermelha de malha', 39.90, 'Roupa', 'Camisa', 'MASCULINO', 'https://images.unsplash.com/photo-1506634572416-48cdfe530110?q=80&w=1970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
('Camisa Branca', 'Camisa branca de pescoço V marrom', 50.00, 'Roupa', 'Camisa', 'FEMININO', 'https://images.unsplash.com/photo-1434389677669-e08b4cac3105?q=80&w=2010&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
('Blazer + Calças', 'Blazer preto e calças', 79.99, 'Roupa', 'Combinações', 'FEMININO', 'https://images.unsplash.com/photo-1584273143981-41c073dfe8f8?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
('T-shirt Thrasher', 'T-shirt Thrasher preta pequena', 79.99, 'Roupa', 'T-shirt', 'FEMININO', 'https://images.unsplash.com/photo-1618375066375-c09d6d1c7925?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
('Sapatos Adidas', 'Sapatos multicoloridos', 100.99, 'Sapatos', 'Tênis', 'FEMININO', 'https://images.unsplash.com/photo-1560769629-975ec94e6a86?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
('Sandálias', 'Sandálias de couro branco', 53.99, 'Sapatos', 'Sandálias', 'FEMININO', 'https://images.unsplash.com/photo-1628182981545-1bead3e9d5be?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
('Sapatos Nike', 'Tênis vermelhos SuperNike', 79.99, 'Sapatos', 'Tênis', 'MASCULINO', 'https://images.unsplash.com/photo-1537261131936-3cdff36a1ac9?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
('Fato Creme', 'Fato social creme', 199.99, 'Roupa', 'Fatos', 'MASCULINO', 'https://images.unsplash.com/photo-1574968699009-6426913fce69?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTl8fHJvdXBhJTIwTUFzY3VsaW5hfGVufDB8fDB8fHwy'),
('Calças Jeans', 'Calças vermelhas', 49.99, 'Roupa', 'Calças', 'MASCULINO', 'https://images.unsplash.com/photo-1578142866716-12c8957d3dc7?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjF8fHJvdXBhJTIwTUFzY3VsaW5hfGVufDB8fDB8fHwy'),
('Gravata', 'Gravata dos patinhos', 19.99, 'Acessórios', 'Gravata', 'MASCULINO', 'https://images.unsplash.com/photo-1588191677737-fc8bf09ddcad?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MzF8fHJvdXBhJTIwTUFzY3VsaW5hfGVufDB8fDB8fHwy');
