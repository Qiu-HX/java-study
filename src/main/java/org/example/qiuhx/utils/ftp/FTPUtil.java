package org.example.qiuhx.utils.ftp;

import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FTPUtil
 * @Description: sun.net下 API
 * @Author: qhengxian
 * @Date: 2021/06/18 17:02
 */
public class FTPUtil {
    //FTP服务器IP地址
    public final static String FTP_HOST = "192.168.173.47";

    //FTP服务器端口
    public final static int FTP_PORT = 21;

    //FTP服务器用户名
    public final static String FTP_USER = "ftp";

    //密码
    public final static String FTP_PASSWORD = "ftp";


    /**
     * 连接FTP服务器
     *
     * @return ftp客户端
     */
    public static FtpClient getConnect() {
        try {
            FtpClient ftpClient = FtpClient.create(FTP_HOST);
            ftpClient.login(FTP_USER, FTP_PASSWORD.toCharArray());
            return ftpClient;
        } catch (FtpProtocolException e) {
            e.printStackTrace();
            System.out.println("Connect to FTP Server fail!");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Connect to FTP Server fail!");
            return null;
        }
    }

    /**
     * ftp 文件上传
     *
     * @param path     上传文件的路径
     * @param fileName 上传文件名称
     * @return 上传成功返回true, 否则返回false
     */
    public static boolean ftpUpload(String path, String fileName) {
        TelnetOutputStream os = null;
        FileInputStream is = null;
        FtpClient ftpClient = getConnect();
        try {
            ftpClient.setBinaryType();
            os = (TelnetOutputStream) ftpClient.putFileStream(fileName, true);
            is = new FileInputStream(new File(path));
            byte[] buffer = new byte[1024];
            int c;
            while ((c = is.read(buffer)) != -1) {
                os.write(buffer, 0, c);
            }
            System.out.println("Upload Success!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Upload fail!");
            return false;
        } finally {
            try {
                if (is != null) is.close();
                if (os != null) os.close();
                if (ftpClient != null) ftpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ftp file download
     *
     * @param path     下载文件的保存路径
     * @param fileName 下载文件名称
     * @return 下载成功返回true, 否则返回false
     */
    public static boolean ftpDownload(String path, String fileName) {
        FileInputStream is = null;
        FileOutputStream os = null;
        FtpClient ftpClient = getConnect();
        try {
            is = (FileInputStream) ftpClient.getFileStream(fileName);
            os = new FileOutputStream(new File(path));
            byte[] buffer = new byte[1024];
            int c;
            while ((c = is.read(buffer)) != -1) {
                os.write(buffer, 0, c);
            }
            System.out.println("Download Success!");
            return true;
        } catch (FtpProtocolException e) {
            e.printStackTrace();
            System.out.println("Download fail!");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Download fail");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ftpClient != null) ftpClient.close();
                if (is != null) is.close();
                if (os != null) os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 遍历FTP目录文件
     *
     * @param path 遍历目录的路径
     * @return 遍历的文件名列表
     */
    public static List<String> getFtpFileList(String path) {
        List<String> filenames = new ArrayList<>();
        BufferedReader ds = null;
        FtpClient ftpClient = getConnect();
        try {
            ds = new BufferedReader(new InputStreamReader(ftpClient.nameList(path), "ISO-8859-1"));
            String line = "";
            while ((line = ds.readLine()) != null) {
                line = new String(line.getBytes("ISO-8859-1"), "GBK");
                String name[] = line.split("/");
                filenames.add(name[name.length - 1]);
            }
            return filenames;
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ftpClient != null) ftpClient.close();
                if (ds != null) ds.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filenames;
    }
}
