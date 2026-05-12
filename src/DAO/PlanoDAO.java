package DAO;

import Model.Instrutor;
import Model.Plano;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    //Busca o Plano pelo id--
    public Plano buscarPlanoId(int planoId){

        String sql = "SELECT * FROM plano WHERE idplano = ?";

        try {
            PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql);
            stmt.setInt(1, planoId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return new Plano(
                        rs.getInt("idplano"),
                        rs.getString("nomeplano"),
                        rs.getString("descricaoplano"),
                        rs.getBigDecimal("valormensalplano"),
                        rs.getInt("duracaomeses"),
                        rs.getString("beneficiosplano")
                );
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar Plano: " + e.getMessage());
        }
        return null;
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

}
