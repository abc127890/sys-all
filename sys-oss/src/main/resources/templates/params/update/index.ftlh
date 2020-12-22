<#assign moudle = "params">
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
        <label class="layui-form-label">ID</label>
        <div class="layui-input-inline">
            <input name="id" style="width: 400px;" readonly lay-verify="required|number" maxlength="8" placeholder="请输入ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-inline">
            <input name="remark" style="width: 400px;" lay-verify="required" maxlength="100" placeholder="请输入备注" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">参数值</label>
        <div class="layui-input-inline">
            <textarea name="value" lay-verify="required" style="width: 400px; height: 150px;" maxlength="2000" placeholder="请输入参数值" autocomplete="off" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="form-submit" id="form-submit" value="确认提交">
    </div>
</div>


<script>
    layui.config({
        base: '${RootPath}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form'], function(){
        var $ = layui.$;
        var form = layui.form;
        ${moudle}.setInitValues();
        //监听提交
        form.on('submit(form-submit)', function(data){
            var field = data.field; //获取提交的字段
            ${moudle}.submit(field,function () {
                parent["${moudle}"].reload();
                let index = top.layer.getFrameIndex(window.name);
                top.layer.close(index);
            })
        });
    })

    ${moudle}.submit=function (data,callback) {
        let $ = top.layui.$;
        $.ajax({
            url:"${moudle}/update",
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
    };


    ${moudle}.setInitValues=function () {
        let data=${record.toString()};
        layui.form.val("${moudle}-form", data);
    };

</script>
</body>
</html>
