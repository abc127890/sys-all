<style>
    .layadmin-side-shrink .layui-layout-admin .layui-logo {
        background: none;
    }

    .layui-layout-admin .layui-logo {
        text-align: left;
        padding-left: 46px;
        font-size: 13px;
        background: url("logo.png") no-repeat center;
        background-size:99.9% 99.9%;
    }
</style>
<div class="layui-side layui-side-menu">
    <div class="layui-side-scroll">
        <div class="layui-logo">

        </div>

        <ul id="menu" class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu"
            lay-filter="layadmin-system-side-menu">
        </ul>
    </div>
</div>
