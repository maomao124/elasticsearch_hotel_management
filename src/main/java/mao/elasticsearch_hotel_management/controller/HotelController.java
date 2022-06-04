package mao.elasticsearch_hotel_management.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mao.elasticsearch_hotel_management.constants.RabbitMQConstants;
import mao.elasticsearch_hotel_management.entity.Hotel;
import mao.elasticsearch_hotel_management.entity.PageResult;
import mao.elasticsearch_hotel_management.service.IHotelService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;

/**
 * Project name(项目名称)：elasticsearch_hotel_management
 * Package(包名): mao.elasticsearch_hotel_management.controller
 * Class(类名): HotelController
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/6/4
 * Time(创建时间)： 13:45
 * Version(版本): 1.0
 * Description(描述)： HotelController
 */

@RestController
@RequestMapping("hotel")
public class HotelController
{
    @Autowired
    private IHotelService hotelService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/{id}")
    public Hotel queryById(@PathVariable("id") Long id)
    {
        return hotelService.getById(id);
    }

    @GetMapping("/list")
    public PageResult hotelList
            (
                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                    @RequestParam(value = "size", defaultValue = "1") Integer size
            )
    {
        Page<Hotel> result = hotelService.page(new Page<>(page, size));

        return new PageResult(result.getTotal(), result.getRecords());
    }

    @PostMapping
    public void saveHotel(@RequestBody Hotel hotel)
    {
        // 新增酒店
        hotelService.save(hotel);
        // 发送MQ消息
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.INSERT_KEY, hotel.getId());
    }

    @PutMapping()
    public void updateById(@RequestBody Hotel hotel)
    {
        if (hotel.getId() == null)
        {
            throw new InvalidParameterException("id不能为空");
        }
        hotelService.updateById(hotel);

        // 发送MQ消息
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.INSERT_KEY, hotel.getId());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id)
    {
        hotelService.removeById(id);
        // 发送MQ消息
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.DELETE_KEY, id);
    }
}
