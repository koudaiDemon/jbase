package com.something.drools;

import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cWww
 * @Title Demo2 用于监控drl是否改变
 * @Description Registering and starting a KieScanner on a KieContainer
 * @date: 2018/12/11  19:46
 */
public class Demo2 {

    public static void main(String[] args) {

        final KieServices kieServices = KieServices.Factory.get();
        final ReleaseId releaseId = kieServices.newReleaseId( "com.drools", "drools-demo", "1.0-SNAPSHOT" );
        final KieContainer kContainer = kieServices.newKieContainer( releaseId );
        final KieSession test = kContainer.newKieSession("test");
        test.fireAllRules();
        test.dispose();


//        System.out.println(releaseId);
//        final KieRepository repository = kieServices.getRepository();
//        final ReleaseId defaultReleaseId = repository.getDefaultReleaseId();
//        System.out.println(defaultReleaseId);
//        final KieModule kieModule = repository.getKieModule(releaseId);
//        System.out.println(kieModule);



//        KieScanner kScanner = kieServices.newKieScanner( kContainer );
//
//// Start the KieScanner polling the Maven repository every 10 seconds
//        kScanner.start( 10000L );

//        List<String> list = Arrays.asList("aaa","bbb","ccc");
//
//        for (String str : list) {
////            list.remove(str);
//        }

//        String str = "address-book,replenishmentConfirmationPage,\n" +
//                "search,\n" +
//                "homepage,\n" +
//                "my-replenishment-orders,\n" +
//                "cartPage,\n" +
//                "payment-details,\n" +
//                "my-quotes,\n" +
//                "multiStepCheckoutSummaryPage,\n" +
//                "orderConfirmationPage,\n" +
//                "add-edit-address,\n" +
//                "my-replenishment-details,\n" +
//                "searchGrid,\n" +
//                "my-replenishment-cancel-confirmation,\n" +
//                "quote-detail,\n" +
//                "order-approval-dashboard,\n" +
//                "updatePassword,\n" +
//                "storefinderPage,\n" +
//                "order-approval-details,\n" +
//                "saved-carts,\n" +
//                "update-profile,\n" +
//                "orders,\n" +
//                "login,\n" +
//                "notFound,\n" +
//                "order,\n" +
//                "checkout-login,\n" +
//                "update-email,\n" +
//                "savedCartDetailsPage,\n" +
//                "faq,\n" +
//                "searchAdvancedEmpty,\n" +
//                "quoteEditPage,\n" +
//                "searchEmpty,\n" +
//                "orderExpired,\n" +
//                "importCSVSavedCartPage,\n" +
//                "quickOrderPage,\n" +
//                "productList,\n" +
//                "termsAndConditions,\n" +
//                "productDetails,\n" +
//                "productGrid,\n" +
//                "category,\n" +
//                "OrderPendingApprovalEmail,\n" +
//                "OrderApprovalRejectionEmail,\n" +
//                "ReplenishmentOrderConfirmationEmail,\n" +
//                "ReplenishmentOrderPlacedEmail,\n" +
//                "NotPickedUpConsignmentCanceledEmail,\n" +
//                "OrderPartiallyCanceledEmail,\n" +
//                "OrderPartiallyRefundedEmail,\n" +
//                "DeliverySentEmail,\n" +
//                "CustomerRegistrationEmail,\n" +
//                "OrderRefundEmail,\n" +
//                "OrderConfirmationEmail,\n" +
//                "ReadyForPickupEmail,\n" +
//                "QuoteBuyerSubmissionEmail,\n" +
//                "QuoteToExpireSoonEmail,\n" +
//                "OrderCollectionReminderEmail,\n" +
//                "QuoteExpiredEmail,\n" +
//                "OrderMoveToCsEmail,\n" +
//                "OrderCancelledEmail,\n" +
//                "my-company-login,\n" +
//                "forgetPassword,\n" +
//                "account-center,\n" +
//                "strategicOrder,\n" +
//                "base-info,\n" +
//                "modify-password,\n" +
//                "notice-announcement,\n" +
//                "dispatch,\n" +
//                "wishlistPage,\n" +
//                "ForgottenPasswordEmail,\n" +
//                "QuoteBuyerCancellationEmail,\n" +
//                "manageUnitApprovers,\n" +
//                "manageUsergroups,\n" +
//                "my-company,\n" +
//                "organizationManagement,\n" +
//                "manageUnits,\n" +
//                "apply-ship,\n" +
//                "checking-account,\n" +
//                "dispatch-detail,\n" +
//                "not-permissions,\n" +
//                "credit-overdue,\n" +
//                "credit-changes,\n" +
//                "remittance-info,\n" +
//                "repayment-plan,\n" +
//                "checking-ship,\n" +
//                "checking-missing,\n" +
//                "checking-use,\n" +
//                "mobile_orderList,\n" +
//                "checking-title\n";
//
////        System.out.println(str.replace("\n",""));
//
//        System.out.println("AAA".toLowerCase());
//
//        System.out.println("aaa.bbb".substring("aaa.bbb".indexOf(".")));

    }

}
