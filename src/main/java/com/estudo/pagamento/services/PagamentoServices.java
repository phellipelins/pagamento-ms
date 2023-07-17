package com.estudo.pagamento.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estudo.pagamento.external.CarrinhoClient;
import com.estudo.pagamento.dtos.PagamentoDto;
import com.estudo.pagamento.entities.Pagamento;
import com.estudo.pagamento.entities.PagamentoStatus;
import com.estudo.pagamento.repository.PagamentoRepository;

@Service
public class PagamentoServices {
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CarrinhoClient carrinhoClient;
	
	public Page<PagamentoDto> findAll(Pageable pagination) {
		return pagamentoRepository.findAll(pagination).map(pag -> 
			mapper.map(pag, PagamentoDto.class)
		);
	}
	
	public PagamentoDto findById(Long id) {
		Pagamento pag = pagamentoRepository.getReferenceById(id);
		
		return mapper.map(pag, PagamentoDto.class);
	}
	
	public PagamentoDto save(PagamentoDto pagamentoDto) {
		var pagamento = mapper.map(pagamentoDto, Pagamento.class);
		
		pagamento.setStatus(PagamentoStatus.CRIADO);
		pagamento = pagamentoRepository.save(pagamento);
		return mapper.map(pagamento, PagamentoDto.class);
	}
	
	public PagamentoDto update(Long id, PagamentoDto pagamentoDto) {
		var pagamento = mapper.map(pagamentoDto, Pagamento.class);
		
		pagamento.setId(id);
		pagamento = pagamentoRepository.save(pagamento);
		
		if (pagamento.getStatus().equals(PagamentoStatus.CONFIRMADO)) {
			carrinhoClient.confirmarPagamento(pagamento.getCarrinhoId());
		} else if (pagamento.getStatus().equals(PagamentoStatus.CANCELADO)) { 
			carrinhoClient.cancelarPagamento(pagamento.getCarrinhoId());
		}

		return mapper.map(pagamentoDto,  PagamentoDto.class);
	}
	
	public void delete(Long id) {
		pagamentoRepository.deleteById(id);
	}
}
