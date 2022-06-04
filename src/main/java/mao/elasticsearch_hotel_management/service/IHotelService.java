package mao.elasticsearch_hotel_management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mao.elasticsearch_hotel_management.entity.Hotel;

/**
 * Project name(项目名称)：elasticsearch_hotel_management
 * Package(包名): mao.elasticsearch_hotel_management.service
 * Interface(接口名): IHotelService
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/6/4
 * Time(创建时间)： 13:46
 * Version(版本): 1.0
 * Description(描述)： 接口
 */

public interface IHotelService extends IService<Hotel>
{

    /**
     * 删除酒店数据
     *
     * @param id 要删除的酒店id
     */
    void deleteHotelById(Long id);

    /**
     * 更新酒店数据
     *
     * @param hotel Hotel
     */
    void updateHotelById(Hotel hotel);

    /**
     * 保存或者添加一条酒店信息
     *
     * @param hotel Hotel
     */
    void saveHotel(Hotel hotel);
}
