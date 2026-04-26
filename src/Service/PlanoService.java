package Service;

//Imports uteis para o pacote--
import java.util.ArrayList;

//Import do pacote Plano--
import Model.Plano;

public class PlanoService {

    public void exibirPlanos(ArrayList<Plano> planos){

        if(planos.isEmpty()){
            System.out.println("Nenhum plano cadastrado.");
        }
        for (Plano p : planos){
            System.out.println(p);
        }

    }

    //Função buscarPlanoPorId() que retorna o id do plano escolhido--
    public Plano buscarPlanoPorId(ArrayList<Plano> planos, int planoId){

        for (Plano p : planos){
            if (p.getIdPlano() == planoId){
                return p;
            }
        }
        return null;
    }

}
