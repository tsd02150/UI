/**
 * 
 */

/* 
async function loadData(){
	let promise = await fetch('noticeListJson.do');
	let promise1 = await promise.json(); //json-> object
	let fields=['noticeId','noticeTitle','noticeWriter','attachFile']
	promise1.forEach(function(item){
		console.log(item);
		let tr = document.createElement('tr');
		for(let prop in fields){
			let td = document.createElement('td');
			td.innerText=item[prop];
			tr.append(td);
		}
		document.getElementById('noticeList').append(tr);
	});
}

document.addEventListener('DOMContentLoaded',function(){
	loadData();
})
*/