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

public class InstrutorService {

    //Verifica se a tabela instrutor do Banco de Dados está vazia--
    public boolean verificarInstrutoresVazio(){
        String sql = "SELECT * FROM instrutor";

        try(Statement stmt = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (!rs.next()){
                System.out.println("=======================================================INSTRUTORES====================================================================\n");
                System.out.println("Nenhum instrutor cadastrado.");
                System.out.println("=================================================================================================================================\n");
                return false;
            }

            return true;

        } catch (SQLException e){
            System.out.println("Erro ao buscar Instrutores: " + e.getMessage());
            return false;
        }
    }

}
