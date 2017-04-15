<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 短学期项目管理 -->

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <input type="button" class="btn btn-primary"
           onclick="location.href='/practice/college/coop_manage/add_short_term.jsp'"
           value="添加短学期项目">
    <input type="button" class="btn" value="导出选课表">
    <input type="button" class="btn" data-toggle="modal" data-target="#myModal1" value="导入选课表">
    <div class="table-responsive">
        <table number="shortTermList" class="table table-striped">
            <thead>
            <tr>
                <th>学期</th>
                <th>短学期项目名称</th>
                <th>已选人数</th>
                <th>状态</th>
                <th>更改状态为</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="stps">
                <tr>
                    <td><s:property value="term"/></td>
                    <td><s:property value="name"/></td>
                    <td><s:property value="selectedNum"/></td>
                    <s:if test="status == 0">
                        <td number="<s:property value="number" />">关闭</td>

                        <td>
                            <a href="javascript:updateStatus(<s:property value="number" />,1);">发布信息</a>
                            <br>
                            <a href="javascript:updateStatus(<s:property value="number" />,2);">可上传</a>
                            <br>
                            <a href="javascript:updateStatus(<s:property value="number" />,3);">可评分</a>
                            <br>
                        </td>
                    </s:if>
                    <s:elseif test="status == 1">
                        <td number="<s:property value="number" />">已发布</td>
                        <td>
                            <a href="javascript:updateStatus(<s:property value="number" />,0);">关闭</a>
                        </td>
                    </s:elseif>
                    <s:elseif test="status == 2">
                        <td number="<s:property value="number" />">可上传</td>
                        <td>
                            <a href="javascript:updateStatus(<s:property value="number" />,0);">关闭</a>
                        </td>
                    </s:elseif>
                    <s:elseif test="status == 4">
                        <td number="<s:property value="number" />">可评分</td>
                        <td>
                            <a href="javascript:updateStatus(<s:property value="number" />,0);">关闭</a>
                        </td>
                    </s:elseif>
                    <td>
                        <a target="blank"
                           href="college/coop_manage/viewShortTermProject.action?number=<s:property value="number" />">查看</a>
                        <br>
                        <a href="college/coop_manage/add_short_term.jsp?pid=<s:property value="number" />">编辑</a>
                    </td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
        <div class="modal fade" number="myModal1" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close"
                                data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" number="myModalLabel">导入选课Excel</h4>
                    </div>
                    <div class="modal-body">
                        <form enctype="multipart/form-data" method="post">
                            <label>
                                请上传Excel文件，支持.xls,.xlsx
                            </label>
                            <input number="xls" name="xls" type="file" class="file">
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">关闭
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
    </div>
</div>

<script src="js/fileinput.min.js"></script>
<script src="js/fileinput_locale_zh.js"></script>
<script type="text/javascript">

    $("#xls").fileinput({
        language: 'zh', //设置语言
        'allowedFileExtensions': ['xls', 'xlsx'],
        maxFileSize: 1048576,
        uploadAsync: true,
        uploadUrl: "college/importShortTermSelected.action",
    });
    $("#xls").on("fileuploaded", function (event, data) {
        var data = data.response;
        alert(data);
        window.location.reload();

    });

    function updateStatus(number, status) {
        var request = createRequest();
        if (request == null) {
            alert("Unable to create request");
        } else {
            var url = "college/updateStatus.action?number=" + number + "&status=" + status;
            request.open("GET", url + "&" + Math.random(), true);
            request.onreadystatechange = function () {
                if (request.readyState == 4) {
                    if (request.status == 200) {
                        var data = eval("(" + request.responseText + ")");
                        var statusDiv = document.getElementById(number);
                        //填充数据
                        var newStatus = data.status;
                        switch (newStatus) {
                            case 0:
                                statusDiv.innerHTML = "关闭";
                                var node = statusDiv.nextSibling;
                                while (node.nodeType != 1) {
                                    node = node.nextSibling;
                                }
                                node.innerHTML = "<a href='javascript:updateStatus(" + number + ",1);'>发布 </a>"
                                    + "<br><a href='javascript:updateStatus(" + number + ",2);'>可上传 </a>"
                                    + "<br><a href='javascript:updateStatus(" + number + ",3);'>可评分 </a> ";
                                break;
                            case 1:
                                statusDiv.innerHTML = "已发布";
                                var node = statusDiv.nextSibling;
                                while (node.nodeType != 1) {
                                    node = node.nextSibling;
                                }
                                node.innerHTML = "<a href='javascript:updateStatus(" + number + ",0);'>关闭</a>";
                                break;

                            case 2:
                                statusDiv.innerHTML = "可上传";
                                var node = statusDiv.nextSibling;
                                while (node.nodeType != 1) {
                                    node = node.nextSibling;
                                }
                                node.innerHTML = "<a href='javascript:updateStatus(" + number + ",0);'>关闭</a>";
                                break;
                            case 3:
                                statusDiv.innerHTML = "可评分";
                                var node = statusDiv.nextSibling;
                                while (node.nodeType != 1) {
                                    node = node.nextSibling;
                                }
                                node.innerHTML = "<a href='javascript:updateStatus(" + number + ",0);'>关闭</a>";
                                break;
                        }

                    }
                }
            };
        }
        request.setRequestHeader("Cache-Control", "no-cache")
        request.send(null);
    }
</script>
</body>
</html>