package br.com.fiap.checkpoint3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint3.model.Contatos;
import br.com.fiap.checkpoint3.repository.ContatosRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContatosService {
    private final ContatosRepository contatosRepository;

    public List<Contatos> list() {
        return contatosRepository.findAll();
    }

    public Contatos save(Contatos contatos) {        
        return contatosRepository.save(contatos);
    }

    public boolean existsById(Long id) {        
        return contatosRepository.existsById(id);
    }

    public void delete(Long id) {
        contatosRepository.deleteById(id);
    }

    public Optional<Contatos> findById(Long id) {
        return contatosRepository.findById(id);
    }
   
}
