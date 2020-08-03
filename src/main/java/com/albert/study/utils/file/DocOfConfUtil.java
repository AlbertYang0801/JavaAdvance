package com.albert.study.utils.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author yjw
 * @date 2020/8/3 21:44
 */
@Slf4j
public class DocOfConfUtil {

    public static String getContentInPath(String path) {
        File file = new File(path);
        try {
            return FileUtils.readFileToString(file, Charset.forName("UTF-8"));
        } catch (IOException e) {
            log.error("read user message from file error", e);
        }
        return null;
    }


}
