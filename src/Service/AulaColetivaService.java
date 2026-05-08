package Service;

import DAO.ConexaoBD;
import Model.AulaColetiva;
import Model.Instrutor;

import java.sql.PreparedStatement;  // ← import atualizado
import java.sql.ResultSet;
import java.sql.SQLException;

public class AulaColetivaService {

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

            InstrutorService instrutorService = new InstrutorService();

            do {
                String cpf = rs.getString("cpfinstrutor");
                Instrutor instrutor = instrutorService.buscarProfessorCpf(cpf);

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
}