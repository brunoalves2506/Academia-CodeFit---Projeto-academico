package DAO;

import Model.Instrutor;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class InstrutorDAO {

    public void cadastrarInstrutor(Instrutor instrutor) {

        String sql = "INSERT INTO instrutor VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            stmt = ConexaoBD.getConexao().prepareStatement(sql);
            stmt.setString(1, instrutor.getCpf());
            stmt.setString(2, instrutor.getNome());
            stmt.setDate(3, Date.valueOf(instrutor.getDataNascimento()));
            stmt.setString(4, instrutor.getTelefone());
            stmt.setString(5, instrutor.getEmail());
            stmt.setString(6, instrutor.getEspecialidade());
            stmt.setTime(7, Time.valueOf(instrutor.getHorarioAtivo()));
            stmt.setBigDecimal(8, instrutor.getSalario());

            stmt.executeUpdate();
            System.out.println("Instrutor cadastrado com sucesso.");
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void excluirInstrutor(String cpfinstrutor){

        String sql = "DELETE FROM instrutor WHERE CPF = ?";

        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cpfinstrutor);
            stmt.executeUpdate();
            System.out.println("Instrutor excluido com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir instrutor: " + e.getMessage());
        }

    }

}
