package br.com.fiap.checkpoint3.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.checkpoint3.dtos.ContatosRequestCreateDto;
import br.com.fiap.checkpoint3.dtos.ContatosRequestUpdateDto;
import br.com.fiap.checkpoint3.dtos.ContatosResponseDto;
import br.com.fiap.checkpoint3.mapper.ContatosMapper;
import br.com.fiap.checkpoint3.repository.ContatosRepository;
import br.com.fiap.checkpoint3.service.ContatosService;
import br.com.fiap.checkpoint3.views.ContatosSimpleView;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contatos")
@RequiredArgsConstructor
public class ContatosController {    
    private final ContatosService contatosService;
    private final ContatosMapper contatosMapper;
    private final ContatosRepository contatosRepository;

    @GetMapping
    public ResponseEntity<List<ContatosResponseDto>> list() {
        List<ContatosResponseDto> dtos = contatosService.list()
            .stream()
            .map(e -> contatosMapper.toDto(e))
            .toList();
        
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<ContatosResponseDto> create(@RequestBody ContatosRequestCreateDto dto) {        

        return ResponseEntity
        		.status(HttpStatus.CREATED)
        		.body(
        			contatosMapper.toDto(
        					contatosService.save(contatosMapper.toModel(dto)))
        			);
    }

    @PutMapping("{id}")
    public ResponseEntity<ContatosResponseDto> update(
                        @PathVariable Long id, 
                        @RequestBody ContatosRequestUpdateDto dto) {
        if (! contatosService.existsById(id)){
            throw new RuntimeException("Id inexistente");
        }                
        return ResponseEntity.ok()
        		.body(
        			contatosMapper.toDto(
        				contatosService.save(contatosMapper.toModel(id, dto)))
        		);
    }
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (! contatosService.existsById(id)){
        	throw new RuntimeException("Id inexistente");
        }

        contatosService.delete(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<ContatosResponseDto> findById(@PathVariable Long id) {    	
    	return ResponseEntity.ok()
    			.body(
    				contatosService
    					.findById(id)
    					.map(e -> contatosMapper.toDto(e))
    					.orElseThrow(() -> new RuntimeException("Id inexistente"))
    			);    	  		     
    }
    
    @GetMapping("/")
    public  ResponseEntity<?> findByNome(
                @RequestParam String nome) {
            
                return ResponseEntity.ok().body(contatosRepository.findAllByNomeContains(nome, ContatosSimpleView.class));            
    }
}