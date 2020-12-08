<script id="${moudle}-table-operation-list" type="text/html">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update">
        <i class="layui-icon layui-icon-edit"></i>
        <span>编辑</span>
    </a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">
        <i class="layui-icon layui-icon-delete"></i>
        <span>删除</span>
    </a>
</script>

<div class="operations-list">
    <button class="layui-btn layuiadmin-btn-list layui-btn-danger" data-type="deletes">删除</button>
    <button class="layui-btn layuiadmin-btn-list" data-type="add">添加</button>
</div>

<table id="${moudle}-list" lay-filter="${moudle}-list"></table>

<#include "list.cols.ftlh">
<#include "list.operations.ftlh">

<script>
    layui.config({
        base: '${RootPath}/layuiadmin/' //静态资源所在路径
    }).use(['util', 'table'], function () {
        var $ = layui.$;
        $('.layui-btn.layuiadmin-btn-list').on('click', function () {
            var type = $(this).data('type');
            ${moudle}[type] && ${moudle}[type].call(this);
        });
        ${moudle}.initTable();
        document.getElementById("${moudle}-search").click();
        layui.table.on('tool(${moudle}-list)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            ${moudle}[layEvent] && ${moudle}[layEvent].call(this, data);
        });
    });

    ${moudle}.where = {};

    ${moudle}.initTable = function () {
        ${moudle}.table = layui.table.render({
            elem: '#${moudle}-list',
            page: true,
            method: 'post',
            loading: true,
            cellMinWidth: 120,
            cols: ${moudle}.getCols(),
            request:{
                pageName: 'pNum',
                limitName: 'limit'
            },
            parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": res.success ? 0 : 1,
                    "msg": res.msg,
                    "count": res.success ? res.data.total : 0,
                    "data": res.success ? res.data.list : []
                };
            }
        });
    };


    ${moudle}.search = function (data) {
        ${moudle}.where = data;
        let index = layer.load(1);
        ${moudle}.table.reload({
            where: data,
            url: '${RootPath}/${moudle}/find',
            page: {
                curr: 1 //重新从第 1 页开始
            },
            done: function () {
                layer.close(index);
            }
        });
    };

    ${moudle}.reload = function () {
        ${moudle}.table.reload();
    };

</script>