package com.atdecap.springbootbackstage.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author Decap
 * @BelongsProject: IntelliJ IDEA
 * @BelongsPackage: com.atdecap.springbootbackstage.utils
 * @create 2022-05-2022/5/311:47
 * @desc
 **/
public class CodeGenerator {
    public static void main(String[] args) {}
        private void generate(){
            FastAutoGenerator.create("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2b8", "root", "")
                    .globalConfig(builder -> {
                        builder.author("Decap") // 设置作者
                                .enableSwagger() // 开启 swagger 模式
                                .fileOverride() // 覆盖已生成文件
                                .outputDir("D:\\IdeaProjects\\springbootbackstage\\src\\main\\java\\"); // 指定输出目录
                    })
                    .packageConfig(builder -> {
                        builder.parent("com/atdecap/springbootbackstage") // 设置父包名
                                .moduleName("") // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.mapper, "D:\\IdeaProjects\\springbootbackstage\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                    })
                    .strategyConfig(builder -> {
                        builder.addInclude("user") // 设置需要生成的表名
                                .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                    })
                    .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                    .execute();
        }


}
