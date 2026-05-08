package Model;

import java.math.BigDecimal;

//Classe Plano com correlação a subclasse Aluno--
public class Plano {
    private int idPlano;
    private String nomePlano;
    private String descricaoPlano;
    private BigDecimal valorMensal;
    private int duracaoMeses;
    private String beneficios;

    //Construtor--
    public Plano(int idPlano, String nomePlano,
                 String descricaoPlano, BigDecimal valorMensal, int duracaoMeses, String beneficios){
        setIdPlano(idPlano);
        setNomePlano(nomePlano);
        setDescricaoPlano(descricaoPlano);
        setValorMensal(valorMensal);
        setDuracaoMeses(duracaoMeses);
        setBeneficios(beneficios);
    }

    //Getters e setters com validação--
    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        if(idPlano > 0){
            this.idPlano = idPlano;
        }else{
            System.out.println("O valor deve ser maior que zero.");
        }
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        if(!nomePlano.isEmpty() && nomePlano != null){
            this.nomePlano = nomePlano;
        }else{
            System.out.println("O nome do plano não pode ficar em branco.");
        }
    }

    public String getDescricaoPlano() {
        return descricaoPlano;
    }

    public void setDescricaoPlano(String descricaoPlano) {
        if(!descricaoPlano.isEmpty() && descricaoPlano != null){
            this.descricaoPlano = descricaoPlano;
        }else{
            System.out.println("A descrição do plano não pode ficar em branco.");
        }
    }

    public BigDecimal getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(BigDecimal valorMensal) {
        if(valorMensal.compareTo(BigDecimal.ZERO) > 0){
            this.valorMensal = valorMensal;
        } else {
            System.out.println("O valor do plano deve ser maior que zero.");
        }
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        if(duracaoMeses > 0){
            this.duracaoMeses = duracaoMeses;
        }else{
            System.out.println("A duração deve ser maior que zero meses.");
        }
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        if(!beneficios.isEmpty() && beneficios != null){
            this.beneficios = beneficios;
        }else{
            System.out.println("Informe os benefícios do plano.");
        }
    }

    //toString que retorna as iformações do Plano--
    @Override
    public String toString(){
        return "====================PLANO " + getNomePlano() + " ====================\n" +
                "ID. " + getIdPlano() + "\n" +
                "Descrição. " + getDescricaoPlano() + "\n" +
                "Valor Mensal. R$" + getValorMensal() + "\n" +
                "Duração (Meses). " + getDuracaoMeses() + "\n" +
                "Benefícios. " + getBeneficios() + "\n" +
                "====================================================================";
    }

}
