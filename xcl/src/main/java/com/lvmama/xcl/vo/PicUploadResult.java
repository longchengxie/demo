package com.lvmama.xcl.vo;

public class PicUploadResult {
    
    private Integer error;//上传图片的状态,0:是图片   1:不是图片
    
    private String url;//上传图片的绝对路径
    
    private String width;//图片的宽度
    
    private String height;//图片的高度

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    
    

}
