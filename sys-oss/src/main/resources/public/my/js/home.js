function logout(){
    $.ajax({
        type : 'post',
        url : 'sys/logout',
        xhrFields: {
            withCredentials: true // 这里设置了withCredentials
        },
        success : function(data) {
            if(!data.success){
                layui.layer.msg(data.msg);
                return ;
            }
            location.reload(true);
        }
    });
}


function initMenu(){

    $.ajax({
        url: "my/menus",
        type:"GET",
        dataType: 'json',
        success:function(data){
            var menu = $("#menu");
            var first_id=null;
            $.each(data.data, function(i,item){
                let menu_1=`
                    <li data-name="${item.id}" class="layui-nav-item  ${item.leaf==1?'':'layui-nav-itemed'}">
                        <a href="javascript:;" ${item.leaf==1?"lay-href='"+item.url+"'":""} lay-tips="${item.name}" lay-direction="2">
                            <i class="layui-icon ${item.icon||'layui-icon-set'}"></i>
                            <cite>${item.name}</cite>
                        </a>
                        ${item.leaf==0?buildSubMenu(item,item.childrens):""}
                    </li>
                `;


                function buildSubMenu(parent,childrens){


                    let message = `
                        <dl class="layui-nav-child">
                            ${childrens.map((item) => {
                                first_id=first_id==null?item.id:first_id;
                                let url=item.url;
                                if(url&&url.indexOf("/")==0){
                                    url=url.substring(1);
                                }
                                return `
                                    <dd data-name="${item.id}" >
                                        <a id="menu-${item.id}" lay-href="${url}" >${item.name}</a>
                                    </dd>
                                `;
                            }).join("")}
                        </dl>
                    `;
                    return message;
                }
                menu.append(menu_1);
            });
            layui.element.render();
            document.getElementById("menu-"+first_id).click();
        },
        error: function (res) {
            console.log(res);
        }
    });
}
