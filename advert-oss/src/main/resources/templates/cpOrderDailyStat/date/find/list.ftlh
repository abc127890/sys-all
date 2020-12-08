<script id="${moudle}-table-operation-list" type="text/html">

    {{#  if(d.app_id >0){ }}
<a class="layui-btn layui-btn-danger layui-btn-xs" style="margin-top: 10%" lay-href="cpOrderDailyStat/date/stat/detail/find?app_id={{d.app_id}}&date={{d.date}}" lay-text="{{d.date}}-详情">
    <i class="layui-icon layui-icon-edit"></i>
    <span>详情</span>
</a>
    {{#  } }}


</script>

<table id="${moudle}-list" lay-filter="${moudle}-list"></table>

<style>
    #${moudle}-list .layui-table td, .layui-table-cell{
        height:50px;
        line-height: 50px;
    }

    #${moudle}-table-operation-list{
        display: flex
        justify-content: center;
        align-items: center;
    }
</style>

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

            url: '${RootPath}/${moudle}/date/stat/find',
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

    ${moudle}.export = function (datas) {
        let $=layui.$;
        let action = '${RootPath}/${moudle}/date/stat/detail/find/export';
        let form = $("<form></form>");

        datas=datas||{};
        datas.file_name="订单统计";

        form.css('display','none');
        form.attr('action',action);
        form.attr('method','post');

        for(var i in datas){
            var input = $("<input type='hidden' name='"+i+"' />");
            input.attr('value',datas[i]);
            form.append(input);
        }

        form.appendTo("body");
        form.submit();
        form.remove();
    };

</script>