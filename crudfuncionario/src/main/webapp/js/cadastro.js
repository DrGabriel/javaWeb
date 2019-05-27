const mensagemCadastro = document.getElementById("mensagemCadastro");
/*
*source: https://stackoverflow.com/questions/469357/html-text-input-allow-only-numeric-input
*https://jsfiddle.net/emkey08/zgvtjc51
*/
// Restricts input for the given textbox to the given inputFilter.
function setInputFilter(textbox, inputFilter) {
    ["input", "keydown", "keyup", "mousedown", "mouseup", "select", "contextmenu", "drop"].forEach(function(event) {
      textbox.addEventListener(event, function() {
        if (inputFilter(this.value)) {
          this.oldValue = this.value;
          this.oldSelectionStart = this.selectionStart;
          this.oldSelectionEnd = this.selectionEnd;
        } else if (this.hasOwnProperty("oldValue")) {
          this.value = this.oldValue;
          this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
        }
      });
    });
  }
function createUser(){
    let nome = document.getElementById('nomeUsuarioCadastro').value;
    let nomeCargo = document.getElementById('nomeCargoCadastro').value;
    let dddTelefone = document.getElementById('dddTelefone').value;
    let numeroTelefone = document.getElementById('numeroTelefone').value;
    let jsonBody = {    "nomeUsuario"    : nome,
                        "nomeCargo"      : nomeCargo,
                        "dddTelefone"    : dddTelefone,
                        "numeroTelefone" : numeroTelefone
                    };
    fetch(url,{
        method: 'POST',
        headers: new Headers({
            'Content-Type': 'application/json'
          }),
        body: JSON.stringify(jsonBody)})
    .then(response=>response.json())
    .then(response =>addRows(response))
    .catch(err =>{
        mensagemProcessamento.classList.remove('hidden');
        mensagemProcessamento.innerText = mensagemErroServidor;
    });
}
function checkValidityFormAndSubmit(){
    mensagemCadastro.classList.remove('hidden');
    var inputs = document.querySelectorAll('#formCadastro input');
    var valido = true;
    var errosCadastro = '';
    
    inputs.forEach(element=>{
        
        if(!element.checkValidity()){
            errosCadastro += element.id.replace(/([A-Z])/g, ' $1') + ' inválido!\n';
            valido = false;
        }
    
    });

    if(valido){
        mensagemCadastro.innerText = 'Cadastrando...';
        createUser();
        
    }else{
        mensagemCadastro.innerText = errosCadastro;
    }
    

}

document.getElementById('cadastrar').addEventListener('click', ()=>{
    checkValidityFormAndSubmit();
});

setInputFilter(document.getElementById("dddTelefone"), function(value) {
    return  /^-?\d*$/.test(value);
    });
setInputFilter(document.getElementById("numeroTelefone"), function(value) {
return /^-?\d*$/.test(value);
});