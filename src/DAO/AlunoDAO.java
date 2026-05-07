package DAO;

import Model.Aluno;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoDAO {

    public void cadastrarAluno(Aluno aluno){

        String sql = "INSERT INTO aluno VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            stmt = ConexaoBD.getConexao().prepareStatement(sql);
            stmt.setString(1, aluno.getCpf());
            stmt.setInt(2, aluno.getPlano().getIdPlano());
            stmt.setString(3, aluno.getNome());
            stmt.setDate(4, Date.valueOf(aluno.getDataNascimento()));
            stmt.setString(5, aluno.getTelefone());
            stmt.setString(6, aluno.getEmail());

            stmt.executeUpdate();
            stmt.close();
            System.out.println("Aluno cadastrado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}