package br.com.api.service;

import br.com.api.dao.DAOVacina;
import br.com.api.model.Vacina;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;
import spark.Route;
import java.time.LocalDate;
import java.util.List;

public class ServicoVacina {
    private static final ObjectMapper mapper = new ObjectMapper();

    // Método para consultar todas as vacinas
    public static Route consultarTodasVacinas() {
        return (Request request, Response response) -> {
            try {
                List<Vacina> vacinas = DAOVacina.consultarTodasVacinas();
                response.status(200); // 200 OK
                return mapper.writeValueAsString(vacinas);
            } catch (Exception e) {
                response.status(500); // 500 Erro no servidor
                return "{\"message\": \"" + e.getMessage() + "\"}";
            }
        };
    }

    // Método para consultar vacinas por faixa etária
    public static Route consultarVacinasPorFaixaEtaria() {
        return (Request request, Response response) -> {
            try {
                int faixaEtaria = Integer.parseInt(request.params(":faixa"));

                // Validação da faixa etária
                if (faixaEtaria < 0) {
                    response.status(400); // 400 Requisição inválida
                    return "{\"message\": \"Faixa etária inválida. A idade deve ser um número positivo.\"}";
                }

                List<Vacina> vacinas = DAOVacina.consultarVacinasPorFaixaEtaria(faixaEtaria);
                response.status(200); // 200 OK
                return mapper.writeValueAsString(vacinas);
            } catch (NumberFormatException e) {
                response.status(400); // 400 Requisição inválida
                return "{\"message\": \"Faixa etária inválida. Forneça um número válido.\"}";
            } catch (Exception e) {
                response.status(500); // 500 Erro no servidor
                return "{\"message\": \"" + e.getMessage() + "\"}";
            }
        };
    }

    // Método para consultar vacinas recomendadas acima de uma idade
    public static Route consultarVacinasRecomendadasAcimaDeIdade() {
        return (Request request, Response response) -> {
            try {
                int meses = Integer.parseInt(request.params(":meses"));

                // Validação da idade
                if (meses < 0) {
                    response.status(400); // 400 Requisição inválida
                    return "{\"message\": \"Idade inválida. A idade deve ser um número positivo.\"}";
                }

                List<Vacina> vacinas = DAOVacina.consultarVacinasRecomendadasAcimaDeIdade(meses);
                response.status(200); // 200 OK
                return mapper.writeValueAsString(vacinas);
            } catch (NumberFormatException e) {
                response.status(400); // 400 Requisição inválida
                return "{\"message\": \"Idade inválida. Forneça um número válido.\"}";
            } catch (Exception e) {
                response.status(500); // 500 Erro no servidor
                return "{\"message\": \"" + e.getMessage() + "\"}";
            }
        };
    }

    // Método para consultar vacinas não aplicáveis para um paciente
    public static Route consultarVacinasNaoAplicaveis() {
        return (Request request, Response response) -> {
            try {
                int idPaciente = Integer.parseInt(request.params(":id"));

                // Consulta a idade do paciente
                Paciente paciente = DAOPaciente.consultarPorID(idPaciente);
                if (paciente == null) {
                    response.status(404); // 404 Não encontrado
                    return "{\"message\": \"Paciente não encontrado.\"}";
                }


                int idadeMeses = calcularIdadeEmMeses(paciente.getDataNascimento());

                // Consulta as vacinas não aplicáveis
                List<Vacina> vacinas = DAOVacina.consultarVacinasNaoAplicaveis(idadeMeses);
                response.status(200); // 200 OK
                return mapper.writeValueAsString(vacinas);
            } catch (NumberFormatException e) {
                response.status(400); // 400 Requisição inválida
                return "{\"message\": \"ID do paciente inválido. Forneça um número válido.\"}";
            } catch (Exception e) {
                response.status(500); // 500 Erro no servidor
                return "{\"message\": \"" + e.getMessage() + "\"}";
            }
        };
    }

    private static int calcularIdadeEmMeses(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        Period periodo = Period.between(dataNascimento, hoje);
        return periodo.getYears() * 12 + periodo.getMonths();
    }
}