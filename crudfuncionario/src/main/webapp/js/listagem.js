var lista;
const url = "http://localhost:8080/usuarios/rest/funcionario?";
const mensagemProcessamento = document.getElementById("mensagemProcessamento");
const mensagemBusca = document.getElementById("mensagemBusca");
const mensagemResultado = document.getElementById("mensagemResultado");
const mensagemErroServidor = 'Ocorreu um erro! Estamos trabalhando para resolver isso!';

function toJson(fieldName,fieldValue){
    return  '{\"'+ fieldName+'\":' + '\"' + fieldValue+ '\"' + '}'
}
function clearRows(){
    let tbodyRef = document.querySelector("tbody");
    let fc = tbodyRef.firstChild;
    while( fc ) {
        tbodyRef.removeChild( fc );
        fc = tbodyRef.firstChild;
    }
}
function addRows(response){
    clearRows();
    if(response.length ===0){
        mensagemResultado.innerText = '0 resultados encontrados';
    }else{
        mensagemResultado.innerText = response.length + ' resultados encontrados';
        response.forEach(function(element,index) {
            
            let tbodyRef = document.querySelector("tbody");
            let newRow   = tbodyRef.insertRow(tbodyRef.rows.length);
            newRow.innerHTML =    `<th scope="row">`+ (index+1) +`</th>`
                                + `<td>`+ element.nomeUsuario +`</td>`
                                + `<td>`+ element.nomeCargo +`</td>`
                                + `<td>`+ `(` + element.dddTelefone + `)` + element.numeroTelefone + `</td>`;
        });
    }
    mensagemProcessamento.classList.add('hidden');
    mensagemBusca.classList.add('hidden');
    mensagemProcessamento.innerText = '';
    mensagemBusca.innerText = '';
}
function listarFuncionarios(){
    
    mensagemBusca.classList.remove('hidden');
    mensagemBusca.innerText = 'Buscando funcionarios...';
    fetch(url)
        .then(response=>response.json())
        .then(response => addRows(response))
        .catch(err =>{
            mensagemProcessamento.classList.remove('hidden');
            mensagemProcessamento.innerText = mensagemErroServidor;
        });
        
}
function buscaFuncionario(){
    mensagemProcessamento.classList.remove('hidden');
    mensagemBusca.classList.remove('hidden');
    let usuarioValue = document.getElementById("nomeUsuario").value;
    let cargoValue = document.getElementById("nomeCargo").value;

    let params = {"nomeUsuario":usuarioValue, "nomeCargo":cargoValue};

    let query = Object.keys(params)
             .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(params[k]))
             .join('&');

    let usuarioLenght = usuarioValue.length;
    let cargoLength = cargoValue.length;

    if(usuarioLenght === 0 && cargoLength ===0){
        mensagemBusca.innerText = 'Preencha ao menos um campo para realizar a pesquisa!';
        return;
    }else{
        let inicioMsg = 'Realizando pesquisa por: ',
            finalMsg= ' do usuário...';
        if(usuarioLenght  != 0 && cargoLength  === 0){
            mensagemBusca.innerText =  inicioMsg +'nome' + finalMsg;
        }else if(usuarioLenght  === 0 && cargoLength  !=0){
            mensagemBusca.innerText = inicioMsg +'por cargo'+finalMsg;
        }else{
            mensagemBusca.innerText = inicioMsg +'nome e cargo'+finalMsg;
        }
        fetch(url+query,{
            method: 'GET',
            headers: new Headers({
                'Content-Type': 'application/json'
              })})
        .then(response=>response.json())
        .then(response =>addRows(response))
        .catch(err =>{
            mensagemProcessamento.classList.remove('hidden');
            mensagemProcessamento.innerText = mensagemErroServidor;
        });

    }

}
function checkSubmit(e) {
    if(e && e.keyCode == 13) {
        buscaFuncionario();
    }
 }

document.getElementById('formulario').addEventListener('keypress', (event)=>{
    checkSubmit(event)
});
document.getElementById('pesquisar').addEventListener('click', ()=>{
    buscaFuncionario()
});

document.getElementById('limpar').addEventListener('click', ()=>{
	document.getElementById("nomeUsuario").value = '';
	document.getElementById("nomeCargo").value = '';
	listarFuncionarios()
});

listarFuncionarios();


