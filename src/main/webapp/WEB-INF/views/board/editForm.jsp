<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- jQuery CDN -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>    
<!-- bootstrap CDN -->
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- fontawesome icon-->
<script src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath }/header.css" rel="stylesheet">
<style>
 #ok, #cancel{
  background-color: #9770f9; 
  border: none;
  color: white;
  text-align: center;
  margin:2px;
}
</style>
<%@ include file="/WEB-INF/views/container/header.jsp"%>
<div class="container">
   <div class="page-header">
               <div class="row">
                  <div class="col-lg-12">
                     <p align="center">
                        <b>게시물 내용을 수정해주세요.</b>
                     </p>
                  </div>
               </div>
            </div>
  <div class="row">
    <form class="form-horizontal" action="${pageContext.request.contextPath }/board/list.do">
        <div class="form-group">
          <div class="col-lg-offset-3 col-lg-6 col-lg-offset-3">
          <label>내용</label>
          </div>
          <div class="col-lg-offset-3 col-lg-6 col-lg-offset-3">
          <textarea class="form-control" id="content" name="content" placeholder="Content" rows="8">#치킨#먹고싶다#잠10시간자고싶다#현실은#프젝#아무나데려가라#회사놈들아
          </textarea>
         </div>
        </div>
      <div class="form-group">
      <div class="col-lg-offset-3 col-lg-6 col-lg-offset-3">
      <label>첨부파일</label>
      </div>
      <div class="col-lg-offset-3 col-lg-6 col-lg-offset-3">
          <input type="file" name="file" value="filefile"><br>
     </div>
     </div>
     <div class="col-lg-offset-3 col-lg-6 col-lg-offset-3">
    <button type="submit" class="btn btn-primary" id = "ok">수정</button>
    <button type="reset" class="btn btn-primary" id = "cancel">취소</button>
    </div>
  </form>
</div>
</div>
<%@ include file="/WEB-INF/views/container/header.jsp"%>
</html>