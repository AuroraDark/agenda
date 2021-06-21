/**
 * Validação de Formulário
 * @author Andressa Silva
 */

function validar() {
	let nome = frmContato.nome.value
	let fone = frmContato.fone.value
	if (nome === "") {
		alert('Preencha o campo Nome')
		frmContato.nome.focus()
		return false
		
	}else if(fone === "") {
		alert('Preencha o campo Telefone')
		frmContato.fone.focus()
		return false
		
	}else {
		document.forms["frmContato"].submit()
	}
}

