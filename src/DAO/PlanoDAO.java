package DAO;

import Model.Instrutor;
import Model.Plano;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlanoDAO {

    public void cadastrarPlano(Plano plano) {

        String sql = "INSERT INTO plano VALUES (?,?,?,?,?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = ConexaoBD.getConexao().prepareStatement(sql);
            stmt.setInt(1, plano.getIdPlano());
            stmt.setString(2, plano.getNomePlano());
            stmt.setString(3, plano.getDescricaoPlano());
            stmt.setBigDecimal(4, plano.getValorMensal());
            stmt.setInt(5, plano.getDuracaoMeses());
            stmt.setString(6, plano.getBeneficios());

            stmt.executeUpdate();
            System.out.println("Plano cadastrado com sucesso.");
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro de Conexão: " + e.getMessage());
        }

    }

    //Edita o Plano escolhido--
    public void editarPlano(String nomePlano, String descricaoPlano, BigDecimal valorMensalPlano, int duracaoMeses, String beneficiosPlano, Plano plano){

        String sql = "UPDATE plano SET nomeplano = ?, descricaoplano = ?, valormensalplano = ?, duracaomeses = ?, beneficiosplano = ? WHERE idplano = ?";

        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {

            stmt.setString(1, nomePlano);
            stmt.setString(2, descricaoPlano);
            stmt.setBigDecimal(3, valorMensalPlano);
            stmt.setInt(4, duracaoMeses);
            stmt.setString(5, beneficiosPlano);
            stmt.setInt(6, plano.getIdPlano());

            stmt.executeUpdate();
            System.out.println("Plano editado com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao editar Plano: " + e.getMessage());        }
    }

    //Exclui o Plano do banco de dados--
    public void excluirPlano(int idPlano){

        String sql = "DELETE FROM plano WHERE idplano = ?";

        try ( PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {

            stmt.setInt(1, idPlano);
            stmt.executeUpdate();
            System.out.println("Plano excluido com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao Excluir Plano: " + e.getMessage());
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
