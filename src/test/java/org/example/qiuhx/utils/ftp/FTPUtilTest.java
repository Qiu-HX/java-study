package org.example.qiuhx.utils.ftp;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FTPUtilTest {

    @Test
    public void ftpUpload() {
        boolean b = FTPUtil.ftpUpload("C:/Users/max/Downloads/ftp/中文名称-EnglishName.txt",
                "/pub/test/qhx_test/中文名称-EnglishName.txt");
        System.out.println("文件上传：" + b);
        Assert.assertTrue(b);
    }

    @Test
    public void ftpDownload() {
        boolean b = FTPUtil.ftpDownload("C:\\Users\\max\\Downloads\\ftp\\中文名称-EnglishName-download.txt",
                "/pub/test/qhx_test/中文名称-EnglishName.txt");
        System.out.println("文件上传：" + b);
        Assert.assertTrue(b);
    }

    @Test
    public void getFtpFileList() {
        List<String> ftpFileList = FTPUtil.getFtpFileList("/pub/test");
        System.out.println(ftpFileList.toString());
    }
}