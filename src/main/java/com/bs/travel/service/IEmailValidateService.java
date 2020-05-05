package com.bs.travel.service;

import com.baomidou.mybatisplus.service.IService;
import com.bs.travel.entity.EmailValidate;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzh
 * @since 2019-01-26
 */
public interface IEmailValidateService extends IService<EmailValidate> {

    public boolean sendEmail(String toEmailAccount) throws Exception;

}
