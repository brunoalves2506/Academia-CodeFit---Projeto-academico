package DAO;

import Model.Instrutor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class InstrutorDAO {

    public void cadastrarInstrutor(Instrutor instrutor) {

        String sql = "INSERT INTO instrutor VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            stmt = ConexaoBD.getConexao().prepareStatement(sql);
            stmt.setString(1, instrutor.getCpf());
            stmt.setString(2, instrutor.getNome());
            stmt.setString(3, instrutor.getTelefone());
            stmt.setString(4, instrutor.getEspecialidade());
            stmt.setTime(5, Time.valueOf(instrutor.getHorarioAtivo()));
            stmt.setBigDecimal(6, instrutor.getSalario());
            stmt.setString(7, instrutor.getEmail());

            stmt.executeUpdate();
            stmt.close();
            System.out.println("Instrutor cadastrado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}