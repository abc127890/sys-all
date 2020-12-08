<#assign moudle = "cpOrder">
<script>
    var ${moudle}={};
</script>

<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../../head.ftlh">
    <script src="${RootPath}/layuiadmin/layui/layui.js"></script>
</head>
<body>

<div class="layui-form" lay-filter="${moudle}-form" id="${moudle}-form" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <label class="layui-form-label">广告主</label>
        <div class="layui-input-inline" style="width: 220px;">

            <select name="cp_id" lay-filter="cp_id"  class="layui-input">
                <option value="">请选择</option>
                <#list cps as cp>
                    ${cp}
                    <option value="${cp.id}">${cp.real_name}</option>
                </#list>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">投放时间</label>
        <div class="layui-input-inline">
            <input id="start_time" name="start_time" style="width: 220px;" lay-verify="required" maxlength="32" placeholder="请选择开始时间" autocomplete="off" class="layui-input">
        </div>

        <div class="layui-input-inline">
            <input id="end_time" name="end_time" style="width: 220px;" lay-verify="required" maxlength="32" placeholder="请选择结束时间" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">APP ID</label>
        <div class="layui-input-inline">
            <input name="app_id" style="width: 220px;" lay-verify="required|number" maxlength="16" placeholder="请输入APP ID" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">关键词</label>
        <div class="layui-input-inline">
            <input name="keyword" style="width: 220px;" lay-verify="required" maxlength="32" placeholder="请输入关键词" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">投放数量</label>
        <div class="layui-input-inline">
            <input id="cnt" name="cnt" style="width: 220px;" onchange="calculateAmount()" lay-verify="required|number" maxlength="8" placeholder="请输入投放数量" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">投放单价</label>
        <div class="layui-input-inline">
            <input id="unit_price" name="unit_price" style="width: 220px;" onchange="calculateAmount()" lay-verify="required|number" maxlength="8" placeholder="请输入投放单价" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">一共合计</label>
        <div class="layui-input-inline">
            <input id="total_amount" name="total_amount" value="0.00元" style="width: 220px;color:red" class="layui-input" readonly>
        </div>
    </div>

    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="form-submit" id="form-submit" value="确认添加">
        <input type="button" lay-submit lay-filter="form-edit" id="form-edit" value="确认编辑">
    </div>
</div>


<script>
    layui.config({
        base: '${RootPath}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form' ,'laydate'], function(){
        var $ = layui.$;
        var form = layui.form;

        ${moudle}.setInitValues();
        //监听提交
        form.on('submit(form-submit)', function(data){
            var field = data.field; //获取提交的字段

            let load_index=top.layer.load();
            ${moudle}.submit(field,function () {
                top.layer.closeAll('loading');
                parent["${moudle}"].reload();
                let index = top.layer.getFrameIndex(window.name);
                top.layer.close(index);
            })
        });

        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#start_time' //指定元素
            ,type: 'datetime',
            format:"yyyy-MM-dd HH:mm:ss",
            trigger: 'click'
        });

        laydate.render({
            elem: '#end_time' //指定元素
            ,type: 'datetime',
            format:"yyyy-MM-dd HH:mm:ss",
            trigger: 'click'
        });
    })



    ${moudle}.submit=function (data,callback) {
        var $ = layui.$;
        $.ajax({
            url:"add",
            data:data,
            method:"post",
            dataType:"json",
            success:function (data) {
                if(!data.success){
                    layui.layer.msg(data.msg);
                    return ;
                }
                callback&&callback();
            },
            error:function (e) {
                layui.layer.msg(e);
            }
        })
    }
    
    function calculateAmount() {
        let cnt=document.getElementById("cnt").value;
        let unit_price=document.getElementById("unit_price").value;

        let total_amount_el=document.getElementById("total_amount");

        if(cnt!="" && unit_price!=""){
            total_amount=(parseInt(cnt) * parseFloat(unit_price)).toFixed(2);
        }else{
            total_amount="0:00"
        }
        total_amount_el.value=total_amount+"元";
    }




    ${moudle}.setInitValues=function () {

        var data={};

        var now=new Date()

        let month=now.getMonth()+1;
        if(month<10){
            month="0"+month;
        }

        let date=now.getDate();
        if(date<10){
            date="0"+date;
        }

        var start_date=now.getFullYear() + '-' + month + '-' + date;

        data.start_time=start_date+ ' 00:00:00';
        data.end_time=start_date+ ' 23:30:00';

        layui.form.val("${moudle}-form", data);
        calculateAmount();
    };
</script>
</body>
</html>
