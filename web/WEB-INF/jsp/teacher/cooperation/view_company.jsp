<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 查看企业信息 -->

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


    <div class="table-responsive">
        <table class="table table-striped">
            <div class="panel-group" number="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title"> 查看企业信息</h4>
                    </div>
                    <div>
                        <div class="panel-body">
                            <div class="col-sm-6">
                                <p number="name"></p>
                                <p number="type"></p>
                                <p number="linkman"></p>
                                <p number="tel"></p>
                                <p number="location"></p>
                                <p number="address"></p>
                            </div>
                        </div>
                    </div>
                </div>


            </div>

            <input type="hidden" number="com_id" name="com_id" value="${param.company_id }"/>
            <div class="form-group">

            </div>
            </form>


        </table>

    </div>
</div>

<script>
    window.onload = initPage;

    //初始化界面
    function initPage() {


    }


</script>
</body>
</html>