# geekbang-lessons
极客时间课程工程
<br/><br/><br/>
## homework

### 8月14日作业

<br/>

#### org.springframework.validation.Validator
##### spring内的定义的校验接口里面有两个方法：
###### boolean supports(Class<?> clazz)：该Validator实现类能否校验该clazz类；
###### void validate(Object target, Errors errors)：校验target对象，将不满足条件的信息保存入errors对象内。

<br/>

####org.springframework.validation.annotation.Validated
##### spring容器内的Validator实例会校验添加了@Validated注释的类实例完成后，其中添加了 Bean Validation各类校验注释是否满足校验。
###### 示例：spring内Bean后置处理类MethodValidationPostProcessor，将该类（实例化时需要注入Validator的实例）添加入spring容器，则该bean后置处理器会调用其Validator成员变量校验添加了@Validated注释的bean是否满足校验条件。