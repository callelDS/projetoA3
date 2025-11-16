async function mostrarImagem() {
    const imagem = document.getElementById("minhaImagem");
    const botaoAv = document.getElementById("botaoAvancar");
    const codigo = document.getElementById("codigodebarras").value;

    // URL da sua API no Render
    const API_URL = "https://projetoa3-1.onrender.com/boletos/codigobarras/" + codigo;

    try {
        const resposta = await fetch(API_URL);

        if (resposta.ok) {
            const boleto = await resposta.json();

            // salva o HASH para a próxima tela
            localStorage.setItem("hashValido", boleto.codigoHash);

            imagem.src = "Boleto-Caixa.png";
            imagem.style.display = "block";
            imagem.style.width = "700px";
            imagem.style.marginTop = "15px";

            botaoAv.disabled = false;

        } else {
            botaoAv.disabled = true;
            imagem.style.display = "none";
            alert("Código de barras inválido!");
        }
    } catch (erro) {
        alert("Erro ao conectar com o servidor!");
    }
}

function AvancarPagina() {
    window.location.href = "CdHasw.html";
}
