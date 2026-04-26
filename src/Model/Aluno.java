//Classe filha Aluno herda os atributos da classe pai Pessoa--

package Model;

//Import para definir Data--
import java.time.LocalDate;

//Classe Aluno--
public class Aluno extends Pessoa{
    private Plano plano;

    //Construtor caso o aluno possua todos os atributos--
    //OBS: precisa ser adicionado o plano, não adicionei por enquanto.
    public Aluno(String nome, String cpf, LocalDate dataNascimento, String telefone, String email, Plano plano){
        super(nome, cpf, dataNascimento, telefone, email);
        this.plano = plano;
    }

    //Construtor caso o aluno não possua telefone e email--
    //OBS: precisa ser adicionado o plano, não adicionei por enquanto.
    public Aluno(String nome, String cpf, LocalDate dataNascimento, Plano plano){
        this(nome, cpf, dataNascimento, "Não cadastrado.", "Não cadastrado.", plano);
    }

    //Getter do plano a ser cadastrado do Aluno--
    public Plano getPlano() {
        return plano;
    }

    //Sobreposição do método Abstrato toString() da classe pai Pessoa--
    @Override
    public String toString() {
        return "================================================================================================================================\n" +
                "Nome. " + getNome() +
                "| CPF. " + getCpf() +
                "| Data de nascimento. " + getDataNascimento() +
                "| Telefone. " + tratamentoDeSaida(getTelefone()) +
                "| Email. " + tratamentoDeSaida(getEmail()) +
                "| Plano. " + getPlano().getNomePlano() + "\n" +
                "================================================================================================================================";
    }

}
