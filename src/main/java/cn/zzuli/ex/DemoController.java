package cn.zzuli.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试
 * 1. 从浏览器请求到 Java方法中
 * 2. 从浏览器请求Java方法并接受参数1
 * 3. 从浏览器请求Java方法并接受参数2
 * 4. 请求到方法之后跳转到指定的网页（两网页跳转）
 * 5. 请求到指定网页周并传递一个参数到网页中展示
 */

@Controller // 注解
@RequestMapping("/demo")
public class DemoController {
    /**
     * @Controller: 控制层, Spring框架的注解，意思是将当前类加载到spring容器中被Spring识别
     * @RequestMapping("/demo")：给当前类起一个访问的名字

     */
    //1. 从浏览器请求到 Java方法中
    @RequestMapping("/t0")
    public void t0(){
        System.out.println("---------> t0");
    }

    // 2. 解决报错问题
    @RequestMapping("/t1")
    public String t1(){
        System.out.println("---------> t1");
        return "success";
    }

    // 3. 接受普通参数
    @RequestMapping("/t2")
    public String t2(String name, int age){
        System.out.println("---------> t2" + "--------> name:" + name + "------> age : " + age);
        return "success";
    }

    // 4. 接受路径参数
    @RequestMapping("/t3/{id}")
    public String t3(@PathVariable String id){
        System.out.println("---------> t3" + id);
        return "success";
    }

    // 5. 测试返回信息
    @RequestMapping("/t4")
    public String t4(Model model){
        String message = "千金散尽还复来";
        model.addAttribute("mm", message);
        System.out.println("---------> t4");
        return "success";
    }
}
