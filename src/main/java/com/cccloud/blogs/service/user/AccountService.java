package com.cccloud.blogs.service.user;

import com.cccloud.blogs.commons.exceptions.BusinessException;
import com.cccloud.blogs.entity.user.Account;
import com.cccloud.blogs.mapper.AccountMapper;
import com.cccloud.blogs.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
        List<Account> accountList = accountMapper.listby(null, account.getAccount(), null);
        if (!accountList.isEmpty()) {
            throw new BusinessException("账号已存在");
        }
        if (StringUtils.hasText(account.getEmail())) {
            List<Account> emailList = accountMapper.listby(null, null, account.getEmail());
            if (!emailList.isEmpty()) {
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
        String md5Password = MD5.md5(account.getPassword());
        account.setPassword(passwordEncoder.encode(md5Password));
    }
}
