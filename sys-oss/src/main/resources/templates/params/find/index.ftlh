<#assign moudle = "params">
<script>
    var ${moudle}={};
</script>

<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../../head.ftlh">
    <script src="${RootPath}/layuiadmin/layui/layui.js?V=${V}"></script>
    <script src="${RootPath}/my/js/form.extend.js?V=${V}"></script>
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <form class="layui-form" onsubmit="return false;">
                <#include "search.form.ftlh">
            </form>
        </div>

        <#include "list.ftlh">
    </div>
</div>


<script>
    layui.config({
        base: '${RootPath}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index'], function(){

    });
</script>
</body>
</html>
