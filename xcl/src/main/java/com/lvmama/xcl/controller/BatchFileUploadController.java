package com.lvmama.xcl.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lvmama.xcl.vo.ResultMessage;


public class BatchFileUploadController {
	
	private static final Log LOGGER = LogFactory.getLog(BatchFileUploadController.class);
	  /**
     * 批量上传
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/materials")
    @ResponseBody
    public Object uploadMaterials(MultipartHttpServletRequest req, String parentType, Long parentId, Long subCompanyId,
            @RequestParam(value = "delIds[]", required = false) List<Long> delIds, HttpServletResponse res,
            String serverType) throws Exception {
        LOGGER.info("start method<MaterialsAction#uploadMaterials>");
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("parentType:" + parentType + ";parentId:" + parentId + ";delIds:" + delIds);
        }

        if (StringUtils.isEmpty(serverType)) {
            LOGGER.error("Invalid server type.");
            //return new ResultMessage(ResultMessage.ERROR, "服务类型不存在");
        }
        List<MultipartFile> tempMaterialsList = req.getFiles("materials");
        List<MultipartFile> tempInternalFileList = req.getFiles("internalFile");
        //List<Materials> materialsList = new ArrayList<Materials>();
        try {
            for (MultipartFile file : tempMaterialsList) {
                /*if(file.getSize() == 0) {
                    continue;
                }
                Materials object = new Materials();
                uploadFile(file, serverType, object);
                object.setInternalFlag(Constants.INVALID_STATUS);
                object.setObjectId(parentId);
                object.setObjectType(parentType);
                materialsList.add(object);*/
            }
            for (MultipartFile file : tempInternalFileList) {
                /*if(file.getSize() == 0) {
                    continue;
                }
                Materials object = new Materials();
                uploadFile(file, serverType, object);
                object.setInternalFlag(Constants.VALID_STATUS);
                object.setObjectId(parentId);
                object.setObjectType(parentType);
                materialsList.add(object);*/
            }
/*            // 将上传好的数据文件名等保存到数据库，如果delIds存在则删除这个List中包含的数据
            materialsService.updateMaterials(materialsList, delIds, this.getLoginUserId(), parentType, parentId, subCompanyId);
*/        } catch (Exception e) {
            LOGGER.error("Error occurred while uploading materials.", e);
            //return new ResultMessage(ResultMessage.ERROR, "系统内部异常");
        }
        //return new ResultMessage(ResultMessage.SUCCESS, "保存成功");
		return "";
    }
    
    
    /**
	 * 批量上传
	 * 
	 * @param req
	 * @param suppSupplierAuditId
	 * @return
	 */
	@RequestMapping(value = "addSuppQualAudit")
	@ResponseBody()
	public ResultMessage addSuppQualAudit(MultipartHttpServletRequest req, Long suppSupplierAuditId, Long supplierId, Long num, String serverType) {
		//PermUser permUser = getLoginUser();
		for (int i = 0; i < num; i++) {
			MultipartFile file = req.getFile("materials" + i);
			//SuppQualAttach suppQualAttach = null;
			if (file.getSize() > 0) {// 如果资质附件为空则不写入资质附件表中
				/*suppQualAttach = new SuppQualAttach();
				String qualType = req.getParameter("qualType" + i);// 资质副本类型
				suppQualAttach.setQualType(qualType);
				try {
					uploadFile(file, serverType, suppQualAttach);
				} catch (Exception e) {
					LOG.error("上传附件失败", e);
					if("BusinessException".equals(e.getClass().getSimpleName())){
						return new ResultMessage("error","文件不可以大于10M");
					}
					return new ResultMessage("error","上传附件失败");
				}*/
			}

			/*SuppQual suppQual = new SuppQual();
			if(supplierId != null){
				suppQual.setSupplierId(supplierId);
			}
			suppQual.setQualStatus(req.getParameter("qualStatus" + i));// 资质状态
			suppQual.setEndTime(DateUtil.stringToDate(req.getParameter("endTime" + i), DateUtil.SIMPLE_DATE_FORMAT));// 资质到期时间
			suppQual.setManagerId(permUser.getUserId());//资质录入人
			// 供应商审核资质
			SuppQualAudit suppQualAudit = new SuppQualAudit();
			suppQualAudit.setSuppSupplierAuditId(suppSupplierAuditId);
			suppQualAudit.setSuppQualInfo(JSONObject.toJSONString(suppQual));
			suppQualAudit.setCreateUser(permUser.getUserName());
			suppQualAudit.setCreateTime(new Date());
			suppQualAudit.setUpdateTime(new Date());

			if (suppQualAttach != null) {// 如果资质附件为空则不写入资质附件表中
				SuppQualAttachAudit suppQualAttachAudit = new SuppQualAttachAudit();// 供应商审核资质附件
				suppQualAttachAudit.setSuppQualAttachInfo(JSONObject.toJSONString(suppQualAttach));
				suppQualAttachAudit.setCreateUser(permUser.getUserName());
				suppQualAttachAudit.setCreateTime(new Date());
				suppQualAttachAudit.setUpdateTime(new Date());
				suppQualAudit.setSuppQualAttachAudit(suppQualAttachAudit);// 将资质附件封装到资质中
			}
			try {
				suppQualAuditService.saveSuppQualAudit(suppQualAudit);// 保存资质审核
			} catch (Exception e) {
				LOGGER.error("SuppQualAuditAction method addSuppQualAudit error:", e);
				return ResultMessage.ADD_FAIL_RESULT;
			}*/

		}
		return ResultMessage.ADD_SUCCESS_RESULT;
	}
	
	 @RequestMapping("filesUpload")  
	    public String filesUpload(@RequestParam("files") MultipartFile[] files) {  
	        //判断file数组不能为空并且长度大于0  
	        if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
	            for(int i = 0;i<files.length;i++){  
	                MultipartFile file = files[i];  
	                //保存文件  
	                //saveFile(file);  
	            }  
	        }  
	        // 重定向  
	        return "redirect:/list.html";  
	    }  
	 
	 /**
	  * 流方式性能慢 
	  * @param files
	  * @param request
	  * @return
	  */
	 @RequestMapping("/upload"   )  
	    public String addUser(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request){  
	          
	        for(int i = 0;i<files.length;i++){  
	            System.out.println("fileName---------->" + files[i].getOriginalFilename());  
	          
	            if(!files[i].isEmpty()){  
	                int pre = (int) System.currentTimeMillis();  
	                try {  
	                    //拿到输出流，同时重命名上传的文件  
	                    FileOutputStream os = new FileOutputStream("H:/" + new Date().getTime() + files[i].getOriginalFilename());  
	                    //拿到上传文件的输入流  
	                    FileInputStream in = (FileInputStream) files[i].getInputStream();  
	                      
	                    //以写字节的方式写文件  
	                    int b = 0;  
	                    while((b=in.read()) != -1){  
	                        os.write(b);  
	                    }  
	                    os.flush();  
	                    os.close();  
	                    in.close();  
	                    int finaltime = (int) System.currentTimeMillis();  
	                    System.out.println(finaltime - pre);  
	                      
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                    System.out.println("上传出错");  
	                }  
	        }  
	        }  
	        return "/success";  
	    }  
}
