package com.atayun.hgs.wuliu.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.atayun.hgs.wuliu.service.AlgorithmService;

@Controller
public class AlgorithmAction {

	@Resource
	private AlgorithmService algorithmService;
}
