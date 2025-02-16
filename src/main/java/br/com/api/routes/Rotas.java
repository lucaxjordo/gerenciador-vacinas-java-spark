package br.com.api.routes;

import br.com.api.service.ServicoUsuario;
import br.com.api.service.ServicoPaciente;
import br.com.api.service.ServicoVacina;
import br.com.api.service.ServicoDose;
import spark.Spark;

public class Rotas {

    public static void processarRotas() {
        // Configuração das rotas de Usuário
        configurarRotasUsuario();

        // Configuração das rotas de Paciente
        configurarRotasPaciente();

        // Configuração das rotas de Vacina
        configurarRotasVacina();

        // Configuração das rotas de Dose
        configurarRotasDose();
    }

    // Método para configurar as rotas de Usuário
    private static void configurarRotasUsuario() {
        Spark.post("/usuario/cadastrar", ServicoUsuario.cadastrarUsuario());
        Spark.get("/usuario/consultar/:id", ServicoUsuario.consultarUsuarioPorId());
        Spark.get("/usuario/consultar", ServicoUsuario.consultarTodosUsuarios());
        Spark.put("/usuario/alterar/:id", ServicoUsuario.alterarUsuario());
        Spark.delete("/usuario/excluir/:id", ServicoUsuario.excluirUsuario());
    }

    // Método para configurar as rotas de Paciente
    private static void configurarRotasPaciente() {
        Spark.post("/paciente/cadastrar", ServicoPaciente.cadastrarPaciente());
        Spark.get("/paciente/consultar/:id", ServicoPaciente.consultarPacientePorId());
        Spark.get("/paciente/consultar", ServicoPaciente.consultarTodosPacientes());
        Spark.put("/paciente/alterar/:id", ServicoPaciente.alterarPaciente());
        Spark.delete("/paciente/excluir/:id", ServicoPaciente.excluirPaciente());
    }

    // Método para configurar as rotas de Vacina
    private static void configurarRotasVacina() {
        Spark.get("/vacinas/consultar", ServicoVacina.consultarTodasVacinas());
        Spark.get("/vacinas/consultar/faixa_etaria/:faixa", ServicoVacina.consultarPorFaixaEtaria());
        Spark.get("/vacinas/consultar/idade_maior/:meses", ServicoVacina.consultarPorIdadeMaior());
        Spark.get("/vacinas/consultar/nao_aplicaveis/paciente/:id", ServicoVacina.consultarNaoAplicaveis());
    }

    // Método para configurar as rotas de Dose
    private static void configurarRotasDose() {
        Spark.get("/doses/consultar/:idVacina", ServicoDose.consultarDosesPorVacina());
    }
}
