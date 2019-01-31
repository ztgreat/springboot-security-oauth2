package com.springboot.security.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.security.base.CommonConstant;
import com.springboot.security.base.ResponseEntity;
import com.springboot.security.base.ResponseList;
import com.springboot.security.base.ResponsePage;
import com.springboot.security.entity.OauthClient;
import com.springboot.security.service.OauthClientService;
import com.springboot.security.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 授权客户端管理
 */
@Controller
@RequestMapping(value="/api/oauthClient")
public class SysOauthClientCol {
	

	@Autowired
	private OauthClientService sysOauthClientService;

	
	// 查询
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public ResponsePage<OauthClient> page(@RequestParam(value = "current", defaultValue = "1") int current,
										  @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
										  @RequestParam(value = "search", defaultValue = "") String search) {
		IPage<OauthClient> page = sysOauthClientService.page(current, pageSize, search);
		ResponsePage<OauthClient> res = new ResponsePage<>(page);
		return res;
	}

	// 保存
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> saveOrUpdate(@RequestBody OauthClient sysOauthClient) {

		ResponseEntity<String> res = new ResponseEntity<>();
		try {
			sysOauthClientService.saveOrUpdate(sysOauthClient);
			res.setMsg(CommonConstant.Message.OPTION_SUCCESS);
		} catch (Exception e) {
			LoggerUtils.error(getClass(),"[SysOauthClient 操作失败]" + e.getMessage());
			res.failure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;
	}

	// 删除
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> delete(@RequestBody Map<String, Object>param) {
		ResponseEntity<String> res = new ResponseEntity<>();
		try {
			List<Integer> ids = (List<Integer>) param.get("ids");
			if(ids!=null && ids.size()>0){
				sysOauthClientService.removeByIds(ids);
			}
		} catch (Exception e) {
			LoggerUtils.error(getClass(),"[SysOauthClient delete]" + e.getMessage());
			res.failure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;

	}

	
}
