<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include  file="/WEB-INF/views/shared/_bjuilayout.jsp"%>
</head>

<body>
<!-- init -->
<script type="text/javascript">
    $(function () {
        // main - menu
        $('#bjui-accordionmenu')
                .collapse()
                .on('hidden.bs.collapse', function (e) {
                    $(this).find('> .panel > .panel-heading').each(function () {
                        var $heading = $(this), $a = $heading.find('> h4 > a');
                        if ($a.hasClass('collapsed')) $heading.removeClass('active');
                    });
                })
                .on('shown.bs.collapse', function (e) {
                    $(this).find('> .panel > .panel-heading').each(function () {
                        var $heading = $(this), $a = $heading.find('> h4 > a');
                        if (!$a.hasClass('collapsed')) $heading.addClass('active');
                    });
                });
        $(document).on('click', 'ul.menu-items > li > a', function (e) {
            var $a = $(this), $li = $a.parent(), options = $a.data('options').toObj();
            var onClose = function () {
                $li.removeClass('active');
            };
            var onSwitch = function () {
                $('#bjui-accordionmenu').find('ul.menu-items > li').removeClass('switch');
                $li.addClass('switch');
            };
            $li.addClass('active');
            if (options) {
                options.url = $a.attr('href');
                options.onClose = onClose;
                options.onSwitch = onSwitch;
                if (!options.title) options.title = $a.text();
                if (!options.target)
                    $a.navtab(options);
                else
                    $a.dialog(options);
            }

            e.preventDefault();
        });

        //时钟
        var today = new Date(), time = today.getTime();
        $('#bjui-date').html(today.formatDate('yyyy/MM/dd'));
        setInterval(function () {
            today = new Date(today.setSeconds(today.getSeconds() + 1));
            $('#bjui-clock').html(today.formatDate('HH:mm:ss'));
        }, 1000);

    });

    //菜单-事件
    function MainMenuClick(event, treeId, treeNode) {
        event.preventDefault();
        if (treeNode.isParent) {
            var zTree = $.fn.zTree.getZTreeObj(treeId);
            zTree.expandNode(treeNode, !treeNode.open, false, true, true);
            return;
        }

        if (treeNode.target && treeNode.target == 'dialog')
            $(event.target).dialog({ id: treeNode.tabid, url: treeNode.url, title: treeNode.name });
        else
            $(event.target).navtab({
                id: treeNode.tabid,
                url: treeNode.url,
                title: treeNode.name,
                fresh: treeNode.fresh,
                external: 'iframe'  //如果为空则直接在本页面里加载，否则使用iframe
            });
    }
</script>

<!--[if lte IE 7]>
<div id="errorie"><div>您还在使用老掉牙的IE，正常使用系统前请升级您的浏览器到 IE8以上版本 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div></div>
<![endif]-->
<div id="bjui-window">
    <header id="bjui-header">
        <div class="bjui-navbar-header">
            <button type="button" class="bjui-navbar-toggle btn-default" data-toggle="collapse" data-target="#bjui-navbar-collapse">
                <i class="fa fa-bars"></i>
            </button>
            <a class="bjui-navbar-logo" href="#"><img src="<%=path%>/static/BJUI/images/logo.png"></a>
        </div>
        <nav id="bjui-navbar-collapse">
            <ul class="bjui-navbar-right">
                <li class="datetime"><div><span id="bjui-date"></span> <span id="bjui-clock"></span></div></li>
                <li><a href="#">消息 <span class="badge">4</span></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">我的账户 <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li>
                            <a href="changepwd.html" data-toggle="dialog" data-id="changepwd_page" data-mask="true"
                               data-width="400" data-height="260">&nbsp;<span class="glyphicon glyphicon-lock"></span>修改密码&nbsp;</a>
                        </li>
                        <li><a href="#">&nbsp;<span class="glyphicon glyphicon-user"></span> 我的资料</a></li>
                        <li class="divider"></li>
                        <li><a href="<%=path%>/login/logout.do" class="red">&nbsp;<span class="glyphicon glyphicon-off"></span> 注销登陆</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle theme blue" data-toggle="dropdown" title="切换皮肤"><i class="fa fa-tree"></i></a>
                    <ul class="dropdown-menu" role="menu" id="bjui-themes">
                        <li><a href="javascript:;" class="theme_default" data-toggle="theme" data-theme="default">&nbsp;<i class="fa fa-tree"></i> 黑白分明&nbsp;&nbsp;</a></li>
                        <li><a href="javascript:;" class="theme_orange" data-toggle="theme" data-theme="orange">&nbsp;<i class="fa fa-tree"></i> 橘子红了</a></li>
                        <li><a href="javascript:;" class="theme_purple" data-toggle="theme" data-theme="purple">&nbsp;<i class="fa fa-tree"></i> 紫罗兰</a></li>
                        <li class="active"><a href="javascript:;" class="theme_blue" data-toggle="theme" data-theme="blue">&nbsp;<i class="fa fa-tree"></i> 天空蓝</a></li>
                        <li><a href="javascript:;" class="theme_green" data-toggle="theme" data-theme="green">&nbsp;<i class="fa fa-tree"></i> 绿草如茵</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="bjui-hnav">
            <button type="button" class="btn-default bjui-hnav-more-left" title="导航菜单左移"><i class="fa fa-angle-double-left"></i></button>
            <div id="bjui-hnav-navbar-box">
                <ul id="bjui-hnav-navbar" style="width: 10000%;">
                    <li class="active">
                        <a href="javascript:;" data-toggle="slidebar"><i class="fa fa-check-square-o"></i> 首页</a>
                        <div class="items hide" data-noinit="true" id="navTree">
                            <%--<ul id="bjui-doc-tree-base" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick"--%>
                                <%--data-expand-all="true" data-faicon="star-o" data-tit="控制台">--%>
                                <%--<c:forEach items="${models}" var="module">--%>
                                    <%--<li data-id="${module.funcId}" data-pid="${module.funcFatherId}"--%>
                                            <%--<c:choose>--%>
                                                <%--<c:when test="${ empty module.funcUrl}">--%>
                                                    <%--data-faicon="folder-open-o" data-faicon-close="folder-o"--%>
                                                <%--</c:when>--%>
                                                <%--<c:otherwise>--%>
                                                    <%--data-url="${module.funcUrl}" data-tabid="${module.funcId}"--%>
                                                <%--</c:otherwise>--%>
                                            <%--</c:choose>--%>
                                    <%-->${module.funcName}--%>
                                    <%--</li>--%>
                                <%--</c:forEach>--%>
                            <ul class="menu-items" data-faicon="hand-o-up" data-tit="首页">
                                <li><a href="#" data-options="{id:'form-checkbox', faicon:'check-square-o'}">hello</a></li>
                                <li><a href="#" data-options="{id:'form-checkbox', faicon:'check-square-o'}">world</a></li>

                            </ul>
                        </div>
                    </li>
                    <%--<li class="dropdown">--%>
                        <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">--%>
                            <%--<i class="fa fa-cog"></i> 系统设置 <span class="caret"></span>--%>
                        <%--</a>--%>
                    <%--</li>--%>

                    <c:forEach items="${models}" var="m">
                        <li><a href="javascript:;" data-toggle="slidebar"><i class="${m.funcIcon}"></i>${m.funcName}</a>
                            <div class="items hide" data-noinit="true">
                                <c:forEach items="${m.childMenuDoList}" var="mc">
                                    <ul class="menu-items" data-faicon="${mc.funcIcon}" data-tit="${mc.funcName}">
                                        <c:forEach items="${mc.childMenuDoList}" var="mcc">
                                            <li><a href="${pageContext.request.contextPath}${mcc.funcUrl}" data-options="{id:'a${mcc.funcId}'}">${mcc.funcName}</a></li>
                                        </c:forEach>
                                    </ul>
                                </c:forEach>
                            </div>
                        </li>
                    </c:forEach>

                </ul>
            </div>
            <button type="button" class="btn-default bjui-hnav-more-right" title="导航菜单右移">
                <i class="fa fa-angle-double-right"></i>
            </button>
        </div>
    </header>
    <div id="bjui-container">
        <div id="bjui-leftside">
            <div id="bjui-sidebar-s">
                <div class="collapse"></div>
            </div>
            <div id="bjui-sidebar">
                <div class="toggleCollapse"><h2><i class="fa fa-bars"></i> 导航栏 <i class="fa fa-bars"></i></h2><a href="javascript:;" class="lock"><i class="fa fa-lock"></i></a></div>
                <div class="panel-group panel-main" data-toggle="accordion" id="bjui-accordionmenu" data-heightbox="#bjui-sidebar" data-offsety="26">
                </div>
            </div>
        </div>
        <div id="bjui-navtab" class="tabsPage">
            <div class="tabsPageHeader">
                <!--标签头-->
                <div class="tabsPageHeaderContent">
                    <ul class="navtab-tab nav nav-tabs">
                        <!--class="external"  该class标识在iframe中打开-->
                        <li data-url="home/main.do" >
                            <a href="javascript:;">
                                    <span>
                                        <i class="fa fa-home"></i> #maintab#
                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="tabsLeft"><i class="fa fa-angle-double-left"></i></div>
                <div class="tabsRight"><i class="fa fa-angle-double-right"></i></div>
                <div class="tabsMore"><i class="fa fa-angle-double-down"></i></div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">#maintab#</a></li>
            </ul>
            <div class="navtab-panel tabsPageContent">
                <!--标签内容-->
                <div class="navtabPage unitBox">
                    <div class="bjui-pageContent" style="background:#FFF;">
                        Loading...
                    </div>
                </div>
            </div>

            <%--<p>Hello World!</p>--%>
        </div>
    </div>
    <footer id="bjui-footer">
        Copyright &copy; 2016　milanyangbo
    </footer>
</div>
</body>
</html>
