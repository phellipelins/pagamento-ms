package com.estudo.pagamento.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.estudo.pagamento.dtos.PagamentoDto;
import com.estudo.pagamento.services.PagamentoServices;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/pagamento")
public class PagamentoController {

	@Autowired
	private PagamentoServices pagamentoService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(description = "Esta função retorna os pagamentos disponíveis.")
	public ResponseEntity<Page<PagamentoDto>> findAll(@PageableDefault(size = 5) Pageable pagination) {
		return ResponseEntity.ok(pagamentoService.findAll(pagination));
	}
	
	@GetMapping(
		path = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<PagamentoDto> findById(@PathVariable Long id) {
		return ResponseEntity.ok(pagamentoService.findById(id));
	}

	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<PagamentoDto> save(@RequestBody @Valid PagamentoDto pagamentoDto, UriComponentsBuilder uriBuilder) {
		var tempPagamento = pagamentoService.save(pagamentoDto);
		
		URI address = uriBuilder.path("/pagamento/{id}").buildAndExpand(tempPagamento.getId()).toUri();
		
		return ResponseEntity.created(address).body(tempPagamento);
	}

	@PutMapping(
		path = "/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<PagamentoDto> update(@PathVariable Long id, @RequestBody @Valid PagamentoDto pagamentoDto) {
		return ResponseEntity.ok(pagamentoService.update(id,  pagamentoDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		pagamentoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
