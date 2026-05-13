/* =========================================================
   PROJETO: CodeFit - Sistema de Gerenciamento de Academia
   GRUPO: Bruno Alves Ribeiro de Souza - 42276047 ; Samuel de Oliveira Santos - 41761782 ; Lucas Felix Romero - 41969286 ; Victor Gabriel Alves - 45620105
   DISCIPLINA: Banco de Dados
   PROFESSOR: Caio Gustavo Rodrigues Da Cruz
   DATA: 13/05/2026

   DESCRIÇÃO:
   Script responsável pela inserção dos dados de exemplo no
   banco de dados CodeFit. Execute este script APÓS o schema.sql.
   ========================================================= */

SET search_path TO public;

-- =========================================================
-- PLANO
-- =========================================================

INSERT INTO Plano (idPlano, nomePlano, descricaoPlano, valorMensalPlano, duracaoMeses, beneficiosPlano) VALUES
                                                                                                            (111, 'CodeFit Essentials ⭐', 'Plano básico ideal para quem está começando. Dá acesso à área de musculação e equipamentos da academia, com acompanhamento de um instrutor na avaliação inicial.', 89.90, 1, '🕐 Acesso 24 horas | 💪 Acesso à area de musculação'),
                                                                                                            (222, 'CodeFit Pro ⭐⭐', 'Plano completo para quem quer ir além. Além de todo o acesso à musculação 24 horas, inclui aulas coletivas com instrutores especializados e participação em sorteios exclusivos para membros.', 140.00, 1, '🕐 Acesso 24 horas | 💪 Acesso à area de musculação | 🏃 Acesso à aulas coletivas | 🎁 Participação em Sorteios');

-- =========================================================
-- INSTRUTOR
-- =========================================================

INSERT INTO Instrutor (cpfInstrutor, nomeInstrutor, dataNascimentoInstrutor, telefoneInstrutor, emailInstrutor, especialidade, horarioAtivo, salario) VALUES
    ('45678912345', 'Rogério Sant''ana', '1989-11-03', '11945678912', 'rogerioSantana@gmail.com', 'Musculação', '13:00:00', 2950.00);

-- =========================================================
-- ALUNO
-- =========================================================

INSERT INTO Aluno (cpfAluno, nomeAluno, dataNascimentoAluno, telefoneAluno, emailAluno, idPlano) VALUES
                                                                                                     ('78945612345', 'Samuel de Oliveira', '2003-05-20', '11978945612', 'samuelOliveira@gmail.com', 111),
                                                                                                     ('12345678945', 'Bruno Souza', '2003-06-25', '11912345678', 'brunoSouza@gmail.com', 222);

-- =========================================================
-- AULA COLETIVA
-- =========================================================

INSERT INTO AulaColetiva (idAula, cpfInstrutor, nomeAula, capacidadeMax, horarioAula, duracaoAula) VALUES
    (111, '45678912345', 'Musculação 12/05/2026', 10, '14:00:00', 90);

-- =========================================================
-- FREQUENCIA (sem dados cadastrados)
-- =========================================================

-- Nenhum registro inserido.

-- =========================================================
-- INSCRICAO (sem dados cadastrados)
-- =========================================================

-- Nenhum registro inserido.