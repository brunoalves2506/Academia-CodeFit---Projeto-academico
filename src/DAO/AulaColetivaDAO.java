package DAO;

import Model.AulaColetiva;
import Model.Instrutor;
import Model.Plano;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

public class AulaColetivaDAO {

    //Cadastra uma Aula coletiva no Banco de Dados--
    public void cadastrarAulaColetiva(AulaColetiva aulaColetiva) {

        String sql = "INSERT INTO aulacoletiva VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = null;

        try {
            stmt = ConexaoBD.getConexao().prepareStatement(sql);
            stmt.setInt(1, aulaColetiva.getIdAula());
            stmt.setString(2, aulaColetiva.getInstrutor().getCpf());
            stmt.setString(3, aulaColetiva.getNomeAula());
            stmt.setInt(4, aulaColetiva.getCapacidadeMax());
            stmt.setTime(5, Time.valueOf(aulaColetiva.getHorarioAula()));
            stmt.setInt(6, aulaColetiva.getDuracaoAula());

            stmt.executeUpdate();
            System.out.println("Aula cadastrada com sucesso.");
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro de Conexão: " + e.getMessage());
        }

    }

    //Atualiza as informações gerais da Aula Coletiva no banco de dados--
    public void editarAulaColetiva(String nomeAula, Instrutor instrutor, int capacidadeMax, LocalTime horarioAula, int duracaoAula, AulaColetiva aulaColetiva) {

        String sql = "UPDATE aulacoletiva SET nomeaula = ?, cpfinstrutor = ?, capacidademax = ?, horarioaula = ?, duracaoaula = ? WHERE idaula = ?";

        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {

            stmt.setString(1, nomeAula);
            stmt.setString(2, instrutor.getCpf());
            stmt.setInt(3, capacidadeMax);
            stmt.setTime(4, Time.valueOf(horarioAula));
            stmt.setInt(5, duracaoAula);
            stmt.setInt(6, aulaColetiva.getIdAula());

            stmt.executeUpdate();
            System.out.println("Aula Coletiva editada com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao editar as informações da Aula Coletiva: " + e.getMessage());
        }

    }

    //Exclui a Aula Coletiva do banco de dados--
    public void excluirAulaColetiva(int idAula) {

        String sql = "DELETE FROM aulacoletiva WHERE idAula = ?";

        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {

            stmt.setInt(1, idAula);
            stmt.executeUpdate();
            System.out.println("Aula Coletiva excluida com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir Aula Coletiva: " + e.getMessage());
        }

    }

    //Exibe a aula coletiva escolhida--
    public void exibirAulasColetivasBD() {

        String sql = "SELECT * FROM aulacoletiva";

        try (
                PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql);  // ← tipo corrigido
                ResultSet rs = stmt.executeQuery()
        ) {

            if (!rs.next()) {
                System.out.println("==================================================AULAS COLETIVAS================================================================\n");
                System.out.println("Nenhuma aula coletiva cadastrada.");
                System.out.println("=================================================================================================================================\n");
                return;
            }

            InstrutorDAO instrutorDAO = new InstrutorDAO();

            do {
                String cpf = rs.getString("cpfinstrutor");
                Instrutor instrutor = instrutorDAO.buscarProfessorCpf(cpf);

                AulaColetiva aulaColetiva = new AulaColetiva(
                        rs.getInt("idaula"),
                        instrutor,
                        rs.getString("nomeaula"),
                        rs.getTime("horarioaula").toLocalTime(),
                        rs.getInt("duracaoaula")
                );

                System.out.println(aulaColetiva);

            } while (rs.next());

        } catch (SQLException e) {
            System.out.println("Erro ao buscar aulas coletivas: " + e.getMessage());
        }
    }

    //Busca a Aula Coletiva pelo id--
    public AulaColetiva buscarAulaColetivaPorId(int idAula) {

        String sql = "SELECT * FROM aulacoletiva WHERE idaula = ?";

        try (
                PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)
        ) {
            stmt.setInt(1, idAula);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Instrutor instrutor = new InstrutorDAO().buscarProfessorCpf(rs.getString("cpfinstrutor"));
                    return new AulaColetiva(
                            rs.getInt("idaula"),
                            instrutor,
                            rs.getString("nomeaula"),
                            rs.getTime("horarioaula").toLocalTime(),
                            rs.getInt("duracaoaula")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar Aula Coletiva: " + e.getMessage());
        }
        return null;

    }

}
