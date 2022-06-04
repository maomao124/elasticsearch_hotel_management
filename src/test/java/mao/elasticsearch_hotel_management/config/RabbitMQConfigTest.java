package mao.elasticsearch_hotel_management.config;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project name(项目名称)：elasticsearch_hotel_management
 * Package(包名): mao.elasticsearch_hotel_management.config
 * Class(测试类名): RabbitMQConfigTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/6/4
 * Time(创建时间)： 14:45
 * Version(版本): 1.0
 * Description(描述)： 测试类
 */

@SpringBootTest
class RabbitMQConfigTest
{

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void test()
    {
        rabbitTemplate.convertAndSend("test");
    }
}