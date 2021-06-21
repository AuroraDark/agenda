/**
 * Validacao de Exclusao
 * @author Andressa Silva
 * @param idcon
 */

function confirmar(idcon){
	let resposta = confirm("Confirma a exclusão deste contato?")
	if(resposta === true){
		
		//encaminhando o parametro idcon para controller
		window.location.href = "delete?idcon=" + idcon
	}
}