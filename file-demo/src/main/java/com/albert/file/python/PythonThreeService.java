package com.albert.file.python;

import com.albert.file.utils.JsonUtils;
import com.albert.file.utils.ShellUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangjunwei
 * @date 2021/7/8 2:27 下午
 */
@Service
public class PythonThreeService {

    public void testExecScript() {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 20, 1000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5000));

        Runnable task = () -> {
            String path = "/Users/yangjunwei/IdeaProjects/JavaAdvanced/file-demo/file";
            String command = "python3 CheckTest.py user";
            try {
                ShellUtils.exec(path, command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5000; i++) {
            threadPoolExecutor.execute(task);
        }
    }

    public void teseExecResult() {
        String path = "/Users/yangjunwei/IdeaProjects/JavaAdvanced/file-demo/file";
        String command = "python3 CheckTest.py user";
        StringBuilder execResult = execCommand(path, command);
        String replace = execResult.toString().replace("'", "\"");
        System.out.println(replace);
        ExecResultData execResultData = JsonUtils.toBean(replace, ExecResultData.class);
        System.out.println(JsonUtils.toString(execResultData));
    }


    private StringBuilder execCommand(String filePath, String command) {
        InputStream in = null;
        Reader reader = null;
        BufferedReader bReader = null;
        StringBuilder execResult = null;
        try {
            //指定目录
            File dir = new File(filePath);
            //执行进程命令
            Process process = Runtime.getRuntime().exec(command, null, dir);
            // 记录dos命令的返回信息
            execResult = new StringBuilder();
            // 获取返回信息的流
            in = process.getInputStream();
            reader = new InputStreamReader(in);
            bReader = new BufferedReader(reader);
            for (String res = ""; (res = bReader.readLine()) != null; ) {
                execResult.append(res + "\n");
            }
            process.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (bReader != null) {
                    bReader.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return execResult;
    }

    public static void main(String[] args) {
        PythonThreeService pythonThreeService = new PythonThreeService();
        pythonThreeService.teseExecResult();
    }

}
