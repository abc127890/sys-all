<script>
    ${moudle}.delete = function (data) {
        top.layui.layer.confirm("是否确定删除吗？", function (index) {
            ${moudle}.__delete(data, function () {
                ${moudle}.reload();
                top.layui.layer.msg('删除成功');
            });
        });
    };

    ${moudle}.__delete = function (data, callback) {
        var $ = layui.$;
        $.ajax({
            url: "${moudle}/delete",
            data: {
                id: data.id
            },
            method: "delete",
            dataType: "json",
            success: function (data) {
                if (!data.success) {
                    top.layui.layer.msg(data.msg);
                    return;
                }
                callback && callback();
            },
            error: function (e) {
                top.layui.layer.msg(e);
            }
        })
    };

    ${moudle}.deletes = function () {
        let checkStatus = layui.table.checkStatus('${moudle}-list');
        let checkData = checkStatus.data; //得到选中的数据

        if (checkData.length === 0) {
            return top.layui.layer.msg('请选择数据');
        }
        let ids = checkData.map((item) => {
            return item.id;
        });

        let title = ids.length == 1 ? '是否确定删除吗？' : '是否确定进行批量删除吗？';

        top.layui.layer.confirm(title, function (index) {
            ${moudle}.__deletes(ids, function () {
                ${moudle}.reload();
                top.layui.layer.msg('已删除');
            })
        });

    };
    ${moudle}.__deletes = function (ids, callback) {
        var $ = layui.$;
        $.ajax({
            url: "${moudle}/deletes",
            data: {
                ids: ids
            },
            method: "delete",
            dataType: "json",
            success: function (data) {
                if (!data.success) {
                    top.layui.layer.msg(data.msg);
                    return;
                }
                callback && callback();
            },
            error: function (e) {
                top.layui.layer.msg("系统异常，请稍后再试");
            }
        })
    };

    ${moudle}.add = function () {
        let index = window.top.layer.open({
            type: 2,
            title: '下单',
            content: '${moudle}/add?order_id=${order_id}',
            maxmin: true,
            offset: '50px',
            area: ['400px', '610px'],
            btn: ['确定', '取消'],
            success: function (layero, index) {
                //设置父级窗口
                var iframe = layero.find('iframe');
                iframe[0].contentWindow.parent = window;
            },
            yes: function (index, layero) {
                let submit_button = layero.find('iframe').contents().find("#form-submit");
                submit_button.click();
            }
        });
    };

    ${moudle}.update = function (data) {
        let index = window.top.layer.open({
            type: 2,
            title: '编辑',
            content: '${moudle}/update?id=' + data.id,
            maxmin: true,
            offset: '50px',
            area: ['400px', '610px'],
            btn: ['确定', '取消'],
            success: function (layero, index) {
                //设置父级窗口
                var iframe = layero.find('iframe');
                iframe[0].contentWindow.parent = window;
            },
            yes: function (index, layero) {
                let submit_button = layero.find('iframe').contents().find("#form-submit");
                submit_button.click();
            }
        });
    };



</script>