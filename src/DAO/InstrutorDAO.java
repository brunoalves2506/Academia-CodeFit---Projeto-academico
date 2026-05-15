package DAO;

import Model.Instrutor;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

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

    //Atualiza as informações gerais do Instrutor selecionado--
    public void editarInstrutor(String nomeInstrutor, LocalDate dataNascimentoInstrutor, String telefoneInstrutor, String emailInstrutor, String especialidade, LocalTime horarioAtivo, BigDecimal salario, Instrutor instrutor) {

        String sql = "UPDATE instrutor SET nomeInstrutor = ?, dataNascimentoInstrutor = ?, telefoneInstrutor = ?, emailInstrutor = ?, especialidade = ?, horarioAtivo = ?, salario = ? WHERE cpfInstrutor = ?";

        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {

            stmt.setString(1, nomeInstrutor);
            stmt.setObject(2, dataNascimentoInstrutor);
            stmt.setString(3, telefoneInstrutor);
            stmt.setString(4, emailInstrutor);
            stmt.setString(5, especialidade);
            stmt.setObject(6, horarioAtivo);
            stmt.setBigDecimal(7, salario);
            stmt.setString(8, instrutor.getCpf());

            stmt.executeUpdate();
            System.out.println("Instrutor editado com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao editar as informações do Instrutor: " + e.getMessage());
        }
    }

    //Exclui o instrutor do banco de dados--
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

    //Exibe os Instrutores cadastrados no Banco de Dados--
    public void exibirInstrutoresBd(){
        String sql = "SELECT * FROM instrutor";

        try(Statement stmt = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (!rs.next()){
                System.out.println("=======================================================INSTRUTORES====================================================================\n");
                System.out.println("Nenhum instrutor cadastrado.");
                System.out.println("======================================================================================================================================\n");
                return;
            }

            System.out.println("===========================================================================INSTRUTORES==================================================================================================================================\n");

            do {
                Instrutor instrutor = new Instrutor(
                        rs.getString("cpfinstrutor"),
                        rs.getString("nomeinstrutor"),
                        rs.getDate("datanascimentoinstrutor").toLocalDate(),
                        rs.getString("telefoneinstrutor"),
                        rs.getString("emailinstrutor"),
                        rs.getString("especialidade"),
                        rs.getTime("horarioativo").toLocalTime(),
                        rs.getBigDecimal("salario")
                );

                System.out.println(instrutor);

            } while (rs.next());

        } catch (SQLException e){
            System.out.println("Erro ao buscar Instrutor: " + e.getMessage());
        }

    }

    //Busca o Instrutor pelo cpf--
    public Instrutor buscarProfessorCpf(String cpfInstrutor){

        String sql = "SELECT * FROM instrutor WHERE cpfinstrutor = ?";

        try {
            PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql);
            stmt.setString(1, cpfInstrutor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return new Instrutor(
                        rs.getString("cpfinstrutor"),
                        rs.getString("nomeinstrutor"),
                        rs.getDate("datanascimentoinstrutor").toLocalDate(),
                        rs.getString("telefoneinstrutor"),
                        rs.getString("emailinstrutor"),
                        rs.getString("especialidade"),
                        rs.getTime("horarioativo").toLocalTime(),
                        rs.getBigDecimal("salario")
                );
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar Instrutor: " + e.getMessage());
        }
        return null;
    }

    //Busca o Instrutor pelo Nome--
    public Instrutor buscarProfessorNome(String nomeinstrutor){

        String sql = "SELECT * FROM instrutor WHERE nomeInstrutor = ?";

        try {
            PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql);
            stmt.setString(1, nomeinstrutor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return new Instrutor(
                        rs.getString("cpfinstrutor"),
                        rs.getString("nomeinstrutor"),
                        rs.getDate("datanascimentoinstrutor").toLocalDate(),
                        rs.getString("telefoneinstrutor"),
                        rs.getString("emailinstrutor"),
                        rs.getString("especialidade"),
                        rs.getTime("horarioativo").toLocalTime(),
                        rs.getBigDecimal("salario")
                );
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar Instrutor: " + e.getMessage());
        }
        return null;
    }

}
