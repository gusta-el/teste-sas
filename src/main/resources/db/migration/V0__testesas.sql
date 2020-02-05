CREATE TABLE ALUNO(
   id_aluno INT NOT NULL AUTO_INCREMENT,
   nome_aluno VARCHAR(250),
   sobrenome_aluno VARCHAR(250),
   nota_final DECIMAL(20, 2),
   id_simulado INT,
   PRIMARY KEY(id_aluno)
);


CREATE TABLE PROVA_ALUNO(
   id_aluno_prova INT NOT NULL AUTO_INCREMENT,
   nota INT,
   id_prova INT,
   id_aluno INT,
   respostas_acertos VARCHAR(10),
   gabarito_aluno VARCHAR(10),
   prova_finalizada BOOL,
   PRIMARY KEY(id_aluno, id_prova, id_aluno_prova)
);

CREATE TABLE SIMULADO(
   id_simulado INT NOT NULL AUTO_INCREMENT,
   nome_simulado VARCHAR(250),
   data_simulado DATE,
   simulado_encerrado BOOL,
   PRIMARY KEY(id_simulado)
);

CREATE TABLE PROVA(
   id_prova INT NOT NULL AUTO_INCREMENT,
   nome_prova VARCHAR(250),
   id_simulado INT,
   PRIMARY KEY(id_prova)
);


CREATE TABLE QUESTAO(
   id_questao INT NOT NULL AUTO_INCREMENT,
   enunciado VARCHAR(500),
   complexidade varchar(10),
   alternativa_correta CHAR,
   numero_questao INT,
   questao_respondida BOOL,
   id_prova INT,
   PRIMARY KEY(id_questao)
);

ALTER TABLE PROVA_ALUNO ADD FOREIGN KEY (ID_ALUNO) REFERENCES ALUNO(id_aluno);
ALTER TABLE PROVA_ALUNO ADD FOREIGN KEY (id_prova) REFERENCES PROVA(id_prova);
ALTER TABLE PROVA ADD FOREIGN KEY (id_simulado) REFERENCES SIMULADO(id_simulado);
ALTER TABLE QUESTAO ADD FOREIGN KEY (id_prova) REFERENCES PROVA(id_prova);
ALTER TABLE ALUNO ADD FOREIGN KEY (id_simulado) REFERENCES ALUNO(id_simulado);

INSERT INTO ALUNO (nome_aluno, sobrenome_aluno, id_simulado, nota_final)
VALUES ('Gustavo', 'Cabrera', 1, 0);

INSERT INTO ALUNO (nome_aluno, sobrenome_aluno, id_simulado, nota_final)
VALUES ('Albus', 'Dumbledore', 1, 0);

INSERT INTO ALUNO (nome_aluno, sobrenome_aluno, id_simulado, nota_final)
VALUES ('Hermione', 'Granger', 1, 0);

INSERT INTO ALUNO (nome_aluno, sobrenome_aluno, id_simulado, nota_final)
VALUES ('Clark', 'Kent', 1, 0);

INSERT INTO ALUNO (nome_aluno, sobrenome_aluno, id_simulado, nota_final)
VALUES ('Belatriz', 'Lestrange', 1, 0);

INSERT INTO ALUNO (nome_aluno, sobrenome_aluno, id_simulado, nota_final)
VALUES ('Carmen', 'Cabrera', 1, 0);


INSERT INTO SIMULADO (nome_simulado, data_simulado, simulado_encerrado)
VALUES ('Ciências da Natureza e Suas Tecnologias', '2020-01-25', true);

INSERT INTO SIMULADO (nome_simulado, data_simulado, simulado_encerrado)
VALUES ('Linguagens Códigos e Suas Tecnologias', '2020-01-26', false);

INSERT INTO SIMULADO (nome_simulado, data_simulado, simulado_encerrado)
VALUES ('Ciências Humanas e Suas Tecnologias', '2020-01-26', false);

INSERT INTO SIMULADO (nome_simulado, data_simulado, simulado_encerrado)
VALUES ('Matemática e Suas Tecnologias', '2020-01-25', false);


INSERT INTO PROVA (nome_prova, id_simulado)
VALUES ('Biologia', 1);

INSERT INTO PROVA (nome_prova, id_simulado)
VALUES ('Química', 1);

INSERT INTO PROVA (nome_prova, id_simulado)
VALUES ('Física', 1);

INSERT INTO PROVA (nome_prova, id_simulado)
VALUES ('Geografia', 2);

INSERT INTO PROVA (nome_prova, id_simulado)
VALUES ('História', 2);

INSERT INTO PROVA (nome_prova, id_simulado)
VALUES ('Sociologia', 2);

INSERT INTO PROVA (nome_prova, id_simulado)
VALUES ('Filosofia', 2);

INSERT INTO PROVA (nome_prova, id_simulado)
VALUES ('Língua Portuguesa', 3);

INSERT INTO PROVA (nome_prova, id_simulado)
VALUES ('Literatura', 3);

INSERT INTO PROVA (nome_prova, id_simulado)
VALUES ('Língua Estrangeira', 3);

INSERT INTO PROVA (nome_prova, id_simulado)
VALUES ('Matemática', 4);


INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (1, 1, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (2, 1, '          ', '          ', 5, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (3, 1, '          ', '          ', 5, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (4, 1, '          ', '          ', 5, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (5, 1, '          ', '          ', 5, false);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (6, 1, '          ', '          ', 5, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (7, 1, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (8, 1, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (9, 1, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (10, 1, '          ', '          ', 10, true);


INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (1, 2, '          ', '          ', 0, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (2, 2, '          ', '          ', 0, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (3, 2, '          ', '          ', 0, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (4, 2, '          ', '          ', 0, false);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (5, 2, '          ', '          ', 0, false);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (6, 2, '          ', '          ', 0, false);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (7, 2, '          ', '          ', 0, false);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (8, 2, '          ', '          ', 0, false);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (9, 2, '          ', '          ', 0, false);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (10, 2, '          ', '          ', 10, true);

INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (1, 3, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (2, 3, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (3, 3, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (4, 3, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (5, 3, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (6, 3, '          ', '          ', 1, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (7, 3, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (8, 3, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (9, 3, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (10, 3, '          ', '          ', 10, true);

INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (1, 4, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (2, 4, '          ', '          ', 6, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (3, 4, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (4, 4, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (5, 4, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (6, 4, '          ', '          ', 6, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (7, 4, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (8, 4, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (9, 4, '          ', '          ', 7, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (10, 4, '          ', '          ', 10, true);

INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (1, 5, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (2, 5, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (3, 5, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (4, 5, '          ', '          ', 6, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (5, 5, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (6, 5, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (7, 5, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (8, 5, '          ', '          ', 4, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (9, 5, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (10, 5, '          ', '          ', 10, true);

INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (1, 6, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (2, 6, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (3, 6, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (4, 6, '          ', '          ', 6, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (5, 6, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (6, 6, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (7, 6, '          ', '          ', 2, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (8, 6, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (9, 6, '          ', '          ', 10, true);
INSERT INTO PROVA_ALUNO (ID_PROVA, id_aluno, respostas_acertos, gabarito_aluno, nota, prova_finalizada)
VALUES (10, 6, '          ', '          ', 8, true);





INSERT INTO QUESTAO (enunciado, complexidade, alternativa_correta, numero_questao, id_prova)
VALUES ('Non cras amet ante hendrerit adipiscing.', 'FÁCIL', 'C', 1, 1);

INSERT INTO QUESTAO (enunciado, complexidade, alternativa_correta, numero_questao, id_prova)
VALUES ('Cursus justo imperdiet ullamcorper dui ante.', 'FÁCIL', 'C', 2, 1);

INSERT INTO QUESTAO (enunciado, complexidade, alternativa_correta, numero_questao, id_prova)
VALUES ('Justo inceptos tristique varius felis at.', 'FÁCIL', 'C', 3, 1);

INSERT INTO QUESTAO (enunciado, complexidade, alternativa_correta, numero_questao, id_prova)
VALUES ('Ultricies cubilia class dapibus sodales cursus.', 'MÉDIA', 'C', 4, 1);

INSERT INTO QUESTAO (enunciado, complexidade, alternativa_correta, numero_questao, id_prova)
VALUES ('Aenean convallis gravida per potenti aliquam habitasse.', 'MÉDIA', 'C', 5, 1);

INSERT INTO QUESTAO (enunciado, complexidade, alternativa_correta, numero_questao, id_prova)
VALUES ('A nullam donec rutrum ultricies magna ullamcorper nunc tempus.', 'MÉDIA', 'C', 6, 1);

INSERT INTO QUESTAO (enunciado, complexidade, alternativa_correta, numero_questao, id_prova)
VALUES ('At risus netus sit vel curabitur, eleifend cubilia porta.', 'MÉDIA', 'C', 7, 1);

INSERT INTO QUESTAO (enunciado, complexidade, alternativa_correta, numero_questao, id_prova)
VALUES ('Odio imperdiet ut elementum velit inceptos, lobortis.', 'DIFÍCIL', 'C', 8, 1);

INSERT INTO QUESTAO (enunciado, complexidade, alternativa_correta, numero_questao, id_prova)
VALUES ('Sem nulla iaculis tortor netus etiam hac nam lobortis.', 'DIFÍCIL', 'C', 9, 1);

INSERT INTO QUESTAO (enunciado, complexidade, alternativa_correta, numero_questao, id_prova)
VALUES ('Adipiscing ullamcorper mi ac diam vel, ipsum primis.', 'DIFÍCIL', 'C', 10, 1);




