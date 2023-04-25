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
<table style="display:none;">
	<tbody>
		<tr class="template">
			<td>10</td>
			<td><input type="text" class="reply"></td>
			<td>user01</td>
			<td><button>수정</button></td>
		</tr>
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
		
		//this  1) Object 안에서 사용되면 object자체를 가리킴
		//		2) function 선언 안에서 this는 window전역객체 <-> 지역
		//		3) event안에서 사용되는 this는 이벤트를 받는 대상
		tr.addEventListener('dblclick',function(e){
			let writer = tr.children[2].innerText;
			
			if(writer!='${id}'){
				alert('권한이 없습니다');
				return;
			}
			for(let contentTlist of document.getElementById('tlist').children){
				if(contentTlist.className=='template'){
					alert('이미 수정중인 댓글이 있습니다.');
					return;
				}
			}
			
			let template = document.querySelector('.template').cloneNode(true);
			// template.children[0].innerText = reply.replyId;
			// template.children[1].children[0].value = reply.reply;
			// template.children[2].innerText = reply.replyWriter;
			template.querySelector('td:nth-of-type(1)').innerText = reply.replyId;
			template.querySelector('td:nth-of-type(2)>input').value = reply.reply;
			template.querySelector('td:nth-of-type(3)').innerText = reply.replyWriter;
			template.querySelector('td:nth-of-type(4)>button').addEventListener('click',function(e){
				let replyId=reply.replyId;
				let replyContent=this.parentElement.parentElement.children[1].children[0].value;
				console.log(replyId,replyContent);
				let xhtp = new XMLHttpRequest();
				xhtp.open('post','modifyReply.do');
				xhtp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
				xhtp.send('rid='+replyId+'&reply='+replyContent); 
				xhtp.onload = function(){
					let result=JSON.parse(xhtp.response);
					if(result.retCode=='Success'){
						// 화면 변경
						tr.children[1].innerText=result.data.reply;
						document.getElementById('tlist').replaceChild(tr,template);
					}else if(result.retCode=='Fail'){
						alert('처리중 에러');
					}else{
						alert('알수없는 반환값');
					}
				}
			});
			//화면 전환
			document.getElementById('tlist').replaceChild(template,tr);
		});
		for(let prop of showFields){
			let td = document.createElement('td');
			td.innerText=reply[prop];
			tr.append(td);
		}
		//삭제 버튼
		let btn = document.createElement('button');
		btn.addEventListener('click',function(e){
			let writer = btn.parentElement.previousElementSibling.innerText;
			
			if(writer!='${id}'){
				alert('권한이 없습니다');
				return;
			}
			
			//밑의 방식으로 받아올수도 있다.
			//let rid = btn.parentElement.parentElement.children[0].innerText;
			
			let xhtp = new XMLHttpRequest();
			xhtp.open('post','removeReply.do');
			xhtp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			xhtp.send('rid='+reply.replyId); // 요청방식이 post 일 경우에 parameter를 send()에 포함
			
			xhtp.onload = function(){
				let result = JSON.parse(xhtp.response);
				if(result.retCode=='Success'){
					btn.parentElement.parentElement.remove(); //제거
				}else if(result.retCode=='Fail'){
					alert('잘못된 입력입니다.')					
				}else{
					alert('알수 없는 결과값.')
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
