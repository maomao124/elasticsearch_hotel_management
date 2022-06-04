package mao.elasticsearch_hotel_management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mao.elasticsearch_hotel_management.constants.RabbitMQConstants;
import mao.elasticsearch_hotel_management.entity.Hotel;
import mao.elasticsearch_hotel_management.mapper.HotelMapper;
import mao.elasticsearch_hotel_management.service.IHotelService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.InvalidParameterException;

/**
 * Project name(项目名称)：elasticsearch_hotel_management
 * Package(包名): mao.elasticsearch_hotel_management.service.impl
 * Class(类名): HotelServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/6/4
 * Time(创建时间)： 13:53
 * Version(版本): 1.0
 * Description(描述)： HotelService实现类
 */

@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements IHotelService
{

    @Resource
    private RabbitTemplate rabbitTemplate;


    @Override
    public void deleteHotelById(Long id)
    {
        this.removeById(id);
        //发送MQ消息
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.DELETE_KEY, id);
    }

    @Override
    public void updateHotelById(Hotel hotel)
    {
        if (hotel.getId() == null)
        {
            throw new InvalidParameterException("id不能为空");
        }
        boolean b = this.updateById(hotel);

        //判断是否成功
        if (b)
        {
            //发送MQ消息
            rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.INSERT_KEY, hotel.getId());
        }
    }

    @Override
    public void saveHotel(Hotel hotel)
    {
        // 新增酒店
        boolean save = this.save(hotel);
        if (save)
        {
            // 发送MQ消息
            rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.INSERT_KEY, hotel.getId());
        }
    }
}
