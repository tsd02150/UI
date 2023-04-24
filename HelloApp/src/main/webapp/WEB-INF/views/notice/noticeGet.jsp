<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	#content{
	padding:15px auto
	}
</style>

<h3>공지사항 상세 페이지.(noticeGet.jsp)</h3>

<form action="modifyNotice.do" method="GET">
	<table class="table">
		<tr>
			<th>글번호</th>
			<td><input type="text" name="nid"
				value="${noticeInfo.noticeId }" readonly></td>
		<tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"
				value="${noticeInfo.noticeTitle }" readonly></td>
		<tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="3" cols="20" name="subject" readonly>${noticeInfo.noticeSubject }</textarea></td>
		<tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer"
				value="${noticeInfo.noticeWriter }" readonly></td>
		<tr>
		<tr>
			<th>첨부파일 : ${fileType }</th>
			<td>
			<c:if test="${noticeInfo.attachFile != null}">
				<c:choose>
					<c:when test="${fileType == 'image' }">
						<img width="200px" src="images/${noticeInfo.attachFile }">
					</c:when>
					<c:otherwise>
						<a href="images/${noticeInfo.attachFile }">${noticeInfo.attachFile }</a>
					</c:otherwise>
				</c:choose>
			</c:if></td>
		<tr>
		<tr>
			<td colspan="2" align="center">
				<c:choose>
				<c:when test="${id==noticeInfo.noticeWriter }">
				<button type="submit">수정</button>
				</c:when>
				<c:otherwise>				
				<button disabled="disabled">수정</button>
				</c:otherwise>	
				</c:choose>
				<button type="button" onclick="location.href='noticeList.do?page=${pageNum}'">목록</button>
			</td>
		</tr>
	</table>
</form>

<div id="content">
	<input type="text" id="reply">
	<span>${id }</span>
	<button type="button" id="addBtn">등록</button>
</div>

<!-- 댓글 정보 -->
<table class="table">
	<thead>
		<tr>
			<th>댓글번호</th>
			<th>글내용</th>
			<th>작성자</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody id="tlist">
	</tbody>
</table>



<script>
	let xhtp = new XMLHttpRequest();
	xhtp.open('get','replyList.do?nid=${noticeInfo.noticeId}');
	xhtp.send();
	let showFields=['replyId','reply','replyWriter'];
	xhtp.onload = function(){
		let data =JSON.parse(xhtp.response);
		let tlist = document.getElementById('tlist');
		for(let reply of data){
			console.log(reply);
			let tr = makeTrFunc(reply);
			tlist.append(tr);
		}
	}
	
	//등록 이벤트
	document.querySelector('#addBtn').addEventListener('click',addReplyFnc);
	function addReplyFnc(e){
		let id = document.querySelector('#content span').innerText;
		if(id==null||id==''){
			alert("로그인 후에 처리하세요");
			location.href='loginForm.do';
			return;
		}
		console.log('click',e.target);
		console.log('reply',document.querySelector('#reply').value);
		console.log('id','${id}');
		let reply=document.querySelector('#reply').value;
		// Ajax 호출
		let xhtp = new XMLHttpRequest();
		xhtp.open('post','addReply.do');
		xhtp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xhtp.send('notice_id=${noticeInfo.noticeId}&id=${id}&reply='+reply);
		xhtp.onload = function(){
			console.log(xhtp.response);
			let result = JSON.parse(xhtp.response);
			if(result.retCode=='Success'){
				let tr = makeTrFunc(result.data);
				tlist.append(tr);
				
				document.getElementById("reply").value='';
				document.getElementById("reply").focus();
			}else if(result.retCode == 'Fail'){
				alert('처리중 에러발생');
			}
		}
	}
	
	function makeTrFunc(reply){
		let tr = document.createElement('tr');
		for(let prop of showFields){
			let td = document.createElement('td');
			td.innerText=reply[prop];
			tr.append(td);
		}
		//삭제 버튼
		let btn = document.createElement('button');
		btn.addEventListener('click',function(e){
			//밑의 방식으로 받아올수도 있다.
			//let rid = btn.parentElement.parentElement.children[0].innerText;
			
			let xhtp = new XMLHttpRequest();
			xhtp.open('post','removeReply.do');
			xhtp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			xhtp.send('rid='+reply['replyId']);
			
			xhtp.onload = function(){
				let result = JSON.parse(xhtp.response);
				if(result.retCode=='Success'){
					btn.parentElement.parentElement.remove(); //제거
				}else if(result.retCode=='Fail'){
					alert('처리 중 에러발생')					
				}else{
					alert('알수 없는 결과값입니다.')
				}
			}
		});
		btn.innerText='삭제';
		let td = document.createElement('td');
		td.append(btn);
		tr.append(td);
		
		return tr;
	}
	
	
</script>
