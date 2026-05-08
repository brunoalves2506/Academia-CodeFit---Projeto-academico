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

    //Exibe os planos cadastrados no Banco de Dados--
    public void exibirPlanoBd(){
        String sql = "SELECT * FROM plano";

        try(Statement stmt = ConexaoBD.getConexao().createStatement();
        ResultSet rs = stmt.executeQuery(sql)){

            if (!rs.next()){
                System.out.println("=======================================================PLANOS====================================================================\n");
                System.out.println("Nenhum plano cadastrado.");
                System.out.println("=================================================================================================================================\n");
                return;
            }

            do {
                Plano plano = new Plano(
                        rs.getInt("idplano"),
                        rs.getString("nomeplano"),
                        rs.getString("descricaoplano"),
                        rs.getBigDecimal("valormensalplano"),
                        rs.getInt("duracaomeses"),
                        rs.getString("beneficiosplano")
                );

                System.out.println(plano);

            } while (rs.next());

        } catch (SQLException e){
            System.out.println("Erro ao buscar Planos: " + e.getMessage());
        }

    }

    // Função buscarPlanoPorId() que retorna o plano pelo id
    public Plano buscarPlanoPorId(int idplano) {
        String sql = "SELECT * FROM plano WHERE idplano = ?";

        try (
                PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)
        ) {
            stmt.setInt(1, idplano);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Plano(
                            rs.getInt("idplano"),
                            rs.getString("nomeplano"),
                            rs.getString("descricaoplano"),
                            rs.getBigDecimal("valormensalplano"),
                            rs.getInt("duracaomeses"),
                            rs.getString("beneficiosplano")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar plano: " + e.getMessage());
        }
        return null;
    }

}
