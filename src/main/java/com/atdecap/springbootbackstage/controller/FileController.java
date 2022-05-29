package com.atdecap.springbootbackstage.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.atdecap.springbootbackstage.common.Result;
import com.atdecap.springbootbackstage.entity.Files;
import com.atdecap.springbootbackstage.mapper.FileMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author Decap
 * @BelongsProject: IntelliJ IDEA
 * @BelongsPackage: com.atdecap.springbootbackstage.controller
 * @create 2022-05-2022/5/1820:03
 * @desc 文件上传相关接口
 **/
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}") //把配置文件引进来
    private String fileUploadPath;

    @Resource
    private FileMapper fileMapper;
    /**
     * 文件上传接口
     * @param file 前端传递过来的文件
     * @return
     * @throws IOException
     */
    @PostMapping("upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();
       /* 整理代码逻辑
        //先存储到磁盘
        File uploadParentFile = new File(fileUploadPath);
        // 判断配置文件目录是否存在，若不存在创建一个新的文件目录
        if (!uploadParentFile.exists()){
            uploadParentFile.mkdirs();
        }*/
        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()) {
            parentFile.mkdirs();
        }

        String url;
        //上传文件到磁盘
        file.transferTo(uploadFile);
        // 获取文件的md5
        String  md5 = SecureUtil.md5(uploadFile);
//        // 获取文件的md5
//        String md5 = SecureUtil.md5(file.getInputStream());
        // 从数据库查询是否存在相同的记录
        Files dbFiles = getFileByMd5(md5);
        if (dbFiles != null) { // 文件已存在
            url = dbFiles.getUrl();
           // 由于文件已存在，所以删除刚才上传的文件
            uploadFile.delete();
        } else {
            // 上传文件到磁盘
//            file.transferTo(uploadFile);
            // 数据库若不存在重复文件，则不删除刚才上传的文件
            url = "http://localhost:8888/file/" + fileUUID;
        }
      /* 不删了留着吸取教训(花了1.5h) if的条件错了  uploadFile每次都是新的因为源头的originalFilename每次都不一样 String type = FileUtil.extName(originalFilename);
      File uploadFile = new File(fileUploadPath + fileUUID);
        // 判断配置文件目录是否存在，若不存在创建一个新的文件目录
        if (!(uploadFile.getParentFile()).exists()){
            (uploadFile.getParentFile()).mkdirs();
        }
        String md5;
        String url;
        //当文件存在时 再获取文件的md5
        if (uploadFile.exists()){
            //获取文件的md5,通过对比md5避免重复上传相同内容的文件
            md5 = SecureUtil.md5(uploadFile);
            //从数据库查询是否存在相同的记录
            Files dbfiles = getFileByMd5(md5);
            //获取文件的url

            if (dbfiles != null){ //查询到的dbfiles不是空的话 就把数据库url传值给文件的url
                url = dbfiles.getUrl();
            }
            else {
                //把获取到的文件存储到磁盘目录
                file.transferTo(uploadFile);
                url = "http://localhost:8888/file/" + fileUUID;
            }
        }else {//文件不存在时候是第一次上传 存储完之后再获取md5再定义新的url
            //把获取到的文件存储到磁盘目录
            file.transferTo(uploadFile);
            md5 = SecureUtil.md5(uploadFile);
            url = "http://localhost:8888/file/" + fileUUID;
        }
*/
    /*   避免过于臃肿在下面写个getFileByMd5方法
        //查询文件的md5是否存在
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5",md5);
        Files files = fileMapper.selectOne(queryWrapper);
        */

        //存储到数据库
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size/1024);
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        fileMapper.insert(saveFile);
        return url;
    }

    /**
     * 文件下载接口   http://localhost:9090/file/{fileUUID}
     * @param fileUUID
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }
    /**
     * 通过文件的md5查询文件
     * @param md5
     * @return
     */
    private Files getFileByMd5(String md5) {
        // 查询文件的md5是否存在
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<Files> filesList = fileMapper.selectList(queryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Files files) {
        return Result.success(fileMapper.updateById(files));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Files files = fileMapper.selectById(id);
        files.setIsDelete(true);
        fileMapper.updateById(files);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        // select * from sys_file where id in (id,id,id...)
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<Files> files = fileMapper.selectList(queryWrapper);
        for (Files file : files) {
            file.setIsDelete(true);
            fileMapper.updateById(file);
        }
        return Result.success();
    }
    /**
     * 分页查询接口
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {

        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        // 查询未删除的记录
        queryWrapper.eq("is_delete", false);
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return Result.success(fileMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
