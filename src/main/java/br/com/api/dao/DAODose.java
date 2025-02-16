package br.com.api.dao;

import br.com.api.model.Dose;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAODose {
    public static Connection conexao;

    // Consultar doses por vacina
    public static List<Dose> consultarPorVacina(int idVacina) throws SQLException {
        List<Dose> doses = new ArrayList<>();
        String sql = "SELECT * FROM dose WHERE id_vacina = ?";

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, idVacina);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Dose dose = new Dose(
                        resultado.getInt("id"),
                        resultado.getInt("id_vacina"),
                        resultado.getString("dose"),
                        resultado.getInt("idade_recomendada_aplicacao")
                );
                doses.add(dose);
            }
        }
        return doses;
    }
}