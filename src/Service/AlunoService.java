package Service;

//Imports uteis para o pacote--
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Import dos Pacotes--
import DAO.ConexaoBD;
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

}
