document.getElementById('numeroForm').addEventListener('submit', function(event){
    event.preventDefault(); //Impede o envio do formulário

    //obtem o valor do numero digitado
    const numero = parseFloat(document.getElementById('numero').value);

    //calcula o quadrado e o cubo
    const quadrado = calcularQuadrado(numero);
    const cubo = calcularCubo(numero);

    //exibe os resultados na página
    document.getElementById('resultadoQuadrado').textContent = quadrado;
    document.getElementById('resultadoCubo').textContent = cubo;
});

function calcularQuadrado(numero){
    return numero * numero;
}

function calcularCubo(numero){
    return numero * numero * numero;
}