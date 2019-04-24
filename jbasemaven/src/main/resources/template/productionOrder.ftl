<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<body>

<style>
    .print-body {
        width: 800px;
        margin: auto;
    }

    .print-top {
        text-align: center;
        font-weight: bold;
        flex-wrap: wrap;
        justify-content: flex-start;
        font-size: 18px;
        padding: 10px;
    }

    .print-info-content {
        display: flex;
        flex-wrap: wrap;
    }

    .print-content-block {
        width: 50%;
        padding: 10px 0px;
        display: flex;
    }

    .print-content-remark {
        display: flex;
        padding: 10px 0px;
    }

    .print-order-info {
        display: flex;
        flex-wrap: wrap;
    }

    .order-block {
        display: flex;
        /* width:20%; */
    }

    .block-title-l {
        width: 100px;
    }

    .block-title-m {}

    .block-content {
        width: 85px;
    }

    .block-content-s {
        width: 40px;
    }

    .block-content-l {
        width: 150px;
    }

    .print-tip {
        font-weight: bold;
        border-bottom: 1px dotted #ccc;
        padding: 10px 0px 10px 0px;
    }

    .print-table {
        margin: auto;
    }

    .print-table .table {
        width: 100%;
        max-width: 100%;
        margin-bottom: 20px;
    }

    .print-table .table td {
        padding: 8px;
        vertical-align: middle;
        text-align: center;
        max-width: 460px;
    }

    .print-bottom-block {
        display: flex;
    }

    .print-bottom {
        display: flex;
        justify-content: space-between;
    }
</style>
<div>
    <img src="/backoffice/cng/images/yuyue.jpg">
</div>
<div class="print-body">
    <#--<div class="print-top" style=""> 生产通知单</div>-->
    <#--<div class="print-info-content">-->
        <#--<div class="print-content-block">-->
            <#--<div class="block-title"> 订单编号：</div>-->
            <#--<div class="block-content">${orderCode!}</div>-->
        <#--</div>-->
        <#--<div class="print-content-block">-->
            <#--<div class="block-title"> 订单日期：</div>-->
            <#--<div class="block-content"> ${orderTime!}</div>-->
        <#--</div>-->
        <#--<div class="print-content-block">-->
            <#--<div class="block-title"> 客户名称：</div>-->
            <#--<div class="block-content"> ${customerName!}</div>-->
        <#--</div>-->
        <#--<div class="print-content-block">-->
            <#--<div class="block-title"> 交货日期：</div>-->
            <#--<div class="block-content"> ${deliveryDate!}</div>-->
        <#--</div>-->
        <#--<div class="print-content-block" style="width:100%">-->
            <#--<div class="block-title"> 交货工厂：</div>-->
            <#--<div class="block-content"> ${deliveryFactory!}</div>-->
        <#--</div>-->
        <#--<div class="print-remark">-->
            <#--<div class="print-content-remark">-->
                <#--<div class="block-title"> 订单备注：</div>-->
                <#--<div class="block-content"> ${orderNote!}</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
    <#--<div class="print-tip"> 订单商品信息</div>-->
    <#--<#list entryList! as entry>-->
        <#--<div class="print-order-info">-->
            <#--<div class="order-block">-->
                <#--<div class="block-title-l"> 商品名称：</div>-->
                <#--<div class="block-content"> ${entry.productName!}</div>-->
            <#--</div>-->
            <#--<div class="order-block">-->
                <#--<div class="block-title-l"> 商品编码：</div>-->
                <#--<div class="block-content"> ${entry.productCode!}</div>-->
            <#--</div>-->
            <#--<div class="order-block">-->
                <#--<div class="block-title-l"> 规格型号：</div>-->
                <#--<div class="block-content-l"> ${entry.productSpecifications!}</div>-->
            <#--</div>-->
            <#--<div class="order-block">-->
                <#--<div class="block-title-m"> 数量：</div>-->
                <#--<div class="block-content-s"> ${entry.productQty!}</div>-->
            <#--</div>-->
            <#--<div class="order-block">-->
                <#--<div class="block-title-m"> 单位：</div>-->
                <#--<div class="block-content-s">${entry.productUnit!}</div>-->
            <#--</div>-->
            <#--<div class="order-block">-->
                <#--<div class="block-title-l"> 商品备注：</div>-->
                <#--<div class="block-content"> ${entry.productNote!}</div>-->
            <#--</div>-->
        <#--</div>-->
        <#--<div class="print-table">-->
            <#--<table border="1" class="table" cellspacing="0">-->
                <#--<tbody>-->
                    <#--<tr class="table-top">-->
                        <#--<td>配置项名称</td>-->
                        <#--<td>配置内容</td>-->
                    <#--</tr>-->
                    <#--<#list entry.configurations as configuartion>-->
                        <#--<tr>-->
                            <#--<td>${configuartion.label!}</td>-->
                            <#--<td>${configuartion.value!}</td>-->
                        <#--</tr>-->
                    <#--</#list>-->
                <#--</tbody>-->
            <#--</table>-->
        <#--</div>-->
    <#--</#list>-->

    <div class="print-bottom">
        <div class="print-bottom-block">
            <div class="content"> 生产：</div>
            <div class=""> ${user.uid!}</div>
        </div>
        <div class="print-bottom-block">
            <div class="content"> 审核：</div>
            <div class="">${user.password!}</div>
        </div>
        <div class="print-bottom-block">
            <div class="content"> 制单：</div>
            <div class="">${user.birthday}</div>
        </div>
    </div>
</div>
</body>

</html>
