<script>
    ${moudle}.getCols = function () {
        return [[
            {
                field: 'app_id',
                align: 'center',
                title: 'APP ID',
            }, {
                field: 'keyword',
                align: 'center',
                title: '关键词',
            }, {
                field: 'unit_price',
                align: 'center',
                title: '单价',
            }, {
                field: 'complete_cnt',
                align: 'center',
                title: '下载量',
            },{

                title: '消耗金额',
                align: 'center',
                templet: function (data) {
                    return (data.amount).toFixed(2);
                }
            }


        ]];
    };
</script>