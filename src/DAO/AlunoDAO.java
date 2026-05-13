package DAO;

import Model.Aluno;
import Model.Plano;
import Service.PlanoService;

import java.sql.*;
import java.time.LocalDate;

public class AlunoDAO {

    //Insere o aluno no banco de dados--
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

    //Atualiza as informações gerais do Aluno no banco de dados--
    public void editarInfoAluno(String nomeAluno, LocalDate dataNascimentoAluno, String telefoneAluno, String emailAluno, Aluno aluno){

        String sql = "UPDATE aluno SET nomeAluno = ?, dataNascimentoAluno = ?, telefoneAluno = ?, emailAluno = ? WHERE cpfAluno = ?";

        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)){

            stmt.setString(1, nomeAluno);
            stmt.setDate(2, Date.valueOf(dataNascimentoAluno));
            stmt.setString(3, telefoneAluno);
            stmt.setString(4, emailAluno);
            stmt.setString(5, aluno.getCpf());


            stmt.executeUpdate();
            System.out.println("Aluno editado com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao editar as informações do aluno: " + e.getMessage());
        }

    }

    //Atualiza o Plano do Aluno--
    public void editarPlanoAluno(Plano planoAluno, Aluno aluno){

        String sql = "UPDATE aluno SET idplano = ? WHERE cpfaluno = ?";

        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)){

            stmt.setInt(1, planoAluno.getIdPlano());
            stmt.setString(2, aluno.getCpf());

            stmt.executeUpdate();
            System.out.println("Plano atualizado com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao editar o plano do aluno: " + e.getMessage());        }
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

    //Exibe os Alunos cadastrados no Banco de Dados--
    public void exibirAlunosBd(){
        String sql = "SELECT * FROM aluno";

        try(Statement stmt = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (!rs.next()){
                System.out.println("=======================================================ALUNOS====================================================================\n");
                System.out.println("Nenhum aluno cadastrado.");
                System.out.println("=================================================================================================================================\n");
                return;
            }

            System.out.println("===================================================================ALUNOS===============================================================================\n");

            do {
                Plano plano = new PlanoDAO().buscarPlanoPorId(rs.getInt("idplano"));
                Aluno aluno = new Aluno(
                        rs.getString("cpfaluno"),
                        rs.getString("nomealuno"),
                        rs.getDate("datanascimentoaluno").toLocalDate(),
                        rs.getString("telefonealuno"),
                        rs.getString("emailaluno"),
                        plano
                );

                System.out.println(aluno);

            } while (rs.next());

        } catch (SQLException e){
            System.out.println("Erro ao buscar Aluno: " + e.getMessage());
        }

    }

    //Busca o Aluno pelo cpf--
    public Aluno buscarAlunoCpf(String cpfAluno){

        String sql = "SELECT * FROM aluno WHERE cpfaluno = ?";

        try {
            PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql);
            stmt.setString(1, cpfAluno);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                Plano plano = new PlanoDAO().buscarPlanoPorId(rs.getInt("idplano"));

                return new Aluno(
                        rs.getString("cpfaluno"),
                        rs.getString("nomealuno"),
                        rs.getDate("datanascimentoaluno").toLocalDate(),
                        rs.getString("telefonealuno"),
                        rs.getString("emailaluno"),
                        plano
                );
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar Aluno: " + e.getMessage());
        }
        return null;
    }

}
