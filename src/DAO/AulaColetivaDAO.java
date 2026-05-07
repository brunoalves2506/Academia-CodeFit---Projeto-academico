package DAO;

import Model.AulaColetiva;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class AulaColetivaDAO {

    public void cadastrarAulaColetiva(AulaColetiva aulaColetiva){

        String sql = "INSERT INTO aulaColetiva VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try{
            stmt = ConexaoBD.getConexao().prepareStatement(sql);
            stmt.setInt(1, aulaColetiva.getIdAula());
            stmt.setString(2, aulaColetiva.getInstrutor().getCpf());
            stmt.setString(3, aulaColetiva.getNomeAula());
            stmt.setInt(4, aulaColetiva.getCapacidadeMax());
            stmt.setTime(5, Time.valueOf(aulaColetiva.getHorarioAula()));
            stmt.setInt(6, aulaColetiva.getDuracaoAula());

            stmt.executeUpdate();
            stmt.close();
            System.out.println("Aula Coletiva cadastrada com sucesso!");

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
