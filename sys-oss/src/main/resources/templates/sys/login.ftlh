<!DOCTYPE html>
<html lang="en">
<head>
    <title>登录</title>
    <#include "../head.ftlh">
    <script src="${RootPath}/layuiadmin/layui/layui.js"></script>
    <link rel="stylesheet" href="${RootPath}/layuiadmin/style/login.css" media="all">
</head>

<body>

<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>APP Store 平台</h2>
        </div>
        <form class="layui-form" onsubmit="layui.$('#login-btn').trigger('click');return false;">
            <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-username"
                           for="LAY-user-login-username"></label>
                    <input type="text" name="user_name" id="LAY-user-login-username" lay-verify="required"
                           placeholder="用户名" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-password"
                           for="LAY-user-login-password"></label>
                    <input type="password" name="password" id="LAY-user-login-password" lay-verify="required"
                           placeholder="密码" class="layui-input">
                </div>


                <div class="layui-form-item" style="margin-top: 50px">
                    <button id="login-btn" class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">登 入</button>
                </div>

            </div>
        </form>
    </div>
</div>

<script>
    layui.config({
        base: '${RootPath}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'user'], function () {
        var $ = layui.$
                , setter = layui.setter
                , admin = layui.admin
                , form = layui.form
                , router = layui.router()
                , search = router.search;

        form.render();

        //提交
        form.on('submit(LAY-user-login-submit)', function (obj) {

            //请求登入接口
            login(obj.field.user_name, obj.field.password);

        });


        //实际使用时记得删除该代码
        layer.msg('欢迎使用', {
            offset: '15px'
            , icon: 1
        });


    });

    function login(user_name, password) {
        var $ = layui.$;
        $.ajax({
            url: "sys/login",
            data: {
                user_name,
                password
            },
            method: "post",
            dataType: "json",
            success: function (data) {
                if (!data.success) {
                    layui.layer.msg(data.msg);
                    return;
                }
                location.reload(true);
            },
            error: function (e) {
                layui.layer.msg(e);
            }
        })
    }
</script>
</body>
</html>