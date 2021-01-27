package com.mymusic.testelasticsearch.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 销售商品表  
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dn_goods")
public class Goods extends Base {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    /**
     * 品牌供应商ID
     */
    private Integer supplierId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 货号
     */
    private String goodsSn;

    /**
     * 商品类型 0单品 1组合商品
     */
    private String goodsType;

    /**
     * 分类ID
     */
    private Integer catId;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 规格商品ID
     */
    private Integer gsId;

    /**
     * 上架状态0下架1上架
     */
    private String isOnsale;

    /**
     * 单独购买 1只能立即购买0都可以
     */
    private String isAloneSale;

    /**
     * 是否可在小程序购买
     */
    private Boolean saleOnMiniApp;

    /**
     * 推广公司ID
     */
    private Integer cpId;

    /**
     * 商户ID, 0:非商户自建商品
     */
    private Integer chainId;

    /**
     * 贸易模式 0:一般贸易 1：保税贸易 2：保税直购 3：快件直邮
     */
    private Boolean tradeMode;

    /**
     * 起购数
     */
    private Integer minNum;

    /**
     * 限购数 0为不限购
     */
    private Integer maxNum;

    /**
     * 成本价
     */
    private BigDecimal basePrice;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Integer updatedAt;

    /**
     * 商品主图
     */
    private String goodsImg;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 有商品规格 时 对应商品属性表ID
     */
    private Integer goodsAttrId;

    /**
     * 有效期
     */
    private String validityDate;

    /**
     * 模版ID
     */
    private Integer shippingId;

    /**
     * 0正常1删除
     */
    private String isDelete;

    /**
     * 排序号 数字越小越靠前
     */
    private Boolean sortOrder;

    /**
     * 销售数量 提交订单就记数 取消订单 就减去对应的数量
     */
    private Integer saleNum;

    /**
     * 浏览次数
     */
    private Integer clickNum;

    /**
     * 第三方库存
     */
    private Integer goodsNumber;

    /**
     * 第三方冻结库存
     */
    private Integer goodsFrozenNumber;

    /**
     * 商品重量（KG）
     */
    private BigDecimal goodsWeight;

    /**
     * 1：展示到首页，0：不展示到首页(默认展示2罐装，没有就展示最小罐装)
     */
    private String inHome;

    /**
     * 是否控货，1控货， 0未控货
     */
    private Integer isLimit;

    /**
     * 贸易模式，1：一般贸易,2：跨境贸易
     */
    private Integer tradeType;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 是否支持改价，0：不支持，1:支持
     */
    private Integer isChangePrice;

    /**
     * 是否是最小罐装的商品
     */
    private Integer isMinNum;

    /**
     * 销量
     */
    private Integer saleNumber;

    /**
     * 用户修改购买商品数量步长
     */
    private Integer numStep;

    /**
     * 库存来源，0：商城库存，1：本地库存
     */
    private Boolean stockFrom;


}
