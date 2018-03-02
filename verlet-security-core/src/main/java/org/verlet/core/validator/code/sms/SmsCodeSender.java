package org.verlet.core.validator.code.sms;

/**
 * @author verlet
 * @date 2018/2/28
 */
public interface SmsCodeSender {
    void send(String mobile,String code);

}
