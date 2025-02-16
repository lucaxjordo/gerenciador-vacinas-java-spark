package br.com.api.service;

import br.com.api.dao.DAOVacina;
import br.com.api.model.Vacina;
import spark.Request;
import spark.Response;
import spark.Route;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

public class ServicoVacina {
    public static Route consultarTodasVacinas() {
        return (Request request, Response response) -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<Vacina> vacinas = DAOVacina.consultarTodas();
                response.status(200); // OK
                return mapper.writeValueAsString(vacinas);
            } catch (SQLException e) {
                response.status(500); // Erro no servidor
                return "{\"message\": \"" + e.getMessage() + "\"}";
            }
        };
    }

    public static Route consultarPorFaixaEtaria() {
        return (Request request, Response response) -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String faixaEtaria = request.params(":faixa");
                List<Vacina> vacinas = DAOVacina.consultarPorFaixaEtaria(faixaEtaria);
                response.status(200); // OK
                return mapper.writeValueAsString(vacinas);
            } catch (SQLException e) {
                response.status(500); // Erro no servidor
                return "{\"message\": \"" + e.getMessage() + "\"}";
            }
        };
    }

    public static Route consultarPorIdadeMaior() {
        return (Request request, Response response) -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                int meses = Integer.parseInt(request.params(":meses"));
                List<Vacina> vacinas = DAOVacina.consultarPorIdadeMaior(meses);
                response.status(200); // OK
                return mapper.writeValueAsString(vacinas);
            } catch (SQLException e) {
                response.status(500); // Erro no servidor
                return "{\"message\": \"" + e.getMessage() + "\"}";
            }
        };
    }

    public static Route consultarNaoAplicaveis() {
        return (Request request, Response response) -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                int idPaciente = Integer.parseInt(request.params(":id"));
                List<Vacina> vacinas = DAOVacina.consultarNaoAplicaveis(idPaciente);
                response.status(200); // OK
                return mapper.writeValueAsString(vacinas);
            } catch (SQLException e) {
                response.status(500); // Erro no servidor
                return "{\"message\": \"" + e.getMessage() + "\"}";
            }
        };
    }
}