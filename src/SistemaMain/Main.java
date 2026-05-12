//Execução do código Main--

package SistemaMain;

//Imports importantes para o sistema--
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

//Imports dos pacotes--
import Model.*;
import Service.*;
import Util.*;
import Service.InstrutorService;
import Service.PlanoService;
import View.ViewMenu;
import DAO.*;

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
        EntradaUtil entradaUtil = new EntradaUtil(scanner);
        ViewMenu viewMenu = new ViewMenu();
        AlunoDAO alunoDAO = new AlunoDAO();
        PlanoDAO planoDAO = new PlanoDAO();
        AulaColetivaDAO aulaColetivaDAO = new AulaColetivaDAO();
        InstrutorDAO instrutorDAO = new InstrutorDAO();

        //Parâmetros da função escolherOpcao()--
        int opcao = 0;
        int opcaoAluno = 0;
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
                                planoService.exibirPlanoBd();

                                int planoId = entradaUtil.lerInt("Informe o ID do Plano: ");

                                Plano planoEscolhido = planoService.buscarPlanoPorId(planoId);

                                if (planoEscolhido == null){
                                    System.out.println("Plano não encontrado! Encerrando cadastro.");
                                    break;
                                }

                                //Cria o objeto Aluno--
                                aluno = new Aluno(cpfAluno, nomeAluno, dataNascimentoAluno, telefoneAluno, emailAluno, planoEscolhido);

                                //Adiciona o objeto Aluno no banco de dados--
                                alunoDAO.cadastrarAluno(aluno);
                                break;

                            //Edita o aluno desejado--
                            case 2:

                            //Exclui o aluno desejado--
                            case 3:
                                if(!alunoService.verificarAlunosVazio()){
                                    break;
                                }

                                alunoService.exibirAlunosBd();
                                cpfAluno = entradaUtil.lerCpf("Informe o CPF do Aluno que deseja excluir: ");

                                aluno = alunoService.buscarAlunoCpf(cpfAluno);
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

                                alunoService.exibirAlunosBd();
                                break;

                            //Registra a chegada do Aluno--
                            case 5:

                                alunoService.exibirAlunosBd();
                                
                                String cpfbusca = entradaUtil.lerCpf("Informe o cpf do Aluno: ");
                                aluno = alunoService.buscarAlunoCpf(cpfbusca);

                                if (aluno == null) {
                                    System.out.println("Aluno não encontrado.");
                                    break;
                                }

                                LocalTime horarioChegada = entradaUtil.lerHorario("Informe o horário de chegada: ");
                                aluno.passarCatracaEntrada(horarioChegada);
                                break;

                            //Registra a saída do Aluno--
                            case 6:

                                alunoService.exibirAlunosBd();

                                String cpfbuscar = entradaUtil.lerCpf("Informe o cpf do Aluno: ");
                                aluno = alunoService.buscarAlunoCpf(cpfbuscar);

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

                                LocalTime horarioAtivo = entradaUtil.lerHorario("Digite o horário ativo (HH:MM):");

                                BigDecimal salario = entradaUtil.lerBigDecimal("Informe o salário do Instrutor (Piso salarial: 2900): ");

                                //Cria o objeto Instrutor--
                                Instrutor instrutor = new Instrutor(cpfInstrutor, nomeInstrutor, dataNascimentoInstrutor, telefoneInstrutor, emailInstrutor, especialidade, horarioAtivo, salario
                                );

                                //Adiciona o objeto no Banco de Dados--
                                instrutorDAO.cadastrarInstrutor(instrutor);
                                break;

                            //Exclui o Instrutor do banco de dados--
                            case 3:

                                if(!instrutorService.verificarInstrutoresVazio()){
                                    break;
                                }

                                instrutorService.exibirInstrutoresBd();
                                cpfInstrutor = entradaUtil.lerCpf("Informe o CPF do Instrutor que deseja excluir: ");

                                instrutor = instrutorService.buscarProfessorCpf(cpfInstrutor);
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
                                instrutorService.exibirInstrutoresBd();
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

                            //Exclui o plano do banco de dados--
                            case 3:
                                if(!planoService.verificarPlanosVazio()){
                                    break;
                                }

                                planoService.exibirPlanoBd();
                                idPlano = entradaUtil.lerInt("Informe o ID do Plano que deseja excluir: ");

                                plano = planoService.buscarPlanoPorId(idPlano);
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
                                planoService.exibirPlanoBd();
                                break;

                        }

                    } while (opcaoPlano != 0);
                    break;

                //Interação com o menu Aulas--
                case 4:

                    do {
                        viewMenu.exibirMenuAulas();
                        opcaoAulas = escolherOpcao(entradaUtil);

                        switch (opcaoAulas){

                            //Cadastro de Aula--
                            case 1:
                                //Recebe os parâmetros do objeto Aula--
                                int idAula = entradaUtil.lerInt("Informe o ID do Aula: ");

                                //Exibe os instrutores presentes no banco de dados para fazer o cadastro na aula--
                                instrutorService.exibirInstrutoresBd();
                                //Busca o instrutor pelo cpf--
                                String cpfInstrutor = entradaUtil.lerCpf("Informe o CFP do instrutor que vai ministrar a aula: ");
                                Instrutor instrutorEscolhido = instrutorService.buscarProfessorCpf(cpfInstrutor);

                                String nomeAula = entradaUtil.lerTexto("Informe o nome do Aula: ");

                                LocalTime horarioAula = entradaUtil.lerHorario("Digite o horario do Aula: ");

                                int duracaoSegundosAula = entradaUtil.lerInt("Informe a duração em segundos da Aula: ");

                                //Criação do objeto aulaColetiva--
                                AulaColetiva aulaColetiva = new AulaColetiva(idAula, instrutorEscolhido, nomeAula, horarioAula, duracaoSegundosAula);

                                //Cadastra a aula coletiva no Banco de Dados--
                                aulaColetivaDAO.cadastrarAulaColetiva(aulaColetiva);
                                break;

                            //Exclui a Aula Coletiva do banco de dados--
                            case 3:


                            //Exibe as aulas coletivas cadastradas no Banco de Dados--
                            case 4:
                                aulaColetivaService.exibirAulasColetivasBD();
                                break;

                        }

                    } while (opcaoAulas != 0);
                    break;

                //Interação com o menu Inscricoes--
                case 5:

                    do {
                        viewMenu.exibirMenuInscricoes();
                        opcaoInscricoes = escolherOpcao(entradaUtil);

                        switch (opcaoInscricoes){

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