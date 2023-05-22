// fetchMEmber.js

$(function(){
  $('#list').remove();
  $('#show2').remove();
  $('#show').empty();
  $('#show').before('<h3>회원목록</h3>');

  //json 데이터 출력
  fetch('MOCK_DATA.json')
  .then(resolve=>resolve.json())
  .then(makeList)
  .catch(err=>console.log(err));

  function makeList(result){
    let tbl = $('<table border="1"/>');
    let tbd = $('<tbody/>').attr('id','memberList');
    result.forEach((member,idx)=> {
      if(idx<5){
        let tr=$('<tr/>').append($('<td/>').text(member.id),
                                 $('<td/>').text(member.first_name),
                                 $('<td/>').text(member.last_name),
                                 $('<td/>').text(member.gender),
                                 $('<td/>').append($('<button>삭제</button>').on('click',delMember)),
                                 $('<td/>').append($('<input/>').attr('type','checkbox'))
                                 );
        tbd.append(tr);
      }
    });
    tbl.append(tbd);
    $('#show').append(tbl);
    makeHead();
  }

  function makeHead(){
    //title등록
    $('table:eq(1)').prepend(
      $('<thead/>').append($('<th/>').text('ID'),
                           $('<th/>').text('이름'),
                           $('<th/>').text('성'),
                           $('<th/>').text('성별'),
                           $('<th/>').text('삭제'),
                           $('<th/>').html('<input type="checkbox"/>')
      )
    );
  }

  function delMember(e){
    $(this).parentsUntil('tbody').remove();
  }
  
  function addMemberFnc(){
    let tr=$('<tr/>').append($('<td/>').text($('#id').val()),
                             $('<td/>').text($('#fname').val()),
                             $('<td/>').text($('#lname').val()),
                             $('<td/>').text($('#gender').val()),
                             $('<td/>').append($('<button>삭제</button>').on('click',delMember)),
                             $('<td/>').append($('<input/>').attr('type','checkbox'))
    )
    $('#show tbody').append(tr);
    $('input[type="text"]').val('');
  }

  //등록버튼 이벤트 추가
  $('button:contains(등록)').on('click',addMemberFnc);

  //tr에 마우스 오버가 되면 등록화면에 출력이 되도록

  
  //더블클릭하면 그부분 인풋
  //라이브 이벤트
  $('body').on('dblclick','#memberList tr',function(){
    let oldTr = $(this);
    let oldId=$(this).children().eq(0).text();
    let oldFname=$(this).children().eq(1).text();
    let oldLname=$(this).children().eq(2).text();
    let oldGender=$(this).children().eq(3).text();
    let newTr=$('<tr/>').append($('<td/>').text(oldId),
                                $('<td/>').append($('<input/>').val(oldFname)),
                                $('<td/>').html('<input type="text" value='+oldLname+'>'),
                                $('<td/>').html(oldGender),
                                $('<td/>').append($('<button/>').text('수정').on('click',updateTr)),
                                $('<td/>').append($('<input/>').attr('type','checkbox'))
    );
    oldTr.replaceWith(newTr);
  })

  function updateTr(e){
    let oldTr = $(this).parentsUntil('tbody');
    let oldId = oldTr.find('td:eq(0)').text();
    let oldFname = oldTr.find('td:eq(1)>input').val();
    let oldLname = oldTr.find('td:eq(2)>input').val();
    let oldGender = oldTr.find('td:eq(3)').text();
    
    let newTr =$('.template').clone();
    newTr.find('td:eq(0)').text(oldId);
    newTr.find('td:eq(1)').text(oldFname);
    newTr.find('td:eq(2)').text(oldLname);
    newTr.find('td:eq(3)').text(oldGender);
    newTr.find('td:eq(4)>button').on('click',delMember);
    newTr.removeAttr('class');

    oldTr.replaceWith(newTr);
  }
  
  $('button:contains(변경)').on('click',updateMemberFnc);

  function updateMemberFnc(e){
    let id = $('#id').val();
    let fname=$('#fname').val();
    let lname=$('#lname').val();
    let gender=$('#gender').val();

    let newTr=$('.template').clone();
    newTr.find('td:eq(0)').text(id);
    newTr.find('td:eq(1)').text(fname);
    newTr.find('td:eq(2)').text(lname);
    newTr.find('td:eq(3)').text(gender);
    newTr.find('td:eq(4)>button').on('click',delMember);
    newTr.removeAttr('class');

    let targetTr = $('#memberList tr:contains('+id+')');
    targetTr.replaceWith(newTr);
  }

  $('button:contains(선택된tr이동)').on('click',selectTrMove);
  function selectTrMove(e){
    let targetTr = $('input[type="checkbox"]:checked').parentsUntil('tbody');
    targetTr.next().after(targetTr[1]);
    console.log(targetTr);
    console.log(targetTr.next());
  }
})