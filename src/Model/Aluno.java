//Classe filha Aluno herda os atributos da classe pai Pessoa--

package Model;

//Import para definir Data--
import DAO.ConexaoBD;
import Util.Ajuda;
import Util.Catraca;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;


//Classe Aluno--
public class Aluno extends Pessoa implements Catraca, Ajuda {
    private Plano plano;

    //Construtor caso o aluno possua todos os atributos--
    //OBS: precisa ser adicionado o plano, não adicionei por enquanto.
    public Aluno(String cpf, String nome, LocalDate dataNascimento, String telefone, String email, Plano plano){
        super(cpf, nome, dataNascimento, telefone, email);
        this.plano = plano;
    }

    //Getter do plano a ser cadastrado do Aluno--
    public Plano getPlano() {
        return plano;
    }

    //Retorna True se o plano estiver ativo--
    public void verificaPlano(){

    }

    //Sobreposição do método Abstrato toString() da classe pai Pessoa--
    @Override
    public String toString() {
        return "========================================================================================================================================================\n" +
                "Nome. " + getNome() +
                "| CPF. " + getCpf() +
                "| Data de nascimento. " + getDataNascimento() +
                "| Telefone. " + tratamentoDeSaida(getTelefone()) +
                "| Email. " + tratamentoDeSaida(getEmail()) +
                "| Plano. " + getPlano().getNomePlano() + "\n" +
                "========================================================================================================================================================";
    }

    //Método da Interface Ajuda--
    @Override
    public Instrutor solicitarInstrutor(String nomeinstrutor) {
        System.out.println("Informe o nome do Instrutor: ");
        String sql = "SELECT * FROM instrutor WHERE nomeInstrutor = ?";

        try {
            PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql);
            stmt.setString(1, nomeinstrutor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return new Instrutor(
                        rs.getString("nomeinstrutor"),
                        rs.getString("cpfinstrutor"),
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

            System.out.println("Professor " + getNome() + " solicitado.");

        } catch (SQLException e) {
            System.out.println("Erro ao buscar Instrutor: " + e.getMessage());
        }
        return null;
    }

    //Métodos da Interface Catraca--
    @Override
    public void passarCatracaEntrada(LocalTime horarioChegada) {
        System.out.println("Horario de entrada do aluno " + this.getNome() + ": " + horarioChegada);
    }

    @Override
    public void passarCatracaSaida(LocalTime horarioSaida) {
        System.out.println("Horario de saida do aluno " + this.getNome() + ": " + horarioSaida);
    }

}
