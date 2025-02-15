package br.com.api.dao;

import br.com.api.model.Dose;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAODose {
    public static Connection conexao;


     //* Método para consultar todas as doses de uma vacina específica.
    public static List<Dose> consultarDosesPorVacina(int idVacina) throws Exception {
        List<Dose> doses = new ArrayList<>();
        String sql = "SELECT * FROM dose WHERE id_vacina = ?";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, idVacina);
            // executar a query e processar o resultado
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Dose dose = new Dose(
                            rs.getInt("id"),
                            rs.getInt("id_vacina"),
                            rs.getString("dose"),
                            rs.getInt("idade_recomendada_aplicacao")
                    );
                    doses.add(dose);
                }
            }
        }

        return doses;
    }


    // * Método para consultar as doses recomendadas com base na idade do paciente.
    public static List<Dose> consultarDosesRecomendadasPorIdade(int idadeMeses) throws Exception {
        List<Dose> doses = new ArrayList<>();
        String sql = "SELECT * FROM dose WHERE idade_recomendada_aplicacao <= ?";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, idadeMeses);


            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    Dose dose = new Dose(
                            rs.getInt("id"),
                            rs.getInt("id_vacina"),
                            rs.getString("dose"),
                            rs.getInt("idade_recomendada_aplicacao")
                    );
                    doses.add(dose);
                }
            }
        }

        return doses;
    }
}
