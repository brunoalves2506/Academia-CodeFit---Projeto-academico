package View;

public class ViewMenu {

    //Interface de interação do Menu Principal--
    public void exibirMenuPrincipal(){
        System.out.println("==========ACADEMIA CODE-FIT==========");
        System.out.println("1. MENU DE ALUNO");
        System.out.println("2. MENU DE INSTRUTORES");
        System.out.println("3. MENU DE PLANOS");
        System.out.println("4. MENU DE AULAS");
        System.out.println("5. MENU DE INSCRIÇÕES EM AULAS");
        System.out.println("6. FREQUÊNCIA DE ALUNOS");
        System.out.println("7. RELATÓRIOS");
        System.out.println("0. ENCERRAR PROGRAMA");
        System.out.println("=====================================");
    }

    //Interface de interação do Menu de Aluno--
    public void exibirMenuAluno(){
        System.out.println("============MENU DO ALUNO============");
        System.out.println("1. CADASTRAR ALUNO");
        System.out.println("2. ATUALIZAR ALUNO");
        System.out.println("3. EXCLUIR ALUNO");
        System.out.println("4. VISUALIZAR ALUNOS");
        System.out.println("0. VOLTAR AO MENU PRINCIPAL");
        System.out.println("=====================================");
    }

    //Interface de interação do Menu de Instrutor--
    public void exibirMenuInstrutor(){
        System.out.println("==========MENU DO INSTRUTOR==========");
        System.out.println("1. CADASTRAR INSTRUTOR");
        System.out.println("2. ATUALIZAR INSTRUTOR");
        System.out.println("3. EXCLUIR INSTRUTOR");
        System.out.println("4. VISUALIZAR INSTRUTORES");
        System.out.println("0. VOLTAR AO MENU PRINCIPAL");
        System.out.println("=====================================");
    }

    //Interface de interação do Menu de Planos--
    public void exibirMenuPlanos(){
        System.out.println("============MENU DE PLANOS============");
        System.out.println("1. CADASTRAR PLANO");
        System.out.println("2. ATUALIZAR PLANO");
        System.out.println("3. EXCLUIR PLANO");
        System.out.println("4. EXIBIR PLANOS");
        System.out.println("0. VOLTAR AO MENU PRINCIPAL");
        System.out.println("======================================");
    }

    //Interface de interação do Menu de Aulas--
    public void exibirMenuAulas(){
        System.out.println("============MENU DE AULAS============");
        System.out.println("1. CADASTRAR AULA");
        System.out.println("2. VISUALIZAR AULA");
        System.out.println("3. EXCLUIR AULA");
        System.out.println("0. VOLTAR AO MENU PRINCIPAL");
        System.out.println("======================================");
    }

    //Interface de interação do Menu de Plano--
    public void exibirMenuInscricoes(){
        System.out.println("==========MENU DE INSCRIÇÕES==========");
        System.out.println("1. INSCREVER ALUNO EM AULA");
        System.out.println("2. CANCELAR INSCRIÇÃO DE ALUNO EM AULA");
        System.out.println("0. VOLTAR AO MENU PRINCIPAL");
        System.out.println("======================================");
    }

    //Exibição de Estatísticas de frequência--
    public void exibirFrequenciaAlunos(){
        System.out.println("=========FREQUENCIA DE ALUNOS=========");
        System.out.println("======================================");
    }

    //Interface de interação do Menu de Relatórios--
    public void exibirMenuRelatorios(){
        System.out.println("========RELATÓRIOS DO SISTEMA========");
        System.out.println("=====================================");
    }

}
