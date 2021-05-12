package com.mzxssg.yygh.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mzxssg.yygh.model.order.PaymentInfo;
import com.mzxssg.yygh.model.order.RefundInfo;

public interface RefundInfoService extends IService<RefundInfo> {

    /**
     * 保存退款记录
     * @param paymentInfo
     */
    RefundInfo saveRefundInfo(PaymentInfo paymentInfo);

}
