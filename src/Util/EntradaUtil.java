package Util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class EntradaUtil {

    private Scanner scanner;

    // Construtor--
    public EntradaUtil(Scanner scanner){
        this.scanner = scanner;
    }

    // Lê um texto e garante que não fique vazio--
    public String lerTexto(String mensagem){
        String entrada;
        do {
            System.out.println(mensagem);
            entrada = scanner.nextLine().trim();
            if(entrada.isEmpty()){
                System.out.println("Campo obrigatório, não pode ficar em branco.");
            }
        } while(entrada.isEmpty());
        return entrada;
    }

    // Lê um inteiro e garante que seja válido--
    public int lerInt(String mensagem){
        while(true){
            System.out.println(mensagem);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e){
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    // Lê um double e garante que seja válido--
    public double lerDouble(String mensagem){
        while(true){
            System.out.println(mensagem);
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                return valor;
            } catch (NumberFormatException e){
                System.out.println("Valor inválido. Digite um número (ex: 150.00).");
            }
        }
    }

    // Lê uma data e garante o formato correto--
    public LocalDate lerData(String mensagem){
        while(true){
            System.out.println(mensagem + " (AAAA-MM-DD):");
            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e){
                System.out.println("Data inválida. Use o formato AAAA-MM-DD (ex: 2000-05-20).");
            }
        }
    }

    // Lê um horário e garante o formato correto--
    public LocalTime lerHorario(String mensagem){
        while(true){
            System.out.println(mensagem + " (HH:MM):");
            try {
                return LocalTime.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e){
                System.out.println("Horário inválido. Use o formato HH:MM (ex: 08:30).");
            }
        }
    }

    // Lê um CPF e garante o formato correto (11 dígitos numéricos)--
    public String lerCpf(String mensagem){
        while(true){
            System.out.println(mensagem);
            String cpf = scanner.nextLine().trim();
            if(cpf.matches("\\d{11}")){
                return cpf;
            }
            System.out.println("CPF inválido. Digite exatamente 11 números sem pontos ou traços (ex: 46667546874).");
        }
    }

    // Lê um CPF e garante o formato correto (11 dígitos numéricos)--
    public String lerTelefone(String mensagem){
        while(true){
            System.out.println(mensagem);
            String telefone = scanner.nextLine().trim();
            if(telefone.matches("\\d{11}")){
                return telefone;
            }
            System.out.println("Número de Telefone inválido. O número deve conter 11 dígitos (ex: 11945876411)");
        }
    }

    // Lê um email e garante o formato correto--
    public String lerEmail(String mensagem){
        while(true){
            System.out.println(mensagem);
            String email = scanner.nextLine().trim();
            if(email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)+$")){
                return email;
            }
            System.out.println("Email inválido. Digite um email válido (ex: nome@email.com).");
        }
    }

    //Lê um valor numeric (Big decimal) e garante o formato correto--
    public BigDecimal lerBigDecimal(String mensagem) {
        Scanner scanner = new Scanner(System.in);
        BigDecimal valor = null;

        while (valor == null) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().replace(",", ".");

            try {
                valor = new BigDecimal(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número válido (ex: 99.90).");
            }
        }

        return valor;
    }

}