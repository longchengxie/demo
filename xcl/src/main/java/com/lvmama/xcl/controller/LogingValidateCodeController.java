package com.lvmama.xcl.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Producer;
/**
 * 登录生成验证码
 * @author xiechenglong
 *
 */
@Controller
public class LogingValidateCodeController {
	@Autowired
	private Producer captchaProducer;

	@RequestMapping("/login/validateCode")
	public void validateCode(HttpServletRequest req, HttpServletResponse res) {
		try {
			res.setHeader("Pragma", "No-cache");
			res.setHeader("Cache-Control", "no-cache");
			res.setDateHeader("Expires", 0);
			
			String capText = this.captchaProducer.createText();
			String capImageValue = capText;

			Double rand = Math.ceil(Math.random() * 10);
			if(rand>2){
				String str1 = capText.substring(0, 1);
				String str2 = capText.substring(2, 3);
				Integer result = Integer.valueOf(str1) + Integer.valueOf(str2);
				capText = result.toString();
				capImageValue = str1 + "+" + str2 +"=?";
			}
			BufferedImage bi = this.captchaProducer.createImage(capImageValue);
			//EbkServletUtil.putSession(req, res, EbkConstant.SESSION_EBOOKING_VALIDATE_CODE,capText);
			ImageIO.write(bi, "JPEG",res.getOutputStream());

			res.getOutputStream().flush();
			res.getOutputStream().close();
			res.flushBuffer();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
