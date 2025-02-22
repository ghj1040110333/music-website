package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.Result;
import com.example.yin.constant.Constants;
import com.example.yin.mapper.ConsumerMapper;
import com.example.yin.model.domain.Consumer;
import com.example.yin.model.request.ConsumerRequest;
import com.example.yin.service.ConsumerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.example.yin.constant.Constants.SALT;

@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer>
        implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;


    /**
     * 新增用户
     */
    @Override
    public Result addUser(ConsumerRequest registryRequest) {
        if (this.existUser(registryRequest.getUsername())) {
            return Result.warning("用户名已注册");
        }
        Consumer consumer = new Consumer();
        BeanUtils.copyProperties(registryRequest, consumer);
        //MD5加密
        String password = DigestUtils.md5DigestAsHex((SALT + registryRequest.getPassword()).getBytes(StandardCharsets.UTF_8));
        consumer.setPassword(password);
        //都用用
        if (StringUtils.isBlank(consumer.getPhoneNum())) {
            consumer.setPhoneNum(null);
        }
        if ("".equals(consumer.getEmail())) {
            consumer.setEmail(null);
        }
        consumer.setAvator("img/avatorImages/user.jpg");
        try {
            if (consumerMapper.insert(consumer) > 0) {
                return Result.success("注册成功");
            } else {
                return Result.error("注册失败");
            }
        } catch (DuplicateKeyException e) {
            return Result.fatal(e.getMessage());
        }
    }

    @Override
    public Result updateUserMsg(ConsumerRequest updateRequest) {
        Consumer consumer = new Consumer();
        BeanUtils.copyProperties(updateRequest, consumer);
        if (consumerMapper.updateById(consumer) > 0) {
            return Result.success("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    @Override
    public Result updatePassword(ConsumerRequest updatePasswordRequest) {

       if (!this.verityPasswd(updatePasswordRequest.getUsername(),updatePasswordRequest.getOldPassword())) {
            return Result.error("密码输入错误");
        }

        Consumer consumer = new Consumer();
        consumer.setId(updatePasswordRequest.getId());
        String secretPassword = DigestUtils.md5DigestAsHex((SALT + updatePasswordRequest.getPassword()).getBytes(StandardCharsets.UTF_8));
        consumer.setPassword(secretPassword);

        if (consumerMapper.updateById(consumer) > 0) {
            return Result.success("密码修改成功");
        } else {
            return Result.error("密码修改失败");
        }
    }

    @Override
    public Result updateUserAvator(MultipartFile avatorFile, int id) {
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //路径 他这个会根据你的系统获取对应的文件分隔符
        String filePath = Constants.ASSETS_PATH + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "avatorImages";
        File file = new File(filePath);
        if (!file.exists() && !file.mkdir()) {
            return Result.fatal("创建文件失败");
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/avatorImages/" + fileName;
        try {
            avatorFile.transferTo(dest);
        } catch (IOException e) {
            return Result.fatal("上传失败" + e.getMessage());
        }
        Consumer consumer = new Consumer();
        consumer.setId(id);
        consumer.setAvator(imgPath);
        if (consumerMapper.updateById(consumer) > 0) {
            return Result.success("上传成功", imgPath);
        } else {
            return Result.error("上传失败");
        }
    }

    @Override
    public boolean existUser(String username) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return consumerMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public boolean verityPasswd(String username, String password) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        String secretPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes(StandardCharsets.UTF_8));

        queryWrapper.eq("password",secretPassword);
        return consumerMapper.selectCount(queryWrapper) > 0;
    }

    // 删除用户
    @Override
    public Result deleteUser(Integer id) {
        if (consumerMapper.deleteById(id) > 0) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    @Override
    public Result allUser() {
        return Result.success(null, consumerMapper.selectList(null));
    }

    @Override
    public Result userOfId(Integer id) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return Result.success(null, consumerMapper.selectList(queryWrapper));
    }

    @Override
    public Result loginStatus(ConsumerRequest loginRequest, HttpSession session) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (this.verityPasswd(username, password)) {
            session.setAttribute("username", username);
            Consumer consumer = new Consumer();
            consumer.setUsername(username);
            return Result.success("登录成功", consumerMapper.selectList(new QueryWrapper<>(consumer)));
        } else {
            return Result.error("用户名或密码错误");
        }
    }
}
