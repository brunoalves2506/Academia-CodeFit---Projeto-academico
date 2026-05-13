package Service;

//Imports uteis para o pacote--
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Import do pacote Plano--
import DAO.ConexaoBD;
import Model.Plano;

public class PlanoService {

    //Verifica se a tabela plano do Banco de Dados está vazia--
    public boolean verificarPlanosVazio(){
        String sql = "SELECT * FROM plano";

        try(Statement stmt = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (!rs.next()){
                System.out.println("=======================================================PLANOS====================================================================\n");
                System.out.println("Nenhum plano cadastrado.");
                System.out.println("=================================================================================================================================\n");
                return false;
            }

            return true;

        } catch (SQLException e){
            System.out.println("Erro ao buscar Planos: " + e.getMessage());
            return false;
        }
    }

}
