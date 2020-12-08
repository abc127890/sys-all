<script>
    ${moudle}.getCols = function () {
        return [[
            {
                field: 'app_id',
                align: 'center',
                title: 'APP ID',
                width: 120,
            }, {
                field: 'app_name',
                title: 'APP名称',
                width: 200,
            },{
                field: 'app_icon',
                title: '图标',
                align: 'center',
                width: 100,
                templet: function (data) {
                    return '<img height="50" src="'+data.app_icon+'">';
                }
            },{
                field: 'start_time',
                title: '开始时间',
                align: 'center',
                width: 160,
                templet: function (data) {
                    return layui.util.toDateString(data.start_time, "yyyy-MM-dd HH:mm:ss");
                }
            },{
                field: 'end_time',
                title: '结束时间',
                align: 'center',
                width: 160,
                templet: function (data) {
                    return layui.util.toDateString(new Date(data.end_time), "yyyy-MM-dd HH:mm:ss");
                }
            }, {
                field: 'keyword',
                align: 'center',
                title: '关键词',
                width: 120,
            }, {
                field: 'cnt',
                align: 'center',
                title: '数量',
                width: 100,
            }, {
                field: 'unit_price',
                align: 'center',
                title: '单价',
                width: 100,
            }, {

                title: '订单金额',
                align: 'center',
                width: 120,
                templet: function (data) {
                    return (data.cnt * data.unit_price).toFixed(2);
                }
            }, {
                field: 'complete_cnt',
                align: 'center',
                title: '完成数量',
                width: 120,
            },{

                title: '消耗金额',
                align: 'center',
                width: 120,
                templet: function (data) {
                    return (data.complete_cnt * data.unit_price).toFixed(2);
                }
            },
            {
                field: 'create_time',
                title: '创建时间',
                align: 'center',
                width: 160,
                templet: function (data) {
                    return layui.util.toDateString(data.create_time, "yyyy-MM-dd HH:mm:ss");
                }
            },
            {
                title: '操作',
                fixed: 'right',
                width: 180,
                align: 'center',
                toolbar: '#${moudle}-table-operation-list'
            }
        ]];
    };
</script>