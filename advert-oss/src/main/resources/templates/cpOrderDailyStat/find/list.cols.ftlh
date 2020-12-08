<script>
    ${moudle}.getCols = function () {
        return [[
            {
                field: 'app_id',
                align: 'center',
                title: 'APP ID',
                width: 120,
            }, {
                field: 'date',
                align: 'center',
                title: '日期'
            }, {
                field: 'keyword',
                align: 'center',
                title: '关键词',
                width: 160,
            },{
                field: 'complete_cnt',
                align: 'center',
                title: '下载量'
            }, {
                field: 'unit_price',
                align: 'center',
                title: '单价',
                width: 120,
            },{

                title: '消耗金额',
                align: 'center',
                templet: function (data) {
                    return (data.complete_cnt * data.unit_price).toFixed(2);
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