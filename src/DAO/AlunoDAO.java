package DAO;

import Model.Aluno;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoDAO {

    public void cadastrarAluno(Aluno aluno){

        String sql = "INSERT INTO aluno VALUES (?,?,?,?,?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = ConexaoBD.getConexao().prepareStatement(sql);

            stmt.setString(1, aluno.getCpf());
            stmt.setString(2, aluno.getNome());
            stmt.setDate(3, Date.valueOf(aluno.getDataNascimento()));
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getEmail());
            stmt.setInt(6, aluno.getPlano().getIdPlano());

            stmt.executeUpdate();
            System.out.println("Aluno cadastrado com sucesso.");
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Exclui o Aluno do banco de dados--
    public void excluirAluno(String cpfaluno){

        String sql = "DELETE FROM aluno WHERE cpfaluno = ?";

        try ( PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {

            stmt.setString(1, cpfaluno);
            stmt.executeUpdate();
            System.out.println("Aluno excluido com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao Excluir Aluno: " + e.getMessage());
        }

    }

}
