package br.com.fiap.appprodutoteste.produto.service.impl;

import br.com.fiap.appprodutoteste.produto.model.Produto;
import br.com.fiap.appprodutoteste.produto.model.dto.EmailDTO;
import br.com.fiap.appprodutoteste.produto.model.dto.ProdutoDTO;
import br.com.fiap.appprodutoteste.produto.repositories.ProdutoRepository;
import br.com.fiap.appprodutoteste.produto.service.ProdutoService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto salvar(ProdutoDTO produtoDTO) {
        System.out.println("Produto: " + produtoDTO.getNome());
        ModelMapper modelMapper = new ModelMapper();
        Produto produto = new Produto();
        modelMapper.map(produtoDTO, produto);


        //salvar produto
        produtoRepository.save(produto);


        //Enviar email
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/send-email";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HashMap<String, String> map = new HashMap<>();
        map.put("emailTo", "giovannikenjimendes1@gmail.com");
        map.put("subject", "CADASTRO DE PRODUTO");
        map.put("text", "O produto: " + produto.getNome() + " foi cadastrado com sucesso");

        //
        //    "emailTo": "giovannikenjimendes1@gmail.com",
        //    "subject": "PRODUTO",
        //    "text": "Cadastro de produto"
        //

        HttpEntity<HashMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<EmailDTO> response = restTemplate.postForEntity(url, request, EmailDTO.class);

        if(response.getStatusCode().equals(HttpStatus.OK)){
            System.out.println("Email Enviado com sucesso!");
        }

        return produto;
    }

    @Override
    public List<Produto> getAllProducts() {
        return (List<Produto>) produtoRepository.findAll();
    }


}