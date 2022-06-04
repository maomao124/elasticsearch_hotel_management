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


    /**
     * 根据id查询酒店数据
     *
     * @param id id
     * @return Hotel
     */
    @GetMapping("/{id}")
    public Hotel queryById(@PathVariable("id") Long id)
    {
        return hotelService.getById(id);
    }

    /**
     * 获得酒店数据，分页
     *
     * @param page 当前页
     * @param size 页大小
     * @return PageResult
     */
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

    /**
     * 保存或者添加一条酒店信息
     *
     * @param hotel Hotel
     */
    @PostMapping
    public void saveHotel(@RequestBody Hotel hotel)
    {
        hotelService.saveHotel(hotel);
    }

    /**
     * 更新酒店数据
     *
     * @param hotel Hotel
     */
    @PutMapping()
    public void updateById(@RequestBody Hotel hotel)
    {
        hotelService.updateHotelById(hotel);
    }

    /**
     * 删除酒店数据
     *
     * @param id 要删除的酒店id
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id)
    {
        this.hotelService.deleteHotelById(id);
    }
}
