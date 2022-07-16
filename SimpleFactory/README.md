# 实践-利用反射和泛型优化简单工厂

## 需求

我有十几个类型的数据需要发送给下游应用,每种类型都有一些公共字段;所以我把公共字段都抽取到父类中

1. 创建数据类型的对象

2. 给创建的对象,把公共字段都赋上值

   ```java
   **
    * @author pishi
    * @description: 消息父类
    * @date 2022年07月16日 12:26
    */
   public class MessageVo {
   
       /**
        * 业务唯一主键
        */
       private String uniqueKey;
   
       /**
        * 发送时间
        */
       private Date sendTime;
   
   
       /**
        * 操作标识
        */
       private String operateFlag;
   }
   
   /**
    * @author pishi
    * @description: 检验结果类型
    * @date 2022年07月16日 16:15
    */
   public class JYMessageVo extends MessageVo{
   
       /**
        * 检验结果
        */
       private Boolean judgeResult;
   }
   
   /**
    * @author pishi
    * @description: 价格类型
    * @date 2022年07月16日 16:15
    */
   public class JGMessageVo extends MessageVo{
   
       /**
        * 检验结果
        */
       private Double price;
   }
   ```

   ## 简单工厂

   ```java
   
   /**
    * @author pishi
    * @description: 简单工厂
    * @date 2022年07月16日 12:25
    */
   public class SimpleFactory {
   
       public static MessageVo createMessage(String type){
           MessageVo vo = null ;
           if ("JY".equals(type))
           {
               vo = new JYMessageVo();
           }else if ("JG".equals(type))
           {
               vo = new JGMessageVo();
           }
   
   
           //region 设置公用字段的值
           if (vo != null)
           {
               vo.setUniqueKey(UUID.randomUUID().toString());
               vo.setSendTime(new Date());
               vo.setOperateFlag("N");
           }
           //endregion
           return vo;
       }
     
      public static void main(String[] args) {
           JYMessageVo jy = (JYMessageVo)SimpleFactory.createMessage("JY");
           jy.setJudgeResult(true);
           System.out.println(jy);
   
       }
   
   }
   ```

   简单工厂虽然符合我的需求,把创建对象和初始化公共字段的数据都完成了;

   但是 需要维护简单工厂里面的 那一串判断逻辑,而且必须依赖 `type` 

   ## 反射

   ```java
   
   /**
    * @author pishi
    * @description: 利用反射优化简单工厂
    * @date 2022年07月16日 16:35
    */
   public class ReflexSimpleFactory {
   
       public static MessageVo createMessage(Class clazz){
           MessageVo vo = null;
           try {
               vo = (MessageVo)Class.forName(clazz.getName()).getDeclaredConstructor().newInstance();
           } catch (Exception e) {
               e.printStackTrace();
           }
   
           //region 设置公用字段的值
           if (vo != null)
           {
               vo.setUniqueKey(UUID.randomUUID().toString());
               vo.setSendTime(new Date());
               vo.setOperateFlag("N");
           }
           //endregion
           return vo;
       }
     
     
    public static void main(String[] args) {
      			//每次都需要自行强转回需要的类型
           JYMessageVo jy = (JYMessageVo) ReflexSimpleFactory.createMessage(JYMessageVo.class);
           jy.setJudgeResult(true);
           System.out.println(jy);
       }
   }
   ```

   利用反射创建对象可以 不依赖 `type` ,优化了工厂里面的判断逻辑;

   但是还有个槽点, 每次调用完之后需要 自行强转回需要的类型  ``JYMessageVo jy = (JYMessageVo) ReflexSimpleFactory.createMessage(JYMessageVo.class);``

   ## 泛型优化类型的问题

   ```java
   /**
    * @author pishi
    * @description: 利用泛型优化简单工厂
    * @date 2022年07月16日 16:35
    */
   public class GenericsSimpleFactory {
   
       //利用范型 让调用时不必强转，且限定了 MessageVo才能使用此工厂
       public static<T extends MessageVo> T  createMessage(Class<T> clazz){
           T vo = null;
           try {
               vo = (T)Class.forName(clazz.getName()).getDeclaredConstructor().newInstance();
           } catch (Exception e) {
               e.printStackTrace();
           }
   
           //region 设置公用字段的值
           if (vo != null)
           {
               vo.setUniqueKey(UUID.randomUUID().toString());
               vo.setSendTime(new Date());
               vo.setOperateFlag("N");
           }
           //endregion
           return vo;
       }
     
       public static void main(String[] args) {
           JYMessageVo jy = GenericsSimpleFactory.createMessage(JYMessageVo.class);
           jy.setJudgeResult(true);
           System.out.println(jy);
   
       }
   }
   ```

** 代码仓库

https://github.com/pishizuiqiang/Document/tree/main/SimpleFactory/src/com/pishi

