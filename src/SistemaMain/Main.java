//Execução do código Main--

package SistemaMain;

//Imports importantes para o sistema--
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

public class Main{

    //Função escolherOpcao() com validação que retorna um valor inteiro--
    private static int escolherOpcao(EntradaUtil entradaUtil){
        return entradaUtil.lerInt("Informe a ação a ser tomada: ");
    }

    //Arraylist que armazena as Pessoas do sistema--
    private static ArrayList <Pessoa> pessoas = new ArrayList<>();

    //Arraylist que armazena os Planos do sistema--
    private static ArrayList <Plano> planos = new ArrayList<>();

    //Arraylist que armazena as Inscrições do sistema--
    private static ArrayList <Inscricao> inscricoes = new ArrayList<>();

    //Arraylist que armazena as Aulas Coletivas do sistema--
    private static ArrayList <AulaColetiva> aulasColetivas = new ArrayList<>();

    //Criação do scanner--
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Requisição dos pacotes para utilização no Main--
        AlunoService a = new AlunoService();
        InstrutorService i = new InstrutorService();
        PlanoService p = new PlanoService();
        EntradaUtil entradaUtil = new EntradaUtil(scanner);
        ViewMenu viewMenu = new ViewMenu();

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

                        switch (opcaoAluno){

                            //Cadastro de Aluno--
                            case 1:

                                //Verifica se existe um plano para atribuir ao aluno--
                                if (planos.isEmpty()){
                                    System.out.println("Nenhum plano cadastrado! Cadastre um plano primeiro.");
                                    break;
                                }

                                //Recebe os parâmetros do objeto Aluno--
                                String nomeAluno = entradaUtil.lerTexto("Informe o nome do Aluno:");

                                String cpfAluno = entradaUtil.lerCpf("Informe o CPF do Aluno: ");

                                LocalDate dataNascimentoAluno = entradaUtil.lerData("Digite a data de nascimento (AAAA-MM-DD):");

                                String telefoneAluno = entradaUtil.lerTelefone("Informe o telefone do Aluno: ");

                                String emailAluno = entradaUtil.lerEmail("Informe o email do Aluno: ");

                                //Exibe os planos para que um seja atribuído ao aluno--
                                p.exibirPlanos(planos);

                                int planoId = entradaUtil.lerInt("Informe o ID do Plano: ");

                                Plano planoEscolhido = p.buscarPlanoPorId(planos, planoId);

                                if (planoEscolhido == null){
                                    System.out.println("Plano não encontrado! Encerrando cadastro.");
                                    break;
                                }

                                //Cria o objeto Aluno--
                                Aluno aluno = new Aluno(nomeAluno, cpfAluno, dataNascimentoAluno, telefoneAluno, emailAluno, planoEscolhido);

                                //Adiciona o objeto na arraylist Pessoa--
                                pessoas.add(aluno);
                                System.out.println("Aluno cadastrado com sucesso!");
                                break;

                            //Exibição de Alunos--
                            case 4:

                                a.exibirAluno(pessoas);
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
                                String nomeInstrutor = entradaUtil.lerTexto("Informe o nome do instrutor");

                                String cpfInstrutor = entradaUtil.lerCpf("Informe o cpf do intrutor: ");

                                LocalDate dataNascimentoInstrutor = entradaUtil.lerData("Digite a data de nascimento (AAAA-MM-DD):");

                                String telefoneInstrutor = entradaUtil.lerTelefone("Informe o telefone do instrutor: ");

                                String emailInstrutor = entradaUtil.lerEmail("Informe o Email do instrutor: ");

                                String especialidade = entradaUtil.lerTexto("Informe a especialidade do instrutor: ");

                                LocalTime horarioAtivo = entradaUtil.lerHorario("Digite o horário ativo (HH:MM):");

                                double salario = entradaUtil.lerDouble("Informe o salário do Instrutor: ");

                                //Cria o objeto Instrutor--
                                Instrutor instrutor = new Instrutor(nomeInstrutor, cpfInstrutor, dataNascimentoInstrutor, telefoneInstrutor, emailInstrutor, especialidade, horarioAtivo, salario
                                );

                                //Adiciona o objeto na arraylist Pessoa--
                                pessoas.add(instrutor);
                                System.out.println("Instrutor cadastrado com sucesso!");
                                break;

                            //Exibição de Instrutores--
                            case 4:
                                i.exibirInstrutores(pessoas);
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

                                double valorMensal = entradaUtil.lerDouble("Informe o valor do plano: ");

                                int duracaoMeses = entradaUtil.lerInt("Informe a duração em meses: ");

                                String beneficios = entradaUtil.lerTexto("Informe os benefícios do plano: ");

                                //Criação do objeto Plano--
                                Plano plano = new Plano (idPlano, nomePlano, descricaoPlano, valorMensal, duracaoMeses, beneficios);

                                //Adição do plano na arraylist planos--
                                planos.add(plano);
                                System.out.println("Plano cadastrado com sucesso!");
                                break;

                            //Exibição dos Planos--
                            case 4:
                                p.exibirPlanos(planos);
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