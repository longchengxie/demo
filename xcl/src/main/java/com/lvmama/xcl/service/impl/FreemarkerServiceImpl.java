package com.lvmama.xcl.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lvmama.xcl.service.FreemarkerService;
import com.lvmama.xcl.utils.ResourceUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class FreemarkerServiceImpl implements FreemarkerService {
	protected static boolean isDubgPdf = false;// 开发的时候设置为true，上线设置为false

	public static final String TRAVEL_ECONTRACT_DIRECTORY = "/WEB-INF/resources/econtractTemplate";
	private static final String contractName = "委托服务协议书";
	private static final String templateName = "commissionedServiceAgreementTemplate.ftl";

	@Override
	public String getContractTemplateHtml() {
		String htmlString = null;
		try {
			File directioryFile = initDirectory();
			String absolutePath = directioryFile.getAbsolutePath();
			System.out.println(absolutePath);
			Configuration configuration = initConfiguration(directioryFile);
			Template template = configuration.getTemplate(templateName);
			Map<String, Object> rootMap = new HashMap<String, Object>();
			rootMap.put("absolutePath", "file:///" + absolutePath);//图片单选按钮
			StringWriter sw = new StringWriter();
			template.process(rootMap, sw);
			htmlString = sw.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return htmlString;
	}

	private Configuration initConfiguration(File directioryFile) throws IOException {
		Configuration configuration = null;

		if (directioryFile != null && directioryFile.exists()) {
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setOutputEncoding("UTF-8");
			configuration.setNumberFormat("###");
			configuration.setClassicCompatible(true);
			configuration.setDirectoryForTemplateLoading(directioryFile);
		}

		return configuration;
	}

	private File initDirectory() {
		if (isDubgPdf) {
			return new File("D:/Ted/workspace/vst_order/src/main/webapp/WEB-INF/resources/econtractTemplate/");
		}
		return ResourceUtil.getResourceFile(TRAVEL_ECONTRACT_DIRECTORY);
	}

}
