<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href ="css/modal.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="js/noticeAsync.js"></script>

<h3>공지사항 등록 페이지</h3>

<form action="addNotice.do" method="POST" enctype="multipart/form-data">
<input type="hidden" name="job" value="multi">
<table class="table">
	<tr>
		<th>제목</th><td><input type="text" name="title"></td>
	<tr>
	<tr>
		<th>내용</th><td><textarea rows="3" cols="20" name="subject"></textarea></td>
	<tr>
	<tr>
		<th>작성자</th><td><input type="text" name="writer" value="${id!=null?id:'' }"></td>
	<tr>
	<tr>
		<th>첨부파일</th><td><input type="file" name="attach"></td>
	<tr>
	<tr>
		<td colspan="2" align="center">
			<button type="submit">등록</button>
			<button type="reset">취소</button>
		</td>
	</tr>
</table>
</form>
<hr>
<div id="list">
<table class="table">
	<thead>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>첨부파일</th>
		</tr>
	</thead>
	<tbody id="noticeList">
		
	</tbody>
</table>
</div>

<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>수정</h2>
    </div>
    <div class="modal-body">
		<table class="table">
			<tr>
				<th>글번호</th><td class="nid"></td>
			</tr>
			<tr>
				<th>제목</th><td><input class="nTitle" type="text"></td>
			</tr>
			<tr>
				<th>작성자</th><td class="nWriter"></td>
			</tr>
			<tr>
				<th>내용</th><td><textarea class="nSubject"></textarea></td>
			</tr>
			<tr>
				<th>파일</th><td><img class="nAttach"></td>
			</tr>
			<tr><td colspan="2"><button>수정</button></td></tr>
		</table>
		<input type="file" id="attachFile" style="display:none">
    </div>
    <div class="modal-footer">
      <h3> </h3>
    </div>
  </div>

</div>