<#assign moudle = "sysUser">
<script>
    var ${moudle}={};
</script>

<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../head.ftlh">
    <script src="${RootPath}/layuiadmin/layui/layui.js"></script>
</head>
<body>

<div class="layui-form" lay-filter="${moudle}-form" id="${moudle}-form" style="padding: 20px 30px 0 0;">


    <div class="layui-form-item">
        <label class="layui-form-label">原密码</label>
        <div class="layui-input-inline">
            <input name="password" type="password" style="width: 220px;" lay-verify="required" maxlength="32" placeholder="请输入原密码" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-inline">
            <input name="new_password" type="password" style="width: 220px;" lay-verify="required" maxlength="32" placeholder="请输入新密码" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-inline">
            <input name="new_password2" type="password" style="width: 220px;" lay-verify="required" maxlength="32" placeholder="重新输入原密码" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">&nbsp;</label>
        <div class="layui-input-inline">
            <input type="button" lay-submit lay-filter="form-submit" id="form-submit" value="确认修改" class="layui-btn">
        </div>

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

        //监听提交
        form.on('submit(form-submit)', function(data){
            var field = data.field; //获取提交的字段

            if(field.new_password!=field.new_password2){
                layui.layer.msg("新密码输入不一致");
                return ;
            }

            ${moudle}.submit(field,function () {
                layui.layer.msg("修改密码成功");
                setTimeout(function () {
                    parent.layui.admin.events.closeThisTabs()
                },1000);
            })
        });
    })



    ${moudle}.submit=function (data,callback) {

        var $ = layui.$;
        $.ajax({
            url:"${moudle}/updatePassword",
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





</script>
</body>
</html>
