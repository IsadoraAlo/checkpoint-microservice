package com.br.fiap.ativo.repository;

import com.br.fiap.ativo.model.EmailModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends CrudRepository<EmailModel, String> {

}
