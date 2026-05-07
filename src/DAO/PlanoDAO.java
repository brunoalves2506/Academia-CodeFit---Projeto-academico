package DAO;

import Model.Plano;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlanoDAO {

    public void cadastrarPlano(Plano plano) {

        String sql = "INSERT INTO plano VALUES (?, ?, ?, ?, ?, ?)";
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
            stmt.close();
            System.out.println("Plano cadastrado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}