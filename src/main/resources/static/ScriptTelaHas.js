// BLOQUEAR COLAR NO INPUT
document.addEventListener("DOMContentLoaded", () => {
    const campo = document.getElementById("PlaceHasw");

    campo.addEventListener("paste", (e) => e.preventDefault());
    campo.addEventListener("contextmenu", (e) => e.preventDefault());
    campo.oncopy = () => false;
    campo.oncut = () => false;
});

// Habilitar input e botão ao aceitar termos
function habilitarBotao() {
    const termos = document.getElementById("termos");
    const botao = document.getElementById("botaoEnviar");
    const campo = document.getElementById("PlaceHasw");

    botao.disabled = !termos.checked;
    campo.disabled = !termos.checked;

    if (!termos.checked) campo.value = "";
}

// Carregar boleto ao entrar
document.addEventListener("DOMContentLoaded", async () => {

    const hash = localStorage.getItem("hashValido");

    if (!hash) {
        alert("Erro: Nenhum hash encontrado! Volte e tente novamente.");
        return;
    }

    // Exibir hash no visor
    document.getElementById("CodigoHasw").textContent = hash;

    // 🔥 ENDPOINT CORRETO (buscar boleto pelo hash)
    const API_URL = "https://projetoa3-1.onrender.com/boletos/" + hash;

    try {
        const resposta = await fetch(API_URL);

        if (!resposta.ok) {
            alert("Erro ao carregar boleto! Verifique se o backend está rodando.");
            return;
        }

        const boleto = await resposta.json();

        document.getElementById("valorBoleto").textContent = "R$ " + boleto.valor.toFixed(2);
        document.getElementById("descricaoBoleto").textContent = boleto.descricao;
        document.getElementById("emissaoBoleto").textContent = boleto.dataEmissao;
        document.getElementById("vencimentoBoleto").textContent = boleto.dataVencimento;

    } catch (e) {
        alert("Erro ao conectar com o servidor!");
        console.error(e);
    }

});

// Validação final
function AvancarPagina() {
    const digitado = document.getElementById("PlaceHasw").value.trim();
    const hashReal = localStorage.getItem("hashValido");

    if (digitado === hashReal) {
        window.location.href = "LoadingPag.html";
    } else {
        alert("Código Hash incorreto! Digite exatamente como aparece.");
    }
}
