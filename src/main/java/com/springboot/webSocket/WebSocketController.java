package com.springboot.webSocket;

import com.springboot.project.orders.data.OrdersData;
import com.springboot.project.orders.model.dao.OrdersDao;
import com.springboot.project.orders.model.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Optional;

@Controller
public class WebSocketController {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private OrdersMapper ordersMapper;

    // @MessageMapping和@RequestMapping功能類似，前端傳送後端url。
    // @SendTo service 傳訊息給前端接收。
    @MessageMapping("/F2B")
    @SendTo("/topic/B2F")
    public String gameInfo(String js){
        StringBuilder msg = new StringBuilder();
        Optional<OrdersData> orders = this.ordersMapper.getById(js);
        OrdersData orderData = orders.get();
        msg.append(orderData.getOrderName());
        msg.append("於").append(new Date());
        msg.append("訂購'").append(orderData.getBook()).append("'乙本！");
        return msg.toString();
    }
}
