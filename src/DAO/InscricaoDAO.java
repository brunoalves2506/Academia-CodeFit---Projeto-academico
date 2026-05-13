package DAO;

import Model.Aluno;
import Model.AulaColetiva;
import Model.Inscricao;

import java.sql.*;

public class InscricaoDAO {

    //Insere a Inscrição no banco de dados--
    public void cadastrarInscricao(Inscricao inscricao) {

        String sql = "INSERT INTO inscricao VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            stmt = ConexaoBD.getConexao().prepareStatement(sql);

            stmt.setInt(1, inscricao.getIdInscricao());
            stmt.setString(2, inscricao.getAluno().getCpf());
            stmt.setInt(3, inscricao.getAulaColetiva().getIdAula());
            stmt.setDate(4, Date.valueOf(inscricao.getDataInscicao()));
            stmt.setString(5, inscricao.getStatusInscricao());

            stmt.executeUpdate();
            System.out.println("Inscrição realizada com sucesso.");
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Exibe as Inscrições cadastradas no Banco de Dados--
    public void exibirInscricoesBd() {

        String sql = "SELECT * FROM inscricao";

        try (Statement stmt = ConexaoBD.getConexao().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.next()) {
                System.out.println("=======================================================INSCRIÇÕES====================================================================\n");
                System.out.println("Nenhuma inscrição cadastrada.");
                System.out.println("=================================================================================================================================\n");
                return;
            }

            System.out.println("=============================================================INSCRIÇÕES==========================================================================\n");

            do {
                Aluno aluno = new AlunoDAO().buscarAlunoCpf(rs.getString("cpfaluno"));
                AulaColetiva aulaColetiva = new AulaColetivaDAO().buscarAulaColetivaPorId(rs.getInt("idaula"));

                Inscricao inscricao = new Inscricao(
                        rs.getInt("idinscricao"),
                        aluno,
                        aulaColetiva,
                        rs.getDate("datainscricao").toLocalDate(),
                        rs.getString("statusinscricao")
                );

                System.out.println(inscricao);

            } while (rs.next());

        } catch (SQLException e) {
            System.out.println("Erro ao buscar Inscrições: " + e.getMessage());
        }

    }

}
