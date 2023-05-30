export let basket = {
    totalCount: 0,
    totalPrice: 0,
    delCheckedItem: function () {
		let checkList = $("input[name='buy']:checked");
		$.each(checkList,function(index,checkItem){
			$(checkItem).parentsUntil('#basket').remove();
		})
		this.updateUI();
        console.log('delCheckedItem');
    },
    delAllItem: function () {
		let checkList = $("input[name='buy']");
		$.each(checkList,function(index,checkItem){
			$(checkItem).parentsUntil('#basket').remove();
		})
		this.updateUI();
        console.log('delAllItem');
    },
    reCalc: function () {
        console.log('reCalc');
        let itemList = $(".row.data");
        let cnt=0;
        let price=0;
        $.each(itemList,function(index,item){
			if($(item).parent().css("display")!="none"){
				cnt+=parseInt($(item).find(".p_num").val());
				let temp = $(item).find(".sum").text().split('원')[0];
				temp=temp.split(',')[0]+temp.split(',')[1];
				price+=parseInt(temp);
			}
		});
        this.totalCount = cnt;
        this.totalPrice = price;
    },
    updateUI: function () {
        console.log('updateUI');
        this.reCalc();
        document.querySelector('#sum_p_num').textContent = '상품개수: ' + this.totalCount.formatNumber() + '개'
        document.querySelector('#sum_p_price').textContent = '합계금액: ' + this.totalPrice.formatNumber() + '원'
    },
    changePNum: function () {
		let targetBasket = $(event.target).parentsUntil('#basket');
		let count = targetBasket.find('.p_num').val()
		
		if(event.target.classList.contains('down')){
			if(targetBasket.find('.p_num').val()!=0){
				targetBasket.find('.p_num').val(count-1);
			}
		}else if(event.target.classList.contains('up')){
			targetBasket.find('.p_num').val(parseInt(count)+1);
		}else if(event.target.classList.contains('p_num')){
			if(targetBasket.find('.p_num').val()<0){
				targetBasket.find('.p_num').val(0);
			}
		}
		
		count = targetBasket.find('.p_num').val();
		let pPrice =targetBasket.find('.p_price').val();      
        let sumPrice = parseInt(pPrice)*parseInt(count);
        targetBasket.find('.sum').text(sumPrice.formatNumber()+'원');
        
        this.updateUI();   
    },
    delItem: function () {
        $(event.target).parentsUntil('#basket').remove();
        this.updateUI();
    },
    cartList: function () {
        cartItems.forEach((item, idx) => {
            let template = document.querySelector('#template>div.row.data').cloneNode(true);
            template.querySelector('.img>img').setAttribute('src', '../img/' + item.image)
            template.querySelector('.pname>span').textContent = item.productNm
            template.querySelector('.basketprice>input').value = item.price
            template.querySelector('.basketprice').childNodes[2].textContent = item.price.formatNumber() + "원"
            template.querySelector('.updown>input').value = item.qty
            template.querySelector('.updown>input').setAttribute('value', item.qty)
            template.querySelector('.updown>input').setAttribute('id', 'p_num' + (idx + 1));
            template.querySelector('.sum').textContent = (item.price * item.qty).formatNumber() + "원"

            document.querySelector('#basket').append(template)
        })
    }
};

var cartItems = [{
        no: 1,
        productNm: '이것이 민트다.',
        qty: 2,
        price: 12000,
        image: 'item1.PNG'
    },
    {
        no: 2,
        productNm: '와 아이스크림.',
        qty: 1,
        price: 22000,
        image: 'item2.PNG'
    },
    {
        no: 3,
        productNm: '모나카 케익.',
        qty: 1,
        price: 13600,
        image: 'item3.PNG'
    }
]

// 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function () {
    if (this == 0) return 0;
    let regex = /(^[+-]?\d+)(\d{3})/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};