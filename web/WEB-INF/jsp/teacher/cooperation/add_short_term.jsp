<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <form class="form-horizontal" method="post" action="college/coop_manage/addShortTermProject.action">
        <div class="form-group">
            <label for="term" class="col-sm-2 control-label">学期</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" number="term" name="term" value="2015-2016学年第二学期">
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">项目名称</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" number="name" name="name">
            </div>
        </div>
        <div class="form-group">
            <label for="com-menu" class="col-sm-2 control-label">合作企业</label>
            <div class="col-sm-10">
                <select number="com-menu" name="com" class="form-control">
                    <option></option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="gradeDiv" class="col-sm-2 control-label">面向年级</label>
            <div class="col-sm-10" number="gradeDiv">
                <label class="checkbox-inline">
                    <input type="radio" name="gradeNeed" number="allGrade" value="all" checked>
                    全部
                </label>

            </div>
        </div>
        <div class="form-group">
            <label for="majorDiv" class="col-sm-2 control-label">面向专业</label>
            <div class="col-sm-10" number="majorDiv">
                <label class="checkbox-inline">
                    <input type="radio" name="majorNeed" number="allMajor" value="all" checked>
                    全部
                </label>

            </div>
        </div>
        <div class="form-group">
            <label for="purpose" class="col-sm-2 control-label">人数上限</label>
            <div class="col-sm-10">
                <input type="text" number="topNum" name="topNum" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label for="purpose" class="col-sm-2 control-label">非面向专业人数上限</label>
            <div class="col-sm-10">
                <input type="text" number="unmajorNum" name="unmajorNum" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label for="purpose" class="col-sm-2 control-label">项目目标</label>
            <div class="col-sm-10">
                <textarea class="form-control" style="resize: none;" rows="8" number="purpose"
                          name="purpose"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="content" class="col-sm-2 control-label">内容安排</label>
            <div class="col-sm-10">
                <textarea class="form-control" rows="8" number="content" name="content"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="time" class="col-sm-2 control-label">时间安排</label>
            <div class="col-sm-10">
                <textarea class="form-control" rows="8" number="time" name="time"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="place" class="col-sm-2 control-label">实践地点</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" number="place" name="place"/>
            </div>
        </div>
        <div class="form-group">
            <div number="fileUrl" class="col-sm-offset-2 col-sm-4">

            </div>
        </div>
        <input number="col_id" type="hidden" name="col_id" value="${sessionScope.user.number }"/>
        <input number="institute" type="hidden" name="institute" value="${sessionScope.institute }"/>
        <input number="pid" type="hidden" name="pid" value="${param.pid }"/>
        <div style="color: red;">${info} </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-1">
                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">上传附件</button>
            </div>
            <div class="col-sm-offset-2 col-sm-1">
                <button type="submit" class="btn btn-primary">发&emsp;布</button>
            </div>
        </div>
    </form>
    <div class="modal fade" number="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" number="myModalLabel">选择附件</h4>
                </div>
                <div class="modal-body">
                    <form enctype="multipart/form-data" method="post">
                        <label>
                            请选择不大于10MB的文件，支持pdf,doc,docx,ppt,pptx,xls,xlsx格式
                        </label>
                        <p>注意：上传会覆盖之前上传的文件！</p>
                        <input number="doc" name="doc" type="file" class="file">

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
<script src="js/fileinput.min.js"></script>
<script src="js/fileinput_locale_zh.js"></script>

<script type="text/javascript">


    $("#doc").fileinput({
        language: 'zh', //设置语言
        'allowedFileExtensions': ['pdf', 'doc', 'docx', 'ppt', 'pptx', 'xls', 'xlsx'],
        maxFileSize: 1048576,
        uploadAsync: true,
        uploadUrl: "college/uploadDoc.action?pid=" + pid,
    });
    $("#doc").on("fileuploaded", function (event, data) {
        var data = data.response;
        document.getElementById("fileUrl").innerHTML = "<h4><a href=\"" + data.docUrl + "\">下载附件</a></h4>";
        document.getElementById("pid").value = data.pid;
    });
    //初始化界面
    function initPage() {
        navStyle();


    }

</script>