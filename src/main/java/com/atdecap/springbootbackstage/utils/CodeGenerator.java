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
    public static void main(String[] args) {
        generate();
    }
        private static void generate(){
            FastAutoGenerator.create("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2b8", "root", "")
                    .globalConfig(builder -> {
                        builder.author("Decap") // 设置作者
                                .enableSwagger() // 开启 swagger 模式
                                .fileOverride() // 覆盖已生成文件
                                .outputDir("D:\\IdeaProjects\\springbootbackstage\\src\\main\\java\\"); // 指定输出目录
                    })
                    .packageConfig(builder -> {
                        builder.parent("com.atdecap.springbootbackstage") // 设置父包名
                                .moduleName(null) // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\IdeaProjects\\springbootbackstage\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径

                    })
                    .strategyConfig(builder -> {
                        builder.entityBuilder().enableLombok();
//                    builder.mapperBuilder().enableMapperAnnotation().build(); 在每个Mapper上加个@mapper注解 用Mapper扫描@MapperScan不用加
                        builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                                .enableRestStyle();  // 开启生成@RestController 控制器
                        builder.addInclude("course") // 设置需要生成的表名
                                .addTablePrefix("t_", "sys_"); // 设置过滤表前缀
                    })
//                    .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                    .execute();
        }


}
