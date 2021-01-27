package com.mymusic.testelasticsearch.schedules;

import com.alibaba.fastjson.JSONObject;
import com.mymusic.testelasticsearch.bean.DbSubscribeBean;
import com.mymusic.testelasticsearch.bean.GoodsBean;
import com.mymusic.testelasticsearch.entity.Goods;
import com.mymusic.testelasticsearch.mapper.GoodsMapper;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class DbSubscribeSchedule {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Value("${spring.canal.queueName}")
    String queueName;

    @Resource
    GoodsMapper goodsMapper;

    @Scheduled(fixedDelay = 500)
    public void subscribe() {
        log.info("====start====");
        String str = stringRedisTemplate.opsForList().rightPop(queueName, 10, TimeUnit.SECONDS);
        log.info(str);
        if (str == null) {
            return;
        }
        DbSubscribeBean bean = null;
        try {
            bean = JSONObject.parseObject(str, DbSubscribeBean.class);
        } catch (Exception e) {
            log.error("数据解析失败：" + str);
        }
        if (bean == null) {
            log.error("数据解析失败：" + str);
        }
        if ("dn_goods".equals(bean.getTable())) {
            log.info("before" + bean.getBefore());
            log.info("after" + bean.getAfter());
            GoodsBean dataBefore = JSONObject.parseObject(bean.getBefore(), GoodsBean.class);
            GoodsBean dataAfter = JSONObject.parseObject(bean.getAfter(), GoodsBean.class);
            deal(dataBefore, dataAfter);
            log.info("====end====");
        } else {
            log.warn("不是商品表： " + str);
        }
    }

    private void deal(GoodsBean dataBefore, GoodsBean dataAfter) {
        if (dataAfter.getGoods_id() == null) {
            log.warn("商品ID为空");
        }
        //获取数据
        Goods goods = goodsMapper.selectById(dataAfter.getGoods_id());
        if (goods.getGoodsId() == null) {
            log.warn("商品不存在[" + dataAfter.getGoods_id() + "]！");
        }

        log.info(JSONObject.toJSONString(goods));

        //获取ES数据


        //获取变更字段

        //封装待更新数据

        //更新ES


    }
}
