# @RequestBody配合@Valid校验入参参数

RequestBody 注解的主要作用就是用于接收前端的参数，当我们使用 post 请求的时候，我们会将参数放在 request body 中，此时我们就需要在Controller 的方法的参数前面加上 @RequestBody 用来接受到前端传过来的 request body 中的值

## 使用@RequestBody配合@Valid 案例

**自定义一个Controller**

```java
import com.example.demo.pojo.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController; 
import javax.validation.Valid;

@RestController
public class ValiController {
    @PostMapping(value = "/",produces = "application/json;charset=UTF-8")
    public User vali(@RequestBody @Valid User user){
        return user;
    }
}
```

**自定义实体类**

```java
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {
    @NotBlank(message = "这个姓名不能为空")
    private String name;
    @NotNull(message = "这个年龄不能为空")
    @Max(value = 105,message = "太大了")
    private Integer age;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public Integer getAge() {
        return age;
    }
 
    public void setAge(Integer age) {
        this.age = age;
    }
}
```

**自定义全局异常处理器**

```java
import com.example.demo.pojo.ErrorMsg;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
 
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerContrller { 
 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public List<ErrorMsg> exception(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<ErrorMsg> errorMsgs = new ArrayList<>();
 
        allErrors.forEach(objectError -> {
            ErrorMsg errorMsg = new ErrorMsg();
            FieldError fieldError = (FieldError)objectError;
            errorMsg.setField(fieldError.getField());
            errorMsg.setObjectName(fieldError.getObjectName());
            errorMsg.setMessage(fieldError.getDefaultMessage());
            errorMsgs.add(errorMsg);
        });
        return errorMsgs;
    }
}
```

**PostMan测试下**

```json
[
    {
        "field": "name",
        "message": "这个姓名不能为空",
        "objectName": "user"
    },
    {
        "field": "age",
        "message": "这个年龄不能为空",
        "objectName": "user"
    }
]
```

## 附录

**@Valid 注解类型的使用：**

* ​`@Null`​：限制只能为null
* ​`@NotNull`​：限制必须不为null
* ​`@AssertFalse`​：限制必须为false
* ​`@AssertTrue`​：限制必须为true
* ​`@DecimalMax(value)`​：限制必须为一个不大于指定值的数字
* ​`@DecimalMin(value)`​：限制必须为一个不小于指定值的数字
* ​`@Digits(integer,fraction)`​：限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction
* ​`@Future`​：限制必须是一个将来的日期
* ​`@Max(value)`​：限制必须为一个不大于指定值的数字
* ​`@Min(value)`​：限制必须为一个不小于指定值的数字
* ​`@Past`​：限制必须是一个过去的日期
* ​`@Pattern(value)`​：限制必须符合指定的正则表达式
* ​`@Size(max,min)`​：限制字符长度必须在min到max之间
* ​`@Past`​：验证注解的元素值（日期类型）比当前时间早
* ​`@NotEmpty`​：验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0）
* ​`@NotBlank`​：验证注解的元素值不为空（不为null、去除首位空格后长度为0），不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的空格
* ​`@Email`​：验证注解的元素值是Email，也可以通过正则表达式和flag指定自定义的email格式

## 参考

* [使用@RequestBody配合@Valid校验入参参数](https://www.jb51.net/article/240825.htm)
* [注解RequestBody详解](https://blog.csdn.net/weixin_40159122/article/details/103292809)
