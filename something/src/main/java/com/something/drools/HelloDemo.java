package com.something.drools;

import com.something.drools.fact.Product;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.conf.ClockTypeOption;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/12/6  11:32
 */
public class HelloDemo {

    public static void main(String[] args) {


        // 构建KieServices
//        KieServices ks = KieServices.Factory.get();
//        ks.getRepository();
//        final KieFileSystem kieFileSystem = ks.newKieFileSystem();

        KieServices kieServices = KieServices.Factory.get();
        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
//
        KieBaseModel kieBaseModel1 = kieModuleModel.newKieBaseModel( "KBase1 ")
                .setDefault( true )
                .setEqualsBehavior( EqualityBehaviorOption.EQUALITY )
                .setEventProcessingMode( EventProcessingOption.STREAM );
//

        kieBaseModel1.newKieSessionModel( "KSession1" )
                .setDefault( true )
                .setType( KieSessionModel.KieSessionType.STATEFUL )
                .setClockType( ClockTypeOption.get("realtime") );
//
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.writeKModuleXML(kieModuleModel.toXML());

        kfs.write( "src/main/resources/KBase1/ruleSet1.drl", "package drools.hello;\n" +
                "dialect  \"mvel\"\n" +
                "\n" +
                "rule \"hello1\"\n" +
                "    when\n" +
                "    then\n" +
                "    System.out.println(\"hello\");\n" +
                "\n" +
                "end" );
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();

        KieSession kSession1 = kieClasspathContainer.newKieSession("KSession1");

        int count = kSession1.fireAllRules();
//
//        KieContainer kieContainer = ks.getKieClasspathContainer();
//        KieSession kSession = kieContainer.newKieSession("hello");
//
//        kSession.fireAllRules();
//
//        int count = kSession.fireAllRules();
        System.out.println("命中了" + count + "条规则！");
//        System.out.println("商品" +product.getType() + "的商品折扣为" + product.getDiscount() + "%。");
//

//        kieFileSystem.generateAndWritePomXML();
//        KieContainer kieContainer = ks.getKieClasspathContainer();
/*//        KieModuleModel
//        KieContainer kieContainer = ks.getKieClasspathContainer("/drools/kmoudule.xml");
//        System.out.println(kieContainer);*/
//         获取kmodule.xml中配置中名称为ksession-rule的session，默认为有状态的。
//        KieSession kSession = kieContainer.newKieSession("hello");
////
//        Product product = new Product();
//        product.setType(Product.DIAMOND);
////
//        kSession.insert(product);
//        int count = kSession.fireAllRules();
//        System.out.println("命中了" + count + "条规则！");
//        System.out.println("商品" +product.getType() + "的商品折扣为" + product.getDiscount() + "%。");

    }

}
