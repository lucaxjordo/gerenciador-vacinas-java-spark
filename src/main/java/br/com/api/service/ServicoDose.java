package br.com.api.service;

import br.com.api.dao.DAODose;
import br.com.api.model.Dose;
import spark.Request;
import spark.Response;
import spark.Route;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

public class ServicoDose {
    public static Route consultarDosesPorVacina() {
        return (Request request, Response response) -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                int idVacina = Integer.parseInt(request.params(":idVacina"));
                List<Dose> doses = DAODose.consultarPorVacina(idVacina);
                response.status(200); // OK
                return mapper.writeValueAsString(doses);
            } catch (SQLException e) {
                response.status(500); // Erro no servidor
                return "{\"message\": \"" + e.getMessage() + "\"}";
            }
        };
    }
}