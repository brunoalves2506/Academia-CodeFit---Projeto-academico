# 🏋️ Academia Code Fit - Sistema de Gerenciamento de Academia

## 👥 Integrantes do Grupo
- Bruno Alves Ribeiro de Souza - 42276047
- Samuel de Oliveira Santos - 41761782
- Lucas Felix Romero - 41969286
- Victor Gabriel Alves - 45620105
- Luiz Miguel de Almeida Santos - 44984847
- Gustavo do Carmo Germano - 47880287

---

## 📋 Tema Escolhido
Academia

---

## 🎯 Objetivo do Sistema

O sistema tem como objetivo gerenciar as principais operações de uma academia, permitindo o cadastro e controle de alunos, instrutores, planos, aulas e frequência dos alunos.

---

## 📦 Funcionalidades Principais

1. Cadastro de alunos (nome, CPF, data de nascimento, telefone e email)
2. Cadastro de instrutores
3. Gerenciamento de planos da academia
4. Cadastro e controle de aulas
5. Registro de frequência dos alunos
6. Controle de inscrições em aulas
7. Visualização de relatórios

---

## 🏗️ Estrutura de Classes (Planejada)

- **Aluno:** Armazena dados do aluno (CPF, nome, data de nascimento, contato)
- **Instrutor:** Representa os instrutores da academia
- **Plano:** Define os tipos de planos disponíveis
- **Aula:** Representa as aulas oferecidas
- **Inscricao:** Relaciona alunos às aulas
- **Frequencia:** Registra a presença dos alunos
- **ViewMenu:** Responsável pela interface com o usuário (menus)
- **Main:** Classe principal responsável pela execução do sistema

---

## 🔄 Regra de Negócio Complexa

O sistema implementa o controle de frequência dos alunos, onde cada presença é registrada com base no aluno (identificado pelo CPF), data e horário de entrada.

Além disso, o sistema pode validar regras como:
- Impedir registro de frequência duplicada no mesmo dia
- Relacionar automaticamente a frequência ao aluno cadastrado
- Garantir que apenas alunos válidos possam ter presença registrada

Essas regras garantem a consistência dos dados e o correto funcionamento do sistema.

---