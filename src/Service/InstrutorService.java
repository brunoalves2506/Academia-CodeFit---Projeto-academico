package Service;

//Imports uteis para o pacote--
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Import dos Pacotes--
import DAO.ConexaoBD;
import Model.*;

public class InstrutorService {

    //Verifica se a tabela instrutor do Banco de Dados está vazia--
    public boolean verificarInstrutoresVazio(){
        String sql = "SELECT * FROM instrutor";

        try(Statement stmt = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (!rs.next()){
                System.out.println("=======================================================INSTRUTORES====================================================================\n");
                System.out.println("Nenhum instrutor cadastrado.");
                System.out.println("=================================================================================================================================\n");
                return false;
            }

            return true;

        } catch (SQLException e){
            System.out.println("Erro ao buscar Instrutores: " + e.getMessage());
            return false;
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

            System.out.println("=======================================================INSTRUTORES====================================================================\n");

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
