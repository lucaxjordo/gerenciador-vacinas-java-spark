package br.com.api.model;
import java.util.Arrays;
import java.util.List;

public class Vacina {
    private int id;
    private String vacina;
    private String descricao;
    private Integer limiteAplicacao;
    private String publicoAlvo;

    // lista de valores válidos para publicoAlvo
    private static final List<String> PUBLICOS_VALIDOS = Arrays.asList("CRIANCA", "ADOLESCENTE", "ADULTO", "GESTANTE");


    public Vacina() {}


    public Vacina(int id, String vacina, String descricao, Integer limiteAplicacao, String publicoAlvo) {
        this.id = id;
        this.vacina = vacina;
        this.descricao = descricao;
        this.limiteAplicacao = limiteAplicacao;
        this.setPublicoAlvo(publicoAlvo); // Usando o setter para validar
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVacina() {
        return vacina;
    }

    public void setVacina(String vacina) {
        this.vacina = vacina;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getLimiteAplicacao() {
        return limiteAplicacao;
    }

    public void setLimiteAplicacao(Integer limiteAplicacao) {
        this.limiteAplicacao = limiteAplicacao;
    }

    public String getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(String publicoAlvo) {
        if (publicoAlvo != null && PUBLICOS_VALIDOS.contains(publicoAlvo)) {
            this.publicoAlvo = publicoAlvo;
        } else {
            throw new IllegalArgumentException("Público-alvo inválido. Valores permitidos: CRIANCA, ADOLESCENTE, ADULTO, GESTANTE.");
        }
    }
}
