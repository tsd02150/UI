/**
 * 
 */
// multipart request.
$(document).ready(function() {
	//modal 처리. 라이브 이벤트 처리.(후에 나올 것들에도 이벤트 추가)
	$('#noticeList').on('dblclick','tr',function(){
		let id = $(this).children().eq(0).text();
		$.ajax({
			url:'getNoticeJson.do?nid='+id,
			dataType:'json',
			error:function(xhr){
				console.log(xhr);
			},
			success:function(data){
				$('.nid').text(data.noticeId);
				$('.nTitle').val(data.noticeTitle);
				$('.nWriter').text(data.noticeWriter);
				$('.nSubject').val(data.noticeSubject);
				$('.nAttach').css('width','300px').attr('src','images/'+data.attachFile);
			}
		})
		$('#myModal').css('display','block');
	})
	
	$('span.close').on('click',function(){
		$('#myModal').css('display','none');
	})
	$(window).on('click',function(e){
		if(e.target==$('#myModal')[0]){
			$('#myModal').css('display','none');
		}
	})
	
	//modal창에 있는 이미지 클릭.
	$('img.nAttach').on('click',function(){
		$('#attachFile').click(); //trigger event.
	})
	
	// 파일 선택하면 change이벤트
	$('#attachFile').on('change',function(e){
		// 게시글 번호, 파일 => 서버전송 : noticeId 기준으로 attach수정
		let data = new FormData();
		data.append('nid',$('.nid').text());
		data.append('nfile',e.target.files[0]);
		$.ajax({
			url:'modifyNoticeFile.do',
			method: 'post',
			data: data,
			//multipart요청
			contentType: false,
			processData: false,
			error:function(err){
				console.log(err);
			},
			success:function(result){
				console.log(result);
				$('.nAttach').css('width','300px').attr('src','images/'+result.attachFile);
			}
		});
	})
	
	$('button:contains(수정)').on('click',function(){
		let id=$('.nid').text();
		let title=$('.nTitle').val();
		let subject=$('.nSubject').val();
		
		$.ajax({
			url:'modifyNoticeJson.do',
			method:'post',
			data:{id:id,title:title,subject:subject},
			error:function(err){
				
			},
			success:function(result){
				if(result.retCode == 'Success'){
					console.log(result.retVal);					
					$('#tr_'+result.retVal.noticeId).children().eq(1).text(result.retVal.noticeTitle);
					$('#tr_'+result.retVal.noticeId+' img').attr('src','images/'+result.retVal.attachFile);
				}else if(result.retCode=='Fail'){
					alert('error 발생');
				}
			}
		})
		
		$('#myModal').hide();
	
	})
	
	// 등록 버튼
	$('form').on('submit', function(e) {
		e.preventDefault();
		var frm = $('form')[0];
		//$(frm).find('input[name="job"]').val('multi');
		var data = new FormData(frm); //multipart/form-data 처리하는 객체
		for (let val of data.entries()) {
			console.log(val);
		}
		$.ajax({
			url: 'addNotice.do',
			method: 'post',
			data: data,
			//multipart요청
			contentType: false,
			processData: false,
			error: function(jqxhr) {
				console.log(jqxhr.responseText);
			},
			success: function(data, status, xhr) {
				let val = data.retVal;
				
				if(data.retCode == 'Success'){
					let tr=$('<tr/>').append(
						$('<td/>').text(val.noticeId),
						$('<td/>').text(val.noticeTitle),
						$('<td/>').text(val.noticeWriter),
						$('<td/>').append($('<img>').css('width','30px').attr('src','images/'+val.attachFile)),
						$('<td/>').append($('<button/>').text('삭제').on('click',deleteRow))
					);
					tr.attr('id','tr_'+val.noticeId);
					$('#noticeList').prepend(tr);
					
					$('form')[0].reset();
				}else if(data.retCode == 'Fail'){
					alert('처리 에러');
				}else{
					alert('알수없는 반환값');
				}
			}
		})
		.always(function() {
			console.log('final.')
		})
	}); // end of 등록 버튼
	
	// 공지목록 출력
	$.ajax({
		url : 'noticeListJson.do',
		method: 'GET',
		data : 'json',
		error:function(xhr){
			console.log(xhr.responseText);
		},
		success : function(data){
			console.log(data);
			data.forEach(notice=>{
				let tr = $('<tr/>').append($('<td/>').text(notice.noticeId),
											$('<td/>').text(notice.noticeTitle),
											$('<td/>').text(notice.noticeWriter),
											$('<td/>').append($('<img>').css('width','30px').attr('src','images/'+notice.attachFile)),
											$('<td/>').append($('<button/>').text('삭제').on('click',deleteRow))
											);
				tr.attr('id','tr_'+notice.noticeId);
				$('#noticeList').append(tr);
			})
		}
	})
})

function deleteRow(){
	let tr = $(this).closest('tr');
	let id = tr.children().eq(0).text();
	console.log(tr);
	// ajax호출 id를 기분으로 삭제후 화면에서 제거
	$.ajax({
		url:'delNoticeJson.do?nid='+id,
		dataType:'html',
		error:function(xhr){
			console.log(xhr);
		},
		success:function(result){
			if(result=='Success'){
				tr.remove();
			}else if(result=='Fail'){
				alert('처리 에러');
			}else{
				alert('알수없는 반환')
			}
		}	
	})
	.always(function(){
		console.log('final.');
	})
}

