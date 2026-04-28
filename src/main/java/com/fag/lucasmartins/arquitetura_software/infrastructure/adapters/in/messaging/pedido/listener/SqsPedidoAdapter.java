package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PedidoServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.exceptions.ConsumerSQSException;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.mapper.PedidoEventDTOMapper;

import io.awspring.cloud.sqs.annotation.SqsListener;

@Component
public class SqsPedidoAdapter {

    private static final Logger log = LoggerFactory.getLogger(SqsPedidoAdapter.class);

    private final PedidoServicePort pedidoServicePort;

    public SqsPedidoAdapter(PedidoServicePort pedidoServicePort) {
        this.pedidoServicePort = pedidoServicePort;
    }

    // ATENÇÃO: Substitua pelo nome exato da sua fila criada na AWS
    @SqsListener("T0XN_SEU_NOME_COMPLETO.fifo")
    public void receberMensagem(PedidoEventDTO evento) {
        try {
            log.info("Evento de pedido recebido da AWS! Cliente ID: {}", evento.getCustomerId());

            // Converte o JSON que chegou para o objeto de negócio
            final PedidoBO pedidoBO = PedidoEventDTOMapper.toBo(evento);
            
            // Reutiliza a Service para salvar o pedido no banco de dados
            pedidoServicePort.criarPedido(pedidoBO);

            log.info("Pedido processado e salvo com sucesso no banco de dados!");
        } catch (Exception e) {
            log.error("Erro ao processar o evento de pedido vindo da AWS", e);
            throw new ConsumerSQSException("Erro ao processar pedido do SQS", e);
        }
    }
}