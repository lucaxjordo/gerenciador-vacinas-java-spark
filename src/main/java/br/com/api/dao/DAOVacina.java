package br.com.api.dao;

import br.com.api.model.Vacina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOVacina {
    public static Connection conexao;

    // Consultar todas as vacinas
    public static List<Vacina> consultarTodas() throws SQLException {
        List<Vacina> vacinas = new ArrayList<>();
        String sql = "SELECT * FROM vacina";

        try (PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                Vacina vacina = new Vacina(
                        resultado.getInt("id"),
                        resultado.getString("vacina"),
                        resultado.getString("descricao"),
                        resultado.getInt("limite_aplicacao"),
                        resultado.getString("publico_alvo")
                );
                vacinas.add(vacina);
            }
        }
        return vacinas;
    }

    // Consultar vacinas por faixa etária
    public static List<Vacina> consultarPorFaixaEtaria(String faixaEtaria) throws SQLException {
        List<Vacina> vacinas = new ArrayList<>();
        String sql = "SELECT * FROM vacina WHERE publico_alvo = ?";

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, faixaEtaria);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Vacina vacina = new Vacina(
                        resultado.getInt("id"),
                        resultado.getString("vacina"),
                        resultado.getString("descricao"),
                        resultado.getInt("limite_aplicacao"),
                        resultado.getString("publico_alvo")
                );
                vacinas.add(vacina);
            }
        }
        return vacinas;
    }

    // Consultar vacinas recomendadas acima de uma idade
    public static List<Vacina> consultarPorIdadeMaior(int meses) throws SQLException {
        List<Vacina> vacinas = new ArrayList<>();
        String sql = "SELECT * FROM vacina WHERE limite_aplicacao IS NULL OR limite_aplicacao > ?";

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, meses);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Vacina vacina = new Vacina(
                        resultado.getInt("id"),
                        resultado.getString("vacina"),
                        resultado.getString("descricao"),
                        resultado.getInt("limite_aplicacao"),
                        resultado.getString("publico_alvo")
                );
                vacinas.add(vacina);
            }

            if (vacinas.isEmpty()) {
                throw new SQLException("Nenhuma vacina encontrada para idade maior que " + meses + " meses.");
            }
        }
        return vacinas;
    }

    // Consultar vacinas não aplicáveis para um paciente
    public static List<Vacina> consultarNaoAplicaveis(int idPaciente) throws SQLException {
        List<Vacina> vacinas = new ArrayList<>();
        String sql = "SELECT v.* FROM vacina v " +
                "WHERE v.limite_aplicacao IS NOT NULL AND v.limite_aplicacao < " +
                "(SELECT TIMESTAMPDIFF(MONTH, p.data_nascimento, CURDATE()) FROM paciente p WHERE p.id = ?)";

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, idPaciente);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Vacina vacina = new Vacina(
                        resultado.getInt("id"),
                        resultado.getString("vacina"),
                        resultado.getString("descricao"),
                        resultado.getInt("limite_aplicacao"),
                        resultado.getString("publico_alvo")
                );
                vacinas.add(vacina);
            }

            if (vacinas.isEmpty()) {
                System.out.println("Nenhuma vacina não aplicável encontrada para o paciente com ID " + idPaciente);
            }
        }
        return vacinas;
    }


}