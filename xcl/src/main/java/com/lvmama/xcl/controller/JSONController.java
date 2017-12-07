package com.lvmama.xcl.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvmama.xcl.comm.ComLog;
import com.lvmama.xcl.utils.JSONOutput;
import com.lvmama.xcl.vo.BuyInfo;
import com.lvmama.xcl.vo.BuyInfo.Item;
import com.lvmama.xcl.vo.OptionItem;

import lombok.Data;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 测试json处理数据，响应数据
 * 
 * @author xiechenglong
 *
 */
@Controller
@RequestMapping("/json")
public class JSONController {
	
	private static final Log LOG = LogFactory.getLog(JSONController.class);

	@RequestMapping("/test1")
	@ResponseBody
	public void test1(@RequestBody List<ComLog> logs) {
		for (ComLog comLog : logs) {
			System.out.println(comLog.toString());
		}
		System.out.println(logs.toString());
	}

	@RequestMapping("/test2")
	@ResponseBody
	public void test2(ComLog logs) {
		System.out.println(logs.toString());
	}
	
	/**
	 * 直接返回字符串会乱码，返回对象，list，map中包含中文不会乱码
	 * @return
	 */
	@RequestMapping(value="json")
	@ResponseBody
	public Object json(){
		//Demo demo = new Demo();
		//demo.id = 11;
		//demo.name = "lvmama驴妈妈";
		String str = "驴妈妈";
		return str;
	}
	
	@RequestMapping(value="json1")
	public void json1(HttpServletResponse res){
		JSONArray jsonArray = new JSONArray();
		for (int i=0; i<2 ; i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", i);
			jsonObject.put("text", i);
			jsonArray.add(jsonObject);
		}
		JSONOutput.writeJSON(res, jsonArray);
	}
	/**
	 * 中文不会乱码
	 * @param res
	 */
	@RequestMapping(value="json11")
	public void json11(HttpServletResponse res){
		JSONArray jsonArray = new JSONArray();
		for (int i=0; i<2 ; i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "fuck啊啊"+i);
			jsonObject.put("text", "驴妈妈"+i);
			jsonArray.add(jsonObject);
		}
		JSONOutput.writeJSON(res, jsonArray);
	}
	
	@RequestMapping(value="json2")
	@ResponseBody
	public Object json2(){
		JSONArray jsonArray = new JSONArray();
		for (int i=0; i<2 ; i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", i);
			jsonObject.put("text", i);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	/**
	 * 中文不会乱码
	 * @return
	 */
	@RequestMapping(value="json22")
	@ResponseBody
	public Object json22(){
		JSONArray jsonArray = new JSONArray();
		for (int i=0; i<2 ; i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "fuck啊啊fuck啊啊"+i);
			jsonObject.put("text", "驴妈妈驴妈妈"+i);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@RequestMapping(value="json3")
	public String json3(Model model){
		return "/json/json3";
	}
	
	/**
	 * 中文不会乱码
	 * @param res
	 */
	@RequestMapping(value="get_org_list_by_level")
	public void getOrgListByLevel(HttpServletResponse res){
		List<OptionItem> items = new ArrayList<OptionItem>();
		for(int i=0;i<3;i++){
			OptionItem item = new OptionItem();
			item.setValue(i + "_" + "飞驴湾" + i);
			item.setLabel("飞驴湾"+i);
			items.add(item);
		}
		sendAjaxResultByJson(JSONArray.fromObject(items).toString(), res);
	}
	
	/**
	 * 发送Ajax请求结果json
	 * @param res 
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendAjaxResultByJson(String json, HttpServletResponse res) {
		res.setContentType("application/json;charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = res.getWriter();
			out.write(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="json4")
	public String json4(Model model){
		return "/json/json4";
	}
	
	@RequestMapping(value="encodeURIJSON")
	@ResponseBody
	public BuyInfo encodeURIJSON(HttpServletRequest request){
		try {
			Map<String, Class> mymap = new HashMap<String, Class>();
			mymap.put("itemList", Item.class);
			String orderJsonString = request.getParameter("orderJson");
			String decodeParam = null;
			decodeParam = URLDecoder.decode(orderJsonString, "UTF-8");
			decodeParam = StringEscapeUtils.unescapeJava(decodeParam);
			decodeParam = URLDecoder.decode(decodeParam, "UTF-8");//针对页面请求进行2次编码这里要处理二次编码
			LOG.info("两次转码后orderJson的值为："+URLDecoder.decode(decodeParam, "UTF-8")+"  request.getCharacterEncoding():"+request.getCharacterEncoding());
			if(decodeParam != null){
				decodeParam = decodeParam.replaceAll("\\\\","");
				decodeParam = decodeParam.replaceAll("\"\\{","\u007B");
				decodeParam = decodeParam.replaceAll("\"\\}\"","\"}");
			}
			JSONObject jsonObject = JSONObject.fromObject(decodeParam);
			BuyInfo form = (BuyInfo) JSONObject.toBean(jsonObject, BuyInfo.class, mymap);
			System.out.println(form);
			return form;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new BuyInfo();
	}
	
	@RequestMapping(value="encodeURIJFASTSON")
	@ResponseBody
	public BuyInfo encodeURIFASTJSON(HttpServletRequest request){
		try {
			
			String orderJsonString = request.getParameter("orderJson");
			String decodeParam = null;
			decodeParam = URLDecoder.decode(orderJsonString, "UTF-8");
			decodeParam = StringEscapeUtils.unescapeJava(decodeParam);
			decodeParam = URLDecoder.decode(decodeParam, "UTF-8");//针对页面请求进行2次编码这里要处理二次编码
			LOG.info("两次转码后orderJson的值为："+URLDecoder.decode(decodeParam, "UTF-8")+"  request.getCharacterEncoding():"+request.getCharacterEncoding());
			if(decodeParam != null){
				decodeParam = decodeParam.replaceAll("\\\\","");
				decodeParam = decodeParam.replaceAll("\"\\{","\u007B");
				decodeParam = decodeParam.replaceAll("\"\\}\"","\"}");
			}
			BuyInfo buyInfo = com.alibaba.fastjson.JSONObject.parseObject(decodeParam, BuyInfo.class);
			System.out.println(buyInfo);
			return buyInfo;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new BuyInfo();
	}
	@Data
	class Demo{
		private Integer id;
		private String name;
	}
	

	public static void main(String[] args) {
		String st = "["
						+ "{'msgId': '1e24c203-3e5f-4586-b0f9-4d7f9e273c56[192.168.30.186]','logId': 7967006,'logType': 'SUPP_SUPPLIER_CONTRACT_STATUS','logName': '修改合同状态','content': '修改了合同：【2016-09-12广东经律论酒店管理有限公司合作协议】，修改内容：,合同经办人::[原来值：刘冬玲,新值：蔡颖瑜],产品经理::[原来值：刘宇霆,新值：利国欢]','memo': null,'objectType': 'SUPP_SUPPLIER_CONTRACT','objectId': 21080,'parentId': 21080,'parentType': 'SUPP_SUPPLIER','contentType': 'VARCHAR','operatorName': 'lv13727','createTime': 1505313207000,'sysName': null,'receiveTime': 1505313207000,'logTime': 1505313207000,'tableName': null},"
						+ "{'msgId': '5a03c5ef-5d0e-4ebf-b0a1-80702ad0a995[172.20.4.194]','logId': 7939354,'logType': 'SUPP_SUPPLIER_CONTRACT_STATUS','logName': '合同审核','content': '财务驳回，驳回理由：1：结算对象名称格式：供应商名称-BU-结算类型-X X指某一产品\r\n2：发票情况请按照实际情况填写\r\n','memo': null,'objectType': 'SUPP_SUPPLIER_CONTRACT','objectId': 16800,'parentId': 16800,'parentType': 'SUPP_SUPPLIER','contentType': 'VARCHAR','operatorName': 'lv10904','sysName': null,'receiveTime': 1505308226000,'logTime': 1505308226000,'tableName': null}"
						+ "]";
		List<ComLog> list = com.alibaba.fastjson.JSONObject.parseArray(st, ComLog.class);
		System.out.println(list);
	}
}
