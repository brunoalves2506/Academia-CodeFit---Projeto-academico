package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    //Classe responsável pela conexão da aplicação Java com o banco de dados PostgreSQL através da API JDBC--

    private static final String url = "jdbc:postgresql://localhost:5432/CodeFit";
    private static final String user = "postgres";
    private static final String password = "root";

    private static Connection conexao;

    public static Connection getConexao(){

        try{
            if(conexao == null){
                conexao = DriverManager.getConnection(url, user, password);
                return conexao;
            }else{
                return conexao;
            }
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }

}
