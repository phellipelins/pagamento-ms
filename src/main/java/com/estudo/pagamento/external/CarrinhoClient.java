package com.estudo.pagamento.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("carrinho-ms")
public interface CarrinhoClient {
	
	@PutMapping(value = "/carrinho/{id}/confirmar")
	void confirmarPagamento(@PathVariable Long id);
	
	@PutMapping(value = "/carrinho/{id}/cancelar")
	void cancelarPagamento(@PathVariable Long id);
}
