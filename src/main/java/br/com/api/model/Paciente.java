package br.com.api.model;
import java.time.LocalDate;


public class Paciente {
    private int id;
    private String nome;
    private String cpf;
    private String sexo;
    private LocalDate data_nascimento;

    //Construtor para consulta de Paciente com ID
    public Paciente(int id, String nome, String cpf, String sexo, LocalDate data_nascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
    }

    //Construtor para consulta de Paciente sem ID
    public Paciente( String nome, String cpf, String sexo, LocalDate data_nascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

}


