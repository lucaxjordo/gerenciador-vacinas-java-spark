package br.com.api.routes;

import br.com.api.service.*;
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

        // Configuração das rotas de Imunizacao
        configurarRotasImunizacao();
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

    private static void configurarRotasImunizacao() {
        Spark.post("/imunizacao/inserir", ServicoImunizacao.cadastrarImunizacao());
        Spark.get("/imunizacao/consultar/:id", ServicoImunizacao.consultarImunizacaoPorId());
        Spark.get("/imunizacao/consultar", ServicoImunizacao.consultarTodasImunizacoes());
        Spark.put("/imunizacao/alterar/:id", ServicoImunizacao.alterarImunizacao());
        Spark.delete("/imunizacao/excluir/:id", ServicoImunizacao.excluirImunizacao());
        Spark.delete("/imunizacao/excluir/paciente/:id", ServicoImunizacao.excluirImunizacoesPorPaciente());
        Spark.get("/imunizacao/consultar/paciente/:id/aplicacao/:dt_ini/:dt_fim", ServicoImunizacao.consultarImunizacoesPorPacienteEData());
    }

}
