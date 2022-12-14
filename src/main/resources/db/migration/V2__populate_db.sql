INSERT INTO producers(id, name)
VALUES
('b764b65f-f32f-410f-bae8-072e888ed766', 'producer 1'),
('e9412f08-de3a-48e0-aefb-1217fc9ce205', 'producer 2'),
('55c52f24-1b16-466a-b0c2-15bfa07f43a2', 'producer 3'),
('223bc791-6210-468e-851b-db7fe378f3e9', 'producer 4'),
('5f0e6f6e-39a8-4403-b3bf-c5b28ff4a5b5', 'producer 5');

INSERT INTO products(id,name,price,producer_id)
VALUES
('02da85bd-ae0a-4840-90ec-d2b825e7e7a2', 'product 1', '0.12', 'b764b65f-f32f-410f-bae8-072e888ed766'),
('060fd3f1-1a9d-4dfa-8d61-c771a64a617b', 'product 2', '123.00', 'e9412f08-de3a-48e0-aefb-1217fc9ce205'),
('77a7b4b1-2aee-41bb-ac06-5018bd717a78', 'product 3', '83.20', '55c52f24-1b16-466a-b0c2-15bfa07f43a2'),
('b8ebca41-8041-46e4-a596-1335961ffa06', 'product 4', '58.23', '223bc791-6210-468e-851b-db7fe378f3e9'),
('4a1513cf-b921-47eb-bd98-532c93b3cc84', 'product 5', '2354.92', '5f0e6f6e-39a8-4403-b3bf-c5b28ff4a5b5');

INSERT INTO roles(id,role)
VALUES
('6adc7b2b-7b5f-40f1-9638-f44d3ff55e9d', 'ROLE_ADMIN'),
('9ca9dce6-65df-4648-a732-6dbb75164489', 'ROLE_USER');

INSERT INTO users(id,email,password, first_name, last_name)
VALUES
('bd7f5245-f212-4530-a83a-2861d0786356','admin', '$2a$10$oHm5ZM8iHxh7KZKSRwK7hukYTst/qYSegA/60GEHtYP8kQz3dsLXq', 'first 1', 'last 1'),
('7b9e48e6-9c39-4d95-87eb-39381746557e','user', '$2a$10$8PMEvzIIatqAOvyWWD5YMuujHDnoI3iui4GZhD.kG7ft30fdgYPu2', 'first 1', 'last 1');

INSERT INTO user_roles(role_id,user_id)
VALUES
('6adc7b2b-7b5f-40f1-9638-f44d3ff55e9d', 'bd7f5245-f212-4530-a83a-2861d0786356'),
('9ca9dce6-65df-4648-a732-6dbb75164489', '7b9e48e6-9c39-4d95-87eb-39381746557e');







