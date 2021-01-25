package com.mymusic.canal.schedules;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.mymusic.canal.client.CanalClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CananlSchedule{
    @Resource
    CanalConnector connector;

    @Resource(name = "stringRedisTemplate")
    StringRedisTemplate redisTemplate;

    @Value("${spring.canal.batchSize}")
    private int batchSize;

    @Value("${spring.canal.queueName}")
    private String queueName;

    @Scheduled(fixedDelay = 1000)
    public void subscribe () {
        Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
        long batchId = message.getId();
        int size = message.getEntries().size();
        if (batchId != -1 && size > 0) {
            try {
                printEntry(message.getEntries());
            } catch (Exception e) {
                log.error("message处理失败：" + e.getMessage());
            }
        }
        connector.ack(batchId); // 提交确认
    }

    private void printEntry(List<CanalEntry.Entry> entrys) {
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }

            CanalEntry.RowChange rowChage = null;
            try {
                rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }
            Map<String, Object> mqMessage = new HashMap<>();
            CanalEntry.EventType eventType = rowChage.getEventType();
//            System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
//                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
//                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
//                    eventType));

            mqMessage.put("db", entry.getHeader().getSchemaName());
            mqMessage.put("table", entry.getHeader().getTableName());
            mqMessage.put("eventType", eventType);

            Map<String, Object> mqMessageBefore = new HashMap<>();
            Map<String, Object> mqMessageAfter = new HashMap<>();

            for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == CanalEntry.EventType.DELETE) {
                    mqMessageBefore = printColumn(rowData.getBeforeColumnsList());
                } else if (eventType == CanalEntry.EventType.INSERT) {
                    mqMessageAfter = printColumn(rowData.getAfterColumnsList());
                } else {
//                    System.out.println("-------&gt; before");
                    mqMessageBefore = printColumn(rowData.getBeforeColumnsList());
//                    System.out.println("-------&gt; after");
                    mqMessageAfter = printColumn(rowData.getAfterColumnsList());
                }
            }

            mqMessage.put("before", mqMessageBefore);
            mqMessage.put("after", mqMessageAfter);

            log.info(JSONObject.toJSONString(mqMessage));
            redisTemplate.opsForList().leftPush(queueName, JSONObject.toJSONString(mqMessage));
        }
    }

    private Map<String, Object> printColumn(List<CanalEntry.Column> columns) {
        Map<String, Object> mqMessage = new HashMap<>();
        for (CanalEntry.Column column : columns) {
            //只处理被修改的值，提升序列化性能
            mqMessage.put(column.getName(), column.getValue());
//            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
        return mqMessage;
    }
}
