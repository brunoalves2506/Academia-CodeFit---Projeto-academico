package Service;

//Imports uteis para o pacote--
import java.util.ArrayList;

//Import dos Pacotes--
import Model.*;

public class InstrutorService {

    //Função exibirInstrutores() que retorna os instrutores armazenados na arraylist pessoas--
    public void exibirInstrutores(ArrayList<Pessoa> pessoas){

        boolean encontrado = false;

        for (Pessoa p : pessoas){

            if(p instanceof Instrutor){

                System.out.println("=======================================================INSTRUTOR====================================================================\n");
                System.out.println(p);

                encontrado = true;

            }
        }

        if(!encontrado) {
            System.out.println("=======================================================INSTRUTOR====================================================================\n");
            System.out.println("Nenhum instrutor cadastrado.");
            System.out.println("================================================================================================================================\n");
        }
    }

}
