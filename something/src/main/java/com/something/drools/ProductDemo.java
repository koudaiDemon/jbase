package com.something.drools;

import com.something.drools.fact.Product;
import org.apache.commons.lang.BooleanUtils;
import org.kie.api.KieServices;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/11/6  20:24
 */
public class ProductDemo {

    public static void main(String[] args) {

        // 构建KieServices
        KieServices ks = KieServices.Factory.get();
        ks.getRepository();
        KieContainer kieContainer = ks.getKieClasspathContainer();
//        KieModuleModel
//        KieContainer kieContainer = ks.getKieClasspathContainer("/drools/kmoudule.xml");
//        System.out.println(kieContainer);
        // 获取kmodule.xml中配置中名称为ksession-rule的session，默认为有状态的。
        KieSession kSession = kieContainer.newKieSession("ksession-rule");

        Product product = new Product();
        product.setType(Product.GOLD);

        kSession.insert(product);
        int count = kSession.fireAllRules();
        System.out.println("命中了" + count + "条规则！");
        System.out.println("商品" +product.getType() + "的商品折扣为" + product.getDiscount() + "%。");

    }

}
