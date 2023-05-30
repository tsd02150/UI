var APP_LOG_LIFECYCLE_EVENTS = false;
let store = new Vue({
  el: "#app",
  data: {
    sitename: 'Vue.js 애완용품 샵',
    // product: {
    //   id: 1001,
    //   title: '고양이 사료, 25파운드',
    //   description: '당신의 고양이를 위한 <em>거부할 수 없는 </em>, 유기농 사료입니다.',
    //   price: 2000,
    //   image: 'images/product-fullsize.png',
    //   availableInventory:5
    // },
    products:[],
    cart:[],
    showProduct:true,
    states: {
      AL: '알라바마',
      AK: '알래스카',
      AR: '애리조나',
      CA: '캘리포니아',
      NV: '네바다'
    },
    order: {
      firstName: '',
      lastName: '',
      address: '',
      city: '',
      zip: '',
      state: '',
      method: '자택 주소',
      business: '직장 주소',
      home: '자택 주소',
      gift:'선물로 보내기',
      sendGift: '선물로 보내기',
      dontSendGift: '선물로 보내기 않기'
    },
  },
  computed:{
    cartItemCount:function(){
      return this.cart.length;
    },
    sortedProducts:function(){
      //{id,name,desc}
      this.products.sort(function(a,b){
        return parseInt(a.price)-parseInt(b.price);
      })
    }
  },
  methods:{
    addToCart:function(product){
      this.cart.push(product.id);
    },
    cartCount:function(id){
      let count = 0;
      for(let i of this.cart){
        if(i==id){
          count++;
        }
      }
      return count;
    },
    canAddToCart:function(product){
      return this.cartCount(product.id) < product.availableInventory;
    },
    checkRating:function(n,product){
      return product.rating>=n;
    },
    showCheckOut:function(){
      this.showProduct=!this.showProduct;
    },
    submitForm:function(){
      
    }
  },
  filters: {
    formatPrice: function (price) {
      //2000.00 -> 2,000.00
      if (price > 99999) {
        var priceString = (price / 100).toFixed(2);
        console.log(priceString);
        var priceAry = priceString.split('').reverse();
        console.log(priceAry);
        var index = 6;
        while (priceAry.length > index) {
          priceAry.splice(index, 0, ',');
          index += 4;
        }
        return '$' + priceAry.reverse().join('');
      } else {
        return '$' + (price / 100).toFixed(2);
      }
    }
  },
  beforeCreate: function () {
    if (APP_LOG_LIFECYCLE_EVENTS) {
      console.log("beforeCreate");
    }
  },
  created: function () {
    if (APP_LOG_LIFECYCLE_EVENTS) {
      console.log("created");
      // fetch('data.json')
      //   .then(reoslve => reoslve.json())
      //   .then(result => {
      //     store.sitename = result.sitename;
      //     console.log(result.sitename);
      //   })
      //   .catch(err => console.log(err));
    }
    axios.get('./products.json')
    .then(result=>{
      console.log(result);
      this.products=result.data.products;
      this.sortedProducts;
    })
  },
  beforeMount: function () {
    if (APP_LOG_LIFECYCLE_EVENTS) {
      console.log("beforeMount");
    }
  },
  mounted: function () {
    if (APP_LOG_LIFECYCLE_EVENTS) {
      console.log("mounted");
    }
  },
  beforeUpdate: function () {
    if (APP_LOG_LIFECYCLE_EVENTS) {
      console.log("beforeUpdate");
    }
  },
  updated: function () {
    if (APP_LOG_LIFECYCLE_EVENTS) {
      console.log("updated");
      // alert('update');
    }
  },
  beforeDestroy: function () {
    if (APP_LOG_LIFECYCLE_EVENTS) {
      console.log("beforeDestroy ");
    }
  },
  destroyed: function () {
    if (APP_LOG_LIFECYCLE_EVENTS) {
      console.log("destroyed");
    }
  }
});