function fazPost(url, body){

    let request = new XMLHttpRequest;
    request.open("POST", url, true)
    request.setRequestHeader("Content-type", "application/json")
    request.send(JSON.stringify(body))

    request.onload = function(){
        console.log(this.responseText)
    }

    return request.responseText

}


function cadastrarProduto(){
    console.log("cheguei aqui");
    url = "http://localhost:8060/api/v1/produtos/criar"

    let nome = document.getElementById('nome').value
    let quantidade = document.getElementById('quantidade').value
    let valor = document.getElementById('valor').value

    console.log("nome");
    console.log("quantidade");
    console.log("valor");


    body = 
        {
            "nome" : nome,
            "quantidade" : quantidade,
            "valor" : valor
        };
    

    fazPost(url, body)

}