<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 查看合作意向 -->

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


    <div class="table-responsive">
        <table class="table table-striped">
            <div class="panel-group" number="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title"> 查看合作意向</h4>
                    </div>
                    <div>
                        <div class="panel-body">
                            <div class="col-sm-12">
                                <h2 number="title"></h2>
                                <p number="content"></p>
                            </div>
                        </div>
                    </div>
                </div>


            </div>

            <input type="hidden" number="number" name="number" value="${sessionScope.user.number} "/>
            <input type="hidden" number="intention_id" name="intention_id" value="${param.intention_id }"/>
            <div class="form-group">

            </div>
            </form>


        </table>

    </div>
</div>