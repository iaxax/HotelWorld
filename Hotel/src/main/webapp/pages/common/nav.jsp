<%@page pageEncoding="utf-8" %>

<div class="container-fluid">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed"
                data-toggle="collapse" data-target="#navbar"
                aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Hotel World</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
            <li class="active" id="nav-home">
                <a href="javascript:void(0);"
                   onclick="window.location = '/Hotel/pages/home.jsp';"
                >主页</a>
            </li>
            <li id="nav-track">
                <a href="javascript:void(0);"
                   onclick="window.location = '/Hotel/pages/reside.jsp';"
                >住房</a>
            </li>
            <li id="nav-activity">
                <a href="javascript:void(0);"
                   onclick="window.location = '/Hotel/pages/member.jsp';"
                >会员</a>
            </li>
            <li id="nav-statistics">
                <a href="javascript:void(0);"
                   onclick="window.location = '/Hotel/pages/hotel.jsp';"
                >旅店</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="javascript:void(0);"
            >退出</a></li>
        </ul>
    </div>
</div>