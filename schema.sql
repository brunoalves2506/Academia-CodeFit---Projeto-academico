
/* =========================================================
   PROJETO: CodeFit - Sistema de Gerenciamento de Academia
   GRUPO: Bruno Alves Ribeiro de Souza - 42276047 ; Samuel de Oliveira Santos - 41761782 - Lucas Felix Romero - 41969286 ; Victor Gabriel Alves - 45620105 ;
   DISCIPLINA: Banco de Dados
   PROFESSOR: Caio Gustavo Rodrigues Da Cruz
   DATA: 01/05/2026

   DESCRIÇÃO:
   Script responsável pela criação do banco de dados CodeFit,
   utilizado para gerenciar alunos, planos, instrutores,
   aulas coletivas, inscrições e frequências de uma academia.

   REFERÊNCIA:
   Script baseado no dicionário de dados versão 1.0
   ========================================================= */
 
 
/* =========================================================
   CONFIGURAÇÕES INICIAIS
   ========================================================= */
 
-- Define schema (se aplicável)
SET search_path TO public;
 
 
/* =========================================================
   CRIAÇÃO DE OBJETOS
   ========================================================= */
 
-- Criação da tabela plano;

CREATE TABLE Plano (
idPlano INTEGER PRIMARY KEY NOT NULL, 
nomePlano VARCHAR(100) NOT NULL, 
descricaoPlano VARCHAR(200) NOT NULL, 
valorMensalPlano NUMERIC(10, 2) NOT NULL, 
duracaoMeses INTEGER NOT NULL,
beneficiosPlano VARCHAR(200) NOT NULL
);

-- Criação da tabela Aluno;

CREATE TABLE Aluno (
cpfAluno VARCHAR(11) PRIMARY KEY NOT NULL, 
nomeAluno VARCHAR(100) NOT NULL, 
dataNascimentoAluno DATE NOT NULL, 
telefoneAluno VARCHAR(11) NOT NULL, 
emailAluno VARCHAR(100) NOT NULL,
idPlano INTEGER NOT NULL,

FOREIGN KEY (idPlano) REFERENCES Plano(idPlano)
);

-- Criação da tabela Frequencia;

CREATE TABLE Frequencia (
idFrequencia INTEGER PRIMARY KEY NOT NULL, 
cpfAluno VARCHAR(11) NOT NULL, 
diaEntrada DATE NOT NULL, 
horaEntrada TIME NOT NULL,

FOREIGN KEY (cpfAluno) REFERENCES Aluno(cpfAluno)
);

-- Criação da tabela Instrutor;

CREATE TABLE Instrutor (
cpfInstrutor VARCHAR(11) PRIMARY KEY NOT NULL, 
nomeInstrutor VARCHAR (100) NOT NULL, 
dataNascimentoInstrutor DATE NOT NULL,
telefoneInstrutor VARCHAR(11) NOT NULL, 
emailInstrutor VARCHAR(100) NOT NULL,
especialidade VARCHAR(100) NOT NULL, 
horarioAtivo TIME NOT NULL, 
salario NUMERIC(10, 2) NOT NULL
);

-- Criação da tabela AulaColetiva;

CREATE TABLE AulaColetiva (
idAula INTEGER PRIMARY KEY NOT NULL, 
cpfInstrutor VARCHAR(11) NOT NULL, 
nomeAula VARCHAR(100) NOT NULL, 
capacidadeMax INTEGER NOT NULL, 
horarioAula TIME NOT NULL, 
duracaoAula INTEGER NOT NULL,

FOREIGN KEY (cpfInstrutor) REFERENCES Instrutor(cpfInstrutor)
);

-- Criação da tabela Inscricao;

CREATE TABLE Inscricao (
idInscricao INTEGER PRIMARY KEY NOT NULL, 
cpfAluno VARCHAR(11) NOT NULL,
idAula INT NOT NULL, 
dataInscricao DATE NOT NULL, 
statusInscricao VARCHAR(30) NOT NULL,

FOREIGN KEY (cpfAluno) REFERENCES Aluno(cpfAluno),
FOREIGN KEY (idAula) REFERENCES AulaColetiva(idAula)
);
 
/* =========================================================
OBSERVAÇÕES
========================================================= */

