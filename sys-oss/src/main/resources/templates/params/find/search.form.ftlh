<div class="layui-form-item">
    <div class="layui-inline">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-inline">
            <input type="text" name="id" lay-verify="number" maxlength="8" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-inline">
            <input type="text" name="remark" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-inline">
        <button id="${moudle}-search" class="layui-btn layuiadmin-btn-list" lay-submit lay-filter="${moudle}-search">
            <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
        </button>

        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</div>
<script>
    layui.config({
        base: '${RootPath}/layuiadmin/' //静态资源所在路径
    }).use(['form'], function () {
        var form= layui.form;
        form.on('submit(${moudle}-search)', function (data) {
            ${moudle}.search(data.field);
        });
    });
</script>