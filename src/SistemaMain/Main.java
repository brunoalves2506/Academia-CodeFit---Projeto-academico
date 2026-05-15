//Execução do código Main--

package SistemaMain;

//Imports importantes para o sistema--
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

//Imports dos pacotes--
import DAO.*;
import Model.*;
import Service.*;
import Util.*;
import View.ViewMenu;

public class Main{

    //Função escolherOpcao() com validação que retorna um valor inteiro--
    private static int escolherOpcao(EntradaUtil entradaUtil){
        return entradaUtil.lerInt("Informe a ação a ser tomada: ");
    }

    //Criação do scanner--
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Requisição dos pacotes para utilização no Main--
        AlunoService alunoService = new AlunoService();
        InstrutorService instrutorService = new InstrutorService();
        PlanoService planoService = new PlanoService();
        AulaColetivaService aulaColetivaService = new AulaColetivaService();
        InscricaoService inscricaoService = new InscricaoService();
        EntradaUtil entradaUtil = new EntradaUtil(scanner);
        ViewMenu viewMenu = new ViewMenu();
        AlunoDAO alunoDAO = new AlunoDAO();
        PlanoDAO planoDAO = new PlanoDAO();
        InscricaoDAO inscricaoDAO = new InscricaoDAO();
        AulaColetivaDAO aulaColetivaDAO = new AulaColetivaDAO();
        InstrutorDAO instrutorDAO = new InstrutorDAO();

        //Parâmetros da função escolherOpcao()--
        int opcao = 0;
        int opcaoAluno = 0;
        int opcaoEdicaoAluno = 0;
        int opcaoInstrutor = 0;
        int opcaoPlano = 0;
        int opcaoAulas = 0;
        int opcaoInscricoes = 0;
        int opcaoFrequencia = 0;
        int opcaoRelatorios = 0;

        //Execução do código--
        do {
            viewMenu.exibirMenuPrincipal();

            opcao = escolherOpcao(entradaUtil);

            switch (opcao){

                //Interação com o menu Aluno--
                case 1:

                    do {
                        viewMenu.exibirMenuAluno();
                        opcaoAluno = escolherOpcao(entradaUtil);

                        Aluno aluno = null;

                        switch (opcaoAluno){

                            //Cadastro de Aluno--
                            case 1:

                                //Verifica se existe um plano no banco de dados para atribuir ao aluno--
                                if (!planoService.verificarPlanosVazio()){
                                    break;
                                }

                                //Recebe os parâmetros do objeto Aluno--
                                String cpfAluno = entradaUtil.lerCpf("Informe o CPF do Aluno: ");
                                String nomeAluno = entradaUtil.lerTexto("Informe o nome do Aluno:");
                                LocalDate dataNascimentoAluno = entradaUtil.lerData("Digite a data de nascimento (AAAA-MM-DD):");
                                String telefoneAluno = entradaUtil.lerTelefone("Informe o telefone do Aluno: ");
                                String emailAluno = entradaUtil.lerEmail("Informe o email do Aluno: ");

                                //Exibe os planos para que um seja atribuído ao aluno--
                                planoDAO.exibirPlanoBd();

                                int planoId = entradaUtil.lerInt("Informe o ID do Plano: ");

                                Plano planoEscolhido = planoDAO.buscarPlanoPorId(planoId);

                                if (planoEscolhido == null){
                                    System.out.println("Plano não encontrado! Encerrando cadastro.");
                                    break;
                                }

                                //Cria o objeto Aluno--
                                aluno = new Aluno(cpfAluno, nomeAluno, dataNascimentoAluno, telefoneAluno, emailAluno, planoEscolhido);

                                //Adiciona o objeto Aluno no banco de dados--
                                alunoDAO.cadastrarAluno(aluno);
                                break;

                            //Menu de edição de Aluno--
                            case 2:
                                do{
                                    viewMenu.exibirMenuEdicaoAluno();
                                    opcaoEdicaoAluno = escolherOpcao(entradaUtil);
                                    switch (opcaoEdicaoAluno){
                                        case 1:

                                            if(!alunoService.verificarAlunosVazio()){
                                                break;
                                            }

                                            alunoDAO.exibirAlunosBd();
                                            cpfAluno = entradaUtil.lerCpf("Informe o CPF do Aluno que deseja alterar: ");

                                            aluno = alunoDAO.buscarAlunoCpf(cpfAluno);
                                            System.out.println(aluno);

                                            String novoNomeAluno = entradaUtil.lerTexto("Informe o novo nome do Aluno:");
                                            LocalDate novaDataNascimentoAluno = entradaUtil.lerData("Digite a nova data de nascimento (AAAA-MM-DD):");
                                            String novoTelefoneAluno = entradaUtil.lerTelefone("Informe o novo telefone do Aluno: ");
                                            String novoEmailAluno = entradaUtil.lerEmail("Informe o novo email do Aluno: ");

                                            alunoDAO.editarInfoAluno(novoNomeAluno, novaDataNascimentoAluno, novoTelefoneAluno, novoEmailAluno, aluno);
                                            break;

                                        case 2:

                                            if(!alunoService.verificarAlunosVazio()){
                                                break;
                                            }

                                            alunoDAO.exibirAlunosBd();
                                            cpfAluno = entradaUtil.lerCpf("Informe o CPF do Aluno que deseja alterar o Plano: ");

                                            aluno = alunoDAO.buscarAlunoCpf(cpfAluno);
                                            System.out.println(aluno);

                                            planoDAO.exibirPlanoBd();

                                            int idNovoPlano = entradaUtil.lerInt("Informe o ID do Novo Plano: ");
                                            planoEscolhido = planoDAO.buscarPlanoPorId(idNovoPlano);

                                            alunoDAO.editarPlanoAluno(planoEscolhido, aluno);
                                            break;

                                    }
                                }while (opcaoEdicaoAluno != 0);
                                break;

                            //Exclui o aluno desejado--
                            case 3:
                                if(!alunoService.verificarAlunosVazio()){
                                    break;
                                }

                                alunoDAO.exibirAlunosBd();
                                cpfAluno = entradaUtil.lerCpf("Informe o CPF do Aluno que deseja excluir: ");

                                aluno = alunoDAO.buscarAlunoCpf(cpfAluno);
                                System.out.println(aluno);

                                while (true){
                                    String acao = entradaUtil.lerTexto("Confirmar exclusão de aluno? (S/N): ").toLowerCase();
                                    if (acao.equals("n")){
                                        System.out.println("Exclusão cancelada com sucesso.");
                                        break;

                                    }else if (acao.equals("s")){
                                        alunoDAO.excluirAluno(cpfAluno);
                                        break;

                                    }else{
                                        System.out.println("Informe uma ação válida.");
                                    }

                                }

                                break;

                            //Exibição de Alunos--
                            case 4:

                                alunoDAO.exibirAlunosBd();
                                break;

                            //Registra a chegada do Aluno--
                            case 5:

                                alunoDAO.exibirAlunosBd();
                                
                                String cpfbusca = entradaUtil.lerCpf("Informe o cpf do Aluno: ");
                                aluno = alunoDAO.buscarAlunoCpf(cpfbusca);

                                if (aluno == null) {
                                    System.out.println("Aluno não encontrado.");
                                    break;
                                }

                                LocalTime horarioChegada = entradaUtil.lerHorario("Informe o horário de chegada: ");
                                break;

                            //Registra a saída do Aluno--
                            case 6:

                                alunoDAO.exibirAlunosBd();

                                String cpfbuscar = entradaUtil.lerCpf("Informe o cpf do Aluno: ");
                                aluno = alunoDAO.buscarAlunoCpf(cpfbuscar);

                                if (aluno == null) {
                                    System.out.println("Aluno não encontrado.");
                                    break;
                                }

                                LocalTime horarioSaida = entradaUtil.lerHorario("Informe o horário de saída: ");
                                aluno.passarCatracaSaida(horarioSaida);
                                break;

                        }

                    } while (opcaoAluno != 0);
                    break;

                //Interação com o menu Instrutor--
                case 2:

                    do {
                        viewMenu.exibirMenuInstrutor();
                        opcaoInstrutor = escolherOpcao(entradaUtil);

                        switch (opcaoInstrutor){

                            //Cadastro de Instrutor--
                            case 1:

                                //Recebe os parâmetros do objeto instrutor--
                                String cpfInstrutor = entradaUtil.lerCpf("Informe o cpf do intrutor: ");
                                String nomeInstrutor = entradaUtil.lerTexto("Informe o nome do instrutor");
                                LocalDate dataNascimentoInstrutor = entradaUtil.lerData("Digite a data de nascimento (AAAA-MM-DD):");
                                String telefoneInstrutor = entradaUtil.lerTelefone("Informe o telefone do instrutor: ");
                                String emailInstrutor = entradaUtil.lerEmail("Informe o Email do instrutor: ");
                                String especialidade = entradaUtil.lerTexto("Informe a especialidade do instrutor: ");
                                LocalTime horarioAtivo = entradaUtil.lerHorario("Digite o horário ativo ");
                                BigDecimal salario = entradaUtil.lerBigDecimal("Informe o salário do Instrutor (Piso salarial: 2900): ");

                                //Cria o objeto Instrutor--
                                Instrutor instrutor = new Instrutor(cpfInstrutor, nomeInstrutor, dataNascimentoInstrutor, telefoneInstrutor, emailInstrutor, especialidade, horarioAtivo, salario
                                );

                                //Adiciona o objeto no Banco de Dados--
                                instrutorDAO.cadastrarInstrutor(instrutor);
                                break;

                            //Edita as informações gerais do Instrutor selecionado--
                            case 2:

                                if(!instrutorService.verificarInstrutoresVazio()){
                                    break;
                                }

                                instrutorDAO.exibirInstrutoresBd();
                                cpfInstrutor = entradaUtil.lerCpf("Informe o cpf do Instrutor que deseja editar: ");

                                instrutor = instrutorDAO.buscarProfessorCpf(cpfInstrutor);
                                System.out.println(instrutor);

                                String novoNomeInstrutor = entradaUtil.lerTexto("Informe o novo nome do instrutor");
                                LocalDate novaDataNascimentoInstrutor = entradaUtil.lerData("Digite a nova data de nascimento (AAAA-MM-DD):");
                                String novoTelefoneInstrutor = entradaUtil.lerTelefone("Informe o novo telefone do instrutor: ");
                                String novoEmailInstrutor = entradaUtil.lerEmail("Informe o novo Email do instrutor: ");
                                String novaEspecialidade = entradaUtil.lerTexto("Informe a nova especialidade do instrutor: ");
                                LocalTime novoHorarioAtivo = entradaUtil.lerHorario("Digite o novo horário ativo (HH:MM):");
                                BigDecimal novoSalario = entradaUtil.lerBigDecimal("Informe o novo salário do Instrutor (Piso salarial: 2900): ");

                                instrutorDAO.editarInstrutor(novoNomeInstrutor, novaDataNascimentoInstrutor, novoTelefoneInstrutor, novoEmailInstrutor, novaEspecialidade, novoHorarioAtivo, novoSalario, instrutor);
                                break;

                            //Exclui o Instrutor do banco de dados--
                            case 3:

                                if(!instrutorService.verificarInstrutoresVazio()){
                                    break;
                                }

                                instrutorDAO.exibirInstrutoresBd();
                                cpfInstrutor = entradaUtil.lerCpf("Informe o CPF do Instrutor que deseja excluir: ");

                                instrutor = instrutorDAO.buscarProfessorCpf(cpfInstrutor);
                                System.out.println(instrutor);

                                while (true){
                                    String acao = entradaUtil.lerTexto("Confirmar exclusão de Instrutor? (S/N): ").toLowerCase();
                                    if (acao.equals("n")){
                                        System.out.println("Exclusão cancelada com sucesso.");
                                        break;

                                    }else if (acao.equals("s")){
                                        instrutorDAO.excluirInstrutor(cpfInstrutor);
                                        break;

                                    }else{
                                        System.out.println("Informe uma ação válida.");
                                    }

                                }


                                break;

                            //Exibição de Instrutores--
                            case 4:

                                instrutorDAO.exibirInstrutoresBd();
                                break;
                        }

                    } while (opcaoInstrutor != 0);
                    break;

                //Interação com o menu Planos--
                case 3:

                    do {
                        viewMenu.exibirMenuPlanos();
                        opcaoPlano = escolherOpcao(entradaUtil);

                        switch (opcaoPlano){

                            //Cadastro de Plano--
                            case 1:

                                //Recebe os parâmetros do objeto plano--
                                int idPlano = entradaUtil.lerInt("Informe o ID do plano: ");
                                String nomePlano = entradaUtil.lerTexto("Informe o nome do plano: ");
                                String descricaoPlano = entradaUtil.lerTexto("Informe a descrição do plano: ");
                                BigDecimal valorMensal = entradaUtil.lerBigDecimal("Informe o valor do plano: ");
                                int duracaoMeses = entradaUtil.lerInt("Informe a duração em meses: ");
                                String beneficios = entradaUtil.lerTexto("Informe os benefícios do plano: ");

                                //Criação do objeto Plano--
                                Plano plano = new Plano (idPlano, nomePlano, descricaoPlano, valorMensal, duracaoMeses, beneficios);

                                //Adição do plano no Banco de dados--
                                planoDAO.cadastrarPlano(plano);
                                break;

                            //Edita o Plano escolhido--
                            case 2:

                                if(!planoService.verificarPlanosVazio()){
                                    break;
                                }

                                planoDAO.exibirPlanoBd();
                                idPlano = entradaUtil.lerInt("Informe o ID do plano que deseja editar: ");
                                plano = planoDAO.buscarPlanoPorId(idPlano);
                                System.out.println(plano);

                                nomePlano = entradaUtil.lerTexto("Informe o novo nome do plano: ");
                                descricaoPlano = entradaUtil.lerTexto("Informe a nova descrição do plano: ");
                                valorMensal = entradaUtil.lerBigDecimal("Informe o novo valor do plano: ");
                                duracaoMeses = entradaUtil.lerInt("Informe a nova duração em meses do plano: ");
                                beneficios = entradaUtil.lerTexto("Informe os novos beneficios do plano: ");

                                planoDAO.editarPlano(nomePlano, descricaoPlano, valorMensal, duracaoMeses, beneficios, plano);
                                break;

                            //Exclui o plano do banco de dados--
                            case 3:

                                if(!planoService.verificarPlanosVazio()){
                                    break;
                                }

                                planoDAO.exibirPlanoBd();
                                idPlano = entradaUtil.lerInt("Informe o ID do Plano que deseja excluir: ");

                                plano = planoDAO.buscarPlanoPorId(idPlano);
                                System.out.println(plano);

                                while (true){
                                    String acao = entradaUtil.lerTexto("Confirmar exclusão do Plano? (S/N): ").toLowerCase();
                                    if (acao.equals("n")){
                                        System.out.println("Exclusão cancelada com sucesso.");
                                        break;

                                    }else if (acao.equals("s")){
                                        planoDAO.excluirPlano(idPlano);
                                        break;

                                    }else{
                                        System.out.println("Informe uma ação válida.");
                                    }

                                }

                                break;

                            //Exibição dos Planos--
                            case 4:

                                planoDAO.exibirPlanoBd();
                                break;

                        }

                    } while (opcaoPlano != 0);
                    break;

                //Interação com o menu Aulas--
                case 4:

                    do {
                        viewMenu.exibirMenuAulas();
                        opcaoAulas = escolherOpcao(entradaUtil);

                        AulaColetiva aulaColetiva = null;

                        switch (opcaoAulas){

                            //Cadastro de Aula--
                            case 1:

                                Instrutor instrutorEscolhido = null;

                                //Recebe os parâmetros do objeto Aula--
                                int idAula = entradaUtil.lerInt("Informe o ID do Aula: ");

                                //Exibe os instrutores presentes no banco de dados para fazer o cadastro na aula--
                                instrutorDAO.exibirInstrutoresBd();
                                //Busca o instrutor pelo cpf--
                                String cpfInstrutor = entradaUtil.lerCpf("Informe o CPF do instrutor que vai ministrar a aula: ");
                                instrutorEscolhido = instrutorDAO.buscarProfessorCpf(cpfInstrutor);

                                String nomeAula = entradaUtil.lerTexto("Informe o nome da Aula: ");
                                LocalTime horarioAula = entradaUtil.lerHorario("Digite o horario da Aula: ");
                                int duracaoMinutosAula = entradaUtil.lerInt("Informe a duração em minutos da Aula: ");

                                aulaColetiva = new AulaColetiva(idAula, instrutorEscolhido, nomeAula, horarioAula, duracaoMinutosAula);
                                aulaColetivaDAO.cadastrarAulaColetiva(aulaColetiva);
                                break;

                            //Edita a aula coletiva selecionada--
                            case 2:

                                if(!aulaColetivaService.verificarAulaColetivaVazia()){
                                    System.out.println("Nenhuma Aula cadastrada.");
                                    break;
                                }

                                aulaColetivaDAO.exibirAulasColetivasBD();
                                idAula = entradaUtil.lerInt("Informe o ID do Aula que deseja alterar: ");
                                aulaColetiva = aulaColetivaDAO.buscarAulaColetivaPorId(idAula);

                                System.out.println(aulaColetiva);

                                //Exibe os instrutores presentes no banco de dados para fazer o cadastro na aula--
                                instrutorDAO.exibirInstrutoresBd();
                                //Busca o instrutor pelo cpf--
                                String cpfNovoInstrutor = entradaUtil.lerCpf("Informe o CPF do instrutor que vai ministrar a aula: ");
                                instrutorEscolhido = instrutorDAO.buscarProfessorCpf(cpfNovoInstrutor);

                                String novoNomeAula = entradaUtil.lerTexto("Informe o nome da Aula: ");
                                LocalTime novoHorarioAula = entradaUtil.lerHorario("Digite o horario da Aula: ");
                                int novaDuracaoMinutosAula = entradaUtil.lerInt("Informe a duração em minutos da Aula: ");

                                aulaColetivaDAO.editarAulaColetiva(novoNomeAula, instrutorEscolhido, aulaColetiva.getCapacidadeMax(), novoHorarioAula, novaDuracaoMinutosAula, aulaColetiva);
                                break;

                            //Exclui a Aula Coletiva do banco de dados--
                            case 3:

                                if (!aulaColetivaService.verificarAulaColetivaVazia()){
                                    break;
                                }

                                aulaColetivaDAO.exibirAulasColetivasBD();
                                idAula = entradaUtil.lerInt("Informe o ID da Aula que deseja excluir: ");

                                aulaColetiva = aulaColetivaDAO.buscarAulaColetivaPorId(idAula);

                                if (aulaColetiva == null){
                                    System.out.println("Aula não encontrada!");
                                    break;
                                }

                                System.out.println(aulaColetiva);

                                while (true){
                                    String acao = entradaUtil.lerTexto("Confirmar exclusão da Aula Coletiva? (S/N): ").toLowerCase();
                                    if (acao.equals("n")){
                                        System.out.println("Exclusão cancelada com sucesso.");
                                        break;

                                    }else if (acao.equals("s")){
                                        aulaColetivaDAO.excluirAulaColetiva(idAula); // <- int, não o objeto
                                        break;

                                    }else{
                                        System.out.println("Informe uma ação válida.");
                                    }
                                }

                                break;

                            //Exibe as aulas coletivas cadastradas no Banco de Dados--
                            case 4:

                                aulaColetivaDAO.exibirAulasColetivasBD();
                                break;

                        }

                    } while (opcaoAulas != 0);
                    break;

                //Interação com o menu Inscricoes--
                case 5:

                    do {
                        viewMenu.exibirMenuInscricoes();
                        opcaoInscricoes = escolherOpcao(entradaUtil);

                        Inscricao inscricao = null;

                        switch (opcaoInscricoes){

                            //Cadastra uma inscrição no banco de dados--
                            case 1:


                                int idInscricao = entradaUtil.lerInt("Informe o id da inscrição: ");

                                alunoDAO.exibirAlunosBd();
                                String cpfAluno = entradaUtil.lerCpf("Informe o cpf do aluno: ");

                                //Verifica se o Aluno tem acesso as aulas coletivas através do plano Essentials ou Pro--
                                if(!alunoDAO.verificarPlanoAluno(cpfAluno)){
                                    break;
                                }

                                Aluno aluno = alunoDAO.buscarAlunoCpf(cpfAluno);

                                aulaColetivaDAO.exibirAulasColetivasBD();
                                int idAula = entradaUtil.lerInt("Informe o id da aula coletiva: ");
                                AulaColetiva aulaColetiva = aulaColetivaDAO.buscarAulaColetivaPorId(idAula);

                                int inscritos = inscricaoDAO.contarInscricoesPorAula(idAula);
                                if(inscritos >= aulaColetiva.getCapacidadeMax()){
                                    System.out.println("Erro ao cadastrar aluno na Aula: AULA LOTADA. capacidade maxima: " + aulaColetiva.getCapacidadeMax());
                                    break;
                                }

                                LocalDate dataInscricao = entradaUtil.lerData("informe a data de inscrição: ");

                                String statusInscricao = "ativo";

                                inscricao = new Inscricao(idInscricao, aluno, aulaColetiva, dataInscricao, statusInscricao);

                                inscricaoDAO.cadastrarInscricao(inscricao);

                                break;

                            //Exlclui uma inscrição de aluno0--
                            case 2:

                                if(!inscricaoService.verificarInscricaoVazia()){
                                    break;
                                }

                                inscricaoDAO.exibirInscricoesBd();
                                int idInscricaoSelecionada = entradaUtil.lerInt("Informe o id da inscrição: ");
                                inscricao = inscricaoDAO.buscarInscricaoID(idInscricaoSelecionada);

                                if (inscricao == null){
                                    System.out.println("Inscrição não encontrada!");
                                    break;
                                }

                                System.out.println(inscricao);

                                while (true){
                                    String acao = entradaUtil.lerTexto("Confirmar exclusão da Inscrição? (S/N): ").toLowerCase();
                                    if (acao.equals("n")){
                                        System.out.println("Exclusão cancelada com sucesso.");
                                        break;

                                    }else if (acao.equals("s")){
                                        inscricaoDAO.excluirInscricao(idInscricaoSelecionada);
                                        break;

                                    }else{
                                        System.out.println("Informe uma ação válida.");
                                    }
                                }

                                break;

                            //Exibe as inscrições cadastradas no banco de dados--
                            case 3:

                               inscricaoDAO.exibirInscricoesBd();
                               break;

                        }

                    } while (opcaoInscricoes != 0);
                    break;

                //Interação com o menu FrequenciaAlunos--
                case 6:

                    do {
                        viewMenu.exibirFrequenciaAlunos();
                        opcaoFrequencia = escolherOpcao(entradaUtil);

                        switch (opcaoFrequencia){

                        }

                    } while (opcaoFrequencia != 0);
                    break;

                //Interação com o menu Relatorios--
                case 7:

                    do {
                        viewMenu.exibirMenuRelatorios();
                        opcaoRelatorios = escolherOpcao(entradaUtil);

                        switch (opcaoRelatorios){

                            case 1:

                                if (!aulaColetivaService.verificarAulaColetivaVazia()){
                                    break;
                                }

                                aulaColetivaDAO.exibirAulasColetivasBD();
                                int idAula = entradaUtil.lerInt("Informe o id da aula que deseja vizualizar: ");
                                AulaColetiva aulaColetiva = aulaColetivaDAO.buscarAulaColetivaPorId(idAula);

                                System.out.println(aulaColetiva);

                                aulaColetivaDAO.visualizarOcupacaoAulas(idAula);
                                break;

                        }

                        //Retorna ao Menu Principal--
                    } while (opcaoRelatorios != 0);
                    break;

            }

            //Encerra o programa--
        }while (opcao != 0);

        scanner.close();

    }
}