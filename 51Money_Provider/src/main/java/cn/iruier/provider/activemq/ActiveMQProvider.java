package cn.iruier.provider.activemq;

import cn.iruier.service.activemq.ActiveMQService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @Author: iruier
 * @Date: 2018/8/4 19:58
 */
@Service
public class ActiveMQProvider implements ActiveMQService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ActiveMQQueue activeMQQueue;

    @Override
    public void sendMsg(String msg) {
        jmsTemplate.send(activeMQQueue, (session) -> {
            return session.createTextMessage(msg);
        });
    }

    @Override
    public void sendObject(Serializable serializable) {
        jmsTemplate.send(activeMQQueue, (session) -> {
            return session.createObjectMessage(serializable);
        });
    }
}
