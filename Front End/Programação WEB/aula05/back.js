const firebaseConfig = {
    apikey: "AIzaSyBeEr6vzu7lFOY7Krl4JAJ5afN4-OazzL0",
};

const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

document.getElementById('cadastroForm').addEventListener('submit', function(event) {
    event.proventDefault();

    const tijolo = document.getElementById('tijolo').value;
    const precoTijolo = document.getElementById('precoTijolo').value;
    const cimento = document.getElementById('cimento').value;
    const precoCimento = document.getElementById('precoCimento').value;
    const areia = document.getElementById('areia').value;
    const precoAreia = document.getElementById('precoAreia').value;

    const valorTotal = ((tijolo*precoTijolo) + (cimento*precoCimento) + (areia*precoAreia));

    calcularpreco(tijolo, precoTijolo, cimento, precoCimento, areia, precoAreia);
});

function calcularpreco(tijolo, precoTijolo, cimento, precoCimento, areia, precoAreia) {
    db.collection('alunos').add({
        Tijolo: tijolo,
        precoTijolo: precoTijolo,
        Cimento: cimento,
        precoCimento: precoCimento,
        Areia: areia,
        precoAreia: precoAreia
    })
    
    valorTotal


}