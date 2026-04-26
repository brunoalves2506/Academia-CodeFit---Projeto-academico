//Superclase Pessoa que vai ser utilizada na Subclasse Aluno e Instrutor--

package Model;

import java.time.LocalDate;

//Classe pessoa--
public abstract class Pessoa {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;

    //Construtor--
    public Pessoa(String nome, String cpf, LocalDate dataNascimento, String telefone, String email){
        setNome(nome);
        setCpf(cpf);
        setDataNascimento(dataNascimento);
        setTelefone(telefone);
        setEmail(email);
    }

    //Getters e Setters com validação--
    public String getNome() {
        return nome;
    }

    //O nome da pessoa não pode ser nulo e não pode ficar vazio--
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("Nome inválido");
        }
        this.nome = nome.trim();
    }

    public String getCpf() {
        return cpf;
    }

    //O cpf deve conter exatamente 11 caracteres numéricos--
    //EX: 46667546874;
    public void setCpf(String cpf) {
        if(cpf == null || !cpf. matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido");
        }
            this.cpf = cpf.trim();

        }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

        public void setDataNascimento(LocalDate dataNascimento) {
            if (dataNascimento == null){
                throw new IllegalArgumentException("Data não pode estar vazia");
            }

            this.dataNascimento = dataNascimento;
        }


    public String getTelefone() {
        return telefone;
    }

    //O telefone deve conter exatamente 11 caracteres numéricos--
    //EX: 11946578164;
    public void setTelefone(String telefone) {
        if(telefone == null || !telefone.matches("\\d{11}")){
            throw new IllegalArgumentException("Telefone invalido");
        }
            this.telefone = telefone.trim();

    }

    public String getEmail() {
        return email;
    }

    //O endereço de email não deve ficar nulo e não deve ficar em branco--
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty() ||
                !email.trim().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)+$")) {

            throw new IllegalArgumentException("Email inválido");
        }

        this.email = email.trim();
    }

    //Editar telefone com validação--
    public void editarTelefone(String novoTelefone){
        if(novoTelefone != null && novoTelefone.matches("\\d{11}")){
            this.telefone = novoTelefone.trim();
        }else{
            System.out.println("O telefone deve conter exatamente 11 dígitos (DDD + número).");
        }
    }

    //Editar Email com validação--
    public void editarEmail(String novoEmail){
        if(novoEmail != null && !novoEmail.isEmpty()){
            this.email = novoEmail.trim();
        }else {
            System.out.println("O email não pode ficar em branco!!!");
        }
    }
    // Tratamento para saida de dado celular e email caso não estejam preenchidos evita a saida NullPointerException, obs ? é um operador ternario, tendo a mesma função do if
    protected String tratamentoDeSaida(String tratamento){
        return (tratamento != null && !tratamento.trim().isEmpty()) ? tratamento : "Não Cadastrado";
    }

    //Metodo Abstrato--
    //Saida de dados para exibição de informações da Pessoa usando a formatação ToString--
    @Override
    public abstract String toString();

}
