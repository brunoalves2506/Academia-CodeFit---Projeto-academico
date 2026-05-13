package Model;

//Import para definição de data--
import java.time.LocalDate;

//Classe inscricao com correlação a subclasse Aluno--
//OBS: Inscricao recebe duas entidades: Aluno e AulaColetiva--
public class Inscricao {
    private int idInscricao;
    private AulaColetiva aulaColetiva;
    private Aluno aluno;
    private LocalDate dataInscicao;
    private String statusInscricao;

    //Construtor--
    public Inscricao(int idInscricao, Aluno aluno, AulaColetiva aulaColetiva, LocalDate dataInscicao, String statusInscricao){
        setIdInscricao(idInscricao);
        setAluno(aluno);
        setAulaColetiva(aulaColetiva);
        setDataInscicao(dataInscicao);
        setStatusInscricao(statusInscricao);
    }

    //Getters e setters com validação--
    public int getIdInscricao() {
        return idInscricao;
    }

    public void setIdInscricao(int idInscricao) {
        if(idInscricao > 0){
            this.idInscricao = idInscricao;
        }else{
            System.out.println("O valor deve ser maior que zero.");
        }
    }

    public AulaColetiva getAulaColetiva() {
        return aulaColetiva;
    }

    public void setAulaColetiva(AulaColetiva aulaColetiva) {
        this.aulaColetiva = aulaColetiva;
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

    public LocalDate getDataInscicao() {
        return dataInscicao;
    }

    public void setDataInscicao(LocalDate dataInscicao) {
        if(dataInscicao != null){
            this.dataInscicao = dataInscicao;
        }else{
            System.out.println("Data invalida.");
        }
    }

    public String getStatusInscricao() {
        return statusInscricao;
    }

    public void setStatusInscricao(String statusInscricao) {
        if(!statusInscricao.isEmpty() && statusInscricao != null){
            this.statusInscricao = statusInscricao;
        }else{
            System.out.println("Informe um status válido.");
        }
    }

    @Override
    public String toString() {
        return "========================================================================================================================================================\n" +
                "ID Inscrição: " + getIdInscricao() +
                " | Aluno: " + getAluno().getNome() +
                " | CPF: " + getAluno().getCpf() +
                " | Aula: " + getAulaColetiva().getNomeAula() +
                " | Data de Inscrição: " + getDataInscicao() +
                " | Status: " + getStatusInscricao() + "\n" +
                "========================================================================================================================================================";
    }

}
