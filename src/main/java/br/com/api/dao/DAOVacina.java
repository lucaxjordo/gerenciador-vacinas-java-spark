package br.com.api.dao;

import br.com.api.model.Vacina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOVacina {
    public static Connection conexao;

    // Método para consultar todas as vacinas
    public static List<Vacina> consultarTodasVacinas() throws Exception {
        String sql = "SELECT * FROM vacina";
        List<Vacina> vacinas = new ArrayList<>();

        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vacina vacina = new Vacina(
                        rs.getInt("id"),
                        rs.getString("vacina"),
                        rs.getString("descricao"),
                        rs.getInt("limite_aplicacao"),
                        rs.getString("publico_alvo")
                );
                vacinas.add(vacina);
            }
        }

        return vacinas;
    }

    // Método para consultar vacinas por faixa etária
    public static List<Vacina> consultarVacinasPorFaixaEtaria(int faixaEtaria) throws Exception {
        String sql = "SELECT * FROM vacina WHERE (limite_aplicacao IS NULL OR limite_aplicacao >= ?)";
        List<Vacina> vacinas = new ArrayList<>();

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, faixaEtaria);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vacina vacina = new Vacina(
                            rs.getInt("id"),
                            rs.getString("vacina"),
                            rs.getString("descricao"),
                            rs.getInt("limite_aplicacao"),
                            rs.getString("publico_alvo")
                    );
                    vacinas.add(vacina);
                }
            }
        }

        return vacinas;
    }

    // Método para consultar vacinas recomendadas acima de uma idade
    public static List<Vacina> consultarVacinasRecomendadasAcimaDeIdade(int meses) throws Exception {
        String sql = "SELECT * FROM vacina WHERE limite_aplicacao > ?";
        List<Vacina> vacinas = new ArrayList<>();

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, meses);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vacina vacina = new Vacina(
                            rs.getInt("id"),
                            rs.getString("vacina"),
                            rs.getString("descricao"),
                            rs.getInt("limite_aplicacao"),
                            rs.getString("publico_alvo")
                    );
                    vacinas.add(vacina);
                }
            }
        }

        return vacinas;
    }

    // Método para consultar vacinas não aplicáveis para um paciente
    public static List<Vacina> consultarVacinasNaoAplicaveis(int idadeMeses) throws Exception {
        String sql = "SELECT * FROM vacina WHERE limite_aplicacao IS NOT NULL AND limite_aplicacao < ?";
        List<Vacina> vacinas = new ArrayList<>();

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, idadeMeses);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vacina vacina = new Vacina(
                            rs.getInt("id"),
                            rs.getString("vacina"),
                            rs.getString("descricao"),
                            rs.getInt("limite_aplicacao"),
                            rs.getString("publico_alvo")
                    );
                    vacinas.add(vacina);
                }
            }
        }

        return vacinas;
    }
}