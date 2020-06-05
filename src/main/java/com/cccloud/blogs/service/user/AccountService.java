package com.cccloud.blogs.service.user;

import com.cccloud.blogs.commons.exceptions.BusinessException;
import com.cccloud.blogs.entity.user.Account;
import com.cccloud.blogs.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 作者：徐卫超
 * 时间：2020/6/5 9:39
 * 描述：
 */
@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void add(Account account) {
        int accountCount = accountMapper.countBy(null, account.getAccount(), null);
        if (accountCount > 0) {
            throw new BusinessException("账号已存在");
        }
        if (StringUtils.hasText(account.getEmail())) {
            int emailCount = accountMapper.countBy(null, null, account.getEmail());
            if (emailCount > 0) {
                throw new BusinessException("邮箱已存在");
            }
        }
        if (!StringUtils.hasText(account.getName())) {
            account.setName(account.getAccount());
        }
        accountMapper.insert(account);
    }

    public void encoderPassword(Account account, String publicKye) {
        if (StringUtils.hasText(publicKye)) {
            //TODO 传输解密
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
    }

    public Account getBy(String name, String account, String email) {
        Account bean = findBy(name, account, email);
        if (bean == null) {
            throw new BusinessException("账号不存在");
        }
        return bean;
    }

    public Account findBy(String name, String account, String email) {
        return accountMapper.findBy(name, account, email);
    }
}
