package com.wanxiang.service.impl;

import com.wanxiang.entity.Student;
import com.wanxiang.mapper.StudentMapper;
import com.wanxiang.service.StudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author single
 * @since 2018-03-26
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
