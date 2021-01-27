package com.mymusic.testelasticsearch.service.impl;

import com.mymusic.testelasticsearch.entity.Goods;
import com.mymusic.testelasticsearch.mapper.GoodsMapper;
import com.mymusic.testelasticsearch.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 销售商品表   服务实现类
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-26
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

}
