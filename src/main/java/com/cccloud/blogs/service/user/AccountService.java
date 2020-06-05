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

    /**
     * 添加账号信息
     *
     * @param account 用户信息
     */
    public void add(Account account) {

        Account bean = accountMapper.findBy(null, account.getAccount(), null);
        if (bean != null) throw new BusinessException("账号已存在");

        if (StringUtils.hasText(account.getEmail())) {
            bean = accountMapper.findBy(null, null, account.getEmail());
            if (bean != null) throw new BusinessException("邮箱已存在");
        }
        if (!StringUtils.hasText(account.getName())) {
            account.setName(account.getAccount());
        }
        accountMapper.insert(account);
    }

    /**
     * 修改账号信息
     *
     * @param account 账号
     */
    public void update(Account account) {
        Account entity = getBy(account.getId());
        if (StringUtils.hasText(account.getEmail())) {
            Account emailBean = accountMapper.findBy(null, null, account.getEmail());
            if (emailBean != null && !account.getId().equals(emailBean.getId())) throw new BusinessException("邮箱已存在");
        }
        entity.copy(account);
        accountMapper.update(entity);
    }

    /**
     * 处理账号中的密码信息
     *
     * @param account   账号
     * @param publicKye 加密信息
     */
    public void encoderPassword(Account account, String publicKye) {
        if (StringUtils.hasText(publicKye)) {
            //TODO 传输解密
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
    }

    /**
     * 根据条件获取账号信息
     * 获取不到抛出 BusinessException
     *
     * @param name    账号名
     * @param account 账号
     * @param email   邮箱
     * @return 账号
     */
    public Account getBy(String name, String account, String email) {
        Account bean = findBy(name, account, email);
        if (bean == null) {
            throw new BusinessException("账号不存在");
        }
        return bean;
    }

    /**
     * 根据条件获取账号信息
     *
     * @param name    账号名
     * @param account 账号
     * @param email   邮箱
     * @return 账号
     */
    public Account findBy(String name, String account, String email) {
        return accountMapper.findBy(name, account, email);
    }

    /**
     * 查询账号信息
     *
     * @param id 账号ID
     * @return 账号信息
     */
    public Account findBy(Long id) {
        return accountMapper.selectKey(id);
    }

    /**
     * 查询账号信息
     * 查询结果为空 抛出BusinessException异常
     *
     * @param id 账号ID
     * @return 账号信息
     */
    public Account getBy(Long id) {
        Account entity = findBy(id);
        if (entity == null) throw new BusinessException("账号不存在");
        return entity;
    }


}
