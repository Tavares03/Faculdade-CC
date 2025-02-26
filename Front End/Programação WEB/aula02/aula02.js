var area = document.getElementById('area');
var nome = document.getElementById('nome');
var curso = document.getElementById('curso');

function entrar() {
  nome = prompt("Digite seu nome:");
  curso = prompt("Digite seu curso");
  if (nome === '' || nome === null || curso === '' || curso === null) {
    alert("Ops, algo deu errado!");
    area.innerHTML = "Clique no botão para acessar...";
  } else {
    area.innerHTML = "Bem-vindo, " + nome + ", curso de " + curso +"! <br>";

    // Cria um botão "Sair da conta"
    let botaoSair = document.createElement("button");
    botaoSair.innerText = "Sair da conta";
    botaoSair.onclick = sair;
    area.appendChild(botaoSair); // Adiciona o botão ao DOM

   let botaoMedia = document.createElement("button");
   botaoMedia.innerText = "Fazer Media";
   botaoMedia.onclick = mediaTresNotas;
   area.appendChild(botaoMedia);
  }
}

function sair() {
    alert("Até mais!");
    area.innerHTML = "Você saiu!";
}
  
function mediaTresNotas(){

    var nota, media;
    var soma = 0;

    for(i=0; i<3; i++){
        nota = Number(prompt(`Digite sua ${i+1}`));
        while (nota === '' || nota === null){
            nota = Number(prompt(`Digite sua nota ${i+1}`));
        }
        soma += nota;
    }
    media = soma/3;

    area.innerHTML = "Sua media foi: " + media +"";

}