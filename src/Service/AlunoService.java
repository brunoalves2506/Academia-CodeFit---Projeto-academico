package Service;

//Imports uteis para o pacote--
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Import dos Pacotes--
import DAO.ConexaoBD;
import DAO.PlanoDAO;
import Model.*;

public class AlunoService {

    //Função exibirAluno() que retorna os Alunos armazenados na arraylist pessoas--
    public void exibirAluno(ArrayList<Pessoa> pessoas){

        boolean encontrado = false;

        for (Pessoa p : pessoas){

            if(p instanceof Aluno){

                System.out.println("=======================================================ALUNO====================================================================\n");
                System.out.println(p);

                encontrado = true;
            }
        }

        if(!encontrado) {
            System.out.println("=======================================================ALUNO====================================================================\n");
            System.out.println("Nenhum aluno cadastrado.");
            System.out.println("================================================================================================================================\n");
        }
    }

    //Exibe os Alunos cadastrados no Banco de Dados--
    public void exibirAlunosBd(){
        String sql = "SELECT * FROM aluno";

        try(Statement stmt = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (!rs.next()){
                System.out.println("=======================================================ALUNOS====================================================================\n");
                System.out.println("Nenhum aluno cadastrado.");
                System.out.println("=================================================================================================================================\n");
                return;
            }

            System.out.println("=======================================================ALUNOS====================================================================\n");

            do {
                Plano plano = new PlanoService().buscarPlanoPorId(rs.getInt("idplano"));
                Aluno aluno = new Aluno(
                        rs.getString("cpfaluno"),
                        rs.getString("nome"),
                        rs.getDate("datanascimentoaluno").toLocalDate(),
                        rs.getString("telefonealuno"),
                        rs.getString("emailaluno"),
                        plano
                );

                System.out.println(aluno);

            } while (rs.next());

        } catch (SQLException e){
            System.out.println("Erro ao buscar Aluno: " + e.getMessage());
        }

    }

}
