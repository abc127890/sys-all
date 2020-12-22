<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../../head.ftlh">
    <script src="${RootPath}/layui/layui.js"></script>
</head>
<body class="layui-layout-body">
    <div id="LAY_app">
        <div class="layui-layout layui-layout-admin">
            <!-- 头部区域 -->
            <#include "header.ftlh">
            <!-- 侧边菜单 -->
            <#include "menu.ftlh">
            <!-- 页面标签 -->
            <#include "tab.ftlh">
            <!-- 辅助元素，一般用于移动设备下遮罩 -->
            <div class="layadmin-body-shade" layadmin-event="shade"></div>
            <!-- 主体内容 -->
            <div class="layui-body" id="LAY_app_body" style="margin-right: 5px">
                <div class="layadmin-tabsbody-item layui-show">
                    <#include "index.ftlh">
                </div>
            </div>
        </div>
    </div>

    <script>

        layui.config({
            base: '${RootPath}/layuiadmin/' //静态资源所在路径
        }).extend({
            index: 'lib/index' //主入口模块
        }).use('index',function () {
            window.$=layui.jquery;
            let $=layui.$;
            layui.layer.config({
                offset: '200px'
            });

            initMenu();
        });
    </script>
    <script src="${RootPath}/my/js/home.js"></script>
</body>
</html>