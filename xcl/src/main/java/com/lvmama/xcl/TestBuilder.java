package com.lvmama.xcl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestBuilder {
	private static Configuration configuration;

	public void init() {
		if (configuration == null) {
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setOutputEncoding("UTF-8");
			configuration.setNumberFormat("###");
			configuration.setClassicCompatible(true);
			configuration.setClassForTemplateLoading(TestBuilder.class, "/cert");
		}
	}

	public String makeContent() throws IOException, TemplateException {
		StringWriter sw = new StringWriter();
		makeCertContent(sw);
		String writerString = sw.toString();
		sw.close();
		return writerString;
	}

	private void makeCertContent(StringWriter sw) throws IOException, TemplateException {
		init();
		Template template = configuration.getTemplate(joinPath());
		Map<String,Object> rootMap=new HashMap<String, Object>();
		rootMap.put("aa", "123456789");
		template.process(rootMap, sw);
	}

	private String joinPath() {
		StringBuffer sb=new StringBuffer();
		sb.append("pdf");
		sb.append("/");
		sb.append("pdf1");
		sb.append(".html");
		return sb.toString().toLowerCase();
	}

}
