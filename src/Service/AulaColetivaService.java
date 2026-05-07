package Service;

import Model.Aluno;
import Model.AulaColetiva;
import Model.Pessoa;
import Model.Plano;

import java.util.ArrayList;

public class AulaColetivaService {

    //Função exibirAulaColetiva() que retorna as aulas cadastradas no arraylist AulasColetivas--
    public void exibirAulaColetiva(ArrayList<AulaColetiva> aulasColetivas){

        if(aulasColetivas.isEmpty()){
            System.out.println("Nenhuma aula cadastrada.");
        }
        for (AulaColetiva aulaColetiva : aulasColetivas){
            System.out.println(aulaColetiva);
        }

    }

}
