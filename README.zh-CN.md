## 0x00 学习笔记

---

**文章链接: Demo文件**

PS: 文章只有中文版本

### 反射机制RCE&&序列化

[Java反序列化の初见](https://chenlvtang.top/2021/05/10/Java反序列化の初见/)（第一次使用Java的反射机制和序列化）:

+ Java-Reflection_RCE-Example
+ Java-Serialization-Example

### URLDNS-Gadget

[Java反序列化之URLDNS](https://chenlvtang.top/2021/11/30/Java反序列化之URLDNS/)（分析和学习URLDNS-Gadget）:

+ URLDNS-Gadget

### CC1-Gadget

[Java反序列化之commons-collections-3.1漏洞分析](https://chenlvtang.top/2021/05/11/Java反序列化之commons-collections-3-1漏洞分析/)（分析和学习TransformedMap下的CC1-Gadget）:

+ CommonsCollections1-Gadget

[java反序列化之CC1其二](https://chenlvtang.top/2021/12/08/java反序列化之CC1其二/)（分析和学习LazyMap下的CC1-Gadget）:

+ CommonsCollections1-Gadget

### CC2-Gadget

[Java反序列化之CC2](https://chenlvtang.top/2021/12/11/java反序列化之CC2/)（分析和学习CC2-Gadget）:

+ CommonsCollections2-Gadget

### CC3-Gadget

[Java反序列化之CC3](https://chenlvtang.top/2021/12/11/java反序列化之CC2/)（分析和学习CC3-Gadget）:

+ CommonsCollections3-Gadget 

### RMI

[关于Java中RMI的个人拙见](https://chenlvtang.top/2021/07/09/关于Java中RMI的个人拙见/)（第一次使用RMI）: 

+ RMI-Example

[RMI的利用](https://chenlvtang.top/2021/08/07/RMI的利用/) (利用RMI和CC3-Gadget攻击)：

+ RMI-Exp_CC3 (使用的Payload只适用于JDK7)

### JNDI

[JNDI注入の个人拙见](https://chenlvtang.top/2021/09/11/JNDI注入の个人拙见/)（什么是JNDI && 利用JDNI进行攻击）：

+ JNDI-Example 
+ JNDI-Exp_RMI （没有LDAP的Demo，但是文章中有个例子）

[JDK8u191+等高版本下的JNDI注入](https://chenlvtang.top/2021/09/15/JDK8u191-等高版本下的JNDI注入/)（高版本下的JNDI注入）：

+ JNDI-Exp_BeanFactory 
+ JNDI-Exp_LDAPHacker_CC3（请使用 JDK8+，因为里面用了Base64模块 QAQ）

+ JNDI-Exp_LDAPClient_CC3（请使用 JDK7，因为使用的CC3 Payload只适用于JDK7. ORZ）

