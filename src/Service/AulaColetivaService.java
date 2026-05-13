package Service;

import DAO.ConexaoBD;
import DAO.InstrutorDAO;
import Model.AulaColetiva;
import Model.Instrutor;

import java.sql.PreparedStatement;  // ← import atualizado
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AulaColetivaService {

    //Verifica se a tabela aulacoletiva do Banco de Dados está vazia--
    public boolean verificarAulaColetivaVazia(){
        String sql = "SELECT * FROM aulacoletiva";

        try(Statement stmt = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (!rs.next()){
                System.out.println("=======================================================AULAS COLETIVAS====================================================================\n");
                System.out.println("Nenhuma aula cadastrada.");
                System.out.println("=================================================================================================================================\n");
                return false;
            }

            return true;

        } catch (SQLException e){
            System.out.println("Erro ao buscar Aulas: " + e.getMessage());
            return false;
        }
    }

}