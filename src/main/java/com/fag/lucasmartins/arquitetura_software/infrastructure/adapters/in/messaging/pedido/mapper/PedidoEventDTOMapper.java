package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoItemEventDTO;

import java.util.ArrayList;
import java.util.List;

public class PedidoEventDTOMapper {

    public static PedidoBO toBo(PedidoEventDTO dto) {
        PedidoBO pedido = new PedidoBO();
        
        // Mapeia o Cliente (customerId)
        PessoaBO pessoa = new PessoaBO();
        pessoa.setId(dto.getCustomerId());
        pedido.setPessoa(pessoa);
        
        // Mapeia o CEP (zipCode)
        pedido.setCep(dto.getZipCode());
        
        // Mapeia os Itens (orderItems -> PedidoProdutoBO)
        List<PedidoProdutoBO> itens = new ArrayList<>();
        if (dto.getOrderItems() != null) {
            for (PedidoItemEventDTO itemDTO : dto.getOrderItems()) {
                PedidoProdutoBO itemBO = new PedidoProdutoBO();
                
                ProdutoBO produto = new ProdutoBO();
                produto.setId(itemDTO.getSku());
                itemBO.setProduto(produto);
                
                itemBO.setQuantidade(itemDTO.getAmount());
                itens.add(itemBO);
            }
        }
        pedido.setItens(itens);
        
        return pedido;
    }
}