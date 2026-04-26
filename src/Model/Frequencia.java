package Model;

//Imports para definir hora e data--
import java.sql.Time;
import java.time.LocalDate;

//Classe Frequencia com correlação a subclasse Aluno--
public class Frequencia {
    private int idFrequencia;
    private Aluno aluno;
    private LocalDate diaEntrada;
    private Time horaEntrada;

    //Contrutor--
    public Frequencia(int idFrequencia, Aluno aluno, LocalDate diaEntrada, Time horaEntrada){
        setIdFrequencia(idFrequencia);
        setAluno(aluno);
        setDiaEntrada(diaEntrada);
        setHoraEntrada(horaEntrada);
    }

    //Getters e setters com validação--
    public int getIdFrequencia() {
        return idFrequencia;
    }

    public void setIdFrequencia(int idFrequencia) {
        if(idFrequencia > 0){
            this.idFrequencia = idFrequencia;
        }else{
            System.out.println("O valor deve ser maior que zero.");
        }
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        if(aluno != null){
            this.aluno = aluno;
        } else {
            System.out.println("Aluno inválido");
        }
    }

    public LocalDate getDiaEntrada() {
        return diaEntrada;
    }

    public void setDiaEntrada(LocalDate diaEntrada) {
        if(diaEntrada != null){
            this.diaEntrada = diaEntrada;
        }else{
            System.out.println("Data invalida.");
        }
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        if(horaEntrada != null){
            this.horaEntrada = horaEntrada;
        }else{
            System.out.println("Hora invalida.");
        }
    }

}
