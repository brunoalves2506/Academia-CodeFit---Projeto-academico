package Service;

//Imports uteis para o pacote--
import java.util.ArrayList;

//Import dos Pacotes--
import Model.*;

public class AlunoService {

    //Função exibirAluno() que retorna os Alunos armazenados na arraylist pessoas--
    public void exibirAluno(ArrayList<Pessoa> pessoas){

        boolean encontrado = false;

        System.out.println("=======================================================ALUNOS====================================================================\n");

        for (Pessoa p : pessoas){

            if(p instanceof Aluno){

                System.out.println(p);

                encontrado = true;
            }
        }

        if(!encontrado) {
            System.out.println("=======================================================ALUNOS====================================================================\n");
            System.out.println("Nenhum aluno cadastrado.");
            System.out.println("================================================================================================================================\n");
        }
    }

}
