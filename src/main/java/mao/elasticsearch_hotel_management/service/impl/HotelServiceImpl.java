package mao.elasticsearch_hotel_management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mao.elasticsearch_hotel_management.entity.Hotel;
import mao.elasticsearch_hotel_management.mapper.HotelMapper;
import mao.elasticsearch_hotel_management.service.IHotelService;
import org.springframework.stereotype.Service;

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

}