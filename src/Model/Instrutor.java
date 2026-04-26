//Subclasse Instrutor herda os atributos da Superclasse Pessoa--

package Model;

//Imports para definir data e horário--
import java.time.LocalDate;
import java.time.LocalTime;

//Classe Instrutor--
public class Instrutor extends Pessoa{
    private String especialidade;
    private LocalTime horarioAtivo;
    private double salario;

    //Construtor--
    public Instrutor(String nome, String cpf, LocalDate dataNascimento, String telefone, String email, String especialidade, LocalTime horarioAtivo, double salario){
       super(nome, cpf, dataNascimento, telefone, email);
        setEspecialidade(especialidade);
        setHorarioAtivo(horarioAtivo);
        setSalario(salario);
    }

    //Getters e setters com validação--
    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        if(especialidade == null || especialidade.trim().isEmpty()){
            throw new IllegalArgumentException("Especialidade inválida");
        }
        this.especialidade = especialidade.trim();
    }

    public LocalTime getHorarioAtivo() {
        return horarioAtivo;
    }

    public void setHorarioAtivo(LocalTime horarioAtivo) {
         if (horarioAtivo == null){
             throw new IllegalArgumentException("Horário inválido");
             }
        this.horarioAtivo = horarioAtivo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if(salario < 2900) {
            throw new IllegalArgumentException("Salário abaixo do piso");
        }

        this.salario = salario;
    }

    //Sobreposição do método Abstrato toString() da classe pai Pessoa--
    @Override
    public String toString() {
        return "=====================================================================================================================================\n" +
                "Nome. " + getNome() +
                " | CPF. " + getCpf() +
                " | Especialidade. " + getEspecialidade() +
                " | Horário ativo. " + getHorarioAtivo() +
                " | Data de nascimento. " + getDataNascimento() +
                " | Telefone. " + tratamentoDeSaida(getTelefone()) +
                " | Email. " + tratamentoDeSaida(getEmail()) +
                " | Salário. R$" + getSalario() + "\n" +
                "=====================================================================================================================================";
    }

    //Conceder aumento de salário ao instrutor--
        public void aumentoSalario(double aumento){
            if (aumento <= 0){
                throw new IllegalArgumentException("Aumento inválido");
            }
            this.salario += aumento;
        }

    }


