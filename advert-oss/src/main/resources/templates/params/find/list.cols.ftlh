<script>
    ${moudle}.getCols = function () {
        return [[
            {
                type: "checkbox"
            },
            {
                field: 'id',
                title: 'ID',
                width: 100,
                align: 'center'
            }, {
                field: 'value',
                title: '值'
            }, {
                field: 'remark',
                title: '备注'
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
                width: 200,
                align: 'center',
                toolbar: '#${moudle}-table-operation-list'
            }
        ]];
    };
</script>