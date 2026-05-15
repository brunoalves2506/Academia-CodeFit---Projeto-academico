package Service;

import DAO.ConexaoBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InscricaoService {

    //Verifica se a tabela inscricao do Banco de Dados está vazia--
    public boolean verificarInscricaoVazia(){
        String sql = "SELECT * FROM inscricao";

        try(Statement stmt = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (!rs.next()){
                    System.out.println("=======================================================INSCRIÇÕES====================================================================\n");
                System.out.println("Nenhuma inscrição cadastrada.");
                System.out.println("=================================================================================================================================\n");
                return false;
            }

            return true;

        } catch (SQLException e){
            System.out.println("Erro ao buscar inscrições: " + e.getMessage());
            return false;
        }
    }

}
