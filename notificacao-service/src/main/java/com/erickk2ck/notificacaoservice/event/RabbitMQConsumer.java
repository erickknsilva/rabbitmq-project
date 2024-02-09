package com.erickk2ck.notificacaoservice.event;


import com.erickk2ck.notificacaoservice.EmailService;
import com.pedidoservice.dto.PedidoNotifyer;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "${spring.rabbitmq.queue.name}")
@Log4j2
public class RabbitMQConsumer {

    private final EmailService emailService;

    public RabbitMQConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitHandler
    public void consumerProduct(@Payload PedidoNotifyer request) {
        log.info(String.format("Product recebido com sucesso %s", request.toString()));

        String htmlContent = """
                <html>
                    <body>
                        <h1>Pedido aprovado</h1>
                        <h4>Olá, %s! tudo bem ? seu pedido foi aprovado</h4>
                             <table border="1" style=padding: "10px">
                                            <tr>
                                                <td><b>Nome</b></td>
                                                <td><b>Email</b></td>
                                                <td><b>Produto</b></td>
                                                <td><b>Marca</b></td>
                                                <td><b>Preço</b></td>
                                            </tr>
                                            <tr>
                                                <td>%s</td>
                                                <td>%s</td>
                                                <td>%s</td>
                                                <td>%s</td>
                                                <td>%s</td>
                                            </tr>
                                        </table>
                    </body>
                </html>
                """;
        String nome = request.getNome().concat(" " + request.getSobrenome());

        emailService.sendEmail(request.getEmail(), "Pedido aprovado", String.format(htmlContent,
                nome, nome, request.getEmail(),
                request.getProduto().nome(), request.getProduto().marca(), request.getProduto().preco()));

    }
}