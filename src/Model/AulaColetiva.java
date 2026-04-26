package Model;

//import para definição de hora--
import java.sql.Time;

//Classe AulaColetiva com correlação a subclasse Instrutor--
public class AulaColetiva {
    private int idAula;
    private Instrutor instrutor;
    private String nomeAula;
    private int capacidadeMax;
    private Time horarioAula;
    private int duracaoAula;

    //Construtor--
    public AulaColetiva(int idAula, Instrutor instrutor, String nomeAula, Time horarioAula, int duracaoAula){
        setIdAula(idAula);
        setInstrutor(instrutor);
        setNomeAula(nomeAula);
        setCapacidadeMax(10);
        setHorarioAula(horarioAula);
        setDuracaoAula(duracaoAula);
    }

    //Getters e setters com validação--
    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        if(idAula > 0){
            this.idAula = idAula;
        }else{
            System.out.println("O id deve ser maior que zero.");
        }
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        if(instrutor != null){
            this.instrutor = instrutor;
        }else {
            System.out.println("Informe um instrutor válido.");
        }
    }

    public String getNomeAula() {
        return nomeAula;
    }

    public void setNomeAula(String nomeAula) {
        if(!nomeAula.isEmpty() && nomeAula != null){
            this.nomeAula = nomeAula;
        }else{
            System.out.println("O nome da aula não pode ficar em branco.");
        }
    }

    public int getCapacidadeMax() {
        return capacidadeMax;
    }

    public void setCapacidadeMax(int capacidadeMax) {
        if(capacidadeMax > 0){
            this.capacidadeMax = capacidadeMax;
        }else{
            System.out.println("A capacidade maxima deve ser maior que zero.");
        }
    }

    public Time getHorarioAula() {
        return horarioAula;
    }

    public void setHorarioAula(Time horarioAula) {
        if(horarioAula != null){
            this.horarioAula = horarioAula;
        }else{
            System.out.println("Hora iválida.");
        }
    }

    public int getDuracaoAula() {
        return duracaoAula;
    }

    public void setDuracaoAula(int duracaoAula) {
        if(duracaoAula > 0){
            this.duracaoAula = duracaoAula;
        }else{
            System.out.println("A duração em minutos da aula deve ser maior que zero.");
        }
    }

}
