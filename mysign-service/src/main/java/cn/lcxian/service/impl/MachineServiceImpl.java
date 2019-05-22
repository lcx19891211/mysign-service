package cn.lcxian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.lcxian.dao.MachineMapper;
import cn.lcxian.pojo.Machine;
import cn.lcxian.service.MachineService;

@Service
public class MachineServiceImpl implements MachineService {

	@Autowired
	private MachineMapper machineMapper;
	
	@Override
	public List<Machine> selectAll() {
		QueryWrapper<Machine> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("status", 1);
		List<Machine> list = machineMapper.selectList(queryWrapper );
		return list;
	}

}
