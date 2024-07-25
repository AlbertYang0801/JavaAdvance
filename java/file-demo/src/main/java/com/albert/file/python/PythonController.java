package com.albert.file.python;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunwei
 * @date 2021/7/8 2:31 下午
 */
@RestController
public class PythonController {

    @Autowired
    PythonThreeService pythonThreeService;

    @GetMapping("/pythontest")
    public void testPython(){
        pythonThreeService.testExecScript();
    }


}
