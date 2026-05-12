package Service;

//Imports uteis para o pacote--
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Import dos Pacotes--
import DAO.ConexaoBD;
import DAO.PlanoDAO;
import Model.*;

public class AlunoService {

    //Verifica se a tabela aluno do Banco de Dados está vazia--
    public boolean verificarAlunosVazio(){
        String sql = "SELECT * FROM aluno";

        try(Statement stmt = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (!rs.next()){
                System.out.println("=======================================================ALUNOS====================================================================\n");
                System.out.println("Nenhum aluno cadastrado.");
                System.out.println("=================================================================================================================================\n");
                return false;
            }

            return true;

        } catch (SQLException e){
            System.out.println("Erro ao buscar Alunos: " + e.getMessage());
            return false;
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

            System.out.println("===================================================================ALUNOS===============================================================================\n");

            do {
                Plano plano = new PlanoService().buscarPlanoPorId(rs.getInt("idplano"));
                Aluno aluno = new Aluno(
                        rs.getString("cpfaluno"),
                        rs.getString("nomealuno"),
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

    //Busca o Aluno pelo cpf--
    public Aluno buscarAlunoCpf(String cpfAluno){

        String sql = "SELECT * FROM aluno WHERE cpfaluno = ?";

        try {
            PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql);
            stmt.setString(1, cpfAluno);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                Plano plano = new PlanoDAO().buscarPlanoId(rs.getInt("idplano"));

                return new Aluno(
                        rs.getString("cpfaluno"),
                        rs.getString("nomealuno"),
                        rs.getDate("datanascimentoaluno").toLocalDate(),
                        rs.getString("telefonealuno"),
                        rs.getString("emailaluno"),
                        plano
                );
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar Aluno: " + e.getMessage());
        }
        return null;
    }

}
