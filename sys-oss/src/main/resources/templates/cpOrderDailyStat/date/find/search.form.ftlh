<div class="layui-form-item">
    <div class="layui-inline">
        <div class="layui-input-inline">
            <input type="text" id="start_time" name="start_time" placeholder="开始时间" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <div class="layui-input-inline">
            <input type="text" id="end_time" name="end_time" placeholder="结束时间" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <div class="layui-input-inline">
            <input type="text" id="app_id" name="app_id" lay-verify="number" placeholder="APP ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <button id="${moudle}-search" class="layui-btn layuiadmin-btn-list" lay-submit lay-filter="${moudle}-search">
            <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
        </button>
        <button id="${moudle}-export" class="layui-btn layuiadmin-btn-list" lay-submit lay-filter="${moudle}-export">
            导出
        </button>
    </div>
</div>
<script>
    layui.config({
        base: '${RootPath}/layuiadmin/' //静态资源所在路径
    }).use(['form','laydate'], function () {
        var form= layui.form;
        form.on('submit(${moudle}-search)', function (data) {
            ${moudle}.search(data.field);
            form.last_search_data=data.field;
        });
        form.on('submit(${moudle}-export)', function (data) {
            ${moudle}.export(form.last_search_data);
        });

        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#start_time' //指定元素
            ,type: 'date',
            format:"yyyy-MM-dd",
            trigger: 'click'
        });

        laydate.render({
            elem: '#end_time' //指定元素
            ,type: 'date',
            format:"yyyy-MM-dd",
            trigger: 'click'
        });
    });
</script>