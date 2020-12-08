<script>
    ${moudle}.getCols = function () {
        return [[
            {
                field: 'app_id',
                align: 'center',
                title: 'APP ID',
            }, {
                field: 'app_name',
                title: 'APP名称',
            },{
                field: 'app_icon',
                title: '图标',
                align: 'center',
                width: 100,
                templet: function (data) {
                    if(!data.app_icon){
                        return "";
                    }
                    return '<img height="50" src="'+data.app_icon+'">';
                }
            },{
                field: 'date',
                title: '日期',
                align: 'center',
                templet: function (data) {
                    if(!data.date){
                        return "合计";
                    }
                    return data.date;
                }

            }, {
                field: 'complete_cnt',
                align: 'center',
                title: '下载量',

            }, {
                field: 'total_amount',
                align: 'center',
                title: '消耗金额',
                templet: function (data) {
                    return (data.total_amount).toFixed(2);
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